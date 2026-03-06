package br.com.pferreira.domain;

import java.math.BigDecimal;

/**
 * @author Pedro Ferreira
 */

public class Produto {
  private Long id;
  private String nomeP;
  private BigDecimal valor;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNomeP() {
    return nomeP;
  }

  public void setNomeP(String nomeP) {
    this.nomeP = nomeP;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }
}
