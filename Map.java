package project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
	private String name;
	private char[][] charArr;
	private Cell[][] cellArr;
	private Coordinate[] start = new Coordinate[10];
	private Coordinate[] end = new Coordinate[10];
	private Coordinate[] search = new Coordinate[10];
	private Coordinate[] hardCenters;
	
	public Map(String fileName) throws FileNotFoundException {
		File[] file = new File[10];
		for(int i = 0; i < 10; i++){
			if(fileName.contains(".txt"))
				file[i] = new File(fileName);
			
			else
				file[i] = new File(fileName + "_" + i + ".txt");
		}
		//File file = new File(fileName);
		String[] fName = fileName.split("\\\\");
		name = fName[fName.length -1];
		
		Scanner[] sc = new Scanner[10];
		for(int i = 0; i < 10; i++){
			sc[i] = new Scanner(file[i]);
		}
		for(int i = 0; i < 10; i++){
			start[i] = new Coordinate(sc[i].nextInt(), sc[i].nextInt());
			end[i] = new Coordinate(sc[i].nextInt(), sc[i].nextInt());
			search[i] = new Coordinate(0,0);
			//System.out.println(i + ". Start: " + start[i].toString());
			//System.out.println(i + ". End: " + end[i].toString());
		}
		//Scanner sc = new Scanner(file);
		
		this.charArr = new char[160][120];
		this.hardCenters = new Coordinate[8];
		this.cellArr = new Cell[160][120];
		
		//start = new Coordinate(sc.nextInt(),sc.nextInt()); //retrieves coordinates for start vertex
		//end = new Coordinate(sc.nextInt(),sc.nextInt()); //retrieves coordinates for end vertex
		
		//retrieves coordinates for hard to traverse region centers
		for(int i = 0; i < 8; i++){
			hardCenters[i] = 
					new Coordinate(sc[0].nextInt(),sc[0].nextInt());
			//System.out.println(i + ". " + hardCenters[i].toString());
		}
		
		//retrieves the rest of the map
		for(int j = 0; j < 120; j++){
			String row = sc[0].next();
			for(int i = 0; i < 160; i++){
				this.charArr[i][j] = 
						row.charAt(i);
				this.cellArr[i][j] = new Cell(i,j, row.charAt(i));
			}
		}	
	}
	
	//set functions
	public void setName(String name){
		this.name=name;
	}
	public void setStart(Coordinate start, int number){
		this.start[number] = start;
	}
	public void setEnd(Coordinate end, int number){
		this.end[number] = end;
	}
	public void setSearch(Coordinate search, int number){
		this.search[number] = search;
	}
	
	//get functions
	public Cell[][] getCellMap(){
		return cellArr;
	}
	public Cell getCell(int x, int y){
		if(x < 0 || y < 0 || x >= cellArr.length || y >= cellArr.length)
			return null;
		return cellArr[x][y];
	}
	public Cell getCell(Coordinate coordinate){
		return cellArr[coordinate.getX()][coordinate.getY()];
	}
	public String getName(){
		return name;
	}
	public char[][] getCharMap(){
		return charArr;
	}
	public Cell getCellStart(int number){
		return cellArr[start[number].getX()][start[number].getY()];
	}
	public Cell getCellEnd(int number){
		return cellArr[end[number].getX()][end[number].getY()];
	}
	public Coordinate getStart(int number){
		return start[number];
	}
	public Coordinate getEnd(int number){
		return end[number];
	}
	public Coordinate getSearch(int number) {
		return search[number];
	}
	public Coordinate[] getHardCenters(){
		return hardCenters;
	}

	
	
	
}
