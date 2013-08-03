package com.itv.util;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import sun.misc.BASE64Encoder;

public class FetchurlUtil
{
    public static final String DATE_FORMAT = "yyyy-MM-dd kk:mm:ss";
    private static Logger logger = Logger.getLogger(FetchurlUtil.class.getName());

    public static String calcSignature(String content, String secretKey)
    {
        return calcSignature("HmacSHA256", content, secretKey);
    }

    public static String calcSignature(String cryptoType, String content, String secretKey)
    {
        try
        {
            Mac mac = Mac.getInstance(cryptoType);
            SecretKeySpec secret = new SecretKeySpec(secretKey.getBytes(), cryptoType);
            mac.init(secret);
            byte[] digest = mac.doFinal(content.getBytes());
            BASE64Encoder encode = new BASE64Encoder();
            return encode.encode(digest);
        } catch (InvalidKeyException e) {
            logger.error("calc signautre failure.", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("calc signautre failure.", e);
        }
        return "";
    }

    public static String encodeBase64(String encodeContent)
    {
        BASE64Encoder encode = new BASE64Encoder();
        return encode.encode(encodeContent.getBytes());
    }

    public static String getTimeStamp()
    {
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        return sdf.format(date);
    }

    public static String encodeUTF8(String url)
    {
        try
        {
            byte[] bytes = url.getBytes();
            String str = new String(bytes, "UTF-8");
            return str;
        } catch (UnsupportedEncodingException e) {
            logger.error("encode utf-8 failure.", e);
        }return "";
    }

    static HttpClient wrapClient(HttpClient base)
    {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }
                public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {  }

                public X509Certificate[] getAcceptedIssuers() { return null;
                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = base.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", ssf, 443));
            return new DefaultHttpClient(ccm, base.getParams());
        } catch (Exception e) {
            logger.error("warp httpclient failure.", e);
        }return null;
    }
}