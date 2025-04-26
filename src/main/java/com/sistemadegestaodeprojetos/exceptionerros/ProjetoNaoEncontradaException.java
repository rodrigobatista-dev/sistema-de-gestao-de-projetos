package com.sistemadegestaodeprojetos.exceptionerros;

public class ProjetoNaoEncontradaException extends RuntimeException {

    public ProjetoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

}
