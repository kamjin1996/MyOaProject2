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
    public DefaultAdvisorAutoProxyCreator proxyCreator(){
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * 授权拦截器
     *
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor sourceAdvisor(){
        return new AuthorizationAttributeSourceAdvisor();
    }

    /**
     * 配置权限管理器
     *
     * @param userRealm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(UserRealm userRealm){
        return new DefaultWebSecurityManager(userRealm);
    }

    @Bean
    public UserRealm userRealm(ResourceService resourceService){
        return  new UserRealm(resourceService);
    }

    /**
     * 配置Shiro过滤器
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/login.html");
        shiroFilter.setSuccessUrl("/index.html");
        shiroFilter.setUnauthorizedUrl("/unauthor.html");
        Map<String,String> map = new HashMap();
        map.put("/media/**","anon");
        map.put("/login.html","anon");
        map.put("/login.do","anon");
        map.put("/**","authc");
        shiroFilter.setFilterChainDefinitionMap(map);
        return shiroFilter;
    }

}
