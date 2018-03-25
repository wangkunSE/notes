package com.soul.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @author WK
 * @version 2018/3/3
 */
public class AESUtils {

    public static String encodeContentWithAESBase64(String content, String key) throws Exception {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(key.getBytes()));
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] originKey = secretKey.getEncoded();
        SecretKey aes = new SecretKeySpec(originKey, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aes);
        byte[] contentBytes = content.getBytes("utf-8");
        byte[] secretContentBytes = cipher.doFinal(contentBytes);
        String encodeContent = new BASE64Encoder().encode(secretContentBytes);
        return encodeContent;
    }

    public static String decodeContentWithAESBase64(String encodedContent, String key) throws Exception {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(key.getBytes()));
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] originKey = secretKey.getEncoded();
        SecretKey aes = new SecretKeySpec(originKey, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aes);
        byte[] decodeBuffer = new BASE64Decoder().decodeBuffer(encodedContent);
        byte[] decodeBytes = cipher.doFinal(decodeBuffer);
        String content = new String(decodeBytes, "utf-8");
        return content;
    }

    public static void main(String[] args) throws Exception {
//        testOfIntegerStaticMethods();
       /* String contentWithAESBase64 = encodeContentWithAESBase64("题目：高可用分布式任务协调与执行系统设计与实现\n" +
                "英文：Design and Implementation of High Available Distributed Task Coordinate and Execute System\n" +
                "\n" +
                "摘要：\n" +
                "在当今互联网时代，人们享受着互联网带来的各种便利，同时互联网公司也在为提高服务质量做出各种努力以保证用户的良好体验，因此可以保持用户在其服务中的活性（通常分为日活跃用户数与月活跃用户数）。而用户分为多种多样的类型，服务提供者若想要对不同的目标用户做出不同的交互，就需要对所持有的用户信息做筛选，找出目标用户。如今的大型互联网企业多数都拥有千万及上亿的用户信息，采用传统的集中式任务处理已经无法满足如此庞大数据的计算能力，更不用提高效性。因此分布式系统是解决该类问题关键。\n" +
                "\t前文说到的筛选用户只是一种该类型问题的代表，对于这种数据量级别的任务处理是互联网公司发展不可避免的需要面对的一个重要问题。本文就将设计并实现一个基于ZooKeeper的分布式一致性，分布式服务事件监听特性的一个高可用分布式任务协调与执行系统以解决该类问题。所谓高可用即该系统中的部分主机结点宕机或失去响应该系统能够继续提供服务，完成任务处理。\n" +
                "\t该系统主要分为三个模块。第一个为任务分配及调度模块，该模块负责接收实时发起的任务，并将接收到的任务分配给系统中的任务执行模块。第二个模块为任务执行模块，该模块负责接收来自任务分配模块的任务，并将任务执行的结果汇报给任务分配模块。第三个模块为日志记录模块，接受来自前两个模块的任务日志，负责整个任务流程的日志接收与记录。\n" +
                "\n" +
                "选题依据：\n" +
                "（一）选题意义：\n" +
                "\t如今蓬勃发展的互联网公司都拥有一个十分庞大的用户基数，以及关于用户本身的衍生信息，Facebook活跃用户22亿，移动搜索月活跃用户达6.2亿；微信和WeChat的合并月活跃账户数达到6亿，QQ月活跃账户数达到8.43亿；2015年Q2百度贴吧拥有10亿用户，手机百度搜索活跃用户达6.29亿。对于该类信息的处理以及利用是互联网公司保持活跃用户必要以及十分重要的措施。一个互联网企业的成长伴随着使用用户的增加，所以用户数量对于一个互联网公司的意义不言而喻。对于如此庞大的用户信息的处理以及利用就是一个十分重要的问题。\n" +
                "\t因此，通过一个专门的任务处理系统执行相关任务是非常有必要的。而集中式的系统设计不论是计算能力还是扩展性都已然满足不了此类问题，分布式系统是必然的选择，笔者所在的公司也有同样的需求，并且通过设计实现一个高可用的分布式协调与执行系统解决类似的问题。笔者参与到了整个项目的过程中，涉及需求分析，架构设计，模块开发等环节。\n" +
                "\n" +
                "（二）国内外研究现状：\n" +
                "基于分布式系统的研究在当前大互联网时代一直都是热门话题，而其中的关于分布式数据一致性的协议的研究又是重中之重。而分布式一致性协议也是解决本文所研究问题的关键。\n" +
                "由lesile Lamport提出的Paxos很好的解决了分布式一致性问题，在Paxos算法中将参与结点分为提议者（proposal）,接受者（acceptor），学习者（learner），其中每一个角色都拥有自己的职能：提议者可以发起提议请求，接受者可以为提议者的提议进行投票并接受提议者的请求，学习者则可以接受经接受者通过的提议。该场景就模拟了分布式系统的网络通信条件。其使数据达成分布式一致性的过程分为两个阶段，分别为准备阶段，与接受阶段。详细过程将在后面章节介绍。\n" +
                "而本文所使用的分布式一致性开源框架Zookeeper所使用的分布式一致性协议为ZAB协议，该协议与Paxos有相似之处，但不尽相同，比如ZAB协议将参与结点区分为领导者（leader），跟随者（follower），观察者（observer）。虽然角色类似但职能不尽相同，在ZAB协议中只允许一个leader的存在，而Paxos可以允许多个proposaler存在等。\n" +
                "//对于类似系统的研究\n" +
                "\n" +
                "[1]谭玉靖. 基于ZooKeeper的分布式处理框架的研究与实现[D].北京邮电大学,2014.\n" +
                "[2]黄毅斐. 基于ZooKeeper的分布式同步框架设计与实现[D].浙江大学,2012.\n" +
                "[3]从Paxos到Zookeeper[M]. 电子工业出版社 , 倪超, 2015\n" +
                "[4]胡雪婧. 基于ZooKeeper的分布式系统的消息发送机制的设计与实现[D].吉林大学,2016.\n" +
                "[5]余科军. 分布式实时系统任务调度算法的设计和实现[D].四川大学,2006.\n" +
                "[6]Leslie Lamport. The part-time parliament[J]. ACM Transactions on Computer Systems (TOCS),1998,16(2).", "TheKeyForMyMasterDegreeArticle");
        System.out.println(contentWithAESBase64);*/
        String content = decodeContentWithAESBase64("3wj96amTaY8IGBEwEOJ2j4LGOw3OiOZEyztEzlJlsICy6cTf9YLfQs/hY48ShXsJ1kjCsS7pPdRr\n" +
                "elmsHnE1u+xim3DIp8HJetyGxk1CYpncFydyNlS9nAds0vxP5zjOvrdy64MJef1pUEA0SSnsc0j2\n" +
                "IHz2k5gcfOmX8zVa/rD+Lt09HJA2bRa9NAU7lzqbHgY6uJuvlKMJUbZUXvDtgMuSmnpUp4ofeljb\n" +
                "rBFGy+l5PSEpDGQsvJBVcLiwKoPp7cQzxVhmMJJ5OqX6nZOLZSvNrCGdprL+pE0n5lv7E6+3wmRP\n" +
                "2meNPn03WMy9Y6npZ4FhjnhLcpEAUWv7tlid53kxK/4P0e1KWz+m64l0Mpnve/X/r8BZdDE7apG7\n" +
                "sxveewnGbtdPHMEqMmpqf+nooo/lm9WaMFg/EdI72sfs3vlB3nZDwhfnobgRB/21cQfhrAl9lGtD\n" +
                "Mwbbhe3oI1ucUHUiP2F7JvciADXRcyd/RrWhSCr/t5jvqKToZY0obx+t+aD7vZl9shGVgIcJnJ9r\n" +
                "M0Q57EC8w6xBdPdqem0Ov2puKvHxBKJvULIu9dOrf0+RLPDJTm2jC9SE5HR85Gn9lelX9L89Up9T\n" +
                "q0S3wbUYMUr94vg8dBfFun6bn4N0GADI3+FW2/mvG08Jn4pVJUgDkjhLryH7t8JpWbN+ITvlAG89\n" +
                "3ZQeRl4tBbVijkxII8Db3P6ioDqoLA2+lSOs0fb45LPH5VCVN+lT0bFJ01kTgPurrXVWomiwc/jL\n" +
                "2/qvAQqp2TuuztTK9SwFxnM2y3+e9px+POHYu/MbsNGB4EsAkxmNt0/aZpyyykr4KWfTSB3/zrU4\n" +
                "+FcxyE8tIcuSqLh/K5ncyKEmI4p3TdZaXGTQsIb9vykxeZLjtRYW8Z4vbw587mVTaGA6FmC8fO6l\n" +
                "WTPBq4eBm5ar4C8QTdll1uav3QgfA0ha1gQ51qTan0HpqR8+rl2FSuuqMebBo/GRUnkYzMhHULfM\n" +
                "fC3v1STUtIsF/8WqqXhUrSxnUrVWoSpmKAz8Ps1C60K6IG6f2GljD+8hLyUdmiPcD9rYERKvDHML\n" +
                "qOhcDjy2KoRgML53vMe4VF0rekg/EhYvRf9IixFQvoEfQ7W3T85tOgXB93pElHi+GUdmNJHWmIO5\n" +
                "VSKggCctp+PKJ8sLX6Yn3U84K1zRKJdYXhvdqg4rtFC6cr302GSt+Us7xG50BMFYHmuCqS37K6Cc\n" +
                "fKgyCDELQYIFgvzT70eW0wEOCO7o0+h5qk0mxWUUUsuHwu6qq6lTBUs3COWmm+yXsB94n/T7PS2W\n" +
                "rivgv2+QQmx3LxbwtE1crPr3oYx99NhmQF2i2VwABHHvqQZ8ZXkLtKQXJW9pdhlomnDzeW3IkSfU\n" +
                "iiciOkEo2mo3A2orc84Z0MS0RNQTQEfKTa5RCAhxudgxxU2SgWFiMqtB1zQSLyQvLBmsSlKKgbms\n" +
                "REfpDrKmpv6Cdq6OIQGhx7KNcmHfLHtkeWcUAW8D25vbDjmdjGhAF+C5EQimkMS8Xt0x+uoNyuNp\n" +
                "bqmKA47eWWdFt8EUvoyTvFxTVJ3iVPVWCQiV77xyL51I0EFXgLCOtDnNTUihlZvgACNpjT60OYlo\n" +
                "FIT+7x4f+D10CWidW0VEybeuw5/dclox8K3tfvdtjo3eK2RgWCkgYsc+UePdeGhiex6JXGCNwM4Y\n" +
                "M58tcw5l8Jd+kHV42eaReirj33sSBgYn4FEEgebFyUoNVCV4TpQrhNAtEA6T3oKVkACoIRkUSX3U\n" +
                "OY2sluv0O7X01sYUscrN1KIOd9ah8UgV00tLmf0TCmjfqFRrZqG8XRbFidjKUP0pLB0hf251bi77\n" +
                "k2TW2bxU4G754HVHcDjpdZZhdWbOi2Th9b2nNWgtf4DMJ+X+LqrbBcgHfbKieMl5SEMvrByUuE9W\n" +
                "4FQmOsjc41RmO2zt8NXDAJnnutbfAiT5WsxJEj14KaGfDoUcbmOC9sx6IVmQXiPmCkgZj3Xzem17\n" +
                "zt+2gxgXA++0i+BDlM8noQQYzWC+67fmrB/e7QdbqWU4NuuwVaytKZlI36Ufp2zBeAwFt+5pSXCa\n" +
                "wwRBTUuNZTOatsdGuYMMiPZmCqSJC3R+yulB2pKnqfyG27nQGZb0+goWvrZ/61wE+Oi0zogamtgG\n" +
                "+up16b6VFmzBbpS9q32ryijggjRa0rKiimsT6SWKR7UqzJnlW3sjxwqOCDSizeEJpf6pKE0Hn/+U\n" +
                "EMLi0TJjITUxA16Xmtvm/4hjm22LRgPRolugL4KhEWTUG3IfMq4XrZZpP0BCwdhyde/hzsKMp3u5\n" +
                "5qMwV/mVUekREBEdhBGeJoaqnleeZAq1wkqL0BW98aTc/X5G+9j8cBkS6dHUXDMGGYUi2xMJQVZl\n" +
                "PyM1JjO0owgBrWES/q6LqUxCx/33MKQM9fSVavzfPU+vSx04XCEc/ZuFyU17nzSirsDqR4HobSzE\n" +
                "HjMDsT5iEuTLkZOmtGeVroTiDnfEluzFXILhX3sqIiwaYXNwiGYSU4ty9dD4i0Bz1aOnQfk5MhM9\n" +
                "9+66s1mvbPbPhd2V143MieMeeefet8fB01Q8mCV/PUKdUEdnE4RKl+S6L4wRZ/NjMuhtK5MOEmmc\n" +
                "6zZ0SvlJoQFh7qzXrlY7OsaDjcGgQ8FOl1jctJKDv610L33/Kx+fSvS7bEGERslIfFCE8n72wpr+\n" +
                "REOopp+76Iash/46MjoCPxN8INi0B1jrX0/8sH4nIJjkVixJ6ZyAHX0rYAg6iejSCTk7rdhGubdr\n" +
                "rZ4JIadUVa17J3eW+bA8CsR/L5B1t6CEJRBWZ/fXm858UnUy3QBTgyIX3cEv58HDHXsrJ72eS9A8\n" +
                "kn5sKC/HiIGNLPzbfeFE+wTQKLr5Bj7dDTeUsGam017LoAwY1U4GDGdYOnwLI+TSXifGq2Sz6Xhg\n" +
                "66huk6UGnoQdMXo0qJErvW1eG3B/i2hjASUH5mPC2H7CB5AptLOjcw040/Us1+X81rmHB3CknYgG\n" +
                "VeZTKqW5YjCKXtEIXdYjr4xNoiLNIfVSka3w2y6CrPQyQff8RcMv+zNXkKnDfppthCMVEP0Z5XTJ\n" +
                "nPnaMO/9iZjnX9bXiCzanJuq4GmPQ9x09QxWsgDGWmIcxqVYZBAuWMAf7BVazBCr7cGqigyCK/xW\n" +
                "Ppokk21utTY4lpZsFNuo+Awwwjk+O9Kz2lu23MIG/tNLJRD8P+yKnjUJRW+Tblm6xkQ6nCJ9E0OL\n" +
                "x38FPGgbFKb/T7PIuOwcnxzBxaDeW49i2fqDmlWPBerQHDkPnfmu4ntPCW42FywYA89/NNiSPdA4\n" +
                "KR0ULvbU4clue+fL+6ZgJQ/vjqjre8MgiLqmGZ2ZRMLUNWpA4iMIaRfJO6b447p11nP2LeQ84H+F\n" +
                "KPkBu4TEk5C/4kcfnpA51bPh0A2W4fsG6gkjztaKyorw5k04+MABTYNBtcjQnJV2060v7TlGPenW\n" +
                "4O0a+FD2VlS+3ZH2o5b/TnZHOct0EVz5rGIqCMcKl4b51v0zMtXZmT/052P0rtBF5WBVHjNdCbkN\n" +
                "AkS5SrW5sXme9jv4nyExc94NpEw8gWzT37Utvw0uRU/cb0hMx9W8NtJ/7lYeF4kSTnXFuhHZ1SJx\n" +
                "wHWi/qXmzJ6b2PVvAQtYuvSX9xF0m6worEQYXIjjT4kHt1TJB40PyUqluE4o5vTjw7/xFDT+e8RE\n" +
                "P3nxxYrrGnLPSsl1XM53HlUgOyW5mAZ1IigafFQGZUoox9m6HG+6HDNBu/aNrUvDc0TvQhM2oDv3\n" +
                "gLfFdmPMDnIpoMdUlDVG02/BJ7ScNh3ATavaNIJatWIb4XylOIkaEPQShR3UDGoa5KLJmkPigQX9\n" +
                "XG+ttcI5SA2u7o/EBiv7VwfUnud8SVhbnFrtpO1QeMS4CzmtMw3qQk1sNuN0wTP9IphjJuN3+2OB\n" +
                "baPAhZoh+VK5JGaUjxTBJEjWvFM3JTz8TQwig92B8HaxdMKRj7ezW6vbOTQd2sz8o/RNiPla4aDu\n" +
                "UVA7Xj1RUMtQq/TRuu7DExRJtTD3qx8So3g5iK4uxt91pyIZ+XQ2tO1bCVv+aaycujBQq/6TXzBd\n" +
                "46QZ1FKyBUqL5HWWrPSrAmjj5u1CCAWl0p1MuLiiQ+TW+ts0v0ceiOdpFYFKde6qrt50CmZzA94t\n" +
                "0R5CzATkebkBVyu1Ml74CMMgj8l6Vl3XRjTy9Adn3XKbSBKmYCdBJtgOvsozvUrH4XG6xTMsl0+i\n" +
                "tj6WAd+B7AkOSX9KyFs8VPd3CQC0Wp1MhfdHYzz140Y7ZR4tH97TNKeICn1cuHOPquOYm3Bc6ecJ\n" +
                "OAVg0aG6i8EqXwMcobQG6DSD4pWm/UHKhpktbNCOQLdLREoYjPWp3qyhaAfSsrvBZMCP8Ue2FGbx\n" +
                "hl4v6KKCv6dF3WJ2TaOBL+MBV85aMZi7tydgGR0dpeeBXxwVlNV4eNl7ezSgTQX8M1fA9qzT3bj9\n" +
                "dlikVYrPF8/NFtF3UEbJC7G79H/DTdfUxTJ2fJSHlLoACvhXB2WRdp/1AbhlUHU3QsJDzMH5SJ00\n" +
                "0xNIpvc7uoEGS8EqszSqb6zpLodIYiDYLPQj6fCNLkpeC5TFBDVGLqIT7mrovqCXEuC+spqxgK7g\n" +
                "LKpYIYcy1dyLaIqS5E58trgvhlleelvkxjbJSsv/U2iTuPV2KR0VtA3HOHXJ/74Qneo+qPqLcY3C\n" +
                "ykImVuFYakp6w1hyJrI1Ce0eznmfNdZj6sRkOrr4uXOoQPHjx6Cl16xNQZ5BqySyAKkvF9WCVBWB\n" +
                "KR+ySEyVsZRzj7gEllNUBo0ocg0X/QdM8+/7AWsvvtAtVZlRLErri06pjZqJdzXAn/hAs+x5vgJ9\n" +
                "6HoweikvMKUfiL/x1TjukX8033F7opnG2F5jI6uYDAvUfk8u5xFve+tVmqNHLuxuWwcq2KEOGuP1\n" +
                "zX807ihbnH6HEq2qjAFC3UApLYL3DdfS+mJ7zPjywl+FJz4KNn2SHR4eS5LjbVKb3IyYp76nd2JT\n" +
                "RmRYAd9f48OpC895Asj5vuVJrsgQlmP3Ze/Caar4zy+88dHEg7an0Da9fZQS2XVeCFFwoaDGdKU0\n" +
                "mC8/W6IxF5XLkIvac7UT4V7BGrcBva1VOU51g0tEmttGZqlDC8LmXmHZ3uH7w27/4uHrlexyXkG+\n" +
                "+s/+aq/xaHoMrN+L9AjluMQoAmadRVRkBkSC9o8nrcrhLtvaPgiKqRqnfiKlw6l0k57L5cyrrcqA\n" +
                "f7Pqyb8ip25ZyTBU/rK3XJyzuGs8C9HAIf6OhGqYSqwJ3az2Hj6yDyPaJFhjTaJm3MOA0IHKfP1C\n" +
                "gf7Lp7rDapjUdQR9B2d8Y+th90U7lVG3NtNMgeLOh70FW31hElIMSB/PN1PngpHfX2LPRVAjY8EC\n" +
                "jgBQpJnBfF9yRc3NzDHOCQo4yQ4wXwDQ+quU4nL2mD+RIcQRCBHB3z4dOfJ2yW48ZLGiljNC3n10\n" +
                "VIkWnz+AjuvuDGjvno0HSlWXVAFgD8SIE35gt3Kv2s4FwEn3XwsjtUXpK5pXL8/MY5RrlUZVECp9\n" +
                "YnuuFEe/s+Wh4bmyFYCbUir2AlWh/ulF1eNJutUrmMuNX/Djhbzvxv2mF8XELu4ObTN08EjLOiQV\n" +
                "Yd0YX/3G3prnJTg+J9oUasT8Y8oddfDi+3WyK8ta6mli4k7TqdsTL63tnXT89hFLpdNUtCLFUWRs\n" +
                "HCvLs0E1UAXXECRt7bcpNUxfzdWvuS5Mw3A6iUSarbjkJT7eIPmoHEk2ExdGS/IJL+qEUfTP0IhA\n" +
                "OnPzLUgZq1+wQvWF288lr06TF/FyCgUFArr7n8jpPsS38WCXkt14m/pNNEDZLY61YHIfxQus9jXr\n" +
                "X/bMzAkskcEuZx/m5kYTu+Wv5AAca3JxBNc2o/GQ6z7m23v1fpyArtbBu312whywAVY0OJVsU/0o\n" +
                "jsQe3nk6ln3tL0SQPLx7+5Goj28zRN0TXEpRiNVI/0F97DU3SMBPMePWfds+sCHChgaV9SEoJOtX\n" +
                "S4RKZPtXbMQPQpE+eyQdxEzvq/KdM+XiBnovonXwOrRxzKMoIMZ+G+ZXY1wWmwQUux+RIA4z+/Le\n" +
                "a+B8ANouTsDsydzhlphdic0KKHV4mp+8LXTV0T2/5obHjkewvcTfSGoO9UMuRb0S0teSRIeVQ7ZI\n" +
                "AqYimVEWM3tqD9KfpvWjl3mL1tjxjuI8aEi2r2QDMsE+yC8uTLjIB7+13iHDL5j8UuF0Si6YGO+l\n" +
                "V56mhezaRMUcXi6rIYervh6a+l6JrUVEIKHQZzBXpMSD8C7Ag1BQg4DLNQ7hORpqH9VUjfvaxFDB\n" +
                "BvuUoU0wrG4XGHQuzETbPrAhwoYGlfUhKCTrV0uEVFfKF/1cxZVOm/DvZQkBYifZmhhNa5uV74eq\n" +
                "+G4C4lGvVLd+KhWfxx1MXuW7MgOAj48VdI5SSLte3R1TbNvbHIw/xlXJATPjT1QB2nq+amznHp7+\n" +
                "pSr8MsYkqxngeL1SqqOl+Fv1uMZipz5ijL1DaxaV7zyE97dOfJ6WcEiLRoJtzlmBo/HYt0Ko8v00\n" +
                "gQ4yU+uBms9gNvi3IFG483OTLAzwIDoe+LzeUWEce1uJUixa9Mrvm8bl6KqaggRwqpv4Es05J0QH\n" +
                "vSZC0bzs3gxBCdAVV6R6bmwB9Cm8l0WRcLCiej4zzG1jtWYOq8sDVn1qOazv9wL1bJxCd119ChFt\n" +
                "X7KuGCarLii9r801Y0f5Kf8=", "TheKeyForMyMasterDegreeArticle");
        System.out.println(content);

    }

    private static void testOfIntegerStaticMethods(){
        Integer temp = 1;
        System.out.println(numberOfLeadingZeros(temp));
        System.out.println(Integer.numberOfTrailingZeros(temp));
        System.out.println( Integer.highestOneBit(temp));
        System.out.println(Integer.lowestOneBit(temp));
        System.out.println(Integer.toHexString(temp));
        System.out.println(Integer.toBinaryString(temp));
        System.out.println(Integer.toOctalString(temp));
        System.out.println(Integer.rotateLeft(temp,2));
        System.out.println(Integer.rotateRight(temp,2));
    }

    public static int numberOfLeadingZeros(int i) {
        if (i == 0)
            return 32;
        int n = 1;
        // 下面的代码就是定位从左边开始第一个非零值的位置，在定位过程中顺便累加从左边开始0的个数
        // 将i无符号右移16位后，有二种情况；
        //   情况1.i=0,则第一个非零值位于低16位，i至少有16个0，同时将i左移16位（把低16位移到原高16位的位置，这样情况1和情况2就能统一后续的判断方式）
        //   情况2.i!=0,则第一个非零值位于高16位，后续在高16位中继续判断
        // 这个思路就是二分查找，首先把32位的数分为高低16位，如果非零值位于高16位，后续再将高16位继续二分为高低8位，一直二分到集合中只有1个元素
        if (i >>> 16 == 0) { n += 16; i <<= 16; }
        // 判断第一个非零值是否位于高8位
        if (i >>> 24 == 0) { n +=  8; i <<=  8; }
        // 判断第一个非零值是否位于高4位
        if (i >>> 28 == 0) { n +=  4; i <<=  4; }
        // 判断第一个非零值是否位于高2位
        if (i >>> 30 == 0) { n +=  2; i <<=  2; }
        // 判断第一个非零值是否位于左边第一位
        n -= i >>> 31;
        return n;
    }

    public static String byteToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String strHex = Integer.toHexString(bytes[i]);
            if (strHex.length() > 3) {
                sb.append(strHex.substring(6));
            } else {
                if (strHex.length() < 2) {
                    sb.append("0" + strHex);
                } else {
                    sb.append(strHex);
                }
            }
        }
        return sb.toString();
    }
}
