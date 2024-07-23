# StringProcessing3(문자열 다루기3)

- 소수점, 쉼표 등의 특수기호가 단어에 포함된다.
- 숫자 등이 단어로 취급된다.
- 대문자와 소문자가 다른 단어로 취급된다.
- 단어들이 알파벳 순으로 정렬되면 좋겠다.

### String 클래스 기본 메서드

**문자열 동일성 검사**\
**boolean equals(String)**

ex)\
String str1 = "java";
String str2 = "java";
boolean equals = str1.equals(str2);

**문자열 사전식 순서**\
**int compareTo(String)**


String str1 = "absolute";\
String str2 = "base";\
int result = str1.compareTo(str2);
---
**문자열 길이**\
**int length();**

String str = "abcdef";\
int length = str.length();

---
**특정 위치의 문자**\
**char charAt(int)**

String str = "ABCDEFG";\
char ch = str.charAt(2);
---
**지정한 문자의 위치 검색**\
**int indexOf(char)**

String str ="abcdef";\
int index = str.indexOf("d");

---
**지정된 범위의 부분 문자열**\
**String subString(int,int)**

String str = "ABCDEF";\
String substr = str.subString(0,2);