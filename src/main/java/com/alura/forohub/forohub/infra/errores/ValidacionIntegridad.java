package com.alura.forohub.forohub.infra.errores;

public class ValidacionIntegridad extends RuntimeException{
    public ValidacionIntegridad(String s) {
        super(s);
    }
}
