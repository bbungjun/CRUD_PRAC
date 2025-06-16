회원가입/게시판 CRUD 웹 애플리케이션
이 프로젝트는 Spring Boot(Gradle) 기반의 백엔드와 Next.js 기반의 프론트엔드로 구성된 풀스택 웹 애플리케이션입니다. 회원가입, 로그인, 게시글 및 댓글 기능을 제공합니다.

주요 기능
회원가입 및 로그인
게시판 CRUD (게시글 작성, 조회, 수정, 삭제)
댓글 기능 (작성, 조회, 삭제)
MySQL 데이터베이스 연동
반응형 UI 디자인
백엔드 (Spring Boot)
기술 스택
Spring Boot 3.5.0
Java 24
Spring Data JPA
MySQL
Gradle
주요 구현 기능
RESTful API 설계
엔티티 관계 매핑 (Member, Post, Comment)
트랜잭션 관리
CORS 설정
에러 처리
폴더 구조
src/main/java/com/example/demo : Java 소스 코드
엔티티 클래스: Member.java, Post.java, Comment.java
레포지토리: MemberRepository.java, PostRepository.java, CommentRepository.java
서비스: MemberService.java, PostService.java, CommentService.java
컨트롤러: MemberController.java, PostController.java, CommentController.java
설정: CorsConfig.java
src/main/resources : 설정 파일 및 리소스
application.properties: 데이터베이스 연결, 서버 포트 등 설정
schema.sql: 테이블 스키마 정의
API 엔드포인트
회원: /api/members (회원가입, 로그인, 회원 목록)
게시글: /api/posts (게시글 CRUD 기능)
댓글: /api/comments (댓글 작성, 조회, 삭제)
실행 방법
spring-signup/src/main/resources/application.properties에 MySQL 정보가 올바르게 설정되어 있는지 확인합니다.

프로젝트 루트에서 아래 명령어로 실행합니다:

cd spring-signup
./gradlew bootRun
프론트엔드 (Next.js)
기술 스택
Next.js 14
TypeScript
Shadcn UI 컴포넌트
반응형 디자인
주요 구현 기능
회원가입 및 로그인 페이지
게시글 목록, 작성, 상세보기, 수정 페이지
댓글 작성 및 조회 기능
사용자 인증 관리
서버 API와의 통합
폴더 구조
app/: 라우팅 및 페이지 컴포넌트
auth/: 인증 관련 페이지 (로그인, 회원가입)
board/: 게시판 관련 페이지 (목록, 작성, 상세)
components/: 재사용 가능한 UI 컴포넌트
lib/: 유틸리티 함수 및 설정
실행 방법
프로젝트 루트에서 아래 명령어로 의존성을 설치하고 실행합니다:

cd my-app
npm install
npm run dev
브라우저에서 http://localhost:3000으로 접속하여 애플리케이션을 확인합니다.

주요 기능 사용법
회원가입 및 로그인
홈페이지에서 상단 메뉴의 '로그인' 버튼을 클릭합니다.
계정이 없다면 '회원가입' 링크로 이동하여 계정을 생성합니다.
로그인하면 게시판 기능을 이용할 수 있습니다.
게시글 작성 및 관리
로그인 후 게시판 페이지에서 '글쓰기' 버튼을 클릭합니다.
제목과 내용을 입력한 후 '등록' 버튼을 클릭합니다.
게시글 상세 페이지에서 내용을 확인하고 댓글을 작성할 수 있습니다.
본인이 작성한 게시글은 수정 또는 삭제할 수 있습니다.
참고사항
백엔드 서버는 8090 포트에서 실행됩니다.
프론트엔드 서버는 3000 포트에서 실행됩니다.
MySQL 데이터베이스는 UTF-8 인코딩을 사용하여 한글 지원을 보장합니다.
