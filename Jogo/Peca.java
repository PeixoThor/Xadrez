package Jogo;

// 1. CLASSE ABSTRATA BASE PARA TODAS AS PEÇAS
public abstract class Peca {  
    
    // 2. ENUMERADOR DE CORES
    public enum Cor {
        Branco, Preto;
    }
  
    private Cor cor;

    public Cor getCor() {
        return cor;
    }
    
    public Peca(Cor c) {
        this.cor = c;
    }
  
    // 3. ASSINATURA DO MÉTODO DE MOVIMENTO (A ser implementado pelas classes filhas)
    public abstract boolean movimento(int x0, int y0, int x1, int y1);
}