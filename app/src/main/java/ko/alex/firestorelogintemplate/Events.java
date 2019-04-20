package ko.alex.firestorelogintemplate;

//import com.google.firebase.firestore.IgnoreExtraProperties;

//@IgnoreExtraProperties
public class Events {

    //EVENTS POJO
    //CHANGING PRIVATE TO PUBLIC VARIABLES
    //https://stackoverflow.com/questions/50161553/firestore-not-retrieving-certain-fields-from-document

    //THESE VARIABLES MUST MATCH FIREBASE VARIABLES!!!
    public String Name;
    public String Description;
    public String Date;

    public Events(){
        //EMPTY DEFAULT CONSTRUCTOR SO THAT FIREBASE CAN DESERIALIZE USER
    }

    public Events(String Name, String Date, String Description) {
        this.Name = Name;
        this.Date = Date;
        this.Description = Description;
    }

    public String getEventsName() {
        return Name;
    }

    public void setEventsName(String Name) {
        this.Name = Name;
    }

    public String getEventsDate() {
        return Date;
    }

    public void setEventsDate(String Date) {
        this.Date = Date;
    }

    public String getEventsDescription() {
        return Description;
    }

    public void setEventsDescription(String Description) {
        this.Description = Description;
    }



} //END EVENTS OBJECT
