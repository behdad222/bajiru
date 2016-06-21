package app.bajiru.ir.appInterface;

import app.bajiru.ir.StaticFields;
import app.bajiru.ir.object.Gson.LoginGson;
import app.bajiru.ir.object.Response.LoginResponse;
import app.bajiru.ir.object.Response.UserResponse;
import app.bajiru.ir.object.Response.UsersResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {
	@POST(StaticFields.LOGIN_USER)
	Call<LoginResponse> login(
			@Body LoginGson loginGson);

	@GET(StaticFields.USER)
	Call<UserResponse> getUser();

	@GET(StaticFields.ALL_USER)
	Call<UsersResponse> getAllUser();
}
