package cn.ocoop.framework.esign.identity.auth.individual.bank;

import cn.ocoop.framework.esign.EsignRestTemplateHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IdBankCardService {

    /**
     * 发起银行卡4要素实名认证(电子签名)
     *
     * @param accountId
     * @param factors
     * @return
     */
    public IdBankCardResponse start(String accountId, IdBankCardRequest factors) {
        return EsignRestTemplateHelper.post(
                "/v2/identity/auth/api/individual/{accountId}/bankCard4Factors",
                factors, IdBankCardResponse.class, accountId);
    }

    /**
     * 银行预留手机号验证码校验
     *
     * @param flowId
     * @param factors
     * @return
     */
    public IdBankCardResponse0 verify(String flowId, IdBankCardRequest0 factors) {
        return EsignRestTemplateHelper.put(
                "/v2/identity/auth/pub/individual/{flowId}/bankCard4Factors",
                factors, IdBankCardResponse0.class, flowId);
    }

    /**
     * 个人银行卡4要素信息比对
     *
     * @param factors
     * @return
     */
    public IdBankCardResponse1 verify(IdBankCardRequest1 factors) {
        return EsignRestTemplateHelper.post(
                "/v2/identity/verify/individual/bank4Factors",
                factors, IdBankCardResponse1.class);
    }


}
