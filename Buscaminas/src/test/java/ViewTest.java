import View.View;
import Models.InputClass;
import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ViewTest {

    @Test
    public void testFormatInput_ValidInput() {
        View view = new View();

        // Caso válido: "V A5"
        String input1 = "V A5";
        InputClass result1 = view.formatInput(input1);
        assertEquals('V', result1.getAction());
        assertEquals(0, result1.getRow());
        assertEquals(4, result1.getCol());

        // Caso válido: "X J10"
        String input2 = "X J10";
        InputClass result2 = view.formatInput(input2);
        assertEquals('X', result2.getAction());
        assertEquals(9, result2.getRow());
        assertEquals(9, result2.getCol());
    }

    @Test
    public void testFormatInput_InvalidInput_MissingSpace() {
        View view = new View();

        // Verifica que lanza InputMismatchException
        assertThrows(InputMismatchException.class, () -> {
            view.formatInput("VA5");
        });
    }

    @Test
    public void testFormatInput_InvalidInput_OutOfBoundsRow() {
        View view = new View();

        assertThrows(InputMismatchException.class, () -> {
            view.formatInput("V Z5");
        });
    }

    @Test
    public void testFormatInput_InvalidInput_OutOfBoundsCol() {
        View view = new View();

        assertThrows(InputMismatchException.class, () -> {
            view.formatInput("X A11");
        });
    }

    @Test
    public void testFormatInput_InvalidInput_InvalidAction() {
        View view = new View();

        assertThrows(InputMismatchException.class, () -> {
            view.formatInput("Y A5");
        });
    }

    @Test
    public void testFormatInput_InvalidInput_EmptyString() {
        View view = new View();

        assertThrows(InputMismatchException.class, () -> {
            view.formatInput("");
        });
    }
}
