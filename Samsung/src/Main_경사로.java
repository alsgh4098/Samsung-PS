import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_경사로 {
	
	static int[][] map;
	static int res = 0;
	static int N;
	static int L;

	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		L = Integer.parseInt(line[1]);
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		for (int i = 0; i < N; i++) {
			if(checkX(i)) {
				res++;
			}
			if(checkY(i)) {
				res++;
			}
		}
		
		System.out.println(res);
	}
	
	// 세로줄 확인
	private static boolean checkY(int i) {
		int temp = map[i][0];
		boolean chck = true;
		
		// 전부 같은 층인지 확인.
		for (int j = 0; j < N; j++) {
			if(map[i][j] != temp) {
				chck = false;
			}
		}
		if(chck) {
			return true;
		}
		
		// 이미 놓은 경사로 여부.
		boolean[] visited = new boolean[N];
		
		
		for (int j = 0; j < N-1;) {
			
			// 내리막
			if(map[i][j] == map[i][j+1]+1) {
				// 내리막 시작하는 다음 높이를 측정
				int tmp = map[i][j+1];
				// 그 높이가 L만큼 지속되는지 확인.
				for (int j2 = 0; j2 < L; j2++) {
					j++;
					if( j < 0 || j >= N) {
						return false;
					}
					if(visited[j]) {
						return false;
					}
					visited[j] = true;
					if(tmp != map[i][j]) {
						return false;
					}
				}
				// 경사로를 다깔았으면 한칸 다음으로.
				continue;
			}
			
			if(map[i][j] == map[i][j+1]) {
				j++;
				continue;
			}
			
			// 오르막
			if(map[i][j]+1 == map[i][j+1]){
				
				int tmp = map[i][j];
				if(visited[j]) {
					return false;
				}
				for (int j2 = 0; j2 < L-1; j2++) {
					j--;
					if( j < 0 || j >= N) {
						return false;
					}
					if(visited[j]) {
						return false;
					}
					visited[j] = true;
					if(tmp != map[i][j]) {
						return false;
					}
				}
				j += L;
				continue;
			}
			
			return false;
		}
		
		return true;
	}
	
	// 가로줄 확인
	private static boolean checkX(int i) {
		int temp = map[0][i];
		boolean chck = true;
		
		// 전부 같은 층인지 확인.
		for (int j = 0; j < N; j++) {
			if(map[j][i] != temp) {
				chck = false;
			}
		}
		if(chck) {
			return true;
		}
		
		// 이미 놓은 경사로 여부.
		boolean[] visited = new boolean[N];
		
		
		for (int j = 0; j < N-1;) {
			
			// 내리막
			if(map[j][i] == map[j+1][i]+1) {
				// 내리막 시작하는 다음 높이를 측정
				int tmp = map[j+1][i];
				// 그 높이가 L만큼 지속되는지 확인.
				for (int j2 = 0; j2 < L; j2++) {
					j++;
					if( j < 0 || j >= N) {
						return false;
					}
					if(visited[j]) {
						return false;
					}
					visited[j] = true;
					if(tmp != map[j][i]) {
						return false;
					}
				}
				// 경사로를 다깔았으면 한칸 다음으로.
				continue;
			}
			
			if(map[j][i] == map[j+1][i]) {
				j++;
				continue;
			}
			
			// 오르막
			if(map[j][i]+1 == map[j+1][i]){
				
				int tmp = map[j][i];
				if(visited[j]) {
					return false;
				}
				for (int j2 = 0; j2 < L-1; j2++) {
					j--;
					if( j < 0 || j >= N) {
						return false;
					}
					if(visited[j]) {
						return false;
					}
					visited[j] = true;
					if(tmp != map[j][i]) {
						return false;
					}
				}
				j += L;
				continue;
			}
			
			return false;
		}
		
		return true;
	}

}
