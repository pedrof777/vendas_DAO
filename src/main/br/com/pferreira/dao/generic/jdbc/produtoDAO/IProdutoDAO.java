package br.com.pferreira.dao.generic.jdbc.produtoDAO;

import br.com.pferreira.domain.Produto;

import java.util.List;

/**
 * @author Pedro Ferreira
 */

public interface IProdutoDAO {

  public Integer create(Produto produto) throws Exception;

  public Integer update(Produto produto) throws Exception;

  public Produto search(String nome) throws Exception;

  public List<Produto> searchAll() throws Exception;

  public Integer delete(Produto produto) throws Exception;
}
