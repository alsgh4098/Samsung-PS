import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_로봇청소기 {

	static int[][] map;
	static boolean[][] visited;
	static int cnt;
	static int N;
	static int M;

	// 바라보는 방향이 북쪽일때 왼쪽,후진, 동쪽일때, 남쪽일때, 서쪽일때,
	static int[][] dx = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
	static int[][] dy = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] line = br.readLine().split(" ");

		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		map = new int[N][M];
		visited = new boolean[N][M];
		cnt = 0;

		String[] line2 = br.readLine().split(" ");
		int rx = Integer.parseInt(line2[0]);
		int ry = Integer.parseInt(line2[1]);
		// 0 북 1 동 2 남 3 서
		int rd = Integer.parseInt(line2[2]);
		for (int i = 0; i < N; i++) {
			String line3 = br.readLine().replace(" ", "");
			for (int j = 0; j < M; j++) {
				map[i][j] = line3.charAt(j) - '0';
			}
		}

		cnt++;
		visited[rx][ry] = true;
		go(rx, ry, rd, 0);

		System.out.println(cnt);

//		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

	}

//	1.현재 위치를 청소한다.

//	2,현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
//	왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
//	왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
//	네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
//	네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
//	로봇 청소기는 이미 청소되어있는 칸을 또 청소하지 않으며, 벽을 통과할 수 없다.
//  북 -> 서 -> 남 -> 동 -> 북
//	0 -> 3 -> 2 -> 1 -> 0
	private static void go(int rx, int ry, int rd, int spinCnt) {

		map[rx][ry] = 3;

		// 왼쪽 확인.
		int nx = rx + dx[rd][0];
		int ny = ry + dy[rd][0];
		
		if(spinCnt>4) {
			return;
		}

		if (0 <= nx && nx < N && 0 <= ny && ny < M) {
			
			if (spinCnt == 4) {
				nx = rx + dx[rd][1];
				ny = ry + dy[rd][1];
				if (0 <= nx && nx < N && 0 <= ny && ny < M) {
					if (map[nx][ny] != 1) {
						go(nx, ny, rd, 0);
					} else {
//						System.out.println(rx + " "+ ry);
						return;
					}
				}
			}// 왼쪽에 아직 청소하지 않은 공간이 존재한다면.
			else if (map[nx][ny] == 0 && visited[nx][ny] == false) {
				// 왼쪽으로 이동.
				visited[nx][ny] = true;
				cnt++;
				if (rd == 0) {
					rd = 3;
				} else if (rd == 1) {
					rd = 0;
				} else if (rd == 2) {
					rd = 1;
				} else if (rd == 3) {
					rd = 2;
				}
				go(nx, ny, rd, 0);
				// 왼쪽에 청소하지 아직 않은 공간이 없다면, 그 자리에서 왼쪽으로 방향을 돌림.
			} else if ( (map[nx][ny] == 1 || visited[nx][ny] == true)) {
				if (rd == 0) {
					rd = 3;
				} else if (rd == 1) {
					rd = 0;
				} else if (rd == 2) {
					rd = 1;
				} else if (rd == 3) {
					rd = 2;
				}
				// 방향만 바꾸고, 방향 바꾼 횟수만 늘려주고 계속 탐색.
				go(rx, ry, rd, spinCnt + 1);
				// 4방향 탐색을 모두 마쳤으나, 청소할 곳이 없다면. 그자리에서 뒤로 한칸감.
			}
		}

	}// end method

}
