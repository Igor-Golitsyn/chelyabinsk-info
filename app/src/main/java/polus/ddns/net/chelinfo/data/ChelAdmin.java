package polus.ddns.net.chelinfo.data;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import polus.ddns.net.chelinfo.utils.ConstantManager;

public class ChelAdmin {
    static final String TAG = ConstantManager.TAG_PREFIX + "ChelAdmin";

    public static String getSchool() {
        Log.d(TAG, "getSchool");
        try {
            Document document = Jsoup.connect(ConstantManager.CHELADMINURL).userAgent(ConstantManager.USERAGENT_TEXT).referrer(ConstantManager.REFERRER).get();

            String text = "";
            try {
                text = document.getElementsByClass("field-items").first().text();
            } catch (Exception e) {
            }
            return text;
        } catch (IOException e) {
            return ConstantManager.DOWNLOAD_FAIL;
        }
    }
}
