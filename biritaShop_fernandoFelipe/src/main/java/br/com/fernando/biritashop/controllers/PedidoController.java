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

import br.com.fernando.biritashop.model.Cliente;
import br.com.fernando.biritashop.model.Pedido;
import br.com.fernando.biritashop.model.Produto;
import br.com.fernando.biritashop.repositories.ClienteRepository;
import br.com.fernando.biritashop.repositories.PedidoRepository;
import br.com.fernando.biritashop.repositories.ProdutoRepository;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepo;

    @Autowired
    ClienteRepository clienteRepo;

    @Autowired
    ProdutoRepository produtoRepo;

    public PedidoController(PedidoRepository pedidoRepo, ClienteRepository clienteRepo, ProdutoRepository produtoRepo) {
        this.pedidoRepo = pedidoRepo;
        this.clienteRepo = clienteRepo;
        this.produtoRepo = produtoRepo;

    }

    @GetMapping("/listarPedido")
    public ModelAndView listarPedido(@RequestParam(value = "clienteid", required = false) Long clienteid) {
        List<Cliente> todosOsClientes = this.clienteRepo.findAll();
        List<Produto> todosOsProdutos = this.produtoRepo.findAll();
        List<Pedido> todosOsPedidos = this.pedidoRepo.findByCliente_Id(clienteid);
        ModelAndView modelAndView = new ModelAndView("/pedido/listarPedido");
        modelAndView.addObject("todosOsClientes", todosOsClientes);
        modelAndView.addObject("todosOsPedidos", todosOsPedidos);
        modelAndView.addObject("todosOsProdutos", todosOsProdutos);
        modelAndView.addObject("clienteid", clienteid);
        return modelAndView;
    }

    @GetMapping("/adicionarPedido")
    public ModelAndView formularioAdicionarPedidos() {
        List<Cliente> todosOsClientes = this.clienteRepo.findAll();
        List<Produto> todosOsProdutos = this.produtoRepo.findAll();
        ModelAndView modelAndView = new ModelAndView("/pedido/adicionarPedido");
        modelAndView.addObject("todosOsClientes", todosOsClientes);
        modelAndView.addObject("todosOsProdutos", todosOsProdutos);
        modelAndView.addObject(new Pedido());
        return modelAndView;
    }

    @PostMapping("/adicionarPedido")
    public String adicionarCliente(Pedido p) {
        this.pedidoRepo.save(p);
        return "redirect:/pedido/listarPedido";
    }

    @GetMapping("/remover/{id}")
    public ModelAndView removerPedido(@PathVariable("id") long id) {
        Pedido aRemover = this.pedidoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido=" + id));
        pedidoRepo.delete(aRemover);
        return new ModelAndView("redirect:/pedido/listarPedido");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView formularioEditarPedidos(@PathVariable("id") long id) {
        Pedido aEditar = this.pedidoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido=" + id));
        List<Cliente> todosOsClientes = this.clienteRepo.findAll();
        ModelAndView modelAndView = new ModelAndView("/pedido/editarPedido");
        modelAndView.addObject("todosOsClientes", todosOsClientes);
        modelAndView.addObject(aEditar);
        return modelAndView;
    }

    @PostMapping("/editar/{id}")
    public String editarPedido(@PathVariable("id") long id, Pedido p) {
        this.pedidoRepo.save(p);
        return "redirect:/pedido/listarPedido";
    }

}
