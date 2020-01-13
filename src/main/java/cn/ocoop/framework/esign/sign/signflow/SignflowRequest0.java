package cn.ocoop.framework.esign.sign.signflow;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SignflowRequest0 {
    private String flowId;
    private List<Docs> docs;

    @Data
    @Builder
    public static class Docs {
        private String fileId;
        private Integer encryption;
        private String fileName;
        private String filePassword;
    }
}
