package project.lesson.exception;

public enum ExceptionEnum {

	// 요청 자원 존재하지 않는 에러
	RESOURCE_NOT_EXIST(1007, "요청하신 자원이 존재하지 않습니다."),

	// 인증 메일 관련 에러
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
