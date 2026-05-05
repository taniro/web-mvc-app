package ufrn.br.webmvcapp.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ufrn.br.webmvcapp.domain.Tarefa;
import ufrn.br.webmvcapp.dto.TarefaDTO;
import ufrn.br.webmvcapp.service.TarefaService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TarefaController {

    TarefaService service;

    public TarefaController(TarefaService tarefaService){
        this.service = tarefaService;
    }

    @GetMapping(value = {"/dashboard", "/"})
    public String getDashboardPage(Model model){

        model.addAttribute("tarefas", service.listarTodos());
        return "dashboard";
    }

    @GetMapping("/cadastro")
    public String getCadastroPage(Model model){
        model.addAttribute("tarefa", new Tarefa());
        return "cadastro";
    }

    @PostMapping("/salvar")
    public String doProcessSalvar(@ModelAttribute("tarefa")  @Valid TarefaDTO tarefaDTO, BindingResult result){

        if (result.hasErrors()){
            return "cadastro";
        }

        Tarefa t = new Tarefa(tarefaDTO.descricao(), tarefaDTO.ativo());
        service.salvar(t);
        return "redirect:/dashboard";
    }

    @GetMapping("/deletar/{id}")
    public String doProcessarDelete(@PathVariable("id") Long id){
        service.deletar(id);
        return "redirect:/dashboard";
    }



}
