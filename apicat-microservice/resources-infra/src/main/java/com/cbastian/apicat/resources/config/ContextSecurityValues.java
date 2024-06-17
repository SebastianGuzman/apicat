package com.cbastian.apicat.resources.config;

public class ContextSecurityValues {

    public static String KEY_CLIENT_PROTOCOL = "jdk.tls.client.protocols";
    public static String KEY_HTTP_PROTOCOL = "https.protocols";
    public static String KEY_SSL_TRUSTORE = "javax.net.ssl.trustStore";
    public static String KEY_SSL_TRUSTORE_PASSWORD = "javax.net.ssl.trustStorePassword";

    public static String VALUE_CLIENT_PROTOCOL = "TLSv1.1,TLSv1,TLSv1.2,TLSv1.3";
    public static String VALUE_HTTPS_PROTOCOL = "TLSv1.1,TLSv1,TLSv1.2,TLSv1.3";
    public static String VALUE_ENDPOINT_IDENTIFICATION = "true";
}
