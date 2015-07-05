package br.com.projetodevoa.ITCreativity.Shapes.Shapes3D;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import br.com.projetodevoa.ITCreativity.Shapes.Shape3D;

public class DrawRect3D extends Shape3D {

	//Atributo que ser� o objeto do tipo Rectangle2D. Default: null
	private Rectangle2D rectangle;
	private int pivoX;
	private int pivoY;
	
	public DrawRect3D(Rectangle2D rectangle){
		this.rectangle = rectangle;
		this.pivoX = (int) rectangle.getX();
		this.pivoY = (int) rectangle.getY();
	}
	
	public DrawRect3D(int x, int y, int width, int height){
		this.pivoX = x;
		this.pivoY = y;	 
		rectangle = new Rectangle2D.Double(pivoX, pivoY, width,height);
		rectangle.setFrame(x, y, width, height);

	}
	
	public DrawRect3D(int pivoX, int pivoY){
		this(pivoX, pivoY, 1,1);
	}
	
	@Override
	public int getWidth() {
		return (int)rectangle.getWidth();
	}

	@Override
	public int getHeight() {
		return (int)rectangle.getHeight();
	}

 
//		public void setPivoX(int pivoX) {
//			this.pivoX = pivoX;
//		}
// 
//		public void setPivoY(int pivoY) {
//			this.pivoY = pivoY;
//		}

	public Rectangle2D getRectangle2D(){
		return rectangle;
	}
	
	public void setRectangle2D(Rectangle2D rectangle2D){
		this.rectangle = rectangle2D;
	}

	@Override
	public boolean containsShape(Point p) {
		if(rectangle.contains(p)) return true;
		
		return false;
	}
	@Override
	public boolean containsShape(int x, int y) {
		if(rectangle.contains(x,y)) return true;
		return false;
	}
 

	public String getShapeDescription() {
		return rectangle.toString();
	}
	
	@Override
	public double getMinX() {
		return rectangle.getMinX();
	}

	@Override
	public double getMinY() {
		return rectangle.getMinY();
	}

	@Override
	public double getMaxX() {
		return rectangle.getMaxX();
	}

	@Override
	public double getMaxY() {
		return rectangle.getMaxY();
	}

	@Override
	public int getX() {
		return this.pivoX;
	}

	@Override
	public int getY() {
		return this.pivoY;
	}

	@Override
	public void setColor(Color color) {
		super.color = color;		
	}
}
