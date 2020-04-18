import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Main_미세먼지안녕_틀림 {
	
	static int R,C,T;
	static int[][] map;
	
	//위 아래, 기계 두대의 좌표값.
	static int[][] machine = new int[2][2];
	
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	
	static class Dust{
		int x;
		int y;
		int xy;
		int size;
		
		public Dust(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.xy = R*x+y;
			this.size = size;
		}

		@Override
		public String toString() {
			return "Dust [x=" + x + ", y=" + y + ", xy=" + xy + ", size=" + size + "]";
		}
		
		
	}
	
	
	public static void main(String[] args) throws IOException {
		List<Dust> list = new ArrayList<Dust>();
		
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();		
		T = sc.nextInt();
		map = new int[R][C];
		int k = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {			
				map[i][j] = sc.nextInt();
				if(map[i][j] == -1) {
					// x
					machine[k][0] = i;
					// y
					machine[k][1] = j;
					k++;
				}else if(map[i][j] != 0){
					list.add(new Dust(i,j,map[i][j]));
				}
			}
		}// end input
		
//		printMap();
		//-------한 턴---------
		while(T != 0) {
			// 확산하고
			int size = list.size();
//			System.out.println(size);
			for (int i = 0; i < size; i++) {
				Dust temp = list.get(i);
				
				int cnt = 0;
				for (int j = 0; j < 4; j++) {
					int nx = temp.x+dx[j];
					int ny = temp.y+dy[j];
					int nsize = temp.size/5;
					
					if(0<=nx && nx<R && 0<=ny && ny<C) {
						if(map[nx][ny] != -1) {
							if(nsize > 0) {
								System.out.println("asdasd");
								list.add(new Dust(nx,ny,nsize));
								cnt++;
							}
						}
					}					
				}
				temp.size = temp.size - ( cnt*(temp.size/5));
				
			}
			
			Collections.sort(list,new Comparator<Dust>() {
				@Override
				public int compare(Dust o1, Dust o2) {
					if(o1.xy < o2.xy) {
						return 1;
					}else if(o1.xy == o2.xy) {
						return 0;
					}else {
						return -1;
					}
				}
			});
			// 합치고
			for (int i = 0; i < list.size()-1; i++) {
				Dust ths = list.get(i);
				Dust tht = list.get(i+1);
				
				if(ths.xy == tht.xy) {
					ths.size += tht.size;
					list.remove(i+1);
					i--;
				}
			}
			System.out.println(list.size());
			List<Dust> list2 = new ArrayList<Dust>();
			
			// 바람분다 바람은 공기청정기 두대로 분다.
			// 공기청정기에 들어가면 없앤다.
			for (int i = 0; i<list.size(); i++) {
				Dust dst = list.get(i);
				int x = dst.x;
				int y = dst.y;
				
//				위쪽 공기청정기 라인
				if( x == machine[0][0]) {
//					System.out.println(dst.x);
					if( y == C-1) {
						dst.x--;
					}else if( y != C-1) {
						dst.y++;
					}
					list2.add(new Dust(dst.x,dst.y,dst.size));
					continue;
				}
//				아래쪽 공기청정기 라인
				if( x == machine[1][0]) {
					if( y == C-1) {
						dst.x++;
					}else if( y != C-1) {
						dst.y++;
					}
					list2.add(new Dust(dst.x,dst.y,dst.size));
					continue;
				}
				
//				맨 오른쪽 줄
				if( y == C-1) {
					if( x == 0) {
						dst.y--;
					}else if( x < machine[0][0]) {
						dst.x--;
					}else if( x == R-1) {
						dst.y--;
					}else if( x > machine[1][0]) {
						dst.x++;
					}
					list2.add(new Dust(dst.x,dst.y,dst.size));
					continue;
				}
				
//				맨윗줄
				if( x == 0) {
					if( y == 0) {
						dst.x++;
					}else if( y!= 0) {
						dst.y--;
					}
					list2.add(new Dust(dst.x,dst.y,dst.size));
					continue;
				}
				
//				맨 아랫줄
				if( x == R-1) {
					if( y == 0) {
						dst.x--;
					}else if( y!= 0) {
						dst.y--;
					}
					list2.add(new Dust(dst.x,dst.y,dst.size));
					continue;
				}
				
//				맨 왼쪽 줄, 공기청정기로 들어감.
				if( y == 0) {
					System.out.println(dst.toString());
					if( x == machine[0][0]-1) {
						continue;
					}else if( x == machine[1][0]+1 ){
						continue;
					}else if( x < machine[0][0] ) {						
						dst.x++;
					}else if( x > machine[1][0] ) {
						dst.x--;
					}
					list2.add(new Dust(dst.x,dst.y,dst.size));
					continue;
				}
			}
			list = list2;
			System.out.println();
			T--;
			//--------------------
		}
		System.out.println(list.size());
		int res = 0;
		for (Dust dust : list) {
			res += dust.size;
		}
		
		System.out.println(res);
		
	}
	
	static void printMap() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

}

