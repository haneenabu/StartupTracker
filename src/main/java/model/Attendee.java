package model;

/**
 * Created by Guest on 8/18/17.
 */
public class Attendee {
    private String attendeeName;
    private int age;
    private int id;
    private int eventId;

    public Attendee (String attendeeName, int age, int eventId){
        this.attendeeName = attendeeName;
        this.age = age;
        this.eventId = eventId;
    }

    //Getters
    public String getAttendeeName() {
        return attendeeName;
    }
    public int getAge() {
        return age;
    }
    public int getId() {
        return id;
    }
    public int getEventId() {
        return eventId;
    }

    //Setters
    public void setAttendeeName(String attendeeName) {
        this.attendeeName = attendeeName;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    //Equals and HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attendee attendee = (Attendee) o;

        if (age != attendee.age) return false;
        return attendeeName.equals(attendee.attendeeName);
    }

    @Override
    public int hashCode() {
        int result = attendeeName.hashCode();
        result = 31 * result + age;
        return result;
    }
}
