package app.bajiru.ir.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import app.bajiru.ir.object.Model.Customer;
import app.bajiru.ir.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class CustomersAdapter extends RecyclerView.Adapter <CustomersAdapter.ViewHolder> {
    private ArrayList<Customer> customers;
    private int expandedItem = -1;

    public CustomersAdapter(ArrayList<Customer> customers){
        this.customers = customers;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.number) TextView number;
        @Bind(R.id.name) TextView name;
        @Bind(R.id.phone) TextView phone;
        @Bind(R.id.address) TextView address;
        @Bind(R.id.phoneLayout) LinearLayout phoneLayout;
        @Bind(R.id.addressLayout) LinearLayout addressLayout;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            expandedItem = getAdapterPosition();
            notifyDataSetChanged();

        }
    }

    @Override
    public CustomersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_customer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(customers.get(position).getName());
        holder.number.setText(customers.get(position).getNumber());

        if (position == expandedItem) {
            holder.phone.setText(customers.get(position).getPhones()[0].getNumber());
            holder.address.setText(customers.get(position).getPeople()[0].getAddress()[0].getValue());
            holder.phoneLayout.setVisibility(View.VISIBLE);
            holder.addressLayout.setVisibility(View.VISIBLE);
        } else {
            holder.phoneLayout.setVisibility(View.GONE);
            holder.addressLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }
}