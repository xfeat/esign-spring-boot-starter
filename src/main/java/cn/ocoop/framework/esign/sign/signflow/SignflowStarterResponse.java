package cn.ocoop.framework.esign.sign.signflow;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

@Data
public class SignflowStarterResponse {
    private String flowId;
    private String flowName;
    private List<Contract> contract = Lists.newArrayList();

    @Data
    @NoArgsConstructor
    public static class Contract {
        private String fileId;
        private String fileName;

        public Contract(String fileId, String fileName) {
            this.fileId = fileId;
            this.fileName = fileName;
        }
    }
}
