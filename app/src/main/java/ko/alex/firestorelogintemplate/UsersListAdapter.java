package ko.alex.firestorelogintemplate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder> {

    //Working with RecyclerView
    //https://www.androidhive.info/2016/01/android-working-with-recycler-view/

    private List<Users> usersList;

    //CONSTRUCTOR
    public UsersListAdapter(List<Users> usersList){
        this.usersList = usersList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //View view;
        public TextView nameText;
        public TextView birthdayText;
        //CREATE CONSTRUCTOR MATCHING SUPER
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.nameText);
            birthdayText = itemView.findViewById(R.id.birthdayText);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false); //EACH INDIVIDUAL ITEM LAYOUT
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Users users = usersList.get(i); //private List<Users> usersList;
        viewHolder.nameText.setText(users.getName());
        viewHolder.birthdayText.setText(users.getBirthday());
        //viewHolder.nameText.setText(usersList.get(i).getName());
        //viewHolder.birthdayText.setText(usersList.get(i).getBirthday());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }



} //END UsersListAdapter
