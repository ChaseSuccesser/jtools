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
mostTimeThreshold=10000
# 配置备份Recorders的数量，默认为3，最小为1，最大为8 (可选)
backupRecordersCount=5
# 配置时间片，单位为ms，默认60s (可选)
millTimeSlice=5000
# 使用什么方式处理MethodMetrics，默认log (可选)
methodMetricsProcessor=log
```

3.在想要监控的方法上添加`@Profiling`注解。

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
methodMetricsProcessor=influxdb
influxdbUrl=http://127.0.0.1:8086
influxdbUserName=
influxdbPassword=
```

2.安装启动InfluxDB
略

3.安装启动Grafana   
>import [dashboard json](https://github.com/ChaseSuccesser/jtools/blob/609e3cdeafa8a5b100eefe1f7772dfd798a4a1d5/jtools-perf/src/main/resources/Avg_Max_Min_TP_QPS.json):

>修改Grafana Variables：$TableName为monitor.properties配置文件中的appName属性值(如果有中划线，则替换成下划线)。