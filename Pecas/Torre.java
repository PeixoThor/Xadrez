package Pecas;

import Jogo.Peca;
import Jogo.Tabuleiro;

// Classe para a peça Torre.
public class Torre extends Peca {
    private boolean moveu = false; 
    private Tabuleiro tab;
 
    // Retorna status de movimentação da peça.
    public boolean getMoveu() { return moveu; }

    // Altera status de movimentação.
    public void setMoveu(boolean moveu) { this.moveu = moveu; }

    // Método de validação do movimento para essa peça: retorna True se o movimento for possível.
    public boolean movimento(int x0, int y0, int x1, int y1) {
        int Dx = Math.abs(x1 - x0);
        int Dy = Math.abs(y1 - y0);
 
        // Movimentação em linhas ou colunas.
        if (Dx * Dy == 0) {
            int dirX = (x1 > x0) ? 1 : ((x1 < x0) ? -1 : 0);
            int dirY = (y1 > y0) ? 1 : ((y1 < y0) ? -1 : 0);

            // Validação de colisão.
            for (int i = 1; i < Dx + Dy; i++) {
                int X = x0 + (i * dirX);
                int Y = y0 + (i * dirY); 
                if (tab != null && tab.getTabuleiro(Y, X) != null) 
                    return false;
            } 
            return true;
        } else {
            return false;
        }
    }
 
    // Construtor da torre.
    public Torre(Cor c, Tabuleiro t) {
        super(c);
        tab = t;
 
        if (c == Cor.Branco) {
            this.setAparencia("\u265C");
        } else {
            this.setAparencia("\u2656");
        }
    }
}