package app.bajiru.ir.Interface;

import app.bajiru.ir.Object.Response.UsersResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;

public interface GetUsersApi {
    @GET("/user/all/")
    void getUsers(
            @Header("token") String token,
            Callback<UsersResponse> callback);
}
