package br.com.cantina.cantina.model;

import br.com.cantina.cantina.model.enums.Categoria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nome;
    private String descricao;
    private BigDecimal preco;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private boolean ativo;
    private boolean estoqueDia;

    public Produto (String nome, String descricao, BigDecimal preco, Categoria categoria, boolean ativo, boolean estoqueDia){
        this.nome = validarNome(nome);
        this.descricao = descricao;
        this.preco = validarPreco(preco);
        this.categoria = categoria;
        this.ativo = ativo;
        this.estoqueDia = estoqueDia;
    }

    //SET

    public void setNome(String nome){
        this.nome = validarNome(nome);
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public void setPreco (BigDecimal preco){
        this.preco = validarPreco(preco);
    }

    public void setAtivo (boolean ativo){
        this.ativo = ativo;
    }

    public void setEstoqueDia (boolean estoqueDia){
        this.estoqueDia = estoqueDia;
    }

    //GET

    public Long getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getDescricao(){
        return descricao;
    }

    public BigDecimal getPreco (){
        return preco;
    }

    public Categoria getCategoria(){
        return categoria;
    }

    public boolean getAtivo (){
        return ativo;
    }

    public boolean getEstoqueDia (){
        return estoqueDia;
    }

    //VERFICAÇÃO

    private BigDecimal validarPreco (BigDecimal preco){

        if(preco.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Erro: O preço não pode ser zero ou negativo!");
        }
            return preco;
    }

    private String validarNome (String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("ERRO: Adicionar nome do produto");
        }
        return nome;
    }

}
