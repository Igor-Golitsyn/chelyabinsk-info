package polus.ddns.net.chelinfo.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import polus.ddns.net.chelinfo.R;
import polus.ddns.net.chelinfo.beans.GetBeansFromRest;
import polus.ddns.net.chelinfo.beans.NewsItem;
import polus.ddns.net.chelinfo.beans.NewsPage;
import polus.ddns.net.chelinfo.beans.PageRequest;
import polus.ddns.net.chelinfo.utils.ConstantManager;
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
    @BindView(R.id.text_news_small)
    TextView newsTextSmall;
    @BindView(R.id.entry_recycler_view)
    RecyclerView entryRecyclerView;
    @BindView(R.id.button_1)
    Button button1;
    @BindView(R.id.button_2)
    Button button2;
    NewsPage newsPage;
    PageRequest pageRequest;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.entry_activity);
        ButterKnife.bind(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        entryRecyclerView.setLayoutManager(llm);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                pageRequest = (PageRequest) extras.getSerializable(ConstantManager.PAGE_REQUEST);
                getNewsPage();
            } else {
                finish();
            }
        } else {
            pageRequest = (PageRequest) savedInstanceState.getSerializable(ConstantManager.PAGE_REQUEST);
            newsPage = (NewsPage) savedInstanceState.getSerializable(ConstantManager.NEWS_PAGE);
            setDataToFields();
        }
    }

    private void getNewsPage() {
        Log.d(TAG, "getNewsPage");
        showProgress();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstantManager.RESTURL).addConverterFactory(GsonConverterFactory.create()).build();
        GetBeansFromRest service = retrofit.create(GetBeansFromRest.class);
        service.getNewsPage(pageRequest).enqueue(new Callback<NewsPage>() {
            @Override
            public void onResponse(Call<NewsPage> call, Response<NewsPage> response) {
                if (response.code() == 200) {
                    newsPage = response.body();
                    setDataToFields();
                    hideProgress();
                }
            }

            @Override
            public void onFailure(Call<NewsPage> call, Throwable t) {
                newsPage = new NewsPage(ConstantManager.DOWNLOAD_FAIL, null, null, null, null, null, null);
                setDataToFields();
                hideProgress();
            }
        });
    }

    private void setDataToFields() {
        Log.d(TAG, "setDataToFields");
        titleText.setText(newsPage.getName());
        newsTextSmall.setText(newsPage.getText());
        if (newsPage.getButton1Text() != null && !newsPage.getButton1Text().isEmpty()) {
            button1.setVisibility(View.VISIBLE);
            button1.setText(newsPage.getButton1Text());
        }
        if (newsPage.getButton2Text() != null && !newsPage.getButton2Text().isEmpty()) {
            button2.setVisibility(View.VISIBLE);
            button2.setText(newsPage.getButton2Text());
        }
        RVFotoAdapter adapter;
        if (newsPage.getImages() != null && !newsPage.getImages().isEmpty()) {
            adapter = new RVFotoAdapter(new ArrayList<>(newsPage.getImages()), this);
        } else {
            adapter = new RVFotoAdapter(new ArrayList<String>(), this);
        }
        entryRecyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.button_1)
    public void onClickButton1() {
        Log.d(TAG, "onClickButton1");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsPage.getButton1Action()));
        startActivity(intent);
    }

    @OnClick(R.id.button_2)
    public void onClickButton2() {
        Log.d(TAG, "onClickButton1");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsPage.getButton2Action()));
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        outState.putSerializable(ConstantManager.PAGE_REQUEST, pageRequest);
        outState.putSerializable(ConstantManager.NEWS_PAGE, newsPage);
    }

    public void showProgress() {
        Log.d(TAG, "showProgressDum");
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
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

