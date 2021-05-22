package com.inovvet.core.repository.cadastro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.Arquivo.Arquivo;
import com.inovvet.core.enumerator.EnumReferenciaArquivo;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Integer> {
	
	List<Arquivo> findByReferenciaAndReferenciaId(EnumReferenciaArquivo referencia, Integer referenciaid);

}
