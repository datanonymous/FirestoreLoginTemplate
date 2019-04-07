package ko.alex.firestorelogintemplate;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //Simplified Coding - Creating a User w/ Firebase Auth
    //https://www.youtube.com/watch?v=mF5MWLsb4cg

    EditText loginEmail, loginPW, registerEmail, registerPW, registerPW2;
    FloatingActionButton loginFAB, registerFAB;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();



    } //End onCreate



} //End MainActivity
