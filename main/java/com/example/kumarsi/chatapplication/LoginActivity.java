package com.example.kumarsi.chatapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class LoginActivity extends Activity {

    Button button;
    EditText set_username;
    boolean user;

    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        set_username = (EditText) findViewById(R.id.set_username);
        user = false;

        new Thread(new Runnable() {
            @Override
            public void run() {

                ServerConnection.getInstance().connect();
                new Thread (new ServerReader(ServerConnection.getInstance().getSocket())).start();

            }
        }).start();

        addListenerOnButton();
    }

        private void addListenerOnButton() {

            final Context context = this;
            Button login = (Button)findViewById(R.id.loginButton);

            login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUsername();
                Intent intent = new Intent(context,ChatActivity.class);
                startActivity(intent);

                Log.d("Client", "Username set");
            }
        });
    }

    public void setUsername(){

        final String username = set_username.getText().toString();
        Log.d("Client", "setting username");

        if(!username.equals("")){
            //Log.d("Application","Invalid username.");
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        PrintWriter pw= new PrintWriter(new OutputStreamWriter(ServerConnection.getInstance().getSocket().getOutputStream()));

                        pw.println(username);
                        pw.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
