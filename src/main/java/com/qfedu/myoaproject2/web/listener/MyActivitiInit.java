package com.qfedu.myoaproject2.web.listener;



import com.qfedu.myoaproject2.service.LeaveService;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Auther: Kam
 * @Date: 下午 11:37 2018-10-24
 * @Description: a
 * @Version: 1.0
 */
@WebListener
public class MyActivitiInit implements ServletContextAware, ServletContextListener {
    @Override
    public void setServletContext(ServletContext servletContext) {
        LeaveService leaveService = WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean(LeaveService.class);
        leaveService.initPro();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        setServletContext(servletContextEvent.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
