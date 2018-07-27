package starbucks.cmpe202.com.starbucksapp;

public class UserManager {

    private static UserManager instance;
    private UserModel user;

    private UserManager(){
        user = new UserModel();
    }

    public static UserManager getInstance(){
        if (instance == null)
            instance = new UserManager();
        return instance;
    }

    public UserModel getUser(){
        return this.user;
    }

    public void clear(){
        instance = null;
    }




}
