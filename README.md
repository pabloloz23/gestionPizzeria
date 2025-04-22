# Pizza API

## Descripción

Este proyecto es una API para la gestión de pedidos de una pizzería. Permite a los usuarios registrarse, iniciar sesión y realizar pedidos de pizza.

## Tecnologías Utilizadas

*   Java
*   Maven
*   Spring Boot
*   JavaScript
*   TypeScript
*   NPM
*   Vaadin
*   Spring Security

## Requisitos

*   Java Development Kit (JDK) 17 o superior
*   Maven
*   Node.js y npm (opcional, para el frontend)

## Configuración

1.  Clona el repositorio:

    ```bash
    git clone <URL_del_repositorio>
    ```

2.  Navega al directorio del proyecto:

    ```bash
    cd pizza-API
    ```

3.  Compila el proyecto con Maven:

    ```bash
    mvn clean install
    ```

4.  Ejecuta la aplicación:

    ```bash
    mvn spring-boot:run
    ```

## Configuración de Seguridad

La aplicación utiliza Spring Security para la autenticación y autorización. La configuración de seguridad se encuentra en `src/main/java/com/pizza/api/config/security/VaadinSecurityConfig.java`.

Las siguientes rutas están permitidas sin autenticación:

*   `/`
*   `/register`
*   `/login`

Todas las demás rutas requieren autenticación.

## Estilos

Los estilos de la aplicación se definen en `src/main/frontend/styles/landing-page.css`.

## DTOs

El DTO `OrderRequest` se encuentra en `src/main/java/com/pizza/core/dto/OrderRequest.java` y contiene la estructura para realizar pedidos.

```java
package com.pizza.core.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    @NotNull
    private Long userId;

    @NotEmpty
    private List<OrderItemRequest> items;
}
```

## Enlace al Repositorio

[Link al Repositorio](https://github.com/pabloloz23/gestionPizzeria)