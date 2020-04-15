package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution_9480 {
    static List<Set<Integer>> list;
    static int n;
    static Stack<Integer> stack = new Stack<>();
    static int answer = 0;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("res/input9480.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            answer = 0;
            n = sc.nextInt();
            list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                char[] word = sc.next().toCharArray();
                Set<Integer> alphabet = new HashSet<>();
                for (int j = 0; j < word.length; j++) {
                    alphabet.add(getIndex(word[j]));
                }
                list.add(alphabet);
            }

            for (int i = 1; i <= n; i++) {
                combination(n, i, 0);
            }
            System.out.printf("#%d %d\n", test_case, answer);

        }
    }

    public static int getIndex(char a) {
        return (int) a - 97;
    }

    // 조합: 순서는 관심 없고 뽑은 유무만 생각
    public static void combination(int totalLength, int toPick, int idx) {
        if (toPick == 0) {
            // 0개를 뽑는다는 것은 더이상 뽑을 것이 없다는 말과 같다.
            Set<Integer> combine = new HashSet<>();
            for (Integer i : stack) {
                combine.addAll(list.get(i));
            }

            if (combine.size() == 26) {
                answer++;
            }
            return;
        }

        if (totalLength == toPick) {
            //뽑을 개수와 남은 원소의 개수가 같다는 건 나머지를 전부 뽑겠다는 말과 같으므로 전부 스택에 넣은 후 출력하면 된다.
            // idx부터 차례로 남은 모든 원소를 스택에 넣는다
            for (int i = 0; i < toPick; i++) {
                stack.push(idx + i);
            }
            Set<Integer> combine = new HashSet<>();
            for (Integer i : stack) {
                combine.addAll(list.get(i));
            }

            if (combine.size() == 26) {
                answer++;
            }
            // 이후 전부 방금 넣은 원소들을 pop시키고 다음 과정을 진행한다.
            for (int i = 0; i < toPick; i++) {
                stack.pop();
            }
            return;
        } else {
            // idex를 뽑는 경우: topick 1감소
            stack.push(idx);
            combination(totalLength - 1, toPick - 1, idx + 1);

            // indx를 뽑지 않는 경우
            stack.pop();
            combination(totalLength - 1, toPick, idx + 1);
        }
    }
}
