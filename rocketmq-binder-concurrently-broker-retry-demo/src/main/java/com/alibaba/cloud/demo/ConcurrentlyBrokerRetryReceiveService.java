/*
 * Copyright (C) 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.cloud.demo;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@Service
public class ConcurrentlyBrokerRetryReceiveService {

	private static final Logger log = LoggerFactory
			.getLogger(ConcurrentlyBrokerRetryReceiveService.class);

	private AtomicInteger count = new AtomicInteger(1);

	@StreamListener(Sink.INPUT)
	public void receiveConcurrentlyMsg(String receiveMsg) {
	    log.info("invoke: " + count.get());
		if (count.getAndIncrement() <= 5) {
            throw new RuntimeException("Oops: " + receiveMsg);
		}
		else {
			log.info("receiveConcurrentlyMsg: " + receiveMsg);
		}
	}

}
