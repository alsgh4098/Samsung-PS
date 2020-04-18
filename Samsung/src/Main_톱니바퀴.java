import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main_톱니바퀴 {

	static int[][] whls;
	static int[][] spin;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		whls = new int[4][8];

		for (int i = 0; i < 4; i++) {
			String line = br.readLine();
			for (int j = 0; j < 8; j++) {
				whls[i][j] = line.charAt(j)-'0';
			}
		}
		
		int N = Integer.parseInt(br.readLine());
		
		spin = new int[N][2];
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			// 몇번째 바퀴
			spin[i][0] = Integer.parseInt(line[0])-1;
			// 방향
			// 1은 시계 -1은 반시계
			if(line[1].contains("-")) {
				spin[i][1] = -1;
			}else {
				spin[i][1] = 1;
			}
		}


		for (int i = 0; i < N; i++) {
			int[] lwls = new int[4];
			int[] rwls = new int[4];
			int whl_num = spin[i][0];
			int spin_way = spin[i][1];
			// 선택 바퀴기준 왼쪽바퀴들을 반대방향으로 돌릴수 있는지
			int way = spin_way;
			for (int j = whl_num; j > 0; j--) {
				if (Lcheck(whls[j - 1], whls[j])) {
					lwls[j - 1] = -way;
					way = -way;
				} else {
					break;
				}
			}

			way = spin_way;
			// 선택 바퀴기준 오른쪽바퀴들을 반대방향으로 돌릴수 있는지
			for (int j = whl_num; j < 3; j++) {
				if (Rcheck(whls[j + 1], whls[j])) {
					rwls[j + 1] = -way;
					way = -way;
				} else {
					break;
				}
			}

			// 위에서 바퀴들을 돌릴수 있는지 체크했고,

			// 아래서부터 바퀴들을 돌린다.
			// 우선 처음 돌리기로 한 바퀴는 정해진 방향대로 무조건 한번 돌린다.
			if (spin_way == 1) {
				right(whl_num);
			} else {
				left(whl_num);
			}

			// 위에서 선택한 그 바퀴기준 왼쪽, 오른쪽 바퀴들은 위에서 찾은 돌릴수 있는지 여부에 따라 돌린다.
			for (int j = 0; j < 4; j++) {
				if (rwls[j] == 1) {
					right(j);
				} else if (rwls[j] == -1) {
					left(j);
				}

				if (lwls[j] == 1) {
					right(j);
				} else if (lwls[j] == -1) {
					left(j);
				}
			}
			
		}

		int res = 0;

		for (int i = 0; i < 4; i++) {
			if (whls[i][0] == 1) {
				res += Math.pow(2, i);
			} else {
				continue;
			}
		}
		

		System.out.println(res);

	}

	// 시계 1
	static void right(int idx) {
		int temp = whls[idx][7];
		for (int i = 7; i > 0; i--) {
			whls[idx][i] = whls[idx][i-1];
		}

		whls[idx][0] = temp;

	}

	// 반시계 -1
	static void left(int idx) {
		int temp = whls[idx][0];
		for (int i = 0; i < 7; i++) {
			whls[idx][i] = whls[idx][i+1];
		}

		whls[idx][7] = temp;
	}

	// whl2 기준 왼쪽 체크
	static boolean Lcheck(int[] whl1, int[] whl2) {
		if (whl1[2] != whl2[6]) {
			return true;
		} else {
			return false;
		}
	}

	// whl2 기준 오른쪽 체크
	static boolean Rcheck(int[] whl1, int[] whl2) {
		if (whl1[6] != whl2[2]) {
			return true;
		} else {
			return false;
		}
	}

}
