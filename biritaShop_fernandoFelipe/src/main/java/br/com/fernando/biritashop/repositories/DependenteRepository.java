package br.com.fernando.biritashop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fernando.biritashop.model.Dependente;

@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Long> {

    List<Dependente> findByCliente_Id(Long clienteId);
}
