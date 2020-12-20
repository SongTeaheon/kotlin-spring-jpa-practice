# kotlin-spring-jpa-practice

Kotlin과 Spring JPA를 이용한 연습용 프로젝트

Test코드에 대한 연습

해당 프로젝트는 '자바 ORM 표준 JPA프로그래밍'(김영한 저)의 11장 프로젝트 도메인을 참고해서 만들었음

# api
## 회원(member)
회원 등록
POST /members
|이름|설명|필수여부|
|------|---|---|
|name|이름|o|
|city|도시명||
|street|도로명||
|zipcode|우편번호||

전체 회원 조회
GET /members
|이름|설명|필수여부|
|------|---|---|
|page|페이지 위치|x|
|size|페이지 크기|o|


id에 따른 회원 조회
GET /members/{memberId}

이름 변경
PUT /membmers/{memberId}
name : 변경 후 이름

## 상품(Item)
상품 등록
POST /items

상품 수정
POST /items/{itemId]

상품 조회
