# URL 파싱 후 데이터 가공 하기

## 구현방법
* Spring Validator를 이용해 Controller에서 입력데이터 값을 검증했습니다.
* GlobalExceptionHandler을 통해 전역 예외 처리를 진행했습니다.
* TDD의 red-green-refactor 사이클을 통해 구현했습니다.
* 크롤러의 경우 외부 서비스의 상태에 따라 테스트 결과가 달라질 수 있기 때문에 Mock Server를 이용해 html페이지를 띄워 테스트했습니다.
* 34개의 테스트를 작성해 최대한 오류를 줄이고 리펙터링에 용이하도록 했습니다.

## API Document
* http://localhost:8080/swagger-ui/index.html

### Request
* url은 https, http protocol이 포함된 문자열만 허용합니다.
* unit은 1이상의 값만 허용합니다.
* exposureType은 HTML과 TEXT값만 허용합니다. (text, html 허용 안함)

```json
{
  "exposureType": "HTML",
  "unit": 5,
  "url": "https://google.com"
}
```

### Success Response
성공시 몫과 나머지를 반환합니다.
```json
{
  "quotient": "eeeGGGggglllooo",
  "reminder": "ooo"
}
```

### Error Response
에러 발생시 에러 메시지와 에러 코드를 반환합니다.
```json
{
  "errors": [
    "url은 https또는 http가 포함되어야 합니다.",
    "묶음 단위는 0일 수 없습니다.",
    "노출 유형은 TEXT와 HTML만 허용됩니다."
  ],
  "code": "400"
}
```

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
- [x] url은 blank면 안되고 https, http를 포함해야한다.
- [x] 노출 유형은 blank면 안되고 HTML과 TEXT 두개만 지원한다.
- [x] 묶음 단위는 null이면 안되고 1이상의 값만 허용한다. 

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
