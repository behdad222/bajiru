package app.reyhoon.ir.View.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import app.reyhoon.ir.Api.LoginApi;
import app.reyhoon.ir.Callback.LoginCallback;
import app.reyhoon.ir.Object.Gson.LoginGson;
import app.reyhoon.ir.Object.Response.LoginResponse;
import app.reyhoon.ir.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginDialog extends Dialog implements View.OnClickListener {
    Context context;
    Button login;
    EditText useName;
    EditText password;
    LoginCallback loginCallback;

    public LoginDialog(Context context, LoginCallback loginCallback) {
        super (context);
        this.context = context;
        this.loginCallback = loginCallback;
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        setContentView(R.layout.dialog_login);
        login = (Button) findViewById (R.id.login);
        useName = (EditText) findViewById (R.id.userName);
        password = (EditText) findViewById (R.id.password);

        login.setOnClickListener(this);

//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(getWindow().getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//        getWindow().setAttributes(lp);
//        validateAdapter = new ValidateAdapter();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint(context.getString(R.string.domainURL_v2))
                        .build();

                LoginApi loginApi = restAdapter.create(LoginApi.class);
                loginApi.login(
                        new LoginGson(useName.getText().toString(), password.getText().toString()),
                        new Callback<LoginResponse>() {
                            @Override
                            public void success(LoginResponse loginResponse, Response response) {
                                Prefs.putString("token", loginResponse.getToken());
                                loginCallback.LoginCallback();
                                dismiss();
                            }
                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(context, "خطا", Toast.LENGTH_SHORT).show();

                                //todo

                            }
                        });
                break;
        }
    }
}