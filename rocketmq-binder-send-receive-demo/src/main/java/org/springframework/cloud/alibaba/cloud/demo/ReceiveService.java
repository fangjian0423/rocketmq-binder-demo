package org.springframework.cloud.alibaba.cloud.demo;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:fangjian0423@gmail.com">Jim</a>
 */
@Service
public class ReceiveService {

    @StreamListener(Sink.INPUT)
    public void receive(String receiveMsg) {
        System.out.println("receive: " + receiveMsg);
    }

}
