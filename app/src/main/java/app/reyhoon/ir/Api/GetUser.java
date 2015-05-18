package app.reyhoon.ir.Api;

import app.reyhoon.ir.Object.Response.User;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;

public interface GetUser {
    @GET("/user/")
    void getUser(
            @Header("token") String token,
            Callback<User> callback);
}
