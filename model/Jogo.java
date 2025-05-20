package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import db.PerguntasDAO;

public class Jogo{
    private static int pontuacao;
    private static int checkpoint;
    private static boolean jogo_rodando = true;
    private List<Integer> id_perguntas_feitas = new ArrayList<>();
    private List<Integer> id_perguntas_disponiveis = new ArrayList<>();
    private static Random random = new Random();
    private static int dificuldade = 1; 
    
    Scanner scanner = new Scanner(System.in);
    public Jogo(){
        PerguntasDAO db = new PerguntasDAO(); 
        atualiza_perguntas_disponiveis(db);
        while (jogo_rodando) {
            int id;
            //Geração de id da pergunta
            do{
                if(id_perguntas_disponiveis.isEmpty()) {
                    atualiza_perguntas_disponiveis(db);
                    id_perguntas_feitas.clear();
                }
                id = id_perguntas_disponiveis.get(random.nextInt(id_perguntas_disponiveis.size()));
                id_perguntas_disponiveis.remove(Integer.valueOf(id));
            }
            while(id_perguntas_feitas.contains(id));
            Pergunta pergunta = db.getPerguntacomAlternativa(id, 1,dificuldade);
            id_perguntas_feitas.add(pergunta.getId());
            
            //Exibição das perguntas e respostas
            System.out.println(pergunta.getPergunta());
            Alternativa correta = null;
            for(Alternativa i:pergunta.getAlternativas()){
                if(i.isCorreta()) correta = i;
                System.out.println(i);
            }
            System.out.println("Digite o número da questão.");
            int resposta = scanner.nextInt();
            Alternativa alternativa_escolhida = pergunta.getAlternativas().get(resposta);

            //Tramento do Acerto
            if(alternativa_escolhida == correta){
                pontuacao++;
                System.out.println("Resposta Correta\n");
                checkpoint(true);
            }
            
            //Tratamento do Erro
            else{
                pontuacao--;
                System.out.println("Resposta Incorreta\n"); 
                checkpoint(false);
            }

            System.out.println("Pontuação: "+pontuacao);

            //Finalização do jogo
            if(pontuacao >= 10){
                System.out.println("Parabéns, você venceu.");
                jogo_rodando = false;
            }
        }
        scanner.close();        
    }

    
    private void checkpoint(boolean acertou){
        final int[] checkPoints = {0,2,5,7};
        
        if(acertou){
            for(int i:checkPoints){
                if(i == pontuacao){
                    checkpoint = i;
                    System.out.println("Checkpoint Salvo em "+i);
                }
            }
        }
        else{
            pontuacao = checkpoint;
            System.out.println("Voltando para o checkpoint "+checkpoint);
        }
    }

    
    private void atualiza_perguntas_disponiveis(PerguntasDAO db) {
        id_perguntas_disponiveis = db.ids_disponiveis(dificuldade, 1);
    }

    private void atualiza_dificuldade(){
        
    }
}