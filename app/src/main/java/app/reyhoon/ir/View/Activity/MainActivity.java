package app.reyhoon.ir.View.Activity;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import app.reyhoon.ir.Api.GetUser;
import app.reyhoon.ir.Callback.LoginCallback;
import app.reyhoon.ir.Object.Response.User;
import app.reyhoon.ir.R;
import app.reyhoon.ir.View.Dialog.LoginDialog;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button login;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the Prefs class
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

        context = this;

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:

                new LoginDialog(this, new LoginCallback() {
                    @Override
                    public void LoginCallback() {

                        RestAdapter restAdapter = new RestAdapter.Builder()
                                .setEndpoint(getString(R.string.domainURL_v2))
                                .build();

                        GetUser getUser = restAdapter.create(GetUser.class);
                        getUser.getUser(
                                Prefs.getString("token", ""),
                                new Callback<User>() {
                                    @Override
                                    public void success(User user, Response response) {
                                        Toast.makeText(context, user.getName(), Toast.LENGTH_SHORT).show();

                                    }
                                    @Override
                                    public void failure(RetrofitError error) {
                                        Toast.makeText(context,"خطا " + error.getResponse().getStatus(), Toast.LENGTH_SHORT).show();

                                        //todo

                                    }
                                });
                    }
                }).show();
                break;
        }
    }
}
