package org.example;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int SIZE = 10;
    private static final int MINES = 15; // Número de minas
    private static char[][] board = new char[SIZE][SIZE];
    private static boolean[][] mines = new boolean[SIZE][SIZE];
    private static boolean[][] revealed = new boolean[SIZE][SIZE];
    public static void main(String[] args) {
        initializeBoard();
        placeMines();

        Scanner scanner = new Scanner(System.in);
        System.out.println("¡Bienvenido al Buscaminas!");
        System.out.println("Escribe una coordenada para descubrir (ejemplo: A5):");
        printBoard(false); // Imprimir sin mostrar minas

        while (true) {
            System.out.print("Introduce una celda: ");
            String input = scanner.nextLine().toUpperCase();
            if (input.matches("[A-J][1-9]|[A-J]10")) {
                int row = input.charAt(0) - 'A';
                int col = Integer.parseInt(input.substring(1)) - 1;

                if (mines[row][col]) {
                    System.out.println("¡Boom! Has pisado una mina. Fin del juego.");
                    printBoard(true); // Imprimir mostrando minas
                    break;
                } else {
                    revealCells(row, col);
                    printBoard(false);
                }
            } else {
                throw new InputMismatchException("Coordenada no válida. Usa formato como A5.");
            }
        }

        scanner.close();
    }

    // Inicializar el tablero con celdas ocultas
    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '.';
            }
        }
    }

    // Colocar minas aleatoriamente
    private static void placeMines() {
        Random rand = new Random();
        int placedMines = 0;

        while (placedMines < MINES) {
            int row = rand.nextInt(SIZE);
            int col = rand.nextInt(SIZE);
            if (!mines[row][col]) {
                mines[row][col] = true;
                placedMines++;
            }
        }
    }

    // Revelar celdas adyacentes
    private static void revealCells(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || revealed[row][col]) {
            return; // Fuera de límites o ya revelada
        }

        revealed[row][col] = true; // Marcar como revelada
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
    private static int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < SIZE && newCol >= 0 && newCol < SIZE && mines[newRow][newCol]) {
                    count++;
                }
            }
        }
        return count;
    }

    // Imprimir tablero
    private static void printBoard(boolean revealMines) {
        System.out.print("   ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print((char) ('A' + i) + "  ");
            for (int j = 0; j < SIZE; j++) {
                if (revealMines && mines[i][j]) {
                    System.out.print("* ");
                } else if (revealed[i][j]) {
                    System.out.print(board[i][j] + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}