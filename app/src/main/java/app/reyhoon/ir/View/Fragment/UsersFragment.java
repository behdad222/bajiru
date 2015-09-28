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

import app.reyhoon.ir.Adapter.UsersAdapter;
import app.reyhoon.ir.Interface.FinalString;
import app.reyhoon.ir.Interface.GetUsersApi;
import app.reyhoon.ir.Object.Model.User;
import app.reyhoon.ir.Object.Response.UsersResponse;
import app.reyhoon.ir.R;
import app.reyhoon.ir.StaticField;
import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class UsersFragment extends Fragment {

    @InjectView(R.id.recycleView) RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private UsersAdapter adapter;

    private ArrayList<User> users;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);

        ButterKnife.inject(this, view);

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
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(StaticField.domainURL_v2)
                .build();

        GetUsersApi getUsersApi = restAdapter.create(GetUsersApi.class);
        getUsersApi.getUsers(
                Prefs.getString(FinalString.TOKEN, ""),
                new Callback<UsersResponse>() {
                    @Override
                    public void success(UsersResponse usersResponse, Response response) {
                        Collections.addAll(users, usersResponse.getUsers());
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
}
