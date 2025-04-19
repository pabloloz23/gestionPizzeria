package com.pizza.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Route(value = "register", layout = com.pizza.MainLayout.class)
@PageTitle("Registro - Pizza Online")
public class RegisterView extends VerticalLayout {

    public RegisterView() {
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        Div registerContainer = new Div();
        registerContainer.addClassNames("register-container");
        registerContainer.addClassNames("shadow-l", "rounded-l", LumoUtility.Padding.LARGE, LumoUtility.Background.BASE);
        registerContainer.setWidth("350px");

        H2 title = new H2("Crear cuenta");
        title.addClassNames(LumoUtility.TextAlignment.CENTER);

        TextField usernameField = new TextField("Usuario");
        usernameField.setWidthFull();

        EmailField emailField = new EmailField("Email");
        emailField.setWidthFull();

        PasswordField passwordField = new PasswordField("Contraseña");
        passwordField.setWidthFull();

        PasswordField confirmPasswordField = new PasswordField("Confirmar contraseña");
        confirmPasswordField.setWidthFull();

        Button registerButton = new Button("Registrarse");
        registerButton.addClassNames(LumoUtility.Background.PRIMARY,
                LumoUtility.TextColor.PRIMARY_CONTRAST,
                LumoUtility.Padding.SMALL,
                "rounded-m");
        registerButton.setWidthFull();
        registerButton.addClickListener(event -> {
            if (passwordField.getValue().equals(confirmPasswordField.getValue())) {
                String body = "{\"username\":\"" + usernameField.getValue() +
                              "\",\"email\":\"" + emailField.getValue() +
                              "\",\"password\":\"" + passwordField.getValue() + "\"}";

                try {
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("http://localhost:8080/api/auth/register"))
                            .header("Content-Type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString(body))
                            .build();

                    HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                            HttpResponse.BodyHandlers.ofString());

                    if (response.statusCode() == 200) {
                        Notification.show("Registro exitoso", 3000, Notification.Position.TOP_CENTER);
                        UI.getCurrent().navigate("login");
                    } else {
                        Notification.show("Error en el registro", 3000, Notification.Position.TOP_CENTER);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Notification.show("Error de conexión", 3000, Notification.Position.TOP_CENTER);
                }
            } else {
                Notification.show("Las contraseñas no coinciden", 3000, Notification.Position.TOP_CENTER);
            }
        });

        Anchor loginLink = new Anchor("login", "¿Ya tienes cuenta? Inicia sesión aquí");
        loginLink.addClassNames(LumoUtility.TextAlignment.CENTER, LumoUtility.Margin.Top.MEDIUM);
        loginLink.getStyle().set("display", "block");

        registerContainer.add(title, usernameField, emailField, passwordField, confirmPasswordField,
                registerButton, loginLink);
        add(registerContainer);
    }
}