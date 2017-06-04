package grafico;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Jogador extends ObjetoJogo {
	
	public static final Color COR_CASA = new Color(255, 51, 51);
	public static final Color COR_VISITANTE = new Color(0, 170, 255);
	public static final int TAMANHO_JOGADOR = 30;
	
	private Color color;
	private String nome;
	
	public Jogador() {
		setW(TAMANHO_JOGADOR);
		setH(TAMANHO_JOGADOR);
	}
	
	public Jogador(String nome, Color color){
		this();
		this.nome = nome;
		this.color = color;
	}
	
	@Override
	public void desenha() {
		Graphics2D g2 = getCampo().g;
		g2.setColor(getColor());
		g2.fill(new Ellipse2D.Double(getX(), getY(), getW(), getH()));
	}

}
