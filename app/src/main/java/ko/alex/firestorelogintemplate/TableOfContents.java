package ko.alex.firestorelogintemplate;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class TableOfContents extends AppCompatActivity {

    Button raleighButton, morrisvilleButton, durhamButton, loginScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_of_contents);

        raleighButton = findViewById(R.id.raleighButton);
        morrisvilleButton = findViewById(R.id.morrisvilleButton);
        durhamButton = findViewById(R.id.durhamButton);

        loginScreen = findViewById(R.id.loginScreen);
        loginScreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FirebaseAuth.getInstance().signOut();
                finish(); //https://stackoverflow.com/questions/10847526/what-exactly-activity-finish-method-is-doing
                Toast.makeText(getApplicationContext(), "Login screen clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    } //End onCreate



} //End TableOfContents

