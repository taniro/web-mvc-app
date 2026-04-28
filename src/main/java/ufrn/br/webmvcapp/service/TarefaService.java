package ufrn.br.webmvcapp.service;


import org.springframework.stereotype.Service;
import ufrn.br.webmvcapp.domain.Tarefa;
import ufrn.br.webmvcapp.repository.TarefaRepository;

import java.util.List;

@Service
public class TarefaService {

    TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public Tarefa salvar(Tarefa tarefa){
        return repository.save(tarefa);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

    public List<Tarefa> listarTodos(){
        return repository.findAll();
    }

}
