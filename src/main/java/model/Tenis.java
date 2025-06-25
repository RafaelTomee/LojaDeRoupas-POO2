/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rafae
 */
public class Tenis extends Produto{
    private int tamanhoNumerico;
    

    public Tenis(int id, String nome, double preco, int tamanhoNumerico) {
        super(id, nome, preco);
        this.tamanhoNumerico = tamanhoNumerico;
    }

    public int getTamanhoNumerico() {
        return tamanhoNumerico;
    }

    public void setTamanhoNumerico(int tamanhoNumerico) {
        this.tamanhoNumerico = tamanhoNumerico;
    }

    
    
}
