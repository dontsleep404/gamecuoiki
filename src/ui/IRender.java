package ui;

import java.awt.Graphics2D;

public interface IRender {
	public void update(float delta);
	public void render(Graphics2D g2d);
}
