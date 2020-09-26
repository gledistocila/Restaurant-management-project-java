package util;

public enum ErrorMessage {

	MEMBER_EXISTS("Anetari ekziston. "),
	MEMBER_DOES_NOT_EXIST("Anetari nuk ekziston. "),
	MEMBER_CAN_NOT_LOGIN("Te dhenat nuk jane te sakta. "),
    STAFF_EXISTS("Staff ekziston. "),
    STAFF_DOES_NOT_EXIST("Staff nuk ekziston. "),
    CATEGORY_DOES_NOT_EXIST("Kategoria nuk ekziston. "),
    RATING_DOES_NOT_EXIST("Rating nuk ekziston. "),
    FOOD_DOES_NOT_EXIST("Ushqimi nuk ekziston. "),
    TABLE_DOES_NOT_EXIST("Tavolina nuk ekziston. "),
	ADMIN_DOES_NOT_EXIST("Te dhenat e administratorit nuk jane te sakta. ");
	
	private String errorMessage;

	ErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}

