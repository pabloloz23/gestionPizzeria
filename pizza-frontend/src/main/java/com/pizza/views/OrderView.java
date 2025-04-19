package com.pizza.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Route(value = "orders", layout = com.pizza.MainLayout.class)
public class OrderView extends VerticalLayout {

    private final TextField descriptionField = new TextField("Descripción del pedido");
    private final Button createButton = new Button("Crear Pedido");
    private final Grid<Order> orderGrid = new Grid<>(Order.class);

    public OrderView() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);
        setAlignItems(Alignment.CENTER);

        descriptionField.setWidth("300px");
        createButton.addClickListener(e -> createOrder());

        orderGrid.setColumns("id", "description");
        orderGrid.setWidth("600px");
        orderGrid.setHeight("300px");

        createButton.addClassNames("rounded-m", "shadow-s", LumoUtility.Background.PRIMARY, LumoUtility.TextColor.PRIMARY_CONTRAST);

        add(descriptionField, createButton, orderGrid);
    }

    private void createOrder() {
        String description = descriptionField.getValue();
        String json = "{\"description\":\"" + description + "\"}";

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/orders"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Notification.show("Pedido creado exitosamente", 3000, Notification.Position.TOP_CENTER);
                descriptionField.clear();
            } else {
                Notification.show("Error al crear pedido", 3000, Notification.Position.TOP_CENTER);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Notification.show("Error de conexión", 3000, Notification.Position.TOP_CENTER);
        }
    }

    public static class Order {
        public Long id;
        public String description;

        public Long getId() { return id; }
        public String getDescription() { return description; }
    }
}
