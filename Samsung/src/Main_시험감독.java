import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_시험감독 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] testRoom = new int[N];
		
		String[] line = br.readLine().split(" ");

		for (int i = 0; i < testRoom.length; i++) {
			
			testRoom[i] = Integer.parseInt(line[i]);
			
		}
		
		line = br.readLine().split(" ");
		
		int first = Integer.parseInt(line[0]);
		int second = Integer.parseInt(line[1]);
		
		long cnt = 0;
		
		for (int i = 0; i < N; i++) {
			cnt++;
			int number = testRoom[i];
			number -= first;
			
			if(number <= 0 ) {
				continue;
			}else {
				int quo = number / second;
				int remain = number % second;
				
				if(remain > 0) {
					quo += 1;
				}
				
				cnt += quo;
			}
			
		}
		
		System.out.println(cnt);
		
		
	}

}
