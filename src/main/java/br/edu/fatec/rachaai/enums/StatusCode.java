package br.edu.fatec.rachaai.enums;

import lombok.Getter;

@Getter
public enum StatusCode {
    EMAIL_ALREADY_REGISTERED("EMAIL_ALREADY_REGISTERED", "Email já cadastrado"),
    EMAIL_NOT_FOUND("EMAIL_NOT_FOUND", "Email não encontrado"),
    EMAIL_OR_PASSWORD_INVALID("EMAIL_OR_PASSWORD_INVALID", "Email ou senha inválidos"),
    USER_NOT_FOUND("USER_NOT_FOUND", "Usuário não encontrado"),
    USER_NOT_AUTHORIZED("USER_NOT_AUTHORIZED", "Usuário não autorizado"),
    USER_NOT_LOGGED("USER_NOT_LOGGED", "Usuário não logado"),
    USER_NOT_ALLOWED("USER_NOT_ALLOWED", "Usuário não permitido"),
    USER_NOT_ALLOWED_TO_CREATE("USER_NOT_ALLOWED_TO_CREATE", "Usuário não permitido para criar"),
    USER_NOT_ALLOWED_TO_UPDATE("USER_NOT_ALLOWED_TO_UPDATE", "Usuário não permitido para atualizar"),
    USER_NOT_ALLOWED_TO_DELETE("USER_NOT_ALLOWED_TO_DELETE", "Usuário não permitido para deletar"),
    USER_NOT_ALLOWED_TO_READ("USER_NOT_ALLOWED_TO_READ", "Usuário não permitido para ler"),
    USER_NOT_ALLOWED_TO_LIST("USER_NOT_ALLOWED_TO_LIST", "Usuário não permitido para listar"),
    USER_CREATED("USER_CREATED", "Usuário criado com sucesso"),
    USER_UPDATED("USER_UPDATED", "Usuário atualizado com sucesso"),
    USER_DELETED("USER_DELETED", "Usuário deletado com sucesso"),
    USER_READ("USER_READ", "Usuário lido com sucesso"),
    USER_LISTED("USER_LISTED", "Usuário listado com sucesso"),
    USER_LOGGED("USER_LOGGED", "Usuário logado com sucesso"),
    USER_LOGGED_OUT("USER_LOGGED_OUT", "Usuário deslogado com sucesso");

    private final String code;
    private final String message;

    StatusCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

