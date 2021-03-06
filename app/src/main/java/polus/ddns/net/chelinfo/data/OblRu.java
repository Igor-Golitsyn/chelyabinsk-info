package polus.ddns.net.chelinfo.data;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import polus.ddns.net.chelinfo.utils.ConstantManager;

public class OblRu {
    static final String TAG = ConstantManager.TAG_PREFIX + "OblRu";

    public static String getSchool() {
        Log.d(TAG, "getSchool");
        try {
            Document document = Jsoup.connect(ConstantManager.OBLRU_SCHOOL).userAgent(ConstantManager.USERAGENT_TEXT).referrer(ConstantManager.REFERRER).get();
            String text = "";
            try {
                text = document.getElementsByClass("quote_text").first().text();
            } catch (Exception e) {
            }
            return text;
        } catch (IOException e) {
            return ConstantManager.DOWNLOAD_FAIL;
        }
    }
}
