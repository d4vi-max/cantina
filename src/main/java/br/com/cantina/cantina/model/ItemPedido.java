package br.com.cantina.cantina.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    @Column(nullable = false)
    private int quantidade;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnitario;

    public ItemPedido(Pedido pedido, Produto produto, int quantidade){
        this.pedido = pedido;
        this.produto = validarProduto(produto);
        this.quantidade = validarQuantidade(quantidade);
        this.precoUnitario = produto.getPreco();
    }

    public ItemPedido(){}

    //SET

    public void setPedido(Pedido pedido){
        this.pedido = pedido;
    }

    public void setProduto(Produto produto){
        this.produto = validarProduto(produto);
    }

    public void setQuantidade (int quantidade){
        this.quantidade = validarQuantidade(quantidade);
    }

    // GET


    public Long getId() {
        return id;
    }

    public Pedido getPedido(){
        return pedido;
    }

    public Produto getProduto(){
        return produto;
    }

    public int getQuantidade (){
        return quantidade;
    }

    public BigDecimal getPrecoUnitario (){
        return  precoUnitario;
    }

    //Verificação

    private int validarQuantidade (int quantidade){
        if(quantidade <= 0){
            throw new IllegalArgumentException("ERRO: quantidade deve ser maior do que 0 (zero)!");
        }
        return quantidade;
    }

    private Produto validarProduto (Produto produto){
        if(produto == null){
            throw new IllegalArgumentException("ERRO: Produto invalido!");
        }
        return produto;
    }





}
