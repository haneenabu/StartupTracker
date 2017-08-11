package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/11/17.
 */
public class EventTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void Event_Instantiate_True(){
        Event newEvent = setupNewEvent();
        assertTrue(newEvent instanceof Event);
    }

    @Test
    public void InstantiatesWithContent_True() throws Exception{
        Event newEvent = setupNewEvent();
        assertEquals("Code Fun", newEvent.getName());
    }
    @Test
    public void AllEventContainsSelectedInstances_True() throws Exception{
        Event newEvent = setupNewEvent();
        Event newEvent2= new Event("Get Coding", "description two");
        assertTrue(Event.getInstances().contains(newEvent));
        assertTrue(Event.getInstances().contains(newEvent2));

    }

    //helper methods
    public Event setupNewEvent(){
        return new Event ("Code Fun", "Coding Event Description");
    }

}