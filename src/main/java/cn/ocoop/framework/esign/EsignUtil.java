package cn.ocoop.framework.esign;

import org.apache.commons.collections4.EnumerationUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;


public class EsignUtil {
    public static boolean verify(String appSecret, String body, HttpServletRequest request) {
        StringBuilder requestQuery = new StringBuilder();
        for (String key : EnumerationUtils.toList(request.getParameterNames())) {
            String value = request.getParameter(key);
            requestQuery.append(value == null ? "" : value);
        }
        return sha256_HMAC(
                request.getHeader("X-Tsign-Open-TIMESTAMP") + requestQuery.toString() + body,
                appSecret
        ).equals(request.getHeader("X-Tsign-Open-SIGNATURE"));
    }

    /**
     * 将加密后的字节数组转换成字符串
     *
     * @param b 字节数组
     * @return 字符串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }

    /**
     * sha256_HMAC加密
     *
     * @param message 消息
     * @param secret  秘钥
     * @return 加密后字符串
     */
    public static String sha256_HMAC(String message, String secret) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            hash = byteArrayToHexString(bytes);
        } catch (Exception e) {
            System.out.println("Error HmacSHA256 ===========" + e.getMessage());
        }
        return hash;
    }
}
