/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rafae// Esta classe segue o Princípio da Responsabilidade Única (SRP),
 *                 A classe FuncionarioDAO apenas lida com o acesso ao banco de dados.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    static final String url = "jdbc:postgresql://localhost:5432/lojaderoupas";
    static final String driver = "org.postgresql.Driver";
    static final String user = "postgres";
    static final String senha = "utfpr";

    // Cria a tabela Funcionario, se ainda não existir
    public void criarTabelaFuncionario() {
        String sql = "CREATE TABLE IF NOT EXISTS Funcionario ("
                + "id INT PRIMARY KEY,"
                + "nome VARCHAR(100),"
                + "cargo VARCHAR(100),"
                + "salario DOUBLE PRECISION)";
        try {
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(url, user, senha);
                 Statement st = con.createStatement()) {
                st.executeUpdate(sql);
                System.out.println("Tabela Funcionario criada com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar tabela: " + e);
        }
    }

    // Insere um novo funcionario
    public void inserirFuncionario(Funcionario f) {
        String sql = "INSERT INTO Funcionario (id, nome, cargo, salario) VALUES (?, ?, ?, ?)";
        try {
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(url, user, senha);
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, f.getId());
                ps.setString(2, f.getNome());
                ps.setString(3, f.getCargo());
                ps.setDouble(4, f.getSalario());
                ps.executeUpdate();
                System.out.println("Funcionário inserido com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao inserir funcionário: " + e);
        }
    }

    // Lista todos os funcionarios
    public List<Funcionario> listarFuncionarios() {
    List<Funcionario> lista = new ArrayList<>();
    String sql = "SELECT * FROM Funcionario";
    try {
        Class.forName(driver);
        try (Connection con = DriverManager.getConnection(url, user, senha);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cargo = rs.getString("cargo");
                double salario = rs.getDouble("salario");
                Funcionario f = new Funcionario(id, nome, cargo, salario);
                lista.add(f);
            }
        }
    } catch (Exception e) {
        System.out.println("Erro ao listar funcionários: " + e);
    }
    return lista;
}


    // Atualiza os dados de um funcionário existente
    public void atualizarFuncionario(Funcionario f) {
        String sql = "UPDATE Funcionario SET nome = ?, cargo = ?, salario = ? WHERE id = ?";
        try {
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(url, user, senha);
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, f.getNome());
                ps.setString(2, f.getCargo());
                ps.setDouble(3, f.getSalario());
                ps.setInt(4, f.getId());
                ps.executeUpdate();
                System.out.println("Funcionário atualizado com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar funcionário: " + e);
        }
    }

    // Remove um funcionário pelo ID
    public void removerFuncionarioPorId(int id) {
        String sql = "DELETE FROM Funcionario WHERE id = ?";
        try {
            Class.forName(driver);
            try (Connection con = DriverManager.getConnection(url, user, senha);
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.executeUpdate();
                System.out.println("Funcionário removido com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao remover funcionário: " + e);
        }
    }
    
    public Funcionario buscarFuncionarioPorId(int id) {
    Funcionario funcionario = null;
    String sql = "SELECT * FROM funcionario WHERE id = ?";
    try {
        Class.forName(driver);
        try (Connection con = DriverManager.getConnection(url, user, senha);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String cargo = rs.getString("cargo");
                    double salario = rs.getDouble("salario");

                    funcionario = new Funcionario(id, nome, cargo, salario);
                }
            }
        }
    } catch (Exception e) {
        System.out.println("Erro ao buscar funcionário por ID: " + e.getMessage());
    }
    return funcionario;
}
}

