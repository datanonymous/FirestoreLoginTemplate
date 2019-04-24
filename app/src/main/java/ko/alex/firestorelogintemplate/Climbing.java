package ko.alex.firestorelogintemplate;

public class Climbing {

    //USE ALT+INSERT TO GENERATE GETTERS AND SETTERS
    //Climbing POJO
    //CHANGING PRIVATE TO PUBLIC VARIABLES
    //https://stackoverflow.com/questions/50161553/firestore-not-retrieving-certain-fields-from-document

    //THESE VARIABLES MUST MATCH FIREBASE VARIABLES!!!
    public String Name;
    public String Date;
    public String Description;
    public String Instructor;

    public Climbing(){
        //EMPTY DEFAULT CONSTRUCTOR SO THAT FIREBASE CAN DESERIALIZE USER
    }

    public Climbing(String Name, String Date, String Description, String Instructor) {
        this.Name = Name;
        this.Date = Date;
        this.Description = Description;
        this.Instructor = Instructor;
    }

    public String getClimbingName() {
        return Name;
    }
    public void setClimbingName(String Name) {
        this.Name = Name;
    }

    public String getClimbingDate() {
        return Date;
    }
    public void setClimbingDate(String Date) {
        this.Date = Date;
    }

    public String getClimbingDescription() {
        return Description;
    }
    public void setClimbingDescription(String Description) {
        this.Description = Description;
    }

    public String getClimbingInstructor() {
        return Instructor;
    }
    public void setClimbingInstructor(String Instructor) {
        this.Instructor = Instructor;
    }



} //END CLIMBING OBJECT
