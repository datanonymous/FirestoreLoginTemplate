package ko.alex.firestorelogintemplate;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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

public class Bot1Frag extends Fragment {

    private RecyclerView bot1recyclerview;
    private FirebaseFirestore firebaseFirestore;
    private static final String TAG = "AlexKo";
    private List<Climbing> climbingList;
    private ClimbingListAdapter climbingListAdapter;

    public Bot1Frag() {
        // Required empty public constructor
        //Working with RecyclerView
        //https://www.androidhive.info/2016/01/android-working-with-recycler-view/

        //Custom alert dialog
        //https://www.youtube.com/watch?v=plnLs6aST1M

        //Working with Firebase and Recyclerview
        //https://www.youtube.com/watch?v=kyGVgrLG3KU&list=LLj1xIyoM3IcZs9XdwFDaJWA&index=3&t=0s
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bot1frag, container, false);

        bot1recyclerview = view.findViewById(R.id.bot1recyclerview);
//        bot1recyclerview.setHasFixedSize(true); //https://stackoverflow.com/questions/50161553/firestore-not-retrieving-certain-fields-from-document
        bot1recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        climbingList = new ArrayList<>();

        climbingListAdapter = new ClimbingListAdapter(climbingList);
        bot1recyclerview.setAdapter(climbingListAdapter);

        /*
        FIRESTORE IS SETUP LIKE THIS:
        GymLocations -> Durham, Morrisville, Raleigh
        Durham -> ClimbingClinics, YogaSessions, SpecialEvents
        ClimbingClinics -> documents
         */
        //CTRL+ALT+L as a shortcut to auto format code (such as indents)
        LocationActivity locationActivity = (LocationActivity) getActivity(); //Starting LocationActivity class
        String locationSelected = locationActivity.getLocationSelected(); //Accessing the getLocationSelected custom method to retrieve message
        Toast.makeText(getActivity(), "Location selected is: " + locationSelected, Toast.LENGTH_SHORT).show();

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("GymLocations").document(locationSelected).collection("ClimbingClinics").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    Log.d(TAG, "Error: " + e.getMessage());
                }
//                USES TOO MUCH DATA
//                for(DocumentSnapshot doc1: queryDocumentSnapshots){
////                    String className = doc.getString("Name");
////                    Log.d(TAG, "Name: " + className);
//                    Climbing climbing = doc1.toObject(Climbing.class);
//                    climbingList.add(climbing);
//                }
                for(DocumentChange doc: queryDocumentSnapshots.getDocumentChanges()){
                    if(doc.getType() == DocumentChange.Type.ADDED){
                        Climbing climbing = doc.getDocument().toObject(Climbing.class);
                        climbingList.add(climbing);
                        Toast.makeText(getActivity(), "DocumentChange bot1frag: "+doc.getDocument().getString("Name"), Toast.LENGTH_LONG).show();
                    }
                }
                //SEE IF MOVING NOTIFYDATASETCHANGED() OUTSIDE THE FOR LOOP HELPS
                //https://stackoverflow.com/questions/50161553/firestore-not-retrieving-certain-fields-from-document
                //https://stackoverflow.com/questions/46706433/firebase-firestore-get-data-from-collection
                //https://stackoverflow.com/questions/47973354/get-data-from-firestore-firebase/47974076#47974076
                climbingListAdapter.notifyDataSetChanged();
            } //END ONEVENT
        }); //END FIREBASE FIRESTORE COLLECTION



        return view;
    } //END ONCREATEVIEW



} //END BOT1FRAG
