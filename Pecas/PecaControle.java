package Pecas;

import Jogo.Peca;
import Jogo.Posicao;
import Jogo.Tabuleiro;

// Classe controle auxiliar, usada para testar xeque, xeque-mate e posições do Rei.
public class PecaControle extends Peca {
    Tabuleiro tab;

    // Construtor do verificador de controle.
    public PecaControle(Cor c, Tabuleiro tabuleiro) {
        super(c);
        tab = tabuleiro;
    }

    // Método para validar se uma cor está em Xeque atual.
    public boolean verificaCheck(Cor c) {
        Posicao pos = localizaRei(c); 
        if (pos == null) return false;
        Peca aux = null;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                aux = tab.getTabuleiro(i, j);

                if (aux != null && aux.getCor() != c) {
                    if (aux instanceof Peao) {
                        int Dx = pos.getX() - j;
                        int Dy = pos.getY() - i;
                        int direcao = (aux.getCor() == Cor.Branco) ? -1 : 1;
                        if (Math.abs(Dx) == 1 && Dy == direcao) {
                            return true;
                        }
                    } else if (aux.movimento(j, i, pos.getX(), pos.getY())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // Método de sobrecarga para verificar se uma posição específica estaria em Xeque.
    public boolean verificaCheck(Cor c, int X, int Y) {
        Peca aux = null;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                aux = tab.getTabuleiro(i, j);
                if (aux != null && aux.getCor() != c) {
                    if (aux instanceof Peao) {
                        int Dx = X - j;
                        int Dy = Y - i;
                        int direcao = (aux.getCor() == Cor.Branco) ? -1 : 1;
                        if (Math.abs(Dx) == 1 && Dy == direcao) {
                            return true;
                        }
                    } else if (aux.movimento(j, i, X, Y)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // Localiza no array bidimensional em que coordenadas o rei da cor C se encontra.
    public Posicao localizaRei(Cor c) {
        Peca aux = null;
        Posicao pos = null;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                aux = tab.getTabuleiro(i, j); 

                if (aux != null && aux.getCor() == c && aux instanceof Rei) {
                    pos = new Posicao(j, i);
                    return pos;
                }
            }
        }
        return pos;
    }

    // Verifica a condição de fim de partida caso nenhum movimento tire o Rei da mira adversária.
    public boolean verificaXequeMate(Cor c) {
        if (!verificaCheck(c)) {
            return false;
        }

        // Tenta simular todos os movimentos e retorna se pelo menos um evita a ameaça.
        for (int r1 = 0; r1 < 8; r1++) {
            for (int c1 = 0; c1 < 8; c1++) {
                Peca peca = tab.getTabuleiro(r1, c1);
                if (peca != null && peca.getCor() == c) {
                    for (int r2 = 0; r2 < 8; r2++) {
                        for (int c2 = 0; c2 < 8; c2++) {
                            if (r1 == r2 && c1 == c2) continue;

                            if (peca.movimento(c1, r1, c2, r2)) {
                                Peca destino = tab.getTabuleiro(r2, c2);
                                if (destino == null || destino.getCor() != c) {
                                    tab.setTabuleiro(r2, c2, peca);
                                    tab.setTabuleiro(r1, c1, null);

                                    boolean aindaEmXeque = verificaCheck(c);

                                    tab.setTabuleiro(r1, c1, peca);
                                    tab.setTabuleiro(r2, c2, destino);

                                    if (!aindaEmXeque) {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    // Override abstrato ignorado.
    @Override
    public boolean movimento(int x0, int y0, int x1, int y1) {
        return false;
    }
}