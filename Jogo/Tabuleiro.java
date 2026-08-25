package Jogo;

import Jogo.Peca.Cor;
import Pecas.Bispo;
import Pecas.Cavalo;
import Pecas.Peao;
import Pecas.Rainha;
import Pecas.Rei;
import Pecas.Torre;

public class Tabuleiro {
    private Peca[][] tabuleiro = new Peca[8][8];
    
    public Tabuleiro() {
        preencher();
    }

    public Peca getTabuleiro(int x, int y) {
        return tabuleiro[x][y];
    }

    // 1. PREENCHIMENTO INICIAL DO TABULEIRO
    public void preencher() {
        // Torres
        tabuleiro[0][0] = new Torre(Cor.Preto, this);  tabuleiro[0][7] = new Torre(Cor.Preto, this);
        tabuleiro[7][0] = new Torre(Cor.Branco, this); tabuleiro[7][7] = new Torre(Cor.Branco, this);
        
        // Cavalos
        tabuleiro[0][1] = new Cavalo(Cor.Preto);  tabuleiro[0][6] = new Cavalo(Cor.Preto);
        tabuleiro[7][1] = new Cavalo(Cor.Branco); tabuleiro[7][6] = new Cavalo(Cor.Branco);

        // Bispos
        tabuleiro[0][2] = new Bispo(Cor.Preto, this);  tabuleiro[0][5] = new Bispo(Cor.Preto, this);
        tabuleiro[7][2] = new Bispo(Cor.Branco, this); tabuleiro[7][5] = new Bispo(Cor.Branco, this);
        
        // Rainhas
        tabuleiro[0][3] = new Rainha(Cor.Preto, this);
        tabuleiro[7][3] = new Rainha(Cor.Branco, this);

        // Reis
        tabuleiro[0][4] = new Rei(Cor.Preto, this);
        tabuleiro[7][4] = new Rei(Cor.Branco, this);

        // Peões
        for (int i = 0; i < 8; i++) {
            tabuleiro[1][i] = new Peao(Cor.Preto);
            tabuleiro[6][i] = new Peao(Cor.Branco);
        }
    }

    // 2. LÓGICA DE MOVIMENTAÇÃO PELO TABULEIRO
    public boolean movimenta(char origemColuna, int origemLinha, char destinoColuna, int destinoLinha) {
        int origemColunaNumero = 0, destinoColunaNumero = 0;

        // Converte a coluna de origem (Letra) para índice (Número)
        switch (origemColuna) {
            case 'A': origemColunaNumero = 0; break;
            case 'B': origemColunaNumero = 1; break;
            case 'C': origemColunaNumero = 2; break;
            case 'D': origemColunaNumero = 3; break;
            case 'E': origemColunaNumero = 4; break;
            case 'F': origemColunaNumero = 5; break;
            case 'G': origemColunaNumero = 6; break;
            case 'H': origemColunaNumero = 7; break;
            default: break;
        }

        // Converte a coluna de destino (Letra) para índice (Número)
        switch (destinoColuna) {
            case 'A': destinoColunaNumero = 0; break;
            case 'B': destinoColunaNumero = 1; break;
            case 'C': destinoColunaNumero = 2; break;
            case 'D': destinoColunaNumero = 3; break;
            case 'E': destinoColunaNumero = 4; break;
            case 'F': destinoColunaNumero = 5; break;
            case 'G': destinoColunaNumero = 6; break;
            case 'H': destinoColunaNumero = 7; break;
            default: break;
        }

        // Valida o movimento através da própria peça
        if (tabuleiro[origemColunaNumero][origemLinha].movimento(origemColunaNumero, origemLinha, destinoColunaNumero, destinoLinha)) {
            
            // Impede a sobreposição de peças da mesma cor
            if (tabuleiro[destinoColunaNumero][destinoLinha] != null && 
                tabuleiro[origemColunaNumero][origemLinha].getCor() == tabuleiro[destinoColunaNumero][destinoLinha].getCor()) {
                return false;
            }
            
            // Efetua a troca de posições no array bidimensional
            tabuleiro[destinoColunaNumero][destinoLinha] = tabuleiro[origemColunaNumero][origemLinha];
            tabuleiro[origemColunaNumero][origemLinha] = null;
        } else {
            return false;
        }

        return true;
    }
}