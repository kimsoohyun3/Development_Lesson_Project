package project.lesson.exception;

public enum ExceptionEnum {
	//인증 메일 관련 에러
	AUTH_MAIL_SEND_FAIL(1001, "잘못된 수신자 주소이거나 메일 서버와의 연결 문제가 발생하였습니다.");

	int code;
	String message;

	ExceptionEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}
}
