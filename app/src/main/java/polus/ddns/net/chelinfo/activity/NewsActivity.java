package polus.ddns.net.chelinfo.activity;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

import polus.ddns.net.chelinfo.R;
import polus.ddns.net.chelinfo.beans.GetBeansFromRest;
import polus.ddns.net.chelinfo.beans.NewsItem;
import polus.ddns.net.chelinfo.beans.NewsListItem;
import polus.ddns.net.chelinfo.utils.ConstantManager;
import polus.ddns.net.chelinfo.utils.NetworkUtils;
import polus.ddns.net.chelinfo.utils.RVAdapter;
import polus.ddns.net.chelinfo.utils.RecyclerItemClickListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsActivity extends FragmentActivity implements ActionBar.TabListener {
    static final String TAG = ConstantManager.TAG_PREFIX + "NewsActivity";
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    static NewsListItem[] newsListItems;
    static ConcurrentHashMap<Integer, NewsItem[]> newsItemsMap = new ConcurrentHashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                ArrayList<NewsListItem> arrayItems = extras.getParcelableArrayList(ConstantManager.NEWS_LINK);
                newsListItems = arrayItems.toArray(new NewsListItem[arrayItems.size()]);
            } else newsListItems = new NewsListItem[0];
        } else {
            newsListItems = (NewsListItem[]) savedInstanceState.getParcelableArray(ConstantManager.NEWS_LINK);
            for (int i = 1; i < newsListItems.length + 1; i++) {
                newsItemsMap.put(i, (NewsItem[]) savedInstanceState.getParcelableArray(ConstantManager.NEWS_ITEM_LINK + i));
            }
        }
        initPages();
    }

    private void initPages() {
        Log.d(TAG, "initPages");

        setContentView(R.layout.sample_main);
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Log.d(TAG, "onTabSelected");
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        static final String TAG = ConstantManager.TAG_PREFIX + "SectionsPagerAd";

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new DummySectionFragment();
            Bundle args = new Bundle();
            args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            //Log.d(TAG, "getCount");

            //return 3;
            return newsListItems.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.d(TAG, "getPageTitle");
            return newsListItems[position].getName();
        }
    }

    public static class DummySectionFragment extends Fragment {
        static final String TAG = ConstantManager.TAG_PREFIX + "DummySection";
        public static final String ARG_SECTION_NUMBER = "section_number";
        NewsItem[] newsItems = new NewsItem[0];
        RecyclerView recyclerView;
        ProgressDialog mProgressDialog;


        public DummySectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.d(TAG, "onCreateView");
            View rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
            recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
            LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
            recyclerView.setLayoutManager(llm);
            getNews();
            final Context context = this.getActivity();
            recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Log.d(TAG, "onItemClick");
                    if (NetworkUtils.isNetworkAvailable(context)) {
                        //Intent intent = new Intent(ScrollingActivity.this, EntryActivity.class);
                        //intent.putExtra(ConstantManager.ENTRY_LINK, siteList.get(position).getUri());
                        //startActivity(intent);
                        view.setBackgroundColor(Color.parseColor("#DBDBDB"));
                    } else {
                        //showToast(ConstantManager.INTERNET_OUT);
                    }
                }

                @Override
                public void onLongItemClick(View view, int position) {
                    Log.d(TAG, "onLongItemClick");
                    // do whatever
                }
            }));
            return rootView;
        }

        private void getNews() {
            final int num = getArguments().getInt(ARG_SECTION_NUMBER) - 1;
            Log.d(TAG, "getNews" + num);
            showProgress();
            try {
                RVAdapter adapter = new RVAdapter(Arrays.asList(newsItemsMap.get(num)));
                recyclerView.setAdapter(adapter);
                Log.d(TAG, "getNewsHist" + num);
                hideProgress();
            } catch (Exception e) {
                Log.d(TAG, "getNewsInet" + num);
                final Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstantManager.RESTURL).addConverterFactory(GsonConverterFactory.create()).build();
                GetBeansFromRest service = retrofit.create(GetBeansFromRest.class);
                service.getNews(newsListItems[num].getRestLink()).enqueue(new Callback<NewsItem[]>() {
                    @Override
                    public void onResponse(Call<NewsItem[]> call, Response<NewsItem[]> response) {
                        Log.d(TAG, "onResponseGetNews");
                        Log.d(TAG, "responseCode" + response.code());
                        if (response.code() == 200) {
                            newsItems = response.body();
                            if (newsItems == null) newsItems = new NewsItem[0];
                            newsItemsMap.put(num, newsItems);
                            RVAdapter adapter = new RVAdapter(Arrays.asList(newsItems));
                            recyclerView.setAdapter(adapter);
                            hideProgress();
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsItem[]> call, Throwable t) {
                        Log.d(TAG, "onFailureGetNews");
                        newsItems = new NewsItem[0];
                        RVAdapter adapter = new RVAdapter(Arrays.asList(newsItems));
                        recyclerView.setAdapter(adapter);
                        hideProgress();
                    }
                });
            }
        }

        public void showProgress() {
            Log.d(TAG, "showProgress");
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(this.getActivity());
                mProgressDialog.setCancelable(false);
                mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            mProgressDialog.show();
            mProgressDialog.setContentView(R.layout.progress_splash);
        }

        public void hideProgress() {
            Log.d(TAG, "hideProgress");
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.hide();
            }
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        outState.putParcelableArray(ConstantManager.NEWS_LINK, newsListItems);
        for (int i : newsItemsMap.keySet()) {
            outState.putParcelableArray(ConstantManager.NEWS_ITEM_LINK + i, newsItemsMap.get(i));
        }
    }
}
