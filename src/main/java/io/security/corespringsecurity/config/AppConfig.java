package io.security.corespringsecurity.config;

import io.security.corespringsecurity.repository.ResourcesRepository;
import io.security.corespringsecurity.security.service.SecurityResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Autowired
    private ResourcesRepository resourcesRepository;

    @Bean
    public SecurityResourceService securityResourceService() {
        SecurityResourceService securityResourceService = new SecurityResourceService();
        securityResourceService.setResourcesRepository(this.resourcesRepository);
        return securityResourceService;
    }

}
