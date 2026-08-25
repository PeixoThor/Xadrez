package Pecas;

import Jogo.Peca;
import Jogo.Tabuleiro;

public class Rainha extends Peca {
    private Tabuleiro tabuleiro;
    
    // 1. MOVIMENTO NORMAL DA RAINHA (Combinação de Torre e Bispo)
    public boolean movimento(int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        
        if (dx * dy == 0) {
            int dirX = (x1 > x0) ? 1 : ((x1 < x0) ? -1 : 0);
            int dirY = (y1 > y0) ? 1 : ((y1 < y0) ? -1 : 0);
            
            for (int i = 1; i < dx + dy; i++) {
                int x = x0 + (i * dirX);
                int y = y0 + (i * dirY); 
                
                if (tabuleiro.getTabuleiro(y, x) != null) {
                    return false;
                }
            }  
            return true; 
        } else if (dx == dy) {
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
    
    public Rainha(Cor c, Tabuleiro tabuleiro) {
        super(c);
        this.tabuleiro = tabuleiro;
        if (c == Cor.Branco) {
            this.setAparencia("\u265B");
        } else {
            this.setAparencia("\u2655");
        }
    }
}