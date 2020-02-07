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
 * The client/server sent a command that the server/client does not know.
 * 
 * @author guojinfei
 * @version 1.0.0
 */
public class UnknownCommandException extends RuntimeException {

	/**  */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UnknownCommandException() {
	}

	/**
	 * @param message
	 */
	public UnknownCommandException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UnknownCommandException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UnknownCommandException(String message, Throwable cause) {
		super(message, cause);
	}

}
