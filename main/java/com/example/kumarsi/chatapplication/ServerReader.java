package com.example.kumarsi.chatapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


/**
 * Created by kumarsi on 05-Oct-16.
 */
public class ServerReader implements Runnable {

    private Socket socket;

    public ServerReader(Socket socket) {

        this.socket = socket;

    }

    @Override
    public void run() {

        ChatHistory history = ChatHistory.getInstance();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(true){

                String input = in.readLine();

                Log.d("Message: ",input);
                history.insert(input);

            }

            } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


}
