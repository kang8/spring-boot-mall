package com.kang.mall.exception;

import com.kang.mall.common.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yikang
 * ClassName: GlobalExceptionHandler
 * Description: 全局异常处理器
 * Create Date: 2021/1/20 15:33
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Result> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>(16);
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.OK).body(Result.error(errors));
    }

    @ExceptionHandler(value = CustomizeException.class)
    public ResponseEntity<Result> handleCustomizeExceptions(
            CustomizeException e) {
        Map<String, String> errors = new HashMap<>(4);
        errors.put("error", e.getMessage());

        return ResponseEntity.status(HttpStatus.OK).body(Result.error(errors));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Result> handleExceptions(
            Exception e) {
        // 日志待输出
        e.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Result.error("服务器异常"));
    }
}
