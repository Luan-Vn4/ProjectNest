package br.upe.ProjectNest.domain.projetos.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Escopo {
    SAUDE("Saúde"),
    GERENCIAMENTO("Gerenciamento"),
    INTELIGENCIA_ARTIFICIAL("Inteligência Artificial"),
    EDUCACAO("Educação"),
    CULINARIA("Culinária"),
    JOGO("Jogo"),
    BIOLOGIA("Biologia");

    private final String descricao;

    Escopo(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static Escopo fromValue(String value) {
        return Arrays.stream(Escopo.values())
                .filter(e -> e.name().equalsIgnoreCase(value) || e.descricao.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Valor inválido para Escopo: " + value
                ));
    }
}
