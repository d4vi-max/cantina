package br.com.cantina.cantina.model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "janela_retirada")

public class JanelaRetirada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false)
    private LocalTime horaInicio;
    @Column(nullable = false)
    private LocalTime horaFim;
    @Column(nullable = false)
    private LocalTime horaCorte;
    @Column(nullable = false)
    private boolean ativa;

    public JanelaRetirada(String nome, LocalTime horaInicio, LocalTime horaFim, LocalTime horaCorte, boolean ativa){
        validarHorarios(horaInicio, horaFim, horaCorte);
        this.nome = validarNome(nome);
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.horaCorte = horaCorte;
        this.ativa = ativa;
    }

    public JanelaRetirada(){}

    //SET

    public void setNome(String nome){
        this.nome = validarNome(nome);
    }

    public void setHorarios(LocalTime horaInicio, LocalTime horaFim, LocalTime horaCorte){
        validarHorarios(horaInicio, horaFim, horaCorte);
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.horaCorte = horaCorte;
    }

    public void setAtiva(boolean ativa){
        this.ativa = ativa;
    }

    // GET

    public Long getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public LocalTime getHoraInicio(){
        return horaInicio;
    }

    public LocalTime getHoraFim(){
        return horaFim;
    }

    public LocalTime getHoraCorte(){
        return horaCorte;
    }

    public boolean isAtiva(){
        return ativa;
    }

    // Verificações

    private String validarNome (String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("ERRO: Preencha o campo nome!");
        }
        return nome;
    }

    private void validarHorarios(LocalTime horaInicio, LocalTime horaFim, LocalTime horaCorte){
            if(horaFim.isBefore(horaInicio) || horaFim.equals(horaInicio)){
                throw new IllegalArgumentException("ERRO: horários inválidos!");
            }

            if(horaCorte.isAfter(horaInicio) || horaCorte.equals(horaInicio)){
                throw new IllegalArgumentException("ERRO: horario de corte não pode ser depois ou igual ao horário de inicio!");
            }
    }


}
