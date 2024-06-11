package DTO;

public class ExamRoomDTO {
	String room_id;
	String room_name;
	int room_quantity;
	public String getRoom_id() {
		return room_id;
	}
	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public int getRoom_quantity() {
		return room_quantity;
	}
	public void setRoom_quantity(int room_quantity) {
		this.room_quantity = room_quantity;
	}
	public ExamRoomDTO(String room_id, String room_name, int room_quantity) {
		super();
		this.room_id = room_id;
		this.room_name = room_name;
		this.room_quantity = room_quantity;
	}
	public ExamRoomDTO() {
		super();
	}
	@Override
	public String toString() {
		return getRoom_name();
	}
	
}
