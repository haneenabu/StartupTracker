import model.Event;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.staticFileLocation;
import static spark.Spark.*;
/**
 * Created by Haneen on 8/11/17.
 */
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");


        get("/", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            ArrayList<Event> events = Event.getInstances();
            model.put("events", events);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        //show new event form
        get("/event/new", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());
        //add new event to index
        post("/event/new", (request, response) -> {
            Map<String, Object>model = new HashMap<>();
            String name = request.queryParams("eventName");
            String description = request.queryParams("eventDescription");
            String [] attendees = request.queryParamsValues("eventAttendees");
            Event newEvent = new Event(name, description, attendees);
            model.put("events", newEvent);
            response.redirect("/");
            return null;
        });

        //show an individual event's details
        get("/event/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Event foundEvent = Event.findById(Integer.parseInt(request.params("id")));
            model.put("events", foundEvent);
            return new ModelAndView(model, "event-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //to edit existing event
        get("/event/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Event foundEvent = Event.findById(Integer.parseInt(request.params("id")));
            model.put("editEvent", foundEvent);
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());
        //post edited item
        post("/item/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = request.queryParams("eventName");
            String newDescription = request.queryParams("eventDescription");
            String [] newAttendees = request.queryParamsValues("eventAttendees");
            Event editEvent = Event.findById(Integer.parseInt(request.params("id")));
            editEvent.update(newName, newDescription, newAttendees);
            model.put("event", editEvent);
            response.redirect("/");
            return null;
        });

    }
}
