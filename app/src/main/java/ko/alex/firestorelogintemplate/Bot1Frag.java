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

    private RecyclerView mainList;
    private FirebaseFirestore firebaseFirestore;
    private static final String TAG = "AlexKo";
    private List<Users> usersList;
    private UsersListAdapter usersListAdapter;

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

        //mainList refers to the recyclerview
        mainList = view.findViewById(R.id.mainList);
        mainList.setHasFixedSize(true);
        mainList.setLayoutManager(new LinearLayoutManager(getActivity()));

        usersList = new ArrayList<>();

        usersListAdapter = new UsersListAdapter(usersList);
        mainList.setAdapter(usersListAdapter);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    Log.d(TAG, "Error: " + e.getMessage());
                }
//                USES TOO MUCH DATA
//                for(DocumentSnapshot doc: queryDocumentSnapshots){
//                    String userName = doc.getString("name");
//                    Log.d(TAG, "Name: " + userName);
//                }
                for(DocumentChange doc: queryDocumentSnapshots.getDocumentChanges()){
                    if(doc.getType() == DocumentChange.Type.ADDED){
//                        String userName = doc.getDocument().getString("name");
//                        Log.d(TAG, "Name: " + userName);
                        Users users = doc.getDocument().toObject(Users.class);
                        usersList.add(users);

                        usersListAdapter.notifyDataSetChanged();
                    }
                }
            } //END ONEVENT
        }); //END FIREBASE FIRESTORE COLLECTION

        return view;
    } //END ONCREATEVIEW



} //END BOT1FRAG
