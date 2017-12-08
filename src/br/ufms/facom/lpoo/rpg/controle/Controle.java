package br.ufms.facom.lpoo.rpg.controle;

import br.ufms.facom.lpoo.rpg.personagem.Personagem;
import br.ufms.facom.lpoo.rpg.personagem.Posicao;
import br.ufms.facom.lpoo.rpg.personagem.Axtron;
import br.ufms.facom.lpoo.rpg.personagem.Shroud;
import br.ufms.facom.lpoo.rpg.personagem.Echo;
import br.ufms.facom.lpoo.rpg.personagem.Nemesis;
import br.ufms.facom.lpoo.rpg.personagem.Diros;
import br.ufms.facom.lpoo.rpg.personagem.Mist;
import br.ufms.facom.lpoo.rpg.ui.RolePlayingGame;
import java.util.Random;

/**
 * Controle do jogo: personagens e suas interações.
 * <p>
 * O intuito desta implementação é apenas exemplificar o uso dos principais
 * métodos da classe de interface: <code>RolePlayingGame</code>. A implementação
 * apresentada aqui não condiz com os requisitos do trabalho (apenas um tipo de
 * personagem (<code>Soldado</code>) e um tipo de arma (<code>Faca</code>) são
 * implementados aqui). Apenas dois personagens (do mesmo tipo) são adicionados
 * ao tabuleiro e a interação entre eles não respeita as regras do trabalho.
 * 
 * @author eraldo
 *
 */
public class Controle {

	/**
	 * Interface gráfica do jogo. Fornece métodos de entrada e saída.
	 */
	private RolePlayingGame rpg;

	/**
	 * NanoBots.
	 */
	private Axtron axtron;
        private Echo echo;
        private Nemesis nemesis;

	/**
	 * Anarquistas.
	 */
	private Shroud shroud;
        private Diros diros;
        private Mist mist;

	/**
	 * Cria um objeto de controle que usa o objeto <code>rpg</code> como
	 * interface com o usuário.
	 * 
	 * @param rpg
	 *            interface gráfica da aplicação.
	 */
	public Controle(RolePlayingGame rpg) {
            Random gerador = new Random();
            this.rpg = rpg;
            int[] num = new int[]{0, 1, 2, 5, 6 , 7};
            int aX = num[gerador.nextInt(3)];
            int aY = num[gerador.nextInt(2)+1];
            int sX = num[gerador.nextInt(3)+3];
            int sY = num[gerador.nextInt(2)+3];
	// Cria um personagem em um canto do tabuleiro e outro em outro canto.
            axtron = new Axtron("Axtron", aX, aY);
            shroud = new Shroud("Shroud", sX, sY);
            echo = new Echo("Echo", 0, 0);
            mist = new Mist("Mist", 7, 7);
            nemesis = new Nemesis("Nemesis", 3, 2);
            diros = new Diros("Diros", 4, 5);

	// Adiciona os dois personagens ao tabuleiro.
            rpg.addPersonagem(axtron);
            rpg.addPersonagem(shroud);
            rpg.addPersonagem(echo);
            rpg.addPersonagem(mist);
            rpg.addPersonagem(nemesis);
            rpg.addPersonagem(diros);
	}

	/**
	 * Executa um turno do jogo. Este método é invocado pelo interface gráfica
	 * continuamente, enquanto a aplicação estiver rodando.
	 * <p>
	 * A implementação apresentada é apenas um exemplo que não condiz com os
	 * requisitos do trabalho. O turno implementado é muito simples. Cada
	 * jogador pode mover-se (sem restrições) e atacar o outro jogador. Nenhuma
	 * restrição é verificada com relação à velocidade do personagem, alcance
	 * das armas, pontos de vida, teste de habilidade, etc.
	 * 
	 * @throws InterruptedException
	 *             Exceção lançada quando a aplicação é encerrada pelo usuário.
	 *             O controle do jogo é executado em uma thread separada da
	 *             thread principal da aplicação. Esta exceção é lançada para
	 *             permitir o encerramento da thread de controle quando ela está
	 *             esperando uma resposta da interface com relação a uma ação do
	 *             usuário (selecionar personagem ou posição). O tratamento
	 *             desta exceção é realizado pela classe da aplicação
	 *             (<code>RolePlayingGame</code>). Esta exceção não deve ser
	 *             capturada aqui.
	 */
	public void executaTurno() throws InterruptedException {
		/*
		 * Exibe mensagem avisando que o usuário precisa selecionar a posição do
		 * personagem 1.
		 */
                 
		executaTurnoIndividual(shroud);
                executaTurnoIndividual(mist);
                executaTurnoIndividual(diros);

		/*
		 * Abaixo, as mesmas operações realizadas com o personagem 1 são
		 * realizadas com o personagem 2.
		 */
                
                //FALTA FAZER A IA PRA ESSES 3;
                executaTurnoIndividual(axtron);
                executaTurnoIndividual(echo);
                executaTurnoIndividual(nemesis);
                
	}
	
	public void executaTurnoIndividual(Personagem personagem) throws InterruptedException {
		if(personagem.getVida() <= 0) {
			return;
		}
		rpg.info(String.format("Personagem %s, selecione sua nova posição!", personagem.getNome()));
		
		Posicao pos = rpg.selecionaPosicao();
		
		int distanciaPersonagem = rpg.validarDistancia(pos.x, pos.y, personagem.getX(), personagem.getY());
		while(distanciaPersonagem > personagem.getVelocidade()) {
                        rpg.erro(personagem.getNome() + " selecionou distancia: " + distanciaPersonagem);
			rpg.erro("Muito longe, tente novamente.");
			pos = rpg.selecionaPosicao();
			distanciaPersonagem = rpg.validarDistancia(pos.x, pos.y, personagem.getX(), personagem.getY());
		}
		
		personagem.setX(pos.x);
		personagem.setY(pos.y);

		rpg.atualizaTabuleiro();

		rpg.info(String.format("Personagem %s, selecione um inimigo para atacar!", personagem.getNome()));
		Personagem p = rpg.selecionaPersonagem();
				
		if (rpg.validarAlcance(personagem, p) && rpg.validarAtaque(personagem, p) && rpg.testeAtaque(p.getDefesa(), personagem.getAtaque()) ) {
			p.setVida(p.getVida() - 1);
			if(p.getVida() <= 0) {
				rpg.removePersonagem(p);
			}
		}

		rpg.atualizaTabuleiro();
	}
        
        public void executaTurnoIA(Personagem personagem) throws InterruptedException{
            if(personagem.getVida() <= 0) {
			return;
		}
            
            rpg.info(String.format("Personagem %s, irá atacar!", personagem.getNome()));
            
        }
}
