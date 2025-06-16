-- 테이블이 없는 경우에만 생성하도록 기존 테이블 확인 로직 추가
CREATE TABLE IF NOT EXISTS post (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  content TEXT NOT NULL,
  author VARCHAR(100) NOT NULL,
  created_at DATETIME NOT NULL,
  views INT DEFAULT 0
) CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS comment (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  content TEXT NOT NULL,
  author VARCHAR(100) NOT NULL,
  created_at DATETIME NOT NULL,
  post_id BIGINT NOT NULL,
  FOREIGN KEY (post_id) REFERENCES post(id) ON DELETE CASCADE
) CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;
