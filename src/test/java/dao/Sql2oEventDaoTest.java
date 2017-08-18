package dao;
import model.Event;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import static org.junit.Assert.*;


public class Sql2oEventDaoTest {
    private Sql2oEventDao eventDao;
    //private Sql2oAttendees
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString,"","");
        eventDao = new Sql2oEventDao(sql2o);
        //attendeeDao = new Sql2oEventDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void eventAddNewEventInstanceWithID_True() throws Exception{
        Event event =setNewEvent();
        eventDao.add(event);
        assertEquals(1, event.getId());
    }


    //Helper Method
    public Event setNewEvent(){
        return new Event("Event One", "Learn how to code!");
    }
}