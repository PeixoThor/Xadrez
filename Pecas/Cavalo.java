package Pecas;

import Jogo.Peca;

// Classe para a peça Cavalo.
public class Cavalo extends Peca {

    // Método de validação do movimento para essa peça: retorna True se o movimento for possível.
    public boolean movimento(int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        
        // Movimentação em L: o produto dos deltas deve resultar em 2 (2x1 ou 1x2).
        if (dx * dy == 2) {
            return true;
        } else {
            return false;
        }
    }
 
    // Construtor do cavalo.
    public Cavalo(Cor c) {
        super(c);
        if (c == Cor.Branco) {
            this.setAparencia("\u265E");
        } else {
            this.setAparencia("\u2658");
        }
    }
}