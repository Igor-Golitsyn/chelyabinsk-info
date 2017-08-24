package polus.ddns.net.chelinfo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import polus.ddns.net.chelinfo.R;
import polus.ddns.net.chelinfo.beans.GetBeansFromRest;
import polus.ddns.net.chelinfo.beans.NewsItem;
import polus.ddns.net.chelinfo.beans.NewsListItem;
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
    @BindView(R.id.prognoz)
    WebView prognoz;
    @BindView(R.id.school_text)
    TextView schoolText;
    @BindView(R.id.button_news)
    Button buttonNews;


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
                pogoda.loadUrl("file:///android_asset/chelpogoda_center.html");
                prognoz.loadUrl("file:///android_asset/prognoz_center.html");
                schoolText.setText(Edds74ru.getSchool());
            } else {
                showToast(ConstantManager.INTERNET_OUT);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 5000);
            }
        } else {
            if (NetworkUtils.isNetworkAvailable(this)) {
                pogoda.loadUrl("file:///android_asset/chelpogoda_center.html");
                prognoz.loadUrl("file:///android_asset/prognoz_center.html");
                schoolText.setText(Edds74ru.getSchool());
            } else {
                showToast(ConstantManager.INTERNET_OUT);
            }
        }
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

    @OnClick(R.id.button_center)
    public void loadCenterPrognoz() {
        Log.d(TAG, "loadCenterPrognoz");
        pogoda.loadUrl("file:///android_asset/chelpogoda_center.html");
        prognoz.loadUrl("file:///android_asset/prognoz_center.html");
    }

    @OnClick(R.id.button_north)
    public void loadNorthPrognoz() {
        Log.d(TAG, "loadNorthPrognoz");
        pogoda.loadUrl("file:///android_asset/chelpogoda_north.html");
        prognoz.loadUrl("file:///android_asset/prognoz_north.html");
    }

    @OnClick(R.id.button_south)
    public void loadSouthPrognoz() {
        Log.d(TAG, "loadSouthPrognoz");
        pogoda.loadUrl("file:///android_asset/chelpogoda_south.html");
        prognoz.loadUrl("file:///android_asset/prognoz_south.html");
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

}
