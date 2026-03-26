package br.com.ifba.estatistica.controller;

import br.com.ifba.estatistica.entity.FrequenciaRow;
import br.com.ifba.estatistica.service.EstatisticaIService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/frequencia")
@RequiredArgsConstructor
@Slf4j
public class EstatisticaController implements EstatisticaIController {

    private final EstatisticaIService estatisticaService;

    @PostMapping("/discreta")
    public ResponseEntity<List<FrequenciaRow>> getTabelaDiscreta(@RequestBody List<Integer> dados) {
        log.info("Controller: Recebendo dados para tabela discreta");
        return ResponseEntity.ok(estatisticaService.gerarTabelaDiscreta(dados));
    }

    @PostMapping("/continua")
    public ResponseEntity<List<FrequenciaRow>> getTabelaContinua(@RequestBody List<Integer> dados) {
        log.info("Controller: Recebendo dados para tabela contínua");
        return ResponseEntity.ok(estatisticaService.gerarTabelaContinua(dados));
    }
}