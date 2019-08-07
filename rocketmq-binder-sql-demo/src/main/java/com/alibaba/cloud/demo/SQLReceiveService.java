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

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@Service
public class SQLReceiveService {

	@StreamListener("input1")
	public void receiveInput1Msg(String receiveMsg) {
		System.out.println("receive index <= 2 msg: " + receiveMsg);
	}

	@StreamListener("input2")
	public void receiveInput2Msg(String receiveMsg) {
		System.out.println("receive index > 2 msg: " + receiveMsg);
	}

}
