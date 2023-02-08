-- 회원
DROP TABLE IF EXISTS `gms_memb` RESTRICT;

-- 메인카테고리
DROP TABLE IF EXISTS `gms_main_cate` RESTRICT;

-- 최근검색어
DROP TABLE IF EXISTS `gms_keyword` RESTRICT;

-- 소모임
DROP TABLE IF EXISTS `gms_group` RESTRICT;

-- 활동지역
DROP TABLE IF EXISTS `gms_activelocal` RESTRICT;

-- 모임목적
DROP TABLE IF EXISTS `gms_purpose` RESTRICT;

-- 모임태그
DROP TABLE IF EXISTS `gms_grouptag` RESTRICT;

-- 모임가입신청서
DROP TABLE IF EXISTS `gms_applyform` RESTRICT;

-- 모임게시글
DROP TABLE IF EXISTS `gms_board` RESTRICT;

-- 모임내부 게시글 댓글
DROP TABLE IF EXISTS `gms_comment` RESTRICT;

-- 모임게시글사진
DROP TABLE IF EXISTS `gms_photo` RESTRICT;

-- 모임내부 일정
DROP TABLE IF EXISTS `gms_calendar` RESTRICT;

-- 모임내부 첨부파일
DROP TABLE IF EXISTS `gms_file` RESTRICT;

-- 모임맴버
DROP TABLE IF EXISTS `gms_join_memb` RESTRICT;

-- 모금
DROP TABLE IF EXISTS `gms_accounting` RESTRICT;

-- 맴버장소입력
DROP TABLE IF EXISTS `gms_select_midpoint` RESTRICT;

-- 고객센터 자주하는 질문
DROP TABLE IF EXISTS `gms_faq` RESTRICT;

-- 고객센터 공지사항
DROP TABLE IF EXISTS `gms_notice` RESTRICT;

-- 고객센터 문의하기
DROP TABLE IF EXISTS `gms_notice_question` RESTRICT;

-- 관심활동지역
DROP TABLE IF EXISTS `gms_final_activelocal` RESTRICT;

-- 관심모임목적
DROP TABLE IF EXISTS `gms_final_purpose` RESTRICT;

-- 모금유형
DROP TABLE IF EXISTS `gms_accounting_cate` RESTRICT;

-- 모금납부내역
DROP TABLE IF EXISTS `gms_accounting_status` RESTRICT;

-- 모임게시글스크랩
DROP TABLE IF EXISTS `gms_scrap` RESTRICT;

-- 질문유형
DROP TABLE IF EXISTS `gms_query_cate` RESTRICT;

-- SNS계정
DROP TABLE IF EXISTS `gms_final_SNS` RESTRICT;

-- SNS
DROP TABLE IF EXISTS `gms_SNS` RESTRICT;

-- 최종모임장소
DROP TABLE IF EXISTS `gms_destination` RESTRICT;

-- 모임등급유형
DROP TABLE IF EXISTS `gms_memb_grade` RESTRICT;

-- pickme게시판
DROP TABLE IF EXISTS `gms_pickme` RESTRICT;

-- 신고
DROP TABLE IF EXISTS `gms_report` RESTRICT;

-- 신고유형
DROP TABLE IF EXISTS `gms_report_cate` RESTRICT;

-- 초대장저장공간
DROP TABLE IF EXISTS `gms_invite_box` RESTRICT;

-- 모임가입신청서질문목록
DROP TABLE IF EXISTS `gms_applyform_question` RESTRICT;

-- 모임가입신청서질문목록답변
DROP TABLE IF EXISTS `gms_applyform_answer` RESTRICT;

-- 회원
CREATE TABLE `gms_memb` (
	`memb_no`     INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
	`name`        VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
	`pwd`         VARCHAR(100) NOT NULL COMMENT '비밀번호', -- 비밀번호
	`email`       VARCHAR(40)  NOT NULL COMMENT '이메일', -- 이메일
	`tel`         VARCHAR(30)  NOT NULL COMMENT '전화번호', -- 전화번호
	`post_no`     VARCHAR(10)  NOT NULL COMMENT '우편번호', -- 우편번호
	`base_addr`   VARCHAR(255) NOT NULL COMMENT '기본주소', -- 기본주소
	`addr`        VARCHAR(255) NOT NULL COMMENT '상세주소', -- 상세주소
	`type`        VARCHAR(50)  NOT NULL COMMENT '유형', -- 유형
	`unsubscribe` BOOLEAN      NULL     COMMENT '탈퇴여부', -- 탈퇴여부
	`status`      BOOLEAN      NULL     COMMENT '상태', -- 상태
	`stop_dt`     DATE         NULL     COMMENT '제제일', -- 제제일
	`cre_dt`      DATE         NOT NULL DEFAULT current_timestamp() COMMENT '가입일' -- 가입일
)
COMMENT '회원';

-- 회원
ALTER TABLE `gms_memb`
	ADD CONSTRAINT `PK_gms_memb` -- 회원 기본키
		PRIMARY KEY (
			`memb_no` -- 회원번호
		);

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX `UIX_gms_memb`
	ON `gms_memb` ( -- 회원
		`email` ASC, -- 이메일
		`tel` ASC    -- 전화번호
	);

ALTER TABLE `gms_memb`
	MODIFY COLUMN `memb_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

ALTER TABLE `gms_memb`
	AUTO_INCREMENT = 1;

-- 메인카테고리
CREATE TABLE `gms_main_cate` (
	`main_cate_no` INTEGER     NOT NULL COMMENT '메인카테고리번호', -- 메인카테고리번호
	`name`         VARCHAR(50) NOT NULL COMMENT '카테고리목록명' -- 카테고리목록명
)
COMMENT '메인카테고리';

-- 메인카테고리
ALTER TABLE `gms_main_cate`
	ADD CONSTRAINT `PK_gms_main_cate` -- 메인카테고리 기본키
		PRIMARY KEY (
			`main_cate_no` -- 메인카테고리번호
		);

-- 메인카테고리 유니크 인덱스
CREATE UNIQUE INDEX `UIX_gms_main_cate`
	ON `gms_main_cate` ( -- 메인카테고리
		`name` ASC -- 카테고리목록명
	);

ALTER TABLE `gms_main_cate`
	MODIFY COLUMN `main_cate_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '메인카테고리번호';

ALTER TABLE `gms_main_cate`
	AUTO_INCREMENT = 1;

-- 최근검색어
CREATE TABLE `gms_keyword` (
	`k_no`        INTEGER     NOT NULL COMMENT '최근검색어 번호', -- 최근검색어 번호
	`search_name` VARCHAR(50) NOT NULL COMMENT '최근검색어명', -- 최근검색어명
	`memb_no`     INTEGER     NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '최근검색어';

-- 최근검색어
ALTER TABLE `gms_keyword`
	ADD CONSTRAINT `PK_gms_keyword` -- 최근검색어 기본키
		PRIMARY KEY (
			`k_no` -- 최근검색어 번호
		);

ALTER TABLE `gms_keyword`
	MODIFY COLUMN `k_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '최근검색어 번호';

ALTER TABLE `gms_keyword`
	AUTO_INCREMENT = 1;

-- 소모임
CREATE TABLE `gms_group` (
	`g_no`         INTEGER      NOT NULL COMMENT '모임번호', -- 모임번호
	`act_local_no` INTEGER      NOT NULL COMMENT '활동지역번호', -- 활동지역번호
	`pups_no`      INTEGER      NOT NULL COMMENT '모임목적번호', -- 모임목적번호
	`name`         VARCHAR(50)  NOT NULL COMMENT '모임이름', -- 모임이름
	`reg_dt`       DATE         NOT NULL DEFAULT current_timestamp() COMMENT '모임생성일', -- 모임생성일
	`logo`         VARCHAR(255) NULL     COMMENT '모임로고사진', -- 모임로고사진
	`intro`        LONGTEXT     NOT NULL COMMENT '모임안내문구', -- 모임안내문구
	`max_cnt`      INTEGER      NOT NULL DEFAULT 1 COMMENT '모임인원수', -- 모임인원수
	`view_cnt`     INTEGER      NOT NULL DEFAULT 0 COMMENT '방문자수', -- 방문자수
	`status`       BOOLEAN      NULL     COMMENT '상태', -- 상태
	`stop_dt`      DATE         NULL     COMMENT '제제일' -- 제제일
)
COMMENT '소모임';

-- 소모임
ALTER TABLE `gms_group`
	ADD CONSTRAINT `PK_gms_group` -- 소모임 기본키
		PRIMARY KEY (
			`g_no` -- 모임번호
		);

-- 소모임 인덱스
CREATE INDEX `IX_gms_group`
	ON `gms_group`( -- 소모임
		`name` ASC -- 모임이름
	);

ALTER TABLE `gms_group`
	MODIFY COLUMN `g_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '모임번호';

ALTER TABLE `gms_group`
	AUTO_INCREMENT = 1;

-- 활동지역
CREATE TABLE `gms_activelocal` (
	`act_local_no` INTEGER     NOT NULL COMMENT '활동지역번호', -- 활동지역번호
	`name_si`      VARCHAR(50) NOT NULL COMMENT '시', -- 시
	`name_gu`      VARCHAR(50) NOT NULL COMMENT '군구' -- 군구
)
COMMENT '활동지역';

-- 활동지역
ALTER TABLE `gms_activelocal`
	ADD CONSTRAINT `PK_gms_activelocal` -- 활동지역 기본키
		PRIMARY KEY (
			`act_local_no` -- 활동지역번호
		);

ALTER TABLE `gms_activelocal`
	MODIFY COLUMN `act_local_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '활동지역번호';

ALTER TABLE `gms_activelocal`
	AUTO_INCREMENT = 1;

-- 모임목적
CREATE TABLE `gms_purpose` (
	`pups_no` INTEGER     NOT NULL COMMENT '모임목적번호', -- 모임목적번호
	`name`    VARCHAR(50) NOT NULL COMMENT '모임목적명' -- 모임목적명
)
COMMENT '모임목적';

-- 모임목적
ALTER TABLE `gms_purpose`
	ADD CONSTRAINT `PK_gms_purpose` -- 모임목적 기본키
		PRIMARY KEY (
			`pups_no` -- 모임목적번호
		);

-- 모임목적 유니크 인덱스
CREATE UNIQUE INDEX `UIX_gms_purpose`
	ON `gms_purpose` ( -- 모임목적
		`name` ASC -- 모임목적명
	);

ALTER TABLE `gms_purpose`
	MODIFY COLUMN `pups_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '모임목적번호';

ALTER TABLE `gms_purpose`
	AUTO_INCREMENT = 1;

-- 모임태그
CREATE TABLE `gms_grouptag` (
	`tag_no`   INTEGER     NOT NULL COMMENT '태그번호', -- 태그번호
	`g_no`     INTEGER     NOT NULL COMMENT '모임번호', -- 모임번호
	`tag_name` VARCHAR(50) NOT NULL COMMENT '태그이름' -- 태그이름
)
COMMENT '모임태그';

-- 모임태그
ALTER TABLE `gms_grouptag`
	ADD CONSTRAINT `PK_gms_grouptag` -- 모임태그 기본키
		PRIMARY KEY (
			`tag_no` -- 태그번호
		);

-- 모임태그 유니크 인덱스
CREATE UNIQUE INDEX `UIX_gms_grouptag`
	ON `gms_grouptag` ( -- 모임태그
		`tag_name` ASC -- 태그이름
	);

ALTER TABLE `gms_grouptag`
	MODIFY COLUMN `tag_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '태그번호';

ALTER TABLE `gms_grouptag`
	AUTO_INCREMENT = 1;

-- 모임가입신청서
CREATE TABLE `gms_applyform` (
	`appl_no` INTEGER  NOT NULL COMMENT '모임가입신청서 번호', -- 모임가입신청서 번호
	`g_no`    INTEGER  NOT NULL COMMENT '모임번호', -- 모임번호
	`memb_no` INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
	`content` LONGTEXT NOT NULL COMMENT '내용' -- 내용
)
COMMENT '모임가입신청서';

-- 모임가입신청서
ALTER TABLE `gms_applyform`
	ADD CONSTRAINT `PK_gms_applyform` -- 모임가입신청서 기본키
		PRIMARY KEY (
			`appl_no` -- 모임가입신청서 번호
		);

-- 모임가입신청서 유니크 인덱스
CREATE UNIQUE INDEX `UIX_gms_applyform`
	ON `gms_applyform` ( -- 모임가입신청서
		`g_no` ASC,    -- 모임번호
		`memb_no` ASC  -- 회원번호
	);

ALTER TABLE `gms_applyform`
	MODIFY COLUMN `appl_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '모임가입신청서 번호';

ALTER TABLE `gms_applyform`
	AUTO_INCREMENT = 1;

-- 모임게시글
CREATE TABLE `gms_board` (
	`b_no`     INTEGER  NOT NULL COMMENT '게시글번호', -- 게시글번호
	`g_no`     INTEGER  NOT NULL COMMENT '모임번호', -- 모임번호
	`memb_no`  INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
	`reg_dt`   DATE     NOT NULL DEFAULT current_timestamp() COMMENT '작성일시', -- 작성일시
	`content`  LONGTEXT NOT NULL COMMENT '내용', -- 내용
	`view_cnt` INTEGER  NOT NULL DEFAULT 0 COMMENT '조회수', -- 조회수
	`status`   BOOLEAN  NULL     COMMENT '상태' -- 상태
)
COMMENT '모임게시글';

-- 모임게시글
ALTER TABLE `gms_board`
	ADD CONSTRAINT `PK_gms_board` -- 모임게시글 기본키
		PRIMARY KEY (
			`b_no` -- 게시글번호
		);

-- 모임게시글 인덱스
CREATE INDEX `IX_gms_board`
	ON `gms_board`( -- 모임게시글
	);

ALTER TABLE `gms_board`
	MODIFY COLUMN `b_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시글번호';

ALTER TABLE `gms_board`
	AUTO_INCREMENT = 1;

-- 모임내부 게시글 댓글
CREATE TABLE `gms_comment` (
	`cmt_no`  INTEGER  NOT NULL COMMENT '댓글번호', -- 댓글번호
	`b_no`    INTEGER  NOT NULL COMMENT '게시글번호', -- 게시글번호
	`content` LONGTEXT NOT NULL COMMENT '댓글내용 ', -- 댓글내용 
	`memb_no` INTEGER  NOT NULL COMMENT '회원번호', -- 회원번호
	`g_no`    INTEGER  NOT NULL COMMENT '모임번호', -- 모임번호
	`reg_dt`  DATE     NOT NULL DEFAULT current_timestamp() COMMENT '등록일', -- 등록일
	`status`  BOOLEAN  NULL     COMMENT '상태' -- 상태
)
COMMENT '모임내부 게시글 댓글';

-- 모임내부 게시글 댓글
ALTER TABLE `gms_comment`
	ADD CONSTRAINT `PK_gms_comment` -- 모임내부 게시글 댓글 기본키
		PRIMARY KEY (
			`cmt_no` -- 댓글번호
		);

ALTER TABLE `gms_comment`
	MODIFY COLUMN `cmt_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '댓글번호';

ALTER TABLE `gms_comment`
	AUTO_INCREMENT = 1;

-- 모임게시글사진
CREATE TABLE `gms_photo` (
	`g_photo` INTEGER      NOT NULL COMMENT '사진번호', -- 사진번호
	`b_no`    INTEGER      NOT NULL COMMENT '게시글번호', -- 게시글번호
	`path`    VARCHAR(255) NOT NULL COMMENT '사진경로' -- 사진경로
)
COMMENT '모임게시글사진';

-- 모임게시글사진
ALTER TABLE `gms_photo`
	ADD CONSTRAINT `PK_gms_photo` -- 모임게시글사진 기본키
		PRIMARY KEY (
			`g_photo` -- 사진번호
		);

ALTER TABLE `gms_photo`
	MODIFY COLUMN `g_photo` INTEGER NOT NULL AUTO_INCREMENT COMMENT '사진번호';

ALTER TABLE `gms_photo`
	AUTO_INCREMENT = 1;

-- 모임내부 일정
CREATE TABLE `gms_calendar` (
	`cal_no`     INTEGER      NOT NULL COMMENT '일정번호', -- 일정번호
	`name`       VARCHAR(255) NOT NULL COMMENT '약속이름', -- 약속이름
	`content`    LONGTEXT     NOT NULL COMMENT '약속내용', -- 약속내용
	`g_no`       INTEGER      NOT NULL COMMENT '모임번호', -- 모임번호
	`start_dt`   DATE         NOT NULL COMMENT '시작일', -- 시작일
	`end_dt`     DATE         NOT NULL COMMENT '끝일', -- 끝일
	`result_url` VARCHAR(255) NULL     COMMENT '최종장소URL' -- 최종장소URL
)
COMMENT '모임내부 일정';

-- 모임내부 일정
ALTER TABLE `gms_calendar`
	ADD CONSTRAINT `PK_gms_calendar` -- 모임내부 일정 기본키
		PRIMARY KEY (
			`cal_no` -- 일정번호
		);

ALTER TABLE `gms_calendar`
	MODIFY COLUMN `cal_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '일정번호';

ALTER TABLE `gms_calendar`
	AUTO_INCREMENT = 1;

-- 모임내부 첨부파일
CREATE TABLE `gms_file` (
	`file_no` INTEGER     NOT NULL COMMENT '첨부파일번호', -- 첨부파일번호
	`b_no`    INTEGER     NOT NULL COMMENT '게시글번호', -- 게시글번호
	`name`    VARCHAR(50) NOT NULL COMMENT '첨부파일명' -- 첨부파일명
)
COMMENT '모임내부 첨부파일';

-- 모임내부 첨부파일
ALTER TABLE `gms_file`
	ADD CONSTRAINT `PK_gms_file` -- 모임내부 첨부파일 기본키
		PRIMARY KEY (
			`file_no` -- 첨부파일번호
		);

-- 모임내부 첨부파일 유니크 인덱스
CREATE UNIQUE INDEX `UIX_gms_file`
	ON `gms_file` ( -- 모임내부 첨부파일
		`name` ASC -- 첨부파일명
	);

-- 모임내부 첨부파일 인덱스
CREATE INDEX `IX_gms_file`
	ON `gms_file`( -- 모임내부 첨부파일
		`name` ASC -- 첨부파일명
	);

ALTER TABLE `gms_file`
	MODIFY COLUMN `file_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '첨부파일번호';

ALTER TABLE `gms_file`
	AUTO_INCREMENT = 1;

-- 모임맴버
CREATE TABLE `gms_join_memb` (
	`memb_no`       INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
	`g_no`          INTEGER NOT NULL COMMENT '모임번호', -- 모임번호
	`g_memb_grd_no` INTEGER NOT NULL COMMENT '모임등급유형번호' -- 모임등급유형번호
)
COMMENT '모임맴버';

-- 모임맴버
ALTER TABLE `gms_join_memb`
	ADD CONSTRAINT `PK_gms_join_memb` -- 모임맴버 기본키
		PRIMARY KEY (
			`memb_no`, -- 회원번호
			`g_no`     -- 모임번호
		);

-- 모금
CREATE TABLE `gms_accounting` (
	`act_no`      INTEGER      NOT NULL COMMENT '모금번호', -- 모금번호
	`g_no`        INTEGER      NOT NULL COMMENT '모임번호', -- 모임번호
	`act_cate_no` INTEGER      NOT NULL COMMENT '모금유형번호', -- 모금유형번호
	`title`       VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
	`amount`      INTEGER      NOT NULL DEFAULT 0 COMMENT '금액', -- 금액
	`reg_dt`      DATE         NOT NULL DEFAULT current_timestamp() COMMENT '등록일', -- 등록일
	`status`      BOOLEAN      NOT NULL DEFAULT false COMMENT '상태' -- 상태
)
COMMENT '모금';

-- 모금
ALTER TABLE `gms_accounting`
	ADD CONSTRAINT `PK_gms_accounting` -- 모금 기본키
		PRIMARY KEY (
			`act_no` -- 모금번호
		);

-- 모금 인덱스
CREATE INDEX `IX_gms_accounting`
	ON `gms_accounting`( -- 모금
		`title` ASC -- 제목
	);

ALTER TABLE `gms_accounting`
	MODIFY COLUMN `act_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '모금번호';

ALTER TABLE `gms_accounting`
	AUTO_INCREMENT = 1;

-- 맴버장소입력
CREATE TABLE `gms_select_midpoint` (
	`memb_pt_no` INTEGER     NOT NULL COMMENT '맴버장소입력번호', -- 맴버장소입력번호
	`lat`        VARCHAR(50) NOT NULL COMMENT '위도', -- 위도
	`lng`        VARCHAR(50) NOT NULL COMMENT '경도', -- 경도
	`memb_no`    INTEGER     NOT NULL COMMENT '회원번호', -- 회원번호
	`g_no`       INTEGER     NOT NULL COMMENT '모임번호' -- 모임번호
)
COMMENT '맴버장소입력';

-- 맴버장소입력
ALTER TABLE `gms_select_midpoint`
	ADD CONSTRAINT `PK_gms_select_midpoint` -- 맴버장소입력 기본키
		PRIMARY KEY (
			`memb_pt_no` -- 맴버장소입력번호
		);

ALTER TABLE `gms_select_midpoint`
	MODIFY COLUMN `memb_pt_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '맴버장소입력번호';

ALTER TABLE `gms_select_midpoint`
	AUTO_INCREMENT = 1;

-- 고객센터 자주하는 질문
CREATE TABLE `gms_faq` (
	`faq_no`       INTEGER      NOT NULL COMMENT '자주하는질문번호', -- 자주하는질문번호
	`main_cate_no` INTEGER      NOT NULL COMMENT '메인카테고리번호', -- 메인카테고리번호
	`query_no`     INTEGER      NOT NULL COMMENT '질문유형번호', -- 질문유형번호
	`title`        VARCHAR(255) NOT NULL COMMENT '질문', -- 질문
	`content`      LONGTEXT     NOT NULL COMMENT '답변', -- 답변
	`path`         VARCHAR(255) NULL     COMMENT '사진파일경로', -- 사진파일경로
	`reg_dt`       DATE         NOT NULL DEFAULT current_timestamp() COMMENT '등록일' -- 등록일
)
COMMENT '고객센터 자주하는 질문';

-- 고객센터 자주하는 질문
ALTER TABLE `gms_faq`
	ADD CONSTRAINT `PK_gms_faq` -- 고객센터 자주하는 질문 기본키
		PRIMARY KEY (
			`faq_no` -- 자주하는질문번호
		);

ALTER TABLE `gms_faq`
	MODIFY COLUMN `faq_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '자주하는질문번호';

ALTER TABLE `gms_faq`
	AUTO_INCREMENT = 1;

-- 고객센터 공지사항
CREATE TABLE `gms_notice` (
	`notice_no`    INTEGER      NOT NULL COMMENT '공지사항번호', -- 공지사항번호
	`main_cate_no` INTEGER      NOT NULL COMMENT '메인카테고리번호', -- 메인카테고리번호
	`cate`         VARCHAR(50)  NOT NULL COMMENT '내용분류', -- 내용분류
	`title`        VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
	`content`      LONGTEXT     NOT NULL COMMENT '내용', -- 내용
	`reg_dt`       DATE         NOT NULL DEFAULT current_timestamp() COMMENT '등록일' -- 등록일
)
COMMENT '고객센터 공지사항';

-- 고객센터 공지사항
ALTER TABLE `gms_notice`
	ADD CONSTRAINT `PK_gms_notice` -- 고객센터 공지사항 기본키
		PRIMARY KEY (
			`notice_no` -- 공지사항번호
		);

ALTER TABLE `gms_notice`
	MODIFY COLUMN `notice_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '공지사항번호';

ALTER TABLE `gms_notice`
	AUTO_INCREMENT = 1;

-- 고객센터 문의하기
CREATE TABLE `gms_notice_question` (
	`q_no`         INTEGER      NOT NULL COMMENT '문의번호', -- 문의번호
	`main_cate_no` INTEGER      NOT NULL COMMENT '메인카테고리번호', -- 메인카테고리번호
	`query_no`     INTEGER      NOT NULL COMMENT '질문유형번호', -- 질문유형번호
	`memb_no`      INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
	`title`        VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
	`content`      LONGTEXT     NOT NULL COMMENT '내용', -- 내용
	`path`         VARCHAR(255) NULL     COMMENT '첨부파일 경로', -- 첨부파일 경로
	`query_dt`     DATE         NOT NULL DEFAULT current_timestamp() COMMENT '문의일', -- 문의일
	`answer`       LONGTEXT     NULL     COMMENT '답변', -- 답변
	`answer_dt`    DATE         NULL     DEFAULT current_timestamp() COMMENT '답변일' -- 답변일
)
COMMENT '고객센터 문의하기';

-- 고객센터 문의하기
ALTER TABLE `gms_notice_question`
	ADD CONSTRAINT `PK_gms_notice_question` -- 고객센터 문의하기 기본키
		PRIMARY KEY (
			`q_no` -- 문의번호
		);

-- 고객센터 문의하기 인덱스
CREATE INDEX `IX_gms_notice_question`
	ON `gms_notice_question`( -- 고객센터 문의하기
		`title` ASC -- 제목
	);

ALTER TABLE `gms_notice_question`
	MODIFY COLUMN `q_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '문의번호';

ALTER TABLE `gms_notice_question`
	AUTO_INCREMENT = 1;

-- 관심활동지역
CREATE TABLE `gms_final_activelocal` (
	`memb_no`      INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
	`act_local_no` INTEGER NOT NULL COMMENT '활동지역번호' -- 활동지역번호
)
COMMENT '관심활동지역';

-- 관심활동지역
ALTER TABLE `gms_final_activelocal`
	ADD CONSTRAINT `PK_gms_final_activelocal` -- 관심활동지역 기본키
		PRIMARY KEY (
			`memb_no`,      -- 회원번호
			`act_local_no`  -- 활동지역번호
		);

-- 관심모임목적
CREATE TABLE `gms_final_purpose` (
	`pups_no` INTEGER NOT NULL COMMENT '모임목적번호', -- 모임목적번호
	`memb_no` INTEGER NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '관심모임목적';

-- 관심모임목적
ALTER TABLE `gms_final_purpose`
	ADD CONSTRAINT `PK_gms_final_purpose` -- 관심모임목적 기본키
		PRIMARY KEY (
			`pups_no`, -- 모임목적번호
			`memb_no`  -- 회원번호
		);

-- 모금유형
CREATE TABLE `gms_accounting_cate` (
	`act_cate_no` INTEGER     NOT NULL COMMENT '모금유형번호', -- 모금유형번호
	`name`        VARCHAR(50) NOT NULL COMMENT '모금유형명' -- 모금유형명
)
COMMENT '모금유형';

-- 모금유형
ALTER TABLE `gms_accounting_cate`
	ADD CONSTRAINT `PK_gms_accounting_cate` -- 모금유형 기본키
		PRIMARY KEY (
			`act_cate_no` -- 모금유형번호
		);

ALTER TABLE `gms_accounting_cate`
	MODIFY COLUMN `act_cate_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '모금유형번호';

ALTER TABLE `gms_accounting_cate`
	AUTO_INCREMENT = 1;

-- 모금납부내역
CREATE TABLE `gms_accounting_status` (
	`act_no`  INTEGER NOT NULL COMMENT '모금번호', -- 모금번호
	`g_no`    INTEGER NOT NULL COMMENT '모임번호', -- 모임번호
	`memb_no` INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
	`pay_dt`  DATE    NOT NULL COMMENT '납부일' -- 납부일
)
COMMENT '모금납부내역';

-- 모금납부내역
ALTER TABLE `gms_accounting_status`
	ADD CONSTRAINT `PK_gms_accounting_status` -- 모금납부내역 기본키
		PRIMARY KEY (
			`act_no`,  -- 모금번호
			`g_no`,    -- 모임번호
			`memb_no`  -- 회원번호
		);

-- 모임게시글스크랩
CREATE TABLE `gms_scrap` (
	`g_no`    INTEGER NOT NULL COMMENT '모임번호', -- 모임번호
	`memb_no` INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
	`b_no`    INTEGER NOT NULL COMMENT '게시글번호' -- 게시글번호
)
COMMENT '모임게시글스크랩';

-- 모임게시글스크랩
ALTER TABLE `gms_scrap`
	ADD CONSTRAINT `PK_gms_scrap` -- 모임게시글스크랩 기본키
		PRIMARY KEY (
			`g_no`,    -- 모임번호
			`memb_no`, -- 회원번호
			`b_no`     -- 게시글번호
		);

-- 질문유형
CREATE TABLE `gms_query_cate` (
	`query_no` INTEGER     NOT NULL COMMENT '질문유형번호', -- 질문유형번호
	`name`     VARCHAR(50) NOT NULL COMMENT '질문유형명' -- 질문유형명
)
COMMENT '질문유형';

-- 질문유형
ALTER TABLE `gms_query_cate`
	ADD CONSTRAINT `PK_gms_query_cate` -- 질문유형 기본키
		PRIMARY KEY (
			`query_no` -- 질문유형번호
		);

ALTER TABLE `gms_query_cate`
	MODIFY COLUMN `query_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '질문유형번호';

ALTER TABLE `gms_query_cate`
	AUTO_INCREMENT = 1;

-- SNS계정
CREATE TABLE `gms_final_SNS` (
	`memb_no`   INTEGER     NOT NULL COMMENT '회원번호', -- 회원번호
	`SNS_no`    INTEGER     NOT NULL COMMENT 'SNS번호', -- SNS번호
	`SNS_email` VARCHAR(40) NOT NULL COMMENT '이메일' -- 이메일
)
COMMENT 'SNS계정';

-- SNS계정
ALTER TABLE `gms_final_SNS`
	ADD CONSTRAINT `PK_gms_final_SNS` -- SNS계정 기본키
		PRIMARY KEY (
			`memb_no` -- 회원번호
		);

-- SNS
CREATE TABLE `gms_SNS` (
	`SNS_no` INTEGER     NOT NULL COMMENT 'SNS번호', -- SNS번호
	`name`   VARCHAR(50) NOT NULL COMMENT 'SNS명' -- SNS명
)
COMMENT 'SNS';

-- SNS
ALTER TABLE `gms_SNS`
	ADD CONSTRAINT `PK_gms_SNS` -- SNS 기본키
		PRIMARY KEY (
			`SNS_no` -- SNS번호
		);

ALTER TABLE `gms_SNS`
	MODIFY COLUMN `SNS_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'SNS번호';

ALTER TABLE `gms_SNS`
	AUTO_INCREMENT = 1;

-- 최종모임장소
CREATE TABLE `gms_destination` (
	`dest_no`     INTEGER     NOT NULL COMMENT '최종모임장소번호', -- 최종모임장소번호
	`flat`        VARCHAR(50) NOT NULL COMMENT '위도', -- 위도
	`flng`        VARCHAR(50) NOT NULL COMMENT '경도', -- 경도
	`store_name`  VARCHAR(50) NOT NULL COMMENT '매장명', -- 매장명
	`location_id` VARCHAR(50) NOT NULL COMMENT '장소아이디', -- 장소아이디
	`reg_dt`      DATE        NOT NULL DEFAULT current_timestamp() COMMENT '등록일', -- 등록일
	`view_cnt`    INTEGER     NOT NULL DEFAULT 0 COMMENT '선택횟수' -- 선택횟수
)
COMMENT '최종모임장소';

-- 최종모임장소
ALTER TABLE `gms_destination`
	ADD CONSTRAINT `PK_gms_destination` -- 최종모임장소 기본키
		PRIMARY KEY (
			`dest_no` -- 최종모임장소번호
		);

ALTER TABLE `gms_destination`
	MODIFY COLUMN `dest_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '최종모임장소번호';

ALTER TABLE `gms_destination`
	AUTO_INCREMENT = 1;

-- 모임등급유형
CREATE TABLE `gms_memb_grade` (
	`g_memb_grd_no` INTEGER     NOT NULL COMMENT '모임등급유형번호', -- 모임등급유형번호
	`name`          VARCHAR(50) NOT NULL COMMENT '모임등급명' -- 모임등급명
)
COMMENT '모임등급유형';

-- 모임등급유형
ALTER TABLE `gms_memb_grade`
	ADD CONSTRAINT `PK_gms_memb_grade` -- 모임등급유형 기본키
		PRIMARY KEY (
			`g_memb_grd_no` -- 모임등급유형번호
		);

ALTER TABLE `gms_memb_grade`
	MODIFY COLUMN `g_memb_grd_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '모임등급유형번호';

ALTER TABLE `gms_memb_grade`
	AUTO_INCREMENT = 1;

-- pickme게시판
CREATE TABLE `gms_pickme` (
	`pm_b_no`      INTEGER     NOT NULL COMMENT '픽미게시판번호', -- 픽미게시판번호
	`title`        VARCHAR(50) NOT NULL COMMENT '제목', -- 제목
	`content`      LONGTEXT    NOT NULL COMMENT '내용', -- 내용
	`reg_dt`       DATE        NOT NULL DEFAULT current_timestamp() COMMENT '작성일', -- 작성일
	`view_cnt`     INTEGER     NOT NULL DEFAULT 0 COMMENT '조회수', -- 조회수
	`act_local_no` INTEGER     NOT NULL COMMENT '활동지역번호', -- 활동지역번호
	`pups_no`      INTEGER     NOT NULL COMMENT '모임목적번호', -- 모임목적번호
	`memb_no`      INTEGER     NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT 'pickme게시판';

-- pickme게시판
ALTER TABLE `gms_pickme`
	ADD CONSTRAINT `PK_gms_pickme` -- pickme게시판 기본키
		PRIMARY KEY (
			`pm_b_no` -- 픽미게시판번호
		);

ALTER TABLE `gms_pickme`
	MODIFY COLUMN `pm_b_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '픽미게시판번호';

ALTER TABLE `gms_pickme`
	AUTO_INCREMENT = 1;

-- 신고
CREATE TABLE `gms_report` (
	`rpt_no`      INTEGER     NOT NULL COMMENT '신고번호', -- 신고번호
	`title`       VARCHAR(50) NOT NULL COMMENT '신고제목', -- 신고제목
	`content`     LONGTEXT    NOT NULL COMMENT '신고내용', -- 신고내용
	`valid`       BOOLEAN     NOT NULL DEFAULT 0 COMMENT '유효여부', -- 유효여부
	`memb_no`     INTEGER     NOT NULL COMMENT '작성자회원', -- 작성자회원
	`rpt_cate_no` INTEGER     NOT NULL COMMENT '신고유형번호', -- 신고유형번호
	`reported`    INTEGER     NOT NULL COMMENT '피신고대상번호' -- 피신고대상번호
)
COMMENT '신고';

-- 신고
ALTER TABLE `gms_report`
	ADD CONSTRAINT `PK_gms_report` -- 신고 기본키
		PRIMARY KEY (
			`rpt_no` -- 신고번호
		);

ALTER TABLE `gms_report`
	MODIFY COLUMN `rpt_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '신고번호';

ALTER TABLE `gms_report`
	AUTO_INCREMENT = 1;

-- 신고유형
CREATE TABLE `gms_report_cate` (
	`rpt_cate_no` INTEGER     NOT NULL COMMENT '신고유형번호', -- 신고유형번호
	`name`        VARCHAR(50) NOT NULL COMMENT '신고유형명' -- 신고유형명
)
COMMENT '신고유형';

-- 신고유형
ALTER TABLE `gms_report_cate`
	ADD CONSTRAINT `PK_gms_report_cate` -- 신고유형 기본키
		PRIMARY KEY (
			`rpt_cate_no` -- 신고유형번호
		);

ALTER TABLE `gms_report_cate`
	MODIFY COLUMN `rpt_cate_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '신고유형번호';

ALTER TABLE `gms_report_cate`
	AUTO_INCREMENT = 1;

-- 초대장저장공간
CREATE TABLE `gms_invite_box` (
	`invite_no` INTEGER      NOT NULL COMMENT '초대장번호', -- 초대장번호
	`title`     VARCHAR(255) NOT NULL COMMENT '제목', -- 제목
	`content`   LONGTEXT     NOT NULL COMMENT '내용', -- 내용
	`confirm`   BOOLEAN      NULL     DEFAULT 0 COMMENT '읽음', -- 읽음
	`reg_dt`    DATE         NOT NULL DEFAULT current_timestamp() COMMENT '날짜', -- 날짜
	`memb_no`   INTEGER      NOT NULL COMMENT '초대받은회원번호', -- 초대받은회원번호
	`memb_no2`  INTEGER      NOT NULL COMMENT '초대한회원번호', -- 초대한회원번호
	`g_no`      INTEGER      NOT NULL COMMENT '모임번호' -- 모임번호
)
COMMENT '초대장저장공간';

-- 초대장저장공간
ALTER TABLE `gms_invite_box`
	ADD CONSTRAINT `PK_gms_invite_box` -- 초대장저장공간 기본키
		PRIMARY KEY (
			`invite_no` -- 초대장번호
		);

ALTER TABLE `gms_invite_box`
	MODIFY COLUMN `invite_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '초대장번호';

ALTER TABLE `gms_invite_box`
	AUTO_INCREMENT = 1;

-- 모임가입신청서질문목록
CREATE TABLE `gms_applyform_question` (
	`appl_question_no` INTEGER     NOT NULL COMMENT '모임가입신청서질문목록번호', -- 모임가입신청서질문목록번호
	`appl_no`          INTEGER     NOT NULL COMMENT '모임가입신청서 번호', -- 모임가입신청서 번호
	`question_name`    VARCHAR(50) NULL     COMMENT '질문이름' -- 질문이름
)
COMMENT '모임가입신청서질문목록';

-- 모임가입신청서질문목록
ALTER TABLE `gms_applyform_question`
	ADD CONSTRAINT `PK_gms_applyform_question` -- 모임가입신청서질문목록 기본키
		PRIMARY KEY (
			`appl_question_no` -- 모임가입신청서질문목록번호
		);

ALTER TABLE `gms_applyform_question`
	MODIFY COLUMN `appl_question_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '모임가입신청서질문목록번호';

ALTER TABLE `gms_applyform_question`
	AUTO_INCREMENT = 1;

-- 모임가입신청서질문목록답변
CREATE TABLE `gms_applyform_answer` (
	`appl_answer_no`   INTEGER  NOT NULL COMMENT '모임가입신청서질문목록답변', -- 모임가입신청서질문목록답변
	`answer`           LONGTEXT NOT NULL COMMENT '답변', -- 답변
	`appl_question_no` INTEGER  NOT NULL COMMENT '모임가입신청서질문목록번호', -- 모임가입신청서질문목록번호
	`appl_dt`          DATE     NOT NULL DEFAULT current_timestamp() COMMENT '신청일' -- 신청일
)
COMMENT '모임가입신청서질문목록답변';

-- 모임가입신청서질문목록답변
ALTER TABLE `gms_applyform_answer`
	ADD CONSTRAINT `PK_gms_applyform_answer` -- 모임가입신청서질문목록답변 기본키
		PRIMARY KEY (
			`appl_answer_no` -- 모임가입신청서질문목록답변
		);

ALTER TABLE `gms_applyform_answer`
	MODIFY COLUMN `appl_answer_no` INTEGER NOT NULL AUTO_INCREMENT COMMENT '모임가입신청서질문목록답변';

ALTER TABLE `gms_applyform_answer`
	AUTO_INCREMENT = 1;

-- 최근검색어
ALTER TABLE `gms_keyword`
	ADD CONSTRAINT `FK_gms_memb_TO_gms_keyword` -- 회원 -> 최근검색어
		FOREIGN KEY (
			`memb_no` -- 회원번호
		)
		REFERENCES `gms_memb` ( -- 회원
			`memb_no` -- 회원번호
		);

-- 소모임
ALTER TABLE `gms_group`
	ADD CONSTRAINT `FK_gms_activelocal_TO_gms_group` -- 활동지역 -> 소모임
		FOREIGN KEY (
			`act_local_no` -- 활동지역번호
		)
		REFERENCES `gms_activelocal` ( -- 활동지역
			`act_local_no` -- 활동지역번호
		);

-- 소모임
ALTER TABLE `gms_group`
	ADD CONSTRAINT `FK_gms_purpose_TO_gms_group` -- 모임목적 -> 소모임
		FOREIGN KEY (
			`pups_no` -- 모임목적번호
		)
		REFERENCES `gms_purpose` ( -- 모임목적
			`pups_no` -- 모임목적번호
		);

-- 모임태그
ALTER TABLE `gms_grouptag`
	ADD CONSTRAINT `FK_gms_group_TO_gms_grouptag` -- 소모임 -> 모임태그
		FOREIGN KEY (
			`g_no` -- 모임번호
		)
		REFERENCES `gms_group` ( -- 소모임
			`g_no` -- 모임번호
		);

-- 모임가입신청서
ALTER TABLE `gms_applyform`
	ADD CONSTRAINT `FK_gms_group_TO_gms_applyform` -- 소모임 -> 모임가입신청서
		FOREIGN KEY (
			`g_no` -- 모임번호
		)
		REFERENCES `gms_group` ( -- 소모임
			`g_no` -- 모임번호
		);

-- 모임가입신청서
ALTER TABLE `gms_applyform`
	ADD CONSTRAINT `FK_gms_memb_TO_gms_applyform` -- 회원 -> 모임가입신청서
		FOREIGN KEY (
			`memb_no` -- 회원번호
		)
		REFERENCES `gms_memb` ( -- 회원
			`memb_no` -- 회원번호
		);

-- 모임게시글
ALTER TABLE `gms_board`
	ADD CONSTRAINT `FK_gms_join_memb_TO_gms_board` -- 모임맴버 -> 모임게시글
		FOREIGN KEY (
			`memb_no`, -- 회원번호
			`g_no`     -- 모임번호
		)
		REFERENCES `gms_join_memb` ( -- 모임맴버
			`memb_no`, -- 회원번호
			`g_no`     -- 모임번호
		);

-- 모임내부 게시글 댓글
ALTER TABLE `gms_comment`
	ADD CONSTRAINT `FK_gms_board_TO_gms_comment` -- 모임게시글 -> 모임내부 게시글 댓글
		FOREIGN KEY (
			`b_no` -- 게시글번호
		)
		REFERENCES `gms_board` ( -- 모임게시글
			`b_no` -- 게시글번호
		);

-- 모임내부 게시글 댓글
ALTER TABLE `gms_comment`
	ADD CONSTRAINT `FK_gms_join_memb_TO_gms_comment` -- 모임맴버 -> 모임내부 게시글 댓글
		FOREIGN KEY (
			`memb_no`, -- 회원번호
			`g_no`     -- 모임번호
		)
		REFERENCES `gms_join_memb` ( -- 모임맴버
			`memb_no`, -- 회원번호
			`g_no`     -- 모임번호
		);

-- 모임게시글사진
ALTER TABLE `gms_photo`
	ADD CONSTRAINT `FK_gms_board_TO_gms_photo` -- 모임게시글 -> 모임게시글사진
		FOREIGN KEY (
			`b_no` -- 게시글번호
		)
		REFERENCES `gms_board` ( -- 모임게시글
			`b_no` -- 게시글번호
		);

-- 모임내부 일정
ALTER TABLE `gms_calendar`
	ADD CONSTRAINT `FK_gms_group_TO_gms_calendar` -- 소모임 -> 모임내부 일정
		FOREIGN KEY (
			`g_no` -- 모임번호
		)
		REFERENCES `gms_group` ( -- 소모임
			`g_no` -- 모임번호
		);

-- 모임내부 첨부파일
ALTER TABLE `gms_file`
	ADD CONSTRAINT `FK_gms_board_TO_gms_file` -- 모임게시글 -> 모임내부 첨부파일
		FOREIGN KEY (
			`b_no` -- 게시글번호
		)
		REFERENCES `gms_board` ( -- 모임게시글
			`b_no` -- 게시글번호
		);

-- 모임맴버
ALTER TABLE `gms_join_memb`
	ADD CONSTRAINT `FK_gms_memb_TO_gms_join_memb` -- 회원 -> 모임맴버
		FOREIGN KEY (
			`memb_no` -- 회원번호
		)
		REFERENCES `gms_memb` ( -- 회원
			`memb_no` -- 회원번호
		);

-- 모임맴버
ALTER TABLE `gms_join_memb`
	ADD CONSTRAINT `FK_gms_group_TO_gms_join_memb` -- 소모임 -> 모임맴버
		FOREIGN KEY (
			`g_no` -- 모임번호
		)
		REFERENCES `gms_group` ( -- 소모임
			`g_no` -- 모임번호
		);

-- 모임맴버
ALTER TABLE `gms_join_memb`
	ADD CONSTRAINT `FK_gms_memb_grade_TO_gms_join_memb` -- 모임등급유형 -> 모임맴버
		FOREIGN KEY (
			`g_memb_grd_no` -- 모임등급유형번호
		)
		REFERENCES `gms_memb_grade` ( -- 모임등급유형
			`g_memb_grd_no` -- 모임등급유형번호
		);

-- 모금
ALTER TABLE `gms_accounting`
	ADD CONSTRAINT `FK_gms_group_TO_gms_accounting` -- 소모임 -> 모금
		FOREIGN KEY (
			`g_no` -- 모임번호
		)
		REFERENCES `gms_group` ( -- 소모임
			`g_no` -- 모임번호
		);

-- 모금
ALTER TABLE `gms_accounting`
	ADD CONSTRAINT `FK_gms_accounting_cate_TO_gms_accounting` -- 모금유형 -> 모금
		FOREIGN KEY (
			`act_cate_no` -- 모금유형번호
		)
		REFERENCES `gms_accounting_cate` ( -- 모금유형
			`act_cate_no` -- 모금유형번호
		);

-- 맴버장소입력
ALTER TABLE `gms_select_midpoint`
	ADD CONSTRAINT `FK_gms_join_memb_TO_gms_select_midpoint` -- 모임맴버 -> 맴버장소입력
		FOREIGN KEY (
			`memb_no`, -- 회원번호
			`g_no`     -- 모임번호
		)
		REFERENCES `gms_join_memb` ( -- 모임맴버
			`memb_no`, -- 회원번호
			`g_no`     -- 모임번호
		);

-- 고객센터 자주하는 질문
ALTER TABLE `gms_faq`
	ADD CONSTRAINT `FK_gms_main_cate_TO_gms_faq` -- 메인카테고리 -> 고객센터 자주하는 질문
		FOREIGN KEY (
			`main_cate_no` -- 메인카테고리번호
		)
		REFERENCES `gms_main_cate` ( -- 메인카테고리
			`main_cate_no` -- 메인카테고리번호
		);

-- 고객센터 자주하는 질문
ALTER TABLE `gms_faq`
	ADD CONSTRAINT `FK_gms_query_cate_TO_gms_faq` -- 질문유형 -> 고객센터 자주하는 질문
		FOREIGN KEY (
			`query_no` -- 질문유형번호
		)
		REFERENCES `gms_query_cate` ( -- 질문유형
			`query_no` -- 질문유형번호
		);

-- 고객센터 공지사항
ALTER TABLE `gms_notice`
	ADD CONSTRAINT `FK_gms_main_cate_TO_gms_notice` -- 메인카테고리 -> 고객센터 공지사항
		FOREIGN KEY (
			`main_cate_no` -- 메인카테고리번호
		)
		REFERENCES `gms_main_cate` ( -- 메인카테고리
			`main_cate_no` -- 메인카테고리번호
		);

-- 고객센터 문의하기
ALTER TABLE `gms_notice_question`
	ADD CONSTRAINT `FK_gms_main_cate_TO_gms_notice_question` -- 메인카테고리 -> 고객센터 문의하기
		FOREIGN KEY (
			`main_cate_no` -- 메인카테고리번호
		)
		REFERENCES `gms_main_cate` ( -- 메인카테고리
			`main_cate_no` -- 메인카테고리번호
		);

-- 고객센터 문의하기
ALTER TABLE `gms_notice_question`
	ADD CONSTRAINT `FK_gms_query_cate_TO_gms_notice_question` -- 질문유형 -> 고객센터 문의하기
		FOREIGN KEY (
			`query_no` -- 질문유형번호
		)
		REFERENCES `gms_query_cate` ( -- 질문유형
			`query_no` -- 질문유형번호
		);

-- 고객센터 문의하기
ALTER TABLE `gms_notice_question`
	ADD CONSTRAINT `FK_gms_memb_TO_gms_notice_question` -- 회원 -> 고객센터 문의하기
		FOREIGN KEY (
			`memb_no` -- 회원번호
		)
		REFERENCES `gms_memb` ( -- 회원
			`memb_no` -- 회원번호
		);

-- 관심활동지역
ALTER TABLE `gms_final_activelocal`
	ADD CONSTRAINT `FK_gms_memb_TO_gms_final_activelocal` -- 회원 -> 관심활동지역
		FOREIGN KEY (
			`memb_no` -- 회원번호
		)
		REFERENCES `gms_memb` ( -- 회원
			`memb_no` -- 회원번호
		);

-- 관심활동지역
ALTER TABLE `gms_final_activelocal`
	ADD CONSTRAINT `FK_gms_activelocal_TO_gms_final_activelocal` -- 활동지역 -> 관심활동지역
		FOREIGN KEY (
			`act_local_no` -- 활동지역번호
		)
		REFERENCES `gms_activelocal` ( -- 활동지역
			`act_local_no` -- 활동지역번호
		);

-- 관심모임목적
ALTER TABLE `gms_final_purpose`
	ADD CONSTRAINT `FK_gms_purpose_TO_gms_final_purpose` -- 모임목적 -> 관심모임목적
		FOREIGN KEY (
			`pups_no` -- 모임목적번호
		)
		REFERENCES `gms_purpose` ( -- 모임목적
			`pups_no` -- 모임목적번호
		);

-- 관심모임목적
ALTER TABLE `gms_final_purpose`
	ADD CONSTRAINT `FK_gms_memb_TO_gms_final_purpose` -- 회원 -> 관심모임목적
		FOREIGN KEY (
			`memb_no` -- 회원번호
		)
		REFERENCES `gms_memb` ( -- 회원
			`memb_no` -- 회원번호
		);

-- 모금납부내역
ALTER TABLE `gms_accounting_status`
	ADD CONSTRAINT `FK_gms_accounting_TO_gms_accounting_status` -- 모금 -> 모금납부내역
		FOREIGN KEY (
			`act_no` -- 모금번호
		)
		REFERENCES `gms_accounting` ( -- 모금
			`act_no` -- 모금번호
		);

-- 모금납부내역
ALTER TABLE `gms_accounting_status`
	ADD CONSTRAINT `FK_gms_join_memb_TO_gms_accounting_status` -- 모임맴버 -> 모금납부내역
		FOREIGN KEY (
			`memb_no`, -- 회원번호
			`g_no`     -- 모임번호
		)
		REFERENCES `gms_join_memb` ( -- 모임맴버
			`memb_no`, -- 회원번호
			`g_no`     -- 모임번호
		);

-- 모임게시글스크랩
ALTER TABLE `gms_scrap`
	ADD CONSTRAINT `FK_gms_join_memb_TO_gms_scrap` -- 모임맴버 -> 모임게시글스크랩
		FOREIGN KEY (
			`memb_no`, -- 회원번호
			`g_no`     -- 모임번호
		)
		REFERENCES `gms_join_memb` ( -- 모임맴버
			`memb_no`, -- 회원번호
			`g_no`     -- 모임번호
		);

-- 모임게시글스크랩
ALTER TABLE `gms_scrap`
	ADD CONSTRAINT `FK_gms_board_TO_gms_scrap` -- 모임게시글 -> 모임게시글스크랩
		FOREIGN KEY (
			`b_no` -- 게시글번호
		)
		REFERENCES `gms_board` ( -- 모임게시글
			`b_no` -- 게시글번호
		);

-- SNS계정
ALTER TABLE `gms_final_SNS`
	ADD CONSTRAINT `FK_gms_memb_TO_gms_final_SNS` -- 회원 -> SNS계정
		FOREIGN KEY (
			`memb_no` -- 회원번호
		)
		REFERENCES `gms_memb` ( -- 회원
			`memb_no` -- 회원번호
		);

-- SNS계정
ALTER TABLE `gms_final_SNS`
	ADD CONSTRAINT `FK_gms_SNS_TO_gms_final_SNS` -- SNS -> SNS계정
		FOREIGN KEY (
			`SNS_no` -- SNS번호
		)
		REFERENCES `gms_SNS` ( -- SNS
			`SNS_no` -- SNS번호
		);

-- pickme게시판
ALTER TABLE `gms_pickme`
	ADD CONSTRAINT `FK_gms_activelocal_TO_gms_pickme` -- 활동지역 -> pickme게시판
		FOREIGN KEY (
			`act_local_no` -- 활동지역번호
		)
		REFERENCES `gms_activelocal` ( -- 활동지역
			`act_local_no` -- 활동지역번호
		);

-- pickme게시판
ALTER TABLE `gms_pickme`
	ADD CONSTRAINT `FK_gms_purpose_TO_gms_pickme` -- 모임목적 -> pickme게시판
		FOREIGN KEY (
			`pups_no` -- 모임목적번호
		)
		REFERENCES `gms_purpose` ( -- 모임목적
			`pups_no` -- 모임목적번호
		);

-- pickme게시판
ALTER TABLE `gms_pickme`
	ADD CONSTRAINT `FK_gms_memb_TO_gms_pickme` -- 회원 -> pickme게시판
		FOREIGN KEY (
			`memb_no` -- 회원번호
		)
		REFERENCES `gms_memb` ( -- 회원
			`memb_no` -- 회원번호
		);

-- 신고
ALTER TABLE `gms_report`
	ADD CONSTRAINT `FK_gms_report_cate_TO_gms_report` -- 신고유형 -> 신고
		FOREIGN KEY (
			`rpt_cate_no` -- 신고유형번호
		)
		REFERENCES `gms_report_cate` ( -- 신고유형
			`rpt_cate_no` -- 신고유형번호
		);

-- 신고
ALTER TABLE `gms_report`
	ADD CONSTRAINT `FK_gms_memb_TO_gms_report` -- 회원 -> 신고
		FOREIGN KEY (
			`memb_no` -- 작성자회원
		)
		REFERENCES `gms_memb` ( -- 회원
			`memb_no` -- 회원번호
		);

-- 초대장저장공간
ALTER TABLE `gms_invite_box`
	ADD CONSTRAINT `FK_gms_memb_TO_gms_invite_box` -- 회원 -> 초대장저장공간
		FOREIGN KEY (
			`memb_no` -- 초대받은회원번호
		)
		REFERENCES `gms_memb` ( -- 회원
			`memb_no` -- 회원번호
		);

-- 초대장저장공간
ALTER TABLE `gms_invite_box`
	ADD CONSTRAINT `FK_gms_join_memb_TO_gms_invite_box` -- 모임맴버 -> 초대장저장공간
		FOREIGN KEY (
			`memb_no2`, -- 초대한회원번호
			`g_no`      -- 모임번호
		)
		REFERENCES `gms_join_memb` ( -- 모임맴버
			`memb_no`, -- 회원번호
			`g_no`     -- 모임번호
		);

-- 모임가입신청서질문목록
ALTER TABLE `gms_applyform_question`
	ADD CONSTRAINT `FK_gms_applyform_TO_gms_applyform_question` -- 모임가입신청서 -> 모임가입신청서질문목록
		FOREIGN KEY (
			`appl_no` -- 모임가입신청서 번호
		)
		REFERENCES `gms_applyform` ( -- 모임가입신청서
			`appl_no` -- 모임가입신청서 번호
		);

-- 모임가입신청서질문목록답변
ALTER TABLE `gms_applyform_answer`
	ADD CONSTRAINT `FK_gms_applyform_question_TO_gms_applyform_answer` -- 모임가입신청서질문목록 -> 모임가입신청서질문목록답변
		FOREIGN KEY (
			`appl_question_no` -- 모임가입신청서질문목록번호
		)
		REFERENCES `gms_applyform_question` ( -- 모임가입신청서질문목록
			`appl_question_no` -- 모임가입신청서질문목록번호
		);