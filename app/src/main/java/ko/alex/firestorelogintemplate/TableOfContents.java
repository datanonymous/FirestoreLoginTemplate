package ko.alex.firestorelogintemplate;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

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
                startActivity(new Intent(TableOfContents.this, MainActivity.class)); //Can also use getApplicationContext() instead of TableOfContents.this
            }
        });
        raleighButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Raleigh button clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TableOfContents.this, LocationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message", "RALEIGH LOCATION");
                startActivity(intent);
            }
        });
        morrisvilleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Morrisville button clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TableOfContents.this, LocationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message", "MORRISVILLE LOCATION");
                startActivity(intent);
            }
        });
        durhamButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Durham clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TableOfContents.this, LocationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message", "DURHAM LOCATION");
                startActivity(intent);
            }
        });



    } //End onCreate



} //End TableOfContents

