package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.arma.Arma;
import br.ufms.facom.lpoo.rpg.arma.EletricStick;

public class Nemesis implements Personagem {

	private Arma arma;

	private int vida;
	
	private String nome;

	private Posicao posicao;
	
	private boolean nanobot;

	public Nemesis(String nome) {
		this.nome = nome;
		arma = new EletricStick();
		vida = 5;
		posicao = new Posicao();
		nanobot = true;
	}

	public Nemesis(String nome, int x, int y) {
		this(nome);
		posicao = new Posicao(x, y);
	}

	@Override
	public int getDefesa() {
		return 2;
	}

	@Override
	public int getAtaque() {
		return 5;
	}

	@Override
	public int getVelocidade() {
		return 2;
	}

	@Override
	public int getVida() {
		return vida;
	}

	@Override
	public Arma getArma() {
		return arma;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public int getX() {
		return posicao.x;
	}

	@Override
	public int getY() {
		return posicao.y;
	}

	@Override
	public void setX(int x) {
		posicao.x = x;
	}

	@Override
	public void setY(int y) {
		posicao.y = y;
	}

	@Override
	public void setVida(int vida) {
		this.vida = vida;
	}
	
	@Override
	public boolean isNanoBot() {
		return nanobot;
	}
}
