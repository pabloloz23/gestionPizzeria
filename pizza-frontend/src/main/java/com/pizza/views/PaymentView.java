package com.pizza.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Route(value = "payment", layout = com.pizza.MainLayout.class)
public class PaymentView extends VerticalLayout {

    private final TextField orderIdField = new TextField("ID del pedido");
    private final NumberField amountField = new NumberField("Monto");
    private final Button payButton = new Button("Pagar");

    public PaymentView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        orderIdField.setWidth("300px");
        amountField.setWidth("300px");

        payButton.addClickListener(e -> processPayment());
        payButton.addClassNames("rounded-m", LumoUtility.Background.SUCCESS, LumoUtility.TextColor.PRIMARY_CONTRAST);

        add(orderIdField, amountField, payButton);
    }

    private void processPayment() {
        String orderId = orderIdField.getValue();
        double amount = amountField.getValue();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/payments/" + orderId + "?amount=" + amount))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Notification.show("Pago procesado con éxito", 3000, Notification.Position.TOP_CENTER);
            } else {
                Notification.show("Error al procesar el pago", 3000, Notification.Position.TOP_CENTER);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Notification.show("Error de conexión", 3000, Notification.Position.TOP_CENTER);
        }
    }
}
