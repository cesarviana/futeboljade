package grafico;

import java.awt.Color;

import jogo.CampoAgentesListener;
import lombok.AllArgsConstructor;
import view.Campo;

@AllArgsConstructor
public class OuvinteAgentes implements CampoAgentesListener {

	private Campo campo;

	@Override
	public void jogadorIndoNaDirecaoDaBolaBemLoko(jogo.Jogador jogador) {
	}

	@Override
	public void jogadorAdicionado(jogo.Jogador jogador) {
		System.out.println("Adicionado");
		grafico.Jogador jogadorGrafico = new grafico.Jogador(jogador.getNome(), Color.RED);
		jogadorGrafico.setX((float) (Math.random()*campo.getWidth())-campo.getWidth()/2);
		jogadorGrafico.setY((float) (Math.random()*campo.getHeight())-campo.getHeight()/2);
		campo.addJogador(jogadorGrafico);
	}

	@Override
	public void bolaEmJogo() {
	}

}