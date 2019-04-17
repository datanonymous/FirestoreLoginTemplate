package ko.alex.firestorelogintemplate;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class Bot2Frag extends Fragment {

    private static final String TAG = "AlexKo";
    private RecyclerView bot2recyclerview;
    private FirebaseFirestore firebaseFirestore;
    private List<Yoga> yogaList;
    private YogaListAdapter yogaListAdapter;

    public Bot2Frag() {
        //REQUIRED EMPTY PUBLIC CONSTRUCTOR
        //Working with RecyclerView
        //https://www.androidhive.info/2016/01/android-working-with-recycler-view/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bot2frag, container, false);

        bot2recyclerview = view.findViewById(R.id.bot2recyclerview);
        bot2recyclerview.setHasFixedSize(true);
        bot2recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        yogaList = new ArrayList<>();

        yogaListAdapter = new YogaListAdapter(yogaList);
        bot2recyclerview.setAdapter(yogaListAdapter);

        /*
        FIRESTORE IS SETUP LIKE THIS:
        GymLocations -> Durham, Morrisville, Raleigh
        Durham -> ClimbingClinics, YogaSessions, SpecialEvents
        ClimbingClinics -> documents
         */
        //TODO: Trying to pass data from activity (LocationActivity) to fragment, "which gym location did the user select?"
        //TODO: https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android
        //TODO: https://www.youtube.com/watch?v=vdCejJobMp4
        //TODO: https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android
//        Bundle bundle = this.getArguments();
//        String myValue = bundle.getString("fromLocationActivity");
//        Toast.makeText(getContext(), "Location selected: " + myValue, Toast.LENGTH_SHORT).show();


        //CTRL+ALT+L as a shortcut to auto format code (such as indents)
//        String locationSelected = this.getArguments().getString("fromLocationActivity"); //TODO: Returns java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.String android.os.Bundle.getString(java.lang.String)' on a null object reference
//        Toast.makeText(getContext(), "Location selected: " + locationSelected, Toast.LENGTH_SHORT).show();
        LocationActivity locationActivity = (LocationActivity) getActivity();
        String locationSelected = locationActivity.getLocationSelected();
        Toast.makeText(getActivity(), "Location selected is: " + locationSelected, Toast.LENGTH_SHORT).show();

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("GymLocations").document(locationSelected).collection("YogaSessions").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error: " + e.getMessage());
                }
////                USES TOO MUCH DATA
//                for(DocumentSnapshot doc: queryDocumentSnapshots){
//                    String asdfName = doc.getString("Name");
//                    String asdfDescription = doc.getString("Description");
//                    String asdfInstructor = doc.getString("Instructor");
//                    String asdfDate = doc.getString("Date");
//                    Log.d(TAG, "Name: " + asdfName);
//                    Log.d(TAG, "Description: " + asdfDescription);
//                    Log.d(TAG, "Instructor: " + asdfInstructor);
//                    Log.d(TAG, "Date: " + asdfDate);
//                    //https://github.com/firebase/FirebaseUI-Android/tree/master/firestore
////                List<Events> eventsList = queryDocumentSnapshots.toObjects(Events.class);
//                }
                for(DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()){
                    if(doc.getType() == DocumentChange.Type.ADDED){
//                        String userName = doc.getDocument().getString("name");
//                        Log.d(TAG, "Name: " + userName);
                        Yoga yoga = doc.getDocument().toObject(Yoga.class);
                        yogaList.add(yoga);
                        yogaListAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "DocumentChange bot2frag: "+doc.getDocument().getString("Name"), Toast.LENGTH_LONG).show();
                    }
                }
            } //END ONEVENT
        }); //END FIREBASE FIRESTORE COLLECTION



        return view;
    } //END ONCREATEVIEW



} //END BOT2FRAG
