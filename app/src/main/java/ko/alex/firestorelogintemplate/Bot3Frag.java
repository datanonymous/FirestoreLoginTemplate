package ko.alex.firestorelogintemplate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
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

public class Bot3Frag extends Fragment {

    private RecyclerView bot3recyclerview;
    private FirebaseFirestore firebaseFirestore;
    private static final String TAG = "AlexKo";
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
//        bot3recyclerview.setHasFixedSize(true); //https://stackoverflow.com/questions/50161553/firestore-not-retrieving-certain-fields-from-document
        bot3recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        //adding lines between items
        //https://www.androidhive.info/2016/01/android-working-with-recycler-view/
        bot3recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        eventsList = new ArrayList<>();

        eventsListAdapter = new EventsListAdapter(eventsList);
        bot3recyclerview.setAdapter(eventsListAdapter);

        /*
        FIRESTORE IS SETUP LIKE THIS:
        GymLocations -> Durham, Morrisville, Raleigh
        Durham -> ClimbingClinics, YogaSessions, SpecialEvents
        ClimbingClinics -> documents
         */
        //Trying to pass data from activity (LocationActivity) to fragment, "which gym location did the user select?"
        //https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android
        //https://www.youtube.com/watch?v=vdCejJobMp4
        //https://stackoverflow.com/questions/12739909/send-data-from-activity-to-fragment-in-android
//        String myValue = this.getArguments().getString("fromLocationActivity");
//        Toast.makeText(getContext(), "Location selected: " + myValue, Toast.LENGTH_SHORT).show();
        LocationActivity locationActivity = (LocationActivity) getActivity();
        String locationSelected = locationActivity.getLocationSelected();
        Toast.makeText(getActivity(), "Location selected is: " + locationSelected, Toast.LENGTH_SHORT).show();

//        String locationSelected = this.getArguments().getString("fromLocationActivity"); //Returns java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.String android.os.Bundle.getString(java.lang.String)' on a null object reference
//        Toast.makeText(getContext(), "Location selected: " + locationSelected, Toast.LENGTH_SHORT).show();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("GymLocations").document(locationSelected).collection("SpecialEvents").addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {
//                        String userName = doc.getDocument().getString("name");
//                        Log.d(TAG, "Name: " + userName);
                        Events events = doc.getDocument().toObject(Events.class);
                        eventsList.add(events);
//                        eventsListAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "DocumentChange bot3frag: "+doc.getDocument().getString("Name"), Toast.LENGTH_LONG).show();
                    }
                }
                //SEE IF MOVING NOTIFYDATASETCHANGED() OUTSIDE THE FOR LOOP HELPS
                //https://stackoverflow.com/questions/50161553/firestore-not-retrieving-certain-fields-from-document
                eventsListAdapter.notifyDataSetChanged();
            } //END ONEVENT
        }); //END FIREBASE FIRESTORE COLLECTION



        return view;
    } //END ONCREATEVIEW



} //END BOT3FRAG
