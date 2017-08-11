package model;

import java.util.ArrayList;

/**
 * Created by Guest on 8/11/17.
 */
public class Event {
    private String name;
    private String description;
    private static ArrayList<Event> instances = new ArrayList<>();


    //constructor
    public Event (String name, String description){
        this.name = name;
        this.description = description;
        instances.add(this);
    }

    //getters
    public String getName(){
        return name;
    }
    public String getDescription() {
        return description;
    }
    public static ArrayList<Event> getInstances() {
        return instances;
    }
}
