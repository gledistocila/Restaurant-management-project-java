package util;

public enum ErrorMessage {

	MEMBER_EXISTS("Anetari qe doni te shtoni  ekziston. "),
	MEMBER_DOES_NOT_EXIST("Anetari qe doni te fshini nuk ekziston. "),
    STAFF_EXISTS("Staff qe doni te shtoni ekziston. "),
    STAFF_DOES_NOT_EXIST("Staff qe doni te fshini nuk ekziston. ");
	private String errorMessage;

	ErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}

