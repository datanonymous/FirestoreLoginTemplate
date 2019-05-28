package ko.alex.firestorelogintemplate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Bot4Frag extends Fragment {

    Button pilotMountain, hangingRock, tableMountain;
    private FirebaseFirestore firebaseFirestore;
    String outdoorLocationSelected;

    RadioGroup radioGroup;
    RadioButton radioButton, radioButtonYes, radioButtonNo, radioButtonMaybe;

    public Bot4Frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bot4frag, container, false);

        pilotMountain = view.findViewById(R.id.pilotmountain);
        pilotMountain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "Pilot Mountain button clicked", Toast.LENGTH_SHORT).show();
                outdoorLocationSelected = "PilotMountain";
                startCustomAlertDialog(outdoorLocationSelected);
            }
        });
        hangingRock = view.findViewById(R.id.hangingrock);
        hangingRock.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "Hanging Rock button clicked", Toast.LENGTH_SHORT).show();
                outdoorLocationSelected = "HangingRock";
                startCustomAlertDialog(outdoorLocationSelected);
            }
        });
        tableMountain = view.findViewById(R.id.tablemountain);
        tableMountain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "Table Mountain button clicked", Toast.LENGTH_SHORT).show();
                outdoorLocationSelected = "TableMountain";
                startCustomAlertDialog(outdoorLocationSelected);
            }
        });

        return view;
    } //END ONCREATEVIEW

    public void startCustomAlertDialog(final String outdoorLocationSelected){
        // https://www.youtube.com/watch?v=plnLs6aST1M
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); // android.R.style.Theme_Black_NoTitleBar_Fullscreen or android.R.style.Theme_Light
        builder.setTitle("Attendance status:");
        // Need subView because you are referencing the view in custom_alert_dialog.xml, not the main view
        final View subView = getLayoutInflater().inflate(R.layout.custom_alert_dialog,null);
        //Youtube; Coding In Flow; https://www.youtube.com/watch?v=fwSJ1OkK304

        radioButtonYes = subView.findViewById(R.id.radioButtonYes);
        radioButtonNo = subView.findViewById(R.id.radioButtonNo);
        radioButtonMaybe = subView.findViewById(R.id.radioButtonMaybe);

        radioGroup = subView.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonYes:
                        Toast.makeText(getActivity(), "Selected: " + radioButtonYes.getText().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButtonNo:
                        Toast.makeText(getActivity(), "Selected: " + radioButtonNo.getText().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButtonMaybe:
                        Toast.makeText(getActivity(), "Selected: " + radioButtonMaybe.getText().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });



        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                int radioButtonSelected = radioGroup.getCheckedRadioButtonId();
                RadioButton singleButton = subView.findViewById(radioButtonSelected);

                firebaseFirestore = FirebaseFirestore.getInstance();
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final String email = user.getEmail();
                final String uid = user.getUid();

                if(singleButton.getText().toString().equals("Yes")){
                    Map<String, String> userMap = new HashMap<>();
                    userMap.put("Email", email);
                    firebaseFirestore.collection(outdoorLocationSelected).document(uid).set(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getActivity(), "Email added to: " + outdoorLocationSelected + " attendee list!", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String error = e.getMessage();
                            Toast.makeText(getActivity(), "Error: " + error, Toast.LENGTH_SHORT).show();
                        }
                    }); //USING SET BECAUSE DOCUMENT ID IS KNOWN.  USE ADD TO GENERATE RANDOM DOCUMENT ID
                } else if(singleButton.getText().toString().equals("No")){
                    firebaseFirestore.collection(outdoorLocationSelected).document(uid).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getActivity(), "User removed from " + outdoorLocationSelected + " list", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String error = e.getMessage();
                            Toast.makeText(getActivity(), "Error: " + error, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if(singleButton.getText().toString().equals("Maybe")){
                    Toast.makeText(getActivity(), "Why not?!?!?", Toast.LENGTH_SHORT).show();
                }

            } //END ONCLICK

        }); //END BUILDER.SETPOSITIVEBUTTON
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss(); // dialog.cancel() can also work
            }
        });
        // For custom alert dialog
        builder.setView(subView);
        AlertDialog dialog = builder.create();
        dialog.show();
    } //END STARTCUSTOMALERTDIALOG



} //END BOT4FRAG
