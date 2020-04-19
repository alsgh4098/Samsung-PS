import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_주사위굴리기 {
	
	// 북,남 방향 주사위의 숫자들
	static int[] NS;
	// 서,동 방향 주사위의 숫자들
	static int[] WE;
	
	static int[][] map;
	
	static int[] diceMove;
	
	// 빈칸 동 서 북 남
	static int[] dx = {0,0,0,-1,1};
	static int[] dy = {0,1,-1,0,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] line = br.readLine().split(" ");
		
		int N = Integer.parseInt(line[0]);
		int M = Integer.parseInt(line[1]);
		int X = Integer.parseInt(line[2]);
		int Y = Integer.parseInt(line[3]);
		int K = Integer.parseInt(line[4]);
		
		map = new int[N][M];
		diceMove = new int[K];
		WE = new int[4];
		NS = new int[4];
		
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}// end input
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(dice[i][j]+" ");
//			}
//			System.out.println();
//		} // input test
		
		line = br.readLine().split(" ");
		
		for (int i = 0; i < K; i++) {
			diceMove[i] = Integer.parseInt(line[i]);
		}
		
		
		// 1 동 
		// 2 서 
		// 3 북 
		// 4 님
		
		for (int i = 0; i < K; i++) {
			int nx = X+dx[diceMove[i]];
			int ny = Y+dy[diceMove[i]];
			
//			System.out.println(nx+" "+ny);
			
			if(0<= nx && nx < N && 0<= ny && ny < M) {
				int up = 0;
//				System.out.println(map[nx][ny]);
				// 주사위를 굴림.
				moveNS(diceMove[i]);
				moveWE(diceMove[i]);
//				System.out.println(map[nx][ny]);
				// 주사위 제일 윗면
				if(diceMove[i] == 1 || diceMove[i] == 2) {
					up = WE[1];
//					System.out.println(up);
				}else if(diceMove[i] == 3 || diceMove[i] == 4) {
					up = NS[1];
//					System.out.println(up);
				}
				
				// 맵과 주사위 바닥에 숫자변경,
				if(map[nx][ny] != 0) {
					if(diceMove[i] == 1 || diceMove[i] == 2) {
						WE[3] = map[nx][ny];
//						System.out.println(WE[3]);
					}else if(diceMove[i] == 3 || diceMove[i] == 4) {
						NS[3] = map[nx][ny];
					}
					map[nx][ny] = 0;
				}else {
					if(diceMove[i] == 1 || diceMove[i] == 2) {
						map[nx][ny] = WE[3];
					}else if(diceMove[i] == 3 || diceMove[i] == 4) {
						map[nx][ny] = NS[3];
					}
				}
				if(diceMove[i] == 1 || diceMove[i] == 2) {
					NS[1] = WE[1];
					NS[3] = WE[3];
				}else if(diceMove[i] == 3 || diceMove[i] == 4) {
					WE[1] = NS[1];
					WE[3] = NS[3];
				}
				
				X = nx;
				Y = ny;
				
				sb.append(up+"\n");
//				for (int i2 = 0; i2 < WE.length; i2++) {
//					System.out.print(WE[i2]+" ");
//				}
//				System.out.println();
//				
//				for (int i2 = 0; i2 < NS.length; i2++) {
//					System.out.print(NS[i2]+" ");
//				}
//				System.out.println("\n");
			}else {
				continue;
			}
		}// end logic
		
		
		System.out.println(sb);
		
	}// end main
	
	static void moveNS(int way) {
		if(way == 4) {
			int temp = NS[NS.length-1];
			for (int i = NS.length-1; i >0; i--) {
				NS[i] = NS[i-1];
			}
			NS[0] = temp;
		}else if(way == 3) {
			int temp = NS[0];
			for (int i = 0; i < NS.length-1; i++) {
				NS[i] = NS[i+1];
			}
			NS[NS.length-1] = temp;
		}
	}
	
	static void moveWE(int way) {
		if(way == 1) {
			int temp = WE[WE.length-1];
			for (int i = WE.length-1; i > 0; i--) {
				WE[i] = WE[i-1];
			}
			WE[0] = temp;
		}else if(way == 2) {
			int temp = WE[0];
			for (int i = 0; i < WE.length-1; i++) {
				WE[i] = WE[i+1];
			}
			WE[WE.length-1] = temp;
		}
	}

}// end class
