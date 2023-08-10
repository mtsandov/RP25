/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.trie;

/**
 *
 * @author wayar
 */
public class Data {
    private String character1;
    private String character2;
    private int number;

    public Data(String character, int number) {
        this.character1 = character;
        this.number = number;
    }
    
    public Data(String character1, String character2) {
        this.character1 = character1;
        this.character1 = character2;
    }

    public String getCharacter() {
        return character1;
    }

    public void setCharacter(String character) {
        this.character1 = character;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}