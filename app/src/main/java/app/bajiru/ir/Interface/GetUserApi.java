package app.bajiru.ir.Interface;

import app.bajiru.ir.Object.Response.UserResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;

public interface GetUserApi {
    @GET("/user/")
    void getUser(
            @Header("token") java.lang.String token,
            Callback<UserResponse> callback);
}
