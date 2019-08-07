package com.alibaba.cloud.demo;

import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@Service
public class ServiceActivatorReceiveService {

	/**
	 * read msg from MessageChannel with Sink.INPUT name & send the return values to
	 * MessageChannel with SendAndReceiveApplication.TEMP_OUTPUT name
	 */
	@ServiceActivator(inputChannel = Sink.INPUT, outputChannel = SendAndReceiveApplication.TEMP_OUTPUT)
	public String receiveByServiceActivator(String receiveMsg) {
		System.out.println("receive by @ServiceActivator(inputChannel=" + Sink.INPUT
				+ ", outputChannel=" + SendAndReceiveApplication.TEMP_OUTPUT + "): "
				+ receiveMsg);
		return "handle by ServiceActivator, raw msg(" + receiveMsg + ")";
	}

	/**
	 * read msg from MessageChannel with SendAndReceiveApplication.TEMP_OUTPUT name
	 */
	@ServiceActivator(inputChannel = SendAndReceiveApplication.TEMP_OUTPUT)
	public void receiveByServiceActivatorTemp(String receiveMsg) {
		System.out.println("receive by @ServiceActivator(inputChannel="
				+ SendAndReceiveApplication.TEMP_OUTPUT + "): " + receiveMsg);
	}

}
