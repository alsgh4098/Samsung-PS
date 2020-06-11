import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_감시_잘못접근 {
	
	static int N,M;
	static int[][] map;
	static boolean[][] visited;
	static boolean[][] nvisited;
	static boolean[][] tvisited;
	
	static int sum;
	static int cctvOverlap;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static class Sight{
		int x;
		int y;
		int way;
		int cnt;
		
		public Sight(int x, int y, int way) {
			super();
			this.x = x;
			this.y = y;
			this.way = way;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		map = new int[N][M];
		visited = new boolean[N][M];
		cctvOverlap = 0;
		
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
				if(map[i][j] != 0) {
					visited[i][j] = true;
				}
			}
		}// end input
		
		solve();
		
		int res = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(visited[i][j]+" ");
				if(visited[i][j]) {
					res++;
				}
			}
			System.out.println();
		}
		
		System.out.println(N*M-res);
	}

	private static void solve() {
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] != 0 && map[i][j] != 6) {
					find(i,j);
				}
			}
		}
		
		
	}

	private static void find(int i, int j) {
		int x = i;
		int y = j;
		int ytemp = 0;
		int val = map[x][y];
		int res = Integer.MIN_VALUE;
		int temp = 0;
		
		int cctv = Integer.MAX_VALUE;
		cctvOverlap = 0;
		
		if(val == 1) {
			nvisited = new boolean[map.length][map[0].length];
			tvisited = new boolean[map.length][map[0].length];
			temp = cctv(x,y,1);
			if(temp > res) {
				res = temp;
				nvisited = tvisited;
			}else if(temp == res) {
				if(cctv > cctvOverlap) {
					cctv = cctvOverlap;
					res = temp;
					nvisited = tvisited;
				}
			}
			
			rotate();
			ytemp = y;
			y = x;
			x = map.length-1-ytemp;
			tvisited = new boolean[map.length][map[0].length];
			temp = cctv(x,y,1);
			if(temp > res) {
				res = temp;
				nvisited = tvisited;
			}else if(temp == res) {
				if(cctv > cctvOverlap) {
					System.out.println("Asdasdasd123");
					cctv = cctvOverlap;
					res = temp;
					nvisited = tvisited;
				}
			}
			
			rotate();
			ytemp = y;
			y = x;
			x = map.length-1-ytemp;
			tvisited = new boolean[map.length][map[0].length];
			temp = cctv(x,y,1);
			if(temp > res) {
				res = temp;
				nvisited = tvisited;
			}else if(temp == res) {
				if(cctv > cctvOverlap) {
					cctv = cctvOverlap;
					res = temp;
					nvisited = tvisited;
				}
			}
			
			rotate();
			ytemp = y;
			y = x;
			x = map.length-1-ytemp;
			tvisited = new boolean[map.length][map[0].length];
			temp = cctv(x,y,1);
			if(temp > res) {
				res = temp;
				nvisited = tvisited;
			}else if(temp == res) {
				if(cctv > cctvOverlap) {
					cctv = cctvOverlap;
					res = temp;
					nvisited = tvisited;
				}
			}
			rotate();
			
			dump();
			
		}else if(val == 2) {
			nvisited = new boolean[map.length][map[0].length];
			tvisited = new boolean[map.length][map[0].length];
			temp = cctv(x,y,2);
			if(temp > res) {
				res = temp;
				nvisited = tvisited;
			}else if(temp == res) {
				if(cctv > cctvOverlap) {
					cctv = cctvOverlap;
					res = temp;
					nvisited = tvisited;
				}
			}
			
			rotate();
			ytemp = y;
			y = x;
			x = map.length-1-ytemp;
			tvisited = new boolean[map.length][map[0].length];
			temp = cctv(x,y,2);
			if(temp > res) {
				res = temp;
				nvisited = tvisited;
			}else if(temp == res) {
				if(cctv > cctvOverlap) {
					cctv = cctvOverlap;
					res = temp;
					nvisited = tvisited;
				}
			}
			
			rotate();
			rotate();
			rotate();
			
			dump();
		}else if(val == 3) {
			nvisited = new boolean[map.length][map[0].length];
			tvisited = new boolean[map.length][map[0].length];
			temp = cctv(x,y,3);
			if(temp > res) {
				res = temp;
				nvisited = tvisited;
			}else if(temp == res) {
				if(cctv > cctvOverlap) {
					cctv = cctvOverlap;
					res = temp;
					nvisited = tvisited;
				}
			}
			
			rotate();
			ytemp = y;
			y = x;
			x = map.length-1-ytemp;
			tvisited = new boolean[map.length][map[0].length];
			temp = cctv(x,y,3);
			if(temp > res) {
				res = temp;
				nvisited = tvisited;
			}else if(temp == res) {
				if(cctv > cctvOverlap) {
					cctv = cctvOverlap;
					res = temp;
					nvisited = tvisited;
				}
			}
			
			rotate();
			ytemp = y;
			y = x;
			x = map.length-1-ytemp;
			tvisited = new boolean[map.length][map[0].length];
			temp = cctv(x,y,3);
			if(temp > res) {
				res = temp;
				nvisited = tvisited;
			}else if(temp == res) {
				if(cctv > cctvOverlap) {
					cctv = cctvOverlap;
					res = temp;
					nvisited = tvisited;
				}
			}
			
			rotate();
			ytemp = y;
			y = x;
			x = map.length-1-ytemp;
			tvisited = new boolean[map.length][map[0].length];
			temp = cctv(x,y,3);
			if(temp > res) {
				res = temp;
				nvisited = tvisited;
			}else if(temp == res) {
				if(cctv > cctvOverlap) {
					cctv = cctvOverlap;
					res = temp;
					nvisited = tvisited;
				}
			}
			rotate();
			
			dump();
		}else if(val == 4) {
			nvisited = new boolean[map.length][map[0].length];
			tvisited = new boolean[map.length][map[0].length];
			temp = cctv(x,y,4);
			if(temp > res) {
				res = temp;
				nvisited = tvisited;
			}else if(temp == res) {
				if(cctv > cctvOverlap) {
					cctv = cctvOverlap;
					res = temp;
					nvisited = tvisited;
				}
			}
			
			rotate();
			ytemp = y;
			y = x;
			x = map.length-1-ytemp;
			tvisited = new boolean[map.length][map[0].length];
			temp = cctv(x,y,4);
			if(temp > res) {
				res = temp;
				nvisited = tvisited;
			}else if(temp == res) {
				if(cctv > cctvOverlap) {
					cctv = cctvOverlap;
					res = temp;
					nvisited = tvisited;
				}
			}
			
			rotate();
			ytemp = y;
			y = x;
			x = map.length-1-ytemp;
			tvisited = new boolean[map.length][map[0].length];
			temp = cctv(x,y,4);
			if(temp > res) {
				res = temp;
				nvisited = tvisited;
			}else if(temp == res) {
				if(cctv > cctvOverlap) {
					cctv = cctvOverlap;
					res = temp;
					nvisited = tvisited;
				}
			}
			
			rotate();
			ytemp = y;
			y = x;
			x = map.length-1-ytemp;
			tvisited = new boolean[map.length][map[0].length];
			temp = cctv(x,y,4);
			if(temp > res) {
				res = temp;
				nvisited = tvisited;
			}else if(temp == res) {
				if(cctv > cctvOverlap) {
					cctv = cctvOverlap;
					res = temp;
					nvisited = tvisited;
				}
			}
			rotate();
			
			dump();
		}else if(val == 5) {
			nvisited = new boolean[map.length][map[0].length];
			tvisited = new boolean[map.length][map[0].length];
			temp = cctv(x,y,5);
			if(temp > res) {
				res = temp;
				nvisited = tvisited;
			}else if(temp == res) {
				if(cctv > cctvOverlap) {
					cctv = cctvOverlap;
					res = temp;
					nvisited = tvisited;
				}
			}
			dump();
		}
		
	}
	
	
	private static int cctv(int x, int y, int val) {
		int cnt = 0;
		
		Queue<Sight> qu = new LinkedList<Sight>();
		cctvOverlap = 0;
		
		// 우
		if(val == 1) {
			qu.add(new Sight(x,y,1));
		// 좌 우
		}else if(val == 2) {
			qu.add(new Sight(x,y,1));
			qu.add(new Sight(x,y,3));
		// 상 우
		}else if(val == 3) {
			qu.add(new Sight(x,y,0));
			qu.add(new Sight(x,y,1));
		// 상 우 좌
		}else if(val == 4) {
			qu.add(new Sight(x,y,0));
			qu.add(new Sight(x,y,1));
			qu.add(new Sight(x,y,3));
		// 상 우 좌 하
		}else if(val == 5) {
			qu.add(new Sight(x,y,0));
			qu.add(new Sight(x,y,1));
			qu.add(new Sight(x,y,2));
			qu.add(new Sight(x,y,3));
		}
		
		while(!qu.isEmpty()) {
			Sight temp = qu.poll();
			int nx = temp.x + dx[temp.way];
			int ny = temp.y + dy[temp.way];
			
			if(0<= nx && nx < map.length && 0<= ny && ny < map[0].length && map[nx][ny] != 6) {
				tvisited[nx][ny] = true;
				qu.add(new Sight(nx,ny,temp.way));
				if(!visited[nx][ny]) {
					cnt++;					
				}
				if(map[nx][ny] != 0) {
					cctvOverlap++;
				}
			}
		}
		
		return cnt;
	}
	
	static void rotate() {
		int[][] nmap = new int[map[0].length][map.length];
		boolean[][] cvisited = new boolean[map[0].length][map.length];
		boolean[][] cvisited2 = new boolean[map[0].length][map.length];
		
		// 회전만 함.
		tvisited = cvisited;

		for (int i = 0; i < nmap.length; i++) {
			for (int j = 0; j < nmap[0].length; j++) {
				nmap[i][j] = map[map.length-1-j][i];
				cvisited[i][j] = nvisited[map.length-1-j][i];
				cvisited2[i][j] = visited[map.length-1-j][i];
			}
		}
		
		map = nmap;
		nvisited = cvisited;
		visited = cvisited2;
	}

	static void dump() {
		for (int i = 0; i < nvisited.length; i++) {
			for (int j = 0; j < nvisited[0].length; j++) {
				if(nvisited[i][j]) {
					visited[i][j] = true;
				}
			}
		}
	}
}

