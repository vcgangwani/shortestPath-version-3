package project1;

public class Coordinate {
	int x;
	int y;
	
	public Coordinate(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	
	public String toString(){
		return (x + " " + y);
	}
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		else if(obj == null)
			return false;
		else if(!(obj instanceof Coordinate))
			return false;
		else if(x == ((Coordinate) obj).getX() && y == ((Coordinate) obj).getY())
			return true;
		return false;
	}
	public int hashCode(){
		int hash = 1;
		hash = ((31*hash)+x);
		hash = ((31*hash)+y);
		return hash;
	}
}
