import dao.Sql2oAttendeeDao;
import dao.Sql2oEventDao;
import model.Event;
import org.sql2o.Sql2o;
import spark.ModelAndView;
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

        get("/event/about", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            List<Event> events = eventDao.getAllEvents();
            model.put("events", events);
            return new ModelAndView(model, "about.hbs");
        }, new HandlebarsTemplateEngine());

//        get("/", (request, response) ->{
//            Map<String, Object> model = new HashMap<>();
//            ArrayList<Event> events = Event.getInstances();
//            model.put("events", events);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//        //show new event form
//        get("/event/new", (request, response) -> {
//            Map<String,Object> model = new HashMap<>();
//            return new ModelAndView(model, "form.hbs");
//        }, new HandlebarsTemplateEngine());
//        //add new event to index
//        post("/event/new", (request, response) -> {
//            Map<String, Object>model = new HashMap<>();
//            String name = request.queryParams("eventName");
//            String description = request.queryParams("eventDescription");
//            String [] attendees = request.queryParamsValues("eventAttendees");
//            Event newEvent = new Event(name, description, attendees);
//            model.put("events", newEvent);
//            response.redirect("/");
//            return null;
//        });
//
//        //show an individual event's details
//        get("/event/:id", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            Event foundEvent = Event.findById(Integer.parseInt(request.params("id")));
//            model.put("events", foundEvent);
//            return new ModelAndView(model, "event-detail.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //to edit existing event
//        get("/event/:id/edit", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            Event foundEvent = Event.findById(Integer.parseInt(request.params("id")));
//            model.put("editEvent", foundEvent);
//            return new ModelAndView(model, "form.hbs");
//        }, new HandlebarsTemplateEngine());
//        //post edited item
//        post("/event/:id/edit", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            String newName = request.queryParams("eventName");
//            String newDescription = request.queryParams("eventDescription");
//            String [] newAttendees = request.queryParamsValues("eventAttendees");
//            Event editEvent = Event.findById(Integer.parseInt(request.params("id")));
//            editEvent.update(newName, newDescription, newAttendees);
//            model.put("event", editEvent);
//            response.redirect("/");
//            return null;
//        });
//
//        //delete
//        get("/event/:id/delete", (request,response)-> {
//            int idOfEventToDelete = Integer.parseInt(request.params("id")); //pull id - must match route segment
//            Event.deleteById(idOfEventToDelete);
//            response.redirect("/");
//            return null;
//        });
    }
}
