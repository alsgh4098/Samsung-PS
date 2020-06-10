import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_테트로미노 {
	
	static int N,M;
	static int max;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
		map = new int[N][M];
		
		max = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		calc(N,M);
		map = reverse(map);
		calc(N,M);
		map = reverse(map);
		
		for (int i = 0; i < 3; i++) {
			map = rotate(map);
			calc(map.length,map[0].length);
			map = reverse(map);
			calc(map.length,map[0].length);
			map = reverse(map);
		}
		
		System.out.println(max);
		
	}
	
	static int[][] rotate(int[][] arr) {
	    int n = arr.length;
	    int m = arr[0].length;
	    int[][] rotate = new int[m][n];

	    for (int i = 0; i < rotate.length; i++) {
	        for (int j = 0; j < rotate[i].length; j++) {
	            rotate[i][j] = arr[n-1-j][i];
	        }
	    }

	    return rotate;
	}
	
	static int[][] reverse(int[][] arr){
		int[][] reverse = new int[arr.length][arr[0].length];
		
		for (int i = 0; i < reverse.length; i++) {
			for (int j = 0; j < reverse[0].length; j++) {
				reverse[i][j] = arr[i][reverse[0].length-1-j];
			}
		}
		
		return reverse;
		
	}
	
	static void calc(int N,int M) {
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				calc2(i,j,N,M);
			}
		}
		
		
	}

	private static void calc2(int i, int j,int N,int M) {
		
		int sum = 0;
		
		if(j+3 < M) {
			sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i][j+3];
			if(max < sum) {
				max = sum;
			}
		}
		
		if( i+2 < N && j+1 < M) {
			sum = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+2][j+1];
			if(max < sum) {
				max = sum;
			}
		}
		
		if( i+2 < N && j+1 < M) {
			sum = map[i][j] + map[i+1][j] + map[i+1][j+1] + map[i+2][j+1];
			if(max < sum) {
				max = sum;
			}
		}
		
		if( i+1 < N && j+1 < M) {
			sum = map[i][j] + map[i+1][j] + map[i][j+1] + map[i+1][j+1];
			if(max < sum) {
				max = sum;
			}
		}
		
		if( i+1 < N && j+2 < M) {
			sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i+1][j+1];
			if(max < sum) {
				max = sum;
			}
		}
		
	}

}
