package pingPong;

import java.awt.*;

public class Pause {
    Font big = new Font("Digiface", Font.BOLD, 40);
    Game.Grafico gr;

    public String[] options = {"Retomar","Menu"};

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
            if(options[currentOption] == "Retomar") {
                Game.gStatus = "play";
            }
            if(options[currentOption] == "Menu") {
                Game.pumScore = 0;
                Game.pdoisScore = 0;
                Game.pumy = 100;
                Game.pdoisy = 100;
                Game.gStatus = "Menu";
            }
        }
    }

    public void render(Graphics g) {

        g.setColor(Color.white);

        Graphics2D op = (Graphics2D) g;
        op.setFont(big);

        Graphics2D op2 = (Graphics2D) g;
        op2.setFont(big);

        Graphics2D ele = (Graphics2D) g;
        ele.setFont(big);

        Graphics2D ele2 = (Graphics2D) g;
        ele2.setFont(big);

        op.drawString("Retomar" ,Game. widthM/2,70);

        op2.drawString("Menu" ,Game. widthM/2,110);

        if(options[currentOption] == "Retomar"){
            op.setColor(Color.BLUE);
            op.drawString("Retomar" ,Game. widthM/2,70);
        }
        else if(options[currentOption] == "Menu"){
            op2.setColor(Color.BLUE);
            op2.drawString("Menu" ,Game. widthM/2,110);

        }


    }

}
