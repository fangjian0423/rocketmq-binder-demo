/*
 * Copyright (C) 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.cloud.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.bus.event.AckRemoteApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@RestController
public class BusController {

	private final Environment env;

	private final ObjectMapper objectMapper;

	@Value("${spring.cloud.bus.id}")
	private String serviceId;

	public BusController(Environment env, ObjectMapper objectMapper) {
		this.env = env;
        this.objectMapper = objectMapper;
    }

	@GetMapping("/bus/env")
	public String envValue(@RequestParam("key") String key) {
		return env.getProperty(key, "unknown");
	}

    @EventListener
    public void onAckEvent(AckRemoteApplicationEvent event)
        throws JsonProcessingException {
        System.out.printf("ServiceId [%s] listeners on %s\n", serviceId,
            objectMapper.writeValueAsString(event));
    }

}
