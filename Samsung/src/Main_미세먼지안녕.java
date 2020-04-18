import java.io.IOException;
import java.util.Scanner;

public class Main_미세먼지안녕 {

	static int R, C, T;
	static int[][] map;

	// 위 아래, 기계 두대의 좌표값.
	static int[][] machine = new int[2][2];

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();
		map = new int[R][C];
		int k = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == -1) {
					// x
					machine[k][0] = i;
					// y
					machine[k][1] = j;
					k++;
				}
			}
		} // end input

//		printMap();
		// -------한 턴---------
		int[][] nmap;
		while (T != 0) {
			// 확산하고
			nmap = initMap();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					// 네방향으로 확산 / 공기청정기가 없는곳으로 확산.
					if (map[i][j] > 0) {// 먼지인 경우
						int cnt = 0;
						for (int d = 0; d < 4; d++) {
							int nx = i + dx[d];
							int ny = j + dy[d];
							if (nx < 0 || nx > R - 1 || ny < 0 || ny > C - 1 || map[nx][ny] == -1) {
								continue;
							}
							nmap[nx][ny] += map[i][j] / 5;
							cnt++;
						}
						// 이 부분.
						// 위에서 확산된 먼지를 더 해준 것에다가 더 해줘야한다.
						// 여기 때문에 계속 못풀었다.
						nmap[i][j] += map[i][j] - cnt * (map[i][j] / 5) ;
					} else if (map[i][j] == -1) {
						nmap[i][j] = -1;
					}
					// 원래 미세먼지에서 확산한 만큼 빼주기
				}
			}

			map = copyMap(nmap);
//			printMap();
//			System.out.println("확산");
			
			// 공기청정기 가로줄
			for (int i = C - 1; i > 1; i--) {
				nmap[machine[0][0]][i] = map[machine[0][0]][i - 1];
				nmap[machine[1][0]][i] = map[machine[1][0]][i - 1];
			}
			// 맨 오른쪽
			for (int i = 0; i < machine[0][0]; i++) {
				nmap[i][C - 1] = map[i + 1][C - 1];
			}
			for (int i = R - 1; i > machine[1][0]; i--) {
				nmap[i][C - 1] = map[i - 1][C - 1];
			}
			// 맨 윗줄
			for (int i = 0; i < C - 1; i++) {
				nmap[0][i] = map[0][i + 1];
			}
			for (int i = 0; i < C - 1; i++) {
				nmap[R - 1][i] = map[R - 1][i + 1];
			}
			// 공기 청정기 흡수 줄
			for (int i = machine[0][0] - 1; i > 0; i--) {
				nmap[i][0] = map[i - 1][0];
			}
			for (int i = machine[1][0] + 1; i < R - 1; i++) {
				nmap[i][0] = map[i + 1][0];
			}
			nmap[machine[0][0]][1] = 0;
			nmap[machine[1][0]][1] = 0;
			map = copyMap(nmap);
//			printMap();
//			System.out.println("바람");
			T--;
		}

		int res = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] != -1) {					
					res += map[i][j];
				}
			}
		}

		System.out.println(res);

	}

	static int[][] initMap() {
		int[][] nmap = new int[R][C];
		return nmap;
	}

	static int[][] copyMap(int[][] nmap) {
		int[][] map = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = nmap[i][j];
			}
		}

		return map;
	}

	static void printMap() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
