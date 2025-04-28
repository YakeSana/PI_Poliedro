import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Jogo{
    private static int pontuacao;
    private static int checkpoint;
    private static boolean jogo_rodando = true;
    private List<Integer> id_perguntas_feitas = new ArrayList<>();
    
    Scanner scanner = new Scanner(System.in);
    Jogo(){
        while (jogo_rodando) {
            int id;
            Random random = new Random();

            //Geração de id da pergunta
            do{
                id = random.nextInt(50);
            }
            while(id_perguntas_feitas.contains(id));
            Pergunta pergunta = new Pergunta(id);
            id_perguntas_feitas.add(pergunta.getId());
            
            //Exibição das perguntas e respostas
            System.out.println(pergunta.getPergunta());
            for(int i=0;i<5;i++){
                System.out.println("Questão "+i+": "+ "Resposta "+pergunta.getRespostas().get(i));
            }
            System.out.println("Digite o número da questão.");
            int resposta = scanner.nextInt();

            //Tramento do Acerto
            if(resposta == 0){
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
}