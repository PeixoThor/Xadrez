package Pecas;

import Jogo.Peca;
import Jogo.Tabuleiro;

// Classe para a peça Bispo.
public class Bispo extends Peca {
    private Tabuleiro tab; 

    // Método de validação do movimento para essa peça: retorna True se o movimento for possível.
    public boolean movimento(int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        // Movimentação normal: nas diagonais (deslocamento igual em X e Y).
        if (dx == dy) {
            int x, y;
            int dirX = (x1 > x0) ? 1 : -1;
            int dirY = (y1 > y0) ? 1 : -1;
 
            // Verifica se as casas intermediárias estão vazias.
            for (int i = 1; i < dx; i++) { 
                x = x0 + (i * dirX);
                y = y0 + (i * dirY);

                if (tab != null && tab.getTabuleiro(y, x) != null) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }
 
    // Construtor do bispo.
    public Bispo(Cor c, Tabuleiro tabuleiro) {
        super(c);
        tab = tabuleiro;
        if (c == Cor.Branco) {
            this.setAparencia("\u265D");
        } else {
            this.setAparencia("\u2657");
        }
    }
}