package br.com.cantina.cantina.dto;

import org.springframework.util.AutoPopulatingList;

import java.util.List;

public class PedidoFormDTO {

    private Long janelaId;
    private List<ItemCarrinhoDTO> itens = new AutoPopulatingList<>(ItemCarrinhoDTO.class);

    public PedidoFormDTO(Long janelaId, List<ItemCarrinhoDTO> itens){
        this.janelaId = janelaId;
        this.itens = itens;
    }

    public PedidoFormDTO(){}

    public void setJanelaId(Long janelaId) {
        this.janelaId = janelaId;
    }

    public void setItens(List<ItemCarrinhoDTO> itens) {
        this.itens = itens;
    }

    public List<ItemCarrinhoDTO> getItens() {
        return itens;
    }

    public Long getJanelaId() {
        return janelaId;
    }


}
