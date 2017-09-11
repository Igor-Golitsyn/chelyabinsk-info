package polus.ddns.net.chelinfo.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import polus.ddns.net.chelinfo.R;
import polus.ddns.net.chelinfo.beans.GetBeansFromRest;
import polus.ddns.net.chelinfo.beans.NewsItem;
import polus.ddns.net.chelinfo.beans.NewsListItem;
import polus.ddns.net.chelinfo.beans.PageRequest;
import polus.ddns.net.chelinfo.data.Edds74ru;
import polus.ddns.net.chelinfo.utils.ConstantManager;
import polus.ddns.net.chelinfo.utils.NetworkUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    static final String TAG = ConstantManager.TAG_PREFIX + "MainActivity";
    NewsListItem[] newsListItems;
    @BindView(R.id.pogoda)
    WebView pogoda;
    @BindView(R.id.school_text)
    TextView schoolText;
    @BindView(R.id.button_news)
    Button buttonNews;
    @BindView(R.id.search_voda_text)
    EditText searchVodaText;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getNews();
        if (savedInstanceState == null) {
            if (NetworkUtils.isNetworkAvailable(this)) {
                pogoda.loadUrl("file:///android_asset/yandex.html");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        schoolText.setText(Edds74ru.getSchool());
                    }
                }, 10);
            } else {
                showToast(ConstantManager.INTERNET_OUT);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 5000);
            }
        } else {
            pogoda.loadUrl("file:///android_asset/yandex.html");
            schoolText.setText(savedInstanceState.getString(ConstantManager.OTMENA));
        }
        searchVodaText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchVodaText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) getAndShowVoda();
                return false;
            }
        });
    }

    private void getAndShowVoda() {
        showProgress();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstantManager.RESTURL).addConverterFactory(GsonConverterFactory.create()).build();
        GetBeansFromRest service = retrofit.create(GetBeansFromRest.class);
        PageRequest pageRequest = new PageRequest(searchVodaText.getText().toString(), "vodaSearch");
        service.searchNews(pageRequest.getNewsName(), pageRequest).enqueue(new Callback<NewsItem[]>() {
            @Override
            public void onResponse(Call<NewsItem[]> call, Response<NewsItem[]> response) {
                Log.d(TAG, "onResponsegetAndShowVoda");
                if (response.code() == 200) {
                    DateFormat dateFormat = new SimpleDateFormat("dd MMMM");
                    NewsItem[] newsItems = response.body();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    DialogKommunityServices kommunityServices = new DialogKommunityServices();
                    Bundle bundle = new Bundle();
                    String[] data = new String[3];
                    data[0] = "МУП ПОВВ отключения:";
                    data[1] = "";
                    if (newsItems == null || newsItems.length == 0) {
                        data[1] = "По улице: " + searchVodaText.getText().toString() + "\nотключений не найдено.";
                    } else {
                        for (NewsItem item : newsItems) {
                            if (data[1].isEmpty()) data[1] = data[1] + item.getName() + "\nОтключат: " + dateFormat.format(new Date(item.getDate())) + "\n";
                            else data[1] = data[1] + "\n" + item.getName() + "\nОтключат: " + dateFormat.format(new Date(item.getDate())) + "\n";
                        }
                        data[1] = data[1] + "\nБолее подробно в разделе НОВОСТИ.";
                    }
                    data[2] = String.valueOf(R.drawable.logo_voda);
                    bundle.putStringArray(ConstantManager.DIALOG_ARRAY, data);
                    kommunityServices.setArguments(bundle);
                    hideProgress();
                    kommunityServices.show(fragmentManager, "dialog");
                }
            }

            @Override
            public void onFailure(Call<NewsItem[]> call, Throwable t) {
                Log.d(TAG, "onFailuregetAndShowVoda");
                hideProgress();
            }
        });
    }

    public void showToast(String massage) {
        Log.d(TAG, "showToast");
        Toast.makeText(this, massage, Toast.LENGTH_LONG).show();
    }

    private void getNews() {
        Log.d(TAG, "getNews");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstantManager.RESTURL).addConverterFactory(GsonConverterFactory.create()).build();
        GetBeansFromRest service = retrofit.create(GetBeansFromRest.class);
        service.getNewsList().enqueue(new Callback<NewsListItem[]>() {
            @Override
            public void onResponse(Call<NewsListItem[]> call, Response<NewsListItem[]> response) {
                if (response.code() == 200) {
                    NewsListItem[] list = response.body();
                    ArrayList<NewsListItem> arrayList = new ArrayList<>();
                    for (NewsListItem listItem : list) {
                        if (listItem.isShowNewsList()) arrayList.add(listItem);
                    }
                    newsListItems = arrayList.toArray(new NewsListItem[arrayList.size()]);
                    buttonNews.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<NewsListItem[]> call, Throwable t) {
            }
        });
    }

    @OnClick(R.id.search_voda_image)
    public void showVoda() {
        Log.d(TAG, "showVoda");
        getAndShowVoda();
    }

    @OnClick(R.id.image_voda)
    public void showSite(){
        Log.d(TAG, "showVoda");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ConstantManager.VODA));
        startActivity(intent);
    }

    @OnClick(R.id.button_centralny)
    public void showCentralny() {
        Log.d(TAG, "showCentralny");
        createDialog(ConstantManager.CENTRALNY, ConstantManager.EDDS74RU_CENTRALNY);
    }

    @OnClick(R.id.button_kalininsky)
    public void showKalininsky() {
        Log.d(TAG, "showKalininsky");
        createDialog(ConstantManager.KALININSKY, ConstantManager.EDDS74RU_KALININSKY);
    }

    @OnClick(R.id.button_kurchatovsky)
    public void showKurchatovsky() {
        Log.d(TAG, "showKurchatovsky");
        createDialog(ConstantManager.KURCHATOVSKY, ConstantManager.EDDS74RU_KURCHATOVSKY);
    }

    @OnClick(R.id.button_leninsky)
    public void showLeninsky() {
        Log.d(TAG, "showLeninsky");
        createDialog(ConstantManager.LENINSKY, ConstantManager.EDDS74RU_LENINSKY);
    }

    @OnClick(R.id.button_metallurgichesky)
    public void showMetallurgichesky() {
        Log.d(TAG, "showMetallurgichesky");
        createDialog(ConstantManager.METALLURGICHESKY, ConstantManager.EDDS74RU_METALLURGICHESKY);
    }

    @OnClick(R.id.button_sovetsky)
    public void showSovetsky() {
        Log.d(TAG, "showSovetsky");
        createDialog(ConstantManager.SOVETSKY, ConstantManager.EDDS74RU_SOVETSKY);
    }

    @OnClick(R.id.button_traktorozavodsky)
    public void showTraktorozavodsky() {
        Log.d(TAG, "showTraktorozavodsky");
        createDialog(ConstantManager.TRAKTOROZAVODSKY, ConstantManager.EDDS74RU_TRAKTOROZAVODSKY);
    }

    @OnClick(R.id.button_request)
    public void request() {
        Log.d(TAG, "request");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=polus.ddns.net.chelinfo"));
        startActivity(intent);
    }

    @OnClick(R.id.button_news)
    public void showNews() {
        Log.d(TAG, "showNews");
        Intent intent = new Intent(MainActivity.this, NewsActivity.class);
        ArrayList<NewsListItem> listItems = new ArrayList<>(Arrays.asList(newsListItems));
        intent.putParcelableArrayListExtra(ConstantManager.NEWS_LINK, listItems);
        startActivity(intent);
    }

    public void showError(String massage, Exception error) {
        Log.d(TAG, "showError");
        showToast(massage);
        Log.e(TAG, String.valueOf(error));
    }

    private void createDialog(String district, String link) {
        Log.d(TAG, "createDialog");
        if (!NetworkUtils.isNetworkAvailable(this)) {
            showError(ConstantManager.INTERNET_OUT, new Exception(""));
            return;
        }
        String[] data = Edds74ru.getKommunityServices(district, link);
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogKommunityServices kommunityServices = new DialogKommunityServices();
        Bundle bundle = new Bundle();
        bundle.putStringArray(ConstantManager.DIALOG_ARRAY, data);
        kommunityServices.setArguments(bundle);
        kommunityServices.show(fragmentManager, "dialog");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        outState.putString(ConstantManager.OTMENA, schoolText.getText().toString());
    }

    public void showProgress() {
        Log.d(TAG, "showProgress");
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
