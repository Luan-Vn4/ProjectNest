package br.upe.ProjectNest.domain.common;

public interface Mergeable<T> {

    /**
     * Faz um merge dos atributos da entidade {@link Mergeable} com a entidade passada. Caso tenha
     * algum atributo nulo, ele será ignorado no merge.
     * </br></br>
     * <b>OBS.:</b> recomenda-se que apenas valores que podem ser atualizados sejam resolvidos
     * no merge. Por exemplo, se uma entidade usuário não puder ter seu id alterado, então esse
     * atributo não será considerado.
     * @param other outra entidade com a qual você deseja realizar o merge
     */
    void merge(T other);

}
