package br.ufms.facom.lpoo.rpg.personagem;

import br.ufms.facom.lpoo.rpg.arma.Arma;
import br.ufms.facom.lpoo.rpg.arma.PhaserGun;

public class Mist implements Personagem {

	private Arma arma;

	private int vida;
	
	private String nome;

	private Posicao posicao;

	public Mist(String nome) {
		this.nome = nome;
		arma = new PhaserGun();
		vida = 5;
		posicao = new Posicao();
	}

	public Mist(String nome, int x, int y) {
		this(nome);
		posicao = new Posicao(x, y);
	}

	@Override
	public int getDefesa() {
		return 4;
	}

	@Override
	public int getAtaque() {
		return 3;
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
}
