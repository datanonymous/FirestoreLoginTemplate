package ko.alex.firestorelogintemplate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LostPasswordActivity extends AppCompatActivity {

    EditText forgotPWemail;
    FloatingActionButton forgotPWfab, backPWfab;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_password);

        forgotPWemail = findViewById(R.id.forgotPWemail);
        forgotPWfab = findViewById(R.id.forgotPWfab);
        backPWfab = findViewById(R.id.backPWfab);
        firebaseAuth = FirebaseAuth.getInstance();

        //FORGOT PW, SEND EMAIL TO RESET PASSWORD
        //https://www.youtube.com/watch?v=t8vUdt1eEzE
        forgotPWfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String forgottenEmail = forgotPWemail.getText().toString().trim();
                firebaseAuth.sendPasswordResetEmail(forgottenEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Password sent to your email", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        backPWfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Returning back to login screen...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LostPasswordActivity.this, MainActivity.class));
            }
        });



    } //END ONCREATE



} //END LOSTPASSWORDACTIVITY
