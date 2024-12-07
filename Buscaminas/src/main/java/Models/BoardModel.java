package Models;

import Interfaces.IFinder;

import java.util.ArrayList;
import java.util.Random;

public class BoardModel implements IFinder {
    private int SIZE = 10;
    private int MINES = 10;// Número de minas
    private ArrayList<Square> squares = new ArrayList<Square>();
    private boolean finished = false;
    public boolean checkInput(char action, int row, int col){
        if (action == 'V') {
            if (this.findSquare(row,col).getHasFlag()) {
                System.out.println("La celda está marcada con 'X'. No puedes visitarla.");
            } else if (this.findSquare(row,col).getIsMine()) {
                System.out.println("¡Boom! Has pisado una mina. Fin del juego.");
                this.finished = true;
                return true;
            } else {
                revealCells(row, col);
                return false;
            }
        } else if (action == 'X') {
            this.findSquare(row,col).setHasFlag(!this.findSquare(row,col).getHasFlag()); // Alternar marca
            if (checkVictory()) {
                System.out.println("¡Felicidades! Has marcado correctamente todas las minas.");
                this.finished = true;
                return true;
            }
            return false;
        }
        return false;
    }

    // Inicializar el tablero con celdas ocultas
    public void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.squares.add(new Square(i,this.positionYConvert(j)));
            }
        }
        placeMines();
    }
    // Colocar minas aleatoriamente
    private void placeMines() {
        Random rand = new Random();
        int placedMines = 0;

        while (placedMines < this.MINES) {
            int row = rand.nextInt(this.SIZE);
            int col = rand.nextInt(this.SIZE);
            if (!this.findSquare(row,col).getIsMine()) {
                this.findSquare(row,col).setIsMine(true);
                placedMines++;
            }
        }
    }
    // Revelar celdas adyacentes
    private void revealCells(int row, int col) {
        if (row < 0 || row >= this.SIZE || col < 0 || col >= this.SIZE || this.findSquare(row,col).getIsRevealed()) {
            return; // Fuera de límites o ya revelada
        }

        this.findSquare(row,col).setIsRevealed(true);
        int adjacentMines = countAdjacentMines(row, col);

        if (adjacentMines > 0) {
            this.findSquare(row, col).setMinesAround((char) (adjacentMines + '0')); // Mostrar número de minas adyacentes
        } else {
            this.findSquare(row, col).setMinesAround('V'); // Marcar como visitada
            // Expandir recursivamente a celdas vecinas
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i != 0 || j != 0) { // No volver a la celda actual
                        revealCells(row + i, col + j);
                    }
                }
            }
        }
    }
    // Contar minas adyacentes
    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < this.SIZE && newCol >= 0 && newCol < this.SIZE && this.findSquare(newRow, newCol).getIsMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    // Verificar victoria
    private boolean checkVictory() {
        for (int i = 0; i < this.SIZE; i++) {
            for (int j = 0; j < this.SIZE; j++) {
                if (this.findSquare(i,j).getIsMine() && !this.findSquare(i,j).getHasFlag()) {
                    return false; // Hay una mina no marcada
                }
                if (!this.findSquare(i,j).getIsMine() && !this.findSquare(i,j).getHasFlag()) {
                    return false; // Hay una celda marcada incorrectamente
                }
            }
        }
        return true;
    }
    public ResultModel status (){
        return  new ResultModel(this.SIZE,this.finished, this.squares);
    }
    @Override
    public Square findSquare(int positionX, int positionY){
        var charPositionY = this.positionYConvert(positionY);
        var foundSquare = this.squares.stream().filter(s -> positionX == s.getPositionX() && charPositionY == s.getPositionY()).findFirst();
        return foundSquare.get();
    }
    @Override
    public char positionYConvert(int position){
        var positions = new char[]{'A','B','C','D','F','G','H','J','K','M','N'};
        return positions[position];
    }
}
