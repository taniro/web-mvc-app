package ufrn.br.webmvcapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    Long id;
    String descricao;
    Integer prioridade = 0;
    Boolean ativo;

    public Tarefa(String descricao, Boolean ativo) {
        this.descricao = descricao;
        this.ativo = ativo;
    }
}
