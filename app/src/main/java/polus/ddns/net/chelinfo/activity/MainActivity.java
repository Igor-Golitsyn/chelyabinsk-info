package polus.ddns.net.chelinfo.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import polus.ddns.net.chelinfo.R;
import polus.ddns.net.chelinfo.beans.GetBeansFromRest;
import polus.ddns.net.chelinfo.beans.NewsItem;
import polus.ddns.net.chelinfo.beans.NewsListItem;
import polus.ddns.net.chelinfo.beans.PageRequest;
import polus.ddns.net.chelinfo.beans.yandexBeans.FeatureMember;
import polus.ddns.net.chelinfo.beans.yandexBeans.YandexBean;
import polus.ddns.net.chelinfo.data.Edds74ru;
import polus.ddns.net.chelinfo.data.OblRu;
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
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private ProgressDialog mProgressDialog;
    private boolean isSearchLocationProcess = false;
    private NewsItem[] vodaNews;
    private LocationManager locationManager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        context = this;

        checkAccess(Manifest.permission.ACCESS_NETWORK_STATE);
        checkAccess(Manifest.permission.INTERNET);
        checkAccess(Manifest.permission.ACCESS_FINE_LOCATION);
        checkAccess(Manifest.permission.ACCESS_COARSE_LOCATION);

        getNews();
        if (savedInstanceState == null) {
            if (NetworkUtils.isNetworkAvailable(this)) {
                pogoda.loadUrl("file:///android_asset/yandex.html");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        schoolText.setText(OblRu.getSchool());
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
            isSearchLocationProcess = savedInstanceState.getBoolean(ConstantManager.IS_SEARCH_LOCATION_PROCESS);
            vodaNews = (NewsItem[]) savedInstanceState.getParcelableArray(ConstantManager.VODA_NEWS);
            if (isSearchLocationProcess) {
                showProgress();
                locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            }
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

    private void checkAccess(String permission) {
        Log.d(TAG, "checkAccess");
        Dexter.withActivity(this)
                .withPermission(permission)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {/* ... */}

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                }).check();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        if (isSearchLocationProcess) {
            okClicked();
        }
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
        if (isSearchLocationProcess) locationManager.removeUpdates(locationListener);
    }

    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            Log.d(TAG, "onLocationChanged");
            locationManager.removeUpdates(locationListener);
            isSearchLocationProcess = false;
            getAndShowVoda(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d(TAG, "onStatusChanged");
        }


        @Override
        public void onProviderEnabled(String provider) {
            Log.d(TAG, "onProviderEnabled");
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                showWarningPermDisabled();
            } else {
                locationManager.removeUpdates(locationListener);
                isSearchLocationProcess = false;
                getAndShowVoda(locationManager.getLastKnownLocation(provider));
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d(TAG, "onProviderDisabled");
        }
    };

    private void showWarningPermDisabled() {
        Log.d(TAG, "showWarningPermDisabled");
        hideProgress();
        showToast(ConstantManager.GEO_DISABLED_WARNING);
        showVodaDialog(vodaNews);
    }

    private void getAndShowVoda(Location location) {
        Log.d(TAG, "getAndShowVodaLocation");
        showProgress();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstantManager.YANDEXGEOCODE).addConverterFactory(GsonConverterFactory.create()).build();
        GetBeansFromRest service = retrofit.create(GetBeansFromRest.class);
        service.getLocation(String.valueOf(location.getLongitude() + "," + location.getLatitude()), "5", "json", "house").enqueue(new Callback<YandexBean>() {
            @Override
            public void onResponse(Call<YandexBean> call, Response<YandexBean> response) {
                hideProgress();
                if (response.code() == 200) {
                    List<FeatureMember> featureMemberList = response.body().response.geoObjectCollection.featureMember;
                    Set<NewsItem> filterListHouse = new HashSet<NewsItem>();
                    for (FeatureMember featureMember : featureMemberList) {
                        String[] currentAdr = featureMember.geoObject.metaDataProperty.geocoderMetaData.addressDetails.country.addressLine.split(",");
                        String[] street = currentAdr[1].trim().split(" ");
                        String house = currentAdr[2].trim().toLowerCase();
                        Pattern pattern = Pattern.compile("^[0-9]+");
                        Matcher matcher = pattern.matcher(house);
                        if (matcher.find()) house = house.substring(matcher.start(), matcher.end());
                        for (NewsItem item : vodaNews) {
                            for (String str : street) {
                                if (item.getName().toLowerCase().contains(str.trim().toLowerCase())) {
                                    String[] adr = item.getName().toLowerCase().split(" ");
                                    for (String ad : adr) {
                                        matcher = pattern.matcher(ad);
                                        if (matcher.find())
                                            ad = ad.substring(matcher.start(), matcher.end());
                                        if (ad.trim().equals(house)) filterListHouse.add(item);
                                    }
                                }
                            }
                        }
                    }
                    if (filterListHouse.size() > 0)
                        showVodaDialog(filterListHouse.toArray(new NewsItem[filterListHouse.size()]));
                    else showVodaDialog(new NewsItem[]{});
                } else {
                    showToast(ConstantManager.ERROR_LOCATE);
                    showVodaDialog(vodaNews);
                }
            }

            @Override
            public void onFailure(Call<YandexBean> call, Throwable t) {
                hideProgress();
                showToast(ConstantManager.ERROR_LOCATE);
                showVodaDialog(vodaNews);
            }
        });
    }

    private void getAndShowVoda() {
        Log.d(TAG, "getAndShowVoda");
        showProgress();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ConstantManager.RESTURL).addConverterFactory(GsonConverterFactory.create()).build();
        GetBeansFromRest service = retrofit.create(GetBeansFromRest.class);
        PageRequest pageRequest = new PageRequest(searchVodaText.getText().toString(), "vodaSearch");
        service.searchNews(pageRequest.getNewsName(), pageRequest).enqueue(new Callback<NewsItem[]>() {
            @Override
            public void onResponse(Call<NewsItem[]> call, Response<NewsItem[]> response) {
                Log.d(TAG, "onResponsegetAndShowVoda");
                if (response.code() == 200) {
                    vodaNews = response.body();
                    if (!searchVodaText.getText().toString().isEmpty()) {
                        hideProgress();
                        showVodaDialog(vodaNews);
                    } else {
                        QuestDialog questDialog = new QuestDialog();
                        questDialog.show(getSupportFragmentManager(), "dialog");
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsItem[]> call, Throwable t) {
                Log.d(TAG, "onFailuregetAndShowVoda");
                hideProgress();
                showToast(ConstantManager.ERROR_DOWNLOAD);
            }
        });
    }

    private void showVodaDialog(NewsItem[] newsItems) {
        Log.d(TAG, "showVodaDialog");
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM");
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogKommunityServices kommunityServices = new DialogKommunityServices();
        Bundle bundle = new Bundle();
        String[] data = new String[3];
        data[0] = "МУП ПОВВ отключения:";
        data[1] = "";
        if (newsItems == null || newsItems.length == 0) {
            if (searchVodaText.getText().toString().isEmpty()) {
                data[1] = "По вашему местоположению \nотключений не найдено.";
            } else {
                data[1] = "По улице: " + searchVodaText.getText().toString() + "\nотключений не найдено.";
            }

        } else {
            for (NewsItem item : newsItems) {
                if (data[1].isEmpty())
                    data[1] = data[1] + item.getName() + "\nОтключат: " + dateFormat.format(new Date(item.getDate())) + "\n";
                else
                    data[1] = data[1] + "\n" + item.getName() + "\nОтключат: " + dateFormat.format(new Date(item.getDate())) + "\n";
            }
            data[1] = data[1] + "\nБолее подробно в разделе НОВОСТИ.";
        }
        data[2] = String.valueOf(R.drawable.logo_voda);
        bundle.putStringArray(ConstantManager.DIALOG_ARRAY, data);
        kommunityServices.setArguments(bundle);
        hideProgress();
        kommunityServices.show(fragmentManager, "dialog");
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
                        //if (listItem.isShowNewsList()) arrayList.add(listItem);
                        //TODO
                        arrayList.add(listItem);
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
    public void showSite() {
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
        outState.putBoolean(ConstantManager.IS_SEARCH_LOCATION_PROCESS, isSearchLocationProcess);
        outState.putParcelableArray(ConstantManager.VODA_NEWS, vodaNews);
    }

    public void showProgress() {
        Log.d(TAG, "showProgress");
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        Log.d(TAG, "hideProgress");
        progressBar.setVisibility(View.GONE);
    }

    public void okClicked() {
        Log.d(TAG, "okClicked");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            showWarningPermDisabled();
            return;
        }
        showProgress();
        isSearchLocationProcess = true;
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 1, locationListener);
    }

    public void noClicked() {
        Log.d(TAG, "noClicked");
        showVodaDialog(vodaNews);
    }
}
