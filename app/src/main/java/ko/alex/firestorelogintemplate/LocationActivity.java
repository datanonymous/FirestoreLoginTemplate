package ko.alex.firestorelogintemplate;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LocationActivity extends AppCompatActivity {

    TextView textViewVerification, gymChoice, textViewTitle1, textViewTitle2;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mAuth = FirebaseAuth.getInstance();

        //Trying to get what the user picked before
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");



        //SETTING TITLE INFORMATION
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                        final String email = user.getEmail();
//                        final String uid = user.getUid();
        textViewTitle1 = findViewById(R.id.textViewTitle1);
        textViewTitle2 = findViewById(R.id.textViewTitle2);
        textViewTitle1.setText("Location: " + message);
        if (user != null) {
            if (user.isEmailVerified()) {
                textViewTitle2.setText("User: " + user.getEmail() + " -> Email Verified");
            } else {
                textViewTitle2.setText("Email not verified (Click to Verify)");
                textViewTitle2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(LocationActivity.this, "Verification Email Sent", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        } //End user email verification



        //Setting up back floating action button
        findViewById(R.id.backFAB).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(LocationActivity.this, TableOfContents.class)); //Can also use getApplicationContext() instead of TableOfContents.this
            }
        });



        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    } //End onCreate



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_climbing:
                    Toast.makeText(getApplicationContext(), "Climbing clinics", Toast.LENGTH_SHORT).show();
                    loadFragment(new Bot1Frag());
                    return true;
                case R.id.navigation_yoga:
                    Toast.makeText(getApplicationContext(), "Yoga sessions", Toast.LENGTH_SHORT).show();
                    loadFragment(new Bot2Frag());
                    return true;
                case R.id.navigation_event:
                    Toast.makeText(getApplicationContext(), "Special events", Toast.LENGTH_SHORT).show();
                    loadFragment(new Bot3Frag());
                    return true;
                case R.id.navigation_mountain:
                    Toast.makeText(getApplicationContext(), "Outdoor climbing", Toast.LENGTH_SHORT).show();
                    loadFragment(new Bot4Frag());
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //ViewPager should not be used when using bottom navigation
        transaction.replace(R.id.topConstraint, fragment); //TOP CONSTRAINT IS THE LAYOUT THAT IS CHANGED BETWEEN BOTTOM TABS
        transaction.addToBackStack(null);
        transaction.commit();
    }



} //End LocationActivity
