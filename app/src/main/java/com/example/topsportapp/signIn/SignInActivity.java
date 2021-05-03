package com.example.topsportapp.signIn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topsportapp.main.MainActivity;
import com.example.topsportapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * --------Testing Account--------
 * |  Username : test@test.com
 * |  Name : test
 * |  Password : testtest        |
 * -------------------------------
 *
 * **/

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = "SignInActivity";
    private static final String PREF_EMAIL_USER = "UserEmail";
    public static final String PREF_NAME = "UserEmailInfo";



    private FirebaseAuth auth;
    private SharedPreferences sharedPreferences;
    private TextInputLayout textInputEmail, textInputName, textInputPassword, textInputConfirmPassword;
    private Button signUpButton, logInButton, startButton, logOutUserButton, toCreateNewUserFormButton, toLoginUserFormButton;
    private TextView resetPasswordTextClickable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
    }

    public void init() {
        auth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences(PREF_NAME,MODE_PRIVATE);

        textInputEmail = findViewById(R.id.textInputEmail);
        textInputName = findViewById(R.id.textInputName);
        textInputPassword = findViewById(R.id.textInputPassword);
        textInputConfirmPassword = findViewById(R.id.textInputConfirmPassword);

        toCreateNewUserFormButton = findViewById(R.id.button_to_create_new_user_form);
        toLoginUserFormButton = findViewById(R.id.button_to_login_user_form);
        signUpButton = findViewById(R.id.signUpButton);
        logInButton = findViewById(R.id.logInButton);
        startButton = findViewById(R.id.startButton);
        logOutUserButton = findViewById(R.id.logOutUserButton);

        resetPasswordTextClickable = findViewById(R.id.resetPasswordTextClickable);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser != null) {
            showIfSigned();
            saveToSharedPreferences();
        } else {
            showIfSignOut();
        }

    }

    private void showIfSigned() {
        textInputEmail.setVisibility(View.GONE);
        textInputName.setVisibility(View.GONE);
        textInputPassword.setVisibility(View.GONE);
        textInputConfirmPassword.setVisibility(View.GONE);

        signUpButton.setVisibility(View.GONE);
        logInButton.setVisibility(View.GONE);

        resetPasswordTextClickable.setVisibility(View.GONE);
        toCreateNewUserFormButton.setVisibility(View.GONE);

        startButton.setVisibility(View.VISIBLE);
        logOutUserButton.setVisibility(View.VISIBLE);
    }

    private void showIfSignOut() {
        textInputEmail.setVisibility(View.VISIBLE);
        textInputPassword.setVisibility(View.VISIBLE);
        logInButton.setVisibility(View.VISIBLE);
        resetPasswordTextClickable.setVisibility(View.VISIBLE);
        toCreateNewUserFormButton.setVisibility(View.VISIBLE);

        textInputName.setVisibility(View.GONE);
        textInputConfirmPassword.setVisibility(View.GONE);
        signUpButton.setVisibility(View.GONE);
        toLoginUserFormButton.setVisibility(View.GONE);

        Toast.makeText(this, "email password", Toast.LENGTH_SHORT).show();
    }

    public void showCreateNewUserFields(View view) {
        textInputEmail.setVisibility(View.VISIBLE);
        textInputName.setVisibility(View.VISIBLE);
        textInputPassword.setVisibility(View.VISIBLE);
        textInputConfirmPassword.setVisibility(View.VISIBLE);
        signUpButton.setVisibility(View.VISIBLE);
        toLoginUserFormButton.setVisibility(View.VISIBLE);

        logInButton.setVisibility(View.GONE);
        toCreateNewUserFormButton.setVisibility(View.GONE);
        resetPasswordTextClickable.setVisibility(View.GONE);

        Toast.makeText(this, "email name password", Toast.LENGTH_SHORT).show();

    }

    public void showLoginUserFields(View view) {
        showIfSignOut();
    }

    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        if(emailInput.isEmpty()){
            textInputEmail.setError("Please input your email");
            return false;
        }else {
            textInputEmail.setError("");
            return true;
        }

    }

    private boolean validateName() {
        String nameInput = textInputName.getEditText().getText().toString().trim();
        if(nameInput.isEmpty()){
            textInputName.setError("Please input your name");
            return false;
        }else if(nameInput.length() > 15){
            textInputName.setError("Name length have to be less than 15");
            return false;
        }
        else {
            textInputName.setError("");
            return true;
        }

    }

    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();
        String confirmPasswordInput = textInputConfirmPassword.getEditText().getText().toString().trim();

        if(passwordInput.isEmpty()){
            textInputPassword.setError("Please input your password");
            return false;
        }else if(passwordInput.length() <7){
            textInputPassword.setError("Password have to be more 6 ");
            return false;
        }
        else if(!confirmPasswordInput.equals(passwordInput)){
            textInputConfirmPassword.setError("Passwords have to match ");
            return false;
        }
        else {
            textInputPassword.setError("");
            textInputConfirmPassword.setError("");
            return true;
        }

    }

    public void signUpUser(View view) {
        if(!validateEmail() | !validateName() | !validatePassword()){
            return;
        }
        auth.createUserWithEmailAndPassword(textInputEmail.getEditText().getText().toString().trim()
                , textInputPassword.getEditText().getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            Toast.makeText(SignInActivity.this, "Authentication successful.",
                                    Toast.LENGTH_SHORT).show();
                            saveToSharedPreferences();
                            startActivity( new Intent(SignInActivity.this,
                                    MainActivity.class) );
                            //   updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                });
    }

    public void logInUser(View view) {
        auth.signInWithEmailAndPassword(textInputEmail.getEditText().getText().toString().trim()
                , textInputPassword.getEditText().getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            saveToSharedPreferences();
                            Toast.makeText(SignInActivity.this, "SIGNED IN ",
                                    Toast.LENGTH_SHORT).show();
                            showIfSigned();
                            //  updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "FAILED",
                                    Toast.LENGTH_SHORT).show();
                            //   updateUI(null);
                        }
                    }
                });

    }

    public void startApp(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void logOutUser(View view) {
        FirebaseAuth.getInstance().signOut();
        showIfSignOut();
    }



    public void resetPassword(View view) {
        Toast.makeText(this, "New Password", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "onBackPressed", Toast.LENGTH_SHORT).show();
    }

    public void saveToSharedPreferences() {
        String actualUserEmail = textInputEmail.getEditText().toString();
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putString(PREF_NAME, actualUserEmail);
        prefEditor.apply();
    }

}