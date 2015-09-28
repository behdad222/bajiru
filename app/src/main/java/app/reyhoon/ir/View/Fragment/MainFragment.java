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
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import app.reyhoon.ir.Interface.FinalString;
import app.reyhoon.ir.Interface.GetUserApi;
import app.reyhoon.ir.Object.Response.UserResponse;
import app.reyhoon.ir.R;
import app.reyhoon.ir.StaticField;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainFragment extends Fragment {
    Context context;

    @InjectView(R.id.users) Button users;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        context = getActivity();
        ButterKnife.inject(this, view);
        getUserInfo();

        return view;
    }

    private void getUserInfo() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(StaticField.domainURL_v2)
                .build();

        GetUserApi getUserApi = restAdapter.create(GetUserApi.class);
        getUserApi.getUser(
                Prefs.getString(FinalString.TOKEN, ""),
                new Callback<UserResponse>() {
                    @Override
                    public void success(UserResponse userResponse, Response response) {
                        Prefs.putString(FinalString.USER_NAME, userResponse.getName());
                        Toast.makeText(context, userResponse.getName(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        if (error.getKind() == RetrofitError.Kind.HTTP) {
                            Toast.makeText(context,"خطا" + error.getResponse().getStatus(), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, "خطا در برقراری ارتباط", Toast.LENGTH_SHORT).show();

                        }
                        //todo
                    }
                });
    }

    @OnClick(R.id.users)
    public void users() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_holder, new UsersFragment() );
        transaction.commit();
    }
}
