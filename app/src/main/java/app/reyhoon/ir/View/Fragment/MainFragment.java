package app.reyhoon.ir.View.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import app.reyhoon.ir.Interface.FinalString;
import app.reyhoon.ir.Interface.GetUserApi;
import app.reyhoon.ir.Object.Response.User;
import app.reyhoon.ir.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainFragment extends Fragment {
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        context = getActivity();
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(getString(R.string.domainURL_v2))
                .build();

        GetUserApi getUserApi = restAdapter.create(GetUserApi.class);
        getUserApi.getUser(
                Prefs.getString(FinalString.TOKEN, ""),
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
        return view;
    }
}
