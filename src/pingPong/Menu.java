package pingPong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import pingPong.Game.Grafico;

public class Menu {
	
    Font big = new Font("Digiface", Font.BOLD, 40);
    Grafico gr;
	
	public String[] options = {"1 Player","2 Player"};
	
	public int currentOption = 0;
	public int maxOption = options.length-1;
	
	public  boolean up,down,enter;
	
	public void logic() {
		if(up) {
			up = false;
			currentOption--;
			if(currentOption < 0)
				currentOption = maxOption;
			System.out.println("Estou funfando 1: " + currentOption);
		}
		if(down) {
			down = false;
			currentOption++;
			if(currentOption > maxOption)
				currentOption = 0;
			System.out.println("Estou funfando 2: " + currentOption);
		}
		if(enter) {
			enter = false;
			if(options[currentOption] == "1 Player") {
				Game.oneTwo = false;
				Game.gStatus = "play";

			}
			if(options[currentOption] == "2 Player") {
				Game.oneTwo = true;
				Game.gStatus = "play";
			}
		}
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.white);

        Graphics2D op = (Graphics2D) g;
        op.setFont(big);

		Graphics2D op2 = (Graphics2D) g;
		op2.setFont(big);


		op.drawString("Pong" ,Game. widthM/2-50,70);

        op.drawString("1 Player " ,Game. widthM/2-80,Game.hightM/2-20);
        
        op2.drawString("2 Player " ,Game. widthM/2-80,Game.hightM/2+50-20);

		if(options[currentOption] == "1 Player"){
			op.setColor(Color.BLUE);
			op.drawString("1 Player " ,Game. widthM/2-80,Game.hightM/2-20);
		}
		else if(options[currentOption] == "2 Player"){
			op2.setColor(Color.BLUE);
			op2.drawString("2 Player " ,Game. widthM/2-80,Game.hightM/2+50-20);

		}

		
	}
}
