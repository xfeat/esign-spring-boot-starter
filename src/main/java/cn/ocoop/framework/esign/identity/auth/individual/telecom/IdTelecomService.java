package cn.ocoop.framework.esign.identity.auth.individual.telecom;

import cn.ocoop.framework.esign.EsignRestTemplateHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IdTelecomService {

    /**
     * 发起运营商3要素实名认证(电子签名)
     *
     * @param accountId
     * @param factors
     * @return
     */
    public IdTelecomResponse start(String accountId, IdTelecomRequest factors) {
        return EsignRestTemplateHelper.post(
                "/v2/identity/auth/api/individual/{accountId}/telecom3Factors",
                factors, IdTelecomResponse.class, accountId);
    }


    /**
     * 手机号验证码校验
     *
     * @param flowId
     * @param factors
     * @return
     */
    public IdTelecomResponse0 verify(String flowId, IdTelecomRequest0 factors) {
        return EsignRestTemplateHelper.put(
                "/v2/identity/auth/pub/individual/{flowId}/telecom3Factors",
                factors, IdTelecomResponse0.class, flowId);
    }


    /**
     * 个人运营商3要素信息比对
     *
     * @param factors
     * @return
     */
    public IdTelecomResponse1 verify(IdTelecomRequest1 factors) {
        return EsignRestTemplateHelper.post(
                "/v2/identity/verify/individual/telecom3Factors",
                factors, IdTelecomResponse1.class);
    }


}
