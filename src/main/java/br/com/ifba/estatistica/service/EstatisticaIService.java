
package br.com.ifba.estatistica.service;

import br.com.ifba.estatistica.entity.FrequenciaRow;
import java.util.List;


public interface EstatisticaIService {
    List<FrequenciaRow> gerarTabelaDiscreta(List<Integer> dados);
    List<FrequenciaRow> gerarTabelaContinua(List<Integer> dados);
}
