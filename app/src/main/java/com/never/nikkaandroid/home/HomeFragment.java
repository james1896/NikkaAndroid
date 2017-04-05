package com.never.nikkaandroid.home;


import android.app.Fragment;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    public HomeFragment() {
        // Required empty public constructor
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);
//    }


    @Override
    public int getContentView() {
        return R.layout.fragment_home;
    }
}
