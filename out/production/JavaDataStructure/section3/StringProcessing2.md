
# StringProcessing2(문자열 다루기 2)
### 1. 자료구조를 제일 먼저 생각해라!






```java
package section3;  
  
import java.util.Scanner;  
  
public class StringProcessing2 {  
    //1. 자료구조를 제일 먼저 생각해야한다.  
    //입력받은 데이터를 저장할 변수-> 자료구조  
    static String[] words = new String[10000];  // 저장할 단어  
    static int[] count = new int[10000];  
    static int n = 0;   // 파일로 부터 읽은 단어의 개수  
    public static void main(String[] args) {  
  
        Scanner kb = new Scanner(system.in);  
  
        while(true){//언제 끝날지 모르는건 무한루프로 정의하는 방법이 있다. 예)사용자가 종료를 원하기 전까지  
            System.out.print("$ ");  
            String command = kb.next();  
            if(command == "read"){  
  
            }else if(){  
  
            }else if(){  
  
            }else if(){  
                break;  
            }  
  
        }  
        sc.close();  
    }  
}
```

- 초기 설계구조다.그런데 여기서 잘못된 것이 있다. 무엇일까?

바로이곳이다.
```java

if(command == "read"){  
  
            }else if(){  
  
            }else if(){  
  
            }else if(){  
                break;  
            } 
```
- Java에서 문자열을 비교할 때 `==` 대신 `equals()`나 `compareTo()`를 사용하는 이유는 문자열이 참조 타입(reference type)이기 때문이다.

#### ==
==  는 참조변수의 값이 아니라 주소를 비교한다.

```java
String str1 = new String("hello");
String str2 = new String("hello");

if (str1 == str2) {
    System.out.println("Same reference");
} else {
    System.out.println("Different reference");
}


```
뭐가나올까? 같은 문자열이니 당연히 "Same refernce"를 골랐다몉 틀렸다. str1 과 str2는 다른 주소값을 가진 문자열 객체가 되므로 "Different reference"가 되는것이다.


#### equals
equals 또한 마찬가지로 주소를 비교한다 한마디로 eauals를 사용한데도 "Different referene"가 나올 수 밖에 없다는 말이다. 그런데 어떻게 값을 비교한걸까?
이유는 바로 String클래스에서 Object의 equals메서드를 오버라이딩하여 주소가 아닌 String 인스턴스에 저장된 내용을 비교하도록 변경하여 true아니면 false를 나오게 했기 때문이다.



```java
package section3;  
  
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
  
            }else if(command.equals("find")){  
  
            }else if(command.equals("saveas")){  
  
            }else if(command.equals("exit")){  
                break;  
            }  
  
        }  
        kb.close();  
    }  
}
```
설명이 되었다면 이런식으로 바꿔주겠다.




### 2. 적절히 함수(기능)을 나눠라!




# 전체 코드
```java
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
```