-- 회원
DROP TABLE IF EXISTS pms_member RESTRICT;

-- 자유게시판
DROP TABLE IF EXISTS pms_freeboard RESTRICT;

-- 지식인게시판
DROP TABLE IF EXISTS pms_doctorboard RESTRICT;

-- 공지사항
DROP TABLE IF EXISTS pms_noticeboard RESTRICT;

-- 버킷리스트
DROP TABLE IF EXISTS pms_bucket RESTRICT;

-- 댓글
DROP TABLE IF EXISTS pms_comment RESTRICT;

-- 상담신청
DROP TABLE IF EXISTS pms_counseling RESTRICT;

-- 출석체크
DROP TABLE IF EXISTS pms_attendance RESTRICT;

-- 좋아요
DROP TABLE IF EXISTS pms_like RESTRICT;

-- 의약품
DROP TABLE IF EXISTS pms_medicine RESTRICT;

-- 쪽지함
DROP TABLE IF EXISTS pms_mailbox RESTRICT;

-- 화분
DROP TABLE IF EXISTS pms_plant RESTRICT;

-- 의사
DROP TABLE IF EXISTS pms_doctor RESTRICT;

-- 게시판
DROP TABLE IF EXISTS pms_board RESTRICT;

-- 신고
DROP TABLE IF EXISTS pms_report RESTRICT;

-- 관리자
DROP TABLE IF EXISTS pms_admin RESTRICT;

-- 게시판신고승인
DROP TABLE IF EXISTS pms_report_accept RESTRICT;

-- 약품등록요청
DROP TABLE IF EXISTS pms_medicine_accept RESTRICT;

-- 회원
CREATE TABLE pms_member (
  member_no  INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  name       VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
  id         VARCHAR(50)  NOT NULL COMMENT '아이디', -- 아이디
  password   VARCHAR(100) NOT NULL COMMENT '비밀번호', -- 비밀번호
  birth      DATE         NULL     COMMENT '생일', -- 생일
  tel        VARCHAR(30)  NOT NULL COMMENT '연락처', -- 연락처
  email      VARCHAR(50)  NOT NULL COMMENT '이메일', -- 이메일
  photo      VARCHAR(255) NULL     COMMENT '사진', -- 사진
  sex        VARCHAR(20)  NOT NULL COMMENT '성별', -- 성별
  created_dt DATETIME     NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  point      INTEGER      NULL     DEFAULT 1000
   COMMENT '포인트' -- 포인트
)
COMMENT '회원';

-- 회원
ALTER TABLE pms_member
  ADD CONSTRAINT PK_pms_member -- 회원 기본키
    PRIMARY KEY (
      member_no -- 회원번호
    );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_pms_member
  ON pms_member ( -- 회원
    id ASC,    -- 아이디
    email ASC  -- 이메일
  );

-- 회원 인덱스
CREATE INDEX IX_pms_member
  ON pms_member( -- 회원
    id ASC -- 아이디
  );

ALTER TABLE pms_member
  MODIFY COLUMN member_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 자유게시판
CREATE TABLE pms_freeboard (
  free_no INTEGER NOT NULL COMMENT '자유게시글번호' -- 자유게시글번호
)
COMMENT '자유게시판';

-- 자유게시판
ALTER TABLE pms_freeboard
  ADD CONSTRAINT PK_pms_freeboard -- 자유게시판 기본키
    PRIMARY KEY (
      free_no -- 자유게시글번호
    );

-- 지식인게시판
CREATE TABLE pms_doctorboard (
  doctor_no INTEGER NOT NULL COMMENT '지식인게시글번호' -- 지식인게시글번호
)
COMMENT '지식인게시판';

-- 지식인게시판
ALTER TABLE pms_doctorboard
  ADD CONSTRAINT PK_pms_doctorboard -- 지식인게시판 기본키
    PRIMARY KEY (
      doctor_no -- 지식인게시글번호
    );

-- 공지사항
CREATE TABLE pms_noticeboard (
  notice_no INTEGER NOT NULL COMMENT '공지사항번호' -- 공지사항번호
)
COMMENT '공지사항';

-- 공지사항
ALTER TABLE pms_noticeboard
  ADD CONSTRAINT PK_pms_noticeboard -- 공지사항 기본키
    PRIMARY KEY (
      notice_no -- 공지사항번호
    );

-- 버킷리스트
CREATE TABLE pms_bucket (
  bucket_no    INTEGER      NOT NULL COMMENT '버킷리스트번호', -- 버킷리스트번호
  title        VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content      TEXT         NOT NULL COMMENT '내용', -- 내용
  complete     INTEGER      NULL     DEFAULT 0 COMMENT '달성여부', -- 달성여부
  created_dt   DATETIME     NULL     DEFAULT now() COMMENT '생성일', -- 생성일
  completed_dt DATETIME     NULL     COMMENT '달성일', -- 달성일
  member_no    INTEGER      NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '버킷리스트';

-- 버킷리스트
ALTER TABLE pms_bucket
  ADD CONSTRAINT PK_pms_bucket -- 버킷리스트 기본키
    PRIMARY KEY (
      bucket_no -- 버킷리스트번호
    );

ALTER TABLE pms_bucket
  MODIFY COLUMN bucket_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '버킷리스트번호';

-- 댓글
CREATE TABLE pms_comment (
  comment_no INTEGER  NOT NULL COMMENT '댓글번호', -- 댓글번호
  content    TEXT     NOT NULL COMMENT '내용', -- 내용
  created_dt DATETIME NOT NULL DEFAULT now() COMMENT '작성일', -- 작성일
  member_no  INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
  board_no   INTEGER  NOT NULL COMMENT '게시글번호' -- 게시글번호
)
COMMENT '댓글';

-- 댓글
ALTER TABLE pms_comment
  ADD CONSTRAINT PK_pms_comment -- 댓글 기본키
    PRIMARY KEY (
      comment_no -- 댓글번호
    );

ALTER TABLE pms_comment
  MODIFY COLUMN comment_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '댓글번호';

-- 상담신청
CREATE TABLE pms_counseling (
  counsel_no INTEGER NOT NULL COMMENT '상담신청번호', -- 상담신청번호
  doctor_no  INTEGER NOT NULL COMMENT '의사번호', -- 의사번호
  member_no  INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  disease    TEXT    NOT NULL COMMENT '질병여부', -- 질병여부
  content    TEXT    NOT NULL COMMENT '내용' -- 내용
)
COMMENT '상담신청';

-- 상담신청
ALTER TABLE pms_counseling
  ADD CONSTRAINT PK_pms_counseling -- 상담신청 기본키
    PRIMARY KEY (
      counsel_no -- 상담신청번호
    );

ALTER TABLE pms_counseling
  MODIFY COLUMN counsel_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '상담신청번호';

-- 출석체크
CREATE TABLE pms_attendance (
  attendance_no INTEGER  NOT NULL COMMENT '출석번호', -- 출석번호
  attendance_dt DATETIME NOT NULL DEFAULT now() COMMENT '출석일', -- 출석일
  member_no     INTEGER  NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '출석체크';

-- 출석체크
ALTER TABLE pms_attendance
  ADD CONSTRAINT PK_pms_attendance -- 출석체크 기본키
    PRIMARY KEY (
      attendance_no -- 출석번호
    );

ALTER TABLE pms_attendance
  MODIFY COLUMN attendance_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '출석번호';

-- 좋아요
CREATE TABLE pms_like (
  member_no INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  board_no  INTEGER NOT NULL COMMENT '게시글번호' -- 게시글번호
)
COMMENT '좋아요';

-- 좋아요
ALTER TABLE pms_like
  ADD CONSTRAINT PK_pms_like -- 좋아요 기본키
    PRIMARY KEY (
      member_no, -- 회원번호
      board_no   -- 게시글번호
    );

-- 의약품
CREATE TABLE pms_medicine (
  medicine_no INTEGER     NOT NULL COMMENT '약품번호', -- 약품번호
  title       VARCHAR(50) NOT NULL COMMENT '이름', -- 이름
  age         INTEGER     NOT NULL COMMENT '권장 연령', -- 권장 연령
  shape       TEXT        NOT NULL COMMENT '모양', -- 모양
  color       TEXT        NOT NULL COMMENT '색상', -- 색상
  effect      TEXT        NOT NULL COMMENT '효능' -- 효능
)
COMMENT '의약품';

-- 의약품
ALTER TABLE pms_medicine
  ADD CONSTRAINT PK_pms_medicine -- 의약품 기본키
    PRIMARY KEY (
      medicine_no -- 약품번호
    );

ALTER TABLE pms_medicine
  MODIFY COLUMN medicine_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '약품번호';

-- 쪽지함
CREATE TABLE pms_mailbox (
  mail_no   INTEGER      NOT NULL COMMENT '쪽지번호', -- 쪽지번호
  receiver  VARCHAR(50)  NOT NULL COMMENT '받는이', -- 받는이
  title     VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content   TEXT         NOT NULL COMMENT '내용', -- 내용
  sent_time DATETIME     NULL     DEFAULT now() COMMENT '보낸시간', -- 보낸시간
  member_no INTEGER      NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '쪽지함';

-- 쪽지함
ALTER TABLE pms_mailbox
  ADD CONSTRAINT PK_pms_mailbox -- 쪽지함 기본키
    PRIMARY KEY (
      mail_no -- 쪽지번호
    );

ALTER TABLE pms_mailbox
  MODIFY COLUMN mail_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '쪽지번호';

-- 화분
CREATE TABLE pms_plant (
  plant_no   INTEGER     NOT NULL COMMENT '화분번호', -- 화분번호
  level      INTEGER     NULL     DEFAULT 0 COMMENT '레벨', -- 레벨
  name       VARCHAR(50) NOT NULL COMMENT '이름', -- 이름
  experience INTEGER     NULL     DEFAULT 0 COMMENT '경험치', -- 경험치
  shape      TEXT        NOT NULL COMMENT '모양', -- 모양
  created_dt DATETIME    NULL     DEFAULT now() COMMENT '생성일', -- 생성일
  member_no  INTEGER     NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '화분';

-- 화분
ALTER TABLE pms_plant
  ADD CONSTRAINT PK_pms_plant -- 화분 기본키
    PRIMARY KEY (
      plant_no -- 화분번호
    );

ALTER TABLE pms_plant
  MODIFY COLUMN plant_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '화분번호';

-- 의사
CREATE TABLE pms_doctor (
  doctor_no    INTEGER      NOT NULL COMMENT '의사번호', -- 의사번호
  major        VARCHAR(50)  NOT NULL COMMENT '전문분야', -- 전문분야
  license      VARCHAR(255) NOT NULL COMMENT '의사면허', -- 의사면허
  homepage     VARCHAR(255) NULL     COMMENT '홈페이지', -- 홈페이지
  introduction TEXT         NOT NULL COMMENT '소개' -- 소개
)
COMMENT '의사';

-- 의사
ALTER TABLE pms_doctor
  ADD CONSTRAINT PK_pms_doctor -- 의사 기본키
    PRIMARY KEY (
      doctor_no -- 의사번호
    );

-- 의사 유니크 인덱스
CREATE UNIQUE INDEX UIX_pms_doctor
  ON pms_doctor ( -- 의사
    license ASC -- 의사면허
  );

ALTER TABLE pms_doctor
  MODIFY COLUMN doctor_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '의사번호';

-- 게시판
CREATE TABLE pms_board (
  board_no   INTEGER      NOT NULL COMMENT '게시글번호', -- 게시글번호
  member_no  INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  title      VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content    TEXT         NOT NULL COMMENT '내용', -- 내용
  created_dt DATETIME     NOT NULL DEFAULT now() COMMENT '등록일', -- 등록일
  view_cnt   INTEGER      NULL     DEFAULT 0 COMMENT '조회수' -- 조회수
)
COMMENT '게시판';

-- 게시판
ALTER TABLE pms_board
  ADD CONSTRAINT PK_pms_board -- 게시판 기본키
    PRIMARY KEY (
      board_no -- 게시글번호
    );

ALTER TABLE pms_board
  MODIFY COLUMN board_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시글번호';

-- 신고
CREATE TABLE pms_report (
  report_no INTEGER NOT NULL COMMENT '신고번호', -- 신고번호
  reason    TEXT    NOT NULL COMMENT '신고사유', -- 신고사유
  member_no INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  free_no   INTEGER NULL     COMMENT '자유게시글번호', -- 자유게시글번호
  doctor_no INTEGER NULL     COMMENT '지식인게시글번호' -- 지식인게시글번호
)
COMMENT '신고';

-- 신고
ALTER TABLE pms_report
  ADD CONSTRAINT PK_pms_report -- 신고 기본키
    PRIMARY KEY (
      report_no -- 신고번호
    );

ALTER TABLE pms_report
  MODIFY COLUMN report_no INTEGER NOT NULL AUTO_INCREMENT COMMENT '신고번호';

-- 관리자
CREATE TABLE pms_admin (
  admin_no INTEGER NOT NULL COMMENT '관리자번호' -- 관리자번호
)
COMMENT '관리자';

-- 관리자
ALTER TABLE pms_admin
  ADD CONSTRAINT PK_pms_admin -- 관리자 기본키
    PRIMARY KEY (
      admin_no -- 관리자번호
    );

-- 게시판신고승인
CREATE TABLE pms_report_accept (
  report_no INTEGER NOT NULL COMMENT '신고번호' -- 신고번호
)
COMMENT '게시판신고승인';

-- 게시판신고승인
ALTER TABLE pms_report_accept
  ADD CONSTRAINT PK_pms_report_accept -- 게시판신고승인 기본키
    PRIMARY KEY (
      report_no -- 신고번호
    );

-- 약품등록요청
CREATE TABLE pms_medicine_accept (
  medicine_no INTEGER NOT NULL COMMENT '약품번호' -- 약품번호
)
COMMENT '약품등록요청';

-- 약품등록요청
ALTER TABLE pms_medicine_accept
  ADD CONSTRAINT PK_pms_medicine_accept -- 약품등록요청 기본키
    PRIMARY KEY (
      medicine_no -- 약품번호
    );

-- 자유게시판
ALTER TABLE pms_freeboard
  ADD CONSTRAINT FK_pms_board_TO_pms_freeboard -- 게시판 -> 자유게시판
    FOREIGN KEY (
      free_no -- 자유게시글번호
    )
    REFERENCES pms_board ( -- 게시판
      board_no -- 게시글번호
    );

-- 지식인게시판
ALTER TABLE pms_doctorboard
  ADD CONSTRAINT FK_pms_board_TO_pms_doctorboard -- 게시판 -> 지식인게시판
    FOREIGN KEY (
      doctor_no -- 지식인게시글번호
    )
    REFERENCES pms_board ( -- 게시판
      board_no -- 게시글번호
    );

-- 공지사항
ALTER TABLE pms_noticeboard
  ADD CONSTRAINT FK_pms_board_TO_pms_noticeboard -- 게시판 -> 공지사항
    FOREIGN KEY (
      notice_no -- 공지사항번호
    )
    REFERENCES pms_board ( -- 게시판
      board_no -- 게시글번호
    );

-- 버킷리스트
ALTER TABLE pms_bucket
  ADD CONSTRAINT FK_pms_member_TO_pms_bucket -- 회원 -> 버킷리스트
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES pms_member ( -- 회원
      member_no -- 회원번호
    );

-- 댓글
ALTER TABLE pms_comment
  ADD CONSTRAINT FK_pms_member_TO_pms_comment -- 회원 -> 댓글
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES pms_member ( -- 회원
      member_no -- 회원번호
    );

-- 댓글
ALTER TABLE pms_comment
  ADD CONSTRAINT FK_pms_board_TO_pms_comment -- 게시판 -> 댓글
    FOREIGN KEY (
      board_no -- 게시글번호
    )
    REFERENCES pms_board ( -- 게시판
      board_no -- 게시글번호
    );

-- 상담신청
ALTER TABLE pms_counseling
  ADD CONSTRAINT FK_pms_member_TO_pms_counseling -- 회원 -> 상담신청
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES pms_member ( -- 회원
      member_no -- 회원번호
    );

-- 상담신청
ALTER TABLE pms_counseling
  ADD CONSTRAINT FK_pms_doctor_TO_pms_counseling -- 의사 -> 상담신청
    FOREIGN KEY (
      doctor_no -- 의사번호
    )
    REFERENCES pms_doctor ( -- 의사
      doctor_no -- 의사번호
    );

-- 출석체크
ALTER TABLE pms_attendance
  ADD CONSTRAINT FK_pms_member_TO_pms_attendance -- 회원 -> 출석체크
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES pms_member ( -- 회원
      member_no -- 회원번호
    );

-- 좋아요
ALTER TABLE pms_like
  ADD CONSTRAINT FK_pms_member_TO_pms_like -- 회원 -> 좋아요
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES pms_member ( -- 회원
      member_no -- 회원번호
    );

-- 좋아요
ALTER TABLE pms_like
  ADD CONSTRAINT FK_pms_board_TO_pms_like -- 게시판 -> 좋아요
    FOREIGN KEY (
      board_no -- 게시글번호
    )
    REFERENCES pms_board ( -- 게시판
      board_no -- 게시글번호
    );

-- 쪽지함
ALTER TABLE pms_mailbox
  ADD CONSTRAINT FK_pms_member_TO_pms_mailbox -- 회원 -> 쪽지함
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES pms_member ( -- 회원
      member_no -- 회원번호
    );

-- 화분
ALTER TABLE pms_plant
  ADD CONSTRAINT FK_pms_member_TO_pms_plant -- 회원 -> 화분
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES pms_member ( -- 회원
      member_no -- 회원번호
    );

-- 의사
ALTER TABLE pms_doctor
  ADD CONSTRAINT FK_pms_member_TO_pms_doctor -- 회원 -> 의사
    FOREIGN KEY (
      doctor_no -- 의사번호
    )
    REFERENCES pms_member ( -- 회원
      member_no -- 회원번호
    );

-- 게시판
ALTER TABLE pms_board
  ADD CONSTRAINT FK_pms_member_TO_pms_board -- 회원 -> 게시판
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES pms_member ( -- 회원
      member_no -- 회원번호
    );

-- 신고
ALTER TABLE pms_report
  ADD CONSTRAINT FK_pms_member_TO_pms_report -- 회원 -> 신고
    FOREIGN KEY (
      member_no -- 회원번호
    )
    REFERENCES pms_member ( -- 회원
      member_no -- 회원번호
    );

-- 신고
ALTER TABLE pms_report
  ADD CONSTRAINT FK_pms_freeboard_TO_pms_report -- 자유게시판 -> 신고
    FOREIGN KEY (
      free_no -- 자유게시글번호
    )
    REFERENCES pms_freeboard ( -- 자유게시판
      free_no -- 자유게시글번호
    );

-- 신고
ALTER TABLE pms_report
  ADD CONSTRAINT FK_pms_doctorboard_TO_pms_report -- 지식인게시판 -> 신고
    FOREIGN KEY (
      doctor_no -- 지식인게시글번호
    )
    REFERENCES pms_doctorboard ( -- 지식인게시판
      doctor_no -- 지식인게시글번호
    );

-- 관리자
ALTER TABLE pms_admin
  ADD CONSTRAINT FK_pms_member_TO_pms_admin -- 회원 -> 관리자
    FOREIGN KEY (
      admin_no -- 관리자번호
    )
    REFERENCES pms_member ( -- 회원
      member_no -- 회원번호
    );

-- 게시판신고승인
ALTER TABLE pms_report_accept
  ADD CONSTRAINT FK_pms_report_TO_pms_report_accept -- 신고 -> 게시판신고승인
    FOREIGN KEY (
      report_no -- 신고번호
    )
    REFERENCES pms_report ( -- 신고
      report_no -- 신고번호
    );

-- 약품등록요청
ALTER TABLE pms_medicine_accept
  ADD CONSTRAINT FK_pms_medicine_TO_pms_medicine_accept -- 의약품 -> 약품등록요청
    FOREIGN KEY (
      medicine_no -- 약품번호
    )
    REFERENCES pms_medicine ( -- 의약품
      medicine_no -- 약품번호
    );