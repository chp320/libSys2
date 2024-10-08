# libSys2
- 간단한 도서관 대출 시스템 구현

## 개요
1. 회원가입, 도서대출, 도서반납 의 기능 제공
2. 회원가입
- 화면에서 이름, 생년월일을 입력 받고 회원가입절차 진행
- 회원번호는 현재날짜(yyyymmdd)와 6자리 난수로 구성
- 회원가입 api 호출하여 회원정보 등록
3. 도서대출
- 회원번호 입력(key-in, barcode reader-in) 후 고객정보 확인
- 도서의 ISBN 코드 입력 후 국립중앙도서관에서 제공하는 ISBN 조회 api 로 도서 정보 조회
- 최대 대출 건수 이내에서 ISBN 코드로 조회된 도서를 대출 실행
- 대출일로부터 2주 뒤 반납예정일자 설정
4. 도서반납
- 회원번호 입력(key-in, barcode reader-in) 후 고객정보 확인
- 반납할 도서의 ISBN 코드 입력 후 국립중앙도서관에서 제공하는 ISBN 조회 api 로 도서 정보 조회
- 대상 목록 도서 반납 처리

## 개발 환경
- jdk: openjdk 17.0.9 2023-10-17 
- node.js: 18.19.0
- npm: 10.2.3
- SpringBoot: 3.3.3
- SQLite

##### 참고
1. git repository 최초 생성
```
echo "# libSys2" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin <remote repository URL(ex. https://github.com/xxx/yyyy.git)>
git push -u origin main
```
