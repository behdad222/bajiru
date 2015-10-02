package app.reyhoon.ir.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.reyhoon.ir.Interface.ClickListener;
import app.reyhoon.ir.R;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
    private Context context;
    private ClickListener clickListener;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    public int selectedItem = 1;
    String[] drawerTitles;

    public DrawerAdapter(Context context, ClickListener clickListener) {
        this.clickListener = clickListener;
        this.context = context;
        drawerTitles = context.getResources().getStringArray(R.array.drawer_title);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemTitle;
//        TextView itemIcon;
        View backGrand;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            if (ViewType == TYPE_ITEM) {
                itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
//                itemIcon = (ImageView) itemView.findViewById(R.id.ItemIcon);
                backGrand = itemView.findViewById(R.id.back_grand);
            }
        }

        @Override
        public void onClick(View view) {
            clickListener.onClickItem(view, getAdapterPosition());
            selectedItem = getAdapterPosition();
            notifyDataSetChanged();
        }
    }

    @Override
    public DrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.drawer_item, parent, false);

            return new ViewHolder(v, viewType);

        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.drawer_header, parent, false);

            return new ViewHolder(v, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(DrawerAdapter.ViewHolder holder, int position) {
        if (!isPositionHeader(position)) {
            holder.itemTitle.setText(drawerTitles[position - 1]);
//            holder.itemIcon.setText(FinalString.drawerIcon[position - 1]);
            if (selectedItem == position) {
                holder.backGrand.setVisibility(View.VISIBLE);
            } else {
                holder.backGrand.setVisibility(View.GONE);
            }
        }
        holder.itemView.setSelected(false);
    }

    @Override
    public int getItemCount() {
        return drawerTitles.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}