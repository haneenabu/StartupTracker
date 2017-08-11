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
        increment ++;
        this.id = increment;
    }
    public static void clearAllEvents(){
        instances.clear();
        increment =0;
    }
    public static Event findById(int id){
        Event objects = null;
        for (Event instance : instances){
            if (instance.getId() == id){
                objects = instance;
            }
        }
        return objects;
    }
    public void update(String name, String description){
        this.name =name;
        this.description = description;
    }
    public void deleteById(int id){
        instances.remove(Event.findById(id));
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
