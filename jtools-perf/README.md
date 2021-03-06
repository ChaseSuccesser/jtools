# 如何使用
1.添加依赖
```
<dependency>
    <groupId>com.ligx</groupId>
    <artifactId>jtools-perf</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

2.classpath路径下添加配置文件monitor.properties
```
# 应用名称(必填)
appName=jtools-demo
# 方法执行时间最大阈值，单位ms，默认10000 (可选)
maxExecutionTimeThreshold=10000
# 配置备份Recorders的数量，默认为3，最小为1，最大为8 (可选)
backupRecordersCount=5
# 配置时间片，单位为ms，默认60s (可选)
millTimeSlice=5000
# 使用什么方式处理MethodMetrics，默认log (可选)
metricsProcessor=log
```

3.在想要监控的方法上添加`@Profiling`注解。

4.添加`@ComponentScan(basePackages = {"com.ligx"})`

---

## 如果`methodMetricsProcessor`属性指定为`log`，则直接在日志打印出如下信息：
```$xslt
Method Metrics [2018-09-28 16:30:22,2018-09-28 16:30:27]
                           Method[1]      QPS  Avg(ms)  Min(ms)  Max(ms)    Count      TP50     TP90     TP95     TP99    TP999   TP9999  TP99999    TP100
ProfilingTestService.profilingTestV2        0      886      886      886         1      886      886      886      886      886      886      886      886
```

---

## 如果`methodMetricsProcessor`属性指定为`influxdb`，则需要额外增加配置：
1.monitor.properties
```$xslt
metricsProcessor=influxdb
influxdbUrl=http://127.0.0.1:8086
influxdbUserName=
influxdbPassword=
```

2.安装启动InfluxDB

3.安装启动Grafana   
>import [method metrics dashboard json](https://github.com/ChaseSuccesser/jtools/blob/609e3cdeafa8a5b100eefe1f7772dfd798a4a1d5/jtools-perf/src/main/resources/Avg_Max_Min_TP_QPS.json)

>修改标签Table的值：标签值为monitor.properties配置文件中的appName属性值(如果有中划线，则替换成下划线)。

![](https://github.com/ChaseSuccesser/jtools/blob/777bce36c0c430d5a9273b527e01b67e4a165fb6/jtools-perf/src/main/resources/pic1.png)
![](https://github.com/ChaseSuccesser/jtools/blob/777bce36c0c430d5a9273b527e01b67e4a165fb6/jtools-perf/src/main/resources/pic2.png)

## Jvm内存监控

>import [jvm metrics dashboard json](https://github.com/ChaseSuccesser/jtools/blob/c5cf8049ca69da7f2a7bee7341c1b51bac2930ae/jtools-perf/src/main/resources/jvm_memory_metrics.json)

![](https://github.com/ChaseSuccesser/jtools/blob/cdfe41389abfca46a2f55da3b8be77d05f9ef817/jtools-perf/src/main/resources/pic3.png)
![](https://github.com/ChaseSuccesser/jtools/blob/cdfe41389abfca46a2f55da3b8be77d05f9ef817/jtools-perf/src/main/resources/pic4.png)
![](https://github.com/ChaseSuccesser/jtools/blob/cdfe41389abfca46a2f55da3b8be77d05f9ef817/jtools-perf/src/main/resources/pic5.png)

## Jvm GC监控

>import [jvm gc dashboard json](https://github.com/ChaseSuccesser/jtools/blob/c5cf8049ca69da7f2a7bee7341c1b51bac2930ae/jtools-perf/src/main/resources/jvm_gc_metrics.json)

## 参考
https://github.com/ThinkpadNC5/MyPerf4J