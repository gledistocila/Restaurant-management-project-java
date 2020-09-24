package util;

public enum ErrorMessage {

	MEMBER_EXISTS("Anetari qe doni te shtoni  ekziston. "),
	MEMBER_DOES_NOT_EXIST("Anetari qe doni te fshini nuk ekziston. "),
	MEMBER_CAN_NOT_LOGIN("Te dhenat nuk jane te sakta. "),
    STAFF_EXISTS("Staff qe doni te shtoni ekziston. "),
    STAFF_DOES_NOT_EXIST("Staff qe doni te fshini nuk ekziston. "),
    CATEGORY_DOES_NOT_EXIST("Kategoria nuk ekziston. "),
	ADMIN_DOES_NOT_EXIST("Te dhenat e administratorit nuk jane te sakta. ");
	
	private String errorMessage;

	ErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}

