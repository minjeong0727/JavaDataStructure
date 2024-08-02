package part1.ch09_List;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class BOJ1406_best {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String origin = sc.next();
        List<Character> list = new LinkedList<>();
        for(char alp : origin.toCharArray())list.add(alp);

        int M = sc.nextInt();
        ListIterator<Character>it = list.listIterator(list.size());

        while(M-- > 0){
            char cmd = sc.next().charAt(0);
            if(cmd =='L'){
                if(it.hasPrevious())it.previous();
            }
            else if(cmd== 'D'){
                if(it.hasNext())it.next();

            }
            else if(cmd =='B'){
                if(it.hasPrevious()){
                    it.previous();
                    it.remove();
                }
            }
            else if(cmd =='P'){
                it.add(sc.next().charAt(0));
            }
        }
        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (char ch : list) {
            sb.append(ch);
        }
        System.out.println(sb.toString());

        // Scanner 리소스 닫기
        sc.close();
    }
}
