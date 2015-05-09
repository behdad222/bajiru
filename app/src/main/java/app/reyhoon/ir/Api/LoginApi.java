package app.reyhoon.ir.Api;

import app.reyhoon.ir.Object.Response.LoginResponse;
import app.reyhoon.ir.Object.Gson.LoginGson;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface LoginApi {
    @POST("/user/login/")
    void login(
            @Body LoginGson loginGson,
            Callback<LoginResponse> callback);
}
