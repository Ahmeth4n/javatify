package com.magnakod.emulator.base.request;

import okhttp3.*;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseRequest {
    private final OkHttpClientBuilderFactory builderFactory;
    private static BaseRequest instance = null;
    private synchronized static void createInstance(OkHttpClientBuilderFactory builderFactory) {
        if (instance == null)
            if (builderFactory == null)
                instance = new BaseRequest();
            else
                instance = new BaseRequest(builderFactory);
    }
    public static BaseRequest getInstance(OkHttpClientBuilderFactory builderFactory) {
        if (instance == null)
            createInstance(builderFactory);
        return instance;
    }
    private static final int SIGNER_API_TIMEOUT_SECONDS = 15;

    private String content_type;

    public BaseRequest() {
        this.builderFactory = new EnvironmentSettingsOkhttpBuilder();
    }
    public BaseRequest(OkHttpClientBuilderFactory builderFactory) {
        this.builderFactory = builderFactory;
    }

    public Response post_request(String url, byte[] data, Headers header) throws Exception{

        OkHttpClient client = builderFactory.newOkHttpClientBuilder()
                .connectTimeout(SIGNER_API_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(SIGNER_API_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(SIGNER_API_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build();

        MediaType mediaType = MediaType.parse(this.getContent_type());
        RequestBody body = RequestBody.create(mediaType, data);

        Request.Builder httpRequest = new Request.Builder()
                .url(url)
                .headers(header)
                .post(body);

        Response response = client.newCall(httpRequest.build()).execute();
        return response;
    }

    public Response post_request(String url, String data, Headers header) throws Exception{

        OkHttpClient client = builderFactory.newOkHttpClientBuilder()
                .connectTimeout(SIGNER_API_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(SIGNER_API_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(SIGNER_API_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build();

        MediaType mediaType = MediaType.parse(this.getContent_type());
        RequestBody body = RequestBody.create(mediaType, data);

        Request.Builder httpRequest = new Request.Builder()
                .url(url)
                .headers(header)
                .post(body);

        Response response = client.newCall(httpRequest.build()).execute();
        return response;
    }

    public Response get_request(String url, Headers header) throws Exception{

        OkHttpClient client = builderFactory.newOkHttpClientBuilder()
                .connectTimeout(SIGNER_API_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(SIGNER_API_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(SIGNER_API_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build();

        Request.Builder httpRequest = new Request.Builder()
                .url(url)
                .headers(header)
                .get();

        Response response = client.newCall(httpRequest.build()).execute();
        return response;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }
}