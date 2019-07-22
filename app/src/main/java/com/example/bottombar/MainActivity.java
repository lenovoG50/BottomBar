package com.example.bottombar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.bottombar.fragment.TabFragment1;
import com.example.bottombar.fragment.TabFragment2;
import com.example.bottombar.fragment.TabFragment3;

import java.util.ArrayList;

import cn.jzvd.JZVideoPlayer;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        PageNavigationView pgv = findViewById(R.id.pgv);
        NavigationController controller = pgv.material()
                .addItem(R.drawable.home, R.drawable.home1, "首页", getResources().getColor(R.color.bottomBar))
                .addItem(R.drawable.video, R.drawable.video1, "视频", getResources().getColor(R.color.bottomBar))
                .addItem(R.drawable.my, R.drawable.my1, "我的", getResources().getColor(R.color.bottomBar))
                .build();
        ViewPager vp = findViewById(R.id.vp);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        controller.setupWithViewPager(vp);
    }

    private void initFragment() {
        fragmentList.add(new TabFragment1());
        fragmentList.add(new TabFragment2());
        fragmentList.add(new TabFragment3());
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
