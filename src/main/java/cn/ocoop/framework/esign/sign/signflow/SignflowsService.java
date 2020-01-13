package cn.ocoop.framework.esign.sign.signflow;

import cn.ocoop.framework.common.util.Result;
import cn.ocoop.framework.esign.EsignRestTemplateHelper;
import cn.ocoop.framework.esign.sign.template.SignTemplateRequest;
import cn.ocoop.framework.esign.sign.template.SignTemplateResponse;
import cn.ocoop.framework.esign.sign.template.SignTemplateResponse0;
import cn.ocoop.framework.esign.sign.template.SignTemplateService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SignflowsService {

    @Autowired
    SignTemplateService signTemplateService;

    /**
     * 签署流程创建
     *
     * @param request
     * @return
     */
    public SignflowResponse create(SignflowRequest request) {
        return EsignRestTemplateHelper.post("/v1/signflows", request, SignflowResponse.class);
    }

    /**
     * 流程文档添加
     *
     * @param request
     * @return
     */
    public SignflowResponse0 addDocuments(SignflowRequest0 request) {
        return EsignRestTemplateHelper.post("/v1/signflows/{flowId}/documents", request, SignflowResponse0.class, request.getFlowId());
    }

    /**
     * 查询签署区列表
     *
     * @param flowId
     * @param accountId
     * @param signfieldIds
     * @return
     */
    public SignflowResponse1 signfields(String flowId, String accountId, String signfieldIds) {
        return EsignRestTemplateHelper.get(
                "/v1/signflows/{flowId}/signfields?accountId={accountId}&signfieldIds={signfieldIds}",
                SignflowResponse1.class,
                flowId,
                accountId,
                signfieldIds
        );
    }

    /**
     * 添加平台自动盖章签署区
     *
     * @param flowId
     * @param request
     * @return
     */
    public SignflowResponse2 addPlatformSignArea(String flowId, SignflowRequest1 request) {
        return EsignRestTemplateHelper.post("/v1/signflows/{flowId}/signfields/platformSign", request, SignflowResponse2.class, flowId);
    }

    /**
     * 添加签署方自动盖章签署区
     *
     * @param request
     * @return
     */
    public SignflowResponse2 addAutoSignArea(String flowId, SignflowRequest3 request) {
        return EsignRestTemplateHelper.post("/v1/signflows/{flowId}/signfields/autoSign", request, SignflowResponse2.class, flowId);
    }

    /**
     * 添加手动盖章签署区
     *
     * @param flowId
     * @param request
     * @return
     */
    public SignflowResponse2 addHandSignArea(String flowId, SignflowRequest2 request) {
        return EsignRestTemplateHelper.post("/v1/signflows/{flowId}/signfields/handSign", request, SignflowResponse2.class, flowId);
    }

    /**
     * 开启签署流程
     *
     * @param flowId
     * @return
     */
    public SignflowResponse3 start(String flowId) {
        return EsignRestTemplateHelper.put("/v1/signflows/{flowId}/start", null, SignflowResponse3.class, flowId);
    }

    /**
     * 添加平台自动盖章签署区
     *
     * @param flowId
     * @return
     */
    public Object stop(String flowId) {
        return EsignRestTemplateHelper.put(" /v1/signflows/{flowId}/revoke", null, Object.class, flowId);
    }


    /**
     * 签署流程查询
     *
     * @param flowId
     * @return
     */
    public SignflowResponse5 get(String flowId) {
        return EsignRestTemplateHelper.get(" /v1/signflows/{flowId}", SignflowResponse5.class, flowId);
    }


    /**
     * 添加平台自动盖章签署区
     *
     * @param flowId
     * @return
     */
    public SignflowResponse4 searchWordsPosition(String flowId, String fileId, String keywords) {
        return EsignRestTemplateHelper.get(
                "/v1/signflows/{flowId}/documents/{fileId}/searchWordsPosition?keywords={keywords}",
                SignflowResponse4.class,
                flowId,
                fileId,
                keywords
        );
    }

    /**
     * 获取签署地址
     *
     * @param flowId
     * @return
     */
    public SignflowResponse6 executeUrl(String flowId, String accountId, String organizeId, String urlType) {
        return EsignRestTemplateHelper.get(
                "/v1/signflows/{flowId}/executeUrl?accountId={accountId}&organizeId={organizeId}&urlType={urlType}",
                SignflowResponse6.class,
                flowId,
                accountId,
                organizeId,
                urlType
        );
    }

    /**
     * 开启流程
     *
     * @param signflowStarter
     * @return
     */
    public Result startPrepare(SignflowStarter signflowStarter) {
        SignflowStarterResponse starterResponse = new SignflowStarterResponse();


        if (StringUtils.isNotBlank(signflowStarter.getNoticeDeveloperUrl())) {
            signflowStarter.setNoticeDeveloperUrl(signflowStarter.getNoticeDeveloperUrl());
        }

        if (StringUtils.isNotBlank(signflowStarter.getRedirectUrl())) {
            signflowStarter.setRedirectUrl(signflowStarter.getRedirectUrl());
        }

        //签署流程创建
        SignflowResponse flow = this.create(
                SignflowRequest.builder()
                        .autoArchive(true)
                        .businessScene(signflowStarter.getBusinessScene())
                        .configInfo(
                                SignflowRequest.ConfigInfo.builder()
                                        .noticeDeveloperUrl(signflowStarter.getNoticeDeveloperUrl())
                                        .noticeType(signflowStarter.getNoticeType())
                                        .redirectUrl(signflowStarter.getRedirectUrl())
                                        .build()
                        )
                        .contractValidity(signflowStarter.getContractValidity())
                        .signValidity(signflowStarter.getSignValidity())
                        .build()
        );

        if (!flow.success()) return Result.build("FAIL", flow.getMessage(), flow.getCode());

        starterResponse.setFlowId(flow.getData().getFlowId());
        starterResponse.setFlowName(signflowStarter.getBusinessScene());

        //通过模板创建文件
        for (SignflowStarter.Template templateInfo : signflowStarter.getTemplates()) {

            // 查询模板详情
            SignTemplateResponse0 template = signTemplateService.detail(templateInfo.getTemplateId());

            if (!template.success()) {
                return Result.build("FAIL", template.getMessage(), template.getCode());
            }

            if (CollectionUtils.isEmpty(template.getData().getStructComponents())) {
                return Result.build("FAIL", "模板不存在可填充区");
            }

            if (templateInfo.getSimpleFormFields() == null) {
                templateInfo.setSimpleFormFields(new HashMap<>());
            }

            Map<String, List<SignTemplateResponse0.RetData.StructComponents>> templateComponents = template.getData().getStructComponents().stream().collect(Collectors.groupingBy(o -> o.getContext().getLabel()));

            Map<String, String> formFields = Maps.newHashMap();

            for (String label : templateInfo.getSimpleFormFields().keySet()) {
                if (CollectionUtils.size(templateComponents.get(label)) != 1) {
                    return Result.build("FAIL", templateInfo.getName() + "模板设置错误,label:" + label + "对应" + CollectionUtils.size(templateComponents.get(label)) + "个可填充区");
                }

                formFields.put(templateComponents.get(label).get(0).getId(), templateInfo.getSimpleFormFields().get(label));
            }

            SignTemplateResponse file = signTemplateService.create(
                    SignTemplateRequest.builder()
                            .name(templateInfo.getName())
                            .templateId(templateInfo.getTemplateId())
                            .simpleFormFields(formFields)
                            .build()
            );

            if (!file.success()) {
                return Result.build("FAIL", file.getMessage(), file.getCode());
            }

            starterResponse.getContract().add(new SignflowStarterResponse.Contract(file.getData().getFileId(), templateInfo.getName()));

            //流程文档添加
            SignflowResponse0 document = this.addDocuments(
                    SignflowRequest0.builder()
                            .flowId(flow.getData().getFlowId())
                            .docs(
                                    Collections.singletonList(
                                            SignflowRequest0.Docs.builder()
                                                    .fileId(file.getData().getFileId())
                                                    .build()
                                    )
                            )
                            .build()
            );

            if (!document.success()) {
                return Result.build("FAIL", document.getMessage(), document.getCode());
            }

            //添加签署区

            //添加平台自动盖章签署区
            //查询平台自动签署区坐标
            if (CollectionUtils.size(templateComponents.get(signflowStarter.getPlatformSignLabel())) != 1) {
                return Result.build("FAIL", "模板设置错误,签署区label:" + signflowStarter.getPlatformSignLabel() + "对应" + CollectionUtils.size(templateComponents.get(signflowStarter.getPlatformSignLabel())) + "个可填充区");
            }

            SignTemplateResponse0.RetData.StructComponents.Context.Pos platformSignPosition = templateComponents.get(signflowStarter.getPlatformSignLabel()).get(0).getContext().getPos();

            if (StringUtils.isNotBlank(signflowStarter.getPlatformSignLabel())) {
                SignflowResponse2 platformSignArea = this.addPlatformSignArea(
                        flow.getData().getFlowId(),
                        SignflowRequest1.builder()
                                .signfields(
                                        Collections.singletonList(
                                                SignflowRequest1.Signfield.builder()
                                                        .fileId(file.getData().getFileId())
                                                        .posBean(
                                                                SignflowRequest1.Signfield.PosBean.builder()
                                                                        .posPage(String.valueOf(platformSignPosition.getPage()))
                                                                        .posX(platformSignPosition.getX())
                                                                        .posY(platformSignPosition.getY())
                                                                        .addSignTime(true)
                                                                        .build()
                                                        )
                                                        .sealId(signflowStarter.getSealId())
                                                        .build()
                                        )
                                )
                                .build()
                );

                if (!platformSignArea.success()) {
                    return Result.build("FAIL", platformSignArea.getMessage(), platformSignArea.getCode());
                }
            }

            if (StringUtils.isNotBlank(signflowStarter.getAutoSignLabel())) {
                SignflowResponse2 autoSignArea = this.addAutoSignArea(
                        flow.getData().getFlowId(),
                        SignflowRequest3.builder()
                                .signfields(
                                        Collections.singletonList(
                                                SignflowRequest3.Signfield.builder()
                                                        .fileId(file.getData().getFileId())
                                                        .posBean(
                                                                SignflowRequest3.Signfield.PosBean.builder()
                                                                        .posPage(String.valueOf(platformSignPosition.getPage()))
                                                                        .posX(platformSignPosition.getX())
                                                                        .posY(platformSignPosition.getY())
                                                                        .addSignTime(true)
                                                                        .build()
                                                        )
                                                        .sealId(signflowStarter.getSealId())
                                                        .build()
                                        )
                                )
                                .build()
                );

                if (!autoSignArea.success()) {
                    return Result.build("FAIL", autoSignArea.getMessage(), autoSignArea.getCode());
                }
            }

            //添加手动盖章签署区
            //查询平台自动签署区坐标
            if (CollectionUtils.size(templateComponents.get(signflowStarter.getHandSignLabel())) != 1)
                return Result.build("FAIL", "模板设置错误,签署区label:" + signflowStarter.getPlatformSignLabel() + "对应" + CollectionUtils.size(templateComponents.get(signflowStarter.getHandSignLabel())) + "个可填充区");
            SignTemplateResponse0.RetData.StructComponents.Context.Pos handSignPosition = templateComponents.get(signflowStarter.getHandSignLabel()).get(0).getContext().getPos();


            SignflowResponse2 handSignArea = this.addHandSignArea(
                    flow.getData().getFlowId(),
                    SignflowRequest2.builder()
                            .signfields(
                                    Collections.singletonList(
                                            SignflowRequest2.Signfield.builder()
                                                    .fileId(file.getData().getFileId())
                                                    .signerAccountId(signflowStarter.getSignerAccountId())
                                                    .authorizedAccountId(signflowStarter.getAuthorizedAccountId())
                                                    .actorIndentityType(signflowStarter.getActorIndentityType())
                                                    .assignedPosbean(true)
                                                    .posBean(
                                                            SignflowRequest2.Signfield.PosBean.builder()
                                                                    .posPage(String.valueOf(handSignPosition.getPage()))
                                                                    .posX(handSignPosition.getX())
                                                                    .posY(handSignPosition.getY())
                                                                    .addSignTime(true)
                                                                    .build()
                                                    )
                                                    .build()
                                    )
                            )
                            .build()
            );
            if (!handSignArea.success()) return Result.build("FAIL", handSignArea.getMessage(), handSignArea.getCode());
        }

        return Result.success(starterResponse);
    }
}
