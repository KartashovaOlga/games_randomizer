import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class UIInterface extends JFrame
{
    JButton addB = new JButton();
    JButton showAll = new JButton();
    JButton deleteButton = new JButton();
    JButton playNow = new JButton();

    JTextField nameFd = new JTextField("name", 20);
    JTextField playersMinFd = new JTextField("players min", 10);
    JTextField playersMaxFd = new JTextField("players max", 10);
    JTextField gameTimeFd = new JTextField("game time", 10);
    JTextField gameTypeFd = new JTextField("game type", 10);
    JTextField gameSubTypeFd = new JTextField("game subtype", 20);
    JTextField complexityFd = new JTextField("complexity", 20);
    JTextArea outputF = new JTextArea(200,7);

    JPanel panelNewGame = new JPanel(new GridLayout());
    JScrollPane  outputPanel = new JScrollPane (outputF);
    JPanel buttonsPanel = new JPanel(new GridLayout());

    BoardGamesDB boardGamesDB = new BoardGamesDB();

    public UIInterface()
    {
        super();
        this.setTitle("BOARD GAMES");
        this.setSize(1500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(makeTextPanel(panelNewGame, nameFd, playersMinFd, playersMaxFd, gameTimeFd, gameTypeFd, gameSubTypeFd,complexityFd));
        this.add(makeButtonPanel(buttonsPanel, addButton(), showAllButton(), playNowButton(), deleteButton()));
        this.add(outputPanel);
        this.setResizable(true);
        this.setLayout(new GridLayout(7, 5));
        this.setVisible(true);
    }

    public void printToOutput(String outputText)
    {
        outputF.append(outputText);
    }

    private JPanel makeTextPanel(JPanel panel, JTextField ...textFields)
    {
        for(int i = 0; i < textFields.length; i++)
        {
            int finalI = i;

            String text = textFields[i].getText();
            textFields[i].setForeground(Color.GRAY);
            textFields[i].addFocusListener(new FocusAdapter()
            {
                @Override
                public void focusGained(FocusEvent e)
                {
                    textFields[finalI].setForeground(Color.BLACK);
                    textFields[finalI].setText("");
                }

                @Override
                public void focusLost(FocusEvent e)
                {
                    if (textFields[finalI].getText().isEmpty())
                    {
                        textFields[finalI].setForeground(Color.GRAY);
                        textFields[finalI].setText(text);
                    }
                }
            });

            panel.add(textFields[i]);
        }

        return panel;
    }

    private JPanel makeButtonPanel(JPanel panel, JButton ...buttons)
    {
        for(int i = 0; i < buttons.length; i++)
        {
            panel.add(buttons[i]);
        }

        return panel;
    }

    private JButton addButton()
    {
        addB.setText("ADD NEW GAME");
        try {
            addB.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    outputF.setText("");
                    boardGamesDB.addEntry(nameFd.getText(),
                            Integer.parseInt(playersMinFd.getText()),
                            Integer.parseInt(playersMaxFd.getText()),
                            Integer.parseInt(gameTimeFd.getText()),
                            Integer.parseInt(gameTypeFd.getText()),
                            Integer.parseInt(gameSubTypeFd.getText()),
                            Integer.parseInt(complexityFd.getText()));

                    nameFd.setText("");
                    playersMinFd.setText("");
                    playersMaxFd.setText("");
                    gameTimeFd.setText("");
                    gameTypeFd.setText("");
                    gameSubTypeFd.setText("");
                    complexityFd.setText("");
                }
            });
        }
        catch (Exception ex)
        {
            printToOutput("Something goes wrong\nCheck input\n");
        }

        return addB;
    }

    private JButton deleteButton()
    {
        deleteButton.setText("DELETE GAME");
        try {
            deleteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    outputF.setText("");
                    boardGamesDB.deleteEntry(nameFd.getText());
                    nameFd.setText("");
                }
            });
        }
        catch (Exception ex)
        {
            printToOutput("Something goes wrong\nCheck input\n");
        }

        return deleteButton;
    }

    private JButton showAllButton()
    {
        showAll.setText("SHOW ALL");
        try {
            showAll.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    outputF.setText("");
                    boardGamesDB.showAll();
                }
            });
        }
        catch (Exception ex)
        {
            printToOutput("Something goes wrong\nCheck input\n");
        }

        return showAll;
    }

    private JButton playNowButton()
    {
        playNow.setText("PLAY NOW");

        try {
            playNow.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    outputF.setText("");
                    boardGamesDB.playNow(playersMinFd.getText(), gameTimeFd.getText());
                }
            });
        }
        catch (Exception ex)
        {
            printToOutput("Something goes wrong\nCheck input\n");
        }

        return playNow;
    }
}
