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
     * @param dataSource
     * @return
     */
    @Bean
    public StandaloneProcessEngineConfiguration engineConfiguration(DruidDataSource dataSource) {
        StandaloneProcessEngineConfiguration engineConfiguration = new StandaloneProcessEngineConfiguration();
        engineConfiguration.setDataSource(dataSource);
        engineConfiguration.setDatabaseSchemaUpdate("true");
        engineConfiguration.setLabelFontName("宋体");
        engineConfiguration.setActivityFontName("宋体");
        return engineConfiguration;
    }

    /**
     * 创建引擎对象
     *
     * @param engineConfiguration
     * @return
     */
    @Bean
    public ProcessEngine processEngine(StandaloneProcessEngineConfiguration engineConfiguration) {
        engineConfiguration.buildProcessEngine();
        return new ProcessEngineImpl(engineConfiguration);
    }

    /**
     * 仓库服务
     *
     * @param processEngine
     * @return
     */
    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    /**
     * 运行服务
     *
     * @param processEngine
     * @return
     */
    @Bean
    public org.activiti.engine.RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    /**
     * 任务服务
     *
     * @param processEngine
     * @return
     */
    @Bean
    public org.activiti.engine.TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    /**
     * 历史服务
     *
     * @param processEngine
     * @return
     */
    @Bean
    public org.activiti.engine.HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }
}
