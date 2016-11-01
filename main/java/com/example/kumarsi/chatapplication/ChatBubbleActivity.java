package com.example.kumarsi.chatapplication;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by kumarsi on 08-Oct-16.
 */

public class ChatBubbleActivity extends Activity {
    private static final String TAG = "ChatActivity";

    private ChatActivity chatActivity;
    private ListView listView;
    private EditText commentField;
    private Button sendButton;

    Intent intent;
    private boolean side = false;
}
