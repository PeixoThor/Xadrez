package Pecas;

import Jogo.Peca;
import Jogo.Tabuleiro;

// Classe para a peça Rainha.
public class Rainha extends Peca {
    private Tabuleiro tab;

    // Método de validação do movimento para essa peça: retorna True se o movimento for possível.
    public boolean movimento(int x0, int y0, int x1, int y1) {
        int Dx = Math.abs(x1 - x0);
        int Dy = Math.abs(y1 - y0);

        // Movimentação em linhas, igual da Torre.
        if (Dx * Dy == 0) {
            int dirX = (x1 > x0) ? 1 : ((x1 < x0) ? -1 : 0);
            int dirY = (y1 > y0) ? 1 : ((y1 < y0) ? -1 : 0);
            for (int i = 1; i < Dx + Dy; i++) {
                int X = x0 + (i * dirX);
                int Y = y0 + (i * dirY); 
                if (tab != null && tab.getTabuleiro(Y, X) != null) 
                    return false;
            } 
            return true; 
        } 
        // Movimentação nas diagonais, igual do Bispo.
        else if (Dx == Dy) {
            int X, Y;
            int DirX = (x1 > x0) ? 1 : -1;
            int DirY = (y1 > y0) ? 1 : -1;
 
            for (int i = 1; i < Dx; i++) { 
                X = x0 + (i * DirX);
                Y = y0 + (i * DirY);

                if (tab != null && tab.getTabuleiro(Y, X) != null) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }
 
    // Construtor da rainha.
    public Rainha(Cor c, Tabuleiro t) {
        super(c);
        tab = t;
        if (c == Cor.Branco) {
            this.setAparencia("\u265B");
        } else {
            this.setAparencia("\u2655");
        }
    }
}