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
import android.widget.Toast;

import app.bajiru.ir.R;
import app.bajiru.ir.appInterface.UserApi;
import app.bajiru.ir.object.Response.UserResponse;
import app.bajiru.ir.service.ServiceGenerator;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    Context context;

    @Bind(R.id.users) Button users;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        context = getActivity();
        ButterKnife.bind(this, view);
        getUserInfo();

        return view;
    }

    private void getUserInfo() {
		UserApi userApi = ServiceGenerator
				.createServiceWithAccessToken(UserApi.class);

		Call<UserResponse> call = userApi.getUser();

		call.enqueue(new Callback<UserResponse>() {
			@Override
			public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
				if (isAdded()) {
					if (response.isSuccessful()) {

//						Prefs.putString(FinalString.USER_NAME, userResponse.getName());
//						Toast.makeText(context, userResponse.getName(), Toast.LENGTH_SHORT).show();

					} else {

//						Toast.makeText(context, "خطا" + error.getResponse().getStatus(), Toast.LENGTH_SHORT).show();

						//todo
					}
				}
			}

			@Override
			public void onFailure(Call<UserResponse> call, Throwable t) {
				if (isAdded()) {
					Toast.makeText(getActivity(), "خطا در برقراری ارتباط", Toast.LENGTH_SHORT).show();
				}
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
