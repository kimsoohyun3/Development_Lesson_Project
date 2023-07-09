## ✍️ Projects
  <img src="https://github.com/kimsoohyun3/Development_Lesson_Project/assets/127597074/3b05acca-2743-438d-8151-cdca2a96d971" alt=""/>

> 2023.05.07 - 2023.07.07
> **Back-End Developer**

<br/>

### 🔗 Link

## **배포 URL**
> http://codingbada.s3-website.ap-northeast-2.amazonaws.com/

<br/>

### **👨‍👨‍👧‍👧 Back-End 팀원**
<table>
  <tr>
    <td>
      <a href="https://github.com/kimsoohyun3">
        <img src="https://github.com/kimsoohyun3/Development_Lesson_Project/assets/127597074/45669623-a8e1-4f10-b305-2724e9399d93" width="100px" />
      </a>
    </td>
    <td>
      <a href="https://github.com/Pull-Stack">
        <img src="https://avatars.githubusercontent.com/u/108255447?v=4" width="100px" />
      </a>
    </td>
  </tr>
  <tr>
    <td align="center"><b><a href="https://github.com/kimsoohyun3">김수현</a></b></td>
    <td align="center"><b><a href="https://github.com/Pull-Stack">이재원</a></b></td>
  </tr>
</table>

### **👨‍👨‍👧‍👧 Front-End 팀원**
<table>
 <tr>
    <td>
      <a href="https://github.com/reignkk1">
        <img src="https://github.com/kimsoohyun3/Development_Lesson_Project/assets/127597074/75613d3e-d404-46d6-8590-de3efd183c1b" width="100px" />
      </a>
    </td>
    <td>
      <a href="https://github.com/smosco">
        <img src="https://github.com/kimsoohyun3/Development_Lesson_Project/assets/127597074/0721b6e0-d56d-4f58-88ae-597c35ed0867" width="100px" />
      </a>
    </td>
  </tr>
  <tr>
    <td align="center"><b><a href="https://github.com/reignkk1">김민겸</a></b></td>
    <td align="center"><b><a href="https://github.com/smosco">한현</a></b></td>
  </tr>
</table>

</br>
</br>

<img width="500" alt="스크린샷 2023-06-24 오후 10 20 28" src="https://github.com/kimsoohyun3/Development_Lesson_Project/assets/127597074/15bc06cb-e6f7-46ec-9293-1f382928f555">

<br/>
</br>

## 🛠 사용 기술
- Java
- Spring Boot
- JPA
- REST API

<br/>

## 🖥 기능
- Spring Security 를 이용한 회원가입, 로그인(+ 소셜 로그인), 로그아웃 구현
- 마이페이지 수정, 조회 구현
- 선생님 / 학생 모집 글 등록, 수정, 삭제 구현
- 검색 타입과 내용에 맞는 선생님 / 학생 찾기 구현
- 관리자 공지사항 등록, 수정, 삭제, 조회 구현
- 회원간 쪽지 보내기, 삭제, 보낸 편지함과 받은 편지함 구현

<br/>

## 💬 아쉬운 점
- 팀원분들과 소통을 첫째로 생각해서 시작했던 팀 프로젝트였습니다.
  그럼에도 초반 서로의 코드의 응답 방식이 달라서 뒤늦게 공통으로 맞춘 점 등 소통하지 못한 부분으로 오류가 발생한 점이 아쉬웠습니다.
- API 명세서 자동화로 Swagger 를 사용했는데, Java Code 가 지저분해 보이는 점이 아쉬웠습니다.
  알아보니 해결법으로 Swagger 의 장점과 Spring REST Docs 의 장점만 통합해서 사용할 수 있는 방법을 알게 되었습니다.

<br/>

## 💡 생각에 남는 문제 직면 예방 및 해결 과정
- 다양한 연관관계의 매핑에 의해 조회 시 발생할 수 있는 N + 1 문제를 즉시로딩인 @-ToOne 을 지연로딩으로 모두 변경해 주었습니다.
  - 하지만, 지연로딩은 연결 Entity 의 Filed 를 사용시 Proxy 에 대한 쿼리가 또 발생하는 다른 N + 1 문제를 @EntityGraph 로 해결합니다.
  - 또한, @-ToMany 관계인 Collection Join 을 할 경우 발생할 수 있는 Pagination 관련 문제를 @BatchSize 를 설정해 해결합니다.
