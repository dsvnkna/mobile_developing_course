package lab.dsvnkna.mobile_lab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private Set<String> errorsSet = new HashSet<>();
    private TextView errorText;
    private EditText inputFirstName;
    private EditText inputLastName;
    private EditText inputEmail;
    private EditText inputPhone;
    private EditText inputPassword;
    private EditText inputPasswordReEnter;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        inputFirstName = findViewById(R.id.input_first_name);
        inputLastName = findViewById(R.id.input_last_name);
        inputEmail = findViewById(R.id.input_email);
        inputPhone = findViewById(R.id.input_mobile);
        inputPassword = findViewById(R.id.input_password);
        inputPasswordReEnter = findViewById(R.id.input_password_conf);
        errorText = findViewById(R.id.error_text);
        Button viewListButton = findViewById(R.id.view_list_button);
        Button submitButton = findViewById(R.id.submit_button);
        setSupportActionBar(toolbar);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userValidation();
                saveAndCleanUserData(view);
            }
        });
        viewListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveAndCleanUserData(View view) {
        if (errorsSet.isEmpty()) {
            SharedPreferences sharedPref = getSharedPreferences(Constants.USER_DATA_KEY,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(Constants.FIRST_NAME_KEY, firstName);
            editor.putString(Constants.LAST_NAME_KEY, lastName);
            editor.putString(Constants.PHONE_KEY, phone);
            editor.putString(Constants.EMAIL_KEY, email);
            editor.apply();
            inputFirstName.setText("");
            inputLastName.setText("");
            inputPhone.setText("");
            inputEmail.setText("");
            inputPassword.setText("");
            inputPasswordReEnter.setText("");
            Snackbar.make(view, R.string.snackbar_valid_info, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        }
    }

    private void userValidation() {
        firstNameValidation();
        lastNameValidation();
        emailValidation();
        phoneValidation();
        passwordValidation();
        errorText.setText(errorsSet.toString());
    }

    private void firstNameValidation() {
        firstName = inputFirstName.getText().toString();
        if (firstName.isEmpty() || firstName.length() < 3) {
            errorsSet.add(getString(R.string.short_first_name_error));
        } else if (Character.isLowerCase(firstName.charAt(0))) {
            errorsSet.add(getString(R.string.upper_case_first_name_error));
        } else {
            errorsSet.remove(getString(R.string.short_first_name_error));
            errorsSet.remove(getString(R.string.upper_case_first_name_error));
        }
    }

    private void lastNameValidation() {
        lastName = inputLastName.getText().toString();
        if (lastName.isEmpty() || lastName.length() < 4) {
            errorsSet.add(getString(R.string.short_last_name_error));
        } else if (Character.isLowerCase(lastName.charAt(0))) {
            errorsSet.add(getString(R.string.upper_case_last_name_error));
        } else {
            errorsSet.remove(getString(R.string.short_last_name_error));
            errorsSet.remove(getString(R.string.upper_case_last_name_error));
        }
    }

    private void emailValidation() {
        email = inputEmail.getText().toString();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorsSet.add(getString(R.string.invalid_email_error));
        } else {
            errorsSet.remove(getString(R.string.invalid_email_error));
        }
    }

    private void phoneValidation() {
        phone = inputPhone.getText().toString();
        if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches()) {
            errorsSet.add(getString(R.string.invalid_phone_error));
        } else {
            errorsSet.remove(getString(R.string.invalid_phone_error));
        }
    }

    private void passwordValidation() {
        String password = inputPassword.getText().toString();
        String passwordReEnter = inputPasswordReEnter.getText().toString();
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            errorsSet.add(getString(R.string.short_password_error));
        } else {
            errorsSet.remove(getString(R.string.short_password_error));
        }

        if (passwordReEnter.isEmpty() || !passwordReEnter.equals(password)) {
            errorsSet.add(getString(R.string.not_equals_password_error));
        } else {
            errorsSet.remove(getString(R.string.not_equals_password_error));
        }
    }
}