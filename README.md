# dingtalk-spring-boot-starter

钉钉群 机器人消息推送 封装

官方文档地址：https://open.dingtalk.com/document/group/custom-robot-access

- 支持类型如下
> 文本 (text)
>
> 链接 (link)
>
> markdown(markdown)
>
> ActionCard
>
> FeedCard

## 使用

### 默认机器人配置

无论单机器人还是多机器人，都必须配置

~~~yaml
coctrl:
  ding-talk:
    access-token: d4a24b6ea884d5d3ec245fb95e9cdbc3630
    secret: SEC8cc743fc2286f5209f1162d72131f47515843fc0cea661152bd
~~~

### 单机器人使用

~~~java
    @Autowired
    private IDingTalkSender talkSender;

    talkSender.send(TextArgs.builder().content("自定义封装测试").atMobiles(Lists.newArrayList("133xxxx7608")).build());

    talkSender.send(MarkdownArgs.builder().title("markdown测试")
        .text("### andon通知\n - 工位： station\n - 故障： content")
        .build());

    talkSender.send(LinkArgs.builder().title("link").text("这是一条link消息")
        .picUrl("https://help-static-aliyun-doc.aliyuncs.com/assets/img/zh-CN/9977602461/p352641.png")
        .messageUrl("https://open.dingtalk.com/document/group/robot-overview")
        .pcSlide(true)
        .build());

    talkSender.send(ActionCardWholeArgs.builder()
        .singleTitle("测试")
        .text("消息内容")
        .title("ActionCard")
        .singleUrl("https://open.dingtalk.com/document/group/robot-overview").build());

    talkSender.send(ActionCardAloneArgs.builder()
        .btnOrientation(BtnOrientation.VERTICAL)
        .btns(Lists.newArrayList(
            CardButtonArgs.builder().title("测试1").actionUrl("https://open.dingtalk.com").pcSlide(true).build(),
            CardButtonArgs.builder().title("测试2").actionUrl("https://open.dingtalk.com").pcSlide(true).build(),
            CardButtonArgs.builder().title("测试2").actionUrl("https://open.dingtalk.com").pcSlide(true).build()
        ))
        .title("消息标题").text("消息内容").build());

    talkSender.send(FeedCardArgs.builder()
        .links(Lists.newArrayList(
            CardLinksArgs.builder().title("测试1").messageUrl("https://open.dingtalk.com").pcSlide(true)
                .picUrl("https://img.alicdn.com/imgextra/i4/O1CN01oWsqXJ1VSf8Bj1DVs_!!6000000002652-2-tps-588-200.png").build(),
            CardLinksArgs.builder().title("测试2").messageUrl("https://open.dingtalk.com").pcSlide(true)
                .picUrl("https://img.alicdn.com/imgextra/i4/O1CN01oWsqXJ1VSf8Bj1DVs_!!6000000002652-2-tps-588-200.png").build(),
            CardLinksArgs.builder().title("测试3").messageUrl("https://open.dingtalk.com").pcSlide(true)
                .picUrl("https://img.alicdn.com/imgextra/i4/O1CN01oWsqXJ1VSf8Bj1DVs_!!6000000002652-2-tps-588-200.png").build()
        ))
        .build());

~~~

### 多机器人使用

多机器人使用时，需配置对应的机器人群组信息

这里采用ApplicationRunner，项目启动完成，查询数据库并机器人信息添加到配置

~~~java
@Component
public class RobotConfig implements ApplicationRunner {
    @Autowired
    private IMultipleRobot multipleRobot;
    
    //这里可以注入数据库查询service进行查询配置

    @Override
    public void run(ApplicationArguments args) throws Exception {
        multipleRobot.addGroupRobot(
                GroupArgs.builder()
                        .groupId("111")
                        .dingTalkArgs(Lists.newArrayList(
                                DingTalkArgs.builder().accessToken("d4a24b6ea884d5261ac4ec719ee30bb65638fc6e880d3ec245fb95e9cdbc3630")
                                        .secret("SEC8cc743fc2286f5209f1162d7230b13a0bff18d131f47515843fc0cea661152bd").build(),
                                DingTalkArgs.builder().accessToken("421b36a2e9a41f6e3103ea4114efe665df6458982526ca46f87f389753cdf5df")
                                        .secret("SEC3f1ed3e72a0582c40d27aac1c52cb3a85d1cd3b8bd0710bd04be3de3fe0a2d6d").build(),
                                DingTalkArgs.builder().accessToken("15a2dba9253dd92b815a2fac923b42ef1622c8ae0c3d8e9d7cfe3ce5e2ee1549")
                                        .secret("SEC475ecf41acaa4f1dab5074ba61e7d32f06e20d2e277307d967c05d4d50e52d69").build()
                        ))
                        //轮询算法处理消息发送
                        .algorithmHandler(new RoundRobinHandler())
                        .build()
        );
    }
}
~~~

调用与单机器人一样，注入IDingTalkSender即可，发送消息时指定群组ID即可

~~~java
    @Autowired
    private IDingTalkSender talkSender;

        talkSender.send(TextArgs.builder().content("群组机器人测试1").build(), "111");
        talkSender.send(TextArgs.builder().content("群组机器人测试2").build(), "111");
        talkSender.send(TextArgs.builder().content("群组机器人测试3").build(), "111");
        talkSender.send(TextArgs.builder().content("群组机器人测试4").build(), "111");
~~~