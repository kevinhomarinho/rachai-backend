package br.edu.fatec.rachaai.enums;

import lombok.Getter;

@Getter
public enum StatusCode {
    EMAIL_ALREADY_REGISTERED("Email já cadastrado"),
    EMAIL_NOT_FOUND("Email não encontrado"),
    EMAIL_OR_PASSWORD_INVALID("Email ou senha inválidos"),
    USER_NOT_FOUND("Usuário não encontrado"),
    USER_NOT_AUTHORIZED("Usuário não autorizado"),
    USER_NOT_LOGGED("Usuário não logado"),
    USER_NOT_ALLOWED("Usuário não permitido"),
    USER_NOT_ALLOWED_TO_CREATE("Usuário não permitido para criar"),
    USER_NOT_ALLOWED_TO_UPDATE("Usuário não permitido para atualizar"),
    USER_NOT_ALLOWED_TO_DELETE("Usuário não permitido para deletar"),
    USER_NOT_ALLOWED_TO_READ("Usuário não permitido para ler"),
    USER_NOT_ALLOWED_TO_LIST("Usuário não permitido para listar"),
    IMAGE_FORMAT_NOT_SUPPORTED("Formato de imagem não suportado"),
    USERNAME_INVALID("Username inválido"),
    RA_INVALID("RA inválido"),
    EMAIL_INVALID("Email inválido"),
    PASSWORD_INVALID("Senha inválida"),
    HORARIOS_INVALID("Horários inválidos");

    private final String message;

    StatusCode(String message) {
        this.message = message;
    }
}

