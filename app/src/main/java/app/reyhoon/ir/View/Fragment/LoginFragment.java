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

import app.reyhoon.ir.Interface.FinalString;
import app.reyhoon.ir.Interface.LoginApi;
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
                .setEndpoint(FinalString.domainURL_v2)
                .build();

        LoginApi loginApi = restAdapter.create(LoginApi.class);
        loginApi.login(
                new LoginGson(useName.getText().toString(), password.getText().toString()),
                new Callback<LoginResponse>() {
                    @Override
                    public void success(LoginResponse loginResponse, Response response) {
                        Prefs.putString(FinalString.TOKEN, loginResponse.getToken());
                        Prefs.putBoolean(FinalString.LOGIN_USER, true);

                        FragmentManager manager = getFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.fragment_holder, new MainFragment() );
                        transaction.commit();
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        if (error.getKind() == RetrofitError.Kind.HTTP) {
                            switch (error.getResponse().getStatus()) {
                                case 418:
                                    Toast.makeText(context, "باید پسورد عوض شود", Toast.LENGTH_SHORT).show();
                                    break;

                                case 401:
                                    Toast.makeText(context, "خطا ۴۰۱", Toast.LENGTH_SHORT).show();
                                    break;

                                default:
                                    Toast.makeText(context, "خطا", Toast.LENGTH_SHORT).show();
                                    break;
                            }

                        } else {
                            Toast.makeText(context, "خطا در برقراری ارتباط", Toast.LENGTH_SHORT).show();

                        }

                        //todo

                    }
                });

    }


}
