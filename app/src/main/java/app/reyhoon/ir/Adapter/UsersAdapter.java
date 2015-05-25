package app.reyhoon.ir.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.reyhoon.ir.Object.Model.User;
import app.reyhoon.ir.R;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class UsersAdapter extends RecyclerView.Adapter <UsersAdapter.ViewHolder> {
    private ArrayList<User> users;
    public UsersAdapter(ArrayList<User> users){
        this.users = users;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.name) TextView name;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);

        }
    }

    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_users, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(users.get(position).getName());
    }



    @Override
    public int getItemCount() {
        return users.size();
    }
}