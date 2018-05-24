package com.jk.RubbitMq;

import com.jk.model.Company;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static com.jk.util.HttpClient.togetString;
import static io.lettuce.core.pubsub.PubSubOutput.Type.message;

@Component
@RabbitListener(queues = "fanout.A")
public class FanoutReceiverA {

    @RabbitHandler
    public void process(List <Company> list) throws IOException {

        String userphone = list.get(0).getId();

        togetString(userphone);

        System.out.println("fanout Receiver A  : " + userphone );
    }

}
