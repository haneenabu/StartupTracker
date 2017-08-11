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
        Event.clearAllEvents();
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
        Event newEvent2= new Event("Get Coding", "description two", new String[]{"Person1", "Person2"});
        assertTrue(Event.getInstances().contains(newEvent));
        assertTrue(Event.getInstances().contains(newEvent2));

    }
    @Test
    public void ClearsAllEvent_False() throws Exception{
        Event newEvent = setupNewEvent();
        Event newEvent2 = new Event("Coding in Germany","Summer 2018", new String[]{"Person1", "Person2"});
        Event.clearAllEvents();
        assertFalse(Event.getInstances().contains(newEvent));
        assertFalse(Event.getInstances().contains(newEvent2));
    }
    @Test
    public void EventInstantiatesWithId() throws Exception {
        Event newEvent = setupNewEvent();
        assertEquals(1, newEvent.getId());
    }
    @Test
    public void EventInstantiateWithMultipleEntries() throws Exception {
        Event newEvent = setupNewEvent();
        Event newEvent2 = new Event("PDXWIT Presentation", "Monday, August 14, 2017 from 5–7:30pm", new String[]{"Person1", "Person2"});
        assertEquals(1, newEvent.getId());
        assertEquals(2, newEvent2.getId());
    }
    @Test
    public void findBySpecificId() throws Exception{
        Event newEvent = setupNewEvent();
        Event newEvent2 = new Event("Epicodus Coding School", "Job hunting after graduation", new String[]{"Person1", "Person2"});
        assertEquals(2, Event.findById(newEvent2.getId()).getId());
        assertEquals("Epicodus Coding School", Event.findById(newEvent2.getId()).getName());
    }
    @Test
    public void deleteBySpecificId() throws Exception{
        Event newEvent = setupNewEvent();
        Event newEvent2 = new Event("Epicodus", "December 2016", new String[]{"Person1", "Person2"});
        newEvent.deleteById(1);
        assertEquals(1, Event.getInstances().size());
        assertEquals("Epicodus", Event.findById(newEvent2.getId()).getName());
    }
    @Test
    public void updateEvent_True() throws Exception{
        Event newEvent = setupNewEvent();
        newEvent.update("test", "2", new String[]{"Person1", "Person2"});
        assertEquals("test", newEvent.getName());
    }
    @Test
    public void ArrayAttendeesContainesContent()throws Exception{
        Event newEvent = setupNewEvent();
        assertEquals(new String[]{"Tom", "Mike"}, newEvent.getAttendees());
    }
    @Test
    public void ArrayAttendeesContainesSpecificPerson()throws Exception{
        Event newEvent = setupNewEvent();
        assertEquals("Mike", newEvent.findAttendeeNames("Mike"));
    }


    //helper methods
    public Event setupNewEvent(){
        return new Event ("Code Fun", "Coding Event Description", new String[]{"Tom", "Mike"});
    }

}