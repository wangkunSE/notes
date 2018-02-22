package com.soul.kafka.simpledemo;

import com.soul.zookeeper.constants.Constants;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class KafkaProducerDemo extends Thread {

    private static final String TOPIC = "foo";
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private Producer<String, String> producer;

    public KafkaProducerDemo(Producer<String, String> producer) {
        this.producer = producer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            producer.send(new KeyedMessage<String, String>("foo", i+"", "message" + i*1000));
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        /*Properties props = new Properties();
        props.put("zookeeper.connect", Constants.ZOOKEEPER_FAKE_CLUSTER_SERVER_PATH);
        props.put("group.id", "soul");
        props.put("zookeeper.session.timeout.ms", "5000");
        props.put("zookeeper.sync.time.ms", "2000");
        props.put("zookeeper.connection.timeout.ms", "10000");
        props.put("auto.commit.interval.ms", "400");
        props.put("rebalance.max.retries", "10");
        props.put("rebalance.backoff.ms", "2000");

        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(props));
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(TOPIC,new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = consumerMap.get(KafkaProducerDemo.TOPIC).get(0);
        ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
        while (iterator.hasNext()){
            System.out.println(new String(iterator.next().message()));
        }*/


        Properties props = getProperties();

        //创建kafka的生产者类
        Producer<String, String> producer = new Producer<String, String>(new ProducerConfig(props));
        //生产者的主要方法
        // close();//Close this producer.
        //   close(long timeout, TimeUnit timeUnit); //This method waits up to timeout for the producer to complete the sending of all incomplete requests.
        //  flush() ;所有缓存记录被立刻发送
        new KafkaProducerDemo(producer).start();
        countDownLatch.await();
        producer.close();
    }

    private static Properties getProperties() {
        Properties props = new Properties();
//        props.put("bootstrap.servers", Constants.KAFKA_FAKE_CLUSTER_SERVER_PATH);
//        props.put("metadata.broker.list", Constants.KAFKA_FAKE_CLUSTER_SERVER_PATH);
        //The "all" setting we have specified will result in blocking on the full commit of the record, the slowest but most durable setting.
        //“所有”设置将导致记录的完整提交阻塞，最慢的，但最持久的设置。
        props.put("acks", "all");
        //如果请求失败，生产者也会自动重试，即使设置成０ the producer can automatically retry.
        props.put("retries", 10);

        //The producer maintains buffers of unsent records for each partition.
        props.put("batch.size", 16384);
        //默认立即发送，这里这是延时毫秒数
        props.put("linger.ms", 1);
        props.put("producer.type","async");
        //生产者缓冲大小，当缓冲区耗尽后，额外的发送调用将被阻塞。时间超过max.block.ms将抛出TimeoutException
        props.put("buffer.memory", 33554432);
        //The key.serializer and value.serializer instruct how to turn the key and value objects the user provides with their ProducerRecord into bytes.
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        return props;
    }
}
