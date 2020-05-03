import javax.swing.*;
import java.lang.reflect.InvocationTargetException;


public class Main
{

    public static void main(String[] args) throws InvocationTargetException, InterruptedException
    {
        SwingUtilities.invokeAndWait(() -> new BoardGamesDB());
    }
}
