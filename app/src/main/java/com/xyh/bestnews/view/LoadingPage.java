package com.xyh.bestnews.view;

/**
 * Created by Administrator on 2016/9/5.
 */
public abstract class LoadingPage  {

//    public static final int STAT_LOADING = 0;//正在获取数据时的页面标记
//    public static final int STAT_ERROR = 1;//数据获取失败错误时页面标记
//    public static final int STAT_EMPTY = 2;//数据为空，没有数据时的页面标记
//    public static final int STAT_SUCESS = 3;//数据获取成功时显示标记
//    private int currentState = 0;//当前状态
//
//    private View loadingView;//正在获取数据时的页面
//    private View errorView;//数据获取失败错误时页面
//    private View emptyView;//数据为空，没有数据时的页面
//    private View sucessView;//数据获取成功时显示
//
//    public LoadingPage(Context context) {
//        super(context);
//        initViews();
//    }
//
//    private void initViews() {
//
//        if (loadingView == null) {
//            loadingView = View.inflate(getContext(), R.layout.layout_loading, null);
//            addView(loadingView);
//        }
//        if (errorView == null) {
//            errorView = View.inflate(getContext(), R.layout.layout_error, null);
//            addView(errorView);
//        }
//        if (emptyView == null) {
//            emptyView = View.inflate(getContext(), R.layout.layout_empty, null);
//            addView(emptyView);
//        }
//
//    }
//
//
//    public void startNetWork() {
//        String url = getUrl();
//        if (url == null) {
//            currentState = STAT_SUCESS;
//            showPage();
//        } else {
//            x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
//                @Override
//                public void onSuccess(String result) {
//                    if (TextUtils.isEmpty(result)) {
//                        currentState = STAT_EMPTY;
//                        Toast.makeText(getContext(), "空", Toast.LENGTH_SHORT).show();
//                    } else {
//                        currentState = STAT_SUCESS;
//                        paresData(result);
//                    }
//                    showPage();
//                }
//
//                @Override
//                public void onError(Throwable ex, boolean isOnCallback) {
//                    currentState = STAT_ERROR;
//                    showPage();
//                }
//
//                @Override
//                public void onCancelled(CancelledException cex) {
//
//                }
//
//                @Override
//                public void onFinished() {
//
//                }
//            });
//        }
//
//    }
//
//    protected abstract void paresData(String result);
//
//    protected abstract String getUrl();
//
//    protected abstract void bindView(View sucessView);
//
//
//    protected abstract int getSucessLayout();
//
//    private void showPage() {
//        MyApp.handler.post(new Runnable() {
//            @Override
//            public void run() {
//                loadingView.setVisibility(currentState == STAT_LOADING ? View.VISIBLE : View.INVISIBLE);
//                errorView.setVisibility(currentState == STAT_ERROR ? View.VISIBLE : View.INVISIBLE);
//                emptyView.setVisibility(currentState == STAT_EMPTY ? View.VISIBLE : View.INVISIBLE);
//                if (sucessView == null) {
//                    sucessView = View.inflate(getContext(), getSucessLayout(), null);
//                    addView(sucessView);
//                    //在碎片中绑定id
//                    bindView(sucessView);
//                }
//                sucessView.setVisibility(currentState == STAT_SUCESS ? View.VISIBLE : View.INVISIBLE);
//            }
//        });
//    }


}
