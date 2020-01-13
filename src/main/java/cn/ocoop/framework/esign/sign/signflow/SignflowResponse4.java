package cn.ocoop.framework.esign.sign.signflow;

import lombok.Data;

import java.util.List;

@Data
public class SignflowResponse4 {
    private int code;
    private String message;
    private List<RetData> data;

    public boolean success() {
        return 0 == code;
    }

    @Data
    public static class RetData {
        private String fileId;
        private String keyword;
        private List<Position> positionList;

        @Data
        public static class Position {
            private Integer pageIndex;
            private List<Coordinate> coordinateList;

            @Data
            public static class Coordinate {
                private Float posx;
                private Float posy;
            }
        }
    }

}
