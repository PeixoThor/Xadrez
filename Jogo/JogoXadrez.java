package Jogo;

import Pecas.PecaControle;
import java.util.Random;
import java.util.Scanner;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

// Classe base para inicializar a aplicação.
public class JogoXadrez {

    // Método principal.
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
 
        Tabuleiro tabuleiro = new Tabuleiro();
        Scanner sc = new Scanner(System.in);
        Peca.Cor turno = Peca.Cor.Branco;
        PecaControle pc = new PecaControle(null, tabuleiro);
        boolean rodando = true;

        System.out.println("Um jogo legal de Xadrez");
        System.out.println("Regras: letras maiusculas = Brancas. letras minusculas = Pretas");
        System.out.println("Escolha uma opcao de jogo: 1 - PVP. 2 - Maquina. : ");
        int op = sc.nextInt();

        switch (op) {
            case 1: 
                System.out.println("Jogo iniciado");
                while (rodando) {
                    // Avalia condição de fim de jogo.
                    if (pc.verificaXequeMate(turno)) {
                        tabuleiro.imprimir();
                        Peca.Cor vencedor = (turno == Peca.Cor.Branco) ? Peca.Cor.Preto : Peca.Cor.Branco;
                        System.out.println("Xeque-Mate! Fim de jogo. Jogador " + vencedor + " venceu.");
                        break;
                    }

                    // Notifica sobre a condição de Xeque.
                    if (pc.verificaCheck(turno)) {
                        System.out.println("Atencao: O Rei " + turno + " esta em xeque!");
                    }

                    tabuleiro.imprimir();
                    System.out.println("\nTurno do jogador: " + turno);
 
                    System.out.print("Digite a posicao de ORIGEM (ex: A 2 ou A 7): ");
                    char colOrigem = sc.next().charAt(0);
                    int linOrigem = sc.nextInt() - 1;
 
                    System.out.print("Digite a posicao de DESTINO (ex: A 4 ou A 5): ");
                    char colDestino = sc.next().charAt(0);
                    int linDestino = sc.nextInt() - 1;
 
                    int colOrigemNum = Character.toUpperCase(colOrigem) - 'A';
 
                    // Limites do tabuleiro.
                    if (linOrigem < 0 || linOrigem > 7 || colOrigemNum < 0 || colOrigemNum > 7) {
                        System.out.println("\n[ERRO] Posicao fora do tabuleiro!");
                        continue;
                    }

                    Peca pecaSelecionada = tabuleiro.getTabuleiro(linOrigem, colOrigemNum);
 
                    // Verifica se de fato há uma peça selecionada.
                    if (pecaSelecionada == null) {
                        System.out.println("\n[ERRO] Nao ha peca na posicao de origem informada!");
                        continue;
                    }
 
                    // Impede movimento na peça adversária.
                    if (pecaSelecionada.getCor() != turno) {
                        System.out.println("\n[ERRO] Esta peca e do seu oponente! Mova apenas as " + turno + "s.");
                        continue;
                    }
 
                    // Tenta movimentar.
                    boolean moveu = tabuleiro.movimenta(colOrigem, linOrigem, colDestino, linDestino);

                    if (moveu) {
                        System.out.println("\n-> Movimento realizado com sucesso!");
                        turno = (turno == Peca.Cor.Branco) ? Peca.Cor.Preto : Peca.Cor.Branco;
                    } else {
                        System.out.println("\n[ERRO] Movimento invalido!");
                    }
                }
                break;
 
            case 2:
                System.out.println("Jogo iniciado");
                Random gerador = new Random();
                char[] colunas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

                while (rodando) {
                    if (pc.verificaXequeMate(turno)) {
                        tabuleiro.imprimir();
                        Peca.Cor vencedor = (turno == Peca.Cor.Branco) ? Peca.Cor.Preto : Peca.Cor.Branco;
                        System.out.println("Xeque-Mate! Fim de jogo. Jogador " + vencedor + " venceu.");
                        break;
                    }

                    if (turno == Peca.Cor.Branco) {
                        if (pc.verificaCheck(turno)) {
                            System.out.println("Atencao: Seu Rei esta em xeque!");
                        }

                        tabuleiro.imprimir();
                        System.out.println("\nTurno do jogador.");

                        System.out.print("Digite a posicao de ORIGEM (ex: A 2 ou A 7): ");
                        char colOrigem = sc.next().charAt(0);
                        int linOrigem = sc.nextInt() - 1;

                        System.out.print("Digite a posicao de DESTINO (ex: A 4 ou A 5): ");
                        char colDestino = sc.next().charAt(0);
                        int linDestino = sc.nextInt() - 1;

                        int colOrigemNum = Character.toUpperCase(colOrigem) - 'A';

                        if (linOrigem < 0 || linOrigem > 7 || colOrigemNum < 0 || colOrigemNum > 7) {
                            System.out.println("\n[ERRO] Posicao fora do tabuleiro!");
                            continue;
                        }

                        Peca pecaSelecionada = tabuleiro.getTabuleiro(linOrigem, colOrigemNum);

                        if (pecaSelecionada == null) {
                            System.out.println("\n[ERRO] Nao ha peca na posicao de origem informada!");
                            continue;
                        }

                        if (pecaSelecionada.getCor() != turno) {
                            System.out.println("\n[ERRO] Esta peca e do seu oponente! Mova apenas as " + turno + "s.");
                            continue;
                        }

                        boolean moveu = tabuleiro.movimenta(colOrigem, linOrigem, colDestino, linDestino);

                        if (moveu) {
                            System.out.println("\n-> Movimento realizado com sucesso!");
                            turno = Peca.Cor.Preto;
                        } else {
                            System.out.println("\n[ERRO] Movimento invalido!");
                        }
                    } else {
                        // Jogada automática da máquina.
                        int aux = gerador.nextInt(8);
                        char colOrigem = colunas[aux];
                        int IndiceColOrigem = aux;
                        int linOrigem = gerador.nextInt(8);

                        aux = gerador.nextInt(8);
                        char colDestino = colunas[aux];
                        int linDestino = gerador.nextInt(8);

                        Peca pecaSelecionada = tabuleiro.getTabuleiro(linOrigem, IndiceColOrigem);
                        if (pecaSelecionada == null || pecaSelecionada.getCor() != turno) {
                            continue;
                        }

                        boolean moveu = tabuleiro.movimenta(colOrigem, linOrigem, colDestino, linDestino);
                        if (moveu) {
                            System.out.println("\n-> Maquina jogou!");
                            turno = Peca.Cor.Branco;
                        }
                    }
                }
                break;

            default:
                System.out.println("Nao existe essa opcao de jogo");
        } 
        sc.close();
    }
}