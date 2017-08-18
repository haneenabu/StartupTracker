package dao;

import model.Attendee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/18/17.
 */
public class Sql2oAttendeeDaoTest {
    private Sql2oAttendeeDao attendeeDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        attendeeDao = new Sql2oAttendeeDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
    @Test
    public void attendeeSetsId_True(){
        Attendee attendee = setUpAttendee();
        attendeeDao.add(attendee);
        int id = attendee.getId();
        assertEquals(id, attendee.getId());
    }
    @Test
    public void attendeesGetAllInstances_True()throws Exception{
        Attendee attendee = setUpAttendee();
        Attendee attendee1 = setUpAttendee2();
        attendeeDao.add(attendee);
        attendeeDao.add(attendee1);
        assertEquals(2, attendeeDao.getAllAttendees().size());
    }

    //Helper Methods
    public Attendee setUpAttendee(){
        return new Attendee("John Smith", 32, 1);
    }
    public Attendee setUpAttendee2(){
        return new Attendee("Haneen Abu", 26, 2);
    }
}