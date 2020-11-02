package local.reges.easytask.api.model;

import java.time.LocalDate;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import local.reges.easytask.api.enums.StatusTarefa;
@Document
@Data
public class Tarefa {
    @Id
    private String id;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataConclusao;
    private StatusTarefa statusTarefa;
}
