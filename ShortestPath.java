package project1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;

public class ShortestPath {
	public static ArrayList<Cell> openList = new ArrayList<Cell>(); //List of unexpanded vertices
	public static ArrayList<Cell> closedList = new ArrayList<Cell>(); //List of expanded vertices
	public static ArrayList<Cell> noPaths = new ArrayList<Cell>(); //List of vertices that lead to deadends/edge
	public static ArrayList<Cell> shortestPath = new ArrayList<Cell>(); //Final list of VertexNodes for shortest path

	public static int[][] ShortestPath(char[][] map, Cell start, Cell end, double weight, int heur){
		
		double g = 0, h = 0, f =0; //g = path cost, h = heuristic, f = g+h
		Cell current; //Vertex being expanded
		closedList.add(start); //adding start vertex to expanded list
		
		//master loop checking and expanding vertices until the goal vertex is found
		do{
			//setting which vertex to be expanded
			if(closedList.size() > 0)
				current = closedList.get(closedList.size() - 1);
			else
				current = closedList.get(closedList.size());
			
			//finding all the vertices around the current vertex
			Cell ul = getUL(current, map);
			Cell uu = getUU(current, map);
			Cell ur = getUR(current, map);
			Cell ll = getLL(current, map);
			Cell rr = getRR(current, map);
			Cell dl = getDL(current, map);
			Cell dd = getDD(current, map);
			Cell dr = getDR(current, map);
			
			//makes sure that these vertices are not an edge, not blocked, not already expanded, and does not lead to dead end
			if(ul != null && map[ul.getCoords().getX()][ul.getCoords().getY()] != '0' && !closedList.contains(ul) && !noPaths.contains(ul)){
				openList.add(ul);
				//finding heuristic value, which is the Euclidean distance from vertex to end
				h = Math.sqrt(Math.pow((double)end.getCoords().getX() - (double)ul.getCoords().getX(),2) + Math.pow((double)end.getCoords().getY() - (double)ul.getCoords().getY(),2));
				//assigning g value for vertex to move in to
				switch(ul.getType()){
					case '1':
						g = Math.sqrt(2);
					case '2':
						g = 2*(Math.sqrt(2));
					case 'a':
						g = (Math.sqrt(2))/4;
					case 'b':
						g = (Math.sqrt(2))/2;
				}
				//assigning g value for current vertex
				switch(current.getType()){
					case '1':
						g = Math.sqrt(2);
					case '2':
						g = 2*(Math.sqrt(2));
					case 'a':
						g = (Math.sqrt(2))/4;
					case 'b':
						g = (Math.sqrt(2))/2;
				}
				g = g/2; //each movement only moves through half of each vertex
				g = ul.getgScore() + g; //adding g value to previous g value
				ul.setgScore(g); //assign/reassigning g values to vertex
				f = h + g; 
				ul.setfScore(f); 
			}
			//repeat for each of the vertices around current vertex
			if(uu != null && map[uu.getCoords().getX()][uu.getCoords().getY()] != '0' && !closedList.contains(uu) && !noPaths.contains(uu)){
				openList.add(uu);
				h = Math.sqrt(Math.pow((double)end.getCoords().getX() - (double)uu.getCoords().getX(),2) + Math.pow((double)end.getCoords().getY() - (double)uu.getCoords().getY(),2));
				switch(uu.getType()){
					case '1':
						g = 1;
					case '2':
						g = 2;
					case 'a':
						g = 0.25;
					case 'b':
						g = 0.5;
				}
				switch(current.getType()){
					case '1':
						g = 1;
					case '2':
						g = 2;
					case 'a':
						g = 0.25;
					case 'b':
						g = 0.5;
				}
				g = g/2;
				g = uu.getgScore() + g;
				uu.setgScore(g);
				f = h + g;
				uu.setfScore(f);
			}
			if(ur != null && map[ur.getCoords().getX()][ur.getCoords().getY()] != '0' && !closedList.contains(ur) && !noPaths.contains(ur)){
				openList.add(ur);
				h = Math.sqrt(Math.pow((double)end.getCoords().getX() - (double)ur.getCoords().getX(),2) + Math.pow((double)end.getCoords().getY() - (double)ur.getCoords().getY(),2));
				switch(ur.getType()){
					case '1':
						g = Math.sqrt(2);
					case '2':
						g = 2*(Math.sqrt(2));
					case 'a':
						g = (Math.sqrt(2))/4;
					case 'b':
						g = (Math.sqrt(2))/2;
				}
				switch(current.getType()){
					case '1':
						g = Math.sqrt(2);
					case '2':
						g = 2*(Math.sqrt(2));
					case 'a':
						g = (Math.sqrt(2))/4;
					case 'b':
						g = (Math.sqrt(2))/2;
				}
				g = g/2;
				g = ur.getgScore() + g;
				ur.setgScore(g);
				f = h + g;
				ur.setfScore(f);
			}
			if(ll != null && map[ll.getCoords().getX()][ll.getCoords().getY()] != '0' && !closedList.contains(ll) && !noPaths.contains(ll)){
				openList.add(ll);
				h = Math.sqrt(Math.pow((double)end.getCoords().getX() - (double)ll.getCoords().getX(),2) + Math.pow((double)end.getCoords().getY() - (double)ll.getCoords().getY(),2));
				switch(ll.getType()){
					case '1':
						g = 1;
					case '2':
						g = 2;
					case 'a':
						g = 0.25;
					case 'b':
						g = 0.5;
				}
				switch(current.getType()){
					case '1':
						g = 1;
					case '2':
						g = 2;
					case 'a':
						g = 0.25;
					case 'b':
						g = 0.5;
				}
				g = g/2;
				g = ll.getgScore() + g;
				ll.setgScore(g);
				f = h + g;
				ll.setfScore(f);
			}
			if(rr != null && map[rr.getCoords().getX()][rr.getCoords().getY()] != '0' && !closedList.contains(rr) && !noPaths.contains(rr)){
				openList.add(rr);
				h = Math.sqrt(Math.pow((double)end.getCoords().getX() - (double)rr.getCoords().getX(),2) + Math.pow((double)end.getCoords().getY() - (double)rr.getCoords().getY(),2));
				switch(rr.getType()){
					case '1':
						g = 1;
					case '2':
						g = 2;
					case 'a':
						g = 0.25;
					case 'b':
						g = 0.5;
				}
				switch(current.getType()){
					case '1':
						g = 1;
					case '2':
						g = 2;
					case 'a':
						g = 0.25;
					case 'b':
						g = 0.5;
				}
				g = g/2;
				g = rr.getgScore() + g;
				rr.setgScore(g);
				f = h + g;
				rr.setfScore(f);
			}
			if(dl != null && map[dl.getCoords().getX()][dl.getCoords().getX()] != '0' && !closedList.contains(dl) && !noPaths.contains(dl)){
				openList.add(dl);
				h = Math.sqrt(Math.pow((double)end.getCoords().getX() - (double)dl.getCoords().getX(),2) + Math.pow((double)end.getCoords().getY() - (double)dl.getCoords().getY(),2));
				switch(dl.getType()){
					case '1':
						g = Math.sqrt(2);
					case '2':
						g = 2*(Math.sqrt(2));
					case 'a':
						g = (Math.sqrt(2))/4;
					case 'b':
						g = (Math.sqrt(2))/2;
				}
				switch(current.getType()){
					case '1':
						g = Math.sqrt(2);
					case '2':
						g = 2*(Math.sqrt(2));
					case 'a':
						g = (Math.sqrt(2))/4;
					case 'b':
						g = (Math.sqrt(2))/2;
				}
				g = g/2;
				g = dl.getgScore() + g;
				dl.setgScore(g);
				f = h + g;
				dl.setfScore(f);
			}
			if(dd != null && map[dd.getCoords().getX()][dd.getCoords().getY()] != '0' && !closedList.contains(dd) && !noPaths.contains(dd)){
				openList.add(dd);
				h = Math.sqrt(Math.pow((double)end.getCoords().getX() - (double)dd.getCoords().getX(),2) + Math.pow((double)end.getCoords().getY() - (double)dd.getCoords().getY(),2));
				switch(dd.getType()){
					case '1':
						g = 1;
					case '2':
						g = 2;
					case 'a':
						g = 0.25;
					case 'b':
						g = 0.5;
				}
				switch(current.getType()){
					case '1':
						g = 1;
					case '2':
						g = 2;
					case 'a':
						g = 0.25;
					case 'b':
						g = 0.5;
				}
				g = g/2;
				g = dd.getgScore() + g;
				dd.setgScore(g);
				f = h + g;
				dd.setfScore(f);
			}
			if(dr != null && map[dr.getCoords().getX()][dr.getCoords().getY()] != '0' && !closedList.contains(dr) && !noPaths.contains(dr)){
				openList.add(dr);
				h = Math.sqrt(Math.pow((double)end.getCoords().getX() - (double)dr.getCoords().getX(),2) + Math.pow((double)end.getCoords().getY() - (double)dr.getCoords().getY(),2));
				switch(dr.getType()){
					case '1':
						g = Math.sqrt(2);
					case '2':
						g = 2*(Math.sqrt(2));
					case 'a':
						g = (Math.sqrt(2))/4;
					case 'b':
						g = (Math.sqrt(2))/2;
				}
				switch(current.getType()){
					case '1':
						g = Math.sqrt(2);
					case '2':
						g = 2*(Math.sqrt(2));
					case 'a':
						g = (Math.sqrt(2))/4;
					case 'b':
						g = (Math.sqrt(2))/2;
				}
				g = g/2;
				g = dr.getgScore() + g;
				dr.setgScore(g);
				f = h + g;
				dr.setfScore(f);
			}
			//if at dead end then add vertex to noPaths list
			if (openList.size() == 0){
				noPaths.add(current);
				if(closedList.size()>1){
					current = closedList.get(closedList.size() - 2);
				}
				else {
					current = closedList.get(closedList.size() - 1);
				}
				closedList.remove(closedList.size() - 1);
			}
			
			//check for lowest f value in openList to move to closed list
			Cell Low = openList.get(0);
			double lowF = openList.get(0).getfScore();
			int lowIndex = 0;
			
			for(int i = 0; i<openList.size(); i++){
				if(openList.get(i).getfScore() < lowF){
					Low = openList.get(i);
					lowF = openList.get(i).getfScore();
					lowIndex = i;
				}
			}
			closedList.add(Low);
			openList.remove(lowIndex);
			
		}while(closedList.get(closedList.size()-1).getCoords().getX() != end.getCoords().getX() || closedList.get(closedList.size()-1).getCoords().getY() != end.getCoords().getY());
		
		//trace parents from end node to start node
		Cell tracer = closedList.get(closedList.size()-1);
		ArrayList<Cell> shortestPathInvert = new ArrayList<Cell>();
		
		while(tracer.getCoords().getX() != start.getCoords().getX() || tracer.getCoords().getY() != start.getCoords().getY()){
			shortestPathInvert.add(tracer);
			tracer = tracer.getParent();
		}
		
		//change list to start to end instead of end to start
		for(int j = shortestPathInvert.size(); j > 0; j--){
			shortestPath.add(shortestPathInvert.get(j));
		}
		
		//create 2D array of x and y coord to return
		int[][] bestPathCoord = new int[shortestPath.size()][2];
		for(int k = 0; k<shortestPath.size(); k++){
			bestPathCoord[k][0] = shortestPath.get(k).getCoords().getX();
			bestPathCoord[k][1] = shortestPath.get(k).getCoords().getY();
		}
		
		return bestPathCoord;
		
	}
	//creating vertices surrounding current vertex (ul = upper left, uu = up, ur = upper right, rr = right, dl = down left, etc.)
	public static Cell getUL(Cell current, char map[][]){
		int xNew = current.getCoords().getX()-1;
		int yNew = current.getCoords().getY() +1;
		
		//make sure the vertex is not on the edge, if it is return null
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		
		Cell ul = new Cell(xNew,yNew, map[xNew][yNew]);
		
		//the parent is set to the current vertex
		ul.setParent(current);
		return ul;
		
	}
	
	public static Cell getUU(Cell current, char map[][]){
		int xNew = current.getCoords().getX()-1;
		int yNew =current.getCoords().getY() +1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		Cell uu = new Cell(xNew,yNew, map[xNew][yNew]);
		uu.setParent(current);
		return uu;
		
	}
	public static Cell getUR(Cell current, char map[][]){
		int xNew = current.getCoords().getX()-1;
		int yNew = current.getCoords().getY() +1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		Cell ur = new Cell(xNew,yNew, map[xNew][yNew]);
		ur.setParent(current);
		return ur;
		
	}
	public static Cell getLL(Cell current, char map[][]){
		int xNew = current.getCoords().getX()-1;
		int yNew = current.getCoords().getY() +1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		Cell ll = new Cell(xNew,yNew, map[xNew][yNew]);
		ll.setParent(current);
		return ll;
		
	}
	public static Cell getRR(Cell current, char map[][]){
		int xNew = current.getCoords().getX()-1;
		int yNew = current.getCoords().getY() +1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		Cell rr = new Cell(xNew,yNew, map[xNew][yNew]);
		rr.setParent(current);
		return rr;
		
	}
	public static Cell getDL(Cell current, char map[][]){
		int xNew = current.getCoords().getX()-1;
		int yNew = current.getCoords().getX() +1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		Cell dl = new Cell(xNew,yNew, map[xNew][yNew]);
		dl.setParent(current);
		return dl;
		
	}
	public static Cell getDD(Cell current, char map[][]){
		int xNew = current.getCoords().getX()-1;
		int yNew = current.getCoords().getY() +1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		Cell dd = new Cell(xNew,yNew, map[xNew][yNew]);
		dd.setParent(current);
		return dd;
		
	}
	public static Cell getDR(Cell current, char map[][]){
		int xNew = current.getCoords().getX()-1;
		int yNew = current.getCoords().getY() +1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		Cell dr = new Cell(xNew,yNew, map[xNew][yNew]);
		dr.setParent(current);
		return dr;
		
	}	

}
