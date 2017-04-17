package com.never.nikkaandroid.me;


import android.app.Fragment;
import android.view.ViewGroup;
import android.widget.ListView;

import com.never.nikkaandroid.AppDataManager;
import com.never.nikkaandroid.R;
import com.never.nikkaandroid.adpter.BaseListAdapter;
import com.never.nikkaandroid.base.BaseFragment;
import com.never.nikkaandroid.views.CanvasView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public void init() {
        //        final CanvasView canvasView = (CanvasView) contentView.findViewById(R.id.canvasview);
        //用代码写布局
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) canvasView.getLayoutParams();
//        params.height = 550;
//        canvasView.setLayoutParams(params);


        //代码初始化view
        int height = (int) (0.4* AppDataManager.getScreenHeight(getContext()));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        headerView  = new CanvasView(getContext(),height,20.0f,"name","subTitle");
        headerView.setBackgroundColor(getResources().getColor(R.color.white));
        headerView.setLayoutParams(params);
        headerView.refresh("25.0");


//        dataList = new ArrayList<String>();
//        // 初始化数据
//        for (int i = 0; i < 20; i++) {
//            dataList.add("第" + i + "条数据");
//        }
//        // 设置adapter(所在的activity,使用的显示样式,数据源)
//        ListAdapter adapter = new ArrayAdapter<String>(getContext(),
//                android.R.layout.simple_list_item_1, dataList);
//
        ListView listview = (ListView) contentView.findViewById(R.id.meListView);
        listview.setAdapter(new BaseListAdapter(getContext(),getDataList()));
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

    private ArrayList<Map> getDataList(){
        ArrayList<Map> list = new ArrayList<>();

        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("title","礼物赠送");
        map1.put("resId",R.drawable.me_list_zengsong);
        list.add(map1);

        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("title","生活助手");
        map2.put("resId",R.drawable.me_list_key);
        list.add(map2);

        Map<String,Object> map3 = new HashMap<String,Object>();
        map3.put("title","意见反馈");
        map3.put("resId",R.drawable.me_list_yijian);
        list.add(map3);

        Map<String,Object> map4 = new HashMap<String,Object>();
        map4.put("title","呜谢组织");
        map4.put("resId",R.drawable.me_list_thank);
        list.add(map4);

        Map<String,Object> map5 = new HashMap<String,Object>();
        map5.put("title","关于我们");
        map5.put("resId",R.drawable.me_list_about);
        list.add(map5);
        return list;

    }

    CanvasView headerView;


}
