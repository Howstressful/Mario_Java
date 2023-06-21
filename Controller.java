import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements MouseListener, KeyListener
{

	View view;
	Model model;
	Brick b;
	boolean edit = false;
	int x1,x2,y1,y2;
	int xShort,yShort;

	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean spaceBar;

	void setView(View v)
		{
			view = v;

		}
	Controller(Model m)
	{
		model = m;
	}

	public void mousePressed(MouseEvent e){
if(edit){
		x1 = e.getX() + model.mario.x - model.mario.start;
		y1 = e.getY();}
	}
	public void mouseReleased(MouseEvent e) {
if(edit) {
	xShort = e.getX() + model.mario.x - model.mario.start;
	yShort = e.getY();
	x2 = xShort - x1;
	y2 = yShort - y1;
	if (yShort < y1 || xShort < x1) {
		System.out.println("wrong direction try moving the other way bro");
	} else {
		b = new Brick(x1, y1, x2, y2,model);
		model.sprites.add(b);
	}
}
	}



	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {
		if(e.getY() < 100){ System.out.println("break here");}
	}



	public void keyPressed(KeyEvent e)
	{

		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true;
			break;
			case KeyEvent.VK_LEFT: keyLeft = true;
			break;
			case KeyEvent.VK_UP: keyUp = true; break;

			case KeyEvent.VK_SPACE: spaceBar = true; break;

			case KeyEvent.VK_DOWN: keyDown = true; break;
		}

}
	public void keyReleased(KeyEvent e)
	{

		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; model.mario.count = 1;break;
			case KeyEvent.VK_LEFT: keyLeft = false; model.mario.count = 1; break;
			case KeyEvent.VK_UP: keyUp = false; break;

			case KeyEvent.VK_SPACE: spaceBar = false;model.mario.count = 9; model.mario.grounded = false;
			break;

			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_ESCAPE: System.out.println("Later...");
			System.exit(0); break;

		}
		char c = e.getKeyChar();
		if(c == 's'|| c == 'S'){
			model.marshal().save("map.json");
		}
		if(c == 'e'|| c == 'E'){
			edit = !edit;
			System.out.println("Edit mode: " + edit);
		}
		if(c == 'l' || c == 'L'){
		Json j = Json.load("map.json");
		model.unmarshal(j);
		j.save("temp.json");
		System.out.println("nice");

		}
	}

	public void keyTyped(KeyEvent e)
	{}
	void update(){
		model.mario.preY = model.mario.y;
		model.mario.preX = model.mario.x;

		if(keyRight){
			model.mario.x +=10;
			model.backPos -=1;
		    model.mario.updatePosition();
		    model.mario.direction = 1;

		}
		if(keyLeft) {
			model.mario.x -=10;
			model.backPos +=1;
			model.mario.updatePosition();
			model.mario.direction = -1;

		}
		if(spaceBar && model.mario.grounded == true) {
				model.mario.y -= 40;
				model.mario.updateJump();
				System.out.println(model.mario.jump);




		}
	}

}
