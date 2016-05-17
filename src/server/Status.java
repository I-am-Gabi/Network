package server;

/**
 * Created by matthieuberger on 30/04/2016.
 */
public enum Status {

    CODE200 ("200 OK"),
    CODE404 ("404 METHOD NOT FOUND"),
    CODE500 ("500 SERVER ERROR");

    private String name = "";

    Status(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
