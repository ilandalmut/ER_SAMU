/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ModuloConexao {
    public static Connection conectar(){
    Connection conexao;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/db_samu";
    String user = "root";
    String password = "root1";
    try {
    Class.forName(driver);
    conexao = DriverManager.getConnection(url, user, password);
    return conexao;
    } catch(ClassNotFoundException | SQLException e) {
    System.out.println(e);
    return null;
    }
    }
            
}
