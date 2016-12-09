package polus.ddns.net.chelinfo.activity;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import polus.ddns.net.chelinfo.R;
import polus.ddns.net.chelinfo.data.Edds74ru;
import polus.ddns.net.chelinfo.utils.ConstantManager;

public class MainActivity extends AppCompatActivity {
    static final String TAG = ConstantManager.TAG_PREFIX + "MainActivity";
    @BindView(R.id.pogoda)
    WebView pogoda;
    @BindView(R.id.prognoz)
    WebView prognoz;
    @BindView(R.id.school_text)
    TextView schoolText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        pogoda.loadUrl("file:///android_asset/chelpogoda.html");
        prognoz.loadUrl("file:///android_asset/prognoz.html");
        schoolText.setText(Edds74ru.getSchool());
    }

    @OnClick(R.id.button_centralny)
    public void showCentralny() {
        Log.d(TAG, "showCentralny");
        createDialog(ConstantManager.CENTRALNY,ConstantManager.EDDS74RU_CENTRALNY);
    }
    @OnClick(R.id.button_kalininsky)
    public void showKalininsky() {
        Log.d(TAG, "showKalininsky");
        createDialog(ConstantManager.KALININSKY,ConstantManager.EDDS74RU_KALININSKY);
    }
    @OnClick(R.id.button_kurchatovsky)
    public void showKurchatovsky() {
        Log.d(TAG, "showKurchatovsky");
        createDialog(ConstantManager.KURCHATOVSKY,ConstantManager.EDDS74RU_KURCHATOVSKY);
    }
    @OnClick(R.id.button_leninsky)
    public void showLeninsky() {
        Log.d(TAG, "showLeninsky");
        createDialog(ConstantManager.LENINSKY,ConstantManager.EDDS74RU_LENINSKY);
    }
    @OnClick(R.id.button_metallurgichesky)
    public void showMetallurgichesky() {
        Log.d(TAG, "showMetallurgichesky");
        createDialog(ConstantManager.METALLURGICHESKY,ConstantManager.EDDS74RU_METALLURGICHESKY);
    }
    @OnClick(R.id.button_sovetsky)
    public void showSovetsky() {
        Log.d(TAG, "showSovetsky");
        createDialog(ConstantManager.SOVETSKY,ConstantManager.EDDS74RU_SOVETSKY);
    }
    @OnClick(R.id.button_traktorozavodsky)
    public void showTraktorozavodsky() {
        Log.d(TAG, "showCentralny");
        createDialog(ConstantManager.TRAKTOROZAVODSKY,ConstantManager.EDDS74RU_TRAKTOROZAVODSKY);
    }


    private void createDialog(String district, String link) {
        Log.d(TAG, "createDialog");
        String[] data = Edds74ru.getKommunityServices(district, link);
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogKommunityServices kommunityServices = new DialogKommunityServices();
        Bundle bundle = new Bundle();
        bundle.putStringArray(ConstantManager.DIALOG_ARRAY, data);
        kommunityServices.setArguments(bundle);
        kommunityServices.show(fragmentManager, "dialog");
    }

}
