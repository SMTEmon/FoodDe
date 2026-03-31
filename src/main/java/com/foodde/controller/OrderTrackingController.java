package com.foodde.controller;

import com.foodde.repository.StorageRepository;
import io.javalin.http.Context;
import java.util.Map;

public class OrderTrackingController {
    private final StorageRepository repository;

    public OrderTrackingController(StorageRepository repository) {
        this.repository = repository;
    }

    // View all user's orders
    public void viewOrders(Context ctx) {
        com.foodde.model.User user = ctx.sessionAttribute("user");
        
        if (user == null) {
            ctx.redirect("/login");
            return;
        }

        var orders = repository.getOrdersByUserId(user.getId());
        ctx.render("templates/orders.html", Map.of(
                "orders", orders,
                "user", user
        ));
    }
}
