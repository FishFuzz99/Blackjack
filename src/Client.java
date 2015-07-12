import blackjack_contract.Message;

/**
 * Created by Andrew on 6/30/2015.
 */
public class Client extends blackjackFrame {
	// XXX It's useless for something to be serializable if it doesn't include the serialVersionUID value

    private String out = "";
    private Connection connection;
    private Message message;					// XXX remove unused values
    private int handValue;

    You've broken encapsulation throughout - using the "extends" keyword and "protected" methods
    is NOT a valid way to access other class methods (in any Object Oriented language, not just Java)
    @Override
    final protected void sendMessage()
    {
        out = chatInput.getText();
        try {
            connection.sendMessageObjectToServer(Message.createChatMessage(out, "AndrewGray"));
            out = "Client: " + out;
            appendText(out);

        }
        catch (Exception e) Good to handle exceptions, but this is nearly useless - can't say anything about what went wrong
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
        this.startFrame("Test");				// XXX Don't have to use this, unless there is other ambiguity
    }
}
