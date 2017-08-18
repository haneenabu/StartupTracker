package dao;

import model.Attendee;

import java.util.List;

/**
 * Created by Guest on 8/18/17.
 */
public interface AttendeeDao {
    void add (Attendee attendee);

    List<Attendee> getAllAttendees();

    Attendee findAttendeeById (int id);

    void updateAttendee(String name, int age, int id, int attendeeId);

    void deleteAllAttendees();

    void deleteAttendeeById(int id);
}
