package br.com.fernando.cadpessoas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fernando.cadpessoas.model.Pessoa;
import br.com.fernando.cadpessoas.repositories.PessoaRepository;

@Controller
@RequestMapping("/")
public class PessoaController {

    @Autowired
    PessoaRepository pessoaRepo;

    public PessoaController(PessoaRepository pessoaRepo) {
        this.pessoaRepo = pessoaRepo;
    }

    @GetMapping
    public String index() {
        return "index.html";
    }

    @GetMapping("/listarPessoas")
    public ModelAndView listarPessoas() {
        List<Pessoa> todasAsPessoas = pessoaRepo.findAll();
        ModelAndView modelAndView = new ModelAndView("listarPessoas");
        modelAndView.addObject("todasAsPessoas", todasAsPessoas);
        return modelAndView;
    }
}
