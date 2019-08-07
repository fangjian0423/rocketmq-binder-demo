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

import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@Service
public class TransformerService {

	@ServiceActivator(inputChannel = TransformerApplication.TRANSFORMER_INPUT)
	public void receiveTransformerMsg(String receiveMsg) {
		System.out.println("receiveTransformerMsg: " + receiveMsg);
	}

	@Transformer(inputChannel = Sink.INPUT, outputChannel = TransformerApplication.TRANSFORMER_INPUT)
	public String receiveByTransformer(String receiveMsg) {
		return "transformer(" + receiveMsg + ")";
	}

}
