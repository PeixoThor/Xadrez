package Pecas;

import Jogo.Peca;
import Jogo.Tabuleiro;

public class Rei extends Peca {
    private Tabuleiro tabuleiro; 
    private boolean moveu = false;
    private PecaControle pecaControle;
    
    // 1. MOVIMENTO NORMAL DO REI (1 casa em qualquer direção)
    public boolean movimento(int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        if ((dx <= 1 && dy <= 1) && (dx + dy > 0)) {
            // Verifica se a casa de destino coloca o próprio Rei em Check
            if (!pecaControle.verificaCheck(this.getCor(), x1, y1)) {
                moveu = true;
                return true;
            }
            return false;
        } 

        // 2. MOVIMENTO DE ROQUE (dx == 2 e não se moveu)
        if (dy == 0 && dx == 2 && !moveu) {
            int dirX = (x1 > x0) ? 1 : -1;   

            // Roque Curto (Direita)
            if (dirX == 1) {
                Peca pecaAux = (this.getCor() == Cor.Branco) ? tabuleiro.getTabuleiro(7, 7) : tabuleiro.getTabuleiro(0, 7);
                if (pecaAux instanceof Torre && !(((Torre) pecaAux).getMoveu())) {
                    for (int i = 1; i <= dx; i++) {  
                        int x = x0 + (i * dirX);
                        if (tabuleiro.getTabuleiro(y0, x) != null || pecaControle.verificaCheck(this.getCor(), x, y0)) {
                            return false; 
                        }
                    }      
                    // Executa a movimentação da Torre
                    if (this.getCor() == Cor.Branco) {
                        tabuleiro.movimenta('H', 7, 'F', 7);
                    } else {
                        tabuleiro.movimenta('H', 0, 'F', 0);
                    }
                    moveu = true;
                    return true;
                }
            } 
            // Roque Longo (Esquerda)
            else {
                Peca pecaAux = (this.getCor() == Cor.Branco) ? tabuleiro.getTabuleiro(7, 0) : tabuleiro.getTabuleiro(0, 0);
                if (pecaAux instanceof Torre && !(((Torre) pecaAux).getMoveu())) {
                    for (int i = 1; i <= dx; i++) {  
                        int x = x0 + (i * dirX);
                        if (tabuleiro.getTabuleiro(y0, x) != null || pecaControle.verificaCheck(this.getCor(), x, y0)) {
                            return false;  
                        }
                    }
                    // Executa a movimentação da Torre
                    if (this.getCor() == Cor.Branco) {
                        tabuleiro.movimenta('A', 7, 'D', 7);
                    } else {
                        tabuleiro.movimenta('A', 0, 'D', 0);
                    }
                    moveu = true;
                    return true;
                }
            }
        }
        return false;    
    }

    public Rei(Cor c, Tabuleiro tabuleiro) {
        super(c);
        this.tabuleiro = tabuleiro;
        this.pecaControle = new PecaControle(c, tabuleiro);
        
        if (c == Cor.Branco) {
            this.setAparencia("\u265A");
        } else {
            this.setAparencia("\u2654");
        }
    }
}