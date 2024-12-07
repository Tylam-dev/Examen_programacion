package View;

import Controlador.InteractionController;
import Models.InputClass;
import Models.ResultModel;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * La clase View maneja la interacción con el usuario en el juego Buscaminas.
 * Es responsable de mostrar el tablero, recibir la entrada del usuario y
 * procesar la acción solicitada (visitar o marcar una celda).
 */
public class View {

    private Scanner _scanner = new Scanner(System.in); // Escáner para leer la entrada del usuario.
    private InteractionController _controller = new InteractionController(); // Controlador para manejar la lógica del juego.

    /**
     * Inicializa el juego, mostrando el mensaje de bienvenida y el estado inicial del tablero.
     * También arranca el controlador y muestra el tablero.
     */
    public void Incialize() {
        System.out.println("¡Bienvenido al Buscaminas!"); // Mensaje de bienvenida.
        System.out.println("Escribe 'V' para visitar o 'X' para marcar (ejemplo: V A5):"); // Instrucciones al jugador.
        this._controller.start(); // Inicializa el controlador del juego.
        var boardStatus = this._controller.getStatus(); // Obtiene el estado actual del tablero.
        this.printBoard(boardStatus); // Muestra el tablero en la consola.
    }

    /**
     * Imprime el estado actual del tablero en la consola.
     * El tablero se muestra con las columnas numeradas y las filas con letras (A-J).
     *
     * @param boardStatus El estado actual del tablero (estado de cada celda).
     */
    private void printBoard(ResultModel boardStatus) {
        // Imprimir encabezado con los números de columna (1-10).
        System.out.print("   ");
        for (int i = 1; i <= boardStatus.getSize(); i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Imprimir las filas con sus respectivas letras (A-J).
        for (int i = 0; i < boardStatus.getSize(); i++) {
            System.out.print((char) ('A' + i) + "  ");
            for (int j = 0; j < boardStatus.getSize(); j++) {
                // Imprimir el estado de cada celda dependiendo de si está revelada, marcada o tiene una mina.
                if (boardStatus.getFinish() && boardStatus.findSquare(i, j).getIsMine()) {
                    System.out.print("* "); // Mina.
                } else if (boardStatus.findSquare(i, j).getHasFlag()) {
                    System.out.print("X "); // Celda marcada.
                } else if (boardStatus.findSquare(i, j).getIsRevealed()) {
                    System.out.print(boardStatus.findSquare(i, j).getMinesAround() + " "); // Mostrar número de minas adyacentes.
                } else {
                    System.out.print(". "); // Celda oculta.
                }
            }
            System.out.println(); // Nueva línea después de cada fila.
        }
    }

    /**
     * Recibe y procesa la entrada del usuario.
     * Si la entrada es válida, envía la acción al controlador y actualiza el estado del tablero.
     *
     * @return true si el juego ha terminado, false de lo contrario.
     */
    public boolean input() {
        System.out.print("Introduce una acción y celda: "); // Solicita la acción y la celda.
        String input = _scanner.nextLine().toUpperCase(); // Lee la entrada del usuario y la convierte a mayúsculas.

        var inputFormated = this.formatInput(input); // Formatea la entrada para extraer la acción y la celda.
        var finished = this._controller.sendAction(inputFormated.getAction(), inputFormated.getRow(), inputFormated.getCol()); // Envía la acción al controlador.

        var boardStatus = this._controller.getStatus(); // Obtiene el estado actualizado del tablero.
        this.printBoard(boardStatus); // Muestra el tablero actualizado.

        return finished; // Retorna si el juego ha terminado.
    }

    /**
     * Formatea la entrada del usuario para extraer la acción ('V' o 'X'), la fila y la columna.
     *
     * @param input La entrada del usuario en formato "Acción FilaColumna" (ej. 'V A5').
     * @return Un objeto InputClass que contiene la acción, la fila y la columna.
     * @throws InputMismatchException Si la entrada no sigue el formato esperado.
     */
    public InputClass formatInput(String input) {
        if (input.matches("(V|X) [A-J][1-9]|(V|X) [A-J]10")) { // Verifica si la entrada sigue el formato esperado.
            char action = input.charAt(0); // Acción: 'V' para visitar o 'X' para marcar.
            int row = input.charAt(2) - 'A'; // Convierte la letra de la fila a un índice (A -> 0, B -> 1, etc.).
            int col = Integer.parseInt(input.substring(3)) - 1; // Convierte el número de columna a un índice (1 -> 0, 10 -> 9).

            return new InputClass(action, row, col); // Devuelve un objeto InputClass con la acción y las coordenadas.
        } else {
            // Si la entrada no es válida, lanza una excepción.
            throw new InputMismatchException("Entrada no válida. Usa formato como 'V A5' o 'X A5'.");
        }
    }
}
