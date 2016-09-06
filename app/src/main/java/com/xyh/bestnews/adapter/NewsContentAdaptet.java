package com.xyh.bestnews.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xyh.bestnews.R;
import com.xyh.bestnews.entity.NewsEase;
import com.xyh.bestnews.util.XImageUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/2.
 */
public class NewsContentAdaptet extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public void setList(List<NewsEase> list) {
        this.list = list;
    }

    public void addData(List<NewsEase> l) {
        if (l == null) return;
        if (list == null) {
            setList(l);
            return;
        }
        list.addAll(l);
    }

    public List<NewsEase> getList() {
        return list;
    }

    private List<NewsEase> list;
    private Handler handler;

    public NewsContentAdaptet(List<NewsEase> list, Context context) {
        this.list = list;
        this.context = context;

    }

    public NewsContentAdaptet(Context context) {
        this.context = context;
    }

    private Context context;
    public static final int VIEW_TYPE_VP = 0;
    public static final int VIEW_TYPE_ONE_HEAD = 1;
    public static final int VIEW_TYPE_ONE_BIG = 2;
    public static final int VIEW_TYPE_THREE_SMALL = 3;
    public static final int VIEW_TYPE_FOOTER = 4;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_ONE_BIG:
                View v = View.inflate(context, R.layout.layout_item_one_img, null);
                return new OneImageHolder(v);
            case VIEW_TYPE_VP:
                v = View.inflate(context, R.layout.layout_item_vp, null);

                return new ViewPagerHolder(v);
            case VIEW_TYPE_THREE_SMALL:
                v = View.inflate(context, R.layout.layout_item_three_img, null);
                return new ThreeImageHolder(v);
            case VIEW_TYPE_ONE_HEAD:
                v = View.inflate(context, R.layout.layout_item_one_head, null);
                return new HeadPicHolder(v);
            case VIEW_TYPE_FOOTER:
                v = View.inflate(context, R.layout.footer, null);
                return new FootHolder(v);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return list.get(position).ads == null ? VIEW_TYPE_ONE_HEAD : VIEW_TYPE_VP;
        } else if (position > 0 && position < getItemCount() - 1) {
            return list.get(position).imgextra == null ? VIEW_TYPE_ONE_BIG : VIEW_TYPE_THREE_SMALL;
        } else return VIEW_TYPE_FOOTER;

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof OneImageHolder) {

            OneImageHolder h = (OneImageHolder) holder;
            XImageUtil.display(h.imgLeft, list.get(position).imgsrc);
            h.tvTitle.setText(list.get(position).title);
            h.tvFollow.setText(list.get(position).replyCount + "跟帖");

        } else if (holder instanceof HeadPicHolder) {
            HeadPicHolder h = (HeadPicHolder) holder;
            XImageUtil.display(h.imgHead, list.get(position).imgsrc);

            h.tvTitle.setText(list.get(position).title);


        } else if (holder instanceof ThreeImageHolder) {
            ThreeImageHolder h = (ThreeImageHolder) holder;
            XImageUtil.display(h.img1, list.get(position).imgsrc);
            XImageUtil.display(h.img2, list.get(position).imgextra.get(0).imgsrc);
            XImageUtil.display(h.img3, list.get(position).imgextra.get(1).imgsrc);
            h.tvTitle.setText(list.get(position).title);
            h.tvFollow.setText(list.get(position).replyCount + "跟帖");

        } else if (holder instanceof ViewPagerHolder)
            initViewPagerHolder((ViewPagerHolder) holder, position);
        else {
            //footer


        }
    }

    //初始化ViewPager布局：
    private void initViewPagerHolder(ViewPagerHolder holder, final int position) {
        final ViewPagerHolder h = holder;
        final List<NewsEase.AD> ads = list.get(position).ads;
        AdAdapter adapter = new AdAdapter(ads);
        //初始化点：
        //根据ads的长度来添加view，
        for (int i = 0; i < ads.size(); i++) {
            //
            ImageView img = new ImageView(context);
            img.setImageResource(R.drawable.bullet_white);
            h.ll_layout.addView(img);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) img.getLayoutParams();
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;


        }

        //颜色默认第一个是选中的：
        final ImageView childAt = (ImageView) h.ll_layout.getChildAt(0);
        childAt.setImageResource(R.drawable.bullet_red);
        h.vpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int p, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int p) {
                int pos = p % ads.size();//当前的页面pos
                for (int i = 0; i < ads.size(); i++) {
                    ImageView childAt1 = (ImageView) h.ll_layout.getChildAt(i);
                    childAt1.setImageResource(R.drawable.bullet_white);
                }
                ((ImageView) h.ll_layout.getChildAt(pos)).setImageResource(R.drawable.bullet_red);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        h.vpager.setAdapter(adapter);
        h.vpager.setCurrentItem(Integer.MAX_VALUE / 2 - ((Integer.MAX_VALUE / 2) % list.get(position).ads.size()));
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int position = h.vpager.getCurrentItem();
                            h.vpager.setCurrentItem(position + 1);
                            handler.removeMessages(1);
                            handler.sendEmptyMessage(1);
                        }
                    }, 2000);
                }
            }
        };
        handler.sendEmptyMessage(1);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size() + 1;
    }

    //一张图，右边是文字的布局
    public static class OneImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_left)
        ImageView imgLeft;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_follow)
        TextView tvFollow;

        public OneImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //三张图并排，上边是标题下边时间的布局
    public static class ThreeImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.img1)
        ImageView img1;
        @BindView(R.id.img2)
        ImageView img2;
        @BindView(R.id.img3)
        ImageView img3;
        @BindView(R.id.tv_follow)
        TextView tvFollow;

        public ThreeImageHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    //第一行viewpager的布局
    public static class ViewPagerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vpager)
        ViewPager vpager;
        @BindView(R.id.ll_layout)
        LinearLayout ll_layout;

        public ViewPagerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //第一行有一张大图的布局
    public static class HeadPicHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_head)
        ImageView imgHead;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public HeadPicHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    private class FootHolder extends RecyclerView.ViewHolder {
        public FootHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
