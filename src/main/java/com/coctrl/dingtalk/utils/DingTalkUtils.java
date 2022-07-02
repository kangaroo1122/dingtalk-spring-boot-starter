package com.coctrl.dingtalk.utils;

import com.coctrl.dingtalk.constant.DingTalkConstant;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 类 DingTalkUtils 功能描述：
 *
 * @author kangaroo hy
 * @version 0.0.1
 * @date 2022/06/28 23:00
 */
public class DingTalkUtils {
    private DingTalkUtils() {
    }

    public static final Random JVM_RANDOM = new JVMRandom();

    public static String getUrl(String accessToken) {
        return getUrl(accessToken, null);
    }

    public static String getUrl(String accessToken, String secret) {
        Map<String, Object> params = new HashMap<>();
        params.put("access_token", accessToken);
        params.putAll(DingTalkUtils.secretSign(secret));
        return DingTalkConstant.URL + createUrlParams(params);
    }

    public static Map<String, Object> secretSign(String secret) {
        Map<String, Object> params = new HashMap<>(2);
        if (secret != null && !"".equals(secret)) {
            Long timestamp = System.currentTimeMillis();

            String stringToSign = timestamp + "\n" + secret;
            try {
                Mac mac = Mac.getInstance("HmacSHA256");
                mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
                byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
                String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
                params.put("timestamp", timestamp);
                params.put("sign", sign);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return params;
    }

    private static String createUrlParams(Map<String, Object> stringMap) {
        StringBuilder params = new StringBuilder("?");
        for (Map.Entry<String, Object> entry : stringMap.entrySet()) {
            params.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return params.substring(0, params.length() - 1);
    }
}
