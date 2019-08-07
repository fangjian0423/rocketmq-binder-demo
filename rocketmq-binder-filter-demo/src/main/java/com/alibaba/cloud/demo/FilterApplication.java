package com.alibaba.cloud.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

/**
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@SpringBootApplication
@EnableBinding({ Source.class, Sink.class })
public class FilterApplication {

	public static final String DISCARD_INPUT = "discard-output";

	public static final String SUCCESS_INPUT = "success-output";

	public static void main(String[] args) {
		SpringApplication.run(FilterApplication.class, args);
	}

	@Bean(FilterApplication.DISCARD_INPUT)
	public MessageChannel discardChannel() {
		return new DirectChannel();
	}

	@Bean(FilterApplication.SUCCESS_INPUT)
	public MessageChannel successChannel() {
		return new DirectChannel();
	}

	@Bean
	public CustomRunner customRunner() {
		return new CustomRunner();
	}

	public static class CustomRunner implements CommandLineRunner {

		@Autowired
		private Source source;

		@Override
		public void run(String... args) throws Exception {
			int count = 5;
			for (int index = 1; index <= count; index++) {
				source.output().send(MessageBuilder.withPayload("msg-" + index).build());
			}
		}
	}

}
