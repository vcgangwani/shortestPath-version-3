import structures.Coordinate;
import structures.Cell2;
import structures.Cell;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;

public class ShortestPath {
	private static ArrayList<Cell2> openList = new ArrayList<Cell2>(); //List of unexpanded vertices
	private static ArrayList<Cell2> closedList = new ArrayList<Cell2>(); //List of expanded vertices
	private static ArrayList<Cell2> shortestPath = new ArrayList<Cell2>(); //Final list of VertexNodes for shortest path
	private static Cell2 CellMap[][];
	private static double weight;
	private static int hType;
	private static Cell2 start;
	private static Cell2 end;
	
	public ShortestPath(char[][] map, Cell2 startCell, Cell2 endCell, double w, int heur){
		CellMap = new Cell2[map.length][map[0].length];
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				CellMap[i][j] = new Cell2(i,j,map[i][j]);
			}
		}
		
		start = startCell;
		end = endCell;
		weight = w;
		hType = heur;
	}
	
	public int[][] getShortestPath(){
		int Path[][] = null;
		addToOpenList(start);
		Cell2 current = openList.get(0);
		
		
		while(!isEnd(current)){
			openToClosed();
			expandCell();
			current = closedList.get(closedList.size()-1);
		}
		
		Cell2 current2 = current;
		
		while(!isStart(current2)){
			shortestPath.add(current2);
			current2 = current2.getParent();
		}
		shortestPath.add(start);
		Path = new int[shortestPath.size()][2];
		for(int i = 0; i < shortestPath.size(); i++){
			Path[i][0] = shortestPath.get(shortestPath.size()-1 - i).getCoords().getX();
			Path[i][1] = shortestPath.get(shortestPath.size()-1 - i).getCoords().getY();
		}
		
		return Path;
	}
	
	public static void addToOpenList(Cell2 newCell){
		openList.add(newCell);
		int index = openList.size() - 1;
		if(index !=0){
			while((openList.get(index).getfScore() < openList.get((index-1)/2).getfScore())){
				switchOpenListCells(index, (index-1)/2);
				index = (index-1)/2;	
			}
		}
	}
	
	
	
	
	
	
	public void addOpenList(Cell2 c){
		openList.add(c);
	}

	
	public static void getOpenList(){
		for(int i = 0; i<openList.size(); i++)
			System.out.print(": " + openList.get(i).getfScore() + " ");
		System.out.println();
	}
	
	
	public static void getClosedList(){
		for(int i = 0; i<closedList.size(); i++)
			System.out.print(": " + closedList.get(i).getfScore() + " ");
		System.out.println();
	}
	
	
	
	
	public static void openToClosed(){
		closedList.add(openList.get(0));
		
		if(openList.size() > 0){
		switchOpenListCells(0, openList.size() - 1);
		openList.remove(openList.size()-1);
		
		int index1 = 0;
		int leftIndex = 2*index1 + 1;
		int rightIndex = 2*index1 + 2;
		int index2 = 0;
		
		while(leftIndex<(openList.size()-1) || rightIndex<(openList.size()-1)){
			if(leftIndex<(openList.size()-1)){
				if(openList.get(index1).getfScore() > openList.get(leftIndex).getfScore()){
					index2 = leftIndex;
				}
			}
			if(rightIndex<(openList.size()-1)){
				if(openList.get(index2).getfScore() > openList.get(rightIndex).getfScore()){
					index2 = rightIndex;
				}
			}
			if(index2 == index1)
				return;
			else{
				switchOpenListCells(index1,index2);
				index1 = index2;
				leftIndex = 2*index1 + 1;
				rightIndex = 2*index1 + 2;
			}
		}
		}
		
	}
	
	
	public static void switchOpenListCells(int index1, int index2){
		Cell2 temp = openList.get(index2);
		openList.set(index2, openList.get(index1));
		openList.set(index1, temp);
	}
	
	
	public static void expandCell(){
		int x = closedList.get(closedList.size()-1).getCoords().getX();
		int y = closedList.get(closedList.size()-1).getCoords().getY();
		closedList.get(closedList.size()-1).setInClosedList(true);
		
		outerloop:
		for(int i = x-1; i <= x+1; i++){
			for(int j = y-1; j <= y+1; j++){
				boolean isStraight;
				if(x==i ||  y==j)
					isStraight = true;
				else
					isStraight = false;
				if(i>0 && i<(CellMap.length-1) && j>0 && j<(CellMap[0].length- 1)){
					if(CellMap[i][j].getType() !='0' && !CellMap[i][j].isInClosedList()){
						CellMap[i][j].setParent(CellMap[x][y]);
						calculateF(CellMap[i][j],isStraight);
						addToOpenList(CellMap[i][j]);
						if(isEnd(CellMap[i][j])){
							closedList.add(CellMap[i][j]);
							break outerloop;
						}
					}
				}
			}
		}
		
	}
	
	
	public static void calculateG(Cell2 current, boolean S){
		double g = current.getParent().getgScore();
		switch(current.getParent().getType()){
			case '1':
				g = g + 0.5;
				break;
			case '2':
				g = g + 1;
				break;
			case 'a':
				g = g + 0.125;
				break;
			case 'b':
				g = g + 0.25;
				break;
		}
		switch(current.getType()){
			case '1':
				g = g + 0.5;
				break;
			case '2':
				g = g + 1;
				break;
			case 'a':
				g = g + 0.125;
				break;
			case 'b':
				g = g + 0.25;
				break;
		}
		if(!S)
			g=Math.sqrt(2)*g;
		current.setgScore(g);
		
	}
	
	
	public static void calculateH(Cell2 current){
		double h = 0;
		switch(hType){
			case 0:
				h = hEucl(current);
				break;
			case 1:
				h = hManhattan(current);
				break;
			case 2:
				h = hEuclHard(current);
				break;
			case 3:
				h = hEuclRiver(current);
				break;
			case 4:
				h = hManhattanRiver(current);
				break;
		}
		h = weight*h;
		current.sethScore(h);
	}
	public static double hEucl(Cell2 current){
		double h = Math.sqrt(Math.pow((double)current.getCoords().getX() - (double)end.getCoords().getX(), 2) + Math.pow((double)current.getCoords().getY() - (double)end.getCoords().getY(), 2));
		return h;
	}
	public static double hManhattan(Cell2 current){
		double h = Math.abs((double)current.getCoords().getX() - end.getCoords().getX()) + Math.abs((double)current.getCoords().getY() - end.getCoords().getY());
		return h;
	}
	public static double hEuclHard(Cell2 current){
		double h = 2.0*Math.sqrt(Math.pow((double)current.getCoords().getX() - (double)end.getCoords().getX(), 2) + Math.pow((double)current.getCoords().getY() - (double)end.getCoords().getY(), 2));
		return h;
	}
	public static double hEuclRiver(Cell2 current){
		double h = 0.25*Math.sqrt(Math.pow((double)current.getCoords().getX() - (double)end.getCoords().getX(), 2) + Math.pow((double)current.getCoords().getY() - (double)end.getCoords().getY(), 2));
		return h;
	}
	public static double hManhattanRiver(Cell2 current){
		double h = 0.25*Math.abs((double)current.getCoords().getX() - end.getCoords().getX()) + Math.abs((double)current.getCoords().getY() - end.getCoords().getY());
		return h;
	}
	

	public static void calculateF(Cell2 current, boolean S){
		calculateG(current, S);
		calculateH(current);
		double f = current.getgScore()+current.gethScore();
		current.setfScore(f);
	}
	
	
	public static boolean isEnd(Cell2 current){
		if((current.getCoords().getX() == end.getCoords().getX()) && (current.getCoords().getY() == end.getCoords().getY()))
			return true;
		else
			return false;
	}
	public static boolean isStart(Cell2 current){
		if((current.getCoords().getX() == start.getCoords().getX()) && (current.getCoords().getY() == start.getCoords().getY()))
			return true;
		else
			return false;
	}
}
