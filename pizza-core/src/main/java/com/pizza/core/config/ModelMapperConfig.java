package com.pizza.core.config;

    import org.modelmapper.ModelMapper;
    import org.modelmapper.config.Configuration.AccessLevel;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;

    import com.pizza.core.model.Order;
    import com.pizza.core.dto.OrderResponse;

    @Configuration
    public class ModelMapperConfig {

        @Bean
        public ModelMapper modelMapper() {
            ModelMapper mapper = new ModelMapper();

            mapper.getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(AccessLevel.PRIVATE);

            // Configuración personalizada para OrderResponse
            mapper.createTypeMap(Order.class, OrderResponse.class)
                    .addMapping(Order::getId, OrderResponse::setOrderId)
                    .addMapping(src -> src.getStatus().name(), OrderResponse::setStatus);

            return mapper;
        }
    }