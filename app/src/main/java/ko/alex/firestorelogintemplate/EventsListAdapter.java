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
public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.ViewHolder>{
    //Working with RecyclerView
    //https://www.androidhive.info/2016/01/android-working-with-recycler-view/

    private List<Events> eventsList;

    //CONSTRUCTOR
    public EventsListAdapter(List<Events> eventsList){
        this.eventsList = eventsList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        //View view;
        public TextView eventsName;
        public TextView eventsDate;
        public TextView eventsDescription;

        //CREATE CONSTRUCTOR MATCHING SUPER
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventsName = itemView.findViewById(R.id.eventsName);
            eventsDate = itemView.findViewById(R.id.eventsDate);
            eventsDescription = itemView.findViewById(R.id.eventsDescription);
        }
    }

    @NonNull
    @Override
    public EventsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.events_item, viewGroup, false); //EACH INDIVIDUAL ITEM LAYOUT
        return new EventsListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsListAdapter.ViewHolder viewHolder, int i) {
        Events events = eventsList.get(i); //private List<Users> usersList;
        viewHolder.eventsName.setText(events.getEventsName());
        viewHolder.eventsDate.setText(events.getEventsDate());
        viewHolder.eventsDescription.setText(events.getEventsDescription());
    }



    @Override
    public int getItemCount() {
        return eventsList.size();
    }


}
