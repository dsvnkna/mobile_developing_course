package lab.dsvnkna.mobile_lab;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText inputName = findViewById(R.id.input_name);
        final Button clearButton = findViewById(R.id.clear_button);
        final Button sayHelloButton = findViewById(R.id.hello_button);
        final TextView sayHelloText = findViewById(R.id.hello_my_name);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        sayHelloText.setText(R.string.default_text);
        setSupportActionBar(toolbar);


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputName.setText("");
                sayHelloText.setText(R.string.default_text);
            }
        });

        sayHelloButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (inputName.getText().toString().equals("")) {
                    Snackbar.make(view, R.string.empty_name_message, Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                } else {
                    sayHelloText.setText("Hello " + inputName.getText() + "!");
                }
            }
        });

    }
}
