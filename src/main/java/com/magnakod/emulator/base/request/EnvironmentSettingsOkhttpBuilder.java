package com.magnakod.emulator.base.request;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.*;
import java.net.InetSocketAddress;
import java.net.Proxy;

public class EnvironmentSettingsOkhttpBuilder implements OkHttpClientBuilderFactory{
    @Override
    public OkHttpClient.Builder newOkHttpClientBuilder() {
        ProxyType proxyType = getProxyType();
        return switch (proxyType) {
            case DIRECT -> newDirectOkHttpClient();
            case LOCAL_PROXY -> newLocalProxyOkHttpClient(proxyPort);
            case PROXY_SERVICE -> newProxyOkHttpClient();
        };
    }

    public enum ProxyType {
        DIRECT,
        LOCAL_PROXY,
        PROXY_SERVICE
    }

    private static final String ENV_PROXY_USERNAME = "PROXY_USERNAME";
    private static final String ENV_PROXY_PASSWORD = "PROXY_PASSWORD";
    private static final String ENV_PROXY_COUNTRY = "PROXY_COUNTRY";
    private static final String ENV_PROXY_HOST = "PROXY_HOST";
    private static final String ENV_PROXY_PORT = "PROXY_PORT";


    private OkHttpClient.Builder builder;
    private ProxyType proxyType;
    private String proxyHost;
    private int proxyPort;
    private String proxyUsername;
    private String proxyPassword;

    public EnvironmentSettingsOkhttpBuilder() {
        builder = new OkHttpClient.Builder();
    }

    public void setProxy(ProxyType proxyType, String proxyHost, int proxyPort,String proxyUsername, String proxyPassword) {
        this.proxyType = proxyType;
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.proxyUsername = proxyUsername;
        this.proxyPassword = proxyPassword;
        sProxyType = proxyType;
    }
    private static ProxyType sProxyType;

    public static synchronized ProxyType getProxyType() {
        if (sProxyType == null) {
            String proxyType = System.getenv("PROXY_TYPE"); // proxy check block
            if (StringUtils.isNotBlank(proxyType)) {
                sProxyType = ProxyType.valueOf(proxyType);
            } else {
                sProxyType = ProxyType.DIRECT;
            }
        }
        return sProxyType;
    }

    private static OkHttpClient.Builder newDirectOkHttpClient() {
        return new OkHttpClient.Builder();
    }
    public static OkHttpClient.Builder newLocalProxyOkHttpClient(int port) {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", port));
            return builder.proxy(proxy);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public OkHttpClient.Builder newProxyOkHttpClient() {
        final String username = this.proxyUsername;
        final String password = this.proxyPassword;
        final String host = this.proxyHost;
        final int port = this.proxyPort;

        InetSocketAddress proxyAddress = new InetSocketAddress(host,port);

        Authenticator proxyAuthenticator = (route, response) -> {
            String credential = Credentials.basic(username, password);
            return response.request().newBuilder()
                    .header("Proxy-Authorization", credential)
                    .build();
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Proxy luminatiProxy = new Proxy(Proxy.Type.HTTP, proxyAddress);
        return builder
                .proxyAuthenticator(proxyAuthenticator)
                .proxy(luminatiProxy);
    }



}