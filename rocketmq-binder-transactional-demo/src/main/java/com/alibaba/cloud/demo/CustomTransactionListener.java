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

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

/**
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@RocketMQTransactionListener(txProducerGroup = "txGroup", corePoolSize = 5, maximumPoolSize = 10)
public class CustomTransactionListener implements RocketMQLocalTransactionListener {

	@Autowired
	private FooService fooService;

	@Override
	public RocketMQLocalTransactionState executeLocalTransaction(Message msg,
			Object arg) {
		fooService.checkInfo(msg);
		if ("1".equals(msg.getHeaders().get("error"))) {
			System.out.println(new String((byte[]) msg.getPayload()) + " rollback");
			return RocketMQLocalTransactionState.ROLLBACK;
		}
		return RocketMQLocalTransactionState.COMMIT;
	}

	@Override
	public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
		System.out.println(msg + " check local");
		return RocketMQLocalTransactionState.COMMIT;
	}

}
