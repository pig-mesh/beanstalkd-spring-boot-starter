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
package com.pig4cloud.beanstalkd.client.ex;

/**
 * The client sent a command line that was not well-formed. This can happen if the line does not end with \r\n, if
 * non-numeric characters occur where an integer is expected, if the wrong number of arguments are present, or if the
 * command line is mal-formed in any other way.
 * 
 * @author guojinfei
 * @version 1.0.0
 */
public class BadFormatException extends RuntimeException {

    /**  */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public BadFormatException() {
    }

    /**
     * @param message
     */
    public BadFormatException(String message) {
        super(message);
    }

}
