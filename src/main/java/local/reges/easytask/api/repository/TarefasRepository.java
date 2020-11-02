package local.reges.easytask.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import local.reges.easytask.api.model.Tarefa;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TarefasRepository extends MongoRepository<Tarefa, String> {
    List<Tarefa> findByDescricaoLikeIgnoreCase(String descricao);
}
