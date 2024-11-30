package br.upe.ProjectNest.domain.projetos.models.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    PENDENTE("Pendente"),
    CONCLUIDO("Concluído"),
    EM_ANDAMENTO("Em andamento");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }
}
