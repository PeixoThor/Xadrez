package Pecas;

import Jogo.Peca;
import Jogo.Posicao;
import Jogo.Tabuleiro;

// Classe para a peça Peão.
public class Peao extends Peca {
    public boolean moveu = false; 
    private Tabuleiro tab;

    // Construtor do peão.
    public Peao(Cor c, Tabuleiro tab) {
        super(c);
        this.tab = tab;
        if (c == Cor.Branco) {
            this.setAparencia("\u265F");
        } else {
            this.setAparencia("\u265F"); // OBS: Modifique aqui para "♙" para brancos se desejar a visualização de cores diferentes no terminal
        }
    }

    // Método de validação do movimento para essa peça: retorna True se o movimento for possível.
    @Override
    public boolean movimento(int x0, int y0, int x1, int y1) {
        int Dx = x1 - x0;
        int Dy = y1 - y0;
        int direcao = (this.getCor() == Cor.Branco) ? -1 : 1;

        // Movimento simples à frente: 1 casa.
        if (Dx == 0 && Dy == direcao && tab.getTabuleiro(y1, x1) == null) {
            moveu = true;
            return true;
        }

        // Movimento duplo inicial: 2 casas.
        if (Dx == 0 && Dy == 2 * direcao && !moveu) {
            int linhaIntermediaria = y0 + direcao;
            if (tab.getTabuleiro(linhaIntermediaria, x0) == null && tab.getTabuleiro(y1, x1) == null) {
                moveu = true;
                return true;
            }
        }

        // Captura normal ou En Passant: movimento diagonal de 1 casa.
        if (Math.abs(Dx) == 1 && Dy == direcao) {
            Peca destino = tab.getTabuleiro(y1, x1);
            
            // Captura padrão: há uma peça no destino de cor diferente.
            if (destino != null && destino.getCor() != this.getCor()) {
                moveu = true;
                return true;
            }

            // Captura En Passant: destino é exatamente a casa vulnerável marcada.
            Posicao alvo = tab.getAlvoEnPassant();
            if (alvo != null && alvo.getX() == y1 && alvo.getY() == x1) {
                moveu = true;
                return true;
            }
        }

        return false;
    }
}