import blackjack_contract.Message;

/**
 * Created by Andrew on 6/30/2015.
 */
public class Client extends blackjackFrame {

    private String out = "";
    private Connection connection;
    private Message message;
    private int handValue;

    @Override
    final protected void sendMessage()
    {
        out = chatInput.getText();
        try {
            connection.sendMessageObjectToServer(Message.createChatMessage(out, "AndrewGray"));
            out = "Client: " + out;
            appendText(out);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        chatInput.setText("");
    }

    @Override
    final protected void connectToChat()
    {
        try {
            connection = new Connection(this);
            Thread start = new Thread(connection);
            start.start();
            chatOutput.append("Connected to server\n");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    final protected void hit()
    {
        connection.sendMessageObjectToServer(Message.createActionMessage(Message.Action.HIT, "AndrewGray"));
    }

    @Override
    final protected void stay()
    {
        connection.sendMessageObjectToServer(Message.createActionMessage(Message.Action.STAY, "AndrewGray"));
    }

    @Override
    final protected void startGame()
    {
        connection.sendMessageObjectToServer(Message.createStartMessage("AndrewGray"));
    }
    public Client()
    {
        Runtime.getRuntime().addShutdownHook(new Thread() {

                public void run () {
                    try {
                        connection.terminate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        });
        this.startFrame("Test");
    }
}
