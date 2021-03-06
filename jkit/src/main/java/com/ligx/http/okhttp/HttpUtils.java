package com.ligx.http.okhttp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.squareup.okhttp.*;
import okio.Buffer;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Author: ligongxing.
 * Date: 2018/07/12.
 */
public class HttpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    public static Optional<String> get(String url, Map<String, Object> params, int timeout, TimeUnit timeUnit) {
        if (StringUtils.isBlank(url)) {
            return Optional.empty();
        }

        String paramPath = "";
        if (MapUtils.isNotEmpty(params)) {
            List<String> paramList = params.entrySet().stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.toList());
            paramPath = Joiner.on("&").skipNulls().join(paramList);
        }
        if (StringUtils.isNotBlank(paramPath)) {
            url = url + "?" + paramPath;
        }

        Request request = new Request.Builder()
                .url(url)
                .build();
        return doRequest(url, request, timeout, timeUnit);
    }

    public static Optional<String> postWithJson(String url, Map<String, Object> params, int timeout, TimeUnit timeUnit) {
        if (StringUtils.isBlank(url) || MapUtils.isEmpty(params)) {
            LOGGER.warn("HttpUtils#postWithJson, invalid args! url={}, params={}", url, JSON.toJSONString(params));
            return Optional.empty();
        }

        String jsonStr = JSON.toJSONString(params);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, jsonStr);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return doRequest(url, request, timeout, timeUnit);
    }

    public static Optional<String> postWithJson(String url, String json, int timeout, TimeUnit timeUnit) {
        if (StringUtils.isBlank(url) || StringUtils.isBlank(json)) {
            LOGGER.warn("HttpUtils#postWithJson, invalid args! url={}, json={}", url, json);
            return Optional.empty();
        }

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, json);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return doRequest(url, request, timeout, timeUnit);
    }

    public static Optional<String> postWithForm(String url, Map<String, Object> params, int timeout, TimeUnit timeUnit) {
        if (StringUtils.isBlank(url) || MapUtils.isEmpty(params)) {
            LOGGER.warn("HttpUtils#postWithForm, invalid args! url={}, params={}", url, JSON.toJSONString(params));
            return Optional.empty();
        }

        List<String> paramList = params.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.toList());
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody requestBody = RequestBody.create(mediaType, Joiner.on("&").skipNulls().join(paramList));

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();
        return doRequest(url, request, timeout, timeUnit);
    }

    private static Optional<String> doRequest(String url, Request request, int timeout, TimeUnit timeUnit) {
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(timeout, timeUnit);

        long startTime = System.currentTimeMillis();
        Response response = null;
        try {
            response = client.newCall(request).execute();

            if (response == null || !response.isSuccessful()) {
                LOGGER.warn("HttpUtils#doRequest, invalid Response! Request={}, Response={}, url={}, costTime={}",
                        requestBodyToString(request), response, url, System.currentTimeMillis() - startTime);
                return Optional.empty();
            } else {
                LOGGER.info("HttpUtils#doRequest, Response={}, url={}, costTime={}",
                        response, url, System.currentTimeMillis() - startTime);
            }

            String responseStr = response.body().string();
            return Optional.of(responseStr);
        } catch (Exception e) {
            LOGGER.error("HttpUtils#doRequest, Request={}, url={}, costTime={}",
                    requestBodyToString(request), url, System.currentTimeMillis() - startTime, e);
            return Optional.empty();
        } finally {
            if (response != null) {
                try {
                    response.body().close();
                } catch (Exception e) {
                    LOGGER.error("HttpUtils#doRequest, error to close response body.", e);
                }
            }
        }
    }


    private static String requestBodyToString(Request request) {
        if (request == null) {
            return "";
        }
        Request copy = request.newBuilder().build();
        String method = copy.method();

        String requestContent = "";
        if (!"GET".equals(method)) {
            RequestBody requestBody = copy.body();
            if (requestBody != null) {
                try (Buffer buffer = new Buffer()) {
                    requestBody.writeTo(buffer);
                    requestContent = buffer.readUtf8();
                } catch (Exception e) {
                    LOGGER.error("HttpUtils#requestBodyToString, error, ", e);
                }
            }
        }

        return requestContent;
    }
}
