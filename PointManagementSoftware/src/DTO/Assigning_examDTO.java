package DTO;

public class Assigning_examDTO {
        private String sub_id;
        private String tea_id;

        public Assigning_examDTO() {
        }

        public Assigning_examDTO(String sub_id, String tea_id) {
                this.sub_id = sub_id;
                this.tea_id = tea_id;
        }

        public String getSub_id() {
                return sub_id;
        }

        public void setSub_id(String sub_id) {
                this.sub_id = sub_id;
        }

        public String getTea_id() {
                return tea_id;
        }

        public void setTea_id(String tea_id) {
                this.tea_id = tea_id;
        }
}
