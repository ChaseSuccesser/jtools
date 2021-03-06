package com.ligx.http.httpclient;

import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpAgent {
    private HttpConfig config;
    private PoolingHttpClientConnectionManager connManager;

    private HttpAgent() {
    }

    private void setConfiguration(HttpConfig configuration) {
        this.config = configuration;
    }

    private void setConnManager(PoolingHttpClientConnectionManager connManager) {
        this.connManager = connManager;
    }

    public PoolingHttpClientConnectionManager getConnManager() {
        return connManager;
    }

    public static HttpAgent create() {
        HttpConfig config = new HttpConfig();
        HttpAgent agent = new HttpAgent();
        agent.setConfiguration(config);
        agent.setConnManager(createConnectionManager(config));
        return agent;
    }

    public static HttpAgent create(HttpConfig config) {
        HttpAgent agent = new HttpAgent();
        agent.setConfiguration(config);
        agent.setConnManager(createConnectionManager(config));
        return agent;
    }

    /////////////////////////////// get //////////////////////////////
    /**
     * 提交get请求
     *
     * @param url
     * @return
     */
    public String doGet(String url) throws Exception {
        HttpClient client = buildClient();
        return client.execute(new HttpGet(url), new BasicResponseHandler());
    }


    /**
     * 提交get请求，接受Map参数
     *
     * @param url
     * @param params
     * @return
     */
    public String doGet(String url, Map<String, String> params) throws Exception {
        HttpClient client = buildClient();
        return doGet(client, url, params, null);
    }

    /**
     * 提交get请求，带有请求头
     *
     * @param url     请求路径
     * @param headers 请求头
     * @return
     * @throws Exception
     */
    public String doGetWithHeader(String url, Map<String, String> headers) throws Exception {
        HttpClient client = buildClient();
        return doGet(client, url, null, headers);
    }

    /**
     * 提交get请求，接受Map参数，带有请求头
     *
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws Exception
     */
    public String doGetWithHeader(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
        HttpClient client = buildClient();
        return doGet(client, url, params, headers);
    }

    private String doGet(HttpClient client, String urlPath, Map<String, String> params, Map<String, String> headers) throws Exception {
        HttpGet request = new HttpGet(urlPath);

        // 设置请求头
        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }

        // 设置请求参数
        List<NameValuePair> parameters = new ArrayList<>();
        if(MapUtils.isNotEmpty(params)){
            for (Map.Entry<String, String> param : params.entrySet()) {
                parameters.add(new BasicNameValuePair(param.getKey(), param.getValue()));
            }
        }

        URI uri = new URIBuilder(request.getURI()).addParameters(parameters).build();
        request.setURI(uri);

        String response = client.execute(request, new EncodingResponseHandler("UTF_8"));
        return response;
    }


    /////////////////////////////// post json //////////////////////////////
    /**
     * 提交Json形式的Post请求
     */
    public String doPost(String urlPath, String content) throws IOException {
        HttpClient client = buildClient();
        return doPost(client, urlPath, content, "UTF-8", null);
    }

    /**
     * 提交Json形式的Post请求，自己指定编码
     */
    public String doPost(String urlPath, String content, String encoding) throws IOException {
        HttpClient client = buildClient();
        return doPost(client, urlPath, content, encoding, null);
    }

    /**
     * 提交Json形式的Post请求，包含请求头
     */
    public String doPost(String urlPath, String content, Map<String, String> headers) throws IOException {
        HttpClient client = buildClient();
        return doPost(client, urlPath, content, "UTF-8", headers);
    }

    /**
     * 提交Json形式的Post请求，自己指定编码，包含请求头
     */
    public String doPost(String urlPath, String content, String encoding, Map<String, String> headers) throws IOException {
        HttpClient client = buildClient();
        return doPost(client, urlPath, content, encoding, headers);
    }

    private String doPost(HttpClient client, String url, String content, String encoding, Map<String, String> headers) throws IOException {
        HttpPost post = new HttpPost(url);
        if (headers != null) {
            for (Map.Entry<String, String> kv : headers.entrySet()) {
                post.setHeader(kv.getKey(), kv.getValue());
            }
        }
        post.setEntity(new ByteArrayEntity(content.getBytes(encoding)));
        String response = client.execute(post, new EncodingResponseHandler(encoding));
        return response;
    }

    /////////////////////////////// post byte array //////////////////////////////
    /**
     * 提交post请求，内容为字节数组
     */
    public byte[] doPost(String urlPath, byte[] contentBytes, Map<String, String> headers) throws IOException {
        HttpClient client = buildClient();
        HttpPost post = new HttpPost(urlPath);
        if (headers != null) {
            for (Map.Entry<String, String> kv : headers.entrySet()) {
                post.setHeader(kv.getKey(), kv.getValue());
            }
        }
        post.setEntity(new ByteArrayEntity(contentBytes));
        byte[] response = client.execute(post, new ByteArrayResponseHandler());
        return response;
    }

    /////////////////////////////// post form //////////////////////////////
    /**
     * 提交Form表单形式的Post请求，默认UTF-8编码
     */
    public String doPost(String urlPath, Map<String, String> params) throws Exception {
        return doPost(urlPath, params, "UTF-8");
    }

    /**
     * 提交Form表单形式的Post请求，自己指定编码
     */
    public String doPost(String urlPath, Map<String, String> params, String encoding) throws Exception {
        HttpClient client = buildClient();
        return doPost(client, urlPath, params, encoding);
    }

    private String doPost(HttpClient client, String urlPath, Map<String, String> params, String encoding) throws Exception {
        List<NameValuePair> parameters = new ArrayList<>(params.size());
        for (Map.Entry<String, String> param : params.entrySet()) {
            parameters.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }
        HttpEntity entity = new UrlEncodedFormEntity(parameters, Charset.forName(encoding));

        RequestBuilder builder = RequestBuilder.post().setUri(new URI(urlPath));
        builder.setEntity(entity);
        HttpUriRequest request = builder.build();

        String response = client.execute(request, new EncodingResponseHandler(encoding));
        return response;
    }


    //////////////////////////////////////////////////////////////////
    /**
     * Http客户端构造
     *
     * @return
     */
    private HttpClient buildClient() {
        HttpClientBuilder builder = HttpClientBuilder.create().setConnectionManager(connManager);

        //设置UA
        builder.setUserAgent(config.getUserAgent());

        //设置请求参数
        RequestConfig.Builder rcBuilder = RequestConfig.custom();
        rcBuilder.setConnectTimeout(config.getConnectionTimeout());
        rcBuilder.setStaleConnectionCheckEnabled(Boolean.FALSE);
        rcBuilder.setSocketTimeout(config.getTimeout());
        builder.setDefaultRequestConfig(rcBuilder.build());

        //设置proxy
        if (config.isUseProxy()) {
            builder.setProxy(new HttpHost(config.getProxyHost(), config.getProxyPort()));
        }

        //连接复用和keep-alive设置
        final long keepAlive = config.getKeepAlive();
        if (keepAlive == 0) {
            builder.setConnectionReuseStrategy(new NoConnectionReuseStrategy());
        } else {
            builder.setConnectionReuseStrategy(new DefaultConnectionReuseStrategy());
            builder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy() {
                @Override
                public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                    final long duration = super.getKeepAliveDuration(response, context);
                    return (duration == -1) ? keepAlive : duration;
                }
            });
        }

        //重试机制
        builder.setRetryHandler(config.getRetryHandler());

        //认证机制
        if (config.getCredentialsProvider() != null) {
            builder.setDefaultCredentialsProvider(config.getCredentialsProvider());
        }

        return builder.build();
    }

    /**
     * 创建http连接池，配置最大连接数和每个Route的最大连接数
     *
     * @return
     */
    private static PoolingHttpClientConnectionManager createConnectionManager(HttpConfig configuration) {
        int ttl = configuration.getTimeToLive();

        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(ttl, TimeUnit.MILLISECONDS);
        manager.setDefaultMaxPerRoute(configuration.getMaxConnectionsPerRoute());
        manager.setMaxTotal(configuration.getMaxConnections());

        //socket设置
        SocketConfig.Builder builder = SocketConfig.custom();
        builder.setSoTimeout(configuration.getTimeout());
        builder.setTcpNoDelay(Boolean.TRUE);
        manager.setDefaultSocketConfig(builder.build());

        return manager;
    }
}
