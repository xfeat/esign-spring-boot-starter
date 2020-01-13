package cn.ocoop.framework.esign.identity.auth.individual;

import cn.ocoop.framework.esign.EsignRestTemplateHelper;
import org.springframework.stereotype.Service;

@Service
public class IdIndividualService {

    /**
     * 个人2要素信息比对
     *
     * @param factors
     * @param factors
     * @return
     */
    public IdIndividualResponse verify(IdIndividualRequest factors) {
        return EsignRestTemplateHelper.post(
                "/v2/identity/verify/individual/base",
                factors, IdIndividualResponse.class);
    }
}
