package Models;

import java.util.Random;
import java.util.Scanner;

public class BoardModel {
    private int SIZE = 10;
    private int MINES = 10; // Número de minas
    private char[][] board = new char[SIZE][SIZE];
    private boolean[][] mines = new boolean[SIZE][SIZE];
    private boolean[][] revealed = new boolean[SIZE][SIZE];
    private boolean[][] flags = new boolean[SIZE][SIZE];
    private boolean finished = false;
    public boolean checkInput(char action, int row, int col){

        if (action == 'V') {
            if (flags[row][col]) {
                System.out.println("La celda está marcada con 'X'. No puedes visitarla.");
            } else if (mines[row][col]) {
                System.out.println("¡Boom! Has pisado una mina. Fin del juego.");
                this.finished = true;
                return true;
            } else {
                revealCells(row, col);
                return false;
            }
        } else if (action == 'X') {
            flags[row][col] = !flags[row][col]; // Alternar marca
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
        placeMines();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '.';
            }
        }
    }
    // Colocar minas aleatoriamente
    private void placeMines() {
        Random rand = new Random();
        int placedMines = 0;

        while (placedMines < this.MINES) {
            int row = rand.nextInt(this.SIZE);
            int col = rand.nextInt(this.SIZE);
            if (!mines[row][col]) {
                mines[row][col] = true;
                placedMines++;
            }
        }
    }
    // Revelar celdas adyacentes
    private void revealCells(int row, int col) {
        if (row < 0 || row >= this.SIZE || col < 0 || col >= this.SIZE || revealed[row][col]) {
            return; // Fuera de límites o ya revelada
        }

        revealed[row][col] = true;
        int adjacentMines = countAdjacentMines(row, col);

        if (adjacentMines > 0) {
            board[row][col] = (char) (adjacentMines + '0'); // Mostrar número de minas adyacentes
        } else {
            board[row][col] = 'V'; // Marcar como visitada
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
                if (newRow >= 0 && newRow < this.SIZE && newCol >= 0 && newCol < this.SIZE && this.mines[newRow][newCol]) {
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
                if (this.mines[i][j] && !this.flags[i][j]) {
                    return false; // Hay una mina no marcada
                }
                if (!this.mines[i][j] && this.flags[i][j]) {
                    return false; // Hay una celda marcada incorrectamente
                }
            }
        }
        return true;
    }
    public ResultModel status (){
        return  new ResultModel(this.SIZE,this.MINES,this.finished, this.board, this.mines, this.revealed, this.flags);
    }
}
