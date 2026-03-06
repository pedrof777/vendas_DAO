package br.com.pferreira.dao.generic.jdbc.clienteDAO;

import br.com.pferreira.domain.Cliente;
import java.util.List;

/**
 * @author Pedro Ferreira
 */

public interface IClienteDAO {

  public Integer cadastrar(Cliente cliente) throws Exception;

  public Integer atualizar(Cliente cliente) throws Exception;

  public Cliente buscar(String codigo) throws Exception;

  public List<Cliente> buscarTodos() throws Exception;

  public Integer excluir(Cliente cliente) throws Exception;
}
