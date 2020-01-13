package cn.ocoop.framework.esign.sign.account;

import cn.ocoop.framework.esign.EsignRestTemplateHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SignAccountService {


    /**
     * 创建e签宝个人账号
     *
     * @param factors
     * @return
     */
    public SignAccountResponse create(SignAccountRequest factors) {
        return EsignRestTemplateHelper.post(
                "/v1/accounts/createByThirdPartyUserId",
                factors, SignAccountResponse.class);
    }

    /**
     * 修改e签宝个人账号
     *
     * @param factors
     * @return
     */
    public SignAccountResponse0 update(String accountId, SignAccountRequest0 factors) {
        return EsignRestTemplateHelper.put(
                "/v1/accounts/{accountId}",
                factors, SignAccountResponse0.class, accountId);
    }

    /**
     * 查询个人账号 按照账号ID查询
     *
     * @param accountId
     * @return
     */
    public SignAccountResponse0 get(String accountId) {
        return EsignRestTemplateHelper.get(
                "/v1/accounts/{accountId}",
                SignAccountResponse0.class, accountId);
    }

    /**
     * 查询个人账号 按照第三方用户ID查询
     *
     * @param thirdPartyUserId
     * @return
     */
    public SignAccountResponse0 getByThirdId(String thirdPartyUserId) {
        return EsignRestTemplateHelper.get(
                "/v1/accounts/getByThirdId?thirdPartyUserId={thirdPartyUserId}",
                SignAccountResponse0.class, thirdPartyUserId);
    }

    /**
     * 注销个人账号 按照账号ID注销
     *
     * @param accountId
     * @return
     */
    public SignAccountResponse1 delete(String accountId) {
        return EsignRestTemplateHelper.delete(
                "/v1/accounts/{accountId}",
                SignAccountResponse1.class, accountId);
    }

    /**
     * 注销个人账号 按照账号ID注销
     *
     * @param thirdPartyUserId
     * @return
     */
    public SignAccountResponse1 deleteByThirdId(String thirdPartyUserId) {
        return EsignRestTemplateHelper.delete(
                "/v1/accounts/deleteByThirdId?thirdPartyUserId={thirdPartyUserId}",
                SignAccountResponse1.class, thirdPartyUserId);
    }

}
