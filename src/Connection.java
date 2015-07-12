/**
 * Created by Andrew on 6/30/2015.
 */
import blackjack_contract.Card;
import blackjack_contract.Message;

import java.io.*;
import java.net.Socket;

public class Connection implements Runnable {

    private Socket blackJackConnection;
   //private DataOutputStream output;
    //private DataInputStream input;
    private ObjectInputStream cardInput;
    private ObjectOutputStream cardOutput;
    private Client client;
    private Card incomingCard;					Declared outside of required scope
    private Message incomingMessage;			Declared outside of required scope


    public Connection(Client client)
    {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            blackJackConnection = new Socket("localhost", 8989);
            //input = new DataInputStream(blackJackConnection.getInputStream());
            //output = new DataOutputStream(blackJackConnection.getOutputStream());

            this.cardOutput = new ObjectOutputStream(new BufferedOutputStream(blackJackConnection.getOutputStream()));

            sendMessageToServer("AndrewGray");

            this.cardInput = new ObjectInputStream(new BufferedInputStream(blackJackConnection.getInputStream()));

            while (blackJackConnection.isConnected() && !(blackJackConnection.isClosed())) {
                try {
                    incomingMessage = (Message) cardInput.readObject();

                    if (incomingMessage.getUsername() == "Client")			Don't use == to compare strings
                    {
                        if (incomingMessage.getMessageType() == Message.Type.ACKNOWLEDGE)		.equals comparison for objects, as well
                        {

                        }
                        else if (incomingMessage.getMessageType() == Message.Type.DENY)
                        {

                        }
                        else if (incomingMessage.getMessageType() == Message.Type.CARD)
                        {
                            client.appendText("You drew a " + incomingMessage.getCard().getValue() + " of " + incomingMessage.getCard().getSuite());
                        }
                    }
                    else
                    {
                        if (incomingMessage.getMessageType() == Message.Type.CHAT)
                        {
                            client.appendText(incomingMessage.getUsername() + ": " + incomingMessage.getText());
                        }
                        // stuff from other people
                    }

                    //client.appendText(incomingMessage.getText());
                } catch (NullPointerException e) {			// XXX Don't forget, use client == null, not an NPE!

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void terminate()
    {
        try{
            cardInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Need to close the socket connection, not just the input (don't forget the output)
    }

    public void sendMessageToServer(String message)
    {
        try {
            cardOutput.writeUTF(message);
            cardOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageObjectToServer(Message message)
    {
        try {
            cardOutput.writeObject(message);
            cardOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
