/**
 * Created by Andrew on 6/30/2015.
 */
import blackjack_contract.Card;

import java.io.*;
import java.net.Socket;

public class Connection implements Runnable {

    private Socket blackJackConnection;
   //private DataOutputStream output;
    //private DataInputStream input;
    private ObjectInputStream cardInput;
    private ObjectOutputStream cardOutput;
    private Client client;
    private Card incomingCard;
    private String incomingString;


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
            this.cardInput = new ObjectInputStream(new BufferedInputStream(blackJackConnection.getInputStream()));
            this.cardOutput = new ObjectOutputStream(new BufferedOutputStream(blackJackConnection.getOutputStream()));

            while (blackJackConnection.isConnected() && !(blackJackConnection.isClosed())) {
                try {
                    incomingCard = (Card) cardInput.readObject();
                    //incomingString - cardInput.readUTF();

                    client.appendText("Extract card info");
                } catch (NullPointerException e) {

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

    public void sendCardToServer(Card card)
    {
        try {
            cardOutput.writeObject(card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
