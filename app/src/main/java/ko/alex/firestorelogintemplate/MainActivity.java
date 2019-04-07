package ko.alex.firestorelogintemplate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //Simplified Coding - Creating a User w/ Firebase Auth
    //https://www.youtube.com/watch?v=mF5MWLsb4cg

    EditText loginEmail, loginPW, registerEmail, registerPW, registerPW2;
    FloatingActionButton loginFAB, registerFAB;
    FirebaseAuth mAuth;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        //LOGIN
        loginEmail = findViewById(R.id.loginEmail);
        loginPW = findViewById(R.id.loginPW);
        loginFAB = findViewById(R.id.loginFAB);

        //REGISTER
        registerEmail = findViewById(R.id.registerEmail);
        registerPW = findViewById(R.id.registerPW);
        registerPW2 = findViewById(R.id.registerPW2);
        registerFAB = findViewById(R.id.registerFAB);

        loginFAB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                userLogin();
                Toast.makeText(getApplicationContext(), "User Login clicked", Toast.LENGTH_SHORT).show();
            }
        });
        registerFAB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //userLogin();
                finish(); //https://stackoverflow.com/questions/10847526/what-exactly-activity-finish-method-is-doing
                Toast.makeText(getApplicationContext(), "Registration clicked", Toast.LENGTH_SHORT).show();
                //TODO: startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });

    } //End onCreate

    private void userLogin() {
        String email = loginEmail.getText().toString().trim(); //.trim() removes whitespace from either side
        String password = loginPW.getText().toString().trim();

        if (email.isEmpty()) {
            loginEmail.setError("Email is required");
            loginEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginEmail.setError("Please enter a valid email");
            loginEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            loginPW.setError("Password is required");
            loginPW.requestFocus();
            return;
        }

        if (password.length() < 6) {
            loginPW.setError("Minimum length of password should be 6");
            loginPW.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish(); //https://stackoverflow.com/questions/10847526/what-exactly-activity-finish-method-is-doing
                    Intent intent = new Intent(MainActivity.this, TableOfContents.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            finish(); //https://stackoverflow.com/questions/10847526/what-exactly-activity-finish-method-is-doing
            startActivity(new Intent(this, TableOfContents.class));
        }
    }

} //End MainActivity
