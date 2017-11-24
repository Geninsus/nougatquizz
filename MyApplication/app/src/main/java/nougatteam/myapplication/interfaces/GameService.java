package nougatteam.myapplication.interfaces;


import nougatteam.myapplication.pojo.GetThemesPojo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Iberos on 24/11/2017.
 */

public interface GameService {
    @GET("randomthemes/{count}")
    Call<GetThemesPojo> getThemes(@Path("count") int count);
}
