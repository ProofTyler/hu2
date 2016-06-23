package hu2.model;

import java.util.ArrayList;

import hu2.LabyrinthController;

public class Labyrinth {
	private static int[][] feld;
	private static ArrayList<int[]> path = new ArrayList<>();
	private static int[] pos;
	
	public static final int GESPERRT = 0;
	public static final int WEG_GELAUFEN = 1;
	public static final int START = 2;
	public static final int WEG_NEU = 3;
	public static final int ZIEL = 4;
	
	public static void create(int y_matrix, int x_matrix) {
		feld = new int[y_matrix][x_matrix];
		for(int y = 0; y < y_matrix; y++){
			for(int x = 0; x < x_matrix; x++)
				LabyrinthController.createRectangle(y,x);
		}
		path.add(new int[]{1,1});
	}
	
	public static void clear(int y_matrix, int x_matrix){
		for(int y = 0; y < y_matrix; y++){
			for(int x = 0; x < x_matrix; x++){
				if(feld[y][x] == WEG_GELAUFEN){
					feld[y][x] = WEG_NEU;
					setStatus(WEG_NEU, y, x);
				}
			}	
		}
		path.clear();
		path.add(new int[]{1,1});
	}

	public static boolean findNextStep() {
		if(path.size() != 0){
			pos = path.get(path.size()-1);
			if(getStatus(pos[0], pos[1]) != ZIEL ){
				setStatus(WEG_GELAUFEN, pos[0], pos[1]);
				path.remove(path.size()-1);				
				if(getStatus(pos[0], pos[1]-1) >= WEG_NEU)	path.add(new int[]{pos[0], pos[1]-1});
				if(getStatus(pos[0]+1, pos[1]) >= WEG_NEU)	path.add(new int[]{pos[0]+1, pos[1]});
				if(getStatus(pos[0], pos[1]+1) >= WEG_NEU)	path.add(new int[]{pos[0], pos[1]+1});
				if(getStatus(pos[0]-1, pos[1]) >= WEG_NEU)	path.add(new int[]{pos[0]-1, pos[1]});
			} else{
				System.out.println("Hurra, Ausweg gefunden!");
				return true;
			}
		} else{
			System.out.println("Kein Ziel gefunden!");
			return true;
		}
		return false;
	}	

	public static void setStatus(int status, int y, int x) {
		feld[y][x] = status;
		LabyrinthController.setFillColor(status,y,x);
	}
	
	public static int getStatus(int y, int x) {
		return feld[y][x];
	}
	
}
