package ko.alex.firestorelogintemplate;

public class Users {

    private String name;
    private String birthday;

    public Users(){
        //EMPTY DEFAULT CONSTRUCTOR SO THAT FIREBASE CAN DESERIALIZE USER
        //Working with Firebase and Recyclerview
        //https://www.youtube.com/watch?v=kyGVgrLG3KU&list=LLj1xIyoM3IcZs9XdwFDaJWA&index=3&t=0s
    }

    //USE ALT+INSERT TO GENERATE CONSTRUCTORS
    public Users(String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    //USE ALT+INSERT TO GENERATE GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }



} //END USERS
