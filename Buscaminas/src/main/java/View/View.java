package View;

import Controlador.InteractionController;
import Exceptions.InvalidMoveException;
import Models.ResultModel;

import java.util.Scanner;

public class View {
    private Scanner _scanner = new Scanner(System.in);
    private InteractionController _controller = new InteractionController();
    public void Incialize(){
        System.out.println("¡Bienvenido al Buscaminas!");
        System.out.println("Escribe 'V' para visitar o 'X' para marcar (ejemplo: V A5):");
        this._controller.start();
        var boardStatus = this._controller.getStatus();
        this.printBoard(boardStatus);
    }
    private void printBoard(ResultModel boardStatus) {
        System.out.print("   ");
        for (int i = 1; i <= boardStatus.getSize(); i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < boardStatus.getSize(); i++) {
            System.out.print((char) ('A' + i) + "  ");
            for (int j = 0; j < boardStatus.getSize(); j++) {
                if (boardStatus.getFinish() && boardStatus.findSquare(i,j).getIsMine()) {
                    System.out.print("* ");
                } else if (boardStatus.findSquare(i,j).getHasFlag()) {
                    System.out.print("X ");
                } else if (boardStatus.findSquare(i,j).getIsRevealed()) {
                    System.out.print(boardStatus.findSquare(i,j).getMinesAround() + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
    public boolean input(){
        System.out.print("Introduce una acción y celda: ");
        String input = _scanner.nextLine().toUpperCase();
        if (input.matches("(V|X) [A-J][1-9]|(V|X) [A-J]10")) {
            char action = input.charAt(0); // Acción: 'V' (visitar) o 'X' (marcar)
            int row = input.charAt(2) - 'A';
            int col = Integer.parseInt(input.substring(3)) - 1;
            var finished = this._controller.sendAction(action,row,col);
            var boardStatus = this._controller.getStatus();
            this.printBoard(boardStatus);
            return finished;
        } else {
            throw new InvalidMoveException("Entrada no válida. Usa formato como 'V A5' o 'X A5'.");
        }
    }
}
