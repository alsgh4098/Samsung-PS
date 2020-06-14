import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_사다리조작 {
	
	static int N,M,H;
	static int[][] ladder;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] line = br.readLine().split(" ");
		
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		H = Integer.parseInt(line[2]);
		min = Integer.MAX_VALUE;
		
		ladder = new int[H+1][N+1];
		
		for (int i = 0; i < M; i++) {
			line = br.readLine().split(" ");
			
			int h = Integer.parseInt(line[0]);
			int n = Integer.parseInt(line[1]);
			
			// 오른쪽
			ladder[h][n] = 1;
			// 왼쪽
			ladder[h][n+1] = -1;
			
		}
		
		solve(1,0);
		
		System.out.println(min==Integer.MAX_VALUE?-1:min);
		
	}

	private static void solve(int index,int cnt) {
		
		if(cnt > 3 || cnt > min) {
			return;
		}
		if(check()) {
			if(min > cnt) {
				min = cnt;
			}
			return;
		}
		
		for (int j = 1; j <= H; j++) {
			for (int i = index; i <= N; i++) {
				
				if(possible(j, i)) {
					ladder[j][i] = 1;
					ladder[j][i+1] = -1;
					
					solve(i,cnt+1);
					
					ladder[j][i] = 0;
					ladder[j][i+1] = 0;
				}
				
			}
		}
	}

	private static boolean check() {
		
		for (int i = 1; i <= N; i++) {
			int start = i;
			for (int j = 1; j <= H; j++) {
				// 오른쪽
				if(ladder[j][start] == 1) {
					start++;
				}else if(ladder[j][start] == -1){
					start--;
				}
			}
			if(start == i) {
				continue;
			}else {
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean possible(int h, int n) {
		
		if(ladder[h][n] != 0) {
			return false;
		}
		
		if(n+1 > N) {
			return false;
		}
		
		
		if(ladder[h][n+1] != 0) {
			return false;
		}
		
		return true;
	}

}



