package br.com.pferreira.dao.generic.jdbc.produtoDAO;

import br.com.pferreira.dao.generic.jdbc.ConnectionFactory;
import br.com.pferreira.domain.Cliente;
import br.com.pferreira.domain.Produto;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pedro Ferreira
 */

public class ProdutoDAO implements IProdutoDAO {
  @Override
  public Integer create(Produto produto) throws Exception {
    Connection connection = null;
    PreparedStatement stm = null;
    try{
      connection = ConnectionFactory.getConnection();
      String sql = getSqlCreate();
      stm = connection.prepareStatement(sql);
      adicionarParametrosCreate(stm, produto);
      return stm.executeUpdate();
    }catch (Exception e){
      throw e;
    }finally {
      closeConnection(connection, stm, null);
    }
  }

  @Override
  public Integer update(Produto produto) throws Exception {
    Connection connection = null;
    PreparedStatement stm = null;
    try {
      connection = ConnectionFactory.getConnection();
      String sql = getSqlUpdate();
      stm = connection.prepareStatement(sql);
      adicionarParametrosUpdate(stm, produto);
      return stm.executeUpdate();
    }catch (Exception e){
      throw e;
    }finally {
      closeConnection(connection, stm, null);
    }
  }

  @Override
  public Produto search(String nome) throws Exception {
    Connection connection = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    Produto produto = null;
    try {
      connection = ConnectionFactory.getConnection();
      String sql = getSqlRead();
      stm = connection.prepareStatement(sql);
      adicionarParametrosRead(stm, nome);
      rs = stm.executeQuery();
      if(rs.next()){
        produto = new Produto();
        Long id = rs.getLong("ID");
        String nomep = rs.getString("NOME_P");
        BigDecimal valor = rs.getBigDecimal("VALOR_P");
        produto.setId(id);
        produto.setNomeP(nomep);
        produto.setValor(valor);
      }
    }catch (Exception e){
      throw e;
    }finally {
      closeConnection(connection, stm, rs);
    }
    return produto;
  }

  @Override
  public List<Produto> searchAll() throws Exception {
    Connection connection = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    List<Produto> list = new ArrayList<>();
    Produto produto = null;
    try {
      connection = ConnectionFactory.getConnection();
      String sql = getSqlReadAll();
      stm = connection.prepareStatement(sql);
      rs = stm.executeQuery();
      while (rs.next()){
        produto = new Produto();
        Long id = rs.getLong("ID");
        String nome = rs.getString("NOME_P");
        BigDecimal valorP = rs.getBigDecimal("VALOR_P");
        produto.setId(id);
        produto.setNomeP(nome);
        produto.setValor(valorP);
        list.add(produto);
      }
    }catch (Exception e){
      throw e;
    }finally {
      closeConnection(connection, stm, rs);
    }
    return list;
  }

  @Override
  public Integer delete(Produto produto) throws Exception {
    Connection connection = null;
    PreparedStatement stm = null;
    try{
      connection = ConnectionFactory.getConnection();
      String sql = getSqlDelete();
      stm = connection.prepareStatement(sql);
      adicionarParametrosDelete(stm, produto);
      return stm.executeUpdate();
    }catch (Exception e){
      throw e;
    }finally {
      closeConnection(connection, stm, null);
    }
  }

  private String getSqlCreate(){
    StringBuilder sb = new StringBuilder();
    sb.append("INSERT INTO PRODUTO_DB(ID, NOME_P, VALOR_P) ");
    sb.append("VALUES(NEXTVAL('sq_produto'),?,?)");
    return  sb.toString();
  }

  private void adicionarParametrosCreate(PreparedStatement stm, Produto produto) throws SQLException{
    stm.setString(1,produto.getNomeP());
    stm.setBigDecimal(2,produto.getValor());
  }

  private String getSqlUpdate(){
    StringBuilder sb = new StringBuilder();
    sb.append("UPDATE PRODUTO_DB ");
    sb.append("SET NOME_P = ?, VALOR_P = ? ");
    sb.append("WHERE ID = ?");
    return sb.toString();
  }

  private void adicionarParametrosUpdate(PreparedStatement stm, Produto produto) throws SQLException{
    stm.setString(1, produto.getNomeP());
    stm.setBigDecimal(2, produto.getValor());
    stm.setLong(3, produto.getId());
  }

  private String getSqlRead(){
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT * FROM PRODUTO_DB ");
    sb.append("WHERE NOME_P = ?");
    return sb.toString();
  }

  private void adicionarParametrosRead(PreparedStatement stm, String nomeP) throws SQLException{
    stm.setString(1, nomeP);
  }

  private String getSqlReadAll(){
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT * FROM PRODUTO_DB");
    return sb.toString();
  }

  private String getSqlDelete(){
    StringBuilder sb = new StringBuilder();
    sb.append("DELETE FROM PRODUTO_DB ");
    sb.append("WHERE NOME_P = ?");
    return sb.toString();
  }

  private void adicionarParametrosDelete(PreparedStatement stm, Produto produto) throws SQLException{
    stm.setString(1, produto.getNomeP());
  }

  private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs){
    try {
      if (rs != null && !rs.isClosed()){
          rs.close();
      }
      if (stm != null && !stm.isClosed()){
        stm.close();
      }
      if (connection != null && !connection.isClosed()){
        connection.close();
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
