package ko.alex.firestorelogintemplate;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    TextView textViewVerification, gymChoice;
    Button button;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mAuth = FirebaseAuth.getInstance();

        //Trying to get what the user picked before
        gymChoice = findViewById(R.id.gymChoice);
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        gymChoice.setText(message);

        loadUserInformation();

        //I like this way of setting an on click listener
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //saveUserInformation();

                //https://firebase.google.com/docs/auth/android/manage-users
                //TODO: Working on user profiles; can i get a toast to show a user's data?
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final String name = user.getDisplayName();
                final String email = user.getEmail();
                final String uid = user.getUid();

                TextView textViewA = findViewById(R.id.textViewA);
                TextView textViewB = findViewById(R.id.textViewB);
                TextView textViewC = findViewById(R.id.textViewC);
                textViewA.setText("Name: " + name);
                textViewB.setText("Email: " + email);
                textViewC.setText("UID: " + uid);

                //https://firebase.google.com/docs/auth/android/manage-users
                Toast.makeText(getApplicationContext(), "User info: \n" + "Name: " + name + "\nEmail: " + email + "\nUID: " + uid, Toast.LENGTH_LONG).show();
            }
        });

    } //End onCreate

    private void loadUserInformation() {
        final FirebaseUser user = mAuth.getCurrentUser();
        textViewVerification = findViewById(R.id.textViewVerification);
        if (user != null) {
//            if (user.getPhotoUrl() != null) {
//                //https://bumptech.github.io/glide/
//                Glide.with(this)
//                        .load(user.getPhotoUrl().toString())
//                        .into(imageView);
//            }
//            if (user.getDisplayName() != null) {
//                editText.setText(user.getDisplayName());
//            }
            if (user.isEmailVerified()) {
                textViewVerification.setText("Email Verified");
            } else {
                textViewVerification.setText("Email Not Verified (Click to Verify)");
                textViewVerification.setOnClickListener(new View.OnClickListener() {
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
        }
    }

} //End LocationActivity
