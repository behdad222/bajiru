package app.bajiru.ir.view.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import app.bajiru.ir.MainAplication;
import app.bajiru.ir.R;
import app.bajiru.ir.appInterface.UserApi;
import app.bajiru.ir.object.Gson.LoginGson;
import app.bajiru.ir.object.Response.LoginResponse;
import app.bajiru.ir.service.ServiceGenerator;
import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
	Context context;

	@Bind(R.id.login)
	Button login;
	@Bind(R.id.userName)
	EditText useName;
	@Bind(R.id.password)
	EditText password;
	@Bind(R.id.loading)
	ProgressBar loading;
	@BindString(R.string.unauthorized)
	String unauthorized;
	@BindString(R.string.error_conection)
	String errorConection;
	@BindString(R.string.server_error)
	String serverError;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_login, container, false);
		context = getActivity();
		ButterKnife.bind(this, view);

		return view;
	}

	@OnClick(R.id.login)
	public void sendToserver() {
		login.setEnabled(false);
		useName.setEnabled(false);
		password.setEnabled(false);
		loading.setVisibility(View.VISIBLE);

		UserApi userApi = ServiceGenerator
				.createServiceBasicAuthentication(UserApi.class);

		Call<LoginResponse> call = userApi.login(
				new LoginGson(
						useName.getText().toString(),
						password.getText().toString())
		);

		call.enqueue(new Callback<LoginResponse>() {
			@Override
			public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
				if (isAdded()) {
					if (response.isSuccessful()) {
						loading.setVisibility(View.INVISIBLE);
//						Prefs.putString(FinalString.TOKEN, loginResponse.getToken());
//						Prefs.putBoolean(FinalString.LOGIN_USER, true);

						MainAplication.token = response.body().getToken();

						FragmentManager manager = getFragmentManager();
						FragmentTransaction transaction = manager.beginTransaction();
						transaction.replace(R.id.fragment_holder, new MainFragment());
						transaction.commit();

					} else {
						login.setEnabled(true);
						useName.setEnabled(true);
						password.setEnabled(true);
						loading.setVisibility(View.INVISIBLE);

						switch (response.code()) {
							case 418:
								Toast.makeText(context, "باید پسورد عوض شود", Toast.LENGTH_SHORT).show();
								break;

							case 401:
								Toast.makeText(context, unauthorized, Toast.LENGTH_SHORT).show();
								break;

							default:
								Toast.makeText(context, serverError, Toast.LENGTH_SHORT).show();
								break;
						}
					}
				}
			}

			@Override
			public void onFailure(Call<LoginResponse> call, Throwable t) {
				if (isAdded()) {
					login.setEnabled(true);
					useName.setEnabled(true);
					password.setEnabled(true);
					loading.setVisibility(View.INVISIBLE);
					Toast.makeText(context, errorConection, Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.unbind(this);
	}
}