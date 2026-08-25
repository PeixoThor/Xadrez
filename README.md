# ♟️ Jogo de Xadrez em Java

Um jogo de Xadrez interativo executado no terminal (CLI), totalmente estruturado utilizando os conceitos de Programação Orientada a Objetos (POO) em Java. O projeto conta com regras de movimentação para as peças, sistema de turnos, verificação de xeque e opção de jogar contra a máquina.

## 📂 Estrutura do Projeto

O projeto foi refatorado e dividido em dois pacotes (pastas) principais. Essa estrutura separa a mecânica central e o controle do jogo das regras e comportamentos específicos de cada peça:

```text
Xadrez/
├── Jogo/                   # Classes de controle do jogo e regras de base
│   ├── JogoXadrez.java     # Classe Principal (Main): gerencia menus, inputs e turnos
│   ├── Peca.java           # Classe abstrata: serve de base para os atributos das peças (Cor, Movimento)
│   ├── Posicao.java        # Representa e encapsula as coordenadas X e Y
│   └── Tabuleiro.java      # Matriz 8x8: Popula peças iniciais e executa movimentações
│
├── Pecas/                  # Implementações das Peças e Validações Especiais
│   ├── Bispo.java          # Lógica: diagonais
│   ├── Cavalo.java         # Lógica: formato em L (salto)
│   ├── Peao.java           # Lógica: avanço frontal e avanço duplo inicial
│   ├── Rainha.java         # Lógica: movimentação livre em retas e diagonais
│   ├── Rei.java            # Lógica: 1 casa ao redor + verificação de Roque (Longo/Curto)
│   ├── Torre.java          # Lógica: movimentação reta (linhas e colunas)
│   └── PecaControle.java   # Controlador de regras avançadas (ex: Varredura de Xeque)
│
└── README.md
```

## 🎮 Funcionalidades e Modos

- **Menu Interativo:** Escolha o modo de jogo antes da partida iniciar.
- **Modo PvP (Jogador vs Jogador):** Partida local onde dois jogadores alternam turnos no mesmo terminal.
- **Modo PvE (Máquina):** Jogue contra o computador. Quando chega o turno das pretas, o gerador escolhe peças e movimentos aleatórios (porém validados) de forma autônoma.
- **Regras Clássicas Implementadas:**
  - Sistema de detecção de **Xeque** e impedimento de auto-xeque (o Rei não pode andar para uma casa ameaçada).
  - Condição de **Roque** validada entre o Rei e a Torre (ambos não podem ter se movido).
  - Bloqueio de avanço: As peças não saltam umas às outras (exceto o Cavalo).
  - Capturas e impedimento de captura de peças da própria cor.

## 🚀 Como Compilar e Executar

Este projeto foi construído para ser fácil de executar em qualquer ambiente com o **Java (JDK)** instalado.

### Via Visual Studio Code (Recomendado):
1. Abra a pasta `Xadrez` no VS Code.
2. Navegue até o arquivo `Jogo/JogoXadrez.java`.
3. Clique em **Run** ou aperte `F5` caso tenha a extensão *Extension Pack for Java* configurada.

### Via Terminal Manual:
Navegue até o diretório principal do projeto (onde está o README) e rode os seguintes comandos:

```bash
# 1. Compile todos os arquivos .java das duas pastas
javac Jogo/*.java Pecas/*.java

# 2. Execute a classe principal informando o pacote (se houver)
java Jogo.JogoXadrez
```

## 📌 Como Jogar

1. **Letras Maiúsculas e Minúsculas:** O código utiliza formatação limpa e Símbolos UTF-8 (como ♚, ♞, ♜) para tornar o tabuleiro mais visual. As peças brancas e pretas têm desenhos distintos no console.
2. **Entrada de Dados:** Quando for sua vez, o jogo solicitará a coluna e a linha. Utilize o formato de letra (coluna de A-H) e número (linha de 1-8).
   - *Exemplo de Origem:* `A 2`
   - *Exemplo de Destino:* `A 4`

---
*Desenvolvido em Java como exercício prático de Programação Orientada a Objetos.*
