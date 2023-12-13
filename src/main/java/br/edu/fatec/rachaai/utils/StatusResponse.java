package br.edu.fatec.rachaai.utils;

import br.edu.fatec.rachaai.enums.StatusCode;
import lombok.Data;

@Data
public class StatusResponse {

    private final String error;
    private final String message;

    public StatusResponse(StatusCode errorCode) {
        this.error = errorCode.name();
        this.message = errorCode.getMessage();
    }
}
