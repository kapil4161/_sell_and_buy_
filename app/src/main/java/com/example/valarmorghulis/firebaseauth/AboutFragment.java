package com.example.valarmorghulis.firebaseauth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

}
/*
Android Fragment is the part of activity, it is also known as sub-activity. 
There can be more than one fragment in an activity. Fragments represent multiple screen inside one activity.
Android fragment lifecycle is affected by activity lifecycle because fragments are included in activity.
*/

//		onCreate(Bundle)---------->	It is used to initialize the fragment.
//		onCreateView(LayoutInflater, ViewGroup, Bundle)--------------->	creates and returns view hierarchy.

// Inflate the layout for this fragment  ---
       // return inflater.inflate(R.layout.fragment_fragment2, container, false);  