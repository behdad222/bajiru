package app.bajiru.ir.view.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.Collections;

import app.bajiru.ir.MainAplication;
import app.bajiru.ir.adapter.UsersAdapter;
import app.bajiru.ir.appInterface.FinalString;
import app.bajiru.ir.appInterface.GetUsersApi;
import app.bajiru.ir.appInterface.UserApi;
import app.bajiru.ir.object.Gson.LoginGson;
import app.bajiru.ir.object.Model.User;
import app.bajiru.ir.object.Response.LoginResponse;
import app.bajiru.ir.object.Response.UsersResponse;
import app.bajiru.ir.R;
import app.bajiru.ir.service.ServiceGenerator;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFragment extends Fragment {

	@Bind(R.id.recycleView)
	RecyclerView recyclerView;

	private RecyclerView.LayoutManager layoutManager;
	private UsersAdapter adapter;

	private ArrayList<User> users;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_users, container, false);

		ButterKnife.bind(this, view);

		recyclerView.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);

		users = new ArrayList<>();
		adapter = new UsersAdapter(users);
		recyclerView.setAdapter(adapter);

		getUsersList();
		return view;
	}

	private void getUsersList() {
		UserApi userApi = ServiceGenerator
				.createServiceBasicAuthentication(UserApi.class);

		Call<UsersResponse> call = userApi.getAllUser();
		call.enqueue(new Callback<UsersResponse>() {
			@Override
			public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
				if (isAdded()) {
					if (response.isSuccessful()) {
						Collections.addAll(users, response.body().getUsers());
						adapter.notifyDataSetChanged();
					} else {

						if (response.code() == 403) {
							Toast.makeText(getActivity(), "عدم وجود سطح دسترسی", Toast.LENGTH_SHORT).show();

						} else {
							Toast.makeText(getActivity(), "خطا" + response.code(), Toast.LENGTH_SHORT).show();

						}
					}
				}
			}

			@Override
			public void onFailure(Call<UsersResponse> call, Throwable t) {
				if (isAdded()) {
					Toast.makeText(getActivity(), "خطا در برقراری ارتباط", Toast.LENGTH_SHORT).show();
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
