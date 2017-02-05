import structures.Coordinate;
import structures.Cell2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;

public class ShortestPath {
	public static ArrayList<Cell2> openList = new ArrayList<Cell2>(); //List of unexpanded vertices
	public static ArrayList<Cell2> closedList = new ArrayList<Cell2>(); //List of expanded vertices
	public static ArrayList<Cell2> noPaths = new ArrayList<Cell2>(); //List of vertices that lead to dead ends/edge
	public static ArrayList<Cell2> shortestPath = new ArrayList<Cell2>(); //Final list of VertexNodes for shortest path

	public static int[][] ShortestPath(char[][] map, Cell2 start, Cell2 end, double weight, int heur){
		
		double g = 0, h = 0, f =0; //g = path cost, h = heuristic, f = g+h
		Cell2 current; //Vertex being expanded
		closedList.add(start); //adding start vertex to expanded list
		double w = weight;
		//master loop checking and expanding vertices until the goal vertex is found
		do{
			//setting which vertex to be expanded
			if(closedList.size() > 0)
				current = closedList.get(closedList.size() - 1);
			else
				current = closedList.get(closedList.size());
			
			//finding all the vertices around the current vertex
			Cell2 ul = getUL(current, map);
			Cell2 uu = getUU(current, map);
			Cell2 ur = getUR(current, map);
			Cell2 ll = getLL(current, map);
			Cell2 rr = getRR(current, map);
			Cell2 dl = getDL(current, map);
			Cell2 dd = getDD(current, map);
			Cell2 dr = getDR(current, map);
			for(int c = 0; c< closedList.size(); c++){
				System.out.println(closedList.get(c));
			}
			if(closedList.contains(uu))
				System.out.println(uu + "\nit does");
			
			//makes sure that these vertices are not an edge, not blocked, not already expanded, and does not lead to dead end
			if(ul != null && map[ul.getCoords().getX()][ul.getCoords().getY()] != '0' && !closedList.contains(ul) && !noPaths.contains(ul)){
				openList.add(ul);
				//finding heuristic value, which is the Euclidean distance from vertex to end
				h = h(ul.getCoords().getX(), ul.getCoords().getY(), end.getCoords().getX(), end.getCoords().getY(), heur);
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
				f = w*h + g; 
				ul.setfScore(f); 
			}
			//repeat for each of the vertices around current vertex
			if(uu != null && map[uu.getCoords().getX()][uu.getCoords().getY()] != '0' && !closedList.contains(uu) && !noPaths.contains(uu)){
				openList.add(uu);
				h = h(uu.getCoords().getX(), uu.getCoords().getY(), end.getCoords().getX(), end.getCoords().getY(), heur);				
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
				f = w*h + g; 
				uu.setfScore(f);
			}
			if(ur != null && map[ur.getCoords().getX()][ur.getCoords().getY()] != '0' && !closedList.contains(ur) && !noPaths.contains(ur)){
				openList.add(ur);
				h = h(ur.getCoords().getX(), ur.getCoords().getY(), end.getCoords().getX(), end.getCoords().getY(), heur);				
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
				f = w*h + g; 
				ur.setfScore(f);
			}
			if(ll != null && map[ll.getCoords().getX()][ll.getCoords().getY()] != '0' && !closedList.contains(ll) && !noPaths.contains(ll)){
				openList.add(ll);
				h = h(ll.getCoords().getX(), ll.getCoords().getY(), end.getCoords().getX(), end.getCoords().getY(), heur);				
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
				f = w*h + g; 
				ll.setfScore(f);
			}
			if(rr != null && map[rr.getCoords().getX()][rr.getCoords().getY()] != '0' && !closedList.contains(rr) && !noPaths.contains(rr)){
				openList.add(rr);
				h = h(rr.getCoords().getX(), rr.getCoords().getY(), end.getCoords().getX(), end.getCoords().getY(), heur);				
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
				f = w*h + g; 
				rr.setfScore(f);
			}
			if(dl != null && map[dl.getCoords().getX()][dl.getCoords().getX()] != '0' && !closedList.contains(dl) && !noPaths.contains(dl)){
				openList.add(dl);
				h = h(dl.getCoords().getX(), dl.getCoords().getY(), end.getCoords().getX(), end.getCoords().getY(), heur);				
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
				f = w*h + g; 
				dl.setfScore(f);
			}
			if(dd != null && map[dd.getCoords().getX()][dd.getCoords().getY()] != '0' && !closedList.contains(dd) && !noPaths.contains(dd)){
				openList.add(dd);
				h = h(dd.getCoords().getX(), dd.getCoords().getY(), end.getCoords().getX(), end.getCoords().getY(), heur);				
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
				f = w*h + g; 
				dd.setfScore(f);
			}
			if(dr != null && map[dr.getCoords().getX()][dr.getCoords().getY()] != '0' && !closedList.contains(dr) && !noPaths.contains(dr)){
				openList.add(dr);
				h = h(dr.getCoords().getX(), dr.getCoords().getY(), end.getCoords().getX(), end.getCoords().getY(), heur);				
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
				f = w*h + g; 
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
			Cell2 Low = openList.get(0);
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
		Cell2 tracer = closedList.get(closedList.size()-1);
		ArrayList<Cell2> shortestPathInvert = new ArrayList<Cell2>();
		
		while(tracer.getCoords().getX() != start.getCoords().getX() || tracer.getCoords().getY() != start.getCoords().getY()){
			shortestPathInvert.add(tracer);
			tracer = tracer.getParent();
		}
		
		//change list to start to end instead of end to start
		for(int j = shortestPathInvert.size(); j > 0; j--){
			shortestPath.add(
					shortestPathInvert.get(j-1));
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
	public static Cell2 getUL(Cell2 current, char map[][]){
		int xNew = current.getCoords().getX()-1;
		int yNew = current.getCoords().getY() -1;
		
		//make sure the vertex is not on the edge, if it is return null
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		
		Cell2 ul = new Cell2(xNew,yNew, map[xNew][yNew]);
		
		//the parent is set to the current vertex
		ul.setParent(current);
		return ul;
		
	}
	
	public static Cell2 getUU(Cell2 current, char map[][]){
		int xNew = current.getCoords().getX();
		int yNew =current.getCoords().getY()-1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		Cell2 uu = new Cell2(xNew,yNew, map[xNew][yNew]);
		uu.setParent(current);
		return uu;
		
	}
	public static Cell2 getUR(Cell2 current, char map[][]){
		int xNew = current.getCoords().getX()+1;
		int yNew = current.getCoords().getY()-1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		Cell2 ur = new Cell2(xNew,yNew, map[xNew][yNew]);
		ur.setParent(current);
		return ur;
		
	}
	public static Cell2 getLL(Cell2 current, char map[][]){
		int xNew = current.getCoords().getX()-1;
		int yNew = current.getCoords().getY();
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		Cell2 ll = new Cell2(xNew,yNew, map[xNew][yNew]);
		ll.setParent(current);
		return ll;
		
	}
	public static Cell2 getRR(Cell2 current, char map[][]){
		int xNew = current.getCoords().getX()+1;
		int yNew = current.getCoords().getY();
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		Cell2 rr = new Cell2(xNew,yNew, map[xNew][yNew]);
		rr.setParent(current);
		return rr;
		
	}
	public static Cell2 getDL(Cell2 current, char map[][]){
		int xNew = current.getCoords().getX()-1;
		int yNew = current.getCoords().getX() +1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		Cell2 dl = new Cell2(xNew,yNew, map[xNew][yNew]);
		dl.setParent(current);
		return dl;
		
	}
	public static Cell2 getDD(Cell2 current, char map[][]){
		int xNew = current.getCoords().getX();
		int yNew = current.getCoords().getY() +1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		Cell2 dd = new Cell2(xNew,yNew, map[xNew][yNew]);
		dd.setParent(current);
		return dd;
		
	}
	public static Cell2 getDR(Cell2 current, char map[][]){
		int xNew = current.getCoords().getX()+1;
		int yNew = current.getCoords().getY() +1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		Cell2 dr = new Cell2(xNew,yNew, map[xNew][yNew]);
		dr.setParent(current);
		return dr;
		
	}

	public static double h(int x, int y, int xEnd, int yEnd, int heur){
		double hReturn = 0;
		double xIn = (double)x;
		double yIn = (double)y;
		double xE = (double)xEnd;
		double yE = (double)yEnd;
		
		switch (heur){
			case 1:
				hReturn = Math.sqrt(Math.pow(xE - xIn,2) + Math.pow(yE - yIn,2));
			case 2: 
				hReturn = Math.abs(xE - xIn) + Math.abs(yE - yIn);
			case 3:
				hReturn = 2*(Math.sqrt(Math.pow(xE - xIn,2) + Math.pow(yE - yIn,2)));
			case 4:
				hReturn = 0.25* (Math.sqrt(Math.pow(xE - xIn,2) + Math.pow(yE - yIn,2)));

		 }
		return hReturn;
	}
	
}