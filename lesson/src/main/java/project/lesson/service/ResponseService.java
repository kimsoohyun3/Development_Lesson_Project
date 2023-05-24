package project.lesson.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import project.lesson.model.response.CommonResult;
import project.lesson.model.response.ListResult;
import project.lesson.model.response.SingleResult;

import java.util.List;

@Service
public class ResponseService {

    @Getter
    public enum CommonResponse {
        SUCCESS(0, "요청 성공하였습니다"); // 응답 성공

        int code; // 응답 코드번호
        String message; // 응답 메시지

        CommonResponse(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    // 단건 결과 처리
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    // 리스트 결과 처리
    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }

    // 성공 결과만 처리
    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }

    // 실패 결과만 처리
    public CommonResult getFailureResult(int code, String message) {
        CommonResult result = new CommonResult();
        result.setSuccessOrFailure(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    // 결과 모델에 API 응답 성공 데이터를 세팅
    private void setSuccessResult(CommonResult result) {
        result.setSuccessOrFailure(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMessage(CommonResponse.SUCCESS.getMessage());
    }
}
