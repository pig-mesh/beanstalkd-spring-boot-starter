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

package com.pig4cloud.beanstalkd.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lengleng
 * @date 2020/2/7
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.beanstalkd")
public class BeanstalkConfigProperties {

    /**
     * beanstalk service port
     */
    private String host = "localhost";

    /**
     * beanstalk service port
     */
    private Integer port = 11300;

    /**
     * max job size
     */
    private Integer jobMaxSize = 65535;

    /**
     * beanstalk service charset
     */
    private String charset = "ASCII";

    /**
     * beanstalk service timeout
     */
    private BeanstalkTimeout timeout = new BeanstalkTimeout();

    /**
     * beanstalk service timeout
     */
    private Boolean ignoreDefaultTube = false;

    /**
     * beanstalk service defaultTube
     */
    private String defaultTube = "pig4cloud";
}
