# 회원가입/게시판 CRUD 웹 애플리케이션

이 프로젝트는 Spring Boot(Gradle) 기반의 백엔드와 Next.js 기반의 프론트엔드로 구성된 풀스택 웹 애플리케이션입니다. 회원가입, 로그인, 게시글 및 댓글 기능을 제공합니다.

## 주요 기능
- 회원가입 및 로그인
- 게시판 CRUD (게시글 작성, 조회, 수정, 삭제)
- 댓글 기능 (작성, 조회, 삭제)
- MySQL 데이터베이스 연동
- 반응형 UI 디자인

## 백엔드 (Spring Boot)

### 기술 스택
- Spring Boot 3.5.0
- Java 24
- Spring Data JPA
- MySQL
- Gradle

### 주요 구현 기능
- RESTful API 설계
- 엔티티 관계 매핑 (Member, Post, Comment)
- 트랜잭션 관리
- CORS 설정
- 에러 처리

### 폴더 구조
- `src/main/java/com/example/demo` : Java 소스 코드
  - 엔티티 클래스: `Member.java`, `Post.java`, `Comment.java`
  - 레포지토리: `MemberRepository.java`, `PostRepository.java`, `CommentRepository.java`
  - 서비스: `MemberService.java`, `PostService.java`, `CommentService.java`
  - 컨트롤러: `MemberController.java`, `PostController.java`, `CommentController.java`
  - 설정: `CorsConfig.java`
- `src/main/resources` : 설정 파일 및 리소스
  - `application.properties`: 데이터베이스 연결, 서버 포트 등 설정
  - `schema.sql`: 테이블 스키마 정의

### API 엔드포인트
- 회원: `/api/members` (회원가입, 로그인, 회원 목록)
- 게시글: `/api/posts` (게시글 CRUD 기능)
- 댓글: `/api/comments` (댓글 작성, 조회, 삭제)

### 실행 방법
1. `spring-signup/src/main/resources/application.properties`에 MySQL 정보가 올바르게 설정되어 있는지 확인합니다.
2. 프로젝트 루트에서 아래 명령어로 실행합니다:
   
   ```powershell
   cd spring-signup
   ./gradlew bootRun
   ```

## 프론트엔드 (Next.js)

### 기술 스택
- Next.js 14
- TypeScript
- Shadcn UI 컴포넌트
- 반응형 디자인

### 주요 구현 기능
- 회원가입 및 로그인 페이지
- 게시글 목록, 작성, 상세보기, 수정 페이지
- 댓글 작성 및 조회 기능
- 사용자 인증 관리
- 서버 API와의 통합

### 폴더 구조
- `app/`: 라우팅 및 페이지 컴포넌트
  - `auth/`: 인증 관련 페이지 (로그인, 회원가입)
  - `board/`: 게시판 관련 페이지 (목록, 작성, 상세)
- `components/`: 재사용 가능한 UI 컴포넌트
- `lib/`: 유틸리티 함수 및 설정

### 실행 방법
1. 프로젝트 루트에서 아래 명령어로 의존성을 설치하고 실행합니다:

   ```powershell
   cd my-app
   npm install
   npm run dev
   ```
2. 브라우저에서 `http://localhost:3000`으로 접속하여 애플리케이션을 확인합니다.

## 주요 기능 사용법

### 회원가입 및 로그인
1. 홈페이지에서 상단 메뉴의 '로그인' 버튼을 클릭합니다.
2. 계정이 없다면 '회원가입' 링크로 이동하여 계정을 생성합니다.
3. 로그인하면 게시판 기능을 이용할 수 있습니다.

### 게시글 작성 및 관리
1. 로그인 후 게시판 페이지에서 '글쓰기' 버튼을 클릭합니다.
2. 제목과 내용을 입력한 후 '등록' 버튼을 클릭합니다.
3. 게시글 상세 페이지에서 내용을 확인하고 댓글을 작성할 수 있습니다.
4. 본인이 작성한 게시글은 수정 또는 삭제할 수 있습니다.

---

## 참고사항
- 백엔드 서버는 8090 포트에서 실행됩니다.
- 프론트엔드 서버는 3000 포트에서 실행됩니다.
- MySQL 데이터베이스는 UTF-8 인코딩을 사용하여 한글 지원을 보장합니다.

## API 문서

이 섹션에서는 백엔드에서 제공하는 모든 REST API 엔드포인트에 대한 상세 설명을 제공합니다.

### 회원 관리 API

#### 회원 목록 조회
- **URL**: `/api/members`
- **Method**: `GET`
- **인증 필요**: 없음
- **응답**:
  - 성공: `200 OK` + 회원 목록 (배열)
  ```json
  [
    {
      "id": 1,
      "email": "user1@example.com",
      "name": "사용자1",
      "username": "user1"
    },
    ...
  ]
  ```

#### 회원가입
- **URL**: `/api/members/signup`
- **Method**: `POST`
- **인증 필요**: 없음
- **요청 본문**:
  ```json
  {
    "email": "user@example.com",
    "name": "홍길동",
    "username": "honggildong",
    "password": "password123"
  }
  ```
- **응답**:
  - 성공: `200 OK` + 저장된 회원 정보
  ```json
  {
    "id": 1,
    "email": "user@example.com",
    "name": "홍길동",
    "username": "honggildong"
  }
  ```
  - 실패: `400 Bad Request` + 오류 메시지
  ```json
  {
    "message": "이미 존재하는 이메일입니다."
  }
  ```

#### 로그인
- **URL**: `/api/members/login`
- **Method**: `POST`
- **인증 필요**: 없음
- **요청 본문**:
  ```json
  {
    "email": "user@example.com",
    "password": "password123"
  }
  ```
- **응답**:
  - 성공: `200 OK` + 회원 정보 (비밀번호 제외)
  ```json
  {
    "id": 1,
    "email": "user@example.com",
    "name": "홍길동",
    "username": "honggildong"
  }
  ```
  - 실패: `400 Bad Request` + 오류 메시지
  ```json
  {
    "message": "이메일 또는 비밀번호가 일치하지 않습니다."
  }
  ```

### 게시글 API

#### 게시글 목록 조회
- **URL**: `/api/posts`
- **Method**: `GET`
- **인증 필요**: 없음
- **응답**:
  - 성공: `200 OK` + 게시글 목록 (배열)
  ```json
  [
    {
      "id": 1,
      "title": "첫 번째 게시글",
      "content": "게시글 내용입니다.",
      "author": "홍길동",
      "createdAt": "2025-06-15T10:30:00",
      "views": 10,
      "comments": [...]
    },
    ...
  ]
  ```

#### 게시글 상세 조회
- **URL**: `/api/posts/{id}`
- **Method**: `GET`
- **인증 필요**: 없음
- **URL 파라미터**: `id` - 게시글 ID
- **응답**:
  - 성공: `200 OK` + 게시글 상세 정보
  ```json
  {
    "id": 1,
    "title": "첫 번째 게시글",
    "content": "게시글 내용입니다.",
    "author": "홍길동",
    "createdAt": "2025-06-15T10:30:00",
    "views": 10,
    "comments": [
      {
        "id": 1,
        "content": "댓글 내용입니다.",
        "author": "댓글작성자",
        "createdAt": "2025-06-15T11:00:00"
      }
    ]
  }
  ```
  - 실패: `400 Bad Request` + 오류 메시지
  ```json
  {
    "message": "게시글을 찾을 수 없습니다."
  }
  ```

#### 게시글 작성
- **URL**: `/api/posts`
- **Method**: `POST`
- **인증 필요**: 없음 (실제 서비스에서는 인증 필요)
- **요청 본문**:
  ```json
  {
    "title": "새 게시글 제목",
    "content": "새 게시글 내용",
    "author": "작성자"
  }
  ```
- **응답**:
  - 성공: `200 OK` + 저장된 게시글 정보
  ```json
  {
    "id": 2,
    "title": "새 게시글 제목",
    "content": "새 게시글 내용",
    "author": "작성자",
    "createdAt": "2025-06-16T14:00:00",
    "views": 0,
    "comments": []
  }
  ```

#### 게시글 수정
- **URL**: `/api/posts/{id}?author={author}`
- **Method**: `PUT`
- **인증 필요**: 없음 (실제 서비스에서는 인증 필요)
- **URL 파라미터**: 
  - `id`: 게시글 ID
  - `author`: 게시글 작성자 (권한 확인용)
- **요청 본문**:
  ```json
  {
    "title": "수정된 게시글 제목",
    "content": "수정된 게시글 내용"
  }
  ```
- **응답**:
  - 성공: `200 OK` + 수정된 게시글 정보
  - 실패: `400 Bad Request` + 오류 메시지
  ```json
  {
    "message": "게시글을 찾을 수 없거나 수정 권한이 없습니다."
  }
  ```

#### 게시글 삭제
- **URL**: `/api/posts/{id}?author={author}`
- **Method**: `DELETE`
- **인증 필요**: 없음 (실제 서비스에서는 인증 필요)
- **URL 파라미터**: 
  - `id`: 게시글 ID
  - `author`: 게시글 작성자 (권한 확인용)
- **응답**:
  - 성공: `200 OK`
  - 실패: `400 Bad Request` + 오류 메시지
  ```json
  {
    "message": "게시글을 찾을 수 없거나 삭제 권한이 없습니다."
  }
  ```

### 댓글 API

#### 특정 게시글의 댓글 조회
- **URL**: `/api/comments/post/{postId}`
- **Method**: `GET`
- **인증 필요**: 없음
- **URL 파라미터**: `postId` - 게시글 ID
- **응답**:
  - 성공: `200 OK` + 댓글 목록 (배열)
  ```json
  [
    {
      "id": 1,
      "content": "댓글 내용입니다.",
      "author": "댓글작성자",
      "createdAt": "2025-06-15T11:00:00"
    },
    ...
  ]
  ```

#### 댓글 작성
- **URL**: `/api/comments/post/{postId}`
- **Method**: `POST`
- **인증 필요**: 없음 (실제 서비스에서는 인증 필요)
- **URL 파라미터**: `postId` - 게시글 ID
- **요청 본문**:
  ```json
  {
    "content": "새 댓글 내용",
    "author": "댓글작성자"
  }
  ```
- **응답**:
  - 성공: `200 OK` + 저장된 댓글 정보
  ```json
  {
    "id": 2,
    "content": "새 댓글 내용",
    "author": "댓글작성자",
    "createdAt": "2025-06-16T15:00:00"
  }
  ```
  - 실패: `400 Bad Request` + 오류 메시지
  ```json
  {
    "message": "게시글을 찾을 수 없습니다."
  }
  ```

#### 댓글 삭제
- **URL**: `/api/comments/{commentId}?author={author}`
- **Method**: `DELETE`
- **인증 필요**: 없음 (실제 서비스에서는 인증 필요)
- **URL 파라미터**: 
  - `commentId`: 댓글 ID
  - `author`: 댓글 작성자 (권한 확인용)
- **응답**:
  - 성공: `200 OK`
  - 실패: `400 Bad Request` + 오류 메시지
  ```json
  {
    "message": "댓글을 찾을 수 없거나 삭제 권한이 없습니다."
  }
  ```

---

## 데이터베이스 스키마

### Member 테이블
```sql
CREATE TABLE member (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  name VARCHAR(100) NOT NULL,
  username VARCHAR(100) NOT NULL
) CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
```

### Post 테이블
```sql
CREATE TABLE post (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  content TEXT NOT NULL,
  author VARCHAR(100) NOT NULL,
  created_at DATETIME NOT NULL,
  views INT DEFAULT 0
) CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
```

### Comment 테이블
```sql
CREATE TABLE comment (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  content TEXT NOT NULL,
  author VARCHAR(100) NOT NULL,
  created_at DATETIME NOT NULL,
  post_id BIGINT NOT NULL,
  FOREIGN KEY (post_id) REFERENCES post(id) ON DELETE CASCADE
) CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
```
