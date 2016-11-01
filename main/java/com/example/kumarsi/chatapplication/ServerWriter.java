package com.example.kumarsi.chatapplication;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by kumarsi on 07-Oct-16.
 */

public class ServerWriter implements Runnable{

    private Socket socket;

    public ServerWriter(Socket socket){

        this.socket = socket;
    }
    @Override

    public void run() {

        try {

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(ServerConnection.getInstance().getSocket().getOutputStream()));
            String message = "blablablaaaa";
            pw.write(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
