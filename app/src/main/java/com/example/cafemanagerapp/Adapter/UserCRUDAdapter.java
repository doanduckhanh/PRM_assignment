package com.example.cafemanagerapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafemanagerapp.Entity.User;
import com.example.cafemanagerapp.R;

import java.util.List;

public class UserCRUDAdapter extends RecyclerView.Adapter<UserCRUDAdapter.UserCRUDViewHolder>{
    private IClickUpdate iClickUpdate;
    public interface IClickUpdate{
        void updateUser(User user);
    }
    private List<User> mListUser;

    public UserCRUDAdapter(IClickUpdate iClickUpdate) {
        this.iClickUpdate = iClickUpdate;
    }

    @NonNull
    @Override
    public UserCRUDAdapter.UserCRUDViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserCRUDViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserCRUDAdapter.UserCRUDViewHolder holder, int position) {
        User user = mListUser.get(position);
        if(user==null){
            return;
        }
        holder.tvId.setText(String.valueOf(user.getUser_id()));
        holder.tvFullName.setText(user.getFull_name());
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickUpdate.updateUser(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListUser != null){
            return mListUser.size();
        }
        return 0;
    }
    public void setData(List<User> list){
        this.mListUser = list;
        notifyDataSetChanged();
    }
    public class UserCRUDViewHolder extends RecyclerView.ViewHolder{
        private TextView tvId;
        private TextView tvFullName;
        private Button btnDetail;
        private Button btnUpdate;
        private Button btnDelete;
        public UserCRUDViewHolder(@NonNull View itemView){
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvFullName = itemView.findViewById(R.id.tv_fullName);
            btnDetail = itemView.findViewById(R.id.btn_detail);
            btnUpdate = itemView.findViewById(R.id.btn_update);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }

    }
}
