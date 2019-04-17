package ko.alex.firestorelogintemplate;

//import com.google.firebase.firestore.IgnoreExtraProperties;

//@IgnoreExtraProperties
public class Events {

    private String eventsName;
    private String eventsDescription;
    private String eventsDate;

    public Events(){
        //EMPTY DEFAULT CONSTRUCTOR SO THAT FIREBASE CAN DESERIALIZE USER
    }

    public Events(String eventsName, String eventsDate, String eventsDescription) {
        this.eventsName = eventsName;
        this.eventsDate = eventsDate;
        this.eventsDescription = eventsDescription;
    }

    public String getEventsName() {
        return eventsName;
    }

    public void setEventsName(String eventsName) {
        this.eventsName = eventsName;
    }

    public String getEventsDate() {
        return eventsDate;
    }

    public void setEventsDate(String eventsDate) {
        this.eventsDate = eventsDate;
    }

    public String getEventsDescription() {
        return eventsDescription;
    }

    public void setEventsDescription(String eventsDescription) {
        this.eventsDescription = eventsDescription;
    }



} //END EVENTS OBJECT
