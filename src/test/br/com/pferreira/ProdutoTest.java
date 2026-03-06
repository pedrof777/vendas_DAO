package br.com.pferreira;

import br.com.pferreira.dao.generic.jdbc.produtoDAO.IProdutoDAO;
import br.com.pferreira.dao.generic.jdbc.produtoDAO.ProdutoDAO;
import br.com.pferreira.domain.Produto;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Pedro Ferreira
 */

public class ProdutoTest {
  private IProdutoDAO produtoDAO;

  @Test
  public void createTest() throws Exception {
    produtoDAO = new ProdutoDAO();

    Produto produto = new Produto();
    produto.setValor(new BigDecimal("20.00"));
    produto.setNomeP("Bola");
    Integer countCre = produtoDAO.create(produto);
    assertTrue(countCre == 1);

    Produto produtoDB = produtoDAO.search("Bola");
    assertNotNull(produtoDB);
    assertEquals(produto.getNomeP(), produtoDB.getNomeP());
    assertEquals(produto.getValor(), produtoDB.getValor());

    Integer countDel = produtoDAO.delete(produtoDB);
    assertTrue(countDel == 1);
  }

  @Test
  public void searchTest() throws Exception {
    produtoDAO = new ProdutoDAO();

    Produto produto = new Produto();
    produto.setValor(new BigDecimal("20.00"));
    produto.setNomeP("Bola");
    Integer countCre = produtoDAO.create(produto);
    assertTrue(countCre == 1);


    Produto produtoDB = produtoDAO.search("Bola");
    assertNotNull(produtoDB);
    assertEquals(produto.getNomeP(), produtoDB.getNomeP());
    assertEquals(produto.getValor(), produtoDB.getValor());


    Integer countDel = produtoDAO.delete(produtoDB);
    assertTrue(countDel == 1);
  }

  @Test
  public void deleteTest() throws Exception {
    produtoDAO = new ProdutoDAO();

    Produto produto = new Produto();
    produto.setValor(new BigDecimal("20.00"));
    produto.setNomeP("Bola");
    Integer countCre = produtoDAO.create(produto);
    assertTrue(countCre == 1);


    Produto produtoDB = produtoDAO.search("Bola");
    assertEquals(produto.getNomeP(), produtoDB.getNomeP());
    assertEquals(produto.getValor(), produtoDB.getValor());


    Integer countDel = produtoDAO.delete(produtoDB);
    assertTrue(countDel == 1);
  }

  @Test
  public void searchAll() throws Exception {
    produtoDAO = new ProdutoDAO();

    Produto produto = new Produto();
    produto.setValor(new BigDecimal("20.00"));
    produto.setNomeP("Bola");
    Integer countCre = produtoDAO.create(produto);
    assertTrue(countCre == 1);

    Produto produto2 = new Produto();
    produto2.setValor(new BigDecimal("47.00"));
    produto2.setNomeP("Bicicleta");
    Integer countCre2 = produtoDAO.create(produto2);
    assertTrue(countCre2 == 1);

    List<Produto> list = produtoDAO.searchAll();
    assertNotNull(list);
    assertEquals(2, list.size());

    int countDel = 0;
    for(Produto prod : list){
      produtoDAO.delete(prod);
      countDel++;
    }
    assertEquals(list.size(), countDel);

    list = produtoDAO.searchAll();
    assertEquals(list.size(), 0);

  }

  @Test
  public void  updateTest() throws Exception {
    produtoDAO = new ProdutoDAO();

    Produto produto = new Produto();
    produto.setValor(new BigDecimal("20.00"));
    produto.setNomeP("Bola");
    Integer countCre = produtoDAO.create(produto);
    assertTrue(countCre == 1);


    Produto produtoDB = produtoDAO.search("Bola");
    assertNotNull(produtoDB);
    assertEquals(produto.getNomeP(), produtoDB.getNomeP());
    assertEquals(produto.getValor(), produtoDB.getValor());

    produtoDB.setNomeP("Bicicleta");
    produtoDB.setValor(new BigDecimal("39.00"));
    Integer countUp = produtoDAO.update(produtoDB);
    assertTrue(countUp == 1);

    Produto produtoDB1 = produtoDAO.search("Bola");
    assertNull(produtoDB1);

    Produto produtoAtualizado = produtoDAO.search("Bicicleta");
    assertNotNull(produtoAtualizado);
    assertEquals(produtoDB.getNomeP(), produtoAtualizado.getNomeP());

    List<Produto> list = produtoDAO.searchAll();
    for (Produto prod : list){
      produtoDAO.delete(prod);
    }
  }
}
