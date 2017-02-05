package project1;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MapDisplay extends Applet{
	Map map;
	int mousex,mousey;
	public MapDisplay(){
		mousex=0;mousey=0;
	}
	public void setMap(Map map){
		this.map=map;
		repaint();
	}
	public Map getMap(){
		return map;
	}
	public Cell getCell(){
		//return cell where your mouse is
		int x = mousex / 8;
		int y = mousey / 8;
		return map.getCell(x,y);
	}
	public void paint(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 1280, 960);
		drawMap(g);
	}
	public void drawMap(Graphics g){
		//draw map here
		int markerx1 = 0, markery1 = 0;
		int markerx2 = 0, markery2 = 0;
		int markerx3 = 0, markery3 = 0;
		if(map==null)
			return;
		int mapNum = Integer.parseInt(map.getName().charAt(map.getName().length()-5) + "");
		System.out.println("MAPNUM: " + mapNum);
		System.out.println("START: " + map.getStart(mapNum).toString());
		System.out.println("END: " + map.getEnd(mapNum).toString());
		for (int x = 0; x < map.getCellMap().length; x++) {
			for (int y = 0; y < map.getCellMap()[x].length; y++) {
				Color temp = new Color(50, 50, 50);
				switch (map.getCellMap()[x][y].getType()) {
				case '0':
					temp = Color.black;
					break;
				case '1':
					temp = Color.WHITE;
					break;
				case '2':
					temp = Color.GRAY;
					break;
				case 'a':
					temp = new Color(30,144,255);
					break;
				case 'b':
					temp = Color.cyan;
					break;
				}
				if(map.getStart(mapNum).equals(new Coordinate(x,y))){
					System.out.println("start reached.");
					temp = Color.green;
					markerx1 = x;
					markery1 = y;
				}
				else if(map.getEnd(mapNum).equals(new Coordinate(x,y))){
					System.out.println("end reached.");
					temp = Color.red;
					markerx2 = x;
					markery2 = y;
				}
				else if(map.getSearch(mapNum).equals(new Coordinate(x,y))){
					temp = Color.pink;
					markerx3 = x;
					markery3 = y;
				}
				g.setColor(temp);
				g.fillRect(x*8, y*8,8, 8);
			}
		}
		g.setColor(new Color(0,255,0,100));
		g.fillOval(markerx1*8 - 21, markery1*8 - 21, 50, 50);
		g.setColor(new Color(255,0,0,100));
		g.fillOval(markerx2*8 - 21, markery2*8 - 21, 50, 50);
		g.setColor(new Color(255,0,255,100));
		g.fillOval(markerx3*8 - 21, markery3*8 - 21, 50, 50);
	}

	
}
