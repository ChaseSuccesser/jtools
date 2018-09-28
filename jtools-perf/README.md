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
methodMetricsProcessor=influxdb
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
1.配置logger (以log4j示例)
```$xslt
    <appenders>
        <RollingRandomAccessFile name="metricsAppender"
                                 fileName="D:/log/jtools-demo/metrics.log" filePattern="D:/log/jtools-demo/history/metrics-%d{yyyy-MM-dd}.log">
            <PatternLayout pattern="%m%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" modulate="true" />
            </Policies>
            <DefaultRolloverStrategy max="30" />
        </RollingRandomAccessFile>
    </appenders>
    <loggers>
        <!--logger的name必须为metrics_logger-->
        <AsyncLogger level="INFO" name="metrics_logger" additivity="false">
            <appender-ref ref="metricsAppender"/>
        </AsyncLogger>
    </loggers>
```

2.安装配置Telegraf  
```$xslt
# Global tags can be specified here in key="value" format.
[global_tags]
  dc = ""

# Configuration for telegraf agent
[agent]
  interval = "1s"
  round_interval = true
  metric_batch_size = 1000
  metric_buffer_limit = 10000
  collection_jitter = "0s"
  flush_interval = "1s"
  flush_jitter = "0s"
  precision = "ms"
  debug = false
  quiet = false
  logfile = "/tmp/telegraf.log"
  hostname = ""
  omit_hostname = false

[[outputs.influxdb]]
  urls = ["http://127.0.0.1:8086"]
  database = "MyPerf"
  retention_policy = ""
  write_consistency = "any"
  timeout = "5s"

[[inputs.tail]]
  # 必须与上面logger打印的日志文件路径相同
  files = ["D:/log/jtools-demo/metrics.log"]
  from_beginning = false
  pipe = false
  watch_method = "poll"
  data_format = "influx"
```

3.安装启动InfluxDB
略

4.安装启动Grafana   
>import [dashboard json](https://github.com/ChaseSuccesser/jtools/blob/609e3cdeafa8a5b100eefe1f7772dfd798a4a1d5/jtools-perf/src/main/resources/Avg_Max_Min_TP_QPS.json):

>修改Grafana Variables：$TableName为monitor.properties配置文件中的appName属性值(如果有中划线，则替换成下划线)。