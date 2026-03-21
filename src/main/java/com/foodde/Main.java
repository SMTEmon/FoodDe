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
            Restaurant res1 = new Restaurant("1", "Burger King", "123 Main St", "9:00 AM - 10:00 PM");
            res1.getMenu().add(new com.foodde.model.MenuItem("m1", "Whopper", 5.99, "Flame-grilled beef patty"));
            res1.getMenu().add(new com.foodde.model.MenuItem("m2", "Chicken Fries", 4.49, "9pc Crispy chicken fries"));
            repository.saveRestaurant(res1);

            Restaurant res2 = new Restaurant("2", "Pizza Hut", "456 Oak Ave", "10:00 AM - 11:00 PM");
            res2.getMenu().add(new com.foodde.model.MenuItem("m3", "Pepperoni Pizza", 12.99, "Classic pepperoni pizza"));
            res2.getMenu().add(new com.foodde.model.MenuItem("m4", "Cheesy Garlic Bread", 5.50, "Freshly baked garlic bread"));
            repository.saveRestaurant(res2);
        }

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/static");
            config.fileRenderer(new JavalinThymeleaf());
        }).start(7070);

        // Controllers
        var restaurantController = new com.foodde.controller.RestaurantController(repository);

        // Home Page Route
        app.get("/", restaurantController::listAll);
        
        // Restaurant Detail Route
        app.get("/restaurant/{id}", restaurantController::detail);

        System.out.println("Server started at http://localhost:7070");
    }
}
