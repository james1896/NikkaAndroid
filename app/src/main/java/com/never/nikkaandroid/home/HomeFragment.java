package com.never.nikkaandroid.home;


import android.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements OnClickListener{


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
//        Intent intent = new Intent(getActivity(),BalanceActivity.class);
//        startActivity(intent);

        View v = contentView.findViewById(R.id.payLayout);
        v.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
//        Intent intent = new Intent(getActivity(),BalanceActivity.class);
//        startActivity(intent);
        Log.e("fdsa","fsa");
    }
}
