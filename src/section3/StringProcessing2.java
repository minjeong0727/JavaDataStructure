package section3;

import java.io.*;
import java.util.Scanner;

public class StringProcessing2 {
    //1. 자료구조를 제일 먼저 생각해야한다.
    //입력받은 데이터를 저장할 변수-> 자료구조
    static String[] words = new String[10000];  // 저장할 단어
    static int[] count = new int[10000];
    static int n = 0;   // 파일로 부터 읽은 단어의 개수
    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);

        while(true){//언제 끝날지 모르는건 무한루프로 정의하는 방법이 있다. 예)사용자가 종료를 원하기 전까지
            System.out.print("$ ");
            String command = kb.next();
            if(command.equals("read")){
                String fileName = kb.next();
                makeIndex(fileName);
            }else if(command.equals("find")){
                String str = kb.next();
                int index = findWord(str);
                if(index > -1){// !=-1
                    System.out.println("The word " + words[index] + " appears " + count[index] + "times.");
                }else{
                    System.out.println("The word " + str + " appears " + "does not appear.");
                }
            }else if(command.equals("saveAs")){
                String fileName = kb.next();
                saveAs(fileName);
            }else if(command.equals("exit")){
                break;
            }
            kb.close();
        }


        for(int i=0; i< n; i++) {
            System.out.println("words[i]" + " " + count[i]);
        }
    }
    static void saveAs(String fileName){
        PrintWriter outFile = null;
        try {
            outFile = new PrintWriter(new FileWriter(fileName));
            for(int i = 0; i < n; i++){
                System.out.println(words[i] + " " + count[i]);
            }
            outFile.close();
        } catch (IOException e) {
            System.out.println("Save failed");
            return;
        }



    }

    static void makeIndex(String fileName){
        try {
            Scanner inFile = new Scanner(new File(fileName));
            while(inFile.hasNext()){
                String str = inFile.next();
                addWord(str);//단어를 목록에서 검사해서 이미 있다면 빈도++, 없다면 목록에 추가하는 함수
            }
        } catch (FileNotFoundException e) {
            return;
        }

    }

     static void addWord(String str) {
        int index = findWord(str);//목록에 단어가 포함되어있는 없는지 확인 !!! 정보를 전달해줘야함(boolean,혹으 int)->여기서는 위치 정보도 필요하므로 int-> 찾으면 배열인덱스 retyrn, 못찾으면 -1
        if(index != -1){    //found //경우를 나눈다.찾음/못찾음
            count[index]++;
        }else{//not found
            words[n] += str;
            count[n] = 1;
            n++;
        }
    }

    static int findWord(String str){
        for(int i = 0; i<n; i++){
            if(words[i].equalsIgnoreCase(str))return i;
        }
        return -1;  //여기까지 온다면 찾는게 없다는뜻
    }


}
