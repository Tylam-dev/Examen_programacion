import Exceptions.InvalidMoveException;
import Models.BoardModel;
import Models.Square;
import Models.ResultModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardModelTest {

    @Test
    public void testInitializeBoard() {
        BoardModel board = new BoardModel();
        board.initializeBoard();

        // Verificar que el tablero tiene el tamaño correcto
        assertEquals(100, board.status().getSquares().size(), "El tablero no tiene 100 celdas");

        // Verificar que hay exactamente 10 minas
        long mineCount = board.status().getSquares().stream()
                .filter(Square::getIsMine)
                .count();
        assertEquals(10, mineCount, "El número de minas no es correcto");
    }

    @Test
    public void testPlaceMinesDoesNotOverlap() {
        BoardModel board = new BoardModel();
        board.initializeBoard();

        // Verificar que no hay duplicados en la colocación de minas
        long uniqueMines = board.status().getSquares().stream()
                .filter(Square::getIsMine)
                .distinct()
                .count();
        assertEquals(10, uniqueMines, "Hay minas duplicadas en el tablero");
    }
}
