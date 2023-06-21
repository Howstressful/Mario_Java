import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Brick extends Sprite {


Model model;
boolean coolBrick = false;
int rand;

int numCoins = 5;

static BufferedImage image = null;
static BufferedImage coolImage = null;



    public Brick(int x1, int y1, int w1, int h1, Model m){
        x = x1;
        y = y1;
        w = w1;
        h = h1;
        loadImage();
        model = m;
        setRandom();
    }


void loadImage(){
        if(image == null)
        image = View.loadImage("brick.png");
        if(coolImage == null)
            coolImage = View.loadImage("block1.png");
}




     Json marshal()
     {
         Json ob = Json.newObject();
         ob.add("x", x);
         ob.add("y", y);
         ob.add("w", w);
         ob.add("h", h);
        return ob;
     }

      Brick(Json ob, Model m)
     {

         x = (int)ob.getLong("x");
         y = (int)ob.getLong("y");
         w = (int)ob.getLong("w");
         h = (int)ob.getLong("h");
       loadImage();
         model = m;
         setRandom();

     }

     @Override
     boolean update(){
        if(numCoins <= 0){
            coolBrick = false;
        }
        return true;
    }


    public void removeCoin(){
        if(numCoins > 0) {
            numCoins--;
        }
    }

     @Override
     boolean isBrick(){
        return true;
     }


     void setRandom() {
        rand = ThreadLocalRandom.current().nextInt();
        if(rand %4==0)
            coolBrick = true;
        else
        coolBrick = false;
    }

    @Override
    void draw(Graphics g){
        if(coolBrick)
            g.drawImage(coolImage,x-model.mario.x + model.mario.start,y,w,h,null);
        else
         g.drawImage(image,x-model.mario.x + model.mario.start,y,w,h,null);
     }

}
