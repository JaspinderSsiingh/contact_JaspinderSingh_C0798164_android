package com.demo.contact_jaspindersingh_C0798164_android.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.contact_jaspindersingh_C0798164_android.R;
import com.demo.contact_jaspindersingh_C0798164_android.model.ContactModel;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    Activity activity;
    List<ContactModel> dataList;
    private OnClickMenuListener onClickMenuListener;

    public ContactAdapter(Activity activity, List<ContactModel> dataList,OnClickMenuListener onClickMenuListener) {
        this.activity = activity;
        this.dataList = dataList;
        this.onClickMenuListener = onClickMenuListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.contact_item, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactModel contactModel=dataList.get(position);

        holder.tvcontactName.setText(contactModel.getFirstName());
        String name=contactModel.getFirstName();
        holder.tvLabel.setText(name.substring(0,1).toUpperCase());

        holder.rlContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickMenuListener.onItemClick(position);
            }
        });

        holder.rlContact.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onClickMenuListener.onItemLongClick(position);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvLabel, tvcontactName;
        RelativeLayout rlContact;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rlContact = itemView.findViewById(R.id.rl_contact);
            tvLabel = itemView.findViewById(R.id.tv_name_label);
            tvcontactName = itemView.findViewById(R.id.tv_contact_name);

        }
    }

    public interface OnClickMenuListener {
        void onItemClick(int position);
        void onItemLongClick(int position);
    }
}
