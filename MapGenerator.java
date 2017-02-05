package project1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ThreadLocalRandom;

public class MapGenerator {
	
	public static Map generate(String fileName) throws IOException{
		Path[] file = new Path[10];
		for(int i = 0; i < 10; i++){
			file[i] = Paths.get(fileName + "_" + i + ".txt");
		}
		
		char[][] map = new char[120][160];
		for(int i = 0; i < 120; i ++){
			for(int j = 0; j < 160; j++){
				map[i][j] = '1';
			}
		}
		Coordinate[] hardCoords = new Coordinate[8];
		map = generateHard(map, hardCoords);
		
		map = generateRivers(map);
		
		map = generateImpassable(map);
		
		Coordinate[] start = new Coordinate[10];
		Coordinate[] end = new Coordinate[10];
		List<String> startAndEnd = new ArrayList<String>();
		for(int i = 0; i < 10; i++){
			start[i] = generateStart(map);
			end[i] = generateEnd(map, start[i]);
			startAndEnd.add(start[i].toString());
			startAndEnd.add(end[i].toString());
			System.out.println("Start: " + startAndEnd.get(0));
			System.out.println("End: " + startAndEnd.get(1));
			Files.write(file[i], startAndEnd);
			startAndEnd.clear();
		}
		
		List<String> linesToWrite = new ArrayList<String>();
		for (int i = 0; i < hardCoords.length; i++) {
			linesToWrite.add(hardCoords[i].toString());
		}

		for (int i = 0; i < map.length; i++) {
			String tmp = "";
			for (int j = 0; j < map[0].length; j++) {
				tmp += map[i][j];
			}
			linesToWrite.add(tmp);
		}
		
		for(int i = 0; i < 10; i++){
			Files.write(file[i], linesToWrite, StandardOpenOption.APPEND);
		}
		return new Map(fileName);
		
	}
	private static char[][] generateHard(char[][] map, Coordinate[] coords) {
		// TODO Auto-generated method stub
		int count = 0, x = 0, y = 0;
		boolean b;
		
		//creates the coordinates for the center of each hard to traverse region
		while(count < 8){ 
			b = true;
			x = (int)(Math.random() * 120);
			y = (int)(Math.random() * 160);
			System.out.println("region " + count + ": " + x + ", " + y);
			coords[count] = new Coordinate(x,y);
			
			for(int i = 0; i < count; i++){		//tests if the randomly chosen coordinate is not the same as a different coordinate
				if(coords[i].equals(coords[count])){
					b = false;
					break;
				}
			}
			
			if(b){
				count++;
			}
		}
		
		//generate partially blocked cells
		int xCoord, yCoord, xmin, ymin;
		for(int i = 0; i < 8; i++){
			xCoord = coords[i].getX();
			yCoord = coords[i].getY();
			
			if(xCoord - 15 < 0)
				xmin = 0;
			else
				xmin = xCoord - 15;
			
			if(yCoord - 15 < 0)
				ymin = 0;
			else
				ymin = yCoord - 15;
			
			for(int j = xmin; j < Math.min(xmin+30, 120); j++){
				for(int k = ymin; k < Math.min(ymin+30, 160); k++){
					if(map[j][k] == '1'){
						if((int)Math.random()*2 == 0){
							map[j][k] = '2';
						}
					}
				}
			}
		}
		
		
		return map;
	}
	private static char[][] generateRivers(char[][] map) {
		// TODO Auto-generated method stub
		int count = 0;
		char[][] mapcopy = new char[120][];
		for (int i = 0; i < map.length; i++) {
			mapcopy[i] = map[i].clone();
		}

		while (count < 4) {
			int restartCount = 0;
			char direction = '\0';
			int x = -1, y = -1;
			// boundaries - north: 0, south: 1, east: 2, west: 3
			// direction = n, s, e, w
			switch (ThreadLocalRandom.current().nextInt(0, 4)) {
			case 0:
				direction = 's';
				x = ThreadLocalRandom.current().nextInt(0, 120);
				y = 0;
				break;
			case 1:
				direction = 'n';
				x = ThreadLocalRandom.current().nextInt(0, 120);
				y = 159;
				break;
			case 2:
				direction = 'w';
				x = 119;
				y = ThreadLocalRandom.current().nextInt(0, 160);
				break;
			case 3:
				direction = 'e';
				x = 0;
				y = ThreadLocalRandom.current().nextInt(0, 160);
				break;
			}

			// iterationSteps tracks all steps in an iteration
			List<List<Integer>> iterationSteps = new ArrayList<List<Integer>>();

			int length = 0;
			boolean restartIteration = false;
			// Loop while within boundary to create highway
			do {
				// Ran into another highway; set flag to retry
				if (map[x][y] == 'a' || map[x][y] == 'b') {
					restartIteration = true;
					break;
				}
				if (map[x][y] == '1') {
					map[x][y] = 'a';
					List<Integer> coord = new ArrayList<Integer>();
					coord.add(x);
					coord.add(y);
					iterationSteps.add(coord);
				} else if (map[x][y] == '2') {
					map[x][y] = 'b';
					List<Integer> coord = new ArrayList<Integer>();
					coord.add(x);
					coord.add(y);
					iterationSteps.add(coord);
				}
				length++;
				// If we have gone 20 steps, try to change direction
				if (length % 20 == 0 && length > 0) {
					int tmp = ThreadLocalRandom.current().nextInt(0, 10);
					// turn left
					if (tmp == 0 || tmp == 1) {
						if (direction == 'n') {
							direction = 'w';
						} else if (direction == 's') {
							direction = 'e';
						} else if (direction == 'e') {
							direction = 'n';
						} else if (direction == 'w') {
							direction = 's';
						}
					}
					// turn right
					else if (tmp == 2 || tmp == 3) {
						if (direction == 'n') {
							direction = 'e';
						} else if (direction == 's') {
							direction = 'w';
						} else if (direction == 'e') {
							direction = 's';
						} else if (direction == 'w') {
							direction = 'n';
						}
					}
				}
				// Increment coord based on direction
				switch (direction) {
				case 'b':
					y--;
					break;
				case 's':
					y++;
					break;
				case 'e':
					x++;
					break;
				case 'w':
					x--;
					break;
				}
			} while (x >= 0 && y >= 0 && x < 120 && y < 160);

			// If highway is unacceptable, erase changes and try again
			if (restartIteration || length < 100) {
				// Restart entire process if we have had to restart this
				// iteration 10 times.
				if (restartCount == 10) {
					return generateRivers(mapcopy);
				}
				restartCount++;
				for (List<Integer> coord : iterationSteps) {
					int xCoord = coord.get(0);
					int yCoord = coord.get(1);
					if (map[xCoord][yCoord] == 'a' || map[xCoord][yCoord] == 'a') {
						map[xCoord][yCoord] = '1';
					} else if (map[xCoord][yCoord] == 'b' || map[xCoord][yCoord] == 'b') {
						map[xCoord][yCoord] = '2';
					}
				}
				iterationSteps = new ArrayList<List<Integer>>();
				continue;
			}
			count++;
		}
		return map;
	}
	
	private static char[][] generateImpassable(char[][] map) {
		// TODO Auto-generated method stub
		int count = 0, x, y;
		while(count < 3840){
			x = (int)(Math.random() * 120);
			y = (int)(Math.random() * 160);
			if(map[x][y] == '1' || map[x][y] == '2'){
				map[x][y] = '0';
				//System.out.println(count + ": " + x + ", " + y);
				count++;
			}
		}
		return map;
	}
	
	private static Coordinate generateStart(char[][] map) {
		// TODO Auto-generated method stub
		int x = 0;
		int y = 0;
		Coordinate coord;
		switch ((int)(Math.random() * 4)) {
		case 0: //top 20 rows
			x = (int)(Math.random() * 20);
			y = (int)(Math.random() * 160);
			break;
		case 1: //bottom 20 rows
			x = (int)(Math.random() * 20 + 100);
			y = (int)(Math.random() * 160);
			break;
		case 2: //left-most 20 columns
			x = (int)(Math.random() * 120);
			y = (int)(Math.random() * 20);
			break;
		case 3: //right-most 20 columns
			x = (int)(Math.random() * 120);
			y = (int)(Math.random() * 20 + 140);
			break;
		}
		if (map[x][y] != '0') {
			coord = new Coordinate(x,y);
			System.out.println("Start: " + coord.toString());
			return coord;
		}
		return generateStart(map);
	}
	private static Coordinate generateEnd(char[][] map, Coordinate startCoord) {
		// TODO Auto-generated method stub
		int x = 0;
		int y = 0;
		Coordinate coord;
		switch ((int)(Math.random() * 4)) {
		case 0: //top 20 rows
			x = (int)(Math.random() * 20);
			y = (int)(Math.random() * 160);
			break;
		case 1: //bottom 20 rows
			x = (int)(Math.random() * 20 + 100);
			y = (int)(Math.random() * 160);
			break;
		case 2: //left-most 20 columns
			x = (int)(Math.random() * 120);
			y = (int)(Math.random() * 20);
			break;
		case 3: //right-most 20 columns
			x = (int)(Math.random() * 120);
			y = (int)(Math.random() * 20 + 140);
			break;
		}
		double distance = Math.sqrt(Math.pow(x - startCoord.getX(), 2) + Math.pow(y - startCoord.getY(),2));
		if (map[x][y] != '0' && distance >= 100) {
			coord = new Coordinate(x,y);
			System.out.println("End: " + coord.toString());
			return coord;
		}
		return generateEnd(map, startCoord);
	}
	
	public static void main(String [] args){
		try {
			generate("test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
