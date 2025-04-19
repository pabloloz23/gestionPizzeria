package com.pizza.views;

        import com.vaadin.flow.component.UI;
        import com.vaadin.flow.component.button.Button;
        import com.vaadin.flow.component.dependency.CssImport;
        import com.vaadin.flow.component.html.Div;
        import com.vaadin.flow.component.html.H1;
        import com.vaadin.flow.component.html.Image;
        import com.vaadin.flow.component.html.Paragraph;
        import com.vaadin.flow.component.orderedlayout.FlexComponent;
        import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
        import com.vaadin.flow.component.orderedlayout.VerticalLayout;
        import com.vaadin.flow.router.PageTitle;
        import com.vaadin.flow.router.Route;
        import com.vaadin.flow.router.RouteAlias;
        import com.vaadin.flow.theme.lumo.LumoUtility;

        @Route(value = "home", layout = com.pizza.MainLayout.class)
        @RouteAlias(value = "", layout = com.pizza.MainLayout.class)
        @PageTitle("Pizza Online - Las mejores pizzas")
        @CssImport("./styles/landing-page.css")
        public class LandingView extends VerticalLayout {

            public LandingView() {
                addClassNames("landing-page");
                setSizeFull();
                setAlignItems(FlexComponent.Alignment.CENTER);
                setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
                setPadding(true);
                setSpacing(true);

                // Contenedor principal con animación
                Div mainContainer = new Div();
                mainContainer.addClassNames("main-container", "animate__animated", "animate__fadeIn");
                mainContainer.getStyle().set("animation-duration", "1.5s");

                // Logo o imagen principal
                Image pizzaImage = new Image("images/pizza-hero.jpg", "Deliciosa pizza");
                pizzaImage.addClassNames("hero-image", "animate__animated", "animate__zoomIn");
                pizzaImage.getStyle().set("animation-duration", "1.8s");
                pizzaImage.getStyle().set("animation-delay", "0.5s");
                pizzaImage.setWidth("400px");
                pizzaImage.setHeight("300px");

                // Título con animación
                H1 title = new H1("PIZZA ONLINE");
                title.addClassNames("main-title", "animate__animated", "animate__fadeInDown");
                title.getStyle().set("animation-duration", "1s");

                // Eslogan
                Paragraph slogan = new Paragraph("Las pizzas más deliciosas a un clic de distancia");
                slogan.addClassNames("slogan", "animate__animated", "animate__fadeInUp");
                slogan.getStyle().set("animation-delay", "0.7s");

                // Botones de acción
                Button orderButton = new Button("Ordenar Ahora");
                orderButton.addClickListener(e -> UI.getCurrent().navigate("orders"));
                orderButton.addClassNames("order-button", "animate__animated", "animate__bounceIn");
                orderButton.getStyle().set("animation-delay", "1.2s");
                orderButton.addClassNames(
                    LumoUtility.Background.PRIMARY,
                    LumoUtility.TextColor.PRIMARY_CONTRAST,
                    LumoUtility.Padding.MEDIUM,
                    "rounded-l"
                );

                // Botones de login y registro
                Button loginButton = new Button("Iniciar Sesión");
                loginButton.addClickListener(e -> UI.getCurrent().navigate("login"));
                loginButton.addClassNames("login-button", "animate__animated", "animate__bounceIn");
                loginButton.getStyle().set("animation-delay", "1.4s");
                loginButton.addClassNames(
                    LumoUtility.Background.SUCCESS,
                    LumoUtility.TextColor.PRIMARY_CONTRAST,
                    LumoUtility.Padding.MEDIUM,
                    "rounded-l"
                );

                Button registerButton = new Button("Registrarse");
                registerButton.addClickListener(e -> UI.getCurrent().navigate("register"));
                registerButton.addClassNames("register-button", "animate__animated", "animate__bounceIn");
                registerButton.getStyle().set("animation-delay", "1.6s");
                registerButton.addClassNames(
                    LumoUtility.Background.CONTRAST,
                    LumoUtility.TextColor.PRIMARY_CONTRAST,
                    LumoUtility.Padding.MEDIUM,
                    "rounded-l"
                );

                // Layout para botones de autenticación
                HorizontalLayout authButtons = new HorizontalLayout(loginButton, registerButton);
                authButtons.setSpacing(true);
                authButtons.addClassNames("auth-buttons", "animate__animated", "animate__fadeInUp");
                authButtons.getStyle().set("animation-delay", "1.0s");

                // Agregar componentes al contenedor principal
                mainContainer.add(title, pizzaImage, slogan, orderButton, authButtons);

                // Agregar contenedor a la vista
                add(mainContainer);
            }
        }