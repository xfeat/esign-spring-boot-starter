package cn.ocoop.framework.esign;

import lombok.Data;

@Data
public class FlowFinishNotify {
    private String action;
    private String flowId;
    private String businessScence;
    private String flowStatus;
    private String statusDescription;
    private String createTime;
    private String endTime;
    private long timestamp;
}
