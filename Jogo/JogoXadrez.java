package Jogo;

import Pecas.PecaControle;
import java.util.Random;
import java.util.Scanner;

import Jogo.Peca.Cor;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class JogoXadrez {

    public static void main(String[] args) {
        // 1. CONFIGURAÇÃO INICIAL DA SAÍDA E VARIÁVEIS DE CONTROLE
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        
        Tabuleiro tabuleiro = new Tabuleiro();
        Scanner sc = new Scanner(System.in);
        Cor turno = Cor.Branco;
        PecaControle pc = new PecaControle(null, tabuleiro);
        boolean rodando = true;
        
        System.out.println("Um jogo legal de Xadrez");
        System.out.println("Regras: letras maiúsculas = Brancas. letras minúsculas = Pretas");
        System.out.println("Escolha uma opção de jogo: 1 - PVP. 2 - Máquina. : ");
        int op = sc.nextInt();
        
        // 2. SELEÇÃO DO MODO DE JOGO
        switch (op) {
            case 1: 
                // 3. MODO PVP (JOGADOR VS JOGADOR)
                System.out.println("Jogo iniciado");
                while (rodando) {
                    if (pc.verificaCheck(turno)) {
                        turno = (turno == Cor.Branco) ? Cor.Preto : Cor.Branco; 
                        rodando = false;
                        System.out.println("Fim de jogo. Jogador: " + turno + " Venceu");
                        break;
                    }
                    
                    tabuleiro.imprimir();
                    System.out.println("\nTurno do jogador: " + turno);
                    
                    // Captura a posição de origem
                    System.out.print("Digite a posição de ORIGEM (ex: A 2 ou A 7): ");
                    char colOrigem = sc.next().charAt(0);
                    int linOrigem = sc.nextInt() - 1;
                    
                    // Captura a posição de destino
                    System.out.print("Digite a posição de DESTINO (ex: A 4 ou A 5): ");
                    char colDestino = sc.next().charAt(0);
                    int linDestino = sc.nextInt() - 1;
                    
                    // Converte a coluna para índice numérico e pega a peça
                    int colOrigemNum = Character.toUpperCase(colOrigem) - 'A';
                    Peca pecaSelecionada = tabuleiro.getTabuleiro(linOrigem, colOrigemNum);
                    
                    // Verifica se existe uma peça na posição
                    if (pecaSelecionada == null) {
                        System.out.println("\n[ERRO] Não há peça na posição de origem informada!");
                        continue;
                    }
                    
                    // Verifica se não é a peça do oponente
                    if (pecaSelecionada.getCor() != turno) {
                        System.out.println("\n[ERRO] Esta peça é do seu oponente! Mova apenas as " + turno + "s.");
                        continue;
                    }
                    
                    // Executa a tentativa de movimento
                    boolean moveu = tabuleiro.movimenta(colOrigem, linOrigem, colDestino, linDestino);
                    System.out.println(moveu);

                    if (moveu) {
                        System.out.println("\n-> Movimento realizado com sucesso!");
                        turno = (turno == Cor.Branco) ? Cor.Preto : Cor.Branco;
                    } else {
                        System.out.println("\n[ERRO] Movimento inválido para esta peça!");
                    }
                }
                break;
                         
            case 2:
                // 4. MODO MÁQUINA (JOGADOR VS COMPUTADOR)
                System.out.println("Jogo iniciado");
                while (rodando) {
                    if (turno == Cor.Branco) {
                        tabuleiro.imprimir();
                        System.out.println("\nTurno do jogador. ");

                        // Captura a posição de origem
                        System.out.print("Digite a posição de ORIGEM (ex: A 2 ou A 7): ");
                        char colOrigem = sc.next().charAt(0);
                        int linOrigem = sc.nextInt() - 1;

                        // Captura a posição de destino
                        System.out.print("Digite a posição de DESTINO (ex: A 4 ou A 5): ");
                        char colDestino = sc.next().charAt(0);
                        int linDestino = sc.nextInt() - 1;

                        // Converte a coluna para índice e pega a peça
                        int colOrigemNum = Character.toUpperCase(colOrigem) - 'A';
                        Peca pecaSelecionada = tabuleiro.getTabuleiro(linOrigem, colOrigemNum);

                        // Validações iniciais
                        if (pecaSelecionada == null) {
                            System.out.println("\n[ERRO] Não há peça na posição de origem informada!");
                            continue;
                        }
                        if (pecaSelecionada.getCor() != turno) {
                            System.out.println("\n[ERRO] Esta peça é do seu oponente! Mova apenas as " + turno + "s.");
                            continue;
                        }

                        // Executa a tentativa de movimento
                        boolean moveu = tabuleiro.movimenta(colOrigem, linOrigem, colDestino, linDestino);

                        if (moveu) {
                            System.out.println("\n-> Movimento realizado com sucesso!");
                            turno = (turno == Cor.Branco) ? Cor.Preto : Cor.Branco;
                        } else {
                            System.out.println("\n[ERRO] Movimento inválido para esta peça!");
                        }
                    } else {
                        // 5. TURNO DA MÁQUINA (Movimentos Aleatórios)
                        Random gerador = new Random();
                        char[] colunas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
                        
                        int aux = gerador.nextInt(8);
                        char colOrigem = colunas[aux];
                        int indiceColOrigem = aux;
                        
                        int linOrigem = gerador.nextInt(8);
                        
                        aux = gerador.nextInt(8);
                        char colDestino = colunas[aux];
                        int linDestino = gerador.nextInt(8);
                        
                        Peca pecaSelecionada = tabuleiro.getTabuleiro(linOrigem, indiceColOrigem);
                        
                        if (pecaSelecionada == null || pecaSelecionada.getCor() != turno) {
                            continue;
                        }
                        
                        boolean moveu = tabuleiro.movimenta(colOrigem, linOrigem, colDestino, linDestino);
                        if (moveu) {
                             turno = (turno == Cor.Branco) ? Cor.Preto : Cor.Branco;
                        }
                    }
                }
                break;
                
            default:
                System.out.println("Não existe essa opção de jogo");
        } 
        
        sc.close();
    }
}