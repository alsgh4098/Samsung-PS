import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_스타트와링크 {

	static int[][] map;
	static boolean[] select;
	static int N;
	static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		select = new boolean[N];

		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		} // end input

		min = Integer.MAX_VALUE;

//		input test
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}

		combi(0, 0);

		System.out.println(min);

	}

	private static void combi(int start, int selectSize) {
		if(start>=N) {
			return;
		}
		if (selectSize == N / 2) {
			getMin();
		}
		
		select[start] = true;
		combi(start + 1, selectSize + 1);
		select[start] = false;
		combi(start + 1, selectSize);
	}

	private static void getMin() {
		int teamA = 0;
		int teamB = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) {
					continue;
				}
				if (select[i] == true && select[j] == true) {
					teamA += map[i][j];
				} else if (select[i] == false && select[j] == false) {
					teamB += map[i][j];
				}
			}
		}

		int minus = Math.abs(teamA - teamB);

		if (min > minus) {
			min = minus;
		}
	}

}