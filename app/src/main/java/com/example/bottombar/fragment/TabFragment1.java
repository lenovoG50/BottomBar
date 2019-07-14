package com.example.bottombar.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.example.bottombar.adapter.MyAdapter;
import com.example.bottombar.bean.HomeBean;
import com.example.bottombar.nohttp.CallServer;
import com.example.bottombar.nohttp.Interface.HttpListener;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

/**
 * Time:13:45
 * Author:lenovo
 * Description:新生成的类
 */
public class TabFragment1 extends Fragment implements HttpListener<String> {
    private RecyclerView rcv;

   private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
            rcv.setAdapter(new MyAdapter(resultList, getActivity()));
        }
    };
    private List<HomeBean.ResultBean.DataBean> resultList;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab1, container, false);
        rcv = view.findViewById(R.id.rcv);
        initData();
        return view;
    }

    private void initData() {
        Request<String> request = NoHttp.createStringRequest("http://v.juhe.cn/toutiao/index?key=b0c1a57febbe49da8940dc820c2d8e43&type=top", RequestMethod.GET);
        CallServer callServer = CallServer.getRequestInstance();
        callServer.add(getActivity(), 0, request, this, true, true);
    }

    @Override
    public void onSuccesed(int what, Response response) {
            Gson gson = new Gson();
            String data = response.get().toString();
            resultList = gson.fromJson(data, HomeBean.class).getResult().getData();
            handler.sendEmptyMessage(0);
    }

    @Override
    public void onFailed(int what, Response response) {
        Snackbar.make(view,"请求失败",Snackbar.LENGTH_LONG).show();
    }
}
