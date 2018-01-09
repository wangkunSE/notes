## 一 Netty的概念及体系结构

### 一 Netty--异步和事件驱动

#### 1.1 Java网络编程

>  一个连接需要一个线程。
>
>  - 大部分线程处于休眠状态的情况下十分浪费时间。
>  - 需要为每一个线程分配内存，默认值大小区间为64KB到1MB。
>  - 当线程达到一个量级时，上下文切换的开销会非常大。

##### 1.1.1 Java NIO

> 非阻塞IO

##### 1.1.2 选择器 Selector

> 一个单一的线程便可以处理多个并发的连接。
>
> - 使用较少的线程便可以处理许多连接。
> - 当没有I/O操作需要处理的时候，线程可以被用于其他任务。

#### 1.2 Netty简介

> 对Java NIO底层API的一层封装。

##### 1.2.1 谁在使用Netty

> Cassandra 、Vert.x 、Twitter

##### 1.2.2 异步和事件驱动

> - 异步方法会立即返回，并且在它完成时，会直接或者在稍后的某个时间点通知用户。
> - 选择器使得我们能通过较少的线程便可监视许多连接上的事件。

#### 1.3 Netty的核心组件

##### 1.3.1 Channel

> 一个到实体的开放连接，如读操作和写操作。

##### 1.3.2 回调

> 一个回调就是一个方法，一个指向被提供给另外一个方法的方法的引用。

##### 1.3.3 Future

> ChannelFuture

##### 1.3.4 事件和ChannelHandler

> Netty基于事件发生动作，ChannelHandler是Netty对这些事件处理器的抽象。同时Netty也实现了很多开箱即用的ChannelHandler。

##### 1.3.5 组合

> Netty的异步编程模型是建立在Future和回调的概念之上的。
>
> 在Netty内部，将会为每个Channel分配一个EventLoop，用来处理所有的事件，包括：
>
> - 注册感兴趣的事件
> - 将事件派发给ChannelHandler
> - 安排进一步的动作

### 二 Netty初体验

**Echo服务器与客户端**

> 虽然交互十分简单，但是其充分地体现了客户端/服务器系统中的典型请求-响应交互模式。

> **关于ChannelHandler的关键点：**
>
> - 针对不同类型的事件来调用ChannelHandler
> - 应用程序通过实现或者扩展ChannelHandler来挂钩到事件的生命周期，并且提供自定义的应用程序逻辑
> - 在架构上，ChannelHandler有助于保持业务逻辑与网络处理代码的分离。这简化了开发过程，因为代码必须不断地演化以响应不断变化的需求。

### 三 Netty的组件和设计

> netty实现了将程序逻辑从网络层解耦，同事保证了高负载下应用程序性能的最大化和可伸缩性。

#### 3.1 Channel、EventLoop、和ChannelFuture

> - Channel - Socket
> - EventLoop - 控制流、多线程处理、并发
> - ChannelFuture - 异步通知

##### 3.1.1 Channel接口

> - EmbeddedChannel；
> - LocalServerChannel；
> - NioDatagramChannel；
> - NioSctpChannel；
> - NioSocketChannel。

##### 3.1.2 EventLoop接口

> - 一个EventLoopGroup包含一个或者多个EventLoop
> - 一个EventLoop在它的生命周期内之和一个Thread进行绑定
> - 所有由EventLoop处理I/O事件都将在它专有的Thread上被处理
> - 一个Channel在它的生命周期内只注册于一个EventLoop
> - 一个EventLoop可能会被分配给一个或多个Channel

##### 3.1.3 ChannelFuture 接口

> ChannelFutureListenser ，使得在某个操作完成时得到通知

#### 3.2 ChannelHandler 和ChannelPipeline

##### 3.2.1 ChannelHandler 接口

> ChannelHandler充当了所有处理入站和出站数据的应用程序逻辑的容器。

##### 3.2.2 ChannelPipeline 接口

> ChannelPipeline提供了ChannelHandler链的容器，并定义了用于在该链上传播入站和出站事件流的API。

##### 3.3.3 深入理解ChannelHandler

>  ChannelHandlerAdapter
>  ChannelInboundHandlerAdapter
>  ChannelOutboundHandlerAdapter
>  ChannelDuplexHandler
>
> 适配器类可以将编写自定义的ChannelHandler所需要的努力降到最低。

##### 3.2.4 编码器和解码器

> 也是ChannelHandler的实现

#### 3.3 引导

> 服务端要多一个EventLoopGroup专门用来处理连接。

### 四 传输

#### 4.1 案例研究：传输迁移

> 略

#### 4.2 传输API

> Channel为传输的核心API。

#### 4.3 内置的传输

##### 4.3.1 NIO——非阻塞I/O

> **零拷贝**
> ​	零拷贝（zero-copy）是一种目前只有在使用NIO 和Epoll 传输时才可使用的特性。它使你可以快速高效地将数据从文件系统移动到网络接口，而不需要将其从内核空间复制到用户空间，其在像FTP 或者HTTP 这样的协议中可以显著地提升性能。但是，并不是所有的操作系统都支持这一特性。特别地，它对于实现了数据加密或者压缩的文件系统是不可用的——只能传输文件的原始内容。反过来说，传输已被加密的文件则不是问题。

**4.3.2 Epoll—用于Linux 的本地非阻塞传输**

> Linux作为高性能网络编程的平台，其重要性与日俱增，这催生了大量先进特性的开发，其
> 中包括epoll——一个高度可扩展的I/O事件通知特性。

**4.3.3 OIO—旧的阻塞I/O**

> 