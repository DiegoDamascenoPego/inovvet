package com.inovvet.core.service.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.util.FunctionUtil;
import com.inovvet.core.entity.dto.EnderecoDTO;
import com.inovvet.core.enumerator.EnumTipoDocumento;
import com.inovvet.core.service.AbstractService;
import com.inovvet.core.service.CidadeService;

@Service
public class PessoaService extends AbstractService {

	@Autowired
	private CidadeService cidadeService;

	@Autowired
	private ConsultaPessoaWS consultaPessoaWS;

	private EnderecoDTO carregarEndereco(PessoaReceita dto) {

		try {
			EnderecoDTO endereco = cidadeService.buscarCep(dto.getCep());
			endereco.setNumero(dto.getNumero());
			return endereco;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;

	}


	public PessoaConsultaDTO consultar(String cnpj) {

		PessoaConsultaDTO pessoa = new PessoaConsultaDTO();

		PessoaReceita dto = consultaPessoaWS.consulta(cnpj);

		if (dto != null) {
			pessoa.setTipoDocumento(EnumTipoDocumento.CNPJ);
			pessoa.setDocumento(FunctionUtil.removerMascaraTexto(dto.getCnpj()));
			pessoa.setRazaoSocial(dto.getNome());
			pessoa.setNomeFantasia(dto.getNomeFantasia());
			pessoa.setEmail(dto.getEmail());

			pessoa.setEndereco(carregarEndereco(dto));

			return pessoa;

		} else {
			new ServiceException(EnumError.REGISTRO_NAO_ECONTRADO, cnpj + " informado não econtrado");
		}

		return null;
	}

}
