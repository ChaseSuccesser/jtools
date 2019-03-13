package com.ligx.conf;

import com.ligx.MetaHolder;
import com.ligx.annotation.HystrixProperty;
import com.ligx.annotation.Resilience;
import com.netflix.hystrix.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: ligongxing.
 * Date: 2019/03/13.
 */
public class HystrixPropertiesManager {

    /**
     * Command properties
     */
    // 采样周期
    public static final String METRICS_HEALTH_SNAPSHOT_INTERVAL_IN_MILLISECONDS = "metricsHealthSnapshotIntervalInMilliseconds";  // todo 统一改成带点的名字
    public static final String METRICS_ROLLING_STATISTICAL_WINDOW_IN_MILLISECONDS = "metricsRollingStatisticalWindowInMilliseconds";
    // 熔断器配置
    public static final String CIRCUIT_BREAKER_ENABLED = "circuitBreakerEnabled";
    public static final String CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD = "circuitBreakerRequestVolumeThreshold";
    public static final String CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE = "circuitBreakerErrorThresholdPercentage";
    public static final String CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS = "circuitBreakerSleepWindowInMilliseconds";
    public static final String CIRCUIT_BREAKER_FORCE_OPEN = "circuitBreakerSleepWindowInMilliseconds";
    public static final String CIRCUIT_BREAKER_FORCE_CLOSED = "circuitBreakerSleepWindowInMilliseconds";
    // 降级配置
    public static final String FALLBACK_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS = "fallbackIsolationSemaphoreMaxConcurrentRequests";
    // 隔离配置
    public static final String EXECUTION_ISOLATION_STRATEGY = "executionIsolationStrategy";
    public static final String EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_TIMEOUT = "executionIsolationThreadInterruptOnTimeout";
    public static final String EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_FUTURE_CANCEL = "executionIsolationThreadInterruptOnFutureCancel";
    public static final String EXECUTION_TIMEOUT_IN_MILLISECONDS = "executionTimeoutInMilliseconds";


    /**
     * Thread pool properties.
     */
    public static final String CORE_SIZE = "coreSize";
    public static final String MAXIMUM_SIZE = "maximumSize";
    public static final String ALLOW_MAXIMUM_SIZE_TO_DIVERGE_FROM_CORE_SIZE = "allowMaximumSizeToDivergeFromCoreSize";
    public static final String KEEP_ALIVE_TIME_MINUTES = "keepAliveTimeMinutes";
    public static final String MAX_QUEUE_SIZE = "maxQueueSize";
    public static final String QUEUE_SIZE_REJECTION_THRESHOLD = "queueSizeRejectionThreshold";
    public static final String METRICS_ROLLING_STATS_NUM_BUCKETS = "metrics.rollingStats.numBuckets";
    public static final String METRICS_ROLLING_STATS_TIME_IN_MILLISECONDS = "metrics.rollingStats.timeInMilliseconds";


    private static Map<String, PropSetter<HystrixCommandProperties.Setter, String>> COMMAND_PROP_MAP = new HashMap<String, PropSetter<HystrixCommandProperties.Setter, String>>() {{
        put(METRICS_HEALTH_SNAPSHOT_INTERVAL_IN_MILLISECONDS, new PropSetter<HystrixCommandProperties.Setter, String>() {
            @Override
            public void set(HystrixCommandProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withMetricsHealthSnapshotIntervalInMilliseconds(toInt(METRICS_HEALTH_SNAPSHOT_INTERVAL_IN_MILLISECONDS, value));
            }
        });
        put(METRICS_ROLLING_STATISTICAL_WINDOW_IN_MILLISECONDS, new PropSetter<HystrixCommandProperties.Setter, String>() {
            @Override
            public void set(HystrixCommandProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withMetricsRollingStatisticalWindowInMilliseconds(toInt(METRICS_ROLLING_STATISTICAL_WINDOW_IN_MILLISECONDS, value));
            }
        });
        put(CIRCUIT_BREAKER_ENABLED, new PropSetter<HystrixCommandProperties.Setter, String>() {
            @Override
            public void set(HystrixCommandProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withCircuitBreakerEnabled(toBoolean(value));
            }
        });
        put(CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, new PropSetter<HystrixCommandProperties.Setter, String>() {
            @Override
            public void set(HystrixCommandProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withCircuitBreakerRequestVolumeThreshold(toInt(CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value));
            }
        });
        put(CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, new PropSetter<HystrixCommandProperties.Setter, String>() {
            @Override
            public void set(HystrixCommandProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withCircuitBreakerErrorThresholdPercentage(toInt(CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value));
            }
        });
        put(CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, new PropSetter<HystrixCommandProperties.Setter, String>() {
            @Override
            public void set(HystrixCommandProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withCircuitBreakerSleepWindowInMilliseconds(toInt(CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value));
            }
        });
        put(CIRCUIT_BREAKER_FORCE_OPEN, new PropSetter<HystrixCommandProperties.Setter, String>() {
            @Override
            public void set(HystrixCommandProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withCircuitBreakerForceOpen(toBoolean(value));
            }
        });
        put(CIRCUIT_BREAKER_FORCE_CLOSED, new PropSetter<HystrixCommandProperties.Setter, String>() {
            @Override
            public void set(HystrixCommandProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withCircuitBreakerForceClosed(toBoolean(value));
            }
        });

        put(FALLBACK_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS, new PropSetter<HystrixCommandProperties.Setter, String>() {
            @Override
            public void set(HystrixCommandProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withFallbackIsolationSemaphoreMaxConcurrentRequests(toInt(FALLBACK_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS, value));
            }
        });
        put(EXECUTION_ISOLATION_STRATEGY, new PropSetter<HystrixCommandProperties.Setter, String>() {
            @Override
            public void set(HystrixCommandProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withExecutionIsolationStrategy(toEnum(EXECUTION_ISOLATION_STRATEGY, value, HystrixCommandProperties.ExecutionIsolationStrategy.class,
                        HystrixCommandProperties.ExecutionIsolationStrategy.values()));
            }
        });
        put(EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_TIMEOUT, new PropSetter<HystrixCommandProperties.Setter, String>() {
            @Override
            public void set(HystrixCommandProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withExecutionIsolationThreadInterruptOnTimeout(toBoolean(value));
            }
        });
        put(EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_FUTURE_CANCEL, new PropSetter<HystrixCommandProperties.Setter, String>() {
            @Override
            public void set(HystrixCommandProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withExecutionIsolationThreadInterruptOnFutureCancel(toBoolean(value));
            }
        });
        put(EXECUTION_TIMEOUT_IN_MILLISECONDS, new PropSetter<HystrixCommandProperties.Setter, String>() {
            @Override
            public void set(HystrixCommandProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withExecutionTimeoutInMilliseconds(toInt(EXECUTION_TIMEOUT_IN_MILLISECONDS, value));
            }
        });
    }};


    private static Map<String, PropSetter<HystrixThreadPoolProperties.Setter, String>> THREAD_POOL_PROP_MAP = new HashMap<String, PropSetter<HystrixThreadPoolProperties.Setter, String>>() {{
        put(CORE_SIZE, new PropSetter<HystrixThreadPoolProperties.Setter, String>() {
            @Override
            public void set(HystrixThreadPoolProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withCoreSize(toInt(CORE_SIZE, value));
            }
        });
        put(MAXIMUM_SIZE, new PropSetter<HystrixThreadPoolProperties.Setter, String>() {
            @Override
            public void set(HystrixThreadPoolProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withMaximumSize(toInt(MAXIMUM_SIZE, value));
            }
        });
        put(ALLOW_MAXIMUM_SIZE_TO_DIVERGE_FROM_CORE_SIZE, new PropSetter<HystrixThreadPoolProperties.Setter, String>() {
            @Override
            public void set(HystrixThreadPoolProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withAllowMaximumSizeToDivergeFromCoreSize(toBoolean(value));
            }
        });
        put(MAX_QUEUE_SIZE, new PropSetter<HystrixThreadPoolProperties.Setter, String>() {
            @Override
            public void set(HystrixThreadPoolProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withMaxQueueSize(toInt(MAX_QUEUE_SIZE, value));
            }
        });
        put(QUEUE_SIZE_REJECTION_THRESHOLD, new PropSetter<HystrixThreadPoolProperties.Setter, String>() {
            @Override
            public void set(HystrixThreadPoolProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withQueueSizeRejectionThreshold(toInt(QUEUE_SIZE_REJECTION_THRESHOLD, value));
            }
        });
        put(KEEP_ALIVE_TIME_MINUTES, new PropSetter<HystrixThreadPoolProperties.Setter, String>() {
            @Override
            public void set(HystrixThreadPoolProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withKeepAliveTimeMinutes(toInt(KEEP_ALIVE_TIME_MINUTES, value));
            }
        });
        put(METRICS_ROLLING_STATS_NUM_BUCKETS, new PropSetter<HystrixThreadPoolProperties.Setter, String>() {
            @Override
            public void set(HystrixThreadPoolProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withMetricsRollingStatisticalWindowBuckets(toInt(METRICS_ROLLING_STATS_NUM_BUCKETS, value));
            }
        });
        put(METRICS_ROLLING_STATS_TIME_IN_MILLISECONDS, new PropSetter<HystrixThreadPoolProperties.Setter, String>() {
            @Override
            public void set(HystrixThreadPoolProperties.Setter setter, String value) throws IllegalArgumentException {
                setter.withMetricsRollingStatisticalWindowInMilliseconds(toInt(METRICS_ROLLING_STATS_TIME_IN_MILLISECONDS, value));
            }
        });
    }};

    private static int toInt(String propName, String propValue) throws IllegalArgumentException {
        try {
            return Integer.parseInt(propValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("bad property value. property name '" + propName + "'. Expected int value, actual = " + propValue);
        }
    }

    private static boolean toBoolean(String propValue) {
        return Boolean.valueOf(propValue);
    }

    private static <E extends Enum<E>> E toEnum(String propName, String propValue, Class<E> enumType, E... values) throws IllegalArgumentException {
        try {
            return Enum.valueOf(enumType, propValue);
        } catch (NullPointerException npe) {
            throw createBadEnumError(propName, propValue, values);
        } catch (IllegalArgumentException e) {
            throw createBadEnumError(propName, propValue, values);
        }
    }

    private static IllegalArgumentException createBadEnumError(String propName, String propValue, Enum... values) {
        throw new IllegalArgumentException("bad property value. property name '" + propName + "'. Expected correct enum value, one of the [" + Arrays.toString(values) + "] , actual = " + propValue);
    }


    public static HystrixCommandProperties.Setter initializeCommandProperties(List<HystrixProperty> properties) throws IllegalArgumentException {
        return initializeProperties(HystrixCommandProperties.Setter(), properties, COMMAND_PROP_MAP, "command");
    }

    private static HystrixThreadPoolProperties.Setter initializeThreadPoolProperties(List<HystrixProperty> properties) {
        return initializeProperties(HystrixThreadPoolProperties.Setter(), properties, THREAD_POOL_PROP_MAP, "thread pool");
    }

    private static <S> S initializeProperties(S setter, List<HystrixProperty> properties, Map<String, PropSetter<S, String>> propMap, String type) throws IllegalArgumentException {
        if (properties != null && properties.size() > 0) {
            for (HystrixProperty property : properties) {
                if (!propMap.containsKey(property.name())) {
                    throw new IllegalArgumentException("unknown " + type + " property: " + property.name());
                }
                propMap.get(property.name()).set(setter, property.value());
            }
        }
        return setter;
    }

    public static HystrixCommand.Setter setter(MetaHolder metaHolder) throws IllegalArgumentException {
        HystrixCommand.Setter setter = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(metaHolder.getGroupKey()))
                .andCommandKey(HystrixCommandKey.Factory.asKey(metaHolder.getCommandKey()))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(metaHolder.getThreadPoolKey()));

        Resilience resilience = metaHolder.getResilience();
        setter.andThreadPoolPropertiesDefaults(initializeThreadPoolProperties(Arrays.asList(resilience.threadPoolProperties())));
        setter.andCommandPropertiesDefaults(initializeCommandProperties(Arrays.asList(resilience.commandProperties())));
        return setter;
    }
}
