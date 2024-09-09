package io.bootify.agenda_tarefas.tarefa;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
