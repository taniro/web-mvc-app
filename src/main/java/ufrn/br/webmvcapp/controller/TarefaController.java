package ufrn.br.webmvcapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ufrn.br.webmvcapp.domain.Tarefa;
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
    public String doProcessSalvar(@ModelAttribute Tarefa tarefa){
        service.salvar(tarefa);
        return "redirect:/dashboard";
    }

    @GetMapping("/deletar/{id}")
    public String doProcessarDelete(@PathVariable("id") Long id){
        service.deletar(id);
        return "redirect:/dashboard";
    }



}
