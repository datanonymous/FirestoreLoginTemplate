package ko.alex.firestorelogintemplate;

public class Yoga {

    private String yogaName;
    private String yogaDate;
    private String yogaDescription;
    private String yogaInstructor;

    public Yoga(){
        //EMPTY DEFAULT CONSTRUCTOR SO THAT FIREBASE CAN DESERIALIZE USER
    }

    public Yoga(String yogaName, String yogaDate, String yogaDescription, String yogaInstructor) {
        this.yogaName = yogaName;
        this.yogaDate = yogaDate;
        this.yogaDescription = yogaDescription;
        this.yogaInstructor = yogaInstructor;
    }

    public String getYogaName() {
        return yogaName;
    }

    public void setYogaName(String yogaName) {
        this.yogaName = yogaName;
    }

    public String getYogaDate() {
        return yogaDate;
    }

    public void setYogaDate(String yogaDate) {
        this.yogaDate = yogaDate;
    }

    public String getYogaDescription() {
        return yogaDescription;
    }

    public void setYogaDescription(String yogaDescription) {
        this.yogaDescription = yogaDescription;
    }

    public String getYogaInstructor() {
        return yogaInstructor;
    }

    public void setYogaInstructor(String yogaInstructor) {
        this.yogaInstructor = yogaInstructor;
    }



} //END YOGA OBJECT
