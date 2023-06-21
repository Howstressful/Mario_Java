import java.awt.*;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Coin extends Sprite{

static BufferedImage image = null;
Model model;
int pushSpeed;
int gravity;

    public Coin(int x1, int y1, Model m){
        x = x1;
        y = y1;
        w = 50;
        h = 50;
        if(image == null){
            this.image = View.loadImage("coin1.png");
        }
        model = m;
        gravity = -20;
        Random rand = new Random();
        pushSpeed = rand.nextInt(20)+1;
    }


    @Override
    boolean update(){
        gravity += 1.2;
        y += gravity;
        x += pushSpeed;

        if(y > 579 +100)
        return false;

        return true;
    }

    @Override
    void draw(Graphics g){
        g.drawImage(image,x - model.mario.x + model.mario.start, y, w, h, null);;
    }



}
