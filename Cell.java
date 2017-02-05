package project1;

public class Cell {
	private double fScore = 0;
	private double gScore = 0;
	private double hScore = 0;
	private Coordinate coordinates;
	private char type; //0,1,2,a,b
	
	private Cell Parent;
	
	public Cell(int x, int y, char type){
		coordinates = new Coordinate(x,y);
		this.type = type;
	}
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		else if(obj == null)
			return false;
		else if(!(obj instanceof Cell))
			return false;
		else if(coordinates == null){
			if(((Cell)obj).coordinates != null)
				return false;
		}
		return true;
	}
	public int hashcode(){
		int hash = 1;
		if(coordinates == null)
			hash = 31*hash;
		else
			hash = 31*hash + coordinates.hashCode();
		return hash;
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
	
	public Cell getParent(){
		return Parent;
	}
	public void setParent(Cell parent){
		this.Parent = parent;
}
	@Override
	public String toString(){
		return "Cell Coordinates: " + coordinates.getX() + ", " + coordinates.getY() + "\nType: " + type + "\nfScore: " + fScore + "\ngScore: " + gScore + "\nhScore: " + hScore;
	}
}
