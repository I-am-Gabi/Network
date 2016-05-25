package server;

public enum Status {
    CODE200 ("200 OK"),
    CODE404 ("404 METHOD NOT FOUND"),
    CODE500 ("500 SERVER ERROR"),
    CODE300 ("300 DATABASE ERROR");

    private String name = "";

    Status(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
