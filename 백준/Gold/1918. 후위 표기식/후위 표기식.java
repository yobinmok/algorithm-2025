import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 스택에 담긴 연산자의 우선순위가 자신보다 높거나 같다면 pop
		char[] str_arr = br.readLine().toCharArray();
		Stack<Character> stk = new Stack<Character>();

		for (char str : str_arr) {
			if (str >= 'A' && str <= 'Z')
				sb.append(str);
			else if (str == '(') {
				stk.push(str);
			} else if (str == ')') {
				while (stk.peek() != '(')
					sb.append(stk.pop());
				stk.pop();
			} else {
				while (!stk.isEmpty() && priority(stk.peek()) >= priority(str)) {
					sb.append(stk.pop());
				}
				stk.push(str);
			}
		}
		
		while (!stk.isEmpty()) {
			sb.append(stk.pop());
		}
		
		System.out.println(sb);
	}

	public static int priority(Character str) {
		if (str == '+' || str == '-') {
			return 1;
		} else if (str == '*' || str == '/') {
			return 2;
		} else {
			return 0;
		}
	}

}
