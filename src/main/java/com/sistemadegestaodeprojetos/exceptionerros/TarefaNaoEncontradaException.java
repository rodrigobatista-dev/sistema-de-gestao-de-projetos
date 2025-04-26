package com.sistemadegestaodeprojetos.exceptionerros;

public class TarefaNaoEncontradaException extends RuntimeException {

    public TarefaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
