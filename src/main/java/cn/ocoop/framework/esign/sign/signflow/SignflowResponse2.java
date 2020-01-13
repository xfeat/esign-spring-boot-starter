package cn.ocoop.framework.esign.sign.signflow;

import lombok.Data;

import java.util.List;

@Data
public class SignflowResponse2 {
    private int code;
    private String message;
    private List<SignflowResponse0.RetData> signfieldBeans;

    public boolean success() {
        return 0 == code;
    }

    @Data
    public static class RetData {
        private String accountId;
        private String fileId;
        private String signfieldId;
    }
}
