package br.com.fernando.biritashop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fernando.biritashop.model.Cliente;
import br.com.fernando.biritashop.repositories.ClienteRepository;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepo;

    public ClienteController(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    @GetMapping("/listarCliente")
    public ModelAndView listarCliente() {
        List<Cliente> todosOsClientes = clienteRepo.findAll();
        ModelAndView modelAndView = new ModelAndView("/cliente/listarCliente");
        modelAndView.addObject("todosOsClientes", todosOsClientes);
        return modelAndView;
    }

    @GetMapping("/adicionarCliente")
    public ModelAndView formularioAdicionarClientes() {
        ModelAndView modelAndView = new ModelAndView("/cliente/adicionarCliente");
        modelAndView.addObject(new Cliente());
        return modelAndView;
    }

    @PostMapping("/adicionarCliente")
    public String adicionarCliente(Cliente p) {
        this.clienteRepo.save(p);
        return "redirect:/cliente/listarCliente";
    }

    @GetMapping("/remover/{id}")
    public ModelAndView removerCliente(@PathVariable("id") long id) {
        Cliente aRemover = this.clienteRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido=" + id));
        clienteRepo.delete(aRemover);
        return new ModelAndView("redirect:/cliente/listarCliente");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView formularioEditarClientes(@PathVariable("id") long id) {
        Cliente aEditar = this.clienteRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido=" + id));
        ModelAndView modelAndView = new ModelAndView("/cliente/editarCliente");
        modelAndView.addObject(aEditar);
        return modelAndView;
    }

    @PostMapping("/editar/{id}")
    public String editarCliente(@PathVariable("id") long id, Cliente p) {
        this.clienteRepo.save(p);
        return "redirect:/cliente/listarCliente";
    }

}
