package br.com.alura.leilao.util;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LeilaoBuilder {
    private String nome;
    private BigDecimal valorInicial;
    private Usuario usuario;
    private LocalDate dataAbertura;
    public LeilaoBuilder comNome(String nome){
        this.nome = nome;
        return  this;
    }

    public LeilaoBuilder comValorInicial(String valorInicial) {
        this.valorInicial = new BigDecimal(valorInicial);
        return this;
    }

    public LeilaoBuilder comUsuario(Usuario user){
        this.usuario = user;
        return this;
    }

    public LeilaoBuilder comData(LocalDate data){
        this.dataAbertura = data;
        return this;
    }

    public Leilao criar(){
        return new Leilao(
                this.nome,
                this.valorInicial,
                this.dataAbertura,
                this.usuario
        );
    }
}
