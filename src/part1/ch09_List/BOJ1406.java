package part1.ch09_List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class BOJ1406 {
    //영어 소문자만을 기록할 수 있는 편집기
    //문장 맨앞
    //문장 맨뒤
    //문장 중간
    //위치 가능
    //L:왼쪽으로 한 칸 옮긴다, 커서가 문장의 맨 앞이면 무시
    //D:오른쪽으로 한 칸 옮긴다, 커서가 문장의 맨 뒤이면 무시
    //B : 왼쪽에 있는 문자 삭제, 커서가 문장의 맨 앞이며 무시,삭제 되었을 뿐 오른쪽 위치는 그대로
    //P어쩌구: 어쩌구를 왼쪽에 추가

    //N: 초기 입력되는 문자열(오직 영어 소문자)
    //M: 입력할 명령어의 개수

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            String input = br.readLine();
            LinkedList<Character> userInput = new LinkedList<>();

            //문자열을 LinkedList로 변환
            for(char ch: input.toCharArray()){
                userInput.add(ch);
            }
            //커서 초기화(맨뒤)
            int cursor = userInput.size();

            int tryNumber = Integer.parseInt(br.readLine());
            while(tryNumber!= 0){
                //명령어 실행
                String direct = br.readLine();
                switch(direct.charAt(0)){
                    case 'L':
                        if(cursor>0)cursor--;
                        break;
                    case 'D':
                        if(cursor< userInput.size())cursor++;
                        break;
                    case 'B':
                        if(cursor>0){
                            userInput.remove(cursor-1);
                            cursor--;
                        }
                        break;
                    case 'P':
                        char ch = direct.charAt(2);
                        userInput.add(cursor, ch);
                        cursor++;
                        break;
                    default:
                        System.out.println("Invalid command");
                }
                tryNumber--;
            }
            StringBuilder sb = new StringBuilder();
            for(char c:userInput){
                sb.append(c);
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



}
