package part1.ch09_List;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ1158 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   //사람수
        int K = sc.nextInt();   //제거되는 번째

        List<Integer> list = new ArrayList<>();
        List<String> newList = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        newList.add("<");

        int index = 0;

        while (!list.isEmpty()) {
            index = (index + K - 1) % list.size(); // K번째 사람을 제거할 인덱스 계산
            int pop = list.remove(index); // 해당 인덱스의 사람 제거
            if (list.isEmpty()) {
                newList.add(pop + ">");
            } else {
                newList.add(pop + ", ");
            }
        }

        // 출력 형식 맞추기
        for (String s : newList) {
            System.out.print(s);
        }
    }
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();   //사람수
//        int K = sc.nextInt();   //제거되는 번째
//
//        ArrayList<Integer>list = new ArrayList<>();
//        ArrayList<String>newList = new ArrayList<>();
//        for(int i = 1; i <= N; i++){
//            list.add(i);
//        }
//        newList.add("<");
//        while(list.isEmpty()){
//           int pop =  list.remove(K);
//           newList.add(pop + ",");
//        }
//        newList.add(">");
//
//        System.out.println(newList);

}
