package pingPong;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import javax.sound.sampled.*;

import static java.lang.Thread.sleep;


public class Game extends JFrame implements ActionListener {

    Font big = new Font("Digiface", Font.BOLD, 40);
    Grafico gr;
    Timer timer;
    int bx = 50, by = 50, velx = 8, vely = 8;
    static int  pumy = 100, pdoisy = 100, pumScore = 0, pdoisScore = 0;
    

	static int hightM = Toolkit.getDefaultToolkit().getScreenSize().height-40;
	static int widthM = Toolkit.getDefaultToolkit().getScreenSize().width-40;
	
	public Menu menu;
	public Pause pause;
	
	public static String gStatus = "Menu";

    boolean dirumup, dirumdown,dirdoisup,dirdoisdown;
    
    public static boolean oneTwo = false;


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Jogar();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void Jogar() throws InterruptedException{
    	
    	if(gStatus == "play") {

	        bx += velx;
	        by += vely;
	
	        if(bx > widthM-90 && by > pdoisy - 20 && by < pdoisy+100){
	            velx *= -1;
	
	        }
	        if (bx <= 60 && bx >= 50 && by > pumy - 10 && by < pumy + 100){
	            velx *= -1;
	
	        }
	        if(by > hightM-115 || by <=20){
	            vely *= -1;
	        }
	        if(bx < -40){
	        	bx = widthM/2-20;
	            velx=-6;
	            ++pdoisScore;
	            sleep(200);
	        }
	        if (bx >widthM-20) {
	            bx = widthM/2+20;
	            velx=6;
	            ++pumScore;
	            sleep(200);
	        }
	        if(oneTwo == false) {
		        if(bx >  widthM/2){
		            if (by > pdoisy + 50 && pdoisy < hightM-200){
		                pdoisy += 8;
		            }
		            if (by < pdoisy && pdoisy > 40){
		                pdoisy -= 8;
		            }
		        }
	        }else if(oneTwo == true){
		        if(dirdoisup == true && pdoisy > 26){
		            pdoisy -= 8;
		        } else if (dirdoisdown == true && pdoisy < hightM-200) {
		            pdoisy +=8;
		        }
	        }
	
	
	        gr.repaint();
	
	
	        if(dirumup == true && pumy > 26){
	            pumy -= 8;
	        } else if (dirumdown == true && pumy < hightM-200) {
	            pumy +=8;
	        }
    	}else if(gStatus == "Menu") {
    		menu.logic();
    	}else if(gStatus == "Pause") {
			pause.logic();
		}
    }


    class Grafico extends JPanel{


        public void paintComponent(Graphics g){

            super.paintComponent(g);
            setBackground(Color.black);
            
        	if(gStatus == "Menu") {
        		menu.render(g);
        	}else if (gStatus == "play") {

	            Graphics2D bar0 = (Graphics2D) g;
	            Graphics2D bar1 = (Graphics2D) g;
	            Graphics2D bar2 = (Graphics2D) g;
	            Graphics2D playone = (Graphics2D) g;
	            Graphics2D playtwo = (Graphics2D) g;
	            Graphics2D bola = (Graphics2D) g;
	            Graphics2D scor = (Graphics2D) g;
	            Graphics2D vencedor = (Graphics2D) g;
	            vencedor.setFont(big);
	            scor.setFont(big);
	            bola.setColor(Color.white);
	
	            scor.drawString(pumScore + "           " + pdoisScore, widthM/2-90,70);
	            bar0.fill(new Rectangle2D.Double (40, 20, widthM-80, 5));
	            bar1.fill(new Rectangle2D.Double (40, hightM-99, widthM-80, 5));
	            bar2.fill(new Rectangle2D.Double (widthM/2-5, 20, 5, hightM-115));
	            
	            playone.fill(new Rectangle2D.Double (40, pumy, 20, 100));
	            playtwo.fill(new Rectangle2D.Double (widthM-80, pdoisy, 20, 100));
	
	            bola.fill(new Rectangle2D.Double (bx, by, 15, 15));
        	}else if(gStatus == "Pause"){
				pause.render(g);
			}
        }
    }
    public void Tela(){
    	
        setSize(widthM, hightM);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        gr = new Grafico();
        add(gr);
        timer = new Timer(2, this);
        timer.start();
        menu = new Menu();
		pause = new Pause();
    }
    public void control(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_W){
                	if(gStatus == "Menu") {
                		System.out.println("outro oi");
                		menu.up = true;
                	}else if (gStatus == "Pause") {
						pause.up = true;
					}
                    dirumup = true;
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                 	if(gStatus == "Menu") {
                		menu.down = true;
                		System.out.println("outro oi 2");
                	} else if (gStatus == "Pause") {
						pause.down = true;
					}
					dirumdown = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                	dirdoisup = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                	dirdoisdown = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {

                     	if(gStatus == "Menu") {
                     		menu.enter = true;
                     	}else if (gStatus == "Pause") {
							pause.enter = true;
						} else if (gStatus == "play") {
							gStatus = "Pause";
						}
				}


            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    dirumup = false;
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    dirumdown = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                	dirdoisup = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                	dirdoisdown = false;
                }

            }
        });
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.Tela();
        game.control();
    }
}
