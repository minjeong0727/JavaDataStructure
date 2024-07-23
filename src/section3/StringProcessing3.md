# StringProcessing3(문자열 다루기3)

- 소수점, 쉼표 등의 특수기호가 단어에 포함된다.
- 숫자 등이 단어로 취급된다.
- 대문자와 소문자가 다른 단어로 취급된다.
- 단어들이 알파벳 순으로 정렬되면 좋겠다.

### String 클래스 기본 메서드
**1️⃣문자열 동일성 검사**\  
**boolean equals(String)**

ex)\  
String str1 = "java";  
String str2 = "java";  
boolean equals = str1.equals(str2);

---

**2️⃣문자열 사전식 순서**\  
**int compareTo(String)**


String str1 = "absolute";\  
String str2 = "base";\  
int result = str1.compareTo(str2);

---

**3️⃣문자열 길이**\  
**int length();**

String str = "abcdef";\  
int length = str.length();
  
---  
**4️⃣특정 위치의 문자**\  
**char charAt(int)**

String str = "ABCDEFG";\  
char ch = str.charAt(2);

---

**5️⃣지정한 문자의 위치 검색**\  
**int indexOf(char)**

String str ="abcdef";\  
int index = str.indexOf("d");
  
---  
**6️⃣지정된 범위의 부분 문자열**\  
**String subString(int,int)**

String str = "ABCDEF";\  
String substr = str.subString(0,2);


![img](/JavaDataStructure/src/img/1.png)
![img](/JavaDataStructure/src/img/2.png)


```java
package section3;  
  
import java.io.*;  
import java.util.Scanner;  
  
public class StringProcessing3 {  
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
  
                String trimmed = trimming(str);  
                if(trimmed != null){  
                    String t = trimmed.toLowerCase();//대소문자를 그냥 소문자로 다 저장한다.  
                    addWord(t);//단어를 목록에서 검사해서 이미 있다면 빈도++, 없다면 목록에 추가하는 함수  
                }  
               inFile.close();  
            }  
        } catch (FileNotFoundException e) {  
            return;  
        }  
  
    }  
  
   static String trimming(String str) {  
        int i = 0, j = str.length()-1;  
        while(i <= str.length()&& !Character.isLetter(str.charAt(i)))//while i th character is not letter  
            i++;  
        while(j >= 0  && !Character.isLetter(str.charAt(j)))  
            j--;  
        if(i>j)return null;  
  
       return str.substring(i,j+1);  
    }  
  
    static void addWord(String str) {  
        int index = findWord(str);//목록에 단어가 포함되어있는 없는지 확인 !!! 정보를 전달해줘야함(boolean,혹으 int)->여기서는 위치 정보도 필요하므로 int-> 찾으면 배열인덱스 retyrn, 못찾으면 -1
		if(index != -1){    //단어가 이미 존재하는경우 
            count[index]++;  
        }else{//단어가 존재하지 않는 경우 
            int i=n-1; //배열의 마지막 인덱스부터 시작
            //단어를 삽입할 위치를 찾기 위해 뒤에서부터 검사 
            while(i>=0 && words[i].compareToIgnoreCase(str)>0){  
                words[i+1] = words[i];  //단어를 한칸씩이동
                count[i+i] = count[i];  //카운트도 이동
                i--;  
            }  
            words[i+1] += str;  //새로운 단어 삽입
            count[i+1] = 1;  //카운트를 1로 설정
            n++;  //단어 개수 증가
        }  
    }  
  
    static int findWord(String str){  
        for(int i = 0; i<n; i++){  
            if(words[i].equalsIgnoreCase(str))return i;  
        }  
        return -1;  //여기까지 온다면 찾는게 없다는뜻  
    }  
  
  
}
```

### 정렬된 리스트에서 삽입할때는 뒤에서부터 검사하는게 좋다.
이유는 앞에서부터 시작한다면 모두 스캔하여야하고 삽입된 이후의 원소들을 다 뒤로 보내야하는데에 반해,\
뒤에서부터 검사하면 스캔하면서 뒤쪽으로 하나씩 옮기고 조건을 만족할 시 삽입하면 되기 때문에 뒤에서부터 검사 하는게 좋다.