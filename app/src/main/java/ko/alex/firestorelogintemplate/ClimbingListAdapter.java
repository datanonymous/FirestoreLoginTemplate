package ko.alex.firestorelogintemplate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

//REMEMBER THIS PART!
//RecyclerView.Adapter<????????.ViewHolder>
public class ClimbingListAdapter extends RecyclerView.Adapter<ClimbingListAdapter.ViewHolder> {
    //Working with RecyclerView
    //https://www.androidhive.info/2016/01/android-working-with-recycler-view/

    private List<Climbing> climbingList;

    //CONSTRUCTOR
    public ClimbingListAdapter(List<Climbing> climbingList){
        this.climbingList = climbingList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        //View view;
        public TextView climbingName;
        public TextView climbingDate;
        public TextView climbingDescription;
        public TextView climbingInstructor;

        //CREATE CONSTRUCTOR MATCHING SUPER
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            climbingName = itemView.findViewById(R.id.climbingName);
            climbingDate = itemView.findViewById(R.id.climbingDate);
            climbingDescription = itemView.findViewById(R.id.climbingDescription);
            climbingInstructor = itemView.findViewById(R.id.climbingInstructor);
        }
    }

    @NonNull
    @Override
    public ClimbingListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.climbing_item, viewGroup, false); //EACH INDIVIDUAL ITEM LAYOUT
        return new ClimbingListAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Climbing climbing = climbingList.get(i); //private List<Users> usersList;
        viewHolder.climbingName.setText(climbing.getClimbingName());
        viewHolder.climbingDate.setText(climbing.getClimbingDate());
        viewHolder.climbingDescription.setText(climbing.getClimbingDescription());
        viewHolder.climbingInstructor.setText(climbing.getClimbingInstructor());
    }
    @Override
    public int getItemCount() {
        return climbingList.size();
    }
} //END CLIMBINGLISTADAPTER
