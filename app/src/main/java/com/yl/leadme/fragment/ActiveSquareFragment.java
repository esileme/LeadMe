package com.yl.leadme.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.yl.leadme.R;
import com.yl.leadme.activity.PublishActivity;
import com.yl.leadme.adapter.MainRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActiveSquareFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<AVObject> mList = new ArrayList<>();
    private MainRecyclerAdapter mRecyclerAdapter;


    public ActiveSquareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_active_square, container, false);



        //退出不写在这里
        //final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        //view.setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PublishActivity.class));//////
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_main);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerAdapter = new MainRecyclerAdapter(mList,getContext());
        mRecyclerView.setAdapter(mRecyclerAdapter);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        AVAnalytics.onResume(getContext());
        initData();
    }

    @Override
    public void onPause() {
        super.onPause();
        AVAnalytics.onPause(getContext());
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mList.clear();
        AVQuery<AVObject> avQuery = new AVQuery<>("Product");
        avQuery.orderByDescending("createdAt");
        avQuery.include("owner");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    mList.addAll(list);
                    mRecyclerAdapter.notifyDataSetChanged();
                } else {
                    e.printStackTrace();
                }
            }
        });
    }


}
