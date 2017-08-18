package dao;

import model.Attendee;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

/**
 * Created by Guest on 8/18/17.
 */
public class Sql2oAttendeeDao implements AttendeeDao{
    public final Sql2o sql2o;
    public Sql2oAttendeeDao(Sql2o sql2o){ this.sql2o = sql2o;}

    @Override
    public void add(Attendee attendee) {
        String sql = "INSERT INTO attendee(attendeeName, age, eventId) VALUES (:attendeeName, :age, :eventId)";
        try(Connection con =sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .bind(attendee)
                    .executeUpdate()
                    .getKey();
            attendee.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Attendee> getAllAttendees() {
        try(Connection con =sql2o.open()){
            return con.createQuery("SELECT * FROM attendee")
                    .executeAndFetch(Attendee.class);
        }
    }

    @Override
    public Attendee findAttendeeById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM attendee WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Attendee.class);
        }
    }

    @Override
    public void updateAttendee(String attendeeName, int age, int id, int attendeeId) {
        try(Connection con = sql2o.open()){
            con.createQuery("UPDATE attendee SET (attendeeName, age) = (:attendeeName, :age) WHERE id = :id")
                    .addParameter("attendeeName", attendeeName)
                    .addParameter("age", age)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAllAttendees() {
        try(Connection con = sql2o.open()){
            con.createQuery("DELETE FROM attendee")
                    .executeUpdate();
        }
    }

    @Override
    public void deleteAttendeeById(int id) {
        try(Connection con = sql2o.open()){
            con.createQuery("DELETE FROM attendee WHERE id =:id")
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }

    }
}
