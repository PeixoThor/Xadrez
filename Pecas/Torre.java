package Pecas;

import Jogo.Peca;
import Jogo.Tabuleiro;

public class Torre extends Peca {
    private boolean moveu = false; 
    private Tabuleiro tabuleiro;
    
    // 1. MOVIMENTO NORMAL DA TORRE (Linhas retas horizontais ou verticais)
    public boolean movimento(int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        
        if (dx * dy == 0) {
            int dirX = (x1 > x0) ? 1 : ((x1 < x0) ? -1 : 0);
            int dirY = (y1 > y0) ? 1 : ((y1 < y0) ? -1 : 0);
            
            for (int i = 1; i < dx + dy; i++) {
                int x = x0 + (i * dirX);
                int y = y0 + (i * dirY); 
                
                if (tabuleiro.getTabuleiro(x, y) != null) {
                    return false;
                }
            }  
            moveu = true;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean getMoveu() {
        return moveu;
    }
    
    public Torre(Cor c, Tabuleiro tabuleiro) {
        super(c);
        this.tabuleiro = tabuleiro;
      
        if (c == Cor.Branco) {
            this.setAparencia("\u265C");
        } else {
            this.setAparencia("\u2656");
        }
    }
}