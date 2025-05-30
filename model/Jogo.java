package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import db.DisciplinasDAO;
import db.PerguntasDAO;

public class Jogo {
    private static int pontuacao;
    private static int num_questao = 1;
    private static int checkpoint = 1;
    private static boolean jogo_rodando = true;
    private static int dificuldade = 1;
    private List<Integer> id_perguntas_feitas = new ArrayList<>();
    private List<Integer> id_perguntas_disponiveis = new ArrayList<>();
    private static Random random = new Random();
    public static boolean[] dicas = {true,true,true};//{Pular, 50/50, 75}
    Scanner scanner = new Scanner(System.in);

    public Jogo() {
        PerguntasDAO db = new PerguntasDAO();
        atualiza_dificuldade();
        setIdPerguntasDisponiveis();
        while (jogo_rodando) {
            int id_pergunta;
            // Geração de id da pergunta
            do {
                if (id_perguntas_disponiveis.isEmpty()) {
                    id_perguntas_feitas.clear();
                }
                id_pergunta = id_perguntas_disponiveis.get(random.nextInt(id_perguntas_disponiveis.size()));
                id_perguntas_disponiveis.remove(Integer.valueOf(id_pergunta));
            } while (id_perguntas_feitas.contains(id_pergunta));
            Pergunta pergunta = db.getPerguntacomAlternativa(id_pergunta);
            id_perguntas_feitas.add(pergunta.getId());

            // Exibição das perguntas e respostas
            System.out.println("Questão " + num_questao);
            System.out.println(pergunta.getPergunta());
            Alternativa correta = null;
            for (Alternativa i : pergunta.getAlternativas()) {
                if (i.isCorreta())
                    correta = i;
                System.out.println(i);
            }
            System.out
                    .println("Digite o número da questão, 50 para 50/50, 75 para excluir uma errada, ou 99 para pular");
            int resposta = scanner.nextInt();

            if (resposta == 99) {
                if (dicas[0]) {
                    dicas[0] = false;
                    System.out.println("Você pulou a pergunta!");
                    continue;
                } else {
                    System.out.println("Você já pulou uma pergunta.");
                    resposta = scanner.nextInt();
                }
            }
            List<Alternativa> alternativas = new ArrayList<>(pergunta.getAlternativas());
            List<Alternativa> incorretas = new ArrayList<>();
            if (resposta == 75) {
                if (dicas[1]) {
                    dicas[1] = false;
                    for (Alternativa i : alternativas) {
                        if (!i.isCorreta())
                            incorretas.add(i);
                    }
                    if (!incorretas.isEmpty()) {
                        Alternativa removida = incorretas.get(random.nextInt(incorretas.size()));
                        alternativas.remove(removida);
                        System.out.println("Uma alternativa incorreta foi removida.");
                    }
                    for (int i = 0; i < alternativas.size(); i++) {
                        System.out.println(i + ": " + alternativas.get(i));
                    }
                    System.out.println("Digite o número da questão:");
                    resposta = scanner.nextInt();
                } else {
                    System.out.println("Você já usou a exclusão.");
                    resposta = scanner.nextInt();
                }
            }
            if (resposta == 50) {
                if (dicas[2]) {
                    dicas[2] = false;
                    for (Alternativa i : alternativas) {
                        if (!i.isCorreta())
                            incorretas.add(i);
                    }
                    for (int i = 0; i < 2 && !incorretas.isEmpty(); i++) {
                        Alternativa removida = incorretas.remove(random.nextInt(incorretas.size()));
                        alternativas.remove(removida);
                    }
                    System.out.println("Duas alternativas incorretas foram removidas.");
                    for (int i = 0; i < alternativas.size(); i++) {
                        System.out.println(i + ": " + alternativas.get(i));
                    }
                    System.out.println("Digite o número da questão:");
                    resposta = scanner.nextInt();
                } else {
                    System.out.println("Você já usou o 50/50.");
                    resposta = scanner.nextInt();
                }
            }

            Alternativa alternativa_escolhida = pergunta.getAlternativas().get(resposta);

            // Tramento do Acerto
            if (alternativa_escolhida == correta) {
                pontuacao += 10 * Math.pow(2.2, num_questao - 1);
                num_questao++;
                System.out.println("Resposta Correta\n");
                checkpoint(true);
            }

            // Tratamento do Erro
            else {
                for (int i = 0; i < --num_questao - checkpoint; i++) {
                    pontuacao -= 10 * Math.pow(2.2, num_questao - 1);
                }
                pontuacao -= pontuacao * 0.05;
                System.out.println("Resposta Incorreta\n");
                checkpoint(false);
            }

            System.out.println("Pontuação: " + pontuacao);

            // Finalização do jogo
            if (num_questao > 12) {
                System.out.println("Parabéns, você venceu.");
                jogo_rodando = false;
            }
        }
        scanner.close();
    }

    private void checkpoint(boolean acertou) {
        final int[] checkPoints = { 1, 4, 8 };

        if (acertou) {
            for (int i : checkPoints) {
                if (i == num_questao) {
                    checkpoint = i;
                    atualiza_dificuldade();
                    setIdPerguntasDisponiveis();
                    System.out.println("Checkpoint Salvo em " + i);
                }
            }
        } else {
            num_questao = checkpoint;
            System.out.println("Voltando para o checkpoint " + checkpoint);
        }
    }

    private void setIdPerguntasDisponiveis() {
        DisciplinasDAO dis = new DisciplinasDAO();
        PerguntasDAO db = new PerguntasDAO();
        id_perguntas_disponiveis = db.ids_disponiveis(dificuldade, dis.getListaDisciplinas());
    }

    private void atualiza_dificuldade() {
        switch (checkpoint) {
            case 0:
                dificuldade = 1;
                break;
            case 4:
                dificuldade = 2;
                break;
            case 8:
                dificuldade = 3;
                break;
            default:
                break;
        }
    }
}
