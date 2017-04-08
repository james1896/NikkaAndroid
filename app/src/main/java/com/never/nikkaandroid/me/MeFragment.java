package com.never.nikkaandroid.me;


import android.app.Fragment;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.base.BaseFragment;
import com.never.nikkaandroid.base.CanvasView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment {

    // 模拟数据
    private List<String> dataList = null;


    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_me;
    }
    CanvasView headerView;
    @Override
    protected void init() {
//        final CanvasView canvasView = (CanvasView) contentView.findViewById(R.id.canvasview);
        //用代码写布局
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) canvasView.getLayoutParams();
//        params.height = 550;
//        canvasView.setLayoutParams(params);


        //代码初始化view
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 550);
        headerView  = new CanvasView(getContext());
        headerView.setBackgroundColor(getResources().getColor(R.color.white));
        headerView.setLayoutParams(params);


        dataList = new ArrayList<String>();
        // 初始化数据
        for (int i = 0; i < 20; i++) {
            dataList.add("第" + i + "条数据");
        }
        // 设置adapter(所在的activity,使用的显示样式,数据源)
        ListAdapter adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, dataList);

      ListView  listview = (ListView) contentView.findViewById(R.id.meListView);
        listview.setAdapter(adapter);
        listview.addHeaderView(headerView);

        // 绑定item点击事件
//        listview.setOnItemClickListener(this);






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
