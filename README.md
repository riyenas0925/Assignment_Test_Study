# URL 파싱 후 데이터 가공 하기

## API Document
* http://localhost:8080/swagger-ui/index.html

## 기능 목록
- [x] URL주소에 해당하는 모든 HTML 코드를 불러온다. - Crawler#crawl()
- [x] 노출 유형이 HTML일 경우는 HTML 코드에서 태그를 제거한다.
- [x] 노출 유형이 TEXT일 경우에는 HTML 코드에서 모든 텍스트 포함한다.
- [x] HTML 코드에서 영어를 추출한다. CharacterExtractor#alphabets()
- [x] HTML 코드에서 숫자를 추출한다. CharacterExtractor#numbers()
- [x] 영어와 숫자를 각각 정렬하고 교차 출력한다. Processor#process()
  - [x] 영문, 숫자를 각각 오름차순으로 정렬해야 한다.
  - [x] 영어와 숫자를 교차로 섞어야 한다. Processor#shuffle()
- [x] 출력은 출력 묶음 단위를 기준으로 몫과 나머지를 구한다.
  - [x] 출력은 묶음 단위를 기준으로 몫을 구한다. - SplitCalculator#quotient()
  - [x] 출력은 묶음 단위를 기준으로 나머지를 구한다. - SplitCalculator#reminder()

## 기능 요구 사항
1. URL 입력을 하면 그 링크 안에있는 모든 HTML코드를 불러온다.
2. 노출 유형
   - 노출 유형이 HTML 일 경우는 태그를 제거한다 ```<div>abc</div> -> abc```
   - 노출 유형이 TEXT 일 경우에는 모든 텍스트 포함 ```<div>abc</div> -> <div>abc</div>```
3. 영어, 숫자만 출력
   - 결과 데이터는 영어와 숫자로만 구성된 데이터만 출력한다
4. 오름차순 정렬
   - 영어 : AaBbCcDd ... Zz
   - 숫자 : 0123456789
5. 교차 출력
   - 영어 숫자 영어 숫자
   - 더 이상 출력될 숫자 또는 영어가 없을 경우 남아있는 정렬된 문자열 그대로 출력
6. 출력 묶음 단위
   - 사용자가 입력한 자연수의 묶음 단위를 기준으로 몫과 나머지를 구하여 노출
     - 예) ‘A0a1B2b3’, 출력 묶음 단위 3인경우
     - 몫 ‘A0a1B2’
     - 나머지 ‘b3’
