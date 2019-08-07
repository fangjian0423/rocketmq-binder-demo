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
public class AggregatorApplication {

	public static final String TEMP_INPUT = "temp-output";

	public static final String DISCARD_INPUT = "discard-output";

	@Bean(AggregatorApplication.TEMP_INPUT)
	public MessageChannel tempChannel() {
		return new DirectChannel();
	}

	@Bean(AggregatorApplication.DISCARD_INPUT)
	public MessageChannel discardChannel() {
		return new DirectChannel();
	}

	public static void main(String[] args) {
		SpringApplication.run(AggregatorApplication.class, args);
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
			int count = 6;
			for (int index = 1; index <= count; index++) {
				source.output().send(MessageBuilder.withPayload("msg-" + index).build());
			}
		}
	}

}
