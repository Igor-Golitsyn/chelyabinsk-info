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
            Document document = Jsoup.connect(ConstantManager.OBLRU_SCHOOL).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36").referrer(ConstantManager.REFERRER).get();
            return document.getElementsByClass("quote_text").first().text();
        } catch (IOException e) {
            return ConstantManager.DOWNLOAD_FAIL;
        }
    }
}
