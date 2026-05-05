package ufrn.br.webmvcapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/*
@Data
public class TarefaDTO {
    private String descricao;
    private Boolean ativo;
}

 */

public record TarefaDTO(
        @NotBlank (message = "Não existe tarefa com texto em branco.")
        String descricao,
        Boolean ativo){}