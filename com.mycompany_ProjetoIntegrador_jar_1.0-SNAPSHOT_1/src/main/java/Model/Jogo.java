/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author mitch
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Telas.TelaJogo1;
import BD.DisciplinasDAO;
import BD.PerguntasDAO;
import BD.RankingDAO;
import Telas.TelaGanhou;
import java.util.Arrays;

public class Jogo {

    private int pontuacao;
    private int num_questao = 1;
    private int checkpoint = 1;
    private int dificuldade = 1;
    private List<Integer> id_perguntas_feitas = new ArrayList<>();
    private List<Integer> id_perguntas_disponiveis = new ArrayList<>();
    private List<Integer> disciplinas;
    private static Random random = new Random();
    public  boolean[] dicas = {true, true, true};//{Pular, 50/50, 75}
    private int resposta;
    private TelaJogo1 tela;
    private PerguntasDAO db = new PerguntasDAO();
    private Alternativa correta;
    private List<Alternativa> alternativas;
    private List<Alternativa> incorretas = new ArrayList<>();

    public Jogo(TelaJogo1 tela) {
        this.tela = tela;
        if (disciplinas == null || disciplinas.isEmpty()) {
            disciplinas = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                disciplinas.add(i);
            }
            setIdPerguntasDisponiveis();
            atualiza_dificuldade();
            gerarPergunta();
        }
    }

    public Jogo(TelaJogo1 tela, List<Integer> disciplinas) {
        this(tela);
        if(disciplinas != null && !disciplinas.isEmpty())this.disciplinas = disciplinas;
        setIdPerguntasDisponiveis();
        atualiza_dificuldade();
        gerarPergunta();
    }

    public void gerarPergunta() {
        int id_pergunta;
        // Geração de id da pergunta
        do {
            if (id_perguntas_disponiveis.isEmpty()) {
                setIdPerguntasDisponiveis();
                id_perguntas_feitas.clear();
            }
            id_pergunta = id_perguntas_disponiveis.get(random.nextInt(id_perguntas_disponiveis.size()));
            id_perguntas_disponiveis.remove(Integer.valueOf(id_pergunta));
        } while (id_perguntas_feitas.contains(id_pergunta));
        Pergunta pergunta = db.getPerguntacomAlternativa(id_pergunta);
        id_perguntas_feitas.add(pergunta.getId());
        alternativas = new ArrayList<>(pergunta.getAlternativas());
        // Exibição das perguntas e respostas
        tela.exibePergunta(pergunta.getPergunta());

        for (Alternativa i : alternativas) {
            if (i.isCorreta()) {
                correta = i;
            }
        }

        tela.exibeAlternativas(alternativas);
    }

    public void pularQuestao() {
        if (dicas[0]) {
            dicas[0] = false;
            gerarPergunta();
        }
    }

    public void dicaRemove2() {
        if (dicas[1]) {
            dicas[1] = false;
            for (Alternativa i : alternativas) {
                if (!i.isCorreta()) {
                    incorretas.add(i);
                }
            }
            if (!incorretas.isEmpty()) {
                Alternativa removida1 = incorretas.get(random.nextInt(incorretas.size()));
                Alternativa removida2;
                do {
                    removida2 = incorretas.get(random.nextInt(incorretas.size()));
                } while (removida1.equals(removida2));

                alternativas.set(alternativas.indexOf(removida1), null);
                alternativas.set(alternativas.indexOf(removida2), null);
                tela.exibeAlternativas(alternativas);
            }
        }
    }

    public void dicaUniversitarios() {
        if (dicas[2]) {
            dicas[2] = false;
            int indiceCorreta = alternativas.indexOf(correta);
            double[] porcentagens = generateRiggedArray(indiceCorreta);
            for (int i = 0; i < 5; i++) {
                Alternativa alternativa = alternativas.get(i);
                alternativa.setTexto(alternativa.getTexto() + " / " + aredonda(porcentagens[i]) + " por cento escolheram");
            }
            tela.exibeAlternativas(alternativas);
        }
    }

//        Alternativa alternativa_escolhida = pergunta.getAlternativas().get(resposta);
    public boolean tratandoResposta(Alternativa alternativa_escolhida) {
        // Tramento do Acerto
        if (alternativa_escolhida == correta) {
            pontuacao += 10 * Math.pow(2.2, num_questao - 1);
            num_questao++;
            System.out.println("Resposta Correta\n");
            System.out.println("antes: " + dificuldade);
            atualizaBarra(true);
            checkpoint(true);
            System.out.println("depois: " + dificuldade);
            return true;

        } else {
            for (int i = 0; i < --num_questao - checkpoint; i++) {
                pontuacao -= 10 * Math.pow(2.2, num_questao - 1);
            }
            pontuacao -= pontuacao * 0.05;
            System.out.println("Resposta Incorreta\n");
            atualizaBarra(false);
            checkpoint(false);
            return false;
        }

        // Finalização do jogo
    }

    public void atualizaBarra(boolean acertou) {
        switch (dificuldade) {
            case 1:
                tela.atualizaBarraVerde(acertou);
                break;
            case 2:
                tela.atualizaBarraAmarela(acertou);
                break;
            case 3:
                tela.atualizaBarraVermelha(acertou);
                break;
        }
    }

    public void finalizaJogo() {
        if (num_questao > 12) {
            TelaGanhou telaNova = new TelaGanhou(tela.getUsuario(),disciplinas);
            telaNova.setPontuacao(pontuacao);
            RankingDAO ranking = new RankingDAO();
            ranking.atualizaRanking(tela.getUsuario(), pontuacao);
            telaNova.setVisible(true);
            tela.dispose();
            
        }
    }

    private void checkpoint(boolean acertou) {
        final int[] checkPoints = {1, 5, 9};

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
        id_perguntas_disponiveis = db.ids_disponiveis(dificuldade, disciplinas);
    }

    private void atualiza_dificuldade() {
        switch (checkpoint) {
            case 0:
                dificuldade = 1;
                break;
            case 5:
                dificuldade = 2;
                break;
            case 9:
                dificuldade = 3;
                break;
            default:
                break;
        }
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }

    public Alternativa getCorreta() {
        return correta;
    }

    public double[] generateRiggedArray(int riggedIndex) {
        if (riggedIndex < 0 || riggedIndex >= 5) {
            throw new IllegalArgumentException("riggedIndex must be between 0 and 4.");
        }

        double[] raw = new double[5];
        double[] result = new double[5];
        Random random = new Random();

        boolean allowOutliers = random.nextDouble() < 0.75; // 75% chance to allow outliers
        boolean rig = random.nextDouble() < 0.75; // 75% chance to rig highest

        double total = 0.0;

        // Step 1: Generate raw values
        for (int i = 0; i < 5; i++) {
            double base = 1 + random.nextDouble() * 89; // [1, 90)

            // Allow outliers with exaggerated distribution
            if (allowOutliers) {
                raw[i] = Math.pow(base, 1.5); // exaggerates differences
            } else {
                raw[i] = base; // flatter values
            }

            total += raw[i];
        }

        // Step 2: Normalize to sum 100.0
        for (int i = 0; i < 5; i++) {
            result[i] = raw[i] * 100.0 / total;
        }

        // Step 3: Apply rigging if needed
        if (rig) {
            double maxOther = 0.0;
            for (int i = 0; i < 5; i++) {
                if (i != riggedIndex && result[i] > maxOther) {
                    maxOther = result[i];
                }
            }

            if (result[riggedIndex] <= maxOther) {
                double diff = maxOther - result[riggedIndex] + 0.01;

                for (int i = 0; i < 5 && diff > 0.0001; i++) {
                    if (i != riggedIndex && result[i] > 0.5) {
                        double give = Math.min(diff, result[i] - 0.5);
                        result[i] -= give;
                        result[riggedIndex] += give;
                        diff -= give;
                    }
                }
            }
        }

        // Step 4: Final normalization (to correct any drift)
        double sum = Arrays.stream(result).sum();
        for (int i = 0; i < 5; i++) {
            result[i] = result[i] * 100.0 / sum;
        }

        return result;
    }

    public static double aredonda(double numero) {
        return Math.round(numero * 10) / 10.0;
    }

}
