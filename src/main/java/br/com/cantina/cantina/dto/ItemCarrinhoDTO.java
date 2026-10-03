package br.com.cantina.cantina.dto;

public class ItemCarrinhoDTO {

    private Long produtoId;
    private int quantidade;

    public ItemCarrinhoDTO (Long produtoId, int quantidade){
        this.quantidade = quantidade;
        this.produtoId = produtoId;
    }

    public ItemCarrinhoDTO (){}

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

}
