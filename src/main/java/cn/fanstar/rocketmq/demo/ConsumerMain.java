package cn.fanstar.rocketmq.demo;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by fanjun on 2018/2/28.
 */
public class ConsumerMain {


    public static void main(String[] args) {


        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("demo-consumer");
        consumer.setNamesrvAddr("localhost:9876");

        try {
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.subscribe("demo-topic", "default");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    MessageExt messageExt = list.get(0);
                    System.out.println(String.format("received message : %s", new String(messageExt.getBody())));
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            consumer.start();
            System.out.println("consumer start");

        } catch (MQClientException e) {
            e.printStackTrace();
        }


    }
}
