package br.com.fernando.biritashop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fernando.biritashop.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
