package cn.ocoop.framework.esign.identity.auth.individual.bank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdBankCardRequest1 {
    private String idNo;
    private String name;
    private String cardNo;
    private String mobileNo;
}
