package com.never.nikkaandroid.grid;


import android.app.Fragment;

import com.never.nikkaandroid.base.BaseFragment;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.databinding.FragmentGridBinding;
import com.never.nikkaandroid.venv.AppManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class GridFragment extends BaseFragment<FragmentGridBinding> {


    public GridFragment() {
        // Required empty public constructor
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_grid;
    }

    @Override
    public void init() {
        AppManager.getInstance().setPoints(1000);
    }
}
