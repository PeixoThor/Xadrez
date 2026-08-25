package Jogo;

// Classe abstrata para a peça.
public abstract class Peca { 

    // Enumerador de cores das peças.
    public enum Cor {
        Branco, Preto;
    }
    
    private Cor cor;
    private String aparencia = "";

    // Construtor da peça.
    public Peca(Cor c) {
        this.cor = c;
    }

    // Retorna a cor da peça.
    public Cor getCor() {
        return cor;
    }

    // Retorna a aparência (símbolo) da peça.
    public String getAparencia() {
        return aparencia;
    }

    // Define a aparência da peça.
    public void setAparencia(String aparencia) {
        this.aparencia = aparencia;
    }
    
    // Método de validação do movimento para essa peça: retorna True se o movimento for possível.
    public abstract boolean movimento(int x0, int y0, int x1, int y1);
}