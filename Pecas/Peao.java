package Pecas;

import Jogo.Peca;

public class Peao extends Peca {
    private boolean moveu = false; 
    
    // 1. MOVIMENTO NORMAL DO PEÃO (Avanço simples ou duplo no primeiro movimento)
    public boolean movimento(int x0, int y0, int x1, int y1) {
        int dx = x1 - x0;
        int dy = y1 - y0;
        
        int direcao = (this.getCor() == Cor.Branco) ? -1 : 1;
       
        if (dx == 0 && dy == 2 * direcao && !(moveu)) {
            moveu = true;
            return true;
        } else if (dx == 0 && dy == direcao) {
            moveu = true;
            return true;
        } else {
            return false;
        }
    }
    
    public Peao(Cor c) {
        super(c);
        if (c == Cor.Branco) {
            this.setAparencia("\u265F");
        } else {
            this.setAparencia("\u2659");
        }
    }
}