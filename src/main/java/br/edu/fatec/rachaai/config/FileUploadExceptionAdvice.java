package br.edu.fatec.rachaai.config;

import br.edu.fatec.rachaai.enums.StatusCode;
import br.edu.fatec.rachaai.utils.StatusError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class FileUploadExceptionAdvice {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxUploadSizeExceededException() {
        return ResponseEntity.badRequest().body(new StatusError(StatusCode.IMAGE_MAX_SIZE_EXCEEDED));
    }
}