package com.danyhp.mmithb.phonedialkeyboard;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.danyhp.mmithb.phonedialkeyboard.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editText_main);
        if (editText != null)
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_SEND) {
                        dialNumber();
                        handled = true;
                    }
                    return handled;
                }

                private void dialNumber() {
                    EditText editText = findViewById(R.id.editText_main);
                    String phoneNum = null;

                    if (editText != null) phoneNum = "tel:" +
                            editText.getText().toString();
                    Log.d("Tag", "dialNumber: "+phoneNum);

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(phoneNum));

                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Log.d("ImplicitIntents", "Can't handle this!");
                    }
                }
            });
    }
}
