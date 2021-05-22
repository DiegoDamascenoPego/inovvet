package com.inovvet.core.service.cadastro.pessoa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.service.AbstractService;
import com.inovvet.core.entity.cliente.dto.EnderecoDTO;
import com.inovvet.core.entity.cliente.dto.TelefoneDTO;
import com.inovvet.core.entity.pessoa.Endereco;
import com.inovvet.core.entity.pessoa.Telefone;
import com.inovvet.core.service.cadastro.clientes.CidadeService;

@Service
public class PessoaService extends AbstractService {

	@Autowired
	private CidadeService cidadeService;


	public Set<Endereco> converterEndereco(Set<EnderecoDTO> enderecos) {

		Set<Endereco> lista = new HashSet<Endereco>();
		enderecos.forEach(endereco -> {
			Endereco end = new Endereco();

			mapper.copy(endereco, end);
			end.setId(endereco.getId());

			end.setCidade(cidadeService.buscar(endereco.getCidade().getCodigo())
					.orElseThrow(() -> new ServiceException(EnumError.PARAMETROS_INVALIDOS, "Cidade não encontrada")));
			lista.add(end);
		});

		return lista;
	}

	public Set<Telefone> converterTelefone(Set<TelefoneDTO> telefones) {

		Set<Telefone> lista = new HashSet<Telefone>();
		telefones.forEach(telefone -> {
			Telefone fone = new Telefone();
			mapper.copy(telefone, fone);
			fone.setId(telefone.getId());

			lista.add(fone);
		});

		return lista;
	}


}
