package Models;

import Exceptions.InvalidMoveException;
import Interfaces.IFinder;

import java.util.ArrayList;
import java.util.Random;

/**
 * La clase BoardModel representa el tablero de juego del Buscaminas.
 * Gestiona las celdas del tablero, el estado del juego, la colocación de minas,
 * la verificación de acciones del jugador y la revelación de celdas.
 */
public class BoardModel implements IFinder {

    private int SIZE = 10; // Tamaño del tablero (10x10).
    private int MINES = 10; // Número de minas a colocar en el tablero.
    private ArrayList<Square> squares = new ArrayList<Square>(); // Lista de celdas en el tablero.
    private boolean finished = false; // Indica si el juego ha terminado.

    /**
     * Verifica la entrada del jugador (acción y celda) y ejecuta la acción correspondiente.
     * Si la acción es 'V' (visitar) y la celda contiene una mina, termina el juego.
     * Si la acción es 'X' (marcar), alterna la marca en la celda.
     *
     * @param action La acción a realizar ('V' para visitar, 'X' para marcar).
     * @param row La fila de la celda seleccionada.
     * @param col La columna de la celda seleccionada.
     * @return true si el juego ha terminado, false de lo contrario.
     * @throws InvalidMoveException Si se intenta visitar una celda marcada.
     */
    public boolean checkInput(char action, int row, int col) {
        if (action == 'V') { // Acción de visitar
            if (this.findSquare(row, col).getHasFlag()) {
                throw new InvalidMoveException("La celda está marcada con 'X'. No puedes visitarla.");
            } else if (this.findSquare(row, col).getIsMine()) {
                System.out.println("¡Boom! Has pisado una mina. Fin del juego.");
                this.finished = true; // Fin del juego
                return true;
            } else {
                revealCells(row, col); // Revela celdas adyacentes si no hay mina
                return false;
            }
        } else if (action == 'X') { // Acción de marcar
            this.findSquare(row, col).setHasFlag(!this.findSquare(row, col).getHasFlag()); // Alterna la marca de la celda
            if (checkVictory()) { // Verifica si se ha ganado
                System.out.println("¡Felicidades! Has marcado correctamente todas las minas.");
                this.finished = true; // Fin del juego
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Inicializa el tablero con celdas ocultas y coloca las minas aleatoriamente.
     */
    public void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.squares.add(new Square(i, this.positionYConvert(j))); // Crea una celda por cada posición
            }
        }
        placeMines(); // Coloca las minas aleatoriamente en el tablero
    }

    /**
     * Coloca las minas aleatoriamente en el tablero hasta alcanzar el número especificado.
     */
    private void placeMines() {
        Random rand = new Random();
        int placedMines = 0;

        // Coloca minas aleatorias hasta que se hayan colocado todas
        while (placedMines < this.MINES) {
            int row = rand.nextInt(this.SIZE);
            int col = rand.nextInt(this.SIZE);
            if (!this.findSquare(row, col).getIsMine()) { // Si la celda no tiene mina, coloca una mina
                this.findSquare(row, col).setIsMine(true);
                placedMines++;
            }
        }
    }

    /**
     * Revela una celda y sus celdas adyacentes si no contienen minas.
     * La revelación es recursiva para celdas vacías (sin minas adyacentes).
     *
     * @param row La fila de la celda a revelar.
     * @param col La columna de la celda a revelar.
     */
    private void revealCells(int row, int col) {
        // Verifica si la celda está dentro de los límites del tablero y si no está ya revelada
        if (row < 0 || row >= this.SIZE || col < 0 || col >= this.SIZE || this.findSquare(row, col).getIsRevealed()) {
            return; // Fuera de límites o ya revelada
        }

        this.findSquare(row, col).setIsRevealed(true); // Marca la celda como revelada
        int adjacentMines = countAdjacentMines(row, col); // Cuenta las minas adyacentes

        if (adjacentMines > 0) {
            this.findSquare(row, col).setMinesAround((char) (adjacentMines + '0')); // Muestra el número de minas adyacentes
        } else {
            this.findSquare(row, col).setMinesAround('V'); // Marca como visitada si no hay minas adyacentes
            // Expande recursivamente la revelación a las celdas vecinas
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 || j != 0) { // No vuelve a la celda actual
                        revealCells(row + i, col + j);
                    }
                }
            }
        }
    }

    /**
     * Cuenta cuántas minas hay alrededor de una celda.
     *
     * @param row La fila de la celda.
     * @param col La columna de la celda.
     * @return El número de minas adyacentes a la celda.
     */
    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < this.SIZE && newCol >= 0 && newCol < this.SIZE && this.findSquare(newRow, newCol).getIsMine()) {
                    count++; // Cuenta las minas adyacentes
                }
            }
        }
        return count;
    }

    /**
     * Verifica si el jugador ha ganado, es decir, si ha marcado todas las minas correctamente
     * y no ha marcado celdas incorrectas.
     *
     * @return true si el jugador ha ganado, false de lo contrario.
     */
    private boolean checkVictory() {
        for (int i = 0; i < this.SIZE; i++) {
            for (int j = 0; j < this.SIZE; j++) {
                // Verifica si todas las minas están marcadas y si no hay celdas incorrectamente marcadas
                if (this.findSquare(i, j).getIsMine() && !this.findSquare(i, j).getHasFlag()) {
                    return false; // Hay una mina no marcada
                }
                if (!this.findSquare(i, j).getIsMine() && !this.findSquare(i, j).getHasFlag()) {
                    return false; // Hay una celda no marcada incorrectamente
                }
            }
        }
        return true; // El jugador ha ganado
    }

    /**
     * Devuelve el estado actual del tablero.
     *
     * @return Un objeto ResultModel con el tamaño del tablero, si el juego ha terminado y las celdas.
     */
    public ResultModel status() {
        return new ResultModel(this.SIZE, this.finished, this.squares); // Devuelve el estado del tablero
    }

    /**
     * Busca una celda en el tablero por sus coordenadas (fila y columna).
     *
     * @param positionX La fila de la celda.
     * @param positionY La columna de la celda.
     * @return El objeto Square correspondiente a las coordenadas dadas.
     */
    @Override
    public Square findSquare(int positionX, int positionY) {
        var charPositionY = this.positionYConvert(positionY); // Convierte la columna a un carácter
        var foundSquare = this.squares.stream().filter(s -> positionX == s.getPositionX() && charPositionY == s.getPositionY()).findFirst(); // Busca la celda
        return foundSquare.get(); // Devuelve la celda encontrada
    }

    /**
     * Convierte la columna numérica a su representación en caracteres (A-J).
     *
     * @param position La columna a convertir (0-9).
     * @return El carácter correspondiente a la columna.
     */
    @Override
    public char positionYConvert(int position) {
        var positions = new char[]{'A', 'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'M'}; // Mapeo de columnas
        return positions[position]; // Devuelve el carácter correspondiente
    }
}