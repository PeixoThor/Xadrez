package Pecas;

import Jogo.Peca;
import Jogo.Posicao;
import Jogo.Tabuleiro;

public class PecaControle extends Peca {
    private Tabuleiro tabuleiro;

    public PecaControle(Cor c, Tabuleiro tabuleiro) {
        super(c);
        this.tabuleiro = tabuleiro;
    }
    
    // 1. VERIFICAÇÃO DE XEQUE (Busca no tabuleiro por ameaças ao Rei)
    public boolean verificaCheck(Cor c) {
        Posicao pos = localizaRei(c); 
        Peca pecaAux = null;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pecaAux = tabuleiro.getTabuleiro(i, j);

                if (pecaAux != null && pecaAux.getCor() != c) {
                    if (pecaAux.movimento(i, j, pos.getX(), pos.getY())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    // 2. VERIFICAÇÃO DE XEQUE EM POSIÇÃO ESPECÍFICA
    public boolean verificaCheck(Cor c, int x, int y) {
        Peca pecaAux = null;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pecaAux = tabuleiro.getTabuleiro(i, j);
                if (pecaAux != null && pecaAux.getCor() != c) {
                    if (pecaAux.movimento(i, j, x, y)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    // 3. LOCALIZAÇÃO DO REI NO TABULEIRO
    public Posicao localizaRei(Cor c) {
        Peca pecaAux = null;
        Posicao pos = null;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pecaAux = tabuleiro.getTabuleiro(i, j); 

                if (pecaAux != null && pecaAux.getCor() == c && pecaAux instanceof Rei) {
                    pos = new Posicao(i, j);
                    return pos;
                }
            }
        }
        return pos;
    }
    
    @Override
    public boolean movimento(int x0, int y0, int x1, int y1) {
        return false;
    }
}