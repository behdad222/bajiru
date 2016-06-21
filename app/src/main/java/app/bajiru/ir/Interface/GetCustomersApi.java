package app.bajiru.ir.Interface;

import app.bajiru.ir.Object.Response.CustomersResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;

public interface GetCustomersApi {
    @GET("/customer/")
    void getCustomers(
            @Header("token") String token,
            @Query("per_page") int per_page,
            @Query("page") int page,
            Callback<CustomersResponse> callback);
}
