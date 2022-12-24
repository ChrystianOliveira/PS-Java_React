package br.com.banco.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferenciaService {

	private final TransferenciaRepository transferenciaRepository;

	public List<Transferencia> findTransferencias(Integer contaId, LocalDate minDate, LocalDate maxDate,
			String nomeOperador) {
		if (Objects.nonNull(minDate) && Objects.nonNull(maxDate) && Objects.nonNull(nomeOperador)
				&& !nomeOperador.equals("")) {
			LocalDateTime min = minDate.atTime(0, 0, 0, 0);
			LocalDateTime max = maxDate.atTime(23, 59, 59, 9999);
			return transferenciaRepository.findByContaAndDatesAndOperador(contaId, min, max, nomeOperador);
		} else if (Objects.nonNull(minDate) && Objects.nonNull(maxDate)) {
			LocalDateTime min = minDate.atTime(0, 0, 0, 0);
			LocalDateTime max = maxDate.atTime(23, 59, 59, 9999);
			return transferenciaRepository.findByContaAndDates(contaId, min, max);
		} else if (Objects.nonNull(nomeOperador) && !nomeOperador.equals("")) {
			return transferenciaRepository.findAllByContaIdContaAndNomeOperadorTransacao(contaId, nomeOperador);
		}
		return transferenciaRepository.findAllByContaIdConta(contaId);
	}

}
