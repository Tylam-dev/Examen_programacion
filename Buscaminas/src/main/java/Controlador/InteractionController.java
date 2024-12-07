package Controlador;

import Models.BoardModel;
import Models.ResultModel;

/**
 * La clase InteractionController es responsable de gestionar la interacción entre la vista y el modelo de juego (BoardModel).
 * Actúa como intermediario entre el tablero y la vista, permitiendo iniciar el juego, procesar acciones del jugador
 * y obtener el estado actual del tablero.
 */
public class InteractionController {

    private BoardModel _board = new BoardModel(); // El modelo del tablero de juego.

    /**
     * Inicializa el juego, creando el tablero y colocando las minas.
     */
    public void start() {
        this._board.initializeBoard(); // Inicializa el tablero
    }

    /**
     * Envía una acción (marcar o visitar) al modelo del tablero, procesando la entrada del jugador.
     *
     * @param action La acción a realizar ('V' para visitar, 'X' para marcar).
     * @param row La fila de la celda en la que se va a realizar la acción.
     * @param col La columna de la celda en la que se va a realizar la acción.
     * @return true si el juego ha terminado (ya sea por victoria o derrota), false si continúa.
     */
    public boolean sendAction(char action, int row, int col) {
        return this._board.checkInput(action, row, col); // Envía la acción al tablero para procesarla
    }

    /**
     * Obtiene el estado actual del tablero, incluyendo su tamaño, si el juego ha terminado y las celdas del tablero.
     *
     * @return Un objeto ResultModel con el estado actual del tablero.
     */
    public ResultModel getStatus() {
        return this._board.status(); // Devuelve el estado actual del tablero
    }
}
