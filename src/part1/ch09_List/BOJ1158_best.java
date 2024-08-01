package part1.ch09_List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class BOJ1158_best {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        List<Integer> survivors = new ArrayList<>(N);
        for(int i = 1; i <= N; i++){
            survivors.add(i);
        }
        int[] ans = new int[N];
        int currentIndex = 0;
        for(int i = 0; i < N; i++){
            //currentIndex + K-1
            // `-1`을 하는 이유는 K번째 사람을 제거할 떄 현재 위치에서 `K-1`만큼 이동해야 정확히 K번째 사람을 가리키기 때문이다.
            //인덱스는 0부터 시작하기 때문에 현재 위치를 포함하여 계산하라면   `K-1`만큼 이동해야한다.
            currentIndex = (currentIndex + K - 1) % survivors.size();
            ans[i] = survivors.remove(currentIndex);
        }
        //Stream API 사용..공부 필요
        System.out.print("<" + Arrays.stream(ans)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", ")) + ">");

    }
}
