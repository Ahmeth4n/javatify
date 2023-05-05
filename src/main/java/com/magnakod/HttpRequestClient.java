package com.magnakod;

import okhttp3.OkHttpClient;
import java.util.concurrent.TimeUnit;

public class HttpRequestClient {
    private static HttpRequestClient singletonInstance;
    private final OkHttpClient client;
    private final int TIMEOUT_SECOND = 10;

    private HttpRequestClient() {
        client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_SECOND, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SECOND, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECOND, TimeUnit.SECONDS)
                .build();
    }
    public static HttpRequestClient getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new HttpRequestClient();
        }
        return singletonInstance;
    }

    public OkHttpClient getClient() {
        return client;
    }
    public void closeConnections() {
        client.dispatcher().cancelAll();
    }

}
