import dao.Sql2oAttendeeDao;
import dao.Sql2oEventDao;
import model.Attendee;
import model.Event;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.staticFileLocation;
import static spark.Spark.*;
/**
 * Created by Haneen on 8/11/17.
 */
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/attendee.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oAttendeeDao attendeeDao = new Sql2oAttendeeDao(sql2o);
        Sql2oEventDao eventDao = new Sql2oEventDao(sql2o);

        //Simple About Page
        get("/event/about", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            List<Event> events = eventDao.getAllEvents();
            model.put("events", events);
            return new ModelAndView(model, "about.hbs");
        }, new HandlebarsTemplateEngine());

        //get: List all events and attendees
        get("/", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            List<Event> events = eventDao.getAllEvents();
            List<Attendee> attendees = attendeeDao.getAllAttendees();
            model.put("events", events);
            model.put("attendees", attendees);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new event form
        get("/event/new", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            List<Event> eventList = eventDao.getAllEvents();
            model.put("events", eventList);
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        //add new event to index
        post("/event/new", (request, response) -> {
            Map<String, Object>model = new HashMap<>();
            String name = request.queryParams("eventName");
            String description = request.queryParams("eventDescription");
            Event newEvent = new Event(name, description);
            eventDao.add(newEvent);
            List<Event> events = eventDao.getAllEvents();
            model.put("events", events);
            response.redirect("/");
            return null;
        });

        //get: display a new event
        get("/event/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            List<Attendee> attendeeByEvent = eventDao.getAllAttendeesByEvent(id);
            List<Event> events = eventDao.getAllEvents();
            model.put("events", events);
            model.put("attendees", attendeeByEvent);
            Event thisEvent = eventDao.findById(id);
            model.put("thisEvent", thisEvent);
            return new ModelAndView(model, "event-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //to edit existing event
        get("/event/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idEvent =Integer.parseInt(request.params("id"));
            Event foundEvent = eventDao.findById(idEvent);
            model.put("editEvent", foundEvent);
            List<Event> eventList = eventDao.getAllEvents();
            model.put("events", eventList);
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());
        //post edited item
        post("/event/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("eventName");
            String description = request.queryParams("eventDescription");
            int id = Integer.parseInt(request.params("id"));
            eventDao.update(name, description, id);
            List<Attendee>attendeeList = attendeeDao.getAllAttendees();
            model.put("attendees", attendeeList);
            List<Event>events =eventDao.getAllEvents();
            model.put("events", events);
            response.redirect("/");
            return null;
        });
        //get: show form to enter a new attendee
        get("/attendees/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Event> events = eventDao.getAllEvents();
            model.put("events", events);
            List<Attendee> attendees = attendeeDao.getAllAttendees();
            model.put("attendees", attendees);
            return new ModelAndView(model, "attendee-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new attendee
        post("/attendees/new", (Request request, Response response) -> {
            Map<String, Object> model = new HashMap<>();
            String eventName = request.queryParams("event");
            String attendeeName = request.queryParams("AttendeeName");
            Integer age= Integer.parseInt(request.queryParams("age"));
            Integer eventId = eventDao.findByName(eventName);
            Attendee attendee = new Attendee(attendeeName, age, eventId);
            attendeeDao.add(attendee);
            List<Attendee> attendeeList = attendeeDao.getAllAttendees();
            model.put("attendees", attendeeList);
            List<Event> events = eventDao.getAllEvents();
            model.put("events", events);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        //see attendee details
        get("/event/:eventId/attendees/:attendeeId", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            Attendee attendees = attendeeDao.findAttendeeById(Integer.parseInt(request.params("attendeeId")));
            model.put("attendees", attendees);
            List<Event> events = eventDao.getAllEvents();
            model.put("events", events);
            return new ModelAndView(model, "attendee-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get a form to update attendees
        get("/event/:eventId/attendees/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Attendee editAttendee = attendeeDao.findAttendeeById(Integer.parseInt(request.params("id")));
            List<Event> events = eventDao.getAllEvents();
            model.put("events", events);
            model.put("editAttendees", editAttendee);
            return new ModelAndView(model, "attendee-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: update attendees
        post("/event/:eventId/attendees/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String event = request.queryParams("event");
            String attendeeName = request.queryParams("attendeeName");
            int age = Integer.parseInt(request.queryParams("age"));
            int resId = eventDao.findByName(event);
            int id = Integer.parseInt(request.params("id"));
            attendeeDao.updateAttendee(attendeeName, age, id, resId );
            List<Attendee> attendeeList = attendeeDao.getAllAttendees();
            model.put("attendees", attendeeList);
            return new ModelAndView(model,"attendee-list.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an attendee
        get("/event/:eventId/attendees/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            attendeeDao.deleteAttendeeById(Integer.parseInt(request.params("id")));
            List<Attendee> attendeeList = attendeeDao.getAllAttendees();
            List<Event> events = eventDao.getAllEvents();
            model.put("attendees", attendeeList);
            model.put("events", events);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all attendees
        get("/attendees/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            attendeeDao.deleteAllAttendees();
            List<Event> events = eventDao.getAllEvents();
            model.put("events", events);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete a neighborhood
        get("/event/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            eventDao.deleteEventById(id);
            List<Event> events = eventDao.getAllEvents();
            model.put("events", events);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: see all attendees
        get("/attendees/all", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Attendee> attendeeList = attendeeDao.getAllAttendees();
            model.put("attendees",attendeeList);
            List<Event> events = eventDao.getAllEvents();
            model.put("events", events);
            return new ModelAndView(model, "attendee-list.hbs");
        }, new HandlebarsTemplateEngine());
        //get: see attendee details
        get("/attendees/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            List<Attendee> attendeeByEvent = eventDao.getAllAttendeesByEvent(id);
            List<Event> events = eventDao.getAllEvents();
            model.put("events", events);
            model.put("attendees", attendeeByEvent);
            Attendee thisAttendee = attendeeDao.findAttendeeById(id);
            model.put("thisEvent", thisAttendee);
            return new ModelAndView(model, "attendee-detail.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
