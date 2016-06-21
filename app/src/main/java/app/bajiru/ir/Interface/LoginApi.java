package app.bajiru.ir.Interface;

import app.bajiru.ir.Object.Gson.LoginGson;
import app.bajiru.ir.Object.Response.LoginResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface LoginApi {
    @POST("/user/login/")
    void login(
            @Body LoginGson loginGson,
            Callback<LoginResponse> callback);
}
