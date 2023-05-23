package project.lesson.exception.advice;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.lesson.exception.common.CResourceNotExistException;
import project.lesson.model.response.CommonResult;
import project.lesson.service.ResponseService;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        return responseService.getFailureResult(Integer.valueOf(getMessage("unKnown.code")), getMessage("unKnown.message") + "(" + e.getMessage() + ")");
    }

    @ExceptionHandler(CResourceNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CommonResult resourceNotExistException(HttpServletRequest request, CResourceNotExistException e) {
        return responseService.getFailureResult(Integer.valueOf(getMessage("resourceNotExist.code")), getMessage("resourceNotExist.message"));
    }

    // 코드 정보에 해당하는 메시지를 조회
    private String getMessage(String code) {
        return getMessage(code, null);
    }

    // 코드 정보, 추가 argument 로 현재 locale 에 맞는 메시지를 조회
    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
