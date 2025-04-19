package com.pizza;

        import com.vaadin.flow.component.applayout.AppLayout;
        import com.vaadin.flow.component.html.H1;
        import com.vaadin.flow.component.html.Nav;
        import com.vaadin.flow.component.orderedlayout.FlexComponent;
        import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
        import com.vaadin.flow.router.RouterLink;
        import com.vaadin.flow.theme.lumo.LumoUtility;

        public class MainLayout extends AppLayout {

            public MainLayout() {
                createHeader();
            }

            private void createHeader() {
                H1 logo = new H1("Pizza Online");
                logo.addClassNames(LumoUtility.FontSize.XLARGE, LumoUtility.Margin.MEDIUM);
                logo.getStyle().set("cursor", "pointer");
                logo.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("")));

                RouterLink homeLink = new RouterLink("Inicio", com.pizza.views.LandingView.class);
                RouterLink loginLink = new RouterLink("Login", com.pizza.views.LoginView.class);
                RouterLink registerLink = new RouterLink("Registro", com.pizza.views.RegisterView.class);
                RouterLink orderLink = new RouterLink("Pedidos", com.pizza.views.OrderView.class);
                RouterLink paymentLink = new RouterLink("Pago", com.pizza.views.PaymentView.class);

                HorizontalLayout navLinks = new HorizontalLayout(homeLink, loginLink, registerLink, orderLink, paymentLink);
                navLinks.addClassNames(LumoUtility.Gap.MEDIUM);

                Nav nav = new Nav(navLinks);
                HorizontalLayout header = new HorizontalLayout(logo, nav);
                header.setWidthFull();
                header.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
                header.setAlignItems(FlexComponent.Alignment.CENTER);
                header.addClassNames(LumoUtility.Padding.MEDIUM, LumoUtility.Background.CONTRAST_5);

                addToNavbar(header);
            }
        }