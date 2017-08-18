package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guest on 8/11/17.
 */
public class Event {
    private String name;
    private String description;
    private int id;

    //Constructor
    public Event (String name, String description){
        this.name = name;
        this.description = description;

    }

    //Getters
    public String getName(){
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getId(){
        return id;
    }


    //Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setId(int id) {
        this.id = id;
    }

    //Equals and HashCode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (!name.equals(event.name)) return false;
        return description.equals(event.description);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
