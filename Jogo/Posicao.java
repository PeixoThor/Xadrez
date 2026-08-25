package Jogo;

// Classe para gerenciar a posição no tabuleiro.
public class Posicao {
    private int x;
    private int y;

    // Construtor da posição.
    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Retorna a posição X.
    public int getX() {
        return x;
    }

    // Retorna a posição Y.
    public int getY() {
        return y;
    }
}