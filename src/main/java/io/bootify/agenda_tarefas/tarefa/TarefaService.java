package io.bootify.agenda_tarefas.tarefa;

import io.bootify.agenda_tarefas.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaService(final TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public List<TarefaDTO> findAll() {
        final List<Tarefa> tarefas = tarefaRepository.findAll(Sort.by("horario"));
        return tarefas.stream()
                .map(tarefa -> mapToDTO(tarefa, new TarefaDTO()))
                .toList();
    }

    public TarefaDTO get(final Long id) {
        return tarefaRepository.findById(id)
                .map(tarefa -> mapToDTO(tarefa, new TarefaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final TarefaDTO tarefaDTO) {
        final Tarefa tarefa = new Tarefa();
        mapToEntity(tarefaDTO, tarefa);
        return tarefaRepository.save(tarefa).getId();
    }

    public void update(final Long id, final TarefaDTO tarefaDTO) {
        final Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(tarefaDTO, tarefa);
        tarefaRepository.save(tarefa);
    }

    public void delete(final Long id) {
        tarefaRepository.deleteById(id);
    }

    public void markCompleted(final Long id){
        final Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        tarefa.setConcluida(true);
        tarefaRepository.save(tarefa);
    }

    private TarefaDTO mapToDTO(final Tarefa tarefa, final TarefaDTO tarefaDTO) {
        tarefaDTO.setId(tarefa.getId());
        tarefaDTO.setTitulo(tarefa.getTitulo());
        tarefaDTO.setDescricao(tarefa.getDescricao());
        tarefaDTO.setHorario(tarefa.getHorario());
        tarefaDTO.setConcluida(tarefa.getConcluida());
        return tarefaDTO;
    }

    private Tarefa mapToEntity(final TarefaDTO tarefaDTO, final Tarefa tarefa) {
        tarefa.setTitulo(tarefaDTO.getTitulo());
        tarefa.setDescricao(tarefaDTO.getDescricao());
        tarefa.setHorario(tarefaDTO.getHorario());
        tarefa.setConcluida(tarefaDTO.getConcluida());
        return tarefa;
    }

}
