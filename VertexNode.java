package structures;

public class VertexNode {
	private int x, y;
	
	private double f = 0;
	private double g = 0;
	private double h = 0;
	
	private char type;
	
	private VertexNode Parent;
	
	public VertexNode (int x, int y, char type){
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public int getx(){
		return this.x;
	}
	
	public int gety(){
		return this.y;
	}
	
	public void setf(double F){
		this.f = F;
	}
	public void setg(double G){
		this.g = G;
	}
	public void seth(double H){
		this.h = H;
	}
	
	public double getf(){
		return this.f;
	}
	public double getg(){
		return this.g;
	}
	public double geth(){
		return this.h;
	}
	
	public void setVertexType(char type){
		this.type = type;
	}
	public char getVertexType(){
		return type;
	}
	
	public VertexNode getParent(){
		return Parent;
	}
	public void setParent(VertexNode parent){
		this.Parent = parent;
	}
	
	public String toString() {
		String theString = Integer.toString(x) +"," +Integer.toString(y);
			return theString;
	}
}
