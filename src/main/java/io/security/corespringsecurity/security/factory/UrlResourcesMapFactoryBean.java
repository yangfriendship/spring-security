package io.security.corespringsecurity.security.factory;


import io.security.corespringsecurity.security.service.SecurityResourceService;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class UrlResourcesMapFactoryBean implements
    FactoryBean<LinkedHashMap<RequestMatcher, List<ConfigAttribute>>> {

    private SecurityResourceService service;

    private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resourceMap;

    public void setService(SecurityResourceService service) {
        this.service = service;
    }

    @Override
    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getObject() throws Exception {

        if (resourceMap == null) {
            init();
        }

        return this.resourceMap;
    }

    private void init() {
        resourceMap = service.getResourcesAsMap();
    }

    @Override
    public Class<?> getObjectType() {
        return LinkedHashMap.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
