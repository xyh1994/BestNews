package com.xyh.bestnews.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xyh.bestnews.entity.NetEaseType;
import com.xyh.bestnews.fragments.NewsListFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
public class NewsTaypeAdapter extends FragmentPagerAdapter {

    private List<NetEaseType.TList> titleList;

    public NewsTaypeAdapter(FragmentManager fm, List<NetEaseType.TList> titleList) {
        super(fm);
        this.titleList = titleList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList == null ? "未命名" : titleList.get(position).getTname();
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("tid", titleList.get(position).getTid());
        bundle.putString("tname", titleList.get(position).getTname());
        return NewsListFragment.getInstance(bundle);
    }

    @Override
    public int getCount() {
        return titleList.size();
    }
}
