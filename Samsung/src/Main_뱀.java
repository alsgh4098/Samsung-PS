import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_뱀 {
	
	static int[] dy = {0,1,0,-1};
	static int[] dx = {-1,0,1,0};
	
	static class Dir{
		int time;
		char way;
		
		public Dir(int time, char way) {
			super();
			this.time = time;
			this.way = way;
		}
		
	}
	
	static class Snake{
		int x;
		int y;
		
		public Snake(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map;
		int N = Integer.parseInt(br.readLine());
		map = new int[N+2][N+2];
		
		int appleCnt = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < appleCnt; i++) {
			String[] line = br.readLine().split(" ");
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			
			map[x][y] = 1;
		}
		// end set apple
		
		ArrayList<Dir> DirList = new ArrayList<Dir>();
		
		int dirCnt = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < dirCnt; i++) {
			String[] line = br.readLine().split(" ");
			int time = Integer.parseInt(line[0]);
			char way = line[1].charAt(0);
			
			DirList.add(new Dir(time,way));
		}
		// end set DirList
		
		int time = 0;
		
		ArrayList<Snake> SnakeList = new ArrayList<Snake>();
		
		// 1,1에서 머리가 오른쪽을 향하고 있음.
		// Snake리스트의 0은 머리, 마지막은 꼬리
		SnakeList.add(new Snake(1,1));
		int sway = 1;
		
		map[1][1] = 2;
		
//		printMap(map);
		
//		System.out.println();
		
		while(true) {
			
			time++;

			Snake head = SnakeList.get(0);
			
			int nhx = head.x + dx[sway];
			int nhy = head.y + dy[sway];
			
			// 벽에 부딪힘.
			if(0>= nhx || nhx > N || 0>= nhy || nhy > N) {
				break;
			}
			
			// 자기자신과 만남.
			if(map[nhx][nhy] == 2) {
				break;
			// 사과를 먹어서 늘어남.
			}else if(map[nhx][nhy] == 1) {
				SnakeList.add(0, new Snake(nhx,nhy));
				map[nhx][nhy] = 2;
			
			// 사과를 못먹었다면 그냥 이동만 함.
			}else {
				SnakeList.add(0, new Snake(nhx,nhy));
				map[nhx][nhy] = 2;
				
				Snake tail = SnakeList.get(SnakeList.size()-1);
				map[tail.x][tail.y] = 0;
				SnakeList.remove(SnakeList.size()-1);
			}
			
			if(DirList.size() >= 1) {
				
				Dir dir = DirList.get(0);
				
				if(dir.time == time) {
					char way = dir.way;
					
					if(way == 'L') {
						switch (sway) {
						case 0:
							sway = 3;
							break;
						case 1:
							sway = 0;
							break;
						case 2:
							sway = 1;
							break;
						case 3:
							sway = 2;
							break;
						default:
							break;
						}
					}else {
						switch (sway) {
						case 0:
							sway = 1;
							break;
						case 1:
							sway = 2;
							break;
						case 2:
							sway = 3;
							break;
						case 3:
							sway = 0;
							break;
						default:
							break;
						}
					}
					
					DirList.remove(0);
				}// end changeDir
				
			}
//			printMap(map);
//			System.out.println();
		}// end logic
		
		System.out.println(time);
		
	}
	
	static void printMap(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

}
