package app.reyhoon.ir.View.Fragment;

import android.app.Fragment;
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

import app.reyhoon.ir.Adapter.CustomersAdapter;
import app.reyhoon.ir.Adapter.UsersAdapter;
import app.reyhoon.ir.Interface.FinalString;
import app.reyhoon.ir.Interface.GetCustomersApi;
import app.reyhoon.ir.Interface.GetUsersApi;
import app.reyhoon.ir.Object.Model.Customer;
import app.reyhoon.ir.Object.Model.User;
import app.reyhoon.ir.Object.Response.CustomersResponse;
import app.reyhoon.ir.Object.Response.UsersResponse;
import app.reyhoon.ir.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CustomerFragment extends Fragment {

    @Bind(R.id.recycleView) RecyclerView recyclerView;

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
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(FinalString.domainURL_v2)
                .build();

        GetCustomersApi getCustomersApi = restAdapter.create(GetCustomersApi.class);
        getCustomersApi.getCustomers(
                Prefs.getString(FinalString.TOKEN, ""),
                50, //todo
                1, //todo
                new Callback<CustomersResponse>() {
                    @Override
                    public void success(CustomersResponse customersResponse, Response response) {
                        Collections.addAll(customers, customersResponse.getCustomer());
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        if (error.getKind() == RetrofitError.Kind.HTTP) {
                            if (error.getResponse().getStatus() == 403) {
                                Toast.makeText(getActivity(), "عدم وجود سطح دسترسی", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getActivity(), "خطا" + error.getResponse().getStatus(), Toast.LENGTH_SHORT).show();

                            }

                        } else {
                            Toast.makeText(getActivity(), "خطا در برقراری ارتباط", Toast.LENGTH_SHORT).show();

                        }
                        //todo
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
