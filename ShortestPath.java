import structures.VertexNode;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;

public class ShortestPath {
	public static ArrayList<VertexNode> openList = new ArrayList<VertexNode>(); //List of unexpanded vertices
	public static ArrayList<VertexNode> closedList = new ArrayList<VertexNode>(); //List of expanded vertices
	public static ArrayList<VertexNode> noPaths = new ArrayList<VertexNode>(); //List of vertices that lead to deadends/edge
	public static ArrayList<VertexNode> shortestPath = new ArrayList<VertexNode>(); //Final list of VertexNodes for shortest path

	public int[][] ShortestPath(char[][] map, VertexNode start, VertexNode end){
		
		double g = 0, h = 0, f =0; //g = path cost, h = heuristic, f = g+h
		VertexNode current; //Vertex being expanded
		closedList.add(start); //adding start vertex to expanded list
		
		//master loop checking and expanding vertices until the goal vertex is found
		do{
			//setting which vertex to be expanded
			if(closedList.size() > 0)
				current = closedList.get(closedList.size() - 1);
			else
				current = closedList.get(closedList.size());
			
			//finding all the vertices around the current vertex
			VertexNode ul = getUL(current, map);
			VertexNode uu = getUU(current, map);
			VertexNode ur = getUR(current, map);
			VertexNode ll = getLL(current, map);
			VertexNode rr = getRR(current, map);
			VertexNode dl = getDL(current, map);
			VertexNode dd = getDD(current, map);
			VertexNode dr = getDR(current, map);
			
			//makes sure that these vertices are not an edge, not blocked, not already expanded, and does not lead to dead end
			if(ul != null && map[ul.getx()][ul.gety()] != '0' && !closedList.contains(ul) && !noPaths.contains(ul)){
				openList.add(ul);
				//finding heuristic value, which is the Euclidean distance from vertex to end
				h = Math.sqrt(Math.pow((double)end.getx() - (double)ul.getx(),2) + Math.pow((double)end.gety() - (double)ul.gety(),2));
				//assigning g value for vertex to move in to
				switch(ul.getVertexType()){
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
				switch(current.getVertexType()){
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
				g = ul.getg() + g; //adding g value to previous g value
				ul.setg(g); //assign/reassigning g values to vertex
				f = h + g; 
				ul.setf(f); 
			}
			//repeat for each of the vertices around current vertex
			if(uu != null && map[uu.getx()][uu.gety()] != '0' && !closedList.contains(uu) && !noPaths.contains(uu)){
				openList.add(uu);
				h = Math.sqrt(Math.pow((double)end.getx() - (double)uu.getx(),2) + Math.pow((double)end.gety() - (double)uu.gety(),2));
				switch(uu.getVertexType()){
					case '1':
						g = 1;
					case '2':
						g = 2;
					case 'a':
						g = 0.25;
					case 'b':
						g = 0.5;
				}
				switch(current.getVertexType()){
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
				g = uu.getg() + g;
				uu.setg(g);
				f = h + g;
				uu.setf(f);
			}
			if(ur != null && map[ur.getx()][ur.gety()] != '0' && !closedList.contains(ur) && !noPaths.contains(ur)){
				openList.add(ur);
				h = Math.sqrt(Math.pow((double)end.getx() - (double)ur.getx(),2) + Math.pow((double)end.gety() - (double)ur.gety(),2));
				switch(ur.getVertexType()){
					case '1':
						g = Math.sqrt(2);
					case '2':
						g = 2*(Math.sqrt(2));
					case 'a':
						g = (Math.sqrt(2))/4;
					case 'b':
						g = (Math.sqrt(2))/2;
				}
				switch(current.getVertexType()){
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
				g = ur.getg() + g;
				ur.setg(g);
				f = h + g;
				ur.setf(f);
			}
			if(ll != null && map[ll.getx()][ll.gety()] != '0' && !closedList.contains(ll) && !noPaths.contains(ll)){
				openList.add(ll);
				h = Math.sqrt(Math.pow((double)end.getx() - (double)ll.getx(),2) + Math.pow((double)end.gety() - (double)ll.gety(),2));
				switch(ll.getVertexType()){
					case '1':
						g = 1;
					case '2':
						g = 2;
					case 'a':
						g = 0.25;
					case 'b':
						g = 0.5;
				}
				switch(current.getVertexType()){
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
				g = ll.getg() + g;
				ll.setg(g);
				f = h + g;
				ll.setf(f);
			}
			if(rr != null && map[rr.getx()][rr.gety()] != '0' && !closedList.contains(rr) && !noPaths.contains(rr)){
				openList.add(rr);
				h = Math.sqrt(Math.pow((double)end.getx() - (double)rr.getx(),2) + Math.pow((double)end.gety() - (double)rr.gety(),2));
				switch(rr.getVertexType()){
					case '1':
						g = 1;
					case '2':
						g = 2;
					case 'a':
						g = 0.25;
					case 'b':
						g = 0.5;
				}
				switch(current.getVertexType()){
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
				g = rr.getg() + g;
				rr.setg(g);
				f = h + g;
				rr.setf(f);
			}
			if(dl != null && map[dl.getx()][dl.gety()] != '0' && !closedList.contains(dl) && !noPaths.contains(dl)){
				openList.add(dl);
				h = Math.sqrt(Math.pow((double)end.getx() - (double)dl.getx(),2) + Math.pow((double)end.gety() - (double)dl.gety(),2));
				switch(dl.getVertexType()){
					case '1':
						g = Math.sqrt(2);
					case '2':
						g = 2*(Math.sqrt(2));
					case 'a':
						g = (Math.sqrt(2))/4;
					case 'b':
						g = (Math.sqrt(2))/2;
				}
				switch(current.getVertexType()){
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
				g = dl.getg() + g;
				dl.setg(g);
				f = h + g;
				dl.setf(f);
			}
			if(dd != null && map[dd.getx()][dd.gety()] != '0' && !closedList.contains(dd) && !noPaths.contains(dd)){
				openList.add(dd);
				h = Math.sqrt(Math.pow((double)end.getx() - (double)dd.getx(),2) + Math.pow((double)end.gety() - (double)dd.gety(),2));
				switch(dd.getVertexType()){
					case '1':
						g = 1;
					case '2':
						g = 2;
					case 'a':
						g = 0.25;
					case 'b':
						g = 0.5;
				}
				switch(current.getVertexType()){
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
				g = dd.getg() + g;
				dd.setg(g);
				f = h + g;
				dd.setf(f);
			}
			if(dr != null && map[dr.getx()][dr.gety()] != '0' && !closedList.contains(dr) && !noPaths.contains(dr)){
				openList.add(dr);
				h = Math.sqrt(Math.pow((double)end.getx() - (double)dr.getx(),2) + Math.pow((double)end.gety() - (double)dr.gety(),2));
				switch(dr.getVertexType()){
					case '1':
						g = Math.sqrt(2);
					case '2':
						g = 2*(Math.sqrt(2));
					case 'a':
						g = (Math.sqrt(2))/4;
					case 'b':
						g = (Math.sqrt(2))/2;
				}
				switch(current.getVertexType()){
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
				g = dr.getg() + g;
				dr.setg(g);
				f = h + g;
				dr.setf(f);
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
			VertexNode Low = openList.get(0);
			double lowF = openList.get(0).getf();
			int lowIndex = 0;
			
			for(int i = 0; i<openList.size(); i++){
				if(openList.get(i).getf() < lowF){
					Low = openList.get(i);
					lowF = openList.get(i).getf();
					lowIndex = i;
				}
			}
			closedList.add(Low);
			openList.remove(lowIndex);
			
		}while(closedList.get(closedList.size()-1).getx() != end.getx() || closedList.get(closedList.size()-1).gety() != end.gety());
		
		//trace parents from end node to start node
		VertexNode tracer = closedList.get(closedList.size()-1);
		ArrayList<VertexNode> shortestPathInvert = new ArrayList<VertexNode>();
		
		while(tracer.getx() != start.getx() || tracer.gety() != start.gety()){
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
			bestPathCoord[k][0] = shortestPath.get(k).getx();
			bestPathCoord[k][1] = shortestPath.get(k).gety();
		}
		
		return bestPathCoord;
		
	}
	//creating vertices surrounding current vertex (ul = upper left, uu = up, ur = upper right, rr = right, dl = down left, etc.)
	public VertexNode getUL(VertexNode current, char map[][]){
		int xNew = current.getx()-1;
		int yNew =current.gety() +1;
		
		//make sure the vertex is not on the edge, if it is return null
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		
		VertexNode ul = new VertexNode(xNew,yNew, map[xNew][yNew]);
		
		//the parent is set to the current vertex
		ul.setParent(current);
		return ul;
		
	}
	
	public VertexNode getUU(VertexNode current, char map[][]){
		int xNew = current.getx()-1;
		int yNew =current.gety() +1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		VertexNode uu = new VertexNode(xNew,yNew, map[xNew][yNew]);
		uu.setParent(current);
		return uu;
		
	}
	public VertexNode getUR(VertexNode current, char map[][]){
		int xNew = current.getx()-1;
		int yNew =current.gety() +1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		VertexNode ur = new VertexNode(xNew,yNew, map[xNew][yNew]);
		ur.setParent(current);
		return ur;
		
	}
	public VertexNode getLL(VertexNode current, char map[][]){
		int xNew = current.getx()-1;
		int yNew =current.gety() +1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		VertexNode ll = new VertexNode(xNew,yNew, map[xNew][yNew]);
		ll.setParent(current);
		return ll;
		
	}
	public VertexNode getRR(VertexNode current, char map[][]){
		int xNew = current.getx()-1;
		int yNew =current.gety() +1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		VertexNode rr = new VertexNode(xNew,yNew, map[xNew][yNew]);
		rr.setParent(current);
		return rr;
		
	}
	public VertexNode getDL(VertexNode current, char map[][]){
		int xNew = current.getx()-1;
		int yNew =current.gety() +1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		VertexNode dl = new VertexNode(xNew,yNew, map[xNew][yNew]);
		dl.setParent(current);
		return dl;
		
	}
	public VertexNode getDD(VertexNode current, char map[][]){
		int xNew = current.getx()-1;
		int yNew =current.gety() +1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		VertexNode dd = new VertexNode(xNew,yNew, map[xNew][yNew]);
		dd.setParent(current);
		return dd;
		
	}
	public VertexNode getDR(VertexNode current, char map[][]){
		int xNew = current.getx()-1;
		int yNew =current.gety() +1;
		if(xNew<0 ||yNew<0 ||yNew>= map.length || xNew>=map[0].length)
			return null;
		VertexNode dr = new VertexNode(xNew,yNew, map[xNew][yNew]);
		dr.setParent(current);
		return dr;
		
	}	

}
