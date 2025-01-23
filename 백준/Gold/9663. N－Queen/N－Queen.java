import java.util.Scanner;

public class Main {

	static boolean[] flag_row;
	static boolean[] flag_up;
	static boolean[] flag_down;
	static int[] pos;
	static int count = 0;
	static int N;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		flag_row = new boolean[N];
		flag_up = new boolean[2*N-1];
		flag_down = new boolean[2*N-1];
		pos = new int[N];

		set(0);
		System.out.println(count);
		sc.close();
	}
	
	static void set(int i) { // i열 j행
		for(int j = 0; j<N; j++) {
			if(flag_row[j] == false
				&& flag_up[i+j] ==false
				&& flag_down[i-j+N-1] == false) {
				pos[i] = j;
				if(i == N-1)
					count += 1;
				else {
					flag_row[j] = flag_up[i+j] = flag_down[i-j+(N-1)] = true;
					set(i+1);
					flag_row[j] = flag_up[i+j] = flag_down[i-j+(N-1)] = false;
				}
			}
		}
	}
}