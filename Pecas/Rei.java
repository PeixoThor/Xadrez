package Pecas;

import Jogo.Peca;
import Jogo.Tabuleiro;

// Classe para a peça Rei.
public class Rei extends Peca {
    private Tabuleiro tab; 
    private boolean moveu = false;
    private PecaControle p;

    // Retorna status de movimentação da peça.
    public boolean getMoveu() { return moveu; }
    
    // Altera status de movimentação.
    public void setMoveu(boolean moveu) { this.moveu = moveu; }

    // Método de validação do movimento para essa peça: retorna True se o movimento for possível.
    public boolean movimento(int x0, int y0, int x1, int y1){
        int Dx = Math.abs(x1 - x0);
        int Dy = Math.abs(y1 - y0);

        // Movimentação normal: 1 casa para cada direção.
        if ((Dx <= 1 && Dy <= 1) && (Dx + Dy > 0)) {
            // Verifica se o rei não estará em ataque na casa de destino.
            if (!p.verificaCheck(this.getCor(), x1, y1)) {
                moveu = true;
                return true;
            }
            return false;
        } 

        // Movimentação por Roque: 2 casas para esquerda ou direita se rei e torre não se movimentaram.
        if (Dy == 0 && Dx == 2 && !moveu) {
            // Verifica se o rei não está ameaçado atualmente.
            if (p.verificaCheck(this.getCor(), x0, y0)) {
                return false;
            }

            // Determina a direção do roque (esquerda ou direita).
            int dirX = (x1 > x0) ? 1 : -1;   

            // Roque curto (direita).
            if (dirX == 1) {
                // Posição em que a torre estará.
                int torreX = x0 + 3;
                Peca torre = tab.getTabuleiro(y0, torreX);

                // A peça na posição da torre deve ser uma torre e não pode ter se movido ainda.
                if (!(torre instanceof Torre) || ((Torre) torre).getMoveu()) {
                    return false;
                }
                
                // Aqui inicia a testagem bruta se as casas intermediárias estão vazias.
                // Casa seguinte vazia.
                if (tab.getTabuleiro(y0, x0 + 1) != null) {
                    return false;
                }
                // Casa de destino vazia.
                if (tab.getTabuleiro(y0, x0 + 2) != null) {
                    return false;
                }
                // Casa seguinte sem ameaça de xeque.
                if (p.verificaCheck(this.getCor(), x0 + 1, y0)) {
                    return false;
                }
                // Casa de destino sem ameaça de xeque.
                if (p.verificaCheck(this.getCor(), x0 + 2, y0)) {
                    return false;
                }

                // Se chegou até aqui: Roque possível, então movimenta a torre manualmente.
                 if (!tab.movimenta('H', y0, 'F', y0)) {
                    return false;   // Caso o movimento da torre não aconteça.
                }

                moveu = true;
                return true;
            }
            // Roque Longo (esquerda).
            else {
                // Posição em que a torre estará.
                int torreX = x0 - 4;
                Peca torre = tab.getTabuleiro(y0, torreX);

                // A peça na posição da torre deve ser uma torre e não pode ter se movido ainda.
                if (!(torre instanceof Torre) || ((Torre) torre).getMoveu()) {
                    return false;
                }

                // Aqui inicia a testagem bruta se as casas intermediárias estão vazias.
                // Primeira casa intermediária.
                if (tab.getTabuleiro(y0, x0 - 1) != null) {
                    return false;
                }
                // Segunda casa intermediária.
                if (tab.getTabuleiro(y0, x0 - 2) != null) {
                    return false;
                }
                // Terceira casa intermediária.
                if (tab.getTabuleiro(y0, x0 - 3) != null) {
                    return false;
                }
                // A primeira não pode estar ameaçada.
                if (p.verificaCheck(this.getCor(), x0 - 1, y0)) {
                    return false;
                }
                // A segunda (destino) não pode estar ameaçada. 
                if (p.verificaCheck(this.getCor(), x0 - 2, y0)) {
                    return false;
                }

                // Roque possível: movimenta a torre e retorna pra chamada original.
                if (!tab.movimenta('A', y0, 'D', y0)) {
                    return false; // Caso o movimento da torre não aconteça.
                }

                moveu = true;
                return true;
            }
        } 
        return false;    
    }

    // Construtor do rei. 
    public Rei(Cor c, Tabuleiro tabuleiro) {
        super(c);
        tab = tabuleiro;
        this.p = new PecaControle(c, tabuleiro);
        if(c == Cor.Branco){
            this.setAparencia("\u265A");
        } else {
            this.setAparencia("\u2654");
        }
    }   
}