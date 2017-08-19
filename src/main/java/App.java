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


    }
}
