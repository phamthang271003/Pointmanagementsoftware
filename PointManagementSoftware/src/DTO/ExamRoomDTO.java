package DTO;

public class ExamRoomDTO {
        private String roomID;
        private String roomName;
        private int quantity;

        public String getRoomID() {
                return roomID;
        }

        public void setRoomID(String roomID) {
                this.roomID = roomID;
        }

        public String getRoomName() {
                return roomName;
        }

        public void setRoomName(String roomName) {
                this.roomName = roomName;
        }

        public int getQuantity() {
                return quantity;
        }

        public void setQuantity(int quantity) {
                this.quantity = quantity;
        }

        public ExamRoomDTO(String roomID, String roomName, int quantity) {
                this.roomID = roomID;
                this.roomName = roomName;
                this.quantity = quantity;
        }

        public ExamRoomDTO() {
        }
}
