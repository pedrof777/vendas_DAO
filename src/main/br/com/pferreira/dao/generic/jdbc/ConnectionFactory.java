package br.com.pferreira.dao.generic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Pedro Ferreira
 */

public class ConnectionFactory {
  private static Connection connection;

  private ConnectionFactory (Connection connection){}

  public static Connection getConnection() throws SQLException {
    if(connection == null) {
      connection = initConection();
    } else if (connection != null && connection.isClosed() ) {
      connection = initConection();

    }
    return connection;
  }

  private static Connection initConection(){
    try{
      return DriverManager.getConnection(
              "jdbc:postgresql://localhost:5432/vendas_online_db",
              "postgres",
              "03695147");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
