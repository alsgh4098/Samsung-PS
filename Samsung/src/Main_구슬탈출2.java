import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_구슬탈출2 {
	
	static int N,M;
	static char[][] map;
	static int res;
	
	// 상 우 하 좌
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static class Ball {
		int x;
		int y;
		
		// red = 0;
		// blue = 1;
		int color;

		public Ball(int x, int y, int color) {
			super();
			this.x = x;
			this.y = y;
			this.color = color;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		res = Integer.MAX_VALUE;
		Ball red = null;
		Ball blue = null;
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == 'R') {
					red = new Ball(i,j,0);
				}
				
				if(map[i][j] == 'B') {
					blue = new Ball(i,j,1);
				}
			}
		}
		
		dfs(red,blue,0);
		if(res == Integer.MAX_VALUE) {
			res = -1;
		}
		System.out.println(res);
	}

	private static void dfs(Ball red, Ball blue, int cnt) {
		if(cnt == 10) {
			return;
		}
		
		
		for (int i = 0; i < 4; i++) {
			// key
			Ball first = new Ball(red.x,red.y,red.color);
			Ball second = new Ball(blue.x,blue.y,blue.color);
			Ball temp = new Ball(first.x,first.y,first.color);
			
			switch(i) {
				case 0:
					if(first.x > second.x) {
						first = second;
						second = temp;
					}
					break;
				case 1:
					if(first.y < second.y) {
						first = second;
						second = temp;
					}
					break;
				case 2:
					if(first.x < second.x) {
						first = second;
						second = temp;
					}
					break;
				case 3:
					if(first.y > second.y) {
						first = second;
						second = temp;
					}
					break;
				default:
					break;
			}
			
			int ncnt = cnt+1;
			
			int fx = first.x;
			int fy = first.y;
			boolean ff = false;
			
			while(true) {
				
				fx += dx[i];
				fy += dy[i];
				
				if(map[fx][fy] == 'O') {
					ff = true;
					break;
				}
				
				if(map[fx][fy] == '#') {
					fx -= dx[i];
					fy -= dy[i];
					break;
				}
				
				// key
				if(fx == second.x && fy == second.y) {
					fx -= dx[i];
					fy -= dy[i];
					break;
				}
			}
			
			first.x = fx;
			first.y = fy;
			
			int sx = second.x;
			int sy = second.y;
			boolean sf = false;
			
			while(true) {
				
				sx += dx[i];
				sy += dy[i];
				
				if(map[sx][sy] == 'O') {
					sf = true;
					break;
				}
				
				if(map[sx][sy] == '#') {
					sx -= dx[i];
					sy -= dy[i];
					break;
				}
				// key
				if(sx == first.x && sy == first.y) {
					sx -= dx[i];
					sy -= dy[i];
					break;
				}
			}

			second.x = sx;
			second.y = sy;
			
			if(ff && sf) {
				continue;
			}else if(ff) {
				if(first.color == 0) {
					if(res > ncnt) {
						res = ncnt;
						continue;
					}
				}else {
					continue;
				}
			}else if(sf) {
				if(second.color == 0) {
					if(res > ncnt) {
						res = ncnt;
						continue;
					}
				}else {
					continue;
				}
			}
			
			if(first.color == 0) {
				dfs(first,second,ncnt);
			}else {
				dfs(second,first,ncnt);
			}
		}
		
	}

}
