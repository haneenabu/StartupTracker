package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
    public void InstantiatesWithContentDescription_True() throws Exception{
        Event newEvent = setupNewEvent();
        assertEquals("Coding Event Description", newEvent.getDescription());
    }
    @Test
    public void AllEventContainsSelectedInstances_True() throws Exception{
        Event newEvent = setupNewEvent();
        Event newEvent2= new Event("Get Coding", "description two");
        assertEquals("Coding Event Description", newEvent.getDescription());

    }

    //helper methods
    public Event setupNewEvent(){
        return new Event ("Code Fun", "Coding Event Description");
    }

}