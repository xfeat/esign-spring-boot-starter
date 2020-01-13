package cn.ocoop.framework.esign;

import lombok.Data;

@Data
public class AccessTokenResponse {
    private int code;
    private TokenData data;
    private String message;

    @Data
    public static class TokenData {
        private String token;
        private String expiresIn;
        private String refreshToken;
    }
}
