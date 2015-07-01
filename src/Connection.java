/**
 * Created by Andrew on 6/30/2015.
 */
import java.io.*;
import java.net.Socket;

public class Connection implements Runnable {

    private Socket blackJackConnection;
   //private DataOutputStream output;
    //private DataInputStream input;
    private ObjectInputStream cardInput;
    private ObjectOutputStream cardOutput;
    private Client client;


    public Connection(Client client)
    {
        this.client = client;
    }

    @Override
    public void run()
    {
        try {
            blackJackConnection = new Socket("localhost", 8989);
            //input = new DataInputStream(blackJackConnection.getInputStream());
            //output = new DataOutputStream(blackJackConnection.getOutputStream());
            cardInput = new ObjectInputStream(blackJackConnection.getInputStream());
            cardOutput = new ObjectOutputStream(blackJackConnection.getOutputStream());

            while(blackJackConnection.isConnected() && !(blackJackConnection.isClosed()))
            {
                try
                {
                    Card incomingCard = cardInput.readObject();

                    client.appendText("Extract card info");
                }
                catch (NullPointerException e)
                {

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
