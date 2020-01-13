package cn.ocoop.framework.esign.identity.auth.individual.bank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdBankCardRequest {
    private String mobileNo;
    private String bankCardNo;
    private Boolean repetition;
    private String contextId;
    private String notifyUrl;
}
