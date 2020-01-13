package cn.ocoop.framework.esign.identity.auth.org;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdOrgRequest1 {
    private String agentAccountId;
    private Boolean repetition;
    private String contextId;
    private String notifyUrl;
}
