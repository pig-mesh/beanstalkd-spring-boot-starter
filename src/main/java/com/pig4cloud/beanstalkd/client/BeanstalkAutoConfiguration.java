/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.pig4cloud.beanstalkd.client;

import com.pig4cloud.beanstalkd.client.config.BeanstalkConfigProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lengleng
 * @date 2020/2/7
 * <p>
 * 自动配置入口
 */
@Configuration
@EnableConfigurationProperties(BeanstalkConfigProperties.class)
public class BeanstalkAutoConfiguration {

    @Bean
    public BeanstalkClientFactory factory(BeanstalkConfigProperties properties) {
        return new BeanstalkClientFactory(properties);
    }

    @Bean
    @ConditionalOnMissingBean(JobProducer.class)
    public JobProducer producer(BeanstalkClientFactory factory, BeanstalkConfigProperties properties) {
        return factory.createJobProducer(properties.getDefaultTube());
    }

    @Bean
    @ConditionalOnBean(JobProducer.class)
    @ConditionalOnMissingBean(JobConsumer.class)
    public JobConsumer consumer(BeanstalkClientFactory factory, BeanstalkConfigProperties properties) {
        return factory.createJobConsumer(properties.getDefaultTube());
    }

}
