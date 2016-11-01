package com.example.kumarsi.chatapplication;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by kumarsi on 07-Oct-16.
 */
public class ServerConnection {

    private Socket socket;

    private static ServerConnection ourInstance = new ServerConnection();

    public static ServerConnection getInstance() {
        return ourInstance;
    }

    private ServerConnection() {
    }

    public void connect(){

        try {

            this.socket = new Socket("10.0.2.2",62231);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket(){

        return this.socket;
    }
}
