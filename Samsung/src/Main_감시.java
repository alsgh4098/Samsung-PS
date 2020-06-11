import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main_감시 {
	
	static int[][] map;
	static int N,M;
	static ArrayList<Cctv> list;
	static int min;
	static int wall;
	static boolean[][] visited;
	static int[] dx = {-1,0,1,0};	
	static int[] dy = {0,1,0,-1};	

	static class Cctv{
		int x;
		int y;
		int way;
		int type;
		
		public Cctv(int x, int y,int type) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
		}

		public Cctv(int x, int y, int way, int type) {
			super();
			this.x = x;
			this.y = y;
			this.way = way;
			this.type = type;
		}
		
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
		map = new int[N][M];
		
		list = new ArrayList<Cctv>();
		
		min = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
				if(map[i][j] != 0 && map[i][j] != 6) {
					list.add(new Cctv(i,j,map[i][j]));
				}else if(map[i][j] == 6) {
					wall++;
				}
			}
		}// end input
		
		solve(0);
		
		System.out.println(min);
		
	}

	private static void solve(int cnt) {
		if(cnt == list.size()) {
			calc();
			return;
		}
		
		Cctv ths = list.get(cnt);
		int type = ths.type;
		
		if(type == 1) {
			list.get(cnt).way = 1;
			solve(cnt+1);
			
			list.get(cnt).way = 2;
			solve(cnt+1);
			
			list.get(cnt).way = 3;
			solve(cnt+1);
			
			list.get(cnt).way = 4;
			solve(cnt+1);
		}else if(type == 2) {
			list.get(cnt).way = 1;
			solve(cnt+1);
			
			list.get(cnt).way = 2;
			solve(cnt+1);
		}else if(type == 3) {
			list.get(cnt).way = 1;
			solve(cnt+1);
			
			list.get(cnt).way = 2;
			solve(cnt+1);
			
			list.get(cnt).way = 3;
			solve(cnt+1);
			
			list.get(cnt).way = 4;
			solve(cnt+1);
		}else if(type == 4) {
			list.get(cnt).way = 1;
			solve(cnt+1);
			
			list.get(cnt).way = 2;
			solve(cnt+1);
			
			list.get(cnt).way = 3;
			solve(cnt+1);
			
			list.get(cnt).way = 4;
			solve(cnt+1);
		}else {
			solve(cnt+1);
		}
		
	}

	private static void calc() {
		visited = new boolean[N][M];
		
		for (int i = 0; i < list.size(); i++) {
			Cctv ths = list.get(i);
			visited[ths.x][ths.y] = true;
			Watch(ths);
		}
		
		int sum = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(visited[i][j]) {
					sum++;
				}
			}
		}
		
		sum += wall;
		
		if(min > N*M - sum) {
			min = N*M - sum;
		}
		
	}
	
	static void Watch(Cctv ths){
		Queue<Cctv> qu = new LinkedList<Cctv>();
		
		int type = ths.type;
		int way = ths.way;
		
		if(type == 1) {
			if(way == 1) {
				qu.add(new Cctv(ths.x,ths.y,0,1));
			}else if(way == 2) {
				qu.add(new Cctv(ths.x,ths.y,1,1));
			}else if(way == 3) {
				qu.add(new Cctv(ths.x,ths.y,2,1));
			}else if(way == 4) {
				qu.add(new Cctv(ths.x,ths.y,3,1));
			}
		}else if(type == 2) {
			if(way == 1) {
				qu.add(new Cctv(ths.x,ths.y,1,2));
				qu.add(new Cctv(ths.x,ths.y,3,2));
			}else if(way == 2) {
				qu.add(new Cctv(ths.x,ths.y,0,2));
				qu.add(new Cctv(ths.x,ths.y,2,2));
			}
		}else if(type == 3) {
			if(way == 1) {
				qu.add(new Cctv(ths.x,ths.y,0,1));
				qu.add(new Cctv(ths.x,ths.y,1,1));
			}else if(way == 2) {
				qu.add(new Cctv(ths.x,ths.y,1,1));
				qu.add(new Cctv(ths.x,ths.y,2,1));
			}else if(way == 3) {
				qu.add(new Cctv(ths.x,ths.y,2,1));
				qu.add(new Cctv(ths.x,ths.y,3,1));
			}else if(way == 4) {
				qu.add(new Cctv(ths.x,ths.y,3,1));
				qu.add(new Cctv(ths.x,ths.y,0,1));
			}
		}else if(type == 4) {
			if(way == 1) {
				qu.add(new Cctv(ths.x,ths.y,0,4));
				qu.add(new Cctv(ths.x,ths.y,1,4));
				qu.add(new Cctv(ths.x,ths.y,2,4));
			}else if(way == 2) {
				qu.add(new Cctv(ths.x,ths.y,1,4));
				qu.add(new Cctv(ths.x,ths.y,2,4));
				qu.add(new Cctv(ths.x,ths.y,3,4));
			}else if(way == 3) {
				qu.add(new Cctv(ths.x,ths.y,2,4));
				qu.add(new Cctv(ths.x,ths.y,3,4));
				qu.add(new Cctv(ths.x,ths.y,0,4));
			}else if(way == 4) {
				qu.add(new Cctv(ths.x,ths.y,3,4));
				qu.add(new Cctv(ths.x,ths.y,0,4));
				qu.add(new Cctv(ths.x,ths.y,1,4));
			}
		}else if(type == 5) {
			qu.add(new Cctv(ths.x,ths.y,0,5));
			qu.add(new Cctv(ths.x,ths.y,1,5));
			qu.add(new Cctv(ths.x,ths.y,2,5));
			qu.add(new Cctv(ths.x,ths.y,3,5));
		}
		
		
		while(!qu.isEmpty()) {
			Cctv tmp = qu.poll();
			
			int nx = tmp.x + dx[tmp.way];
			int ny = tmp.y + dy[tmp.way];
			
			if(0<=nx && nx<N && 0<=ny && ny <M && map[nx][ny] != 6) {
				visited[nx][ny] = true;
				qu.add(new Cctv(nx,ny,tmp.way,tmp.type));
			}
			
		}
	}

}
