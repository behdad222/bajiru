package app.bajiru.ir.view.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import app.bajiru.ir.R;
import app.bajiru.ir.adapter.CustomersAdapter;
import app.bajiru.ir.appInterface.CustomerApi;
import app.bajiru.ir.object.Model.Customer;
import app.bajiru.ir.object.Response.CustomersResponse;
import app.bajiru.ir.service.ServiceGenerator;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerFragment extends Fragment {

	@Bind(R.id.recycleView)
	RecyclerView recyclerView;

	private RecyclerView.LayoutManager layoutManager;
	private CustomersAdapter adapter;

	private ArrayList<Customer> customers;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_users, container, false);

		ButterKnife.bind(this, view);

		recyclerView.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(layoutManager);

		customers = new ArrayList<>();
		adapter = new CustomersAdapter(customers);
		recyclerView.setAdapter(adapter);

		getUsersList();
		return view;
	}

	private void getUsersList() {
		CustomerApi customerApi = ServiceGenerator
				.createServiceWithAccessToken(CustomerApi.class);

		Call<CustomersResponse> call = customerApi.getCustomer(50, 1);

		call.enqueue(new Callback<CustomersResponse>() {
			@Override
			public void onResponse(Call<CustomersResponse> call, Response<CustomersResponse> response) {
				if (isAdded()) {
					if (response.isSuccessful()) {

						Collections.addAll(customers, response.body().getCustomer());
						adapter.notifyDataSetChanged();

					} else {

						if (response.code() == 403) {
							Toast.makeText(getActivity(), "عدم وجود سطح دسترسی", Toast.LENGTH_SHORT).show();

						} else {
							Toast.makeText(getActivity(), "خطا" + response.code(), Toast.LENGTH_SHORT).show();

						}

						//todo
					}
				}
			}

			@Override
			public void onFailure(Call<CustomersResponse> call, Throwable t) {
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
