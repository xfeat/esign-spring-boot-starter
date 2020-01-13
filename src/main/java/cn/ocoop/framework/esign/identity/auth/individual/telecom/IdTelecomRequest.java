package cn.ocoop.framework.esign.identity.auth.individual.telecom;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdTelecomRequest {
    private String mobileNo;
    private Boolean repetition;
    private String contextId;
    private String notifyUrl;
}
