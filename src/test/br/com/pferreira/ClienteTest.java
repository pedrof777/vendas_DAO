package br.com.pferreira;


import org.junit.Test;

import br.com.pferreira.dao.generic.jdbc.clienteDAO.ClienteDAO;
import br.com.pferreira.dao.generic.jdbc.clienteDAO.IClienteDAO;
import br.com.pferreira.domain.Cliente;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Pedro Ferreira
 */

public class ClienteTest {

  private IClienteDAO clienteDAO;

  @Test
  public void cadastrarTest() throws Exception{
    clienteDAO = new ClienteDAO();

    Cliente cliente = new Cliente();
    cliente.setCodigo("A1");
    cliente.setNome("Jacó");
    Integer countCad = clienteDAO.cadastrar(cliente);
    assertTrue(countCad == 1);

    Cliente clienteDb = clienteDAO.buscar("A1");
    assertNotNull(clienteDb);
    assertEquals(cliente.getCodigo(), clienteDb.getCodigo());
    assertEquals(cliente.getNome(), clienteDb.getNome());

    Integer countDel = clienteDAO.excluir(clienteDb);
    assertTrue(countDel == 1);
  }

  @Test
  public void buscarTest() throws Exception{
    clienteDAO = new ClienteDAO();

    Cliente cliente = new Cliente();
    cliente.setCodigo("A1");
    cliente.setNome("Jacó");
    Integer countCad = clienteDAO.cadastrar(cliente);
    assertTrue(countCad == 1);

    Cliente clienteDb = clienteDAO.buscar("A1");
    assertNotNull(clienteDb);
    assertEquals(cliente.getCodigo(), clienteDb.getCodigo());
    assertEquals(cliente.getNome(), clienteDb.getNome());

    Integer countDel = clienteDAO.excluir(clienteDb);
    assertTrue(countDel == 1);
  }

  @Test
  public void excluirTest() throws Exception {
    clienteDAO = new ClienteDAO();

    Cliente cliente = new Cliente();
    cliente.setCodigo("A1");
    cliente.setNome("Jacó");
    Integer countCad = clienteDAO.cadastrar(cliente);
    assertTrue(countCad == 1);

    Cliente clienteDB = clienteDAO.buscar("A1");
    assertEquals(cliente.getCodigo(), clienteDB.getCodigo());
    assertEquals(cliente.getNome(), clienteDB.getNome());

    Integer countDel = clienteDAO.excluir(clienteDB);
    assertTrue(countDel == 1);
  }

  @Test
  public void buscarTodosTest() throws Exception {
    clienteDAO = new ClienteDAO();

    Cliente cliente = new Cliente();
    cliente.setCodigo("A1");
    cliente.setNome("Jacó");
    Integer countCad = clienteDAO.cadastrar(cliente);
    assertTrue(countCad == 1);

    Cliente cliente2 = new Cliente();
    cliente.setCodigo("A2");
    cliente.setNome("Paulo");
    Integer countCad2 = clienteDAO.cadastrar(cliente);
    assertTrue(countCad == 1);

    List<Cliente> list = clienteDAO.buscarTodos();
    assertNotNull(list);
    assertEquals(2, list.size());

    int countDel = 0;
    for (Cliente cli : list){
      clienteDAO.excluir(cli);
      countDel++;
    }
    assertEquals(list.size(), countDel);

    list = clienteDAO.buscarTodos();
    assertEquals(list.size(), 0);
  }

  @Test
  public void atualizarTest() throws Exception {
    clienteDAO = new ClienteDAO();

    Cliente cliente = new Cliente();
    cliente.setCodigo("A1");
    cliente.setNome("Jacó");
    Integer countCad = clienteDAO.cadastrar(cliente);
    assertTrue(countCad == 1);

    Cliente clienteDB = clienteDAO.buscar("A1");
    assertNotNull(clienteDB);
    assertEquals(cliente.getCodigo(), clienteDB.getCodigo());
    assertEquals(cliente.getNome(), clienteDB.getNome());

    clienteDB.setCodigo("A10");
    clienteDB.setNome("Moscaa");
    Integer countUp = clienteDAO.atualizar(clienteDB);
    assertTrue(countUp == 1);


    Cliente clienteDB1 = clienteDAO.buscar("A1");
    assertNull(clienteDB1);

    Cliente clienteAtualizado = clienteDAO.buscar("A10");
    assertNotNull(clienteAtualizado);
    assertEquals(clienteDB.getId(), clienteAtualizado.getId());
    assertEquals(clienteDB.getNome(), clienteAtualizado.getNome());
    assertEquals(clienteDB.getCodigo(), clienteAtualizado.getCodigo());

    List<Cliente> list = clienteDAO.buscarTodos();
    for (Cliente cli : list){
      clienteDAO.excluir(cli);
    }
  }
}
