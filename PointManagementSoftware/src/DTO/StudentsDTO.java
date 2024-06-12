package DTO;

import java.sql.Date;

public class StudentsDTO {
        private String stu_id;
        private String stu_name;
        private Date stu_day_entry;
        private int stu_semester;
        private String stu_sex;

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

        public Date getStu_day_entry() {
                return stu_day_entry;
        }

        public void setStu_day_entry(Date stu_day_entry) {
                this.stu_day_entry = stu_day_entry;
        }

        public int getStu_semester() {
                return stu_semester;
        }

        public void setStu_semester(int stu_semester) {
                this.stu_semester = stu_semester;
        }

        public String getStu_sex() {
                return stu_sex;
        }

        public void setStu_sex(String stu_sex) {
                this.stu_sex = stu_sex;
        }

        public Date getDob() {
                return dob;
        }

        public void setDob(Date dob) {
                this.dob = dob;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public String getAca_id() {
                return aca_id;
        }

        public void setAca_id(String aca_id) {
                this.aca_id = aca_id;
        }

        public String getClass_id() {
                return class_id;
        }

        public void setClass_id(String class_id) {
                this.class_id = class_id;
        }

        private Date dob;

        public StudentsDTO() {
        }

        public StudentsDTO(String stu_id, String stu_name, Date stu_day_entry, int stu_semester, String stu_sex, Date dob, String address, String aca_id, String class_id) {
                this.stu_id = stu_id;
                this.stu_name = stu_name;
                this.stu_day_entry = stu_day_entry;
                this.stu_semester = stu_semester;
                this.stu_sex = stu_sex;
                this.dob = dob;
                this.address = address;
                this.aca_id = aca_id;
                this.class_id = class_id;
        }

        private String address;
        private String aca_id;
        private String class_id;



}
