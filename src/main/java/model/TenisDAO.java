/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafae// Esta classe segue o Princípio da Responsabilidade Única (SRP),
 *                 A classe TenisDAO apenas lida com o acesso ao banco de dados.
 */
public class TenisDAO{
    static Connection con = null;
    static final String url = "jdbc:postgresql://localhost:5432/lojaderoupas";
    static String driver = "org.postgresql.Driver";
    static final String user = "postgres";
    static final String senha = "utfpr";
    Statement st = null;
    
    public void criarTabelaTenis() {
        

        String sql = "CREATE TABLE IF NOT EXISTS Tenis (" +
                     "id INT PRIMARY KEY," +
                     "nome VARCHAR(100)," +
                     "preco DOUBLE PRECISION," +
                     "tamanhoNumerico INT)";

        try {
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(url, user, senha);
                 Statement st = con.createStatement()) {
                st.executeUpdate(sql);
                System.out.println("Tabela Tenis criada com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar tabela Tenis: " + e.getMessage());
        }
    }

    
    public void inserirTenis(Tenis t) {
        String sql = "INSERT INTO Tenis (id, nome, preco, tamanhoNumerico) VALUES (?, ?, ?, ?)";
        try {
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(url, user, senha);
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, t.getId());
                ps.setString(2, t.getNome());
                ps.setDouble(3, t.getPreco());
                ps.setInt(4, t.getTamanhoNumerico());
                ps.executeUpdate();
                System.out.println("Tênis inserido com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao inserir tênis: " + e.getMessage());
        }
    }
    
    public Tenis buscarTenisPorId(int id) {
        Tenis tenis = null;
        String sql = "SELECT * FROM Tenis WHERE id = ?";
        try {
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(url, user, senha);
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    tenis = new Tenis(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("tamanhoNumerico")
                    );
                }
                rs.close();
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar tênis: " + e.getMessage());
        }
        return tenis;
    }
    
    public List<Tenis> listarTenis() {
        List<Tenis> lista = new ArrayList<>();
        String sql = "SELECT * FROM Tenis";
        try {
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(url, user, senha);
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    Tenis t = new Tenis(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("tamanhoNumerico")
                    );
                    lista.add(t);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar tênis: " + e.getMessage());
        }
        return lista;
    }
    
    public void atualizarTenis(Tenis t) {
        String sql = "UPDATE Tenis SET nome = ?, preco = ?, tamanhoNumerico = ? WHERE id = ?";
        try {
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(url, user, senha);
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, t.getNome());
                ps.setDouble(2, t.getPreco());
                ps.setInt(3, t.getTamanhoNumerico());
                ps.setInt(4, t.getId());
                ps.executeUpdate();
                System.out.println("Tênis atualizado com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar tênis: " + e.getMessage());
        }
    }
    
    public void removerTenis(int id) {
        String sql = "DELETE FROM Tenis WHERE id = ?";
        try {
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(url, user, senha);
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("Tênis removido com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao remover tênis: " + e.getMessage());
        }
    }

    
}
