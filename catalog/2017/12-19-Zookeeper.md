### 一 分布式架构

#### 1.1 从集中式到分布式

##### 1.1.1 集中式的特点

> 部署简单，价格昂贵，性能卓越，单点问题（一旦大型主机出现故障，整个系统将处于不可用状态）

##### 1.1.2 分布式的特点

> 分布性：分布式系统计算机在物理空间上随意分布，同时，机器的分布情况也随时变动。
>
> 对等性：没有主从之分。多用副本。
>
> 并发性：多点操作
>
> 缺乏全局时钟：无法明确的确定事件顺序。
>
> 故障总会发生：网络及机器的不稳定性。

##### 1.1.3 分布式环境的各种问题

> 通信异常：网络时延
>
> 网络分区：当一部分节点宕机时出现的部分工作区域
>
> 三态：成功、失败、超时（通信双方都有可能出现）
>
> 节点故障：服务器节点出现宕机或僵死状态

#### 1.2 从ACID到CAP/BASE

##### 1.2.1 ACID

> 事务：对数据进行访问与更新的操作所组成的一个程序执行逻辑单元。
>
> 一致性：尚未完成的事务对数据有更改的操作会破坏数据库的一致性。
>
> 持久性：事务成功对数据库的修改是永久的，不管数据库在什么样的状态都可以恢复到事务成功结束时的状态。

##### 1.2.2 分布式事务

> 分布式事务是指事务的参与者、支持事务的服务器、资源服务器以及事务管理器分别位于分布式系统的不同节点之上。
>
> 一个分布式事务可以看做是由多个分布式的操作序列组成的。如转账服务中的取款服务和存款服务。

##### 1.2.3 CAP和BASE理论

> 起因：可用性和一致性的冲突。
>
> CAP： 
>
> - 一致性：各个节点拥有同一数据视图
> - 可用性：对于用户的每一个操作请求总是能够在有限的时间内返回结果。友好的回答，尽可能快速的回复。
> - 分区容错性：任何网络分区发生故障不影响分布式系统提供的服务。（妻子以任何形式不工作了，记录本无法同步）
>
> BASE：
>
> - Basically Available(基本可用)、Soft state(软状态)、Eventually consistent(最终一致性)。

### 二 一致性协议

#### 2.1 2PC与3PC

##### 2.1.1 2PC

> 阶段一：提交事务请求（由协调者发出），参与者记录undo和redo的信息。
>
> 阶段二：根据参与者反馈执行事务提交、中断事务。
>
> 特点：原理简单，实现方便，同步阻塞，单点问题（协调者出现问题，系统无法使用，还会导致数据不一致），容错机制不完善。

##### 2.1.2 3PC

> 分为：CanCommit，PreCommit，DoCommit阶段。比2PC多加了一个PreCommit阶段。
>
> 进入阶段三后如果出现这两种故障：
>
> - 协调者出现问题
> - 协调者和参与者之间出现网络故障
>
> 参与者在等待超时后就会进行事务提交。
>
> 优点：降低阻塞范围，解决单点问题。
>
> 缺点：数据不一致。

#### 2.2 Paxos算法

##### 2.2.1 追本溯源

> 拜占庭将军问题
>
> Paxos决议问题

##### 2.2.2 Paxos理论的诞生

> Leslie Lamport

##### 2.2.3 Paxos算法详解

> 问题描述：
>
> > - 在这些被提出的提案中，只有一个会被选定。
> > - 如果没有提案提出，那么就不会有被选定的提案。
> > - 当一个提案被选定后，进程应该可以获取被选定的提案信息。
> >
> > 安全性需求：
> >
> > - 只有被提出的提案能被选定。
> > - 只能有一个值被选定。
> > - 如果某个进程认为某个提案被选定了，那么这个提案必须是真的被选定的那个。
> >
> > 总结：在参与者会和通信状况都会出现问题的系统中，想确定一个唯一被提出的提案并交给参与者执行。
>
> 推导过程：
>
> > P1：一个Acceptor必须批准它收到的第一个提案。
> >
> > P2：假设某个提案[M0，V0]已经被选定了，证明任何编号Mn>M0的提案，其Value值都是V0.

**提案生成算法**：

> 1. Proposer 选择一个新的提案编号Mn，然后向某个Acceptor集合的成员发送请求，要求该集合中的Acceptor做出如下回应。
>    - 向Proposer承诺，保证不再批准任何编号小于Mn的提案
>    - 若Acceptor已经批准过任何提案，那么其就向Proposer反馈当前该Acceptor接受的编号小于Mn但为最大编号的那个提案值。
> 2. 如果Proposer收到了来自半数以上的Acceptor的响应结果，那么它就可以产生编号为Mn、Value值为Vn的提案。这里的Vn是所有响应中编号最大的提案的Value值。当然还存在另一种情况：还没有任何提议，这时该Proposer可以提出任何提议。

**Acceptor批准提案：**

> - Prepare请求：Acceptor可以在任何时候响应一个Prepare请求
> - Accept请求：在不违背Accept现有承诺的前提下，可以任意响应Accept请求。

**算法陈述：**

*阶段一：*

> 1. Proposer选择一个提案编号Mn，然后向Acceptor的某个超过半数的子集成员发送编号为Mn的Prepare请求。
> 2. 如果一个Acceptor收到编号为Mn的Prepare请求，该Acceptor会将已经批准过的最大编号的提案反馈给Proposer，同时该Acceptor会承诺不会再批准任何编号小于Mn的提案。

*阶段二：*

> 1. 如果Proposer收到来自半数以上的Acceptor对于其发出的编号为Mn的Prepare请求的响应，那么它就会发送一个针对[Mn，Vn]提案的Accept请求给Acceptor。Vn就是收到响应中编号最大的提案的值，如果响应中不包含任何提案，它可以是任意值。
> 2. 如果Acceptor收到这个针对[Mn，Vn]提案的Accept请求，只要该Acceptor尚未对编号大于Mn的Prepare请求做出响应，它就可以通过这个提案。（只被贿赂过<=Mn的钱）

### 三 Paxos工程实践

#### 3.1 Chubby

##### 3.1.1 概述

> Chubby是一个面向松耦合分布式系统的锁服务。

##### 3.1.2 应用场景

> Master选举，定位Master服务器，存储元数据。

##### 3.1.3 设计目标

> 相比提供算法库的优点：
>
> - 对上层应用程序的侵入性小
> - 便于提供数据的发布与订阅
> - 开发人员对基于锁的接口更为熟悉
> - 更便捷地构建更可靠的服务
>
> 设计目标：
>
> - 提供一个完整的、独立的分布式锁服务，而不仅仅是一个一致性协议客户端库
> - 提供粗粒度的锁服务
> - 在提供锁服务的同时提供对小文件的读写功能
> - 高可用、高可靠
> - 提供事件通知机制

##### 3.1.4 Chubby技术架构

> 元数据中包含的4个单调递增的64位编号：
>
> - 实例编号：标识Chubby创建该数据节点的顺序。
> - 文件内容编号（只针对文件）：用于标识文件内容的变化情况，该编号会在文件内容被写入时增加。
> - 锁编号：当节点锁从free状态到held状态时增加。
> - ACL编号：该编号会在节点的ACL配置信息被写入时增加。

**锁延迟与锁序列器**

> 锁延迟：因为网络原因则Chubby为原客户端保留一段时间锁。
>
> 锁序列器：锁名称，锁模式，锁序号。客户端在请求时携带，服务端丢弃检验不通过的锁请求。

**Chubby中的事件通知机制**

> - 文件内容变更
> - 节点删除
> - 子节点新增、删除
> - Master服务器转移

**会话和会话激活**

> 客户端会在建立连接后发送一个KeepAlive请求，服务端会在会话租期时间内阻塞该请求。直到到达租期时间向客户端返回响应信息。当客户端处于危险状态（过了本地的会话租期但是还是没有收到服务端的keepAlive响应时），会清空本地缓存，并且会等待一个宽限期（45S）。Chubby Master故障恢复时同理，只是每一轮选举都会保留一个Master周期编号。

##### 3.1.5 Paxos 协议实现

> 日志副本的一致性原理：
>
> 每一个Paxos Instance都需要进行一轮或者多轮Prepare→Promise→Propose→Accept这样独立的二阶段请求完成对一个提案值的选定。
>
> - 当某副本节点通过选举成为Master后，就会使用新分配的编号N来广播一个Prepare消息。
> - 当Acceptor接收到Prepare消息后，必须对多个Instance同时做出回应（通过封装到一个数据包实现）。
> - 然后拥有提案编号N的Master就可以进行下一个阶段Propose→Accept。若Master发现Acceptor返回了一个Reject消息，说明集群存在另一个Master，且试图使用更大的提案编号（比如M，M>N）发送prepare消息。这时当前Master就需要重新分配新的提案编号（要比M大）并再次进行Prepare→Promise阶段。
>
> 在每个Instance的运行过程中，一旦接收到多数派的Accept反馈后，就可以将对应的提案值写入本地事务日志并广播COMMIT消息给集群中的其他副本节点。

#### 3.2 Hypertable

##### 3.2.1 概述

> 相比传统关系型数据库的优势：
>
> - 支持对大量并发请求的处理
> - 支持对海量数据的管理
> - 扩展性良好，在保证可用性的前提下，能够通过随意添加集群中的机器来实现水平扩容。
> - 可用性极高，具有非常好的容错性。

**架构**

> Hyperspace(等同于Chubby的作用)，Master(记录元数据)，RangeServer(对外提供服务的组件单元，负责数据的读取和写入)，DFS Broker(底层分布式文件系统的抽象层，完成对文件系统的读写操作)

##### 3.2.2 算法实现

> BDB Hypertable中的Acceptor。

### 四 ZooKeeper 与Paxos

> ZooKeeper并没有直接采用Paxos算法，而是采用了一种被称为ZAB（ZooKeeper Atomic Broadcast）的一致性协议。

#### 4.1 初识ZooKeeper

##### 4.1.1 ZooKeeper介绍

> ZooKeeper可以保证如下分布式一致性特性：
>
> - 顺序一致性
> - 原子性
> - 单一视图
> - 可靠性
> - 实时性
>
> ZooKeeper的设计目标：
>
> - 简单的数据模型（存储在内存中的由ZNode数据节点组成的类似于文件结构的数据模型）
> - 可以构建集群
> - 顺序访问
> - 高性能

##### 4.1.2 ZooKeeper来历

> 雅虎研究院

##### 4.1.3 ZooKeeper的基本概念

> 集群角色：Leader（读写），Follower（读，参与选举leader），Observer（读）
>
> 数据节点（Znode）：永久节点，临时节点（客户端会话期），SEQUENTIAL（名称自增属性）
>
> 版本：version(当前ZNode的版本)，cversion（当前ZNode子节点的版本），aversion（当前ZNode的ACL版本）
>
> ACL(Access Control Lists)：权限控制
>
> - CREATE、READ、WRITE、DELETE、ADMIN(设置节点ACL的权限)

##### 4.1.4 为什么选择ZooKeeper

> 成熟，开源，免费。

#### 4.2 ZooKeeper的ZAB协议

##### 4.2.1 ZAB协议

> leader将客户端请求转换成一个事务Proposal，并将该Proposal分发给Follower服务器，若超过半数的Follower服务器进行了正确的反馈，Leader将向所有的Follower发送Commit信息。

##### 4.2.2 协议介绍

> 恢复模式：集群初启动，leader宕机，由于网络原因与过半Follower失去联系。
>
> 消息广播：ZXID(事务ID，全局唯一单调递增)
>
> 崩溃恢复：
>
> - ZAB协议要确保在leader崩溃前发出的事务提交命令在所有的服务器上都被提交成功。
> - ZAB协议需要确保丢弃那些只在Leader服务器上被提出的事务
>
> 数据同步：ZXID（低32位是Proposal自增的一个计数器，高32位代表了Leader周期的epoch编号，当更换leader周期时会从中解析出epoch编号并加一）

##### 4.2.3 深入ZAB协议

**系统模型**

> $$
> \land  \forall Q, Q\subseteq \prod
> $$
>
> $$
> \land \forall Q_1 和 Q_2,Q_1\bigcap Q_2 \neq \emptyset
> $$
>
> 完整性：若进程Pj收到来自进程Pi的消息m，则Pi一定发送了消息m
>
> 前置性：若消息m的前置消息为m‘，那么Pj一定先收到m’再收到m。

**问题描述**

> 选举机制和消息广播机制相当重要。

**主进程周期**

> ready(e（当前的主进程周期）)函数，告诉进程充分完成了崩溃恢复阶段。

**事务**

> transactions(v(事务内容),z(事务标识)< e(主进程周期),c(主进程周期内的事务计数) >)。
>
> epoch(z)：事务标识中的主进程周期epoch，counter(z)：事务标识中的事务计数。

**算法描述：**

> ZAB协议两过程：消息广播和崩溃恢复。（Discovery，Synchronization，Broadcast）
>
> **发现：**
>
> 1. Follower F将自己最后接受的事务Proposal的Proposal的epoch值发送给准Leader L。
> 2. 当收到来自过半Follower的CEPOCH(Fp)消息后，准Leader L会生成NEWEPOCH(e')消息给这些过半的Follower。该epoch的值e‘为Follower返回消息的最大的epoch值加一。
> 3. Follower检查自身的CEPOCH(Fp)值是否小于e’，若小于就会将该值赋值为e‘，并向Leader发送Ack消息。
>
> **同步：**
>
> 1. Leader L会将e'和Ie’（准Leader的hf（历史事务集合））发送给所有Quorum中的Follower。
> 2. Follower接收到Leader L的信息后，对比e'和CEPOCH（Fp（Follower f处理过的最后一个事务Proposal））的值，若不同，则不参与本轮同步，若相同，就会将Ie‘中的事务接受。
> 3. Leader接受到来自过半Follower的反馈信息后，就会向所有Follower发送Commit信息。
> 4. Follower收到信息后提交。
>
> **广播：**
>
> 1. Leader L收到客户端的新事务请求后，生成Proposal并根据ZXID的顺序向所有Follower发送提案。
> 2. Follower根据顺序处理Proposal。并追加到hf中，并反馈给Leader。
> 3. 当Leader收到过半的反馈时就会让所有的Follower提交事务。
> 4. Follower收到信息后就会提交事务，但当前事务的前置事务必定已经提交。

##### 4.2.4 ZAB与Paxos算法的联系与区别

> 主要区别在于设计目标：
>
> ZAB协议：构建一个高可用的分布式数据主备系统
>
> Paxos算法：构建一个分布式一致性状态机系统。

### 五 使用ZooKeeper

#### 5.1 部署与运行

> 集群配置文件
>
> zoo.cfg
>
> ```properties
> # The number of milliseconds of each tick
> tickTime=2000
> # The number of ticks that the initial 
> # synchronization phase can take
> initLimit=10
> # The number of ticks that can pass between 
> # sending a request and getting an acknowledgement
> syncLimit=5
> # the directory where the snapshot is stored.
> # do not use /tmp for storage, /tmp here is just 
> # example sakes.
> dataDir=/var/lib/zookeeper/
> # the port at which the clients will connect
> clientPort=2181
> # the maximum number of client connections.
> # increase this if you need to handle more clients
> #maxClientCnxns=60
> #
> # Be sure to read the maintenance section of the 
> # administrator guide before turning on autopurge.
> #
> # http://zookeeper.apache.org/doc/current/zookeeperAdmin.html#sc_maintenance
> #
> # The number of snapshots to retain in dataDir
> #autopurge.snapRetainCount=3
> # Purge task interval in hours
> # Set to "0" to disable auto purge feature
> #autopurge.purgeInterval=1
> server.1=192.168.241.152:2888:3888
> server.2=192.168.241.153:2888:3888
> server.3=192.168.241.154:2888:3888
> ```

##### 5.4.2 Curator

**典型使用场景**

> **事件监听**
>
> **分布式锁**
>
> **栅栏**
>
> **Master选举**
>
> 具体见代码

### 六 ZooKeeper的典型应用场景

#### 6.1 典型应用场景及实现

##### 6.1.1 数据发布/订阅

> 即配置中心，发布者将数据发布到ZooKeeper的一个或一系列节点上，供订阅者进行数据订阅，进而达到动态获取数据的目的，实现配置信息的集中式管理和数据的动态更新。
>
> ZooKeeper采用的是推拉结合的方式获取最新数据。

##### 6.1.2 负载均衡

> 服务注册与发布

##### 6.1.3 命名服务

> 统一管理名称，类似JNDI

##### 6.1.4 分布式协调/通知

> Mysql集群主备复制框架

##### 6.1.5 集群管理

> 分布式日志收集系统
>
> - 节点类型
>
>   > ——|logs
>   >
>   > ————|collector	永久节点
>   >
>   > ——————|host1
>   >
>   > ——————|host1
>   >
>   > ——————|...
>   >
>   > ——————|hostN
>   >
>   > ————————|status  用来记录收集器状态
>
> - 日志系统节点监听:收集器状态非常多，所以不适合监听状态。
>
> 在线云主机管理

##### 6.1.6 Master 选举

> Master动态选举，快速恢复利用ZooKeeper有非常好的实现。
>
> 只是选举的话利用一个强一致性的系统就可以完成。

##### 6.1.7 分布式锁

> 绝大多数大型分布式系统的性能瓶颈都集中在数据库操作上，因此锁的操作最好能够分离出来。
>
> 排他锁：仅供当前事务操作
>
> 共享锁：当前事务只可以进行读操作

##### 6.1.8 分布式队列

> FIFO：同共享锁实现类似。思路：所有的客户端都会到/queue_fifo节点下注册一个临时顺序结点，例如/queue_fifo/192.168.0.1-00000000000001。
>
> > ——|/queue_fifo
> >
> > ————|host1-00000000001
> >
> > ————|host2-00000000002
> >
> > ————|host4-00000000004
>
> - 通过调用getChildren()接口来获取/queue_fifo结点下的所有子节点，即获取队列中所有的元素
> - 确定自己的节点序号在所有子节点中的顺序
> - 如果自己不是序号最小的子节点，就要进入等待，同时向比自己序号小的最后一个结点注册Watcher监听
> - 接收到Watcher通知后，重复步骤1
>
> Barrier：分布式屏障
>
> 场景：并行计算结果的汇总

#### 6.2 ZooKeeper在大型分布式系统中的应用

##### 6.2.1 Hadoop

**YARN介绍**

> YARN是hadoop为了提高计算节点Master（JT）的扩展性，同时为了支持多计算模型和提供资源的细粒度调度而引入的全新一代分布式调度框架。

**ResourceManager HA**

> 为了解决单点问题，YARN设计了一套Active/Standby模式的架构。

**Fencing（隔离）**

> 脑裂：有多个指挥者
>
> 对需要竞争的数据节点加ACL权限控制机制来实现不同RM之间的隔离。

##### 6.2.2 HBase

> 全称Hadoop Database，是Google Bigtable的开源实现，是一个基于HDFS设计的面向海量数据的高可靠性、高性能、面向列、可伸缩的分布式存储系统。

**系统冗错**

> 当HBase启动时，每个RegionServer服务器都会到ZooKeeper的/hbase/rs节点下创建一个信息节点。
>
> 之所以不用传统的TCP心跳连接机制，是因为如果那样，HMaster的管理负担会越来越重，另外它自身也有挂掉的可能。

**RootRegion 管理**

> 对于HBase集群来说，数据存储的位置信息是记录在元数据分片，也就是RootRegion上。每次客户端发起新的请求，就需要知道数据的位置，就会去查询RootRegion。

**Region 状态管理**

> Region 是HBase中数据的物理切片，每个Region中记录了全局数据的一小部分，并且不同的Region之间的数据是相互不重复的。

**分布式SplitLog任务管理**

> 多个RegionServer服务器处理HLog的恢复工作的协助，任务分配与状态更改。

**Replication管理**

> HBase借助ZooKeeper来完成Replication功能。通过在ZooKeeper上记录一个replication结点。然后把不同的RegionServer服务器对应的HLog文件名称记录到相应的节点上，HMaster集群会将新增的数据推送到Slave集群。

#### 6.2.3 Kafka

**术语介绍**

> - Producer：消息生产者，生成消息并发送到Kafka服务器上
> - Consumer：消息消费者，消费Kafka服务器上的消息
> - Topic：主题，由用户定义，用于建立消费者和生产者之间的订阅关系。
> - Partition：一个Topic下面会分为很多个分区，消息分区机制和分区的数量和消费者的负载均衡机制有很大的关系。
> - Broker：Kafka服务器，用于存储消息。
> - Group：消费者分组，用于归组同类的消费者。组中消费者可以消费部分Topic消息，组间消费者消费全部的Topic消息。
> - Offset：消息存储在Kafka的Broker上，消费者拉取消息数据的过程中需要知道消息在文件中的偏移量，这个偏移量就是所谓的Offset。

**Broker注册**

> 在Kafka的设计中，选择了使用ZooKeeper来进行所有的Broker的管理。每个Broker启动后将会在/brokers/ids/n下将自己的IP地址和端口信息写入节点中。

##### Topic注册

> 在ZooKeeper的/brokers/topics/[topic]节点下。存储了类似下面的数据
>
> ```json 
> {"version":1,"partitions":{"2":[2,0,1],"1":[1,2,0],"0":[0,1,2]}}
> ```

**生产者负载均衡**

> **四层负载均衡**
>
> > 根据生产者的IP地址和端口唯一确定一个相关联的Broker。
> >
> > 好处：
> >
> > - 逻辑简单
> > - 不需额外的TCP连接
> >
> > 缺点：
> >
> > - 无法做到真正的负载均衡
> > - 一方面每个生产者的消息生产量不同
> > - 另一方面生产者无法感觉到Broker的变化
>
> **使用ZooKeeper进行负载均衡**
>
> > 生产者对ZooKeeper上的Broker、Topic、以及它们之间关联的变化注册Watcher，这样就可以实现一种动态的负载均衡机制。

**消费者的负载均衡**

> **消费分区与消费者的关系**
>
> > 每个消费者对应一个分区进行消费
>
> **消息消费进度Offset记录**
>
> > 记录已消费的记录
>
> **消费者注册**
>
> > 所谓负载均衡，是指为了能够让同一个Topic下不同分区的消息尽量均衡地被多个消费者消费而进行的一个消费者与消费分区分配的过程。

**负载均衡**

> 具体看官方文档

### 6.3 ZooKeeper在阿里巴巴的实践与应用

#### 6.3.1 消息中间件：Metamorphosis

> 具有消息存储顺序写、吞吐量大和支持本地XA事务等特性，适用于大吞吐量、顺序消息、消息广播和日志数据传输等分布式应用场景。

##### 消费者的负载均衡

> 消费者数 = Topic分区数时
>
> 一对一消费
>
> 消费者数 >Topic分区数时
>
> 单个分组中多出来的消费者不消费
>
> 消费者数n<Topic分区数时m
>
> 部分消费者额外承担消息的消费任务（n%m）

##### 消息消费位点Offset存储

> 记录到ZooKeeper中

#### 6.3.2 RPC服务框架：Dubbo

**Dubbo的核心部分**

> **远程通信**：提供多种基于长连接的NIO框架的抽象封装。以及“请求-响应”模式的信息交换方式。
>
> **集群容错**：提供基于接口方法的远程过程透明调用，包括对多协议的支持。软负载均衡、失败容错、地址路由和动态配置等集群特性。
>
> **自动发现**：提供基于注册中心的目录服务。

**服务注册中心**：

> /dubbo：根节点
>
> /dubbo/com.foo.BarService：服务节点，代表Dubbo的一个服务
>
> /dubbo/com.foo.BarService/providers：服务提供者的根节点，子节点为真正的提供者。
>
> /dubbo/com.foo.BarService/consumers：服务消费者的根节点，子节点为真正的消费者。

#### 6.3.3 基于MySQL Binlog的增量订阅和消费组件

> 主备切换
>
> 假死保护：网络闪断造成临时节点失效。

#### 6.3.4 分布式数据库同步系统:Otter

> 分布式数据库同步系统

**分布式SEDA（Staged Event-Driven Architecture）模型调度**

> Otter同步流程的ETL处理模型
>
> - Select：数据接入
> - Extract：数据提取
> - Transform：数据转换
> - Load：数据载入

**Stage管理**

> 主要就是维护一组工作线程，在接收到Schedule的Event后分配线程处理任务并反馈信息到Schedule。

**Schedule调度**

> 1. 创建节点
> 2. 任务分配
> 3. 任务通知
> 4. 任务完成

#### 6.3.5 轻量级分布式通用搜索平台：终搜

#### 6.3.6 实时计算引擎：JStorm