## Kafka

### 一 消息队列

> 通讯模式：
>
> 1. 点对点通讯
> 2. 多点广播：MQ 将消息的一个复制版本和该系统上接收者的名单发送到目标 MQ 系统。目标 MQ 系统在本地复制这些消息，并将它们发送到名单上的队列，从而尽可能减少网络的传输量。
> 3. 发布/订阅 (Publish/Subscribe) 模式：使消息按照特定的主题甚至内容进行分发，用户或应用程序可以根据主题或内容接收到所需要的消息
> 4. 集群：群集内部的队列管理器之间通讯时，不需要两两之间建立消息通道，而是采用群集 (Cluster) 通道与其它成员通讯，从而大大简化了系统配置。

### 二 Apache Kafka原理

####  Kafka 专用术语

> Broker：Kafka 集群包含一个或多个服务器，这种服务器被称为 broker。
>
> Topic：每条发布到 Kafka 集群的消息都有一个类别，这个类别被称为 Topic。（物理上不同 Topic 的消息分开存储，逻辑上一个 Topic 的消息虽然保存于一个或多个 broker 上，但用户只需指定消息的 Topic 即可生产或消费数据而不必关心数据存于何处）。
>
> Partition：Partition 是物理上的概念，每个 Topic 包含一个或多个 Partition。
>
> Producer：负责发布消息到 Kafka broker。
>
> Consumer：消息消费者，向 Kafka broker 读取消息的客户端。
>
> Consumer Group：每个 Consumer 属于一个特定的 Consumer Group（可为每个 Consumer 指定 group name，若不指定 group name 则属于默认的 group）。

####  

bin/kafka-console-consumer.sh --topic TestTopic003 --from-beginning --bootstrap-server 192.168.241.152:9092,192.168.241.152:9093,192.168.241.152:9094 This is a message This is another message

bin/kafka-console-producer.sh --topic TestTopic003 --broker-list 192.168.241.152:9092,192.168.241.152:9093,192.168.241.152:9094 /
This is a message
This is another message



bin/kafka-topics.sh --describe --topic TestTopic003 --zookeeper 192.168.241.151:2181,192.168.241.151:2182,192.168.241.151:2183