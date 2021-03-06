package grafico;

import jogo.CampoAgentesListener;
import jogo.MovimentoBola;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OuvinteAgentes implements CampoAgentesListener {

	private view.Campo campo;

	@Override
	public void jogadorIndoNaDirecaoDaBolaBemLoko(jogo.Jogador jogador) {
		campo.jogadorSeguirBola(jogador.getNome());
	}

	@Override
	public void jogadorAdicionado(String nomeJogador, String time) {		
		campo.addJogador(nomeJogador, time);
	}
	@Override
	public void goleiroAdicionado(String nomeGoleiro, String time) {
		campo.addGoleiro(nomeGoleiro, time);
	}

	@Override
	public void informaEstado(String nome, String estado) {
		campo.getJogador(nome).setInfo(estado);
	}

	@Override
	public void debuga(String nome, String mensagem) {
		campo.getJogador(nome).setInfoDebug(mensagem);
	}

	@Override
	public void bolaEmJogo(boolean bolaEmJogo) {
		campo.bolaEmJogo(bolaEmJogo);
	}

	@Override
	public void jogadorPegouBola(String nome) {
		campo.setJogadorComBola(campo.getJogador(nome));
	}
	
	@Override
	public void jogadorDeveAtacar(String nome) {
		campo.getJogador(nome).atacar();
	}

	@Override
	public void jogadorDeveDefender(String nome) {
		campo.getJogador(nome).defender();
	}

	@Override
	public void jogadorDeveChutar(String nome, MovimentoBola movimentoBola) {
		campo.jogadorComBolaChutarGol(movimentoBola);
	}

	@Override
	public void jogadorDevePassar(String passador, String recebedor) {
		campo.getJogador(passador).passarPara(recebedor);
	}

	@Override
	public void informaPosicaoCampo(String nome, String posicao) {
		campo.informaPosicaoCampo(nome, posicao);
	}

	@Override
	public void bolaFoiProCentro() {
		// bola foi pro centro
	}

	@Override
	public void jogadorDeveIrParaOCentroComABola(String nome) {
		campo.jogadorDeveIrParaOCentroComABola(nome);
	}
}
