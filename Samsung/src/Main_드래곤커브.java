import java.beans.Visibility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_드래곤커브 {

	// 0 오른쪽
	// 1 위쪽
	// 2 왼쪽
	// 3 아래쪽
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int res;

	static boolean[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		map = new boolean[101][101];

		int[][] dragon = new int[N][4];

		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");

			int y = Integer.parseInt(line[0]);
			int x = Integer.parseInt(line[1]);
			// 방향
			int d = Integer.parseInt(line[2]);
			// 세대
			int g = Integer.parseInt(line[3]);

			dragon[i][0] = x;
			dragon[i][1] = y;
			dragon[i][2] = d;
			dragon[i][3] = g;
		} // end input

//		test input
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < 4; j++) {
//				System.out.print(dragon[i][j]+" ");
//			}
//			System.out.println();
//		}

		res = 0;

		for (int i = 0; i < N; i++) {
			int[] way = makeWay(new int[] { dragon[i][2] }, dragon[i][3]);

//			for (int j = 0; j < way.length; j++) {
//				System.out.print(way[j] + " ");
//			}
//			System.out.println();

			makeDragon(dragon[i][0], dragon[i][1], way);
		}

		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				// 해당꼭지점 방문처리 되어있는지 확인하고 사각형 이루는지 탐색.
				if (map[i][j] == true) {
					if (getRes(i, j)) {
						res++;
					}
				}
			}
		}

		System.out.println(res);

	}// end main

	private static boolean getRes(int i, int j) {
		// 해당 꼭지점에서 사각형을 이루는 점들이 방문처리가 되어있는지 확인.

		// 현재 점 아래 점
		if (i + 1 < 101) {
			if (map[i + 1][j] == false) {
				return false;
			}
		}else {
			return false;
		}

		// 현재 점 오른쪽 점
		if (j + 1 < 101) {
			if (map[i][j + 1] == false) {
				return false;
			}
		}else {
			return false;
		}

		// 현재점 오른쪽 아래점
		if (i + 1 < 101 && j + 1 < 101) {
			if (map[i + 1][j + 1] == false) {
				return false;
			}
		}else {
			return false;
		}

		return true;

	}

	private static void makeDragon(int x, int y, int[] way) {

		map[x][y] = true;

		int len = way.length;

		for (int i = 0; i < len; i++) {
			int nx = x + dx[way[i]];
			int ny = y + dy[way[i]];

			if (0 <= nx && nx < 101 && 0 <= ny && ny < 101) {
				map[nx][ny] = true;
				x = nx;
				y = ny;
			} else {
//				System.out.println("error");
			}
		}

	}

	static int[] makeWay(int[] way, int g) {
		if (g == 0) {
			return way;
		}

		int len = way.length;

		int[] nway = new int[len * 2];
		int nlen = nway.length;

		for (int i = 0; i < nlen / 2; i++) {
			nway[i] = way[i];
		}

		for (int i = nlen / 2; i < nlen; i++) {
			nway[i] = (way[nlen - i - 1] + 1) == 4 ? 0 : (way[nlen - i - 1] + 1);
		}

		return makeWay(nway, g - 1);

	}

}
