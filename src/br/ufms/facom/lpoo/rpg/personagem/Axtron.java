package br.ufms.facom.lpoo.rpg.personagem;
//NANOBOTS
import br.ufms.facom.lpoo.rpg.arma.Arma;
import br.ufms.facom.lpoo.rpg.arma.CosmicSword;

public class Axtron implements Personagem {

	private Arma arma;

	private int vida;
	
	private String nome;

	private Posicao posicao;
	
	private boolean nanobot;

	public Axtron(String nome) {
		this.nome = nome;
		arma = new CosmicSword();
		vida = 5;
		posicao = new Posicao();
		nanobot = true;
	}

	public Axtron(String nome, int x, int y) {
		this(nome);
		posicao = new Posicao(x, y);
	}

	@Override
	public int getDefesa() {
		return 3;
	}

	@Override
	public int getAtaque() {
		return 3;
	}

	@Override
	public int getVelocidade() {
		return 3;
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
