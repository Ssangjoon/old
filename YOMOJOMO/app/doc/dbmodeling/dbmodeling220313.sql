-- 회원
DROP TABLE IF EXISTS gms_memb RESTRICT;

-- 메인카테고리2
DROP TABLE IF EXISTS gms_mcate2 RESTRICT;

-- 최근검색어
DROP TABLE IF EXISTS gms_keyword RESTRICT;

-- 소모임
DROP TABLE IF EXISTS gms_group RESTRICT;

-- 활동지역
DROP TABLE IF EXISTS gms_activelocal RESTRICT;

-- 모임목적
DROP TABLE IF EXISTS gms_purpose RESTRICT;

-- 직군
DROP TABLE IF EXISTS gms_itr_jobgroup RESTRICT;

-- 직군세부
DROP TABLE IF EXISTS gms_itr_jobgroup_detail RESTRICT;

-- 모임태그
DROP TABLE IF EXISTS gms_grouptag RESTRICT;

-- 모임가입신청서
DROP TABLE IF EXISTS gms_applyform RESTRICT;

-- 모임게시글
DROP TABLE IF EXISTS gms_board RESTRICT;

-- 모임내부 게시글 댓글
DROP TABLE IF EXISTS gms_comment RESTRICT;

-- 모임게시글사진
DROP TABLE IF EXISTS gms_photo RESTRICT;

-- 모임내부 일정
DROP TABLE IF EXISTS gms_calendar RESTRICT;

-- 모임내부 첨부파일
DROP TABLE IF EXISTS gms_file RESTRICT;

-- 모임맴버
DROP TABLE IF EXISTS gms_join_memb RESTRICT;

-- 모금
DROP TABLE IF EXISTS gms_accounting RESTRICT;

-- 맴버장소입력
DROP TABLE IF EXISTS gms_select_midpoint RESTRICT;

-- 고객센터 자주하는 질문
DROP TABLE IF EXISTS gms_faq RESTRICT;

-- 고객센터 공지사항
DROP TABLE IF EXISTS gms_notice RESTRICT;

-- 고객센터 문의하기
DROP TABLE IF EXISTS gms_notice_question RESTRICT;

-- 관심활동지역
DROP TABLE IF EXISTS gms_final_activelocal RESTRICT;

-- 관심직군
DROP TABLE IF EXISTS gms_final_job RESTRICT;

-- 관심모임목적
DROP TABLE IF EXISTS gms_final_purpose RESTRICT;

-- 모금유형
DROP TABLE IF EXISTS gms_accounting_cate RESTRICT;

-- 모금납부내역
DROP TABLE IF EXISTS gms_accounting_status RESTRICT;

-- 모임게시글스크랩
DROP TABLE IF EXISTS gms_scrap RESTRICT;

-- 질문유형
DROP TABLE IF EXISTS gms_query_cate RESTRICT;

-- SNS계정
DROP TABLE IF EXISTS gms_final_SNS RESTRICT;

-- SNS
DROP TABLE IF EXISTS gms_SNS RESTRICT;

-- 최종모임장소
DROP TABLE IF EXISTS gms_destination RESTRICT;

-- 모임등급유형
DROP TABLE IF EXISTS gms_memb_grade RESTRICT;

-- 리쿠르팅게시판
DROP TABLE IF EXISTS gms_recruit RESTRICT;

-- 회원
CREATE TABLE gms_memb (
  membno      INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  name        VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
  pwd         VARCHAR(100) NOT NULL COMMENT '비밀번호', -- 비밀번호
  email       VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
  tel         VARCHAR(30)  NOT NULL COMMENT '전화번호', -- 전화번호
  postno      VARCHAR(10)  NOT NULL COMMENT '우편번호', -- 우편번호
  baseaddr    VARCHAR(255) NOT NULL COMMENT '기본주소', -- 기본주소
  addr        VARCHAR(255) NOT NULL COMMENT '상세주소', -- 상세주소
  type        VARCHAR(50)  NOT NULL COMMENT '유형', -- 유형
  unsubscribe BOOLEAN      NOT NULL DEFAULT false COMMENT '탈퇴여부' -- 탈퇴여부
)
COMMENT '회원';

-- 회원
ALTER TABLE gms_memb
  ADD CONSTRAINT PK_gms_memb -- 회원 기본키
    PRIMARY KEY (
      membno -- 회원번호
    );

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_gms_memb
  ON gms_memb ( -- 회원
    email ASC, -- 이메일
    tel ASC    -- 전화번호
  );

ALTER TABLE gms_memb
  MODIFY COLUMN membno INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

ALTER TABLE gms_memb
  AUTO_INCREMENT = 1;

-- 메인카테고리2
CREATE TABLE gms_mcate2 (
  mcate2no INTEGER     NOT NULL COMMENT '메인카테고리2번호', -- 메인카테고리2번호
  name     VARCHAR(50) NOT NULL COMMENT '카테고리목록명' -- 카테고리목록명
)
COMMENT '메인카테고리2';

-- 메인카테고리2
ALTER TABLE gms_mcate2
  ADD CONSTRAINT PK_gms_mcate2 -- 메인카테고리2 기본키
    PRIMARY KEY (
      mcate2no -- 메인카테고리2번호
    );

-- 메인카테고리2 유니크 인덱스
CREATE UNIQUE INDEX UIX_gms_mcate2
  ON gms_mcate2 ( -- 메인카테고리2
    name ASC -- 카테고리목록명
  );

ALTER TABLE gms_mcate2
  MODIFY COLUMN mcate2no INTEGER NOT NULL AUTO_INCREMENT COMMENT '메인카테고리2번호';

ALTER TABLE gms_mcate2
  AUTO_INCREMENT = 1;

-- 최근검색어
CREATE TABLE gms_keyword (
  kno        INTEGER     NOT NULL COMMENT '최근검색어 번호', -- 최근검색어 번호
  searchname VARCHAR(50) NOT NULL COMMENT '최근검색어명', -- 최근검색어명
  membno     INTEGER     NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '최근검색어';

-- 최근검색어
ALTER TABLE gms_keyword
  ADD CONSTRAINT PK_gms_keyword -- 최근검색어 기본키
    PRIMARY KEY (
      kno -- 최근검색어 번호
    );

ALTER TABLE gms_keyword
  MODIFY COLUMN kno INTEGER NOT NULL AUTO_INCREMENT COMMENT '최근검색어 번호';

ALTER TABLE gms_keyword
  AUTO_INCREMENT = 1;

-- 소모임
CREATE TABLE gms_group (
  gno        INTEGER      NOT NULL COMMENT '모임번호', -- 모임번호
  itrlocalno INTEGER      NOT NULL COMMENT '활동지역번호', -- 활동지역번호
  fileno     INTEGER      NOT NULL COMMENT '모임목적번호', -- 모임목적번호
  name       VARCHAR(50)  NOT NULL COMMENT '모임이름', -- 모임이름
  cdate      DATE         NOT NULL COMMENT '모임생성일', -- 모임생성일
  logo       VARCHAR(255) NULL     COMMENT '모임로고사진', -- 모임로고사진
  intro      LONGTEXT     NOT NULL COMMENT '모임안내문구', -- 모임안내문구
  mcnt       INTEGER      NOT NULL COMMENT '모임인원수', -- 모임인원수
  fee        INTEGER      NOT NULL COMMENT '회비', -- 회비
  vcnt       INTEGER      NOT NULL COMMENT '방문자수' -- 방문자수
)
COMMENT '소모임';

-- 소모임
ALTER TABLE gms_group
  ADD CONSTRAINT PK_gms_group -- 소모임 기본키
    PRIMARY KEY (
      gno -- 모임번호
    );

-- 소모임 인덱스
CREATE INDEX IX_gms_group
  ON gms_group( -- 소모임
    name ASC -- 모임이름
  );

ALTER TABLE gms_group
  MODIFY COLUMN gno INTEGER NOT NULL AUTO_INCREMENT COMMENT '모임번호';

ALTER TABLE gms_group
  AUTO_INCREMENT = 1;

-- 활동지역
CREATE TABLE gms_activelocal (
  itrlocalno INTEGER     NOT NULL COMMENT '활동지역번호', -- 활동지역번호
  name       VARCHAR(50) NOT NULL COMMENT '활동지역명' -- 활동지역명
)
COMMENT '활동지역';

-- 활동지역
ALTER TABLE gms_activelocal
  ADD CONSTRAINT PK_gms_activelocal -- 활동지역 기본키
    PRIMARY KEY (
      itrlocalno -- 활동지역번호
    );

-- 활동지역 유니크 인덱스
CREATE UNIQUE INDEX UIX_gms_activelocal
  ON gms_activelocal ( -- 활동지역
    name ASC -- 활동지역명
  );

ALTER TABLE gms_activelocal
  MODIFY COLUMN itrlocalno INTEGER NOT NULL AUTO_INCREMENT COMMENT '활동지역번호';

ALTER TABLE gms_activelocal
  AUTO_INCREMENT = 1;

-- 모임목적
CREATE TABLE gms_purpose (
  fileno INTEGER     NOT NULL COMMENT '모임목적번호', -- 모임목적번호
  name   VARCHAR(50) NOT NULL COMMENT '모임목적명' -- 모임목적명
)
COMMENT '모임목적';

-- 모임목적
ALTER TABLE gms_purpose
  ADD CONSTRAINT PK_gms_purpose -- 모임목적 기본키
    PRIMARY KEY (
      fileno -- 모임목적번호
    );

-- 모임목적 유니크 인덱스
CREATE UNIQUE INDEX UIX_gms_purpose
  ON gms_purpose ( -- 모임목적
    name ASC -- 모임목적명
  );

ALTER TABLE gms_purpose
  MODIFY COLUMN fileno INTEGER NOT NULL AUTO_INCREMENT COMMENT '모임목적번호';

ALTER TABLE gms_purpose
  AUTO_INCREMENT = 1;

-- 직군
CREATE TABLE gms_itr_jobgroup (
  ijgno INTEGER     NOT NULL COMMENT '직군번호', -- 직군번호
  name  VARCHAR(50) NOT NULL COMMENT '직군명' -- 직군명
)
COMMENT '직군';

-- 직군
ALTER TABLE gms_itr_jobgroup
  ADD CONSTRAINT PK_gms_itr_jobgroup -- 직군 기본키
    PRIMARY KEY (
      ijgno -- 직군번호
    );

-- 직군 유니크 인덱스
CREATE UNIQUE INDEX UIX_gms_itr_jobgroup
  ON gms_itr_jobgroup ( -- 직군
    name ASC -- 직군명
  );

ALTER TABLE gms_itr_jobgroup
  MODIFY COLUMN ijgno INTEGER NOT NULL AUTO_INCREMENT COMMENT '직군번호';

ALTER TABLE gms_itr_jobgroup
  AUTO_INCREMENT = 1;

-- 직군세부
CREATE TABLE gms_itr_jobgroup_detail (
  ijgdno INTEGER     NOT NULL COMMENT '관심직군세부번호', -- 관심직군세부번호
  name   VARCHAR(50) NOT NULL COMMENT '관심직군세부명', -- 관심직군세부명
  ijgno  INTEGER     NOT NULL COMMENT '직군번호' -- 직군번호
)
COMMENT '직군세부';

-- 직군세부
ALTER TABLE gms_itr_jobgroup_detail
  ADD CONSTRAINT PK_gms_itr_jobgroup_detail -- 직군세부 기본키
    PRIMARY KEY (
      ijgdno -- 관심직군세부번호
    );

-- 직군세부 유니크 인덱스
CREATE UNIQUE INDEX UIX_gms_itr_jobgroup_detail
  ON gms_itr_jobgroup_detail ( -- 직군세부
    name ASC -- 관심직군세부명
  );

ALTER TABLE gms_itr_jobgroup_detail
  MODIFY COLUMN ijgdno INTEGER NOT NULL AUTO_INCREMENT COMMENT '관심직군세부번호';

ALTER TABLE gms_itr_jobgroup_detail
  AUTO_INCREMENT = 1;

-- 모임태그
CREATE TABLE gms_grouptag (
  tagno INTEGER     NOT NULL COMMENT '태그번호', -- 태그번호
  gno   INTEGER     NOT NULL COMMENT '모임번호', -- 모임번호
  tag   VARCHAR(50) NOT NULL COMMENT '태그이름' -- 태그이름
)
COMMENT '모임태그';

-- 모임태그
ALTER TABLE gms_grouptag
  ADD CONSTRAINT PK_gms_grouptag -- 모임태그 기본키
    PRIMARY KEY (
      tagno -- 태그번호
    );

-- 모임태그 유니크 인덱스
CREATE UNIQUE INDEX UIX_gms_grouptag
  ON gms_grouptag ( -- 모임태그
    tag ASC -- 태그이름
  );

ALTER TABLE gms_grouptag
  MODIFY COLUMN tagno INTEGER NOT NULL AUTO_INCREMENT COMMENT '태그번호';

ALTER TABLE gms_grouptag
  AUTO_INCREMENT = 1;

-- 모임가입신청서
CREATE TABLE gms_applyform (
  applno  INTEGER  NOT NULL COMMENT '모임가입신청서 번호', -- 모임가입신청서 번호
  gno     INTEGER  NOT NULL COMMENT '모임번호', -- 모임번호
  membno  INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
  content LONGTEXT NOT NULL COMMENT '내용', -- 내용
  appldt  DATE     NOT NULL COMMENT '신청일' -- 신청일
)
COMMENT '모임가입신청서';

-- 모임가입신청서
ALTER TABLE gms_applyform
  ADD CONSTRAINT PK_gms_applyform -- 모임가입신청서 기본키
    PRIMARY KEY (
      applno -- 모임가입신청서 번호
    );

-- 모임가입신청서 유니크 인덱스
CREATE UNIQUE INDEX UIX_gms_applyform
  ON gms_applyform ( -- 모임가입신청서
    gno ASC,    -- 모임번호
    membno ASC  -- 회원번호
  );

ALTER TABLE gms_applyform
  MODIFY COLUMN applno INTEGER NOT NULL AUTO_INCREMENT COMMENT '모임가입신청서 번호';

ALTER TABLE gms_applyform
  AUTO_INCREMENT = 1;

-- 모임게시글
CREATE TABLE gms_board (
  bno     INTEGER  NOT NULL COMMENT '게시글번호', -- 게시글번호
  gno     INTEGER  NOT NULL COMMENT '모임번호', -- 모임번호
  membno  INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
  dt      DATE     NOT NULL COMMENT '작성일시', -- 작성일시
  content LONGTEXT NOT NULL COMMENT '내용', -- 내용
  viewcnt INTEGER  NOT NULL COMMENT '조회수' -- 조회수
)
COMMENT '모임게시글';

-- 모임게시글
ALTER TABLE gms_board
  ADD CONSTRAINT PK_gms_board -- 모임게시글 기본키
    PRIMARY KEY (
      bno -- 게시글번호
    );

-- 모임게시글 인덱스
CREATE INDEX IX_gms_board
  ON gms_board( -- 모임게시글
  );

ALTER TABLE gms_board
  MODIFY COLUMN bno INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시글번호';

ALTER TABLE gms_board
  AUTO_INCREMENT = 1;

-- 모임내부 게시글 댓글
CREATE TABLE gms_comment (
  cmtno   INTEGER  NOT NULL COMMENT '댓글번호', -- 댓글번호
  bno     INTEGER  NOT NULL COMMENT '게시글번호', -- 게시글번호
  content LONGTEXT NOT NULL COMMENT '댓글내용 ', -- 댓글내용 
  membno  INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
  gno     INTEGER  NOT NULL COMMENT '모임번호', -- 모임번호
  regdt   DATE     NOT NULL COMMENT '등록일' -- 등록일
)
COMMENT '모임내부 게시글 댓글';

-- 모임내부 게시글 댓글
ALTER TABLE gms_comment
  ADD CONSTRAINT PK_gms_comment -- 모임내부 게시글 댓글 기본키
    PRIMARY KEY (
      cmtno -- 댓글번호
    );

ALTER TABLE gms_comment
  MODIFY COLUMN cmtno INTEGER NOT NULL AUTO_INCREMENT COMMENT '댓글번호';

ALTER TABLE gms_comment
  AUTO_INCREMENT = 1;

-- 모임게시글사진
CREATE TABLE gms_photo (
  gpno INTEGER      NOT NULL COMMENT '사진번호', -- 사진번호
  bno  INTEGER      NOT NULL COMMENT '게시글번호', -- 게시글번호
  path VARCHAR(255) NOT NULL COMMENT '사진경로' -- 사진경로
)
COMMENT '모임게시글사진';

-- 모임게시글사진
ALTER TABLE gms_photo
  ADD CONSTRAINT PK_gms_photo -- 모임게시글사진 기본키
    PRIMARY KEY (
      gpno -- 사진번호
    );

ALTER TABLE gms_photo
  MODIFY COLUMN gpno INTEGER NOT NULL AUTO_INCREMENT COMMENT '사진번호';

ALTER TABLE gms_photo
  AUTO_INCREMENT = 1;

-- 모임내부 일정
CREATE TABLE gms_calendar (
  calno     INTEGER      NOT NULL COMMENT '일정번호', -- 일정번호
  name      VARCHAR(255) NOT NULL COMMENT '약속이름', -- 약속이름
  content   LONGTEXT     NOT NULL COMMENT '약속내용', -- 약속내용
  resulturl VARCHAR(255) NULL     COMMENT '최종장소URL', -- 최종장소URL
  gno       INTEGER      NOT NULL COMMENT '모임번호' -- 모임번호
)
COMMENT '모임내부 일정';

-- 모임내부 일정
ALTER TABLE gms_calendar
  ADD CONSTRAINT PK_gms_calendar -- 모임내부 일정 기본키
    PRIMARY KEY (
      calno -- 일정번호
    );

ALTER TABLE gms_calendar
  MODIFY COLUMN calno INTEGER NOT NULL AUTO_INCREMENT COMMENT '일정번호';

ALTER TABLE gms_calendar
  AUTO_INCREMENT = 1;

-- 모임내부 첨부파일
CREATE TABLE gms_file (
  fileno INTEGER     NOT NULL COMMENT '첨부파일번호', -- 첨부파일번호
  bno    INTEGER     NOT NULL COMMENT '게시글번호', -- 게시글번호
  name   VARCHAR(50) NOT NULL COMMENT '첨부파일명' -- 첨부파일명
)
COMMENT '모임내부 첨부파일';

-- 모임내부 첨부파일
ALTER TABLE gms_file
  ADD CONSTRAINT PK_gms_file -- 모임내부 첨부파일 기본키
    PRIMARY KEY (
      fileno -- 첨부파일번호
    );

-- 모임내부 첨부파일 유니크 인덱스
CREATE UNIQUE INDEX UIX_gms_file
  ON gms_file ( -- 모임내부 첨부파일
    name ASC -- 첨부파일명
  );

-- 모임내부 첨부파일 인덱스
CREATE INDEX IX_gms_file
  ON gms_file( -- 모임내부 첨부파일
    name ASC -- 첨부파일명
  );

ALTER TABLE gms_file
  MODIFY COLUMN fileno INTEGER NOT NULL AUTO_INCREMENT COMMENT '첨부파일번호';

ALTER TABLE gms_file
  AUTO_INCREMENT = 1;

-- 모임맴버
CREATE TABLE gms_join_memb (
  membno  INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  gno     INTEGER NOT NULL COMMENT '모임번호', -- 모임번호
  gradeno INTEGER NOT NULL COMMENT '모임등급유형번호' -- 모임등급유형번호
)
COMMENT '모임맴버';

-- 모임맴버
ALTER TABLE gms_join_memb
  ADD CONSTRAINT PK_gms_join_memb -- 모임맴버 기본키
    PRIMARY KEY (
      membno, -- 회원번호
      gno     -- 모임번호
    );

-- 모금
CREATE TABLE gms_accounting (
  actno     INTEGER      NOT NULL COMMENT '모금번호', -- 모금번호
  gno       INTEGER      NOT NULL COMMENT '모임번호', -- 모임번호
  actcateno INTEGER      NOT NULL COMMENT '모금유형번호', -- 모금유형번호
  title     VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  amount    INTEGER      NOT NULL DEFAULT 0 COMMENT '금액', -- 금액
  dt        DATE         NOT NULL COMMENT '등록일', -- 등록일
  status    BOOLEAN      NOT NULL COMMENT '상태' -- 상태
)
COMMENT '모금';

-- 모금
ALTER TABLE gms_accounting
  ADD CONSTRAINT PK_gms_accounting -- 모금 기본키
    PRIMARY KEY (
      actno -- 모금번호
    );

-- 모금 인덱스
CREATE INDEX IX_gms_accounting
  ON gms_accounting( -- 모금
    title ASC -- 제목
  );

ALTER TABLE gms_accounting
  MODIFY COLUMN actno INTEGER NOT NULL AUTO_INCREMENT COMMENT '모금번호';

ALTER TABLE gms_accounting
  AUTO_INCREMENT = 1;

-- 맴버장소입력
CREATE TABLE gms_select_midpoint (
  smno   INTEGER     NOT NULL COMMENT '맴버장소입력번호', -- 맴버장소입력번호
  lat    VARCHAR(50) NOT NULL COMMENT '위도', -- 위도
  lng    VARCHAR(50) NOT NULL COMMENT '경도', -- 경도
  membno INTEGER     NOT NULL COMMENT '회원번호', -- 회원번호
  gno    INTEGER     NOT NULL COMMENT '모임번호' -- 모임번호
)
COMMENT '맴버장소입력';

-- 맴버장소입력
ALTER TABLE gms_select_midpoint
  ADD CONSTRAINT PK_gms_select_midpoint -- 맴버장소입력 기본키
    PRIMARY KEY (
      smno -- 맴버장소입력번호
    );

ALTER TABLE gms_select_midpoint
  MODIFY COLUMN smno INTEGER NOT NULL AUTO_INCREMENT COMMENT '맴버장소입력번호';

ALTER TABLE gms_select_midpoint
  AUTO_INCREMENT = 1;

-- 고객센터 자주하는 질문
CREATE TABLE gms_faq (
  faqno    INTEGER      NOT NULL COMMENT '자주하는질문번호', -- 자주하는질문번호
  mcate2no INTEGER      NOT NULL COMMENT '메인카테고리2번호', -- 메인카테고리2번호
  queryno  INTEGER      NOT NULL COMMENT '질문유형번호', -- 질문유형번호
  title    VARCHAR(255) NOT NULL COMMENT '질문', -- 질문
  content  LONGTEXT     NOT NULL COMMENT '답변', -- 답변
  path     VARCHAR(255) NULL     COMMENT '사진파일경로', -- 사진파일경로
  appdt    DATE         NOT NULL COMMENT '등록일' -- 등록일
)
COMMENT '고객센터 자주하는 질문';

-- 고객센터 자주하는 질문
ALTER TABLE gms_faq
  ADD CONSTRAINT PK_gms_faq -- 고객센터 자주하는 질문 기본키
    PRIMARY KEY (
      faqno -- 자주하는질문번호
    );

ALTER TABLE gms_faq
  MODIFY COLUMN faqno INTEGER NOT NULL AUTO_INCREMENT COMMENT '자주하는질문번호';

ALTER TABLE gms_faq
  AUTO_INCREMENT = 1;

-- 고객센터 공지사항
CREATE TABLE gms_notice (
  noticeno INTEGER      NOT NULL COMMENT '공지사항번호', -- 공지사항번호
  mcate2no INTEGER      NOT NULL COMMENT '메인카테고리2번호', -- 메인카테고리2번호
  cate     VARCHAR(50)  NOT NULL COMMENT '내용분류', -- 내용분류
  title    VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content  LONGTEXT     NOT NULL COMMENT '내용', -- 내용
  dt       DATE         NOT NULL COMMENT '등록일' -- 등록일
)
COMMENT '고객센터 공지사항';

-- 고객센터 공지사항
ALTER TABLE gms_notice
  ADD CONSTRAINT PK_gms_notice -- 고객센터 공지사항 기본키
    PRIMARY KEY (
      noticeno -- 공지사항번호
    );

ALTER TABLE gms_notice
  MODIFY COLUMN noticeno INTEGER NOT NULL AUTO_INCREMENT COMMENT '공지사항번호';

ALTER TABLE gms_notice
  AUTO_INCREMENT = 1;

-- 고객센터 문의하기
CREATE TABLE gms_notice_question (
  qno      INTEGER      NOT NULL COMMENT '문의번호', -- 문의번호
  mcate2no INTEGER      NOT NULL COMMENT '메인카테고리2번호', -- 메인카테고리2번호
  queryno  INTEGER      NOT NULL COMMENT '질문유형번호', -- 질문유형번호
  membno   INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  title    VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
  content  LONGTEXT     NOT NULL COMMENT '내용', -- 내용
  path     VARCHAR(255) NULL     COMMENT '첨부파일 경로', -- 첨부파일 경로
  querydt  DATE         NOT NULL COMMENT '문의일', -- 문의일
  answer   LONGTEXT     NULL     COMMENT '답변', -- 답변
  answerdt DATE         NULL     COMMENT '답변일' -- 답변일
)
COMMENT '고객센터 문의하기';

-- 고객센터 문의하기
ALTER TABLE gms_notice_question
  ADD CONSTRAINT PK_gms_notice_question -- 고객센터 문의하기 기본키
    PRIMARY KEY (
      qno -- 문의번호
    );

-- 고객센터 문의하기 인덱스
CREATE INDEX IX_gms_notice_question
  ON gms_notice_question( -- 고객센터 문의하기
    title ASC -- 제목
  );

ALTER TABLE gms_notice_question
  MODIFY COLUMN qno INTEGER NOT NULL AUTO_INCREMENT COMMENT '문의번호';

ALTER TABLE gms_notice_question
  AUTO_INCREMENT = 1;

-- 관심활동지역
CREATE TABLE gms_final_activelocal (
  membno     INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  itrlocalno INTEGER NOT NULL COMMENT '활동지역번호' -- 활동지역번호
)
COMMENT '관심활동지역';

-- 관심활동지역
ALTER TABLE gms_final_activelocal
  ADD CONSTRAINT PK_gms_final_activelocal -- 관심활동지역 기본키
    PRIMARY KEY (
      membno,     -- 회원번호
      itrlocalno  -- 활동지역번호
    );

-- 관심직군
CREATE TABLE gms_final_job (
  membno INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  ijgno  INTEGER NOT NULL COMMENT '직군번호' -- 직군번호
)
COMMENT '관심직군';

-- 관심직군
ALTER TABLE gms_final_job
  ADD CONSTRAINT PK_gms_final_job -- 관심직군 기본키
    PRIMARY KEY (
      membno, -- 회원번호
      ijgno   -- 직군번호
    );

-- 관심모임목적
CREATE TABLE gms_final_purpose (
  fileno INTEGER NOT NULL COMMENT '모임목적번호', -- 모임목적번호
  membno INTEGER NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '관심모임목적';

-- 관심모임목적
ALTER TABLE gms_final_purpose
  ADD CONSTRAINT PK_gms_final_purpose -- 관심모임목적 기본키
    PRIMARY KEY (
      fileno, -- 모임목적번호
      membno  -- 회원번호
    );

-- 모금유형
CREATE TABLE gms_accounting_cate (
  actcateno INTEGER     NOT NULL COMMENT '모금유형번호', -- 모금유형번호
  name      VARCHAR(50) NOT NULL COMMENT '모금유형명' -- 모금유형명
)
COMMENT '모금유형';

-- 모금유형
ALTER TABLE gms_accounting_cate
  ADD CONSTRAINT PK_gms_accounting_cate -- 모금유형 기본키
    PRIMARY KEY (
      actcateno -- 모금유형번호
    );

ALTER TABLE gms_accounting_cate
  MODIFY COLUMN actcateno INTEGER NOT NULL AUTO_INCREMENT COMMENT '모금유형번호';

ALTER TABLE gms_accounting_cate
  AUTO_INCREMENT = 1;

-- 모금납부내역
CREATE TABLE gms_accounting_status (
  actno  INTEGER NOT NULL COMMENT '모금번호', -- 모금번호
  gno    INTEGER NOT NULL COMMENT '모임번호', -- 모임번호
  membno INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  paydt  DATE    NOT NULL COMMENT '납부일' -- 납부일
)
COMMENT '모금납부내역';

-- 모금납부내역
ALTER TABLE gms_accounting_status
  ADD CONSTRAINT PK_gms_accounting_status -- 모금납부내역 기본키
    PRIMARY KEY (
      actno,  -- 모금번호
      gno,    -- 모임번호
      membno  -- 회원번호
    );

-- 모임게시글스크랩
CREATE TABLE gms_scrap (
  gno    INTEGER NOT NULL COMMENT '모임번호', -- 모임번호
  membno INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
  bno    INTEGER NOT NULL COMMENT '게시글번호' -- 게시글번호
)
COMMENT '모임게시글스크랩';

-- 모임게시글스크랩
ALTER TABLE gms_scrap
  ADD CONSTRAINT PK_gms_scrap -- 모임게시글스크랩 기본키
    PRIMARY KEY (
      gno,    -- 모임번호
      membno, -- 회원번호
      bno     -- 게시글번호
    );

-- 질문유형
CREATE TABLE gms_query_cate (
  queryno INTEGER     NOT NULL COMMENT '질문유형번호', -- 질문유형번호
  name    VARCHAR(50) NOT NULL COMMENT '질문유형명' -- 질문유형명
)
COMMENT '질문유형';

-- 질문유형
ALTER TABLE gms_query_cate
  ADD CONSTRAINT PK_gms_query_cate -- 질문유형 기본키
    PRIMARY KEY (
      queryno -- 질문유형번호
    );

ALTER TABLE gms_query_cate
  MODIFY COLUMN queryno INTEGER NOT NULL AUTO_INCREMENT COMMENT '질문유형번호';

ALTER TABLE gms_query_cate
  AUTO_INCREMENT = 1;

-- SNS계정
CREATE TABLE gms_final_SNS (
  membno   INTEGER     NOT NULL COMMENT '회원번호', -- 회원번호
  SNSno    INTEGER     NOT NULL COMMENT 'SNS번호', -- SNS번호
  SNSemail VARCHAR(40) NOT NULL COMMENT '이메일' -- 이메일
)
COMMENT 'SNS계정';

-- SNS계정
ALTER TABLE gms_final_SNS
  ADD CONSTRAINT PK_gms_final_SNS -- SNS계정 기본키
    PRIMARY KEY (
      membno -- 회원번호
    );

-- SNS
CREATE TABLE gms_SNS (
  SNSno INTEGER     NOT NULL COMMENT 'SNS번호', -- SNS번호
  name  VARCHAR(50) NOT NULL COMMENT 'SNS명' -- SNS명
)
COMMENT 'SNS';

-- SNS
ALTER TABLE gms_SNS
  ADD CONSTRAINT PK_gms_SNS -- SNS 기본키
    PRIMARY KEY (
      SNSno -- SNS번호
    );

ALTER TABLE gms_SNS
  MODIFY COLUMN SNSno INTEGER NOT NULL AUTO_INCREMENT COMMENT 'SNS번호';

ALTER TABLE gms_SNS
  AUTO_INCREMENT = 1;

-- 최종모임장소
CREATE TABLE gms_destination (
  destno      INTEGER     NOT NULL COMMENT '최종모임장소번호', -- 최종모임장소번호
  flat        VARCHAR(50) NOT NULL COMMENT '위도', -- 위도
  flng        VARCHAR(50) NOT NULL COMMENT '경도', -- 경도
  storename   VARCHAR(50) NOT NULL COMMENT '매장명', -- 매장명
  location_id VARCHAR(50) NOT NULL COMMENT '장소아이디', -- 장소아이디
  regdt       DATE        NOT NULL COMMENT '등록일', -- 등록일
  cnt         INTEGER     NOT NULL COMMENT '선택횟수' -- 선택횟수
)
COMMENT '최종모임장소';

-- 최종모임장소
ALTER TABLE gms_destination
  ADD CONSTRAINT PK_gms_destination -- 최종모임장소 기본키
    PRIMARY KEY (
      destno -- 최종모임장소번호
    );

ALTER TABLE gms_destination
  MODIFY COLUMN destno INTEGER NOT NULL AUTO_INCREMENT COMMENT '최종모임장소번호';

ALTER TABLE gms_destination
  AUTO_INCREMENT = 1;

-- 모임등급유형
CREATE TABLE gms_memb_grade (
  gradeno INTEGER     NOT NULL COMMENT '모임등급유형번호', -- 모임등급유형번호
  name    VARCHAR(50) NOT NULL COMMENT '모임등급명' -- 모임등급명
)
COMMENT '모임등급유형';

-- 모임등급유형
ALTER TABLE gms_memb_grade
  ADD CONSTRAINT PK_gms_memb_grade -- 모임등급유형 기본키
    PRIMARY KEY (
      gradeno -- 모임등급유형번호
    );

ALTER TABLE gms_memb_grade
  MODIFY COLUMN gradeno INTEGER NOT NULL AUTO_INCREMENT COMMENT '모임등급유형번호';

ALTER TABLE gms_memb_grade
  AUTO_INCREMENT = 1;

-- 리쿠르팅게시판
CREATE TABLE gms_recruit (
  recruit    INTEGER     NOT NULL COMMENT '리쿠르팅게시판번호', -- 리쿠르팅게시판번호
  title      VARCHAR(50) NOT NULL COMMENT '제목', -- 제목
  content    LONGTEXT    NOT NULL COMMENT '내용', -- 내용
  reddt      DATE        NOT NULL COMMENT '작성일', -- 작성일
  viewcnt    INTEGER     NOT NULL COMMENT '조회수', -- 조회수
  itrlocalno INTEGER     NOT NULL COMMENT '활동지역번호', -- 활동지역번호
  fileno     INTEGER     NOT NULL COMMENT '모임목적번호', -- 모임목적번호
  membno     INTEGER     NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '리쿠르팅게시판';

-- 리쿠르팅게시판
ALTER TABLE gms_recruit
  ADD CONSTRAINT PK_gms_recruit -- 리쿠르팅게시판 기본키
    PRIMARY KEY (
      recruit -- 리쿠르팅게시판번호
    );

ALTER TABLE gms_recruit
  MODIFY COLUMN recruit INTEGER NOT NULL AUTO_INCREMENT COMMENT '리쿠르팅게시판번호';

ALTER TABLE gms_recruit
  AUTO_INCREMENT = 1;

-- 최근검색어
ALTER TABLE gms_keyword
  ADD CONSTRAINT FK_gms_memb_TO_gms_keyword -- 회원 -> 최근검색어
    FOREIGN KEY (
      membno -- 회원번호
    )
    REFERENCES gms_memb ( -- 회원
      membno -- 회원번호
    );

-- 소모임
ALTER TABLE gms_group
  ADD CONSTRAINT FK_gms_activelocal_TO_gms_group -- 활동지역 -> 소모임
    FOREIGN KEY (
      itrlocalno -- 활동지역번호
    )
    REFERENCES gms_activelocal ( -- 활동지역
      itrlocalno -- 활동지역번호
    );

-- 소모임
ALTER TABLE gms_group
  ADD CONSTRAINT FK_gms_purpose_TO_gms_group -- 모임목적 -> 소모임
    FOREIGN KEY (
      fileno -- 모임목적번호
    )
    REFERENCES gms_purpose ( -- 모임목적
      fileno -- 모임목적번호
    );

-- 직군세부
ALTER TABLE gms_itr_jobgroup_detail
  ADD CONSTRAINT FK_gms_itr_jobgroup_TO_gms_itr_jobgroup_detail -- 직군 -> 직군세부
    FOREIGN KEY (
      ijgno -- 직군번호
    )
    REFERENCES gms_itr_jobgroup ( -- 직군
      ijgno -- 직군번호
    );

-- 모임태그
ALTER TABLE gms_grouptag
  ADD CONSTRAINT FK_gms_group_TO_gms_grouptag -- 소모임 -> 모임태그
    FOREIGN KEY (
      gno -- 모임번호
    )
    REFERENCES gms_group ( -- 소모임
      gno -- 모임번호
    );

-- 모임가입신청서
ALTER TABLE gms_applyform
  ADD CONSTRAINT FK_gms_group_TO_gms_applyform -- 소모임 -> 모임가입신청서
    FOREIGN KEY (
      gno -- 모임번호
    )
    REFERENCES gms_group ( -- 소모임
      gno -- 모임번호
    );

-- 모임가입신청서
ALTER TABLE gms_applyform
  ADD CONSTRAINT FK_gms_memb_TO_gms_applyform -- 회원 -> 모임가입신청서
    FOREIGN KEY (
      membno -- 회원번호
    )
    REFERENCES gms_memb ( -- 회원
      membno -- 회원번호
    );

-- 모임게시글
ALTER TABLE gms_board
  ADD CONSTRAINT FK_gms_join_memb_TO_gms_board -- 모임맴버 -> 모임게시글
    FOREIGN KEY (
      membno, -- 회원번호
      gno     -- 모임번호
    )
    REFERENCES gms_join_memb ( -- 모임맴버
      membno, -- 회원번호
      gno     -- 모임번호
    );

-- 모임내부 게시글 댓글
ALTER TABLE gms_comment
  ADD CONSTRAINT FK_gms_board_TO_gms_comment -- 모임게시글 -> 모임내부 게시글 댓글
    FOREIGN KEY (
      bno -- 게시글번호
    )
    REFERENCES gms_board ( -- 모임게시글
      bno -- 게시글번호
    );

-- 모임내부 게시글 댓글
ALTER TABLE gms_comment
  ADD CONSTRAINT FK_gms_join_memb_TO_gms_comment -- 모임맴버 -> 모임내부 게시글 댓글
    FOREIGN KEY (
      membno, -- 회원번호
      gno     -- 모임번호
    )
    REFERENCES gms_join_memb ( -- 모임맴버
      membno, -- 회원번호
      gno     -- 모임번호
    );

-- 모임게시글사진
ALTER TABLE gms_photo
  ADD CONSTRAINT FK_gms_board_TO_gms_photo -- 모임게시글 -> 모임게시글사진
    FOREIGN KEY (
      bno -- 게시글번호
    )
    REFERENCES gms_board ( -- 모임게시글
      bno -- 게시글번호
    );

-- 모임내부 일정
ALTER TABLE gms_calendar
  ADD CONSTRAINT FK_gms_group_TO_gms_calendar -- 소모임 -> 모임내부 일정
    FOREIGN KEY (
      gno -- 모임번호
    )
    REFERENCES gms_group ( -- 소모임
      gno -- 모임번호
    );

-- 모임내부 첨부파일
ALTER TABLE gms_file
  ADD CONSTRAINT FK_gms_board_TO_gms_file -- 모임게시글 -> 모임내부 첨부파일
    FOREIGN KEY (
      bno -- 게시글번호
    )
    REFERENCES gms_board ( -- 모임게시글
      bno -- 게시글번호
    );

-- 모임맴버
ALTER TABLE gms_join_memb
  ADD CONSTRAINT FK_gms_memb_TO_gms_join_memb -- 회원 -> 모임맴버
    FOREIGN KEY (
      membno -- 회원번호
    )
    REFERENCES gms_memb ( -- 회원
      membno -- 회원번호
    );

-- 모임맴버
ALTER TABLE gms_join_memb
  ADD CONSTRAINT FK_gms_group_TO_gms_join_memb -- 소모임 -> 모임맴버
    FOREIGN KEY (
      gno -- 모임번호
    )
    REFERENCES gms_group ( -- 소모임
      gno -- 모임번호
    );

-- 모임맴버
ALTER TABLE gms_join_memb
  ADD CONSTRAINT FK_gms_memb_grade_TO_gms_join_memb -- 모임등급유형 -> 모임맴버
    FOREIGN KEY (
      gradeno -- 모임등급유형번호
    )
    REFERENCES gms_memb_grade ( -- 모임등급유형
      gradeno -- 모임등급유형번호
    );

-- 모금
ALTER TABLE gms_accounting
  ADD CONSTRAINT FK_gms_group_TO_gms_accounting -- 소모임 -> 모금
    FOREIGN KEY (
      gno -- 모임번호
    )
    REFERENCES gms_group ( -- 소모임
      gno -- 모임번호
    );

-- 모금
ALTER TABLE gms_accounting
  ADD CONSTRAINT FK_gms_accounting_cate_TO_gms_accounting -- 모금유형 -> 모금
    FOREIGN KEY (
      actcateno -- 모금유형번호
    )
    REFERENCES gms_accounting_cate ( -- 모금유형
      actcateno -- 모금유형번호
    );

-- 맴버장소입력
ALTER TABLE gms_select_midpoint
  ADD CONSTRAINT FK_gms_join_memb_TO_gms_select_midpoint -- 모임맴버 -> 맴버장소입력
    FOREIGN KEY (
      membno, -- 회원번호
      gno     -- 모임번호
    )
    REFERENCES gms_join_memb ( -- 모임맴버
      membno, -- 회원번호
      gno     -- 모임번호
    );

-- 고객센터 자주하는 질문
ALTER TABLE gms_faq
  ADD CONSTRAINT FK_gms_mcate2_TO_gms_faq -- 메인카테고리2 -> 고객센터 자주하는 질문
    FOREIGN KEY (
      mcate2no -- 메인카테고리2번호
    )
    REFERENCES gms_mcate2 ( -- 메인카테고리2
      mcate2no -- 메인카테고리2번호
    );

-- 고객센터 자주하는 질문
ALTER TABLE gms_faq
  ADD CONSTRAINT FK_gms_query_cate_TO_gms_faq -- 질문유형 -> 고객센터 자주하는 질문
    FOREIGN KEY (
      queryno -- 질문유형번호
    )
    REFERENCES gms_query_cate ( -- 질문유형
      queryno -- 질문유형번호
    );

-- 고객센터 공지사항
ALTER TABLE gms_notice
  ADD CONSTRAINT FK_gms_mcate2_TO_gms_notice -- 메인카테고리2 -> 고객센터 공지사항
    FOREIGN KEY (
      mcate2no -- 메인카테고리2번호
    )
    REFERENCES gms_mcate2 ( -- 메인카테고리2
      mcate2no -- 메인카테고리2번호
    );

-- 고객센터 문의하기
ALTER TABLE gms_notice_question
  ADD CONSTRAINT FK_gms_mcate2_TO_gms_notice_question -- 메인카테고리2 -> 고객센터 문의하기
    FOREIGN KEY (
      mcate2no -- 메인카테고리2번호
    )
    REFERENCES gms_mcate2 ( -- 메인카테고리2
      mcate2no -- 메인카테고리2번호
    );

-- 고객센터 문의하기
ALTER TABLE gms_notice_question
  ADD CONSTRAINT FK_gms_query_cate_TO_gms_notice_question -- 질문유형 -> 고객센터 문의하기
    FOREIGN KEY (
      queryno -- 질문유형번호
    )
    REFERENCES gms_query_cate ( -- 질문유형
      queryno -- 질문유형번호
    );

-- 고객센터 문의하기
ALTER TABLE gms_notice_question
  ADD CONSTRAINT FK_gms_memb_TO_gms_notice_question -- 회원 -> 고객센터 문의하기
    FOREIGN KEY (
      membno -- 회원번호
    )
    REFERENCES gms_memb ( -- 회원
      membno -- 회원번호
    );

-- 관심활동지역
ALTER TABLE gms_final_activelocal
  ADD CONSTRAINT FK_gms_memb_TO_gms_final_activelocal -- 회원 -> 관심활동지역
    FOREIGN KEY (
      membno -- 회원번호
    )
    REFERENCES gms_memb ( -- 회원
      membno -- 회원번호
    );

-- 관심활동지역
ALTER TABLE gms_final_activelocal
  ADD CONSTRAINT FK_gms_activelocal_TO_gms_final_activelocal -- 활동지역 -> 관심활동지역
    FOREIGN KEY (
      itrlocalno -- 활동지역번호
    )
    REFERENCES gms_activelocal ( -- 활동지역
      itrlocalno -- 활동지역번호
    );

-- 관심직군
ALTER TABLE gms_final_job
  ADD CONSTRAINT FK_gms_memb_TO_gms_final_job -- 회원 -> 관심직군
    FOREIGN KEY (
      membno -- 회원번호
    )
    REFERENCES gms_memb ( -- 회원
      membno -- 회원번호
    );

-- 관심직군
ALTER TABLE gms_final_job
  ADD CONSTRAINT FK_gms_itr_jobgroup_TO_gms_final_job -- 직군 -> 관심직군
    FOREIGN KEY (
      ijgno -- 직군번호
    )
    REFERENCES gms_itr_jobgroup ( -- 직군
      ijgno -- 직군번호
    );

-- 관심모임목적
ALTER TABLE gms_final_purpose
  ADD CONSTRAINT FK_gms_purpose_TO_gms_final_purpose -- 모임목적 -> 관심모임목적
    FOREIGN KEY (
      fileno -- 모임목적번호
    )
    REFERENCES gms_purpose ( -- 모임목적
      fileno -- 모임목적번호
    );

-- 관심모임목적
ALTER TABLE gms_final_purpose
  ADD CONSTRAINT FK_gms_memb_TO_gms_final_purpose -- 회원 -> 관심모임목적
    FOREIGN KEY (
      membno -- 회원번호
    )
    REFERENCES gms_memb ( -- 회원
      membno -- 회원번호
    );

-- 모금납부내역
ALTER TABLE gms_accounting_status
  ADD CONSTRAINT FK_gms_accounting_TO_gms_accounting_status -- 모금 -> 모금납부내역
    FOREIGN KEY (
      actno -- 모금번호
    )
    REFERENCES gms_accounting ( -- 모금
      actno -- 모금번호
    );

-- 모금납부내역
ALTER TABLE gms_accounting_status
  ADD CONSTRAINT FK_gms_join_memb_TO_gms_accounting_status -- 모임맴버 -> 모금납부내역
    FOREIGN KEY (
      membno, -- 회원번호
      gno     -- 모임번호
    )
    REFERENCES gms_join_memb ( -- 모임맴버
      membno, -- 회원번호
      gno     -- 모임번호
    );

-- 모임게시글스크랩
ALTER TABLE gms_scrap
  ADD CONSTRAINT FK_gms_join_memb_TO_gms_scrap -- 모임맴버 -> 모임게시글스크랩
    FOREIGN KEY (
      membno, -- 회원번호
      gno     -- 모임번호
    )
    REFERENCES gms_join_memb ( -- 모임맴버
      membno, -- 회원번호
      gno     -- 모임번호
    );

-- 모임게시글스크랩
ALTER TABLE gms_scrap
  ADD CONSTRAINT FK_gms_board_TO_gms_scrap -- 모임게시글 -> 모임게시글스크랩
    FOREIGN KEY (
      bno -- 게시글번호
    )
    REFERENCES gms_board ( -- 모임게시글
      bno -- 게시글번호
    );

-- SNS계정
ALTER TABLE gms_final_SNS
  ADD CONSTRAINT FK_gms_memb_TO_gms_final_SNS -- 회원 -> SNS계정
    FOREIGN KEY (
      membno -- 회원번호
    )
    REFERENCES gms_memb ( -- 회원
      membno -- 회원번호
    );

-- SNS계정
ALTER TABLE gms_final_SNS
  ADD CONSTRAINT FK_gms_SNS_TO_gms_final_SNS -- SNS -> SNS계정
    FOREIGN KEY (
      SNSno -- SNS번호
    )
    REFERENCES gms_SNS ( -- SNS
      SNSno -- SNS번호
    );

-- 리쿠르팅게시판
ALTER TABLE gms_recruit
  ADD CONSTRAINT FK_gms_activelocal_TO_gms_recruit -- 활동지역 -> 리쿠르팅게시판
    FOREIGN KEY (
      itrlocalno -- 활동지역번호
    )
    REFERENCES gms_activelocal ( -- 활동지역
      itrlocalno -- 활동지역번호
    );

-- 리쿠르팅게시판
ALTER TABLE gms_recruit
  ADD CONSTRAINT FK_gms_purpose_TO_gms_recruit -- 모임목적 -> 리쿠르팅게시판
    FOREIGN KEY (
      fileno -- 모임목적번호
    )
    REFERENCES gms_purpose ( -- 모임목적
      fileno -- 모임목적번호
    );

-- 리쿠르팅게시판
ALTER TABLE gms_recruit
  ADD CONSTRAINT FK_gms_memb_TO_gms_recruit -- 회원 -> 리쿠르팅게시판
    FOREIGN KEY (
      membno -- 회원번호
    )
    REFERENCES gms_memb ( -- 회원
      membno -- 회원번호
    );