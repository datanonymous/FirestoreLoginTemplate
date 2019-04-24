package ko.alex.firestorelogintemplate;

//import com.google.firebase.firestore.IgnoreExtraProperties;
//https://stackoverflow.com/questions/37902635/no-setter-field-for-warning-firebase-database-retrieve-data-populate-listview/37904004#37904004
//https://stackoverflow.com/questions/41650103/no-setter-field-for-found-android-firebase
//@IgnoreExtraProperties
public class Yoga {

    //YOGA POJO
    //CHANGING PRIVATE TO PUBLIC VARIABLES
    //https://stackoverflow.com/questions/50161553/firestore-not-retrieving-certain-fields-from-document

    //THESE VARIABLES MUST MATCH FIREBASE VARIABLES!!!
    public String Name;
    public String Date;
    public String Description;
    public String Instructor;

    public Yoga(){
        //EMPTY DEFAULT CONSTRUCTOR SO THAT FIREBASE CAN DESERIALIZE USER
    }

    public Yoga(String Name, String Date, String Description, String Instructor) {
        this.Name = Name;
        this.Date = Date;
        this.Description = Description;
        this.Instructor = Instructor;
    }

    public String getYogaName() {
        return Name;
    }

    public void setYogaName(String yogaName) {
        this.Name = Name;
    }

    public String getYogaDate() {
        return Date;
    }

    public void setYogaDate(String yogaDate) {
        this.Date = Date;
    }

    public String getYogaDescription() {
        return Description;
    }

    public void setYogaDescription(String Description) {
        this.Description = Description;
    }

    public String getYogaInstructor() {
        return Instructor;
    }

    public void setYogaInstructor(String Instructor) {
        this.Instructor = Instructor;
    }



} //END YOGA OBJECT
