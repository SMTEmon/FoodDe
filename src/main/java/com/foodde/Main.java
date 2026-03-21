package com.foodde;

import com.foodde.model.Restaurant;
import com.foodde.repository.StorageRepository;
import com.foodde.repository.XmlRepository;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        StorageRepository repository = new XmlRepository();

        // Initialize some sample data if the repository is empty
        if (repository.getAllRestaurants().isEmpty()) {
            repository.saveRestaurant(new Restaurant("1", "Burger King", "123 Main St", "9:00 AM - 10:00 PM"));
            repository.saveRestaurant(new Restaurant("2", "Pizza Hut", "456 Oak Ave", "10:00 AM - 11:00 PM"));
        }

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/static");
            config.fileRenderer(new JavalinThymeleaf());
        }).start(7070);

        // Home Page Route
        app.get("/", ctx -> {
            var restaurants = repository.getAllRestaurants();
            ctx.render("templates/index.html", Map.of("restaurants", restaurants));
        });

        System.out.println("Server started at http://localhost:7070");
    }
}
