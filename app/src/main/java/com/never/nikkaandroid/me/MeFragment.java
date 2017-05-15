package com.never.nikkaandroid.me;


import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.never.nikkaandroid.R;
import com.never.nikkaandroid.adpter.MeListAdapter;
import com.never.nikkaandroid.base.BaseFragment;
import com.never.nikkaandroid.databinding.FragmentMeBinding;
import com.never.nikkaandroid.venv.AppManager;
import com.never.nikkaandroid.venv.CommonUtils;
import com.never.nikkaandroid.views.CanvasView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment<FragmentMeBinding> implements AdapterView.OnItemClickListener {

    // 模拟数据
    private List<String> dataList = null;
    MeListAdapter adapter;
    private CanvasView headerView;


    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected void viewWillappear() {
        super.viewWillappear();

        headerView.refreshPoint(AppManager.getInstance().getPoints());
        adapter.setDataList(null);
        this.adapter.notifyDataSetChanged();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        Log.e("lazyLoad","lazyLoad");
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
        int height = (int) (0.4* CommonUtils.getWindowHeight((Activity) getContext()));

        //ViewGroup$LayoutParams 6.0         4.4崩溃
        //AbsListView$LayoutParams 4.4
        ViewGroup.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        headerView  = new CanvasView(getContext(),height,20.0f,"","subTitle", R.drawable.user_image);
        headerView.setBackgroundColor(getResources().getColor(R.color.white));
        headerView.setLayoutParams(params);
//        headerView.setuserImageId(R.drawable.home_preference);

        ListView listview = dataBind.meListView;
        this.adapter = new MeListAdapter(getContext(),null);
        listview.setAdapter(this.adapter);
        listview.addHeaderView(headerView);
//      绑定item点击事件
        listview.setOnItemClickListener(this);

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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("me","item:"+position);

        switch (position){
            case 0:{
            //header
                break;
            }
            case 1:{
            //礼物赠送
                Intent intent = new Intent(getActivity(),GiftActivity.class);
                getActivity().startActivity(intent);
                break;
            }
            case 2:{
            //生活助手
                break;
            }
            case 3:{
            //意见反馈
                Intent intent = new Intent(getActivity(),FeedbackActivity.class);
                getActivity().startActivity(intent);
                break;
            }
            case 4:{
            //呜谢组织
                Intent intent = new Intent(getActivity(),ThankActivity.class);
                getActivity().startActivity(intent);
                break;
            }
            case 5:{
            //关于我们
                new AlertDialog.Builder(this.getActivity())
                        .setMessage("退出当前账号不会删除任何历史数据，下次登录依然可以使用本账号")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AppManager.getInstance().setUser_id(null);
                                AppManager.getInstance().setUserName(null);
                                AppManager.getInstance().setUser_token(null);
                                adapter.setDataList(null);
                                adapter.notifyDataSetChanged();
                                headerView.refreshPoint(-1);
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e("AlertDialog","AlertDialog");
                    }
                }).show();
                break;
            }
            case 6:{

                new AlertDialog.Builder(this.getActivity())
                        .setMessage("退出当前账号不会删除任何历史数据，下次登录依然可以使用本账号")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppManager.getInstance().setUser_id(null);
                        AppManager.getInstance().setUserName(null);
                        AppManager.getInstance().setUser_token(null);
                        adapter.setDataList(null);
                        adapter.notifyDataSetChanged();
                        headerView.refreshPoint(-1);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e("AlertDialog","AlertDialog");
                    }
                }).show();
                break;
            }
            default:
                break;
        }
    }
}
