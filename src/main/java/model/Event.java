package model;

/**
 * Created by Guest on 8/11/17.
 */
public class Event {
    private String name;
    private String description;



    //constructor
    public Event (String name, String description){
        this.name = name;
        this.description = description;
    }

    //getters
    public String getName(){
        return name;
    }
    public String getDescription() {
        return description;
    }
}
