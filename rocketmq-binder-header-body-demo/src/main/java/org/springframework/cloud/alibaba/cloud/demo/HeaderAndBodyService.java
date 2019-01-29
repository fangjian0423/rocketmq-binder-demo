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

package org.springframework.cloud.alibaba.cloud.demo;

import java.util.Map;

import org.springframework.cloud.alibaba.cloud.demo.HeaderAndBodyApplication.Person;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@Service
public class HeaderAndBodyService {

	@StreamListener(value = Sink.INPUT, condition = "headers['PROPERTIES']['USERS_index']=='1'")
	public void receiveByHeader(Message msg) {
		System.out.println("filter by headers['PROPERTIES']['USERS_index']=='1': " + msg);
	}

	@StreamListener(value = Sink.INPUT, condition = "headers['PROPERTIES']['USERS_index']=='9999'")
	public void receivePerson(@Payload Person person) {
		System.out.println("Person: " + person);
	}

	@StreamListener(value = Sink.INPUT)
	public void receiveAllMsg(String msg) {
		System.out.println(
				"receive allMsg by StreamListener with load balance. content: " + msg);
	}

	@StreamListener(value = Sink.INPUT)
	public void receiveAllMsgHeaders(
			@Header("PROPERTIES") Map<String, String> usersProperties) {
		System.out.println("receive allMsg. custom headers: " + usersProperties);
	}

	@ServiceActivator(inputChannel = Sink.INPUT)
	public void receiveByServiceActivator(String msg) {
		System.out.println(
				"receive allMsg by ServiceActivator with load balance. content: " + msg);
	}

}
