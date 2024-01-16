package es.consumo.gescom.commons.config;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setPropertyCondition(context -> {
                            if (context.getSource() instanceof HibernateProxy) {
                                if (Hibernate.isInitialized(context.getSource())) {
                                    return true;
                                } else {
                                    Hibernate.initialize(context.getSource());
                                }
                            }
                            return true;
                        }

                );
        return modelMapper;
    }

}
