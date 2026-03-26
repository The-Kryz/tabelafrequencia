package br.com.ifba.estatistica.service;

import br.com.ifba.estatistica.entity.FrequenciaRow;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/frequencia")
@RequiredArgsConstructor
@Slf4j //
public class EstatisticaService implements EstatisticaIService {

    @Override
    public List<FrequenciaRow> gerarTabelaDiscreta(List<Integer> dados) {
        log.info("Service: Gerando tabela discreta");
        Collections.sort(dados);
        Map<Integer, Long> contagem = new TreeMap<>();
        dados.forEach(d -> contagem.put(d, contagem.getOrDefault(d, 0L) + 1));

        List<FrequenciaRow> tabela = new ArrayList<>();
        long acumuladoFA = 0;
        double acumuladoFR = 0;

        for (var entry : contagem.entrySet()) {
            long fa = entry.getValue();
            double fr = (fa / (double) dados.size()) * 100;
            acumuladoFA += fa;
            acumuladoFR += fr;
            tabela.add(new FrequenciaRow(entry.getKey().toString(), fa, fr, acumuladoFA, acumuladoFR));
        }
        return tabela;
    }

    @Override
    public List<FrequenciaRow> gerarTabelaContinua(List<Integer> dados) {
        log.info("Service: Iniciando cálculos de Sturges");
        if (dados == null || dados.isEmpty()) {
            return new ArrayList<>();
        }

        Collections.sort(dados);
        int n = dados.size();
        int min = dados.get(0);
        int max = dados.get(n - 1);

        //Regra de calculo Sturges
        int k = (int) Math.round(1 + 3.3 * Math.log10(n));
        double amplitudeTotal = max - min;
        double c = Math.ceil(amplitudeTotal / k);

        List<FrequenciaRow> tabela = new ArrayList<>();
        long fac = 0;
        double frc = 0;

        for (int i = 0; i < k; i++) {
            double li = min + (i * c);
            double ls = li + c;

            final double limiteInferior = li;
            final double limiteSuperior = (i == k - 1) ? ls + 0.1 : ls; // Garante o último elemento

            long fa = dados.stream()
                    .filter(d -> d >= limiteInferior && d < limiteSuperior)
                    .count();

            double fr = (fa / (double) n) * 100;
            fac += fa;
            frc += fr;

            tabela.add(new FrequenciaRow(String.format("%.0f |- %.0f", li, ls), fa, fr, fac, frc));
        }
        return tabela;
    }
}
