package dao;
import model.Attendee;
import model.Event;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Guest on 8/18/17.
 */
public class Sql2oEventDao implements EventDao{
    private final Sql2o sql2o;
    public Sql2oEventDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public void add(Event event){
        String sql = "INSERT INTO event (name, description) VALUES (:name, :description)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(event)
                    .executeUpdate()
                    .getKey();
            event.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Event> getAllEvents() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM  event")
                    .executeAndFetch(Event.class);
        }
    }

    @Override
    public Event findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM event WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Event.class);
        }
    }

    @Override
    public Integer findByName(String name) {
        try(Connection con = sql2o.open()){
            return (Integer) con.createQuery("SELECT id FROM event WHERE name = :name")
                    .addParameter("name", name)
                    .executeAndFetchFirst(Integer.class);
        }
    }

    @Override
    public void update(String name, String description, int id) {

    }

    @Override
    public void deleteAllEvents() {

    }

    @Override
    public void deleteEventById(int id) {

    }
}
