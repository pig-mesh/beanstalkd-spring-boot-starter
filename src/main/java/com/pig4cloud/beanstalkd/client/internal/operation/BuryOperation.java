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
package com.pig4cloud.beanstalkd.client.internal.operation;

import com.pig4cloud.beanstalkd.client.internal.OperationFuture;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.buffer.IoBuffer;

import java.nio.charset.Charset;

@Slf4j
public class BuryOperation extends AbstractOperation<Boolean> {


    public BuryOperation(long id, int priority) {
        super(new OperationFuture<Boolean>());
        this.command = "bury " + id + " " + priority;
    }

    @Override
    public boolean parseReply(Charset charset, IoBuffer in) {
        try {
            String line = in.getString(charset.newDecoder());
            log.debug("command is [{}], reply is [{}]", command, line);

            if (line.startsWith("BURIED")) {
                future.setResult(true);
                return true;
            } else if (line.startsWith("NOT_FOUND")) {
                future.setResult(false);
                return true;
            }

            exceptionHandler(line);
        } catch (Exception e) {
            future.setException(e);
        }
        return true;
    }

}
