# myBoard
### spring mvc2 구조를 이용하여 게시판 만들기
<br>

>기간<br>

2021.08.29 ~ 2021.09.08

>게시판의 기본 서비스 제공
- 로그인, 회원가입
- 회원정보 수정, 회원 탈퇴
- 게시글 조회, 회원이 쓴 게시글 조회, 회원이 쓴 댓글, 대댓글 조회
- 게시글 작성, 게시글 수정, 게시글 삭제

>개발환경
- java, Eclipse를 이용한 JSP, Servlet
- Apache Tomcat 8.5
- Oracle SQL Developer

<hr><br>

>테이블 정의서 (oracle)

![create_table](https://user-images.githubusercontent.com/61831933/132464405-c5faf8d7-1896-46da-b8e8-c4800ce93097.JPG)
<br><br>
>시연 화면<br>
- boardlist.jsp : 게시글 조회(board read) 기능 <br>
로그인 하기전 boardlist 화면에서 게시글을 확인하려고 하면 로그인이 필요하다고 alert이 뜨도록 설정<br><br>
![boardlistpage](https://user-images.githubusercontent.com/61831933/132466031-8243830f-cbc1-4251-a22e-1bac294a13da.JPG)<br>
![loginalert](https://user-images.githubusercontent.com/61831933/132466039-3e492f77-1de4-4a5d-9849-9f9eeec8c127.JPG)<br><br>
로그인 후 Login 버튼 대신 회원의 member_name을 가져와서 출력 <br><br>
![afterlogin_boardlistpage](https://user-images.githubusercontent.com/61831933/132466050-e60d0178-75d5-4c3c-8bd5-b86694e7198b.JPG)<br>
boardlist 페이지를 이용하여 전체 게시글 조회, 회원 게시글 조회, 회원 댓글 조회를 출력<br>
allOnly 변수를 session으로 넘기며 all, only, commentOnly로 구분하여 사용<br>

회원 게시글 조회 페이지 <br>
![memberboardpage](https://user-images.githubusercontent.com/61831933/132469743-ba923c54-cff6-4d34-8c34-06a49972041c.JPG)<br><br>

회원 댓글 조회 페이지 <br>
![membercommentpage](https://user-images.githubusercontent.com/61831933/132469758-dd3c4ed2-15f6-4986-9c97-d1c323a8a8e6.JPG)<br><br>




- login.jsp<br><br>
![loginpage](https://user-images.githubusercontent.com/61831933/132466053-4cc263a2-d293-4909-bef4-9d372b178322.JPG)<br>

- enrollmember.jsp : 회원가입(member create) 기능 <br><br>
![enrollpage](https://user-images.githubusercontent.com/61831933/132466083-ba8b8525-6089-49d3-9590-138f5c7d2d30.JPG)<br>

- boardwrite.jsp : 게시글 작성(board create) 기능 <br><br>
session에 저장되어있는 memberLoginInfo를 이용하여 로그인되어있는 회원을 작성자(writer)로 저장<br>
![writepage](https://user-images.githubusercontent.com/61831933/132467780-03e451e0-74e9-4ed1-89c7-1195b6e408e6.JPG)<br>

- boarddetail.jsp : 한개의 게시글의 내용과 댓글을 조회(board read) 기능<br><br>
click할 때 게시글 번호(bno)를 session으로 넘겨서 그 게시글의 데이터를 출력
![boarddetailpage](https://user-images.githubusercontent.com/61831933/132467812-01a17b97-bf0e-41af-9214-618f0722e9eb.JPG)<br>
댓글과 대댓글까지 남길 수 있도록 설정<br>
댓글은 최근 작성한 댓글이 위로 올라올 수 있도록 설정<br>
대댓글은 말풍선 클릭시 입력할 수 있는 form이 나타나도록 설정<br>

- personalpage.jsp : 회원 개인 페이지 <br><br>
![personalpage](https://user-images.githubusercontent.com/61831933/132467835-bbf81071-708d-4306-b618-4891d8f3e2eb.JPG)<br>

- checkpwd.jsp : 회원정보 수정이나, 회원 탈퇴를 하기 전에 한번 더 비밀번호를 입력하여 확인하는 기능<br><br>
![memberdeletecheck](https://user-images.githubusercontent.com/61831933/132468970-3f663a05-1d83-49f1-839a-4b9b30422aca.JPG)<br>
![memberupdatecheck](https://user-images.githubusercontent.com/61831933/132468978-a6e69011-a580-49ad-a37b-9cd127d761c3.JPG)<br>

- memberupdate.jsp : 회원정보 수정(member update) 기능 <br><br>
session에 저장되어있는 memberLoginInfo를 이용하여 로그인 되어있는 회원의 정보를 form에 불러와서 회원이 입력했던 정보를 확인 후 수정할 수 있도록 구성<br>
![memberupdate](https://user-images.githubusercontent.com/61831933/132469088-41d87c0e-3fb6-4421-a5e7-319421101169.JPG)<br>

<br><br>
>추가 해볼수있는 기능
- 좋아요 기능
- 활발한 활동 member 순위지정
- 추천 게시글 상단에 띄우기 기능
...
