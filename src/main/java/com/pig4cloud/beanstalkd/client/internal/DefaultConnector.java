/*
 * Copyright (C) 2012~2016 dinstone<dinstone@163.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pig4cloud.beanstalkd.client.internal;

import com.pig4cloud.beanstalkd.client.config.BeanstalkConfigProperties;
import com.pig4cloud.beanstalkd.client.ex.ConnectionException;
import com.pig4cloud.beanstalkd.client.internal.codec.OperationDecoder;
import com.pig4cloud.beanstalkd.client.internal.codec.OperationEncoder;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author guojf
 * @version 1.0.0.2013-4-11
 */
public class DefaultConnector implements Connector {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultConnector.class);

	private NioSocketConnector ioConnector;

	private int refCount;

	/**
	 * @param config
	 * @param ioConnector
	 */
	public DefaultConnector(BeanstalkConfigProperties config) {
		initConnector(config);
	}

	/**
	 * @param config
	 */
	private void initConnector(BeanstalkConfigProperties config) {
		// create connector
		ioConnector = new NioSocketConnector();
		ioConnector.setConnectTimeoutMillis(config.getTimeout().getConnect());

		SocketSessionConfig sessionConfig = ioConnector.getSessionConfig();
		LOG.debug("KeepAlive is {}", sessionConfig.isKeepAlive());
		LOG.debug("ReadBufferSize is {}", sessionConfig.getReadBufferSize());
		LOG.debug("SendBufferSize is {}", sessionConfig.getSendBufferSize());
		sessionConfig.setReaderIdleTime(config.getTimeout().getRead());

		// add filter
		DefaultIoFilterChainBuilder chainBuilder = ioConnector.getFilterChain();

		String charsetName = config.getCharset();
		Charset charset = Charset.forName(charsetName == null ? "ASCII" : charsetName);
		LOG.debug("beanstalk.protocol.charset is {}", charset);

		final OperationEncoder encoder = new OperationEncoder(charset);
		final OperationDecoder decoder = new OperationDecoder(charset);
		chainBuilder.addLast("codec", new ProtocolCodecFilter(new ProtocolCodecFactory() {

			@Override
			public ProtocolEncoder getEncoder(IoSession session) throws Exception {
				return encoder;
			}

			@Override
			public ProtocolDecoder getDecoder(IoSession session) throws Exception {
				return decoder;
			}
		}));

		// set handler
		ioConnector.setHandler(new ConnectionHandler());

		InetSocketAddress address = new InetSocketAddress(config.getHost(), config.getPort());
		ioConnector.setDefaultRemoteAddress(address);
	}

	/**
	 * @return
	 */
	@Override
	public IoSession createSession() {
		LOG.debug("Connecting to beanstalkd service on {}", ioConnector.getDefaultRemoteAddress());
		// create session
		ConnectFuture cf = ioConnector.connect().awaitUninterruptibly();

		try {
			IoSession session = cf.getSession();
			return session;
		} catch (RuntimeException e) {
			throw new ConnectionException(
					"can't connect beanstalkd service on " + ioConnector.getDefaultRemoteAddress(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see com.pig4cloud.beanstalkd.client.internal.Connector#dispose()
	 */
	@Override
	public void dispose() {
		ioConnector.dispose(false);
	}

	/**
	 *
	 */
	public void incrementRefCount() {
		++refCount;
	}

	/**
	 *
	 */
	public void decrementRefCount() {
		if (refCount > 0) {
			--refCount;
		}
	}

	/**
	 * @return
	 */
	public boolean isZeroRefCount() {
		return refCount == 0;
	}
}
