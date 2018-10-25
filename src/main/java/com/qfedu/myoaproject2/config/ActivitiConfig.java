package com.qfedu.myoaproject2.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: Kam
 * @Date: 下午 10:08 2018-10-23
 * @Description: 工作流配置类
 * @Version: 1.0
 */
@Configuration
public class ActivitiConfig {

    /**
     * 引擎的配置对象
     *
     * @param druidDataSource
     * @return
     */
    @Bean
    public org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration standaloneProcessEngineConfiguration(DruidDataSource druidDataSource){
        StandaloneProcessEngineConfiguration processEngineConfiguration = new StandaloneProcessEngineConfiguration();
        processEngineConfiguration.setDataSource(druidDataSource);
        processEngineConfiguration.setDatabaseSchemaUpdate("true");
        processEngineConfiguration.setLabelFontName("宋体");
        processEngineConfiguration.setActivityFontName("宋体");
        return processEngineConfiguration;
    }

    /**
     * 创建引擎对象
     *
     * @param processEngineConfiguration
     * @return
     */
    @Bean
    public org.activiti.engine.ProcessEngine processEngine(StandaloneProcessEngineConfiguration processEngineConfiguration){
        processEngineConfiguration.buildProcessEngine();
        ProcessEngine processEngine = new ProcessEngineImpl(processEngineConfiguration);
        return processEngine;
    }

    /**
     * 仓库服务
     *
     * @param processEngine
     * @return
     */
    @Bean
    public org.activiti.engine.RepositoryService repositoryService(ProcessEngine processEngine){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        return repositoryService;
    }

    /**
     * 运行服务
     *
     * @param processEngine
     * @return
     */
    @Bean
    public org.activiti.engine.RuntimeService runtimeService(ProcessEngine processEngine){
        return processEngine.getRuntimeService();
    }

    /**
     * 任务服务
     *
     * @param processEngine
     * @return
     */
    @Bean
    public org.activiti.engine.TaskService taskService(ProcessEngine processEngine){
        return processEngine.getTaskService();
    }

    /**
     * 历史服务
     *
     * @param processEngine
     * @return
     */
    @Bean
    public org.activiti.engine.HistoryService historyService(ProcessEngine processEngine){
        return processEngine.getHistoryService();
    }
}
