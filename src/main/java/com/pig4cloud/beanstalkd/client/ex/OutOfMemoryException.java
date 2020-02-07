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
 * The server cannot allocate enough memory for the job. The client should try
 * again later.
 * 
 * @author guojinfei
 * @version 1.0.0
 */
public class OutOfMemoryException extends RuntimeException {

	/**  */
	private static final long serialVersionUID = 1L;

	public OutOfMemoryException() {
		super();
	}

	public OutOfMemoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public OutOfMemoryException(String message) {
		super(message);
	}

	public OutOfMemoryException(Throwable cause) {
		super(cause);
	}

}
