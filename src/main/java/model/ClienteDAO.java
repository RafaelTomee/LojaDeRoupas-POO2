/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rafae// Esta classe segue o Princípio da Responsabilidade Única (SRP),
 *                 A classe ClienteDAO apenas lida com o acesso ao banco de dados.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    static Connection con = null;
    static final String url = "jdbc:postgresql://localhost:5432/lojaderoupas";
    static String driver = "org.postgresql.Driver";
    static final String user = "postgres";
    static final String senha = "utfpr";
    Statement st = null;
    
public void criarTabelaCliente() {
        

        String sql1 = "CREATE TABLE IF NOT EXISTS Cliente ("
                + "id INT PRIMARY KEY not null,"
                + "nome VARCHAR(100),"
                + "cpf VARCHAR(14),"
                + "telefone VARCHAR(20))";

        try {//criação de tabel
            
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, senha);
            System.out.println("Criando a tabela...");
            st = con.createStatement();
            st.executeUpdate(sql1);
            System.out.println("Tabela criada com sucesso!");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
}

    
    public void inserirCliente(Cliente cliente) {
    String sql2 = "INSERT INTO Cliente (id, nome, cpf, telefone) VALUES (?, ?, ?, ?)";

      try(
          Connection con = DriverManager.getConnection(url,user,senha);
          PreparedStatement ps = con.prepareStatement(sql2))
          {//inserção de dados
        Class.forName(driver);
        
        System.out.println("Inserindo dados...");
        
        ps.setInt(1, cliente.getId());
        ps.setString(2, cliente.getNome());
        ps.setString(3, cliente.getCpf());
        ps.setString(4, cliente.getTelefone());
        
        ps.executeUpdate();
        System.out.println("Dados inseridos com sucesso!");
        }catch (Exception e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    public Cliente buscarClientePorId(int id) {
    String sql = "SELECT * FROM Cliente WHERE id = ?";
    Cliente cliente = null;

    try (
        Connection con = DriverManager.getConnection(url, user, senha);
        PreparedStatement ps = con.prepareStatement(sql)
    ) {
        Class.forName(driver);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            cliente = new Cliente(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getString("telefone")
            );
        }
        rs.close();
    } catch (Exception e) {
        System.out.println(e);
    }
    return cliente;
}
    
    public List<Cliente> listarTodos() {
    List<Cliente> lista = new ArrayList<>();
    String sql = "SELECT * FROM Cliente";

    try (
        Connection con = DriverManager.getConnection(url, user, senha);
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()
    ) {
        Class.forName(driver);
        while (rs.next()) {
            Cliente cliente = new Cliente(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getString("telefone")
            );
            lista.add(cliente);
        }
    } catch (Exception e) {
        System.out.println("Erro ao listar clientes: " + e.getMessage());
    }

    return lista;
}
    
    public void removerCliente(int id) {
    String sql = "DELETE FROM Cliente WHERE id = ?";

    try (
        Connection con = DriverManager.getConnection(url, user, senha);
        PreparedStatement ps = con.prepareStatement(sql)
    ) {
        Class.forName(driver);
        System.out.println("Excluindo cliente com ID: " + id);
        ps.setInt(1, id);
        int linhasAfetadas = ps.executeUpdate();
        
        if (linhasAfetadas > 0) {
            System.out.println("Cliente excluído com sucesso!");
        } else {
            System.out.println("Nenhum cliente encontrado com esse ID.");
        }
    } catch (Exception e) {
        System.out.println(e);
    }
}
    
    public void atualizarCliente(Cliente cliente) {
    String sql = "UPDATE Cliente SET nome = ?, cpf = ?, telefone = ? WHERE id = ?";

    try (
        Connection con = DriverManager.getConnection(url, user, senha);
        PreparedStatement ps = con.prepareStatement(sql)
    ) {
        Class.forName(driver);

        System.out.println("Atualizando cliente com ID: " + cliente.getId());

        ps.setString(1, cliente.getNome());
        ps.setString(2, cliente.getCpf());
        ps.setString(3, cliente.getTelefone());
        ps.setInt(4, cliente.getId());

        int linhasAfetadas = ps.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Nenhum cliente encontrado com esse ID.");
        }

        ps.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e);
    }
}
        
    
}

        
        
        

        

