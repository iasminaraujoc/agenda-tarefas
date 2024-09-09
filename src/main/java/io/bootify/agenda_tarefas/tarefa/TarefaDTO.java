package io.bootify.agenda_tarefas.tarefa;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;


public class TarefaDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String titulo;

    @Size(max = 255)
    private String descricao;

    private LocalDateTime horario;

    private Boolean concluida;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(final LocalDateTime horario) {
        this.horario = horario;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(final Boolean concluida) {
        this.concluida = concluida;
    }

}
