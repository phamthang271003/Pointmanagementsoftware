package DTO;

public class StudyRecordDTO
{
        private String stu_id;
        private String stu_name;
        private String sub_name;
        private int res_time;
        private float res_score;

        public String getStu_id() {
                return stu_id;
        }

        public void setStu_id(String stu_id) {
                this.stu_id = stu_id;
        }

        public String getStu_name() {
                return stu_name;
        }

        public void setStu_name(String stu_name) {
                this.stu_name = stu_name;
        }

        public String getSub_name() {
                return sub_name;
        }

        public void setSub_name(String sub_name) {
                this.sub_name = sub_name;
        }

        public int getRes_time() {
                return res_time;
        }

        public void setRes_time(int res_time) {
                this.res_time = res_time;
        }

        public float getRes_score() {
                return res_score;
        }

        public void setRes_score(float res_score) {
                this.res_score = res_score;
        }

        public StudyRecordDTO() {
        }
}
