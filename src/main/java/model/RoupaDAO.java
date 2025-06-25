/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rafae// Esta classe segue o Princípio da Responsabilidade Única (SRP),
 *                 A classe RoupaDAO apenas lida com o acesso ao banco de dados.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoupaDAO {
    static final String url = "jdbc:postgresql://localhost:5432/lojaderoupas";
    static final String driver = "org.postgresql.Driver";
    static final String user = "postgres";
    static final String senha = "utfpr";

    // Cria a tabela Roupa
    public void criarTabelaRoupa() {
        String sql = "CREATE TABLE IF NOT EXISTS roupa ("
                   + "id INT PRIMARY KEY, "
                   + "nome VARCHAR(100), "
                   + "preco DOUBLE PRECISION, "
                   + "tamanho VARCHAR(10), "
                   + "cor VARCHAR(50), "
                   + "tipo VARCHAR(50))";
        try {
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(url, user, senha);
                 Statement st = con.createStatement()) {
                st.executeUpdate(sql);
                System.out.println("Tabela roupa criada com sucesso.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar tabela roupa: " + e.getMessage());
        }
    }

    // Insere uma roupa
    public void inserirRoupa(Roupa r) {
        String sql = "INSERT INTO roupa (id, nome, preco, tamanho, cor, tipo) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(url, user, senha);
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, r.getId());
                ps.setString(2, r.getNome());
                ps.setDouble(3, r.getPreco());
                ps.setString(4, r.getTamanho());
                ps.setString(5, r.getCor());
                ps.setString(6, r.getTipo());
                ps.executeUpdate();
                System.out.println("Roupa inserida com sucesso.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao inserir roupa: " + e.getMessage());
        }
    }

    // Atualiza uma roupa
    public void atualizarRoupa(Roupa r) {
        String sql = "UPDATE roupa SET nome = ?, preco = ?, tamanho = ?, cor = ?, tipo = ? WHERE id = ?";
        try {
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(url, user, senha);
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, r.getNome());
                ps.setDouble(2, r.getPreco());
                ps.setString(3, r.getTamanho());
                ps.setString(4, r.getCor());
                ps.setString(5, r.getTipo());
                ps.setInt(6, r.getId());
                ps.executeUpdate();
                System.out.println("Roupa atualizada com sucesso.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar roupa: " + e.getMessage());
        }
    }

    // Remove uma roupa por ID
    public void removerRoupaPorId(int id) {
        String sql = "DELETE FROM roupa WHERE id = ?";
        try {
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(url, user, senha);
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("Roupa removida com sucesso.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao remover roupa: " + e.getMessage());
        }
    }

    // Busca uma roupa por ID
    public Roupa buscarRoupaPorId(int id) {
        Roupa roupa = null;
        String sql = "SELECT * FROM roupa WHERE id = ?";
        try {
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(url, user, senha);
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String nome = rs.getString("nome");
                        double preco = rs.getDouble("preco");
                        String tamanho = rs.getString("tamanho");
                        String cor = rs.getString("cor");
                        String tipo = rs.getString("tipo");

                        roupa = new Roupa(id, nome, preco, tamanho, cor, tipo);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar roupa: " + e.getMessage());
        }
        return roupa;
    }

    // Lista todas as roupas
    public List<Roupa> listarRoupas() {
        List<Roupa> lista = new ArrayList<>();
        String sql = "SELECT * FROM roupa";
        try {
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(url, user, senha);
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    double preco = rs.getDouble("preco");
                    String tamanho = rs.getString("tamanho");
                    String cor = rs.getString("cor");
                    String tipo = rs.getString("tipo");

                    Roupa r = new Roupa(id, nome, preco, tamanho, cor, tipo);
                    lista.add(r);
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar roupas: " + e.getMessage());
        }
        return lista;
    }
}

