package cn.ocoop.framework.esign.sign.signflow;

import lombok.Data;

@Data
public class SignflowResponse3 {
    private int code;
    private String message;

    public boolean success() {
        return 0 == code;
    }

}
