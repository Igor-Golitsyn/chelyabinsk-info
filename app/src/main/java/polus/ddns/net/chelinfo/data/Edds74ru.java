package polus.ddns.net.chelinfo.data;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

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
            return document.text().replace("!","!\n");
        } catch (IOException e) {
            return "";
        }
    }

    public static String[] getKommunityServices(String districtName, String link) {
        Log.d(TAG, "getKommunityServices");
        String situation = getSituation(link);
        return new String[]{districtName,situation};
    }

    private static String getSituation(String link) {
        Log.d(TAG, "getSituation");
        try {
            Document document = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36").referrer("http://www.google.com").get();
            String rezult = document.text();
            rezult = rezult.replaceAll(":", ":\n");
            rezult = rezult.replaceAll(";", ";\n");
            rezult = rezult.replaceAll("Отключен", "\nОтключен");
            rezult = rezult.replaceAll("Оператив", "\nОператив");
            return rezult;
        } catch (IOException e) {
            return ConstantManager.OTKLUCHENIY_NET;
        }
    }
}
