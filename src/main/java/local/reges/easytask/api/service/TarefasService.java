package local.reges.easytask.api.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.time.LocalDate;
import local.reges.easytask.api.repository.TarefasRepository;
import local.reges.easytask.api.model.Tarefa;
import java.util.List;
import local.reges.easytask.api.enums.StatusTarefa;
import local.reges.easytask.api.Validate.TarefasValidade;

@Service
public class TarefasService extends TarefasValidade {

    @Autowired
    private TarefasRepository tarefasRepository;

    @ResponseStatus(HttpStatus.OK)
    public List<Tarefa> findAll() {
        List<Tarefa> tarefas = tarefasRepository.findAll();
        tratalistaTarefasNotFound(tarefas);
        return tarefas;
    }

    @ResponseStatus(HttpStatus.OK)
    public Tarefa findById(String id) {
        Tarefa tarefa = tarefasRepository.findById(id).orElse(null);
        trataTarefaNotFound(tarefa, MENSAGEM_NOT_FOUND_CONSULTA);
        return tarefa;
    }

    @ResponseStatus(HttpStatus.OK)
    public List<Tarefa> findByDescricao(String descricao) {
        List<Tarefa> tarefas = tarefasRepository.findByDescricaoLikeIgnoreCase(descricao);
        tratalistaTarefasNotFound(tarefas);
        return tarefas;
    }

    @ResponseStatus(HttpStatus.CREATED)
    public Tarefa save(Tarefa tarefa) {
        tarefa.setDataCriacao(LocalDate.now());

        if(StringUtils.isEmpty(tarefa.getStatusTarefa())){
            tarefa.setStatusTarefa(StatusTarefa.A_FAZER);
        }
        return tarefasRepository.save(tarefa);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(String id, Tarefa tarefa) {
        Tarefa tarefaToUpdate = tarefasRepository.findById(id).orElse(null);
        trataTarefaNotFound(tarefaToUpdate, MENSAGEM_NOT_FOUND_ALTERACAO);
        tarefasRepository.save(trataCamposAlteracao(tarefa, tarefaToUpdate));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(String id) {
        Tarefa tarefaToUpdate = tarefasRepository.findById(id).orElse(null);
        trataTarefaNotFound(tarefaToUpdate, MENSAGEM_NOT_FOUND_EXCLUSAO);
        tarefasRepository.deleteById(id);
    }
}
