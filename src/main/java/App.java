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

    }
}
