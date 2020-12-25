package am.ceocompany.springbootdocker.config;

import am.ceocompany.springbootdocker.service.serviceimpl.BusinessLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class BeanConfig {

    @Bean
    public BusinessLogic createBusinessLogicBean() {
        return new BusinessLogic();
    }
}
