package model;

import java.util.ArrayList;

/**
 * Created by Guest on 8/11/17.
 */
public class Event {
    private String name;
    private String description;
    private static ArrayList<Event> instances = new ArrayList<>();
    private int id;
    public static int increment =0;

    //constructor
    public Event (String name, String description){
        this.name = name;
        this.description = description;
        instances.add(this);
        this.id = increment +1;
    }
    public static void clearAllEvents(){
        instances.clear();
        increment =0;
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
    public int getId(){
        return id;
    }
}
