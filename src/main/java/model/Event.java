package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guest on 8/11/17.
 */
public class Event {
    private String name;
    private String description;
    private String[] attendees;

//    private List<String> attendeesList = new ArrayList<>();
    private static ArrayList<Event> instances = new ArrayList<>();
    private int id;
    public static int increment =0;

    //Constructor
    public Event (String name, String description, String [] attendees){
        this.name = name;
        this.description = description;
        this.attendees  = attendees;
        //attendeesList = Arrays.asList(attendees.split(","));
        instances.add(this);
        increment ++;
        this.id = increment;
    }
    public static void clearAllEvents(){
        instances.clear();
        increment =0;
    }
    public static Event findById(int id){

        for (Event instance : instances){
            if (instance.id == id){
                return instance;
            }
        }
        return null;
    }
    public void update(String name, String description, String [] attendees){
        this.name =name;
        this.description = description;
        this.attendees = attendees;
    }
    public void deleteById(int id){
        instances.remove(Event.findById(id));
    }
    public String findAttendeeNames(String person){
        for (String attendee : attendees){
            if (attendee == person){
                return person;
            }
        }
        return null;
    }

    //Getters
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
    public String[] getAttendees() {
        return attendees;
    }
}
