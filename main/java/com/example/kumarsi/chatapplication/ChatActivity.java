package com.example.kumarsi.chatapplication;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ChatActivity extends Activity implements HistoryObserver {

    private EditText messageInput;
    private ServerWriter sw;
    private TextView comments;
    private Button sendButton;
    private Button exit;
    final Context context = this;
    private LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageInput = (EditText) findViewById(R.id.commentField);
        sendButton = (Button) findViewById(R.id.sendButton);
        exit = (Button) findViewById(R.id.exit);

        ChatHistory.getInstance().register(this);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("ChatApp", "message sent");
                postMessage();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder confirmExit = new AlertDialog.Builder(context);
                confirmExit.setTitle("WE CHAT");

                confirmExit.setMessage("Are you sure you want to exit?");
                confirmExit.setCancelable(false);
                confirmExit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                });

                confirmExit.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog confirmScreen = confirmExit.create();
                confirmScreen.show();
            }
        });
    }

    public void postMessage() {

        final String message = messageInput.getText().toString();
        Log.d("moi", "hello" + message);
        if (message.equals("")) {
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(ServerConnection.getInstance().getSocket().getOutputStream()));

                    pw.println(message);
                    pw.flush();
                    //pw.close();

                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void update(final String s) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                comments = (TextView) findViewById(R.id.chatText);
                comments.setMovementMethod(new ScrollingMovementMethod());
                comments.append(s + "\n");
            }
        });
    }
}
