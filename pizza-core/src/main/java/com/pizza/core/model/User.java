package com.pizza.core.model;

    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.Setter;


    import java.util.Set;
    import java.util.HashSet;

    @Entity
    @Getter
    @Setter
    @Table(name = "usuarios")
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true, nullable = false)
        private String username;

        @Column(nullable = false)
        private String password;

        @Column(nullable = false)
        private String nombre;

        @Column(unique = true)
        private String email;

        @ElementCollection(fetch = FetchType.EAGER)
        @Enumerated(EnumType.STRING)
        private Set<Role> roles = new HashSet<>();
    }