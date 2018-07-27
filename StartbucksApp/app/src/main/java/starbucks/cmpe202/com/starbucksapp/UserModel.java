package starbucks.cmpe202.com.starbucksapp;

public class UserModel {
    private String id;
    private String email;
    private String name;

    private String token;

    UserModel(){
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getEmail(){
        return this.email;
    }

    public String getName(){
        return this.name;
    }

    public String getToken(){
        return this.token;
    }
    public void setToken(String token){
        this.token = token;
    }

    public void setUser(UserModel obj){
        this.id = obj.getId();
        this.email = obj.getEmail();
        this.name = obj.getName();

    }
}
