package structures;

public class Cell2 {
	private double fScore = 0;
	private double gScore = 0;
	private double hScore = 0;
	private Coordinate coordinates;
	private char type; //0,1,2,a,b
	
	private Cell2 Parent;
	
	public Cell2(int x, int y, char type){
		coordinates = new Coordinate(x,y);
		this.type = type;
	}
	public void setfScore(double score){
		fScore = score;
	}
	public void setgScore(double score){
		gScore = score;
	}
	public void sethScore(double score){
		hScore = score;
	}
	
	public double getfScore(){
		return fScore;
	}
	public double getgScore(){
		return gScore;
	}
	public double gethScore(){
		return hScore;
	}
	public Coordinate getCoords(){
		return coordinates;
	}
	public char getType(){
		return type;
	}
	
	public Cell2 getParent(){
		return Parent;
	}
	public void setParent(Cell2 parent){
		this.Parent = parent;
}
	@Override
	public String toString(){
		return "Cell2 Coordinates: " + coordinates.getX() + ", " + coordinates.getY() + "\nType: " + type + "\nfScore: " + fScore + "\ngScore: " + gScore + "\nhScore: " + hScore;
	}
}