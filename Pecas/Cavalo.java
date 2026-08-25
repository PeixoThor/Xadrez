package Pecas;

import Jogo.Peca;

public class Cavalo extends Peca {
    
    // 1. MOVIMENTO NORMAL DO CAVALO (Movimento em "L")
    public boolean movimento(int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        
        if (dx * dy == 2) {
            return true;
        } else {
            return false;
        }
    }
    
    public Cavalo(Cor c) {
        super(c);
        if (c == Cor.Branco) {
            this.setAparencia("\u265E");
        } else {
            this.setAparencia("\u2658");
        }
    }
}