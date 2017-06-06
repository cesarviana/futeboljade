package jogo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import lombok.Getter;

public class Campo {

	private final AgentContainer mainContainer;
	private List<Jogador> jogadores;
	@Getter
	private Point bola;
	private List<JogoListener> listeners;

	public Campo() {
		Runtime jade = Runtime.instance();
		jade.setCloseVM(true);
		Profile profile = new ProfileImpl("127.0.0.1", 1999, "linux");
		mainContainer = jade.createMainContainer(profile);
		jogadores = new ArrayList<>();
		listeners = new ArrayList<>();
	}

	public void bolaEmJogo() {
		bola = new Point(10, 10);
	}
	
	public boolean bolaEstaEmJogo(){
		return bola != null;
	}

	public void adicionaJogador(String nome) {
		adicionaJogador(nome, "Sem Time", new Point());
	}

	public void adicionaJogador(String nome, String time, Point posicao) {
		Object[] args = new Object[] { nome, time, posicao, this };
		jogadores.add(new Jogador(nome,posicao));
		try {
			AgentController controller = mainContainer.createNewAgent(nome, "jogo.agent.JogadorAgent", args);
			controller.start();
		} catch (StaleProxyException e) {
			throw new RuntimeException(e);
		}
	}

	public void addListener(JogoListenerAdapter jogoListener) {
		this.listeners.add(jogoListener);
	}

	public void mostraJogadorCorrendoAtrasDaBolaIgualUmTanso(Jogador jogador, Point bola) {
		this.listeners.forEach(listener->{
			listener.jogadorIndoNaDirecaoDaBolaBemLoko(jogador, bola);
		});
	}

}