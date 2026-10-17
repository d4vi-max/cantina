package br.com.cantina.cantina.dto;

import org.springframework.util.AutoPopulatingList;

import java.util.List;

public class PedidoFormDTO {

    private Long janelaId;
    private List<ItemCarrinhoDTO> itens = new AutoPopulatingList<>(ItemCarrinhoDTO.class);
    private String login;

    public PedidoFormDTO(Long janelaId, List<ItemCarrinhoDTO> itens, String login){
        this.janelaId = janelaId;
        this.itens = itens;
        this.login = login;
    }

    public PedidoFormDTO(){}

    public void setJanelaId(Long janelaId) {
        this.janelaId = janelaId;
    }

    public void setItens(List<ItemCarrinhoDTO> itens) {
        this.itens = itens;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<ItemCarrinhoDTO> getItens() {
        return itens;
    }

    public Long getJanelaId() {
        return janelaId;
    }

    public String getLogin() {
        return login;
    }
}
