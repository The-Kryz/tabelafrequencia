package br.com.ifba.estatistica.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrequenciaRow{
    private String classe; //"18 |- 22" ou "18"
    private long fa; //Frequencia Absoluta
    private double fr; //Frequencia Relativa (%)
    private long fac; //Frequencia Acumulada
    private double frc; //Frequencia Relativa Acumulada (%)
}
