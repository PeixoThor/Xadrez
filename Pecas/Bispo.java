package Pecas;

import Jogo.Peca;
import Jogo.Tabuleiro;

public class Bispo extends Peca {
    private Tabuleiro tabuleiro; 
    
    // 1. MOVIMENTO NORMAL DO BISPO (Diagonais)
    public boolean movimento(int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        System.out.println(dx + " - " + dy);
        if (dx == dy) {
            int x, y;
            int dirX = (x1 > x0) ? 1 : -1;
            int dirY = (y1 > y0) ? 1 : -1;
          
            for (int i = 1; i < dx; i++) {  
                x = x0 + (i * dirX);
                y = y0 + (i * dirY);

                if (tabuleiro.getTabuleiro(y, x) != null) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
    
    public Bispo(Cor c, Tabuleiro tabuleiro) {
        super(c);
        this.tabuleiro = tabuleiro;
        if (c == Cor.Branco) {
            this.setAparencia("\u265D");
        } else {
            this.setAparencia("\u2657");
        }
    }
}