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
    private JPanel panel;
    private JButton connectButton;
    private JButton hitButton;
    private JButton stayButton;
    private JButton sendButton;
    private JScrollPane outputScrollPane;
    private JScrollPane inputScrollPane;
    protected JTextArea chatOutput;
    protected JTextArea chatInput;

    protected abstract void connectToChat();
    protected abstract void sendMessage();
    protected abstract void hit();
    protected abstract void stay();

    protected void startFrame(String buttonText)
    {
        panel = new JPanel();
        chatOutput = new JTextArea();
        chatInput = new JTextArea();
        outputScrollPane = new JScrollPane(chatOutput);
        inputScrollPane = new JScrollPane((chatInput));
        connectButton = new JButton("Connect");
        hitButton = new JButton("Hit");
        stayButton = new JButton("Stay");
        sendButton = new JButton("Send");

        panel.setPreferredSize(new Dimension(500, 700));
        outputScrollPane.setPreferredSize(new Dimension(450, 600));
        inputScrollPane.setPreferredSize((new Dimension(120, 600)));


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

        panel.add(outputScrollPane);
        panel.add(hitButton);
        panel.add(stayButton);
        panel.add(inputScrollPane);
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
