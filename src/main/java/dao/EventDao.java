package dao;

import model.Event;

import java.util.List;

/**
 * Created by Guest on 8/18/17.
 */
public interface EventDao {
    void add(Event event);

    List<Event> getAllEvents();

    Event findById(int id);

    Integer findByName (String name);

    void update (String name, String description, int id);

    void deleteAllEvents();

    void deleteEventById (int id);
}
