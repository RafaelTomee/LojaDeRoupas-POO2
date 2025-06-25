/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafae
 */
public class Cliente extends Pessoa {
    private String cpf;
    private String telefone;
    
    public Cliente() {
    super();
    }
    
    public Cliente(int id, String nome, String cpf, String telefone) {
        super(id, nome);
        this.cpf = cpf;
        this.telefone = telefone;
    }
    
    
    
    

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    

}
