package br.com.fernando.biritashop.controllers;

import java.util.List;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fernando.biritashop.model.Produto;
import br.com.fernando.biritashop.repositories.ProdutoRepository;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepo;

    public ProdutoController(ProdutoRepository produtoRepo) {
        this.produtoRepo = produtoRepo;
    }

    @GetMapping("/listarProduto")
    public ModelAndView listarProduto() {
        List<Produto> todosOsProdutos = produtoRepo.findAll();
        ModelAndView modelAndView = new ModelAndView("/produto/listarProduto");
        modelAndView.addObject("todosOsProdutos", todosOsProdutos);
        return modelAndView;
    }

    @GetMapping("/adicionarProduto")
    public ModelAndView formularioAdicionarProdutos() {
        ModelAndView modelAndView = new ModelAndView("/produto/adicionarProduto");
        modelAndView.addObject(new Produto());
        return modelAndView;
    }

    @PostMapping("/adicionarProduto")
    public String adicionarProduto(Produto p) {
        p.setDataProduto(LocalDate.now());
        this.produtoRepo.save(p);
        return "redirect:/produto/listarProduto";
    }

    @GetMapping("/remover/{id}")
    public ModelAndView removerProduto(@PathVariable("id") long id) {
        Produto aRemover = produtoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido=" + id));
        produtoRepo.delete(aRemover);
        return new ModelAndView("redirect:/produto/listarProduto");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView formularioEditarProdutos(@PathVariable("id") long id) {
        Produto aEditar = produtoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido=" + id));
        ModelAndView modelAndView = new ModelAndView("/produto/editarProduto");
        modelAndView.addObject(aEditar);
        return modelAndView;
    }

    @PostMapping("/editar/{id}")
    public String editarProduto(@PathVariable("id") long id, Produto p) {
        p.setDataProduto(LocalDate.now());
        this.produtoRepo.save(p);
        return "redirect:/produto/listarProduto";
    }

}
