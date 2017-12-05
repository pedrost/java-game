package br.ufms.facom.lpoo.rpg.controle;

import br.ufms.facom.lpoo.rpg.personagem.Personagem;
import br.ufms.facom.lpoo.rpg.personagem.Posicao;
import br.ufms.facom.lpoo.rpg.personagem.Axtron;
import br.ufms.facom.lpoo.rpg.personagem.Shroud;
import br.ufms.facom.lpoo.rpg.ui.RolePlayingGame;

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
	 * Um personagem.
	 */
	private Axtron axtron;

	/**
	 * Outro personagem.
	 */
	private Shroud shroud;

	/**
	 * Cria um objeto de controle que usa o objeto <code>rpg</code> como
	 * interface com o usuário.
	 * 
	 * @param rpg
	 *            interface gráfica da aplicação.
	 */
	public Controle(RolePlayingGame rpg) {
		this.rpg = rpg;

		// Cria um personagem em um canto do tabuleiro e outro em outro canto.
		axtron = new Axtron("Axtron", 1, 1);
		shroud = new Shroud("Shroud", 5, 5);

		// Adiciona os dois personagens ao tabuleiro.
		rpg.addPersonagem(axtron);
		rpg.addPersonagem(shroud);
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
		executaTurnoIndividual(axtron);

		/*
		 * Abaixo, as mesmas operações realizadas com o personagem 1 são
		 * realizadas com o personagem 2.
		 */

		executaTurnoIndividual(shroud);
	}
	
	public void executaTurnoIndividual(Personagem personagem) throws InterruptedException {
		rpg.info(String.format("Personagem %s, selecione sua nova posição!", personagem.getNome()));
		
		Posicao pos = rpg.selecionaPosicao();
		
		int distanciaShroud = rpg.validarDistancia(pos.x, pos.y, personagem.getX(), personagem.getY());
		rpg.erro("DISTANCIA DO " + personagem.getNome() + " " + distanciaShroud);
		while(distanciaShroud > personagem.getVelocidade()) {
			rpg.erro("MUITO LONGE SELECIONA DE NOVO");
			pos = rpg.selecionaPosicao();
			distanciaShroud = rpg.validarDistancia(pos.x, pos.y, personagem.getX(), personagem.getY());
		}
		
		personagem.setX(pos.x);
		personagem.setY(pos.y);

		rpg.atualizaTabuleiro();

		rpg.info(String.format("Personagem %s, selecione um inimigo para atacar!", personagem.getNome()));
		Personagem p = rpg.selecionaPersonagem();
		if(!rpg.isNanoBot(p)) {
			rpg.erro("Você não pode atacar um aliado! Perdeu a vez.");
		}
		if (rpg.testeAtaque(p.getDefesa(), personagem.getAtaque()) ) {
			p.setVida(p.getVida() - 1);
		}

		rpg.atualizaTabuleiro();
	}
}
