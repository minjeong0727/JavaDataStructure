package section3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StringProcessing1 {
    //1. 프로그램어 저장할 데이터 자료구조를 정의한다.
    //2. 사람이름은 names, 전화번호는 numbers
    //변수를 선언할 수 있는 위치가 두곳이다.
    // 현재 있는 곳은 지역변수(해당 함수안에서만 사용 가능), 먼역애 외부에 있으면 전역변수(해당 클래스에 존재하기만 하면 사용 가능, static 필수)
    static String [] names = new String[1000];
    static int[]numbers = new int[1000];
    static int n = 0;//컴퓨터가 읽은 사람의 수

    public static void main(String[] args) throws FileNotFoundException {

        Scanner sc = null;    //표준 입출력 System.in이 아닌 파일 input.txt를 불러오자
        try {
            sc = new Scanner(new File("src/section3/input.txt"));
            while(sc.hasNext()){ //파일끝에 도달할때까지 반복
                names[n] = sc.next();
                numbers[n] = sc.nextInt();
                n++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No File");
            System.exit(1);
        }

        bubbleSort(n, names,numbers);

        for(int i = 0; i <n; i++){
            System.out.println("Name:" + names[i] + ", Numbers:" + numbers[i]); //print()줄안바뀜 println줄바뀜

        }
    }

    public static void bubbleSort(int n, String[] names, int[] numbers){ //call-by-value

        for(int i = n-1; i >0; i--){
            for(int j = 0; j < i; j++){
                if(names[j].compareTo(names[j+1]) > 0){  //문자열 비교법 st1.equals(str2)
                    //swap numbers[j] and numbers[j+1]

                    String tmpstr = names[j];
                    names[j] = names[j+1];
                    names[j+1] = tmpstr;

                    int tmp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = tmp;

                }
            }
        }
    }

}
