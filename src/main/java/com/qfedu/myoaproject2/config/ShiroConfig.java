package com.qfedu.myoaproject2.config;

import com.qfedu.myoaproject2.service.ResourceService;
import com.qfedu.myoaproject2.shiro.UserRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Kam
 * @Date: 下午 9:34 2018-10-23
 * @Description: Shiro配置类
 * @Version: 1.0
 */
@Configuration
public class ShiroConfig {

    /**
     * 配置Shiro注解生效
     *
     * @return
     */
    @Bean
    public org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 授权拦截器
     *
     * @return
     */
    @Bean
    public org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor attributeSourceAdvisor(){
        return new AuthorizationAttributeSourceAdvisor();
    }

    /**
     * 配置权限管理器
     *
     * @param userRealm
     * @return
     */
    @Bean
    public org.apache.shiro.web.mgt.DefaultWebSecurityManager securityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public UserRealm userRealm(ResourceService resourceService){
        UserRealm userRealm = new UserRealm();
        userRealm.setResourceService(resourceService);
        return userRealm;
    }

    /**
     * 配置Shiro过滤器
     *
     * @param securityManager
     * @return
     */
    @Bean
    public org.apache.shiro.spring.web.ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/login.html");
        shiroFilter.setSuccessUrl("/index.html");
        shiroFilter.setUnauthorizedUrl("/unauthor.html");
        Map<String,String> filterChainDefinitions = new HashMap();
        filterChainDefinitions.put("/media/**","anon");
        filterChainDefinitions.put("/login.html","anon");
        filterChainDefinitions.put("/login.do","anon");
        filterChainDefinitions.put("/**","authc");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitions);
        return shiroFilter;
    }

}
