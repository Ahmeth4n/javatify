package com.magnakod.emulator.base.request;

import okhttp3.OkHttpClient;

public interface OkHttpClientBuilderFactory {
    OkHttpClient.Builder newOkHttpClientBuilder();

}
