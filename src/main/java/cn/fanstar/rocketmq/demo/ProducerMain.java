package cn.fanstar.rocketmq.demo;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * Created by fanjun on 2018/2/28.
 */
public class ProducerMain {


    public static void main(String[] args) {

        DefaultMQProducer producer = new DefaultMQProducer("producer-demo");
        producer.setNamesrvAddr("localhost:9876");

        try {
            producer.start();
            Message message = new Message("demo-topic",    /*topic*/
                                                "default", /* tag */
                                             "greeting from fanstar".getBytes(RemotingHelper.DEFAULT_CHARSET) /* message body */
                                          );
            SendResult sendResult = producer.send(message);
            System.out.println(sendResult);

        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }finally {
            producer.shutdown();
        }


    }

}
