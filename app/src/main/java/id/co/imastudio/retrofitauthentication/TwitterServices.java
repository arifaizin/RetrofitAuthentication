package id.co.imastudio.retrofitauthentication;

import java.util.List;

import id.co.imastudio.retrofitauthentication.model.TokenResponse;
import id.co.imastudio.retrofitauthentication.model.TwitterResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by idn on 1/4/2018.
 */

public interface TwitterServices {
    @FormUrlEncoded
    @POST("oauth2/token")
    Call<TokenResponse> getAuthenticationToken(
            @Header("Content-Type") String con_type,
            @Header("Authorization") String author,
            @Field ("grant_type") String grant_type
    );


    @GET("/1.1/statuses/user_timeline.json?screen_name=ipphoright&count=7")
    Call<List<TwitterResponse>> getStatuses(
            @Header("Authorization") String author
    );

}
