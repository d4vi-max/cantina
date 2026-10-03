package br.com.cantina.cantina.service;


import br.com.cantina.cantina.dto.ItemCarrinhoDTO;
import br.com.cantina.cantina.model.*;
import br.com.cantina.cantina.repository.PedidoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;
    private final JanelaRetiradaService janelaRetiradaService;
    private final Random random = new Random();

    public PedidoService(PedidoRepository pedidoRepository, UsuarioService usuarioService, ProdutoService produtoService, JanelaRetiradaService janelaRetiradaService){
        this.pedidoRepository = pedidoRepository;
        this.produtoService = produtoService;
        this.usuarioService = usuarioService;
        this.janelaRetiradaService = janelaRetiradaService;
    }


    @Transactional
    public Pedido criarPedido(Usuario usuario, JanelaRetirada janelaRetirada, LocalDate data, List<ItemCarrinhoDTO> carrinho){
        if(carrinho == null || carrinho.isEmpty()){
            throw new IllegalArgumentException("ERRO: Não é possível realizar pedidos com carrinho vazio!");
        }
        if(!usuarioService.podeFazerPedido(usuario)){
            throw new IllegalStateException("ERRO: usuário não pode pedir, por favor verifique novamente!");
        }
        if(LocalTime.now().isAfter(janelaRetirada.getHoraCorte())){
            throw new IllegalStateException("ERRO: Não é possível realizer pedidos depois da hora de corte!");
        }

        String codigo = gerarCodigo(data);
        Pedido pedido = new Pedido(usuario, janelaRetirada, data, codigo);

        for(ItemCarrinhoDTO dto : carrinho){
            Produto produto = produtoService.buscarPorId(dto.getProdutoId());
            if(produto.getEstoqueDia() < dto.getQuantidade()){
                throw new IllegalStateException("ERRO: estoque insuficiente para " + produto.getNome());
            }
            ItemPedido item = new ItemPedido(pedido, produto, dto.getQuantidade());
            pedido.adicionarItem(item);
            produto.setEstoqueDia(produto.getEstoqueDia() - item.getQuantidade());
        }

        BigDecimal total =  BigDecimal.ZERO;

        for(ItemPedido item : pedido.getItens()){
            BigDecimal subtotal = item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()));
            total = total.add(subtotal);
        }

        pedido.setTotal(total);
        return pedidoRepository.save(pedido);

    }

    private String gerarCodigo (LocalDate data){
        String codigo;
        do {
            codigo = String.valueOf(random.nextInt(1000, 10000));
        } while (pedidoRepository.findByDataRetiradaAndCodigoRetirada(data, codigo).isPresent());
        return codigo;
    }

}
