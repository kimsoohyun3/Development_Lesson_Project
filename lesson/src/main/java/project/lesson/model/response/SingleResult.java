package project.lesson.model.response;

import lombok.Data;

@Data
public class SingleResult<T> extends CommonResult {

    private T data;
}
