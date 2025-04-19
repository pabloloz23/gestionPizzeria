package com.pizza.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Route(value = "login", layout = com.pizza.MainLayout.class)
public class LoginView extends VerticalLayout {

    public LoginView() {
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);

        Div loginContainer = new Div();
        loginContainer.addClassNames("login-container", "animate__animated", "animate__fadeIn");
        loginContainer.getStyle().set("animation-duration", "0.8s");
        loginContainer.addClassNames("shadow-l", "rounded-l", LumoUtility.Padding.LARGE, LumoUtility.Background.BASE);

        H2 title = new H2("Iniciar Sesión");
        title.addClassNames(LumoUtility.TextAlignment.CENTER);

        LoginForm loginForm = new LoginForm();
        loginForm.addLoginListener(event -> {
            String body = "{\"username\":\"" + event.getUsername() + "\",\"password\":\"" + event.getPassword() + "\"}";

            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/api/auth/login"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(body))
                        .build();

                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    Notification.show("Login exitoso", 3000, Notification.Position.TOP_CENTER);
                    UI.getCurrent().navigate("orders");
                } else {
                    loginForm.setError(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                loginForm.setError(true);
            }
        });

        Anchor registerLink = new Anchor("register", "¿No tienes cuenta? Regístrate aquí");
        registerLink.addClassNames(LumoUtility.TextAlignment.CENTER, LumoUtility.Margin.Top.MEDIUM);
        registerLink.getStyle().set("display", "block");

        loginContainer.add(title, loginForm, registerLink);
        add(loginContainer);
    }
}