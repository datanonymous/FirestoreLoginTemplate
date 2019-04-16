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
public class YogaListAdapter extends RecyclerView.Adapter<YogaListAdapter.ViewHolder>{
    //Working with RecyclerView
    //https://www.androidhive.info/2016/01/android-working-with-recycler-view/

    private List<Yoga> yogaList;

    //CONSTRUCTOR
    public YogaListAdapter(List<Yoga> yogaList){
        this.yogaList = yogaList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        //View view;
        public TextView yogaName;
        public TextView yogaDate;
        public TextView yogaDescription;
        public TextView yogaInstructor;

        //CREATE CONSTRUCTOR MATCHING SUPER
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            yogaName = itemView.findViewById(R.id.yogaName);
            yogaDate = itemView.findViewById(R.id.yogaDate);
            yogaDescription = itemView.findViewById(R.id.yogaDescription);
            yogaInstructor = itemView.findViewById(R.id.yogaInstructor);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.yoga_item, viewGroup, false); //EACH INDIVIDUAL ITEM LAYOUT
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YogaListAdapter.ViewHolder viewHolder, int i) {
        Yoga yoga = yogaList.get(i); //private List<Users> usersList;
        viewHolder.yogaName.setText(yoga.getYogaName());
        viewHolder.yogaDate.setText(yoga.getYogaDate());
        viewHolder.yogaDescription.setText(yoga.getYogaDescription());
        viewHolder.yogaInstructor.setText(yoga.getYogaInstructor());
    }

    @Override
    public int getItemCount() {
        return yogaList.size();
    }


}
