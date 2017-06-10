package jogo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import lombok.Getter;
import lombok.SneakyThrows;

public class Campo {

	private final AgentContainer mainContainer;
	private Map<String,AgentController> jogadores;
	private Set<CampoAgentesListener> listeners;
	@Getter
	private boolean bolaEmJogo;

	public Campo() {
		Runtime jade = Runtime.instance();
		jade.setCloseVM(true);
		Profile profile = new ProfileImpl("127.0.0.1", 1999, "linux");
		mainContainer = jade.createMainContainer(profile);
		jogadores = new HashMap<>();
		listeners = new HashSet<>();
	}

	public void setBolaEmJogo(boolean bolaEmJogo) {
		this.bolaEmJogo = bolaEmJogo;
		listeners.forEach(CampoAgentesListener::bolaEmJogo);
	}

	public void adicionaJogador(String nome) {
		adicionaJogador(nome, "Sem Time");
	}

	public void adicionaJogador(String nome, String time) {
		Object[] args = new Object[] { nome, time, this };
		try {
			AgentController controller = mainContainer.createNewAgent(nome, Jogador.class.getName(), args);
			controller.start();
			jogadores.put(nome, controller);
		} catch (StaleProxyException e) {
			throw new RuntimeException(e);
		}
		listeners.forEach(listener -> listener.jogadorAdicionado(nome));
	}

	public void addListener(CampoAgentesListener jogoListener) {
		this.listeners.add(jogoListener);
	}

	public void mostraJogadorCorrendoAtrasDaBolaIgualUmTanso(Jogador jogador) {
		this.listeners.forEach(listener -> {
			listener.jogadorIndoNaDirecaoDaBolaBemLoko(jogador);
		});
	}

	@SneakyThrows
	public void jogadorColidiuComBola(String nome) {
		jogadores.get(nome).putO2AObject("colidiu_com_bola", false);
	}

	public Set<String> getJogadores() {
		return jogadores.keySet();
	}

}
