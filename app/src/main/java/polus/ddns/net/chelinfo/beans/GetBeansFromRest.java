package polus.ddns.net.chelinfo.beans;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by golit on 02.08.2017.
 */

public interface GetBeansFromRest {
    /*@GET("newsList")
    Call<NewsListItem[]> getNewsList();*/

    @GET("newsList")
    Call<NewsListItem[]> getNewsList();

    @GET("{restLink}")
    Call<NewsItem[]> getNews(@Path("restLink") String restLink);

    @POST("newsPage")
    Call<NewsPage> getNewsPage(@Body PageRequest pageRequest);
}
