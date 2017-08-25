package polus.ddns.net.chelinfo.beans;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
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

    @POST("{restLink}")
    Call<NewsItem[]> getNews(@Path("restLink") String restLink);

    @POST("newsPage")
    Call<NewsPage> getNewsPage(@Body PageRequest pageRequest);

    @POST("{restLink}")
    Call<NewsItem[]> searchNews(@Path("restLink") String restLink, @Body PageRequest pageRequest);
}
