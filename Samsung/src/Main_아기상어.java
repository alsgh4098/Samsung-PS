import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_아기상어 {

	static int N;

	static int[][] map;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static class Shark implements Comparable<Shark> {
		int x;
		int y;
		// 크기
		int size;
		// 이동거리
		int cnt;
		// 먹은횟수
		int eat;

		public Shark(int x, int y, int size,int eat, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.cnt = cnt;
			this.eat = eat;
		}

		@Override
		public int compareTo(Shark o) {
			if (this.x < o.x) {
				return -1;
			} else if(this.x == o.x){
				if (this.y < o.y) {
					return -1;
				}else if(this.y == o.y) {
					return 0;
				}
				return 1;
			}else {
				return 1;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		
//		Queue<Shark> qu = new LinkedList<Shark>();
		
		
		int sx = 0;
		int sy = 0;
		int size = 2;
		int cnt = 0;
		int eat = 0;
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine().replace(" ", "");
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j) - '0';
				if (map[i][j] == 9) {
					// 상어의 처음 크기는 2
					sx = i;
					sy = j;
				}
			}
		} // end input

		int res = 0;
		
		while(true) {
//			System.out.println(size);
			Queue<Shark> qu = new LinkedList<Shark>();
			PriorityQueue<Shark> pq = new PriorityQueue<Shark>();
			Shark shrk = new Shark(sx,sy,size,eat,cnt);
			qu.add(shrk);
			boolean[][] visited = new boolean[N][N];
			visited[sx][sy] = true;
			int found = -1;
			
			while (!qu.isEmpty()) {
				Shark bs = qu.poll();
				
				// 먹이는 하나 이상 찾았는데
				// 그 이상으로 거리를 탐색하려 할때 멈춘다.
				if (found == bs.cnt) {
					break;
				}
				
				for (int i = 0; i < 4; i++) {
					int nx = bs.x + dx[i];
					int ny = bs.y + dy[i];
					
					if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
						visited[nx][ny] = true;
						if (map[nx][ny] != 0 && map[nx][ny] < bs.size) {
							// 먹이를 찾음.
							found = bs.cnt+1;
							pq.offer(new Shark(nx, ny, bs.size,bs.eat+1, bs.cnt + 1));
							qu.offer(new Shark(nx, ny, bs.size,bs.eat+1, bs.cnt + 1));
						}
						
						if(map[nx][ny] == 0 || map[nx][ny] == bs.size) {
							qu.offer(new Shark(nx, ny, bs.size,bs.eat, bs.cnt + 1));
						}
					}
				}
			}// 똑같은 거리만큼 한번 찾음.
			
			// 하나 이상의 먹이를 찾았을때.
			if(found != -1) {
				Shark nshrk = pq.poll();
				//틀린부분 
				if(nshrk.eat == nshrk.size) {
					eat = 0;
					size = nshrk.size + 1;
				}else {
					size = nshrk.size;
					eat = nshrk.eat;
				}
				map[sx][sy] = 0;
				sx = nshrk.x;
				sy = nshrk.y;
				map[sx][sy] = 9;
				cnt = 0;
				res += found;
//				System.out.println(size);
			}else {
				break;
			}
		}
		
		System.out.println(res);
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
	}

}
