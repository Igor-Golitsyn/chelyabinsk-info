package polus.ddns.net.chelinfo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import polus.ddns.net.chelinfo.R;
import polus.ddns.net.chelinfo.beans.GetBeansFromRest;
import polus.ddns.net.chelinfo.beans.NewsItem;
import polus.ddns.net.chelinfo.beans.NewsPage;
import polus.ddns.net.chelinfo.beans.PageRequest;
import polus.ddns.net.chelinfo.utils.ConstantManager;
import polus.ddns.net.chelinfo.utils.RVAdapter;
import polus.ddns.net.chelinfo.utils.RVFotoAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by golit on 24.08.2017.
 */

public class EntryActivityPage extends AppCompatActivity {
    static final String TAG = ConstantManager.TAG_PREFIX + "EntryActivity";
    @BindView(R.id.entry_title)
    TextView titleText;
    @BindView(R.id.text_news)
    TextView newsText;
    @BindView(R.id.entry_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.button_1)
    Button button1;
    @BindView(R.id.button_2)
    Button button2;
    NewsPage newsPage;
    PageRequest pageRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.entry_activity);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                pageRequest = (PageRequest) extras.getSerializable(ConstantManager.PAGE_REQUEST);
            } else {
                finish();
            }
        } else {
            pageRequest = (PageRequest) savedInstanceState.getSerializable(ConstantManager.PAGE_REQUEST);
            newsPage = (NewsPage) savedInstanceState.getSerializable(ConstantManager.NEWS_PAGE);
        }
        System.out.println("*************************");
        System.out.println(pageRequest);
        System.out.println("*************************");
        getNewsPage();
    }

    private void getNewsPage() {
        Log.d(TAG, "getNewsPage");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstantManager.RESTURL).addConverterFactory(GsonConverterFactory.create()).build();
        GetBeansFromRest service = retrofit.create(GetBeansFromRest.class);
        service.getNewsPage(pageRequest).enqueue(new Callback<NewsPage>() {
            @Override
            public void onResponse(Call<NewsPage> call, Response<NewsPage> response) {
                if (response.code() == 200) {
                    newsPage = response.body();
                    setDataToFields();
                }
            }

            @Override
            public void onFailure(Call<NewsPage> call, Throwable t) {
                newsPage = new NewsPage(ConstantManager.DOWNLOAD_FAIL, null, null, null, null, null, null);
            }
        });
    }

    private void setDataToFields() {
        Log.d(TAG, "setDataToFields");
        titleText.setText(newsPage.getName());
        newsText.setText(newsPage.getText());
        if (newsPage.getButton1Text() != null) {
            button1.setVisibility(View.VISIBLE);
            button1.setText(newsPage.getButton1Text());
        }
        if (newsPage.getButton2Text() != null) {
            button2.setVisibility(View.VISIBLE);
            button2.setText(newsPage.getButton2Text());
        }
        RVFotoAdapter adapter;
        if (newsPage.getImages() != null) {
            adapter = new RVFotoAdapter(new ArrayList<>(newsPage.getImages()), this);
        } else {
            adapter=new RVFotoAdapter(new ArrayList<String>(),this);
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        outState.putSerializable(ConstantManager.PAGE_REQUEST, pageRequest);
        outState.putSerializable(ConstantManager.NEWS_PAGE, newsPage);
    }
}

