package polus.ddns.net.chelinfo.data;

import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import polus.ddns.net.chelinfo.R;
import polus.ddns.net.chelinfo.activity.MainActivity;
import polus.ddns.net.chelinfo.utils.ConstantManager;


/**
 * Created by Игорь on 09.12.2016.
 */

public class Edds74ru {
    static final String TAG = ConstantManager.TAG_PREFIX + "Edds74ru";

    public static String getSchool() {
        Log.d(TAG, "getSchool");
        try {
            Document document = Jsoup.connect(ConstantManager.EDDS74RU_SCHOOL).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36").referrer("http://www.google.com").get();
            return document.text().replace("!", "!\n");
        } catch (IOException e) {
            return ConstantManager.DOWNLOAD_FAIL;
        }
    }

    public static String[] getKommunityServices(String districtName, String link) {
        Log.d(TAG, "getKommunityServices");
        String situation = getSituation(link);
        int icon;
        switch (districtName) {
            case ConstantManager.CENTRALNY:
                icon = R.drawable.centr;
                break;
            case ConstantManager.KALININSKY:
                icon = R.drawable.kalinin;
                break;
            case ConstantManager.KURCHATOVSKY:
                icon = R.drawable.kurchatov;
                break;
            case ConstantManager.LENINSKY:
                icon = R.drawable.lenin;
                break;
            case ConstantManager.METALLURGICHESKY:
                icon = R.drawable.metall;
                break;
            case ConstantManager.SOVETSKY:
                icon = R.drawable.sovet;
                break;
            case ConstantManager.TRAKTOROZAVODSKY:
                icon = R.drawable.traktor;
                break;
            default:
                icon = R.drawable.def;
        }
        return new String[]{districtName, situation, String.valueOf(icon)};
    }

    private static String getSituation(String link) {
        Log.d(TAG, "getSituation");
        String rez = ConstantManager.DOWNLOAD_FAIL;
        for (int i = 0; i < 10; i++) {
            try {
                Document document = Jsoup.connect(link).timeout(10000).get();
                String rezult = document.text();
                rezult = rezult.replaceAll(":", ":\n");
                rezult = rezult.replaceAll(";", ";\n");
                rezult = rezult.replaceAll("Отключен", "\n\nОтключен");
                rezult = rezult.replaceAll("Оператив", "\n\nОператив");
                rez = rezult;
            } catch (IOException e) {
                rez = ConstantManager.DOWNLOAD_FAIL;
                try {
                    Thread.sleep(500);
                } catch (Exception e1) {
                    Log.d(TAG, "getSituationSleepEx");
                }
            }
            if (!rez.equals(ConstantManager.DOWNLOAD_FAIL)) {
                return rez;
            }
        }
        return rez;
    }
}
