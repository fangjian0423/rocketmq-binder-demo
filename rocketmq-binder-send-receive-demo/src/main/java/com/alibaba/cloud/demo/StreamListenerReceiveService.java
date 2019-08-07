package com.alibaba.cloud.demo;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * @StreamListener annotation handle by
 * StreamListenerMessageHandler(single @StreamListener method) &
 * DispatchingStreamListenerMessageHandler(multi @StreamListener methods)
 *
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@Service
public class StreamListenerReceiveService {

	@StreamListener(Sink.INPUT)
	public void receiveByStreamListener1(String receiveMsg) {
		System.out.println("receiveByStreamListener1: " + receiveMsg);
	}

	@StreamListener(Sink.INPUT)
	public void receiveByStreamListener2(String receiveMsg) {
		System.out.println("receiveByStreamListener2: " + receiveMsg);
	}

}
