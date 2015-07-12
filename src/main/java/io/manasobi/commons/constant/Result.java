package io.manasobi.commons.constant;

public enum Result {

	SUCCESS     ("100001", "작업이 성공했습니다."),
	FAIL        ("100002", "작업이 실패하였습니다."),
	TRUE        ("100003", "STATUS_TRUE"),
	FALSE       ("100004", "STATUS_FALSE"),
	EXISTS      ("100005", "STATUS_FALSE"),
	
	// [101XXX] REG USER
	ERROR_101001 ("100001", "존재하는 유저입니다."),
	ERROR_101002 ("100002", "패스워드가 일치하지 않습니다.");
	
	private String code;
	
	private String message;
	
	private Result(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
