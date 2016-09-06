package com.xyh.bestnews.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyh.bestnews.R;
import com.xyh.bestnews.entity.NewsEase;
import com.xyh.bestnews.util.XImageUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */
public class AdAdapter extends PagerAdapter {//继承viewpager专用的适配器

List<NewsEase.AD> ads;

    public AdAdapter(List<NewsEase.AD> ads) {
        this.ads = ads;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v=View.inflate(container.getContext(), R.layout.layout_item_one_head,null);
        TextView tv_title= (TextView) v.findViewById(R.id.tv_title);
        ImageView img_head= (ImageView) v.findViewById(R.id.img_head);
        tv_title.setText(ads.get(position%ads.size()).title);
        XImageUtil.display(img_head,ads.get(position%ads.size()).imgsrc);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {


        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
