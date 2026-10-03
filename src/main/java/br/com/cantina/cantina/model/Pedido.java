package br.com.cantina.cantina.model;

import br.com.cantina.cantina.model.enums.Status;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "janela_id", nullable = false)
    private JanelaRetirada janelaRetirada;
    @Column(nullable = false)
    private LocalDate dataRetirada;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;
    @Column(nullable = false)
    private String codigoRetirada;
    @Column(nullable = false)
    private LocalDateTime criadoEm;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido(Usuario usuario, JanelaRetirada janelaRetirada, LocalDate dataRetirada, String codigoRetirada){
        this.usuario = usuario;
        this.janelaRetirada = janelaRetirada;
        this.dataRetirada = dataRetirada;
        this.codigoRetirada = codigoRetirada;
        this.criadoEm = LocalDateTime.now();
        this.status = Status.CRIADO;
    }

    public Pedido(){}

    //SET

    public void setDataRetirada(LocalDate dataRetirada){
        this.dataRetirada = dataRetirada;
    }

    public void setTotal (BigDecimal total){
        this.total =total;
    }

    public void setCodigoRetirada (String codigoRetirada){
        this.codigoRetirada = codigoRetirada;
    }


    public void mudarStatus(Status novo){
        boolean permitido = switch (this.status){
            case CRIADO -> novo == Status.CONFIRMADO || novo == Status.CANCELADO;
            case CONFIRMADO -> novo == Status.PRONTO || novo == Status.CANCELADO;
            case PRONTO ->  novo == Status.RETIRADO || novo == Status.NAO_RETIRADO;
            default -> false;
        };
        if(!permitido){
            throw new IllegalStateException("ERRO: Status inválido! não é possível mudar de " + this.status + "para " + novo);
        }
        this.status = novo;
    }

    public void adicionarItem(ItemPedido item){
        itens.add(item);
        item.setPedido(this);
    }

    public void removerItem(ItemPedido item){
        itens.remove(item);
        item.setPedido(null);
    }

    //GET


    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public JanelaRetirada getJanelaRetirada() {
        return janelaRetirada;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public Status getStatus() {
        return status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getCodigoRetirada() {
        return codigoRetirada;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }
}
