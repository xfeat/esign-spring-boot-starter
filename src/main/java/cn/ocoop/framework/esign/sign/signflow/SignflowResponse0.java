package cn.ocoop.framework.esign.sign.signflow;

import lombok.Data;

@Data
public class SignflowResponse0 {
    private int code;
    private String message;
    private RetData data;

    public boolean success() {
        return 0 == code;
    }

    @Data
    public static class RetData {
        private String flowId;
    }
}
