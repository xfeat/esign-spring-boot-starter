package cn.ocoop.framework.esign.sign.signflow;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class SignflowStarter {
    /**
     * 流程:文件主题
     */
    private final String businessScene;

    /**
     * 添加签署区: 平台自动签署区搜索关键字
     */
    private final String platformSignLabel;

    /**
     * 添加签署区: 签署方自动签署区搜索关键字
     */
    private final String autoSignLabel;

    /**
     * 添加签署区: 印章id， 仅限企业公章，暂不支持指定企业法定代表人印章
     */
    private final String sealId;

    /**
     * 添加签署区: 印章id， 仅限签署方自动签署
     */
    private final String autoSealId;

    /**
     * 添加签署区: 签署操作人个人账号标识，即操作本次签署的个人
     */
    private final String handSignLabel;
    /**
     * 添加签署区: 签署操作人个人账号标识，即操作本次签署的个人
     */
    private final String signerAccountId;
    /**
     * 添加签署区: 签约主体账号标识，即本次签署对应任务的归属方
     */
    private final String authorizedAccountId;
    /**
     * 添加签署区: 机构签约类别，当签约主体为机构时必传：2-机构盖章，3-法定代表人盖章 ；
     */
    private final Integer actorIndentityType;
    /**
     * 流程:签署完成回调通知地址 ,默认取项目配置通知地址
     */
    private String noticeDeveloperUrl;
    /**
     * 流程:通知方式，逗号分割，1-短信，2-邮件 。默认值1 如果客户需要不通知，可以设置noticeType=""
     */
    private String noticeType;
    /**
     * 流程:签署完成重定向地址,默认签署完成停在当前页面
     */
    private String redirectUrl;
    /**
     * 流程:文件有效截止日期,毫秒，默认不失效
     */
    private Long contractValidity;
    /**
     * 流程:文签署有效截止日期,毫秒，默认不失效
     */
    private Long signValidity;


    private List<Template> templates;

    @Data
    @Builder
    public static class Template {
        /**
         * 通过模板创建文件: 文件名称
         */
        private final String name;
        /**
         * 通过模板创建文件: 模板编号, 可通过e签宝网站->企业模板下创建和查询
         */
        private final String templateId;
        /**
         * 通过模板创建文件: 输入项填充内容，label:value 传入
         */
        private Map<String, String> simpleFormFields;
    }
}
