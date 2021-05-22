package com.inovvet.core.repository.cadastro.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.cliente.Cliente;
import com.inovvet.core.entity.cliente.Endereco;
import com.inovvet.core.entity.cliente.Telefone;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	Cliente findByCpf(String cpf);
	
	List<Cliente> findByNomeContainingIgnoreCase(String nome);
	
	List<Cliente> findByCpfContainingIgnoreCase(String cpf);
	
	
	@Query(  "SELECT t FROM Cliente c "
			+ " INNER join c.telefones t "
			+ " WHERE  c.id = :clienteId")	
	List<Telefone> listarTelefones(Integer clienteId);
	
	@Query(  "SELECT e FROM Cliente c "
			+ " INNER join c.endereco e "
			+ " WHERE  c.id = :clienteId")	
	List<Endereco> listarEnderecos(Integer clienteId);
	
}
