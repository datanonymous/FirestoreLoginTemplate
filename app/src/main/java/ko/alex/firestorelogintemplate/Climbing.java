package ko.alex.firestorelogintemplate;

public class Climbing {

    //USE ALT+INSERT TO GENERATE GETTERS AND SETTERS
    private String climbingName, climbingDate, climbingDescription, climbingInstructor;

    public Climbing(){
        //EMPTY DEFAULT CONSTRUCTOR SO THAT FIREBASE CAN DESERIALIZE USER
    }

    public Climbing(String climbingName, String climbingDate, String climbingDescription, String climbingInstructor) {
        this.climbingName = climbingName;
        this.climbingDate = climbingDate;
        this.climbingDescription = climbingDescription;
        this.climbingInstructor = climbingInstructor;
    }

    public String getClimbingName() {
        return climbingName;
    }

    public void setClimbingName(String climbingName) {
        this.climbingName = climbingName;
    }

    public String getClimbingDate() {
        return climbingDate;
    }

    public void setClimbingDate(String climbingDate) {
        this.climbingDate = climbingDate;
    }

    public String getClimbingDescription() {
        return climbingDescription;
    }

    public void setClimbingDescription(String climbingDescription) {
        this.climbingDescription = climbingDescription;
    }

    public String getClimbingInstructor() {
        return climbingInstructor;
    }

    public void setClimbingInstructor(String climbingInstructor) {
        this.climbingInstructor = climbingInstructor;
    }



} //END CLIMBING OBJECT
