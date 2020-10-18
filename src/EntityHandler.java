/**
 * author (Max Ole Kleffmann ~ null)
 * version 2.0
 * date (11th of January 2019)
*/

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class EntityHandler extends JPanel {
    public Sketch sketch = Sketch.sketch;

    public int x, y, wallSpeedX, wallSpeedY, width, height;
    public static final int RAD = 60;
    public float vx, vy;
    public int score, total;
    public boolean deathStatus = false, gameStatus = true;
    public ArrayList<Point> walls = new ArrayList<Point>();
    public ArrayList<Integer> wallsPoints = new ArrayList<Integer>();

    public String getPathJar = null;

    Random random = new Random();

    public EntityHandler() {
        width = 600;
        height = 600;

        wallSpeedY = 0;
        wallSpeedX = 1;

        x = 80;
        y = ((height/2)+160);

        getPathJar = new String(new File(".").getAbsolutePath());

        wallsPoints.add(random.nextInt((height)/2));
        wallsPoints.add(random.nextInt((height)/2));
        wallsPoints.add(random.nextInt((height)/2));
        wallsPoints.add(random.nextInt((height)/2));
        wallsPoints.add(random.nextInt((height)/2));
        wallsPoints.add(random.nextInt((height)/2));


        walls.add(new Point(200, 0));
        walls.add(new Point(200,  height-wallsPoints.get(0)));

        walls.add(new Point(500, 0));
        walls.add(new Point(500, height-wallsPoints.get(1)));

        walls.add(new Point(800, 0));
        walls.add(new Point(800, height-wallsPoints.get(2)));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        initGame(g);

        if (gameStatus) {
            x += vx;
            y += vy;
            vy+=0.5f;

            for(Point item : walls){
                item.x -=5;
            }

            paintHeadSnake(g);
            showPipe(g);
            initScore(g);
        }
    }

    private void initGame(Graphics g) { //snake_background.jpg.png
        try {
            BufferedImage image = ImageIO.read(new File(getPathJar + "//Images//snake_background.jpg.png"));
            g.drawImage(image, 0, 40, width, height, null);
            g.setColor(new Color(87, 138, 52));
            g.fillRect(0,0, width, 40);
        } catch (Exception e) {
            e.printStackTrace();
        }
        g.setColor(new Color(170, 215, 81));
        g.fillRect(0, 0, width, height);
    }
    private void showPipe(Graphics g){
        try{
            BufferedImage image = ImageIO.read(new File(getPathJar + "//Images//78px-Pipe.png"));
            for(int i=0;i<walls.size();i++){
                g.setColor(Color.BLACK);
                if(i==2){
                    g.drawImage(image ,walls.get(i).x, walls.get(i).y, 70, wallsPoints.get(0), null);
                }else if(i==3){
                    g.drawImage(image, walls.get(i).x, walls.get(i).y, 70, wallsPoints.get(1), null);
                }else if(i==5){
                    g.drawImage(image,walls.get(i).x, walls.get(i).y, 70, wallsPoints.get(2), null);
                }else if(i==1){
                    g.drawImage(image, walls.get(i).x, walls.get(i).y, 70, wallsPoints.get(3), null);
                }else if(i==4){
                    g.drawImage(image, walls.get(i).x, walls.get(i).y, 70, wallsPoints.get(4), null);
                }else if(i==0){
                    g.drawImage(image, walls.get(i).x, walls.get(i).y, 70, wallsPoints.get(5), null);
                }
            }
        }catch (Exception e){

        }
    }
    public void initScore(Graphics g){
        Point point = new Point(width/2-20, height/2-80);
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        g.drawString(Integer.toString(score), point.x, point.y);
    }
    public void collision(){
        for(int i=0;i<walls.size();i++){
            if(walls.get(i).x < -80){
                if(i==0){
                    wallsPoints.set(5, random.nextInt(height/2));
                    walls.set(i, new Point(900, 0));
                }else if(i==1){
                    wallsPoints.set(3, random.nextInt(height/2));
                    walls.set(i, new Point(900, height-wallsPoints.get(3)));
                }
                else if(i==2){
                    wallsPoints.set(0, random.nextInt(height/2));
                    walls.set(i, new Point(900, 0));
                }
                else if(i==3){
                    wallsPoints.set(1, random.nextInt(height/2));
                    walls.set(i, new Point(900, height-wallsPoints.get(1)));
                }
                else if(i==4){
                    wallsPoints.set(4, random.nextInt(height/2));
                    walls.set(i, new Point(900, 0));
                }else if(i==5){
                    wallsPoints.set(2, random.nextInt(height/2));
                    walls.set(i, new Point(900, height-wallsPoints.get(2)));
                }
            }
        }
        for(int i=0;i<walls.size();i++){
            if(Math.round(x-RAD) > walls.get(i).x && Math.round(x-RAD) < walls.get(i).x + 70){
                if(Math.round(y-RAD) > walls.get(i).y && Math.round(y-RAD) < walls.get(i).y + + wallsPoints.get(i)){
                    score=0;
                }
            }
        }
        for(Point item : walls){
            if(Math.round(x-RAD) + 10 == item.x){
                score++;
            }
        }
    }
    private void paintHeadSnake(Graphics g) {
        try {
            BufferedImage image = ImageIO.read(new File(getPathJar + "//Images//sticker,375x360.u2.png"));
            g.drawImage(image, Math.round(x-RAD),  Math.round(y-RAD), 50, 50, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void birdJump(){
        vy = -8;
    }
}