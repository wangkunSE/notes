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

> 分为：CanCommit，PreCommit，DoCommit阶段。比2PC多加了一个CanCommit阶段。
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
> 2. 如果Proposer收到了来自半数以上的Acceptor的响应结果，那么它就可以产生编号为Mn、Value值为Vn的提案。这里的Vn是所有响应中编号最大的提案的Value值。当然还存在另一种情况：还没有任何提议，这是该Proposer可以提出任何提议。

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
> - 在提供锁服务的同事提供对小文件的读写功能
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
> 锁序列器：锁名称，锁模式，锁序号。客户端在请求时携带，服务端丢弃检验不通过的锁。

**Chubby中的事件通知机制**

> - 文件内容变更
> - 节点删除
> - 子节点新增、删除
> - Master服务器转移

**会话和会话激活**

> 客户端会在建立连接后发送一个KeepAlive请求，服务端会在会话租期时间内阻塞该请求。直到到达租期时间向客户端返回响应信息。当客户端处于危险状态（过了本地的会话租期但是还是没有收到服务端的keepAlive响应时），会清空本地缓存，并且会等待一个宽限期（45S）。Chubby Master故障恢复时同理，只是每一轮选举都会保留一个Master周期编号。