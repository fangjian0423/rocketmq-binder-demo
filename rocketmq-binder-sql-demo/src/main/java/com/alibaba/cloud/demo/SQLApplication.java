package com.alibaba.cloud.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import com.alibaba.cloud.demo.SQLApplication.CustomSink;

/**
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@SpringBootApplication
@EnableBinding({ Source.class, CustomSink.class })
public class SQLApplication {

	interface CustomSink {

		@Input("input1")
		MessageChannel output1();

		@Input("input2")
		MessageChannel output2();

	}

	public static void main(String[] args) {
		SpringApplication.run(SQLApplication.class, args);
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
				source.output().send(MessageBuilder.withPayload("msg-" + index)
						.setHeader("index", index).build());
			}
		}
	}

}
