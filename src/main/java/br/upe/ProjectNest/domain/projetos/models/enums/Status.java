package br.upe.ProjectNest.domain.projetos.models.enums;

public enum Status {
    PENDENTE("Pendente"),
    CONCLUIDO("Concluido"),
    EM_ANDAMENTO("EmAndamento");

    private final String status;

    Status(String status) {
        this.status = status;
    }
}
