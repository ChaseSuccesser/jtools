package com.ligx.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("redis")
public class RedisProperties {
    /**
     * Database index used by the connection factory.
     */
    private int database = 0;

    /**
     * Redis url, which will overrule host, port and password if set.
     */
    private String url;

    /**
     * Redis server host.
     */
    private String host = "localhost";

    /**
     * Login password of the redis server.
     */
    private String password;

    /**
     * Redis server port.
     */
    private int port = 6379;

    /**
     * Enable SSL.
     */
    private boolean ssl = false;

    /**
     * Connection timeout in milliseconds.
     */
    private int timeout = 2000;

    private Pool pool = new Pool();


    public int getDatabase() {
        return this.database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isSsl() {
        return this.ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public Pool getPool() {
        return this.pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }


    public static class Pool {

        /**
         * Max number of "idle" connections in the pool. Use a negative value to indicate
         * an unlimited number of idle connections.
         */
        private int maxIdle = 8;

        /**
         * Target for the minimum number of idle connections to maintain in the pool. This
         * setting only has an effect if it is positive.
         */
        private int minIdle = 0;

        /**
         * Max number of connections that can be allocated by the pool at a given time.
         * Use a negative value for no limit.
         */
        private int maxActive = 8;

        /**
         * Maximum amount of time (in milliseconds) a connection allocation should block
         * before throwing an exception when the pool is exhausted. Use a negative value
         * to block indefinitely.
         */
        private int maxWait = -1;

        public int getMaxIdle() {
            return this.maxIdle;
        }

        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }

        public int getMinIdle() {
            return this.minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public int getMaxActive() {
            return this.maxActive;
        }

        public void setMaxActive(int maxActive) {
            this.maxActive = maxActive;
        }

        public int getMaxWait() {
            return this.maxWait;
        }

        public void setMaxWait(int maxWait) {
            this.maxWait = maxWait;
        }

    }

}
