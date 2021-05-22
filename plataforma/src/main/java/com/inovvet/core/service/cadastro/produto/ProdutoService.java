package com.inovvet.core.service.cadastro.produto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inovvet.app.config.Contexto;
import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.service.AbstractService;
import com.inovvet.app.util.FiltroUtil;
import com.inovvet.core.entity.Arquivo.dto.ArquivoCadastroDTO;
import com.inovvet.core.entity.base.dto.FiltroDTO;
import com.inovvet.core.entity.classificacao.Classificacao;
import com.inovvet.core.entity.loja.Loja;
import com.inovvet.core.entity.pessoa.Marca;
import com.inovvet.core.entity.produto.Preco;
import com.inovvet.core.entity.produto.Produto;
import com.inovvet.core.entity.produto.TipoProduto;
import com.inovvet.core.entity.produto.Unidade;
import com.inovvet.core.entity.produto.dto.PrecoDTO;
import com.inovvet.core.entity.produto.dto.ProdutoCadastroDTO;
import com.inovvet.core.entity.produto.dto.ProdutoConsultaDTO;
import com.inovvet.core.entity.produto.dto.ProdutoDTO;
import com.inovvet.core.entity.produto.dto.ProdutoFiltroDTO;
import com.inovvet.core.enumerator.EnumReferenciaArquivo;
import com.inovvet.core.repository.cadastro.produto.ProdutoRepository;
import com.inovvet.core.service.cadastro.ArquivoService;
import com.inovvet.core.service.cadastro.CrudService;
import com.inovvet.core.service.cadastro.loja.LojaService;
import com.inovvet.core.service.cadastro.pessoa.FabricanteService;

@Service
public class ProdutoService extends AbstractService
		implements CrudService<Produto, ProdutoDTO, ProdutoCadastroDTO, ProdutoFiltroDTO> {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private ClassificacaoService classificacaoService;

	@Autowired
	private TipoProdutoService tipoProdutoService;

	@Autowired
	private ArquivoService arquivoService;

	@Autowired
	private FabricanteService fabricanteService;

	@Autowired
	private UnidadeService unidadeService;

	@Autowired
	private LojaService lojaservice;

	private Produto salvar(Produto entity) {
		try {
			return repository.save(entity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO, e.getMessage());
		}

	}

	private Set<Preco> converterPreco(Set<PrecoDTO> precos) {

		Set<Preco> lista = new HashSet<Preco>();

		precos.forEach(precoDTO -> {
			Preco preco = new Preco();
			mapper.copy(precoDTO, preco);
			preco.setId(precoDTO.getId());
			preco.setLoja(lojaservice.carregarLoja(precoDTO.getLoja().getToken()));

			lista.add(preco);
		});

		return lista;
	}

	private Produto salvar(ProdutoDTO dto, Produto produto) {

		validar(dto);

		mapper.copy(dto, produto, "unidade", "tipoProduto", "classificacao", "arquivos", "precos");

		produto.getPrecos().clear();

		Classificacao classificacao = classificacaoService.localizar(dto.getClassificacao().getId());
		TipoProduto tipoProduto = tipoProdutoService.localizar(dto.getTipo().getId());
		Unidade unidade = unidadeService.localizar(dto.getUnidade().getId());

		Marca marca = null;

		if (tipoProduto.getNome().equals("Serviço")) {
			produto.setEan(null);
		}

		if (dto.getMarca() != null && dto.getMarca().getId() != null) {
			marca = fabricanteService.carregarMarca(dto.getMarca().getId());
		}

		Set<Preco> precos = converterPreco(dto.getPrecos());

		produto.setClassificacao(classificacao);
		produto.setTipo(tipoProduto);
		produto.setUnidade(unidade);
		produto.setMarca(marca);
		produto.getPrecos().addAll(precos);
		if (produto.getId() == null) {
			produto.setAtivo(true);
		}

		return salvar(produto);
	}

	private void validar(ProdutoDTO dto) {

	}

	private void carregarInfo(ProdutoCadastroDTO produtoCadastroDTO) {
		produtoCadastroDTO
				.setArquivos(arquivoService.carregar(EnumReferenciaArquivo.PRODUTO, produtoCadastroDTO.getId()));

		produtoCadastroDTO.setClassificacao(
				classificacaoService.carregarClassificacaoCadastro(produtoCadastroDTO.getClassificacao().getId()));
	}
	
	private Preco carregarPreco(Produto produto, Loja loja) {
		for (Preco preco : produto.getPrecos()) {
			if(preco != null) {
				if (preco.getLoja().getId().equals(loja.getId())) {
					return preco;
				}
			}
		}
		return null;
	}

	private ProdutoConsultaDTO converter(Produto produto, Loja loja) {
		ProdutoConsultaDTO dto = new ProdutoConsultaDTO();
		dto.setId(produto.getId());
		dto.setDescricao(produto.getDescricao());
		dto.setEan(produto.getEan());

		dto.setNome(produto.getNome());
		
		Preco preco = carregarPreco(produto, loja);
		
		dto.setPreco(preco.getPreco());
		

		if (produto.getMarca() != null) {
			dto.setMarca(produto.getMarca().getNome());
		}

		List<ArquivoCadastroDTO> arquivos = arquivoService.carregar(EnumReferenciaArquivo.PRODUTO, produto.getId());

		if (arquivos != null && arquivos.size() > 0) {
			dto.setUrl(arquivoService.carregar(EnumReferenciaArquivo.PRODUTO, produto.getId()).get(0).getUrl());
		}

		return dto;
	}

	@Override
	public Produto localizar(Integer id) {
		return repository.findById(id).orElseThrow(
				() -> new ServiceException(EnumError.REGISTRO_NAO_ECONTRADO, "Registro Produto não encontrado"));
	}

	@Override
	public ProdutoCadastroDTO carregar(Integer id) {

		ProdutoCadastroDTO produtoCadastroDTO = mapper.map(localizar(id), ProdutoCadastroDTO.class);

		carregarInfo(produtoCadastroDTO);

		return produtoCadastroDTO;
	}

	@Override
	@Transactional
	public ProdutoCadastroDTO novo(ProdutoDTO dto) {
		Produto produto = salvar(dto, new Produto());
		ProdutoCadastroDTO produtoCadastroDTO = mapper.map(produto, ProdutoCadastroDTO.class);

		carregarInfo(produtoCadastroDTO);

		return produtoCadastroDTO;
	}

	@Override
	public ProdutoCadastroDTO atualizar(Integer id, ProdutoDTO dto) {

		Produto produto = localizar(id);

		ProdutoCadastroDTO produtoCadastroDTO = mapper.map(salvar(dto, produto), ProdutoCadastroDTO.class);

		carregarInfo(produtoCadastroDTO);

		return produtoCadastroDTO;
	}

	@Override
	public void remover(Produto entity) {
		try {
			repository.delete(entity);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_REMOVER_REGISTRO, "Registro já utilizado não pode ser removido");
		}

	}

	@Override
	public void remover(Integer id) {
		remover(localizar(id));
	}

	@Override
	public List<ProdutoCadastroDTO> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ProdutoCadastroDTO> filtrar(FiltroDTO<ProdutoFiltroDTO> filtroDTO) {
		Example<Produto> example = FiltroUtil.getByExample(filtroDTO, Produto.class);

		Page<Produto> list = repository.findAll(example,
				PageRequest.of(filtroDTO.getPage(), filtroDTO.getSize(), Sort.by(Sort.Direction.ASC, "nome")));

		return list.map(entity -> {
			return mapper.map(entity, ProdutoCadastroDTO.class);
		});
	}

	public List<ProdutoConsultaDTO> consultar(String nome) {

		List<ProdutoConsultaDTO> lista = new ArrayList<ProdutoConsultaDTO>();

		repository.findByNomeContainingIgnoreCase(nome).forEach(produto -> {
			lista.add(converter(produto, Contexto.getLoja()));
		});
		return lista;

	}

	public ProdutoConsultaDTO consultar(Loja loja, Integer id) {
		Produto produto = repository.findById(id).orElseThrow(
				() -> new ServiceException(EnumError.REGISTRO_NAO_ECONTRADO, "Registro Produto não encontrado"));

		return converter(produto, loja);
	}

}
