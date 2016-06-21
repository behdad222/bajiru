package app.bajiru.ir.appInterface;

import app.bajiru.ir.object.Gson.LoginGson;
import app.bajiru.ir.object.Response.LoginResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface LoginApi {
    @POST("/user/login/")
    void login(
            @Body LoginGson loginGson,
            Callback<LoginResponse> callback);
}
