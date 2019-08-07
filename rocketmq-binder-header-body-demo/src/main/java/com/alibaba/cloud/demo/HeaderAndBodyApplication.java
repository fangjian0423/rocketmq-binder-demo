package com.alibaba.cloud.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.MimeTypeUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@SpringBootApplication
@EnableBinding({ Source.class, Sink.class })
public class HeaderAndBodyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeaderAndBodyApplication.class, args);
	}

	@Bean
	public CustomRunner customRunner() {
		return new CustomRunner();
	}

	public static class CustomRunner implements CommandLineRunner {

		@Autowired
		private Source source;

		private ObjectMapper objectMapper = new ObjectMapper();

		@Override
		public void run(String... args) throws Exception {
			int count = 5;
			for (int index = 1; index <= count; index++) {
				source.output().send(MessageBuilder.withPayload("msg-" + index)
						.setHeader("index", index).build());
			}
			source.output().send(MessageBuilder
					.withPayload(objectMapper.writeValueAsString(new Person(99, "Jim")))
					.setHeader("index", 9999)
                    .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                    .build());
		}
	}

	public static class Person {
		int age;
		String name;

		public Person() {
		}

		public Person(int age, String name) {
			this.age = age;
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Person{" + "age=" + age + ", name='" + name + '\'' + '}';
		}
	}

}
