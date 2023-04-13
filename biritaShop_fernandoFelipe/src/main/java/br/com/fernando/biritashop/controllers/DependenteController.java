package br.com.fernando.biritashop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.fernando.biritashop.model.Dependente;
import br.com.fernando.biritashop.model.Cliente;
import br.com.fernando.biritashop.repositories.DependenteRepository;
import br.com.fernando.biritashop.repositories.ClienteRepository;

@Controller
@RequestMapping("/dependente")
public class DependenteController {

    @Autowired
    DependenteRepository dependenteRepo;

    @Autowired
    ClienteRepository clienteRepo;

    public DependenteController(DependenteRepository dependenteRepo, ClienteRepository clienteRepo) {
        this.dependenteRepo = dependenteRepo;
        this.clienteRepo = clienteRepo;
    }

    @GetMapping("/listarDependente")
    public ModelAndView listarDependente(@RequestParam(value = "clienteid", required = false) Long clienteid) {
        List<Cliente> todosOsClientes = this.clienteRepo.findAll();
        List<Dependente> todosOsDependentes = this.dependenteRepo.findByCliente_Id(clienteid);
        ModelAndView modelAndView = new ModelAndView("/dependente/listarDependente");
        modelAndView.addObject("todosOsClientes", todosOsClientes);
        modelAndView.addObject("todosOsDependentes", todosOsDependentes);
        modelAndView.addObject("clienteid", clienteid);
        return modelAndView;
    }

    @GetMapping("/adicionarDependente")
    public ModelAndView formularioAdicionarDependentes() {
        List<Cliente> todosOsClientes = this.clienteRepo.findAll();
        ModelAndView modelAndView = new ModelAndView("/dependente/adicionarDependente");
        modelAndView.addObject("todosOsClientes", todosOsClientes);
        modelAndView.addObject(new Dependente());
        return modelAndView;
    }

    @PostMapping("/adicionarDependente")
    public String adicionarDependente(Dependente p) {
        this.dependenteRepo.save(p);
        return "redirect:/dependente/listarDependente";
    }

    @GetMapping("/remover/{id}")
    public ModelAndView removerDependente(@PathVariable("id") long id) {
        Dependente aRemover = this.dependenteRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido=" + id));
        dependenteRepo.delete(aRemover);
        return new ModelAndView("redirect:/dependente/listarDependente");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView formularioEditarDependentes(@PathVariable("id") long id) {
        Dependente aEditar = this.dependenteRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido=" + id));
        List<Cliente> todosOsClientes = this.clienteRepo.findAll();
        ModelAndView modelAndView = new ModelAndView("/dependente/editarDependente");
        modelAndView.addObject("todosOsClientes", todosOsClientes);
        modelAndView.addObject(aEditar);
        return modelAndView;
    }

    @PostMapping("/editar/{id}")
    public String editarDependente(@PathVariable("id") long id, Dependente p) {
        this.dependenteRepo.save(p);
        return "redirect:/dependente/listarDependente";
    }

}
