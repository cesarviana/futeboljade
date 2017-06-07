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
		jogadorGrafico.setX(0);
		jogadorGrafico.setY(0);
		campo.addJogador(jogadorGrafico);
	}

	@Override
	public void bolaEmJogo() {
	}

}
