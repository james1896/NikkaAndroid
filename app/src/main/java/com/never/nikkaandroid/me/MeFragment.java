package com.never.nikkaandroid.me;


import android.app.Fragment;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.base.BaseFragment;
import com.never.nikkaandroid.base.CanvasView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment {


    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_me;
    }

    @Override
    protected void init() {
        final CanvasView canvasView = (CanvasView) contentView.findViewById(R.id.canvasview);
        final TextView textView = (TextView) contentView.findViewById(R.id.tetview);


        //用代码写布局
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) canvasView.getLayoutParams();
        params.height = 550;
        canvasView.setLayoutParams(params);




        //布局初始化完成才可以打印view的宽高度

//        float hhh = getResources().getDimension(R.dimen.hhh);
//        Log.e("00000", "hhh-<<>>>>>"+hhh);
//        canvasView.post(new Runnable() {
//            @Override
//            public void run() {
//                Log.e("--->", "canvasView.getHeight()--->"+canvasView.getHeight());
//            }
//        });
//
//        textView.post(new Runnable() {
//            @Override
//            public void run() {
//                Log.e("--->", "textView.getHeight()--->"+textView.getHeight());
//            }
//        });
    }

}
