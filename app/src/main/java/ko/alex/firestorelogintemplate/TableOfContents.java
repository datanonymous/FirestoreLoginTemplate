package ko.alex.firestorelogintemplate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class TableOfContents extends AppCompatActivity {

    Button raleighButton, morrisvilleButton, durhamButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_of_contents);

        raleighButton = findViewById(R.id.raleighButton);
        morrisvilleButton = findViewById(R.id.morrisvilleButton);
        durhamButton = findViewById(R.id.durhamButton);

    } //End onCreate



} //End TableOfContents
