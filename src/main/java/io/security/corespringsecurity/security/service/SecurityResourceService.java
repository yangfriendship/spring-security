package io.security.corespringsecurity.security.service;

import io.security.corespringsecurity.domain.entity.Resources;
import io.security.corespringsecurity.repository.ResourcesRepository;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class SecurityResourceService {

    private ResourcesRepository resourcesRepository;

    public void setResourcesRepository(
        ResourcesRepository resourcesRepository) {
        this.resourcesRepository = resourcesRepository;
    }

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourcesAsMap() {

        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> map = new LinkedHashMap<>();

        List<Resources> allResources = resourcesRepository.findAllResources();

        allResources.stream()
            .forEach(re -> {
                List<ConfigAttribute> configAttributes = new ArrayList<>();

                re.getRoleSet()
                    .forEach(role -> {
                        configAttributes.add(new SecurityConfig(role.getRoleName()));
                        map.put(new AntPathRequestMatcher(re.getResourceName()),configAttributes);
                    });
            });

        return map;
    }
}
