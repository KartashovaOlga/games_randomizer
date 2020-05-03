import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;


public class Main
{

    public static void main(String[] args) throws InvocationTargetException, InterruptedException
    {
        SwingUtilities.invokeAndWait(() -> new BoardGamesDB());
    }
}
