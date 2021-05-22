package com.inovvet.core.repository.cadastro.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.classificacao.Classificacao;
import com.inovvet.core.entity.classificacao.dto.OpcaoDTO;

@Repository
public interface ClassificacaoRepository extends JpaRepository<Classificacao, Integer>, ClassificacaoRepositoryCustom{

	@Query("SELECT c from Classificacao c "
			+ " inner join fetch c.departamento d"
			+ " inner join fetch c.categoria ca"
			+ " inner join fetch c.grupo g"
			+ " inner join fetch c.subgrupo s "
			+ "where "
			+ " d.id = :departamento_id and "
			+ " ca.id = :categoria_id and "
			+ " g.id = :grupo_id and "
			+ " s.id = :subgrupo_id  and "
			+ " c.ativo = 1")
	public List<Classificacao> listarClassificacao(
			@Param("departamento_id") Integer departamento_id,			
			@Param("categoria_id") Integer categoria_id,
			@Param("grupo_id") Integer grupo_id,
			@Param("subgrupo_id") Integer subgrupo_id
			);
	
	@Query("SELECT c from Classificacao c"
			+ " inner join fetch c.departamento "
			+ " inner join fetch c.categoria "
			+ " inner join fetch c.grupo "
			+ " inner join fetch c.subgrupo "
			+ "where "
			+ " c.ativo = 1")
	public List<Classificacao> listarClassificacaoAtiva();	
	
	
	
	@Query("SELECT new com.inovvet.core.entity.classificacao.dto.OpcaoDTO(c.id, CONCAT(ca.nome,' / ', g.nome,' / ', s.nome), CONCAT(d.nome,' / ',ca.nome,' / ', g.nome,' / ', s.nome)) from Classificacao c "
			+ " inner join  c.departamento d"
			+ " inner join  c.categoria ca "
			+ " inner join  c.grupo g"
			+ " inner join  c.subgrupo s "
			+ "where "
			+ " c.departamento.id = :departamentoId and "
			+ " c.ativo = 1")
	public List<OpcaoDTO> listarDescricao(@Param("departamentoId") Integer departamentoId);	
	
	
	@Query("SELECT new com.inovvet.core.entity.classificacao.dto.OpcaoDTO(c.id, CONCAT(ca.nome,' / ', g.nome,' / ', s.nome), CONCAT(d.nome,' / ',ca.nome,' / ', g.nome,' / ', s.nome)) from Classificacao c "
			+ " inner join  c.departamento d"
			+ " inner join  c.categoria ca "
			+ " inner join  c.grupo g"
			+ " inner join  c.subgrupo s "
			+ "where "
			+ " c.id = :id and "
			+ " c.ativo = 1")
	public OpcaoDTO carregar(@Param("id") Integer id);	

	
	
	
	

}
