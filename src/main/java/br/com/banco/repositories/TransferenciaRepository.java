package br.com.banco.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.banco.entities.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {

	List<Transferencia> findAllByContaIdConta(Integer contaId);
	
	@Query("SELECT obj FROM Transferencia obj WHERE (obj.data BETWEEN :minDate AND :maxDate) "
			+ "AND obj.conta.idConta = :contaId")
	List<Transferencia> findByContaAndDates(Integer contaId, LocalDateTime minDate, LocalDateTime maxDate);
	
	List<Transferencia> findAllByContaIdContaAndNomeOperadorTransacao(Integer contaId, String nomeOperador);
	
	@Query("SELECT obj FROM Transferencia obj WHERE (obj.data BETWEEN :minDate AND :maxDate) "
			+ "AND obj.conta.idConta = :contaId "
			+ "AND obj.nomeOperadorTransacao = :nomeOperador")
	List<Transferencia> findByContaAndDatesAndOperador(Integer contaId, LocalDateTime minDate, LocalDateTime maxDate, String nomeOperador);

}
