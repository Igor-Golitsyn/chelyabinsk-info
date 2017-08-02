package polus.ddns.net.chelinfo.bians;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by golit on 02.08.2017.
 */

public interface GetBiansFromRest {
    @GET("newsList")
    Call<NewsListItem[]> getNewsList();
}
