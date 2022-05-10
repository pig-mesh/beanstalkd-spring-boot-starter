## ① 部署  beanstalkd 服务


```shell
docker run -d -p 11300:11300 schickling/beanstalkd
```


## ② 引入依赖

```shell
<dependency>
    <groupId>com.pig4cloud.beanstalk</groupId>
    <artifactId>beanstalkd-client-spring-boot-starter</artifactId>
    <version>0.0.2</version>
</dependency>
```

## ③ 代码调用

- 配置文件
- 
```properties
spring.beanstalkd.host=127.0.0.1
spring.beanstalkd.port=11300
```

- 消费方
```java
@Slf4j
@Configuration(proxyBeanMethods = false)
public class WorkConfig extends AbstractTubeConsumerListener {
    @Override
    public void work(JobConsumer consumer) {
        Job job = consumer.reserveJob(10);
        consumer.deleteJob(job.getId());
        log.info("任务内容{}", new String(job.getData()));
    }
}
```

- 提供方
```java
@Autowired
private JobProducer producer;

@Test
void contextLoads() {
    long jobId = producer.putJob(1, 10, 5, "hello job".getBytes(StandardCharsets.UTF_8));
}
```
