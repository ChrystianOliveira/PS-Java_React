package br.com.banco.resource;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.entities.Transferencia;
import br.com.banco.services.TransferenciaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transferencias")
public class TransferenciaResource {

	private final TransferenciaService transferenciaService;

	@GetMapping("/conta/{contaId}")
	public List<Transferencia> findByConta(@PathVariable Integer contaId,
			@RequestParam(value = "minDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate minDate,
			@RequestParam(value = "maxDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate maxDate,
			@RequestParam(value = "nomeOperador", required = false) String nomeOperador) {
		return transferenciaService.findTransferencias(contaId, minDate, maxDate, nomeOperador);
	}

}
