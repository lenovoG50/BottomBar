package com.example.bottombar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bottombar.R;
import com.example.bottombar.bean.HomeBean;

import java.util.List;

/**
 * Time:08:16
 * Author:lenovo
 * Description:新生成的类
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<HomeBean.ResultBean.DataBean> resultList;
    private Context context;

    public MyAdapter(List<HomeBean.ResultBean.DataBean> resultList, Context context) {
        this.resultList = resultList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.adapter_tab1, null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, int i) {
        Glide.with(context).load(resultList.get(i).getThumbnail_pic_s()).error(R.drawable.my1).into(viewHolder.img);
        viewHolder.tv.setText(resultList.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tv = itemView.findViewById(R.id.title);
        }
    }
}
