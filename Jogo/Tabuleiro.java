package Jogo;

import Pecas.*;

// Classe para o Tabuleiro.
public class Tabuleiro {
    private Peca[][] tabuleiro = new Peca[8][8];
    private Posicao alvoEnPassant = null; // Armazena a casa vulnerável ao En Passant.

    // Construtor do tabuleiro.
    public Tabuleiro() {
        preencher();
    }

    // Retorna o alvo atual do movimento En Passant.
    public Posicao getAlvoEnPassant() {
        return alvoEnPassant;
    }

    // Retorna a peça contida em uma coordenada.
    public Peca getTabuleiro(int linha, int coluna) {
        return tabuleiro[linha][coluna];
    }

    // Sobrescreve a peça em uma determinada coordenada.
    public void setTabuleiro(int linha, int coluna, Peca peca) {
        tabuleiro[linha][coluna] = peca;
    }

    // Preenche o tabuleiro com a formação inicial do xadrez.
    public void preencher() {
        tabuleiro[0][0] = new Torre(Peca.Cor.Preto, this); tabuleiro[0][7] = new Torre(Peca.Cor.Preto, this);
        tabuleiro[7][0] = new Torre(Peca.Cor.Branco, this); tabuleiro[7][7] = new Torre(Peca.Cor.Branco, this);

        tabuleiro[0][1] = new Cavalo(Peca.Cor.Preto); tabuleiro[0][6] = new Cavalo(Peca.Cor.Preto);
        tabuleiro[7][1] = new Cavalo(Peca.Cor.Branco); tabuleiro[7][6] = new Cavalo(Peca.Cor.Branco);

        tabuleiro[0][2] = new Bispo(Peca.Cor.Preto, this); tabuleiro[0][5] = new Bispo(Peca.Cor.Preto, this);
        tabuleiro[7][2] = new Bispo(Peca.Cor.Branco, this); tabuleiro[7][5] = new Bispo(Peca.Cor.Branco, this);

        tabuleiro[0][3] = new Rainha(Peca.Cor.Preto, this);
        tabuleiro[7][3] = new Rainha(Peca.Cor.Branco, this);

        tabuleiro[0][4] = new Rei(Peca.Cor.Preto, this);
        tabuleiro[7][4] = new Rei(Peca.Cor.Branco, this);

        for (int i = 0; i < 8; i++) {
            tabuleiro[1][i] = new Peao(Peca.Cor.Preto, this);
            tabuleiro[6][i] = new Peao(Peca.Cor.Branco, this);
        }
    }

    // Imprime o tabuleiro no console.
    public void imprimir() {
        System.out.println("\n  A B C D E F G H");
        for (int i = 0; i < 8; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                if (tabuleiro[i][j] == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(tabuleiro[i][j].getAparencia() + " ");
                }
            }
            System.out.println(" " + (i + 1));
        }
        System.out.println("  A B C D E F G H\n");
    }

    // Método que tenta efetuar o movimento entre origens e destinos.
    public boolean movimenta(char OrigemColuna, int OrigemLinha, char DestinoColuna, int DestinoLinha) {
        int oCol = Character.toUpperCase(OrigemColuna) - 'A';
        int dCol = Character.toUpperCase(DestinoColuna) - 'A';

        // Verifica se a posição informada está dentro dos limites do tabuleiro.
        if (OrigemLinha < 0 || OrigemLinha > 7 || oCol < 0 || oCol > 7) return false;
        if (DestinoLinha < 0 || DestinoLinha > 7 || dCol < 0 || dCol > 7) return false;

        Peca pecaOrigem = tabuleiro[OrigemLinha][oCol];
        if (pecaOrigem == null) return false;

        // Verifica se o movimento da peça é logicamente viável.
        if (pecaOrigem.movimento(oCol, OrigemLinha, dCol, DestinoLinha)) {
            Peca pecaDestino = tabuleiro[DestinoLinha][dCol];

            // Retorna falso se houver peça aliada no destino.
            if (pecaDestino != null && pecaOrigem.getCor() == pecaDestino.getCor()) {
                return false;
            }

            // Tratamento prévio do En Passant.
            boolean ehEnPassant = (pecaOrigem instanceof Peao) && (oCol != dCol) && (pecaDestino == null);
            Peca peaoCapturadoEnPassant = null;

            if (ehEnPassant) {
                peaoCapturadoEnPassant = tabuleiro[OrigemLinha][dCol];
                tabuleiro[OrigemLinha][dCol] = null;
            }

            // Efetua a troca no array bidimensional.
            tabuleiro[DestinoLinha][dCol] = pecaOrigem;
            tabuleiro[OrigemLinha][oCol] = null;

            // Instancia validador de Xeque.
            PecaControle pc = new PecaControle(pecaOrigem.getCor(), this);
            
            // Reverte o movimento caso ele coloque o próprio rei em xeque.
            if (pc.verificaCheck(pecaOrigem.getCor())) {
                tabuleiro[OrigemLinha][oCol] = pecaOrigem;
                tabuleiro[DestinoLinha][dCol] = pecaDestino;
                if (ehEnPassant) {
                    tabuleiro[OrigemLinha][dCol] = peaoCapturadoEnPassant;
                }
                return false;
            }

            // Move também a torre caso a ação realizada seja o Roque.
            if (pecaOrigem instanceof Rei && Math.abs(dCol - oCol) == 2) {
                if (dCol > oCol) {
                    if (pecaOrigem.getCor() == Peca.Cor.Branco) {
                        tabuleiro[7][5] = tabuleiro[7][7];
                        tabuleiro[7][7] = null;
                    } else {
                        tabuleiro[0][5] = tabuleiro[0][7];
                        tabuleiro[0][7] = null;
                    }
                } else {
                    if (pecaOrigem.getCor() == Peca.Cor.Branco) {
                        tabuleiro[7][3] = tabuleiro[7][0];
                        tabuleiro[7][0] = null;
                    } else {
                        tabuleiro[0][3] = tabuleiro[0][0];
                        tabuleiro[0][0] = null;
                    }
                }
            }

            // Atualiza a casa alvo do En Passant para o próximo turno.
            if (pecaOrigem instanceof Peao && Math.abs(DestinoLinha - OrigemLinha) == 2) {
                alvoEnPassant = new Posicao((OrigemLinha + DestinoLinha) / 2, dCol);
            } else {
                alvoEnPassant = null;
            }

            if (pecaOrigem instanceof Peao) {
                ((Peao) pecaOrigem).moveu = true;
                if ((pecaOrigem.getCor() == Peca.Cor.Branco && DestinoLinha == 0) ||
                    (pecaOrigem.getCor() == Peca.Cor.Preto && DestinoLinha == 7)) {
                    tabuleiro[DestinoLinha][dCol] = new Rainha(pecaOrigem.getCor(), this);
                }
            }

            // Marca que a peça já se moveu na partida.
            if (pecaOrigem instanceof Torre) ((Torre) pecaOrigem).setMoveu(true);
            if (pecaOrigem instanceof Rei) ((Rei) pecaOrigem).setMoveu(true);

            return true;
        }

        return false;
    }
}