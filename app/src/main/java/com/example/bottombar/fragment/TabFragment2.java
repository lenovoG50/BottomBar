package com.example.bottombar.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bottombar.R;
import com.example.bottombar.adapter.VideoAdapter;
import com.example.bottombar.bean.VideoBean;
import com.example.bottombar.nohttp.CallServer;
import com.example.bottombar.nohttp.Interface.HttpListener;
import com.example.bottombar.utils.UrlClass;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Time:13:45
 * Author:lenovo
 * Description:新生成的类
 */
public class TabFragment2 extends Fragment implements HttpListener {

    private RecyclerView rcv;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab2, container, false);
        rcv = view.findViewById(R.id.tab2);
        initData();
        return view;
    }

    private void initData() {
        Request<String> request = NoHttp.createStringRequest(UrlClass.video, RequestMethod.GET);
        CallServer callServer = CallServer.getRequestInstance();
        callServer.add(getActivity(), 0, request, this, true, true);
    }

    @Override
    public void onSuccesed(int what, Response response) {
        Gson gson = new Gson();
        String data = response.get().toString();
        List<VideoBean.V9LG4E6VRBean> vr = gson.fromJson(data, VideoBean.class).getV9LG4E6VR();
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcv.setAdapter(new VideoAdapter(getActivity(), vr));
    }

    @Override
    public void onFailed(int what, Response response) {
        Snackbar.make(view, "请求失败", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayerStandard.releaseAllVideos();
    }
}
