/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rafae
 */
import java.sql.Connection;
import java.sql.DriverManager;


public class Conexao {

    static Connection con = null;
    static final String URL = "jdbc:postgresql://localhost:5432/lojaderoupas";
    static String driver = "org.postgresql.Driver";
    static final String USUARIO = "postgres";
    static final String SENHA = "utfpr";

    public static Connection conectar() throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
    
}
    
