package com.emas93.bff_agendadortarefas.infrastructure.exceptions;




public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
    public UnauthorizedException(String message,Throwable throwable){super(message,throwable);}
}
