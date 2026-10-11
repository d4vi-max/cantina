package br.com.cantina.cantina.model.enums;

public enum Status {
    CRIADO, CONFIRMADO, PRONTO, RETIRADO, NAO_RETIRADO, CANCELADO;

    public boolean isFinal() {
        return this == RETIRADO || this == CANCELADO || this == NAO_RETIRADO;
    }
}
