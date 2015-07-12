/**
 * Created by Andrew on 6/30/2015.
 */

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.JTextComponent;

abstract public class blackjackFrame extends JFrame {
    private JPanel panel;					Declared outside of required scope
    private JButton connectButton;			Declared outside of required scope
    private JButton hitButton;				Declared outside of required scope
    private JButton stayButton;				Declared outside of required scope
    private JButton sendButton;				Declared outside of required scope
    private JButton startGameButton;		Declared outside of required scope
    private JScrollPane outputScrollPane;	Declared outside of required scope
    private JScrollPane inputScrollPane;	Declared outside of required scope
    protected JTextArea chatOutput;			Declared outside of required scope
    protected JTextArea chatInput;			Declared outside of required scope

    protected abstract void connectToChat();		// XXX as noted in the Client class, these could be avoided if you weren't using extends
    protected abstract void sendMessage();
    protected abstract void hit();
    protected abstract void stay();
    protected abstract void startGame();

    protected void startFrame(String buttonText)
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 800);

        panel = new JPanel();
        chatOutput = new JTextArea();
        chatInput = new JTextArea();
        outputScrollPane = new JScrollPane(chatOutput);
        inputScrollPane = new JScrollPane((chatInput));
        connectButton = new JButton("Connect");
        startGameButton = new JButton("Start Game");
        hitButton = new JButton("Hit");
        stayButton = new JButton("Stay");
        sendButton = new JButton("Send");

        panel.setPreferredSize(new Dimension(575, 775));
        outputScrollPane.setPreferredSize(new Dimension(550, 400));	// XXX The same effect can be achieved by
        inputScrollPane.setPreferredSize((new Dimension(550, 150)));	// using rows/columns on the JTextArea constructor


        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToChat();
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hit();
            }
        });
        stayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stay();
            }
        });
        startGameButton.addActionListener(new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent e) { startGame(); }
        });

        panel.add(outputScrollPane);
        panel.add(inputScrollPane);
        panel.add(startGameButton);
        panel.add(hitButton);
        panel.add(stayButton);
        panel.add(connectButton);
        panel.add(sendButton);
        add(panel);
        setVisible(true);


    }



    final protected void appendText(String text)
    {
        chatOutput.append(text + "\n");
        chatOutput.setCaretPosition(chatOutput.getDocument().getLength());
        chatInput.setText("");
        chatInput.requestFocus();
    }
}
