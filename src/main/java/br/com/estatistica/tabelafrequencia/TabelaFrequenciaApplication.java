package br.com.estatistica.tabelafrequencia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.ifba.estatistica", "br.com.estatistica.tabelafrequencia"})
public class TabelaFrequenciaApplication {
    public static void main(String[] args) {
        SpringApplication.run(TabelaFrequenciaApplication.class, args);
    }
}
