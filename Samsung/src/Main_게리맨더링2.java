import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main_게리맨더링2{

	static int N;
	static int[][] map;

	// 5번 선거구의 경계선.
	static int[][] boundary;

	// 왼쪽 아래 , 오른쪽 아래, 오른쪽 위, 왼쪽 위
	static int[] dx = { 1, 1, -1, -1 };
	static int[] dy = { -1, 1, 1, -1 };

	// 5번 선거구의 사람들의 합을 구하고 그 구역을 전부 방문 처리하기 위해서.
	static int[] dx5 = { 0, 1, 0, -1 };
	static int[] dy5 = { 1, 0, -1, 0 };

	static int min = Integer.MAX_VALUE;

	static int people1;
	static int people2;
	static int people3;
	static int people4;
	static int people5;

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();

		map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				for (int d1 = 1; d1 <= N; d1++) {
					for (int d2 = 1; d2 <= N; d2++) {
						// 경계의 길이 d1과 d2의 조건
//						if (x + d1 + d2 <= N && 1 <= y - d1 && y + d2 <= N) {
						if (d1>=1 && d2 >= 1 && 1 <= x && x < x+d1+d2 && x+d1+d2 <= N &&  1 <= y-d1 && y-d1 < y && y < y+d2 && y+d2 <= N) {
							// 5번 선거구의 경계선을 초기화.
							boundary = new int[N + 1][N + 1];
							
							//5번 선거구의 경계설정
							setBound5(x, y, d1, d2);
							//5번 선거구가 아닌 영역 체크
							setNot5(0, 0);
							setNot5(0, N);
							setNot5(N, 0);
							setNot5(N, N);
							
							//각 구역별 선거인원 초기화
							people1 = 0;
							people2 = 0;
							people3 = 0;
							people4 = 0;
							people5 = 0;
							
							//각 구역별 선거인원 구함
							sum5();
							sum4(x, y, d1, d2);
							sum3(x, y, d1, d2);
							sum2(x, y, d1, d2);
							sum1(x, y, d1, d2);
							
							int[] getMin = new int[] { people1, people2, people3, people4, people5 };

							Arrays.sort(getMin);
							// 가장 큰곳, 가장 작은곳 뺌.
							int res = getMin[4] - getMin[0];

							if (min > res) {
								min = res;
							}

						}
					}
				}
			}
		} // end for

		System.out.println(min);

	}// end main


	// 5번 구역의 경계선을 만든다.
	static void setBound5(int x, int y, int d1, int d2) {
		// 사각형을 그려주면 된다.
		// 순서는 마음대로
		// 나는,
		// 기준점 x,y에서 d1의 길이 만큼 왼쪽아래로 가고 꼭지점 1을 만들고
		// 꼭지점1에서 d2길이 만큼 오른쪽 아래로 가서 꼭지점 2를 만들고
		// 거기서 d1의 길이만큼 오른쪽 위로 가서 꼭지점 3을 만들고
		// 다시 d2의 길이만큼 왼쪽 위로 가서 기준점과 만나는 경계선을 만들려고 한다.
		// dx, dy를 인덱스 순서대로 왼쪽아래, 오른쪽아래, 오른쪽 위, 왼쪽 위로 순서로 정해놨다.

		int len = d1;
		while (len != 0) {
			boundary[x][y] = 5;
			x += dx[0];
			y += dy[0];
			len--;
		}

		len = d2;
		while (len != 0) {
			boundary[x][y] = 5;
			x += dx[1];
			y += dy[1];
			len--;
		}

		len = d1;
		while (len != 0) {
			boundary[x][y] = 5;
			x += dx[2];
			y += dy[2];
			len--;
		}

		len = d2;
		while (len != 0) {
			boundary[x][y] = 5;
			x += dx[3];
			y += dy[3];
			len--;
		}
	}

	static void setNot5(int x, int y) {
		// 5번 선거구의 영역과 경계면을 제외한 나머지 지역들을 
		// dfs탐색으로 전부 1로 처리해준다.
		boundary[x][y] = 1;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx5[i];
			int ny = y + dy5[i];
			if (0 <= nx && nx <= N && 0 <= ny && ny <= N && boundary[nx][ny] == 0) {
				setNot5(nx, ny);
			}
		}

	}
	
//	5번 선거구
	static void sum5() {
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N ; y++) {
				// 5선거구 경계 안은 0으로 초기화돼있다.
				// 5선거구 경계도 포함해야 한다.
				if (boundary[x][y] == 0 || boundary[x][y] == 5) {
					people5 += map[x][y];
				}
			}
		}
		
	}

//	1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
	static void sum1(int x, int y, int d1, int d2) {
		for (int r = 1; r < x + d1; r++) {
			for (int c = 1; c <= y; c++) {
				if (boundary[r][c] == 1) {
					people1 += map[r][c];
				}
			}
		}
	}

//	2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
	static void sum2(int x, int y, int d1, int d2) {
		for (int r = 1; r <= x + d2; r++) {
			for (int c = y + 1; c <= N; c++) {
				if (boundary[r][c] == 1) {
					people2 += map[r][c];
				}
			}
		}
	}

//	3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
	static void sum3(int x, int y, int d1, int d2) {
		for (int r = x + d1; r <= N; r++) {
			for (int c = 1; c < y - d1 + d2; c++) {
				if (boundary[r][c] == 1) {
					people3 += map[r][c];
				}
			}
		}
	}

//	4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
	static void sum4(int x, int y, int d1, int d2) {
		for (int r = x + d2+1; r <= N; r++) {
			for (int c = y - d1 + d2; c <= N; c++) {
				if (boundary[r][c] == 1) {
					people4 += map[r][c];
				}
			}
		}
	}

}
