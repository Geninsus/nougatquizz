package nougatteam.myapplication.interfaces;


import nougatteam.myapplication.pojo.GetQuestionsPojo;
import nougatteam.myapplication.pojo.GetScoresPojo;
import nougatteam.myapplication.pojo.GetThemesPojo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Iberos on 24/11/2017.
 */

public interface GameService {
    @GET("randomthemes/{count}")
    Call<GetThemesPojo> getThemes(@Path("count") int count);

    @GET("randomquestions/{theme}/{count}")
    Call<GetQuestionsPojo> getQuestions(@Path("theme") String theme, @Path("count") int count);

    @GET("scores/{count}")
    Call<GetScoresPojo> getScores(@Path("count") int count);

    @POST("scores/{name}/{theme}/{score}/{count}")
    Call<GetScoresPojo> addScore(@Path("name") String name, @Path("theme") String theme, @Path("score") int score, @Path("count") int count);
}
