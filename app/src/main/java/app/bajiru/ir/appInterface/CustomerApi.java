package app.bajiru.ir.appInterface;

import app.bajiru.ir.StaticFields;
import app.bajiru.ir.object.Response.CustomersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CustomerApi {
	@GET(StaticFields.CUSTOMER)
	Call<CustomersResponse> getCustomer(
			@Query(StaticFields.PER_PAGE) int per_page,
			@Query(StaticFields.PAGE) int page);
}
