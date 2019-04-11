package ko.alex.firestorelogintemplate;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Bot1Frag extends Fragment {



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

        return view;
    } //END ONCREATEVIEW



} //END BOT1FRAG
