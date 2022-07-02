package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Key extends Panel{
	private boolean press;
	private String key;

	public Key(String key) {
		super();
		this.key = key;
		this.press = false;
	}

	public boolean isPress() {
		return press;
	}

	public void setPress(boolean press) {
		this.press = press;
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(Color.red);
		g2d.drawRect(0, 0, getWidth(), getHeight());
		if(isPress()) {
			g2d.setColor(Color.blue);
		}else {
			g2d.setColor(Color.white);
		}
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
