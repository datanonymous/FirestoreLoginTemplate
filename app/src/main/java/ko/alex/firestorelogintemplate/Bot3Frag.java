package ko.alex.firestorelogintemplate;

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
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class Bot3Frag extends Fragment {

    private static final String TAG = "AlexKo";
    private RecyclerView bot3recyclerview;
    private FirebaseFirestore firebaseFirestore;
    private List<Events> eventsList;
    private EventsListAdapter eventsListAdapter;

    public Bot3Frag() {
        //REQUIRED EMPTY PUBLIC CONSTRUCTOR
        //Working with RecyclerView
        //https://www.androidhive.info/2016/01/android-working-with-recycler-view/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bot3frag, container, false);

        bot3recyclerview = view.findViewById(R.id.bot3recyclerview);
        bot3recyclerview.setHasFixedSize(true);
        bot3recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        eventsList = new ArrayList<>();
        eventsListAdapter = new EventsListAdapter(eventsList);
        bot3recyclerview.setAdapter(eventsListAdapter);

        firebaseFirestore = FirebaseFirestore.getInstance();
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
//        String myValue = this.getArguments().getString("fromLocationActivity");
//        Toast.makeText(getContext(), "Location selected: " + myValue, Toast.LENGTH_SHORT).show();
        LocationActivity locationActivity = (LocationActivity) getActivity();
        String locationSelected = locationActivity.getLocationSelected();
        Toast.makeText(getActivity(), "Location selected is: " + locationSelected, Toast.LENGTH_SHORT).show();

//        String locationSelected = this.getArguments().getString("fromLocationActivity"); //TODO: Returns java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.String android.os.Bundle.getString(java.lang.String)' on a null object reference
//        Toast.makeText(getContext(), "Location selected: " + locationSelected, Toast.LENGTH_SHORT).show();

        firebaseFirestore.collection("GymLocations").document(locationSelected).collection("SpecialEvents").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error: " + e.getMessage());
                }
//                USES TOO MUCH DATA
//                for(DocumentSnapshot doc: queryDocumentSnapshots){
//                    String userName = doc.getString("name");
//                    Log.d(TAG, "Name: " + userName);
//                }
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {
//                        String userName = doc.getDocument().getString("name");
//                        Log.d(TAG, "Name: " + userName);
                        Events events = doc.getDocument().toObject(Events.class);
                        eventsList.add(events);
                        eventsListAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "SHITFUCK bot3frag: "+doc.getDocument().getString("Name"), Toast.LENGTH_LONG).show();
                    }
                }
            } //END ONEVENT
        }); //END FIREBASE FIRESTORE COLLECTION



        return view;
    } //END ONCREATEVIEW



} //END BOT3FRAG
