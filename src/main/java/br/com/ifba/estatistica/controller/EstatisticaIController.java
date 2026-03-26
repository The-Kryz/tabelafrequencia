package br.com.ifba.estatistica.controller;

import br.com.ifba.estatistica.entity.FrequenciaRow;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface EstatisticaIController {
    ResponseEntity<List<FrequenciaRow>> getTabelaDiscreta(List<Integer> dados);
    ResponseEntity<List<FrequenciaRow>> getTabelaContinua(List<Integer> dados);
}