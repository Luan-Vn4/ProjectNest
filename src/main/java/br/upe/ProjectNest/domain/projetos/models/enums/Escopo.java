package br.upe.ProjectNest.domain.projetos.models.enums;

public enum Escopo {
    SAUDE("Saude"),
    GERENCIAMENTO("Gerenciamento"),
    INTELIGENCIA_ARTIFICIAL("InteligenciaArtificial"),
    EDUCACAO("Educacao"),
    CULINARIA("Culinaria"),
    JOGO("Jogo"),
    BIOLOGIA("Biologia");

    private final String escopo;

    Escopo(String escopo) {
        this.escopo = escopo;
    }

    public String getEscopo() {
        return escopo;
    }
}