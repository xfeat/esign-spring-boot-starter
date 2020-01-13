package cn.ocoop.framework.esign.sign.template;

import lombok.Data;

@Data
public class SignTemplateResponse {
    private int code;
    private String message;
    private RetData data;

    public boolean success() {
        return 0 == code;
    }

    @Data
    public static class RetData {
        private String downloadUrl;
        private String fileId;
        private String fileName;
    }

}
