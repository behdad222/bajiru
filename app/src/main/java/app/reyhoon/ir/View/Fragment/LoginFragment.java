package app.reyhoon.ir.View.Fragment;

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
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import app.reyhoon.ir.Api.LoginApi;
import app.reyhoon.ir.Object.Gson.LoginGson;
import app.reyhoon.ir.Object.Response.LoginResponse;
import app.reyhoon.ir.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginFragment extends Fragment {
    Context context;

    @InjectView(R.id.login) Button login;
    @InjectView(R.id.userName) EditText useName;
    @InjectView(R.id.password) EditText password;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        context = getActivity();
        ButterKnife.inject(this, view);

        return view;
    }

    @OnClick(R.id.login)
    public void login() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(context.getString(R.string.domainURL_v2))
                .build();

        LoginApi loginApi = restAdapter.create(LoginApi.class);
        loginApi.login(
                new LoginGson(useName.getText().toString(), password.getText().toString()),
                new Callback<LoginResponse>() {
                    @Override
                    public void success(LoginResponse loginResponse, Response response) {
                        Prefs.putString("token", loginResponse.getToken());
//                        loginCallback.LoginCallback();
//                        dismiss();

                        FragmentManager manager = getFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.fragment_holder, new MainFragment() );
                        transaction.commit();
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(context, "خطا ", Toast.LENGTH_SHORT).show();

                        //todo

                    }
                });

    }


}
