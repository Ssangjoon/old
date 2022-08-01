package com.simple_board.adm.service;

import java.util.List;
import com.simple_board.adm.vo.MemberVO;
import com.simple_board.utils.PagingVO;

public interface AdmService2 {
  //	// 로그인 조회
  //	public MemberVO selectLogin(MemberVO vo) throws Exception;
  //	// 로그인 성공시 업데이트
  //	public void updateLogin(MemberVO vo) throws Exception;
  //	// 로그인 실패시 업데이트
  //	public void updateFailLogin(MemberVO vo) throws Exception;
  //	// 로그인 계정 찾기
  //	public MemberVO selectEmailInfo(String email) throws Exception;
  //	// 로그인 계정 찾기
  //	public void updatePassword(MemberVO vo) throws Exception;

  //	// 관리자 수
  //	public int countMemberList(PagingVO vo) throws Exception;
  // 관리자 리스츠 조회
  public List<MemberVO> selectMemberList(PagingVO vo) throws Exception;
  //	// 관리자 조회
  //	public MemberVO selectMember(MemberVO vo) throws Exception;
  //	// 관리자 등록 
  //	public void insertMemberList(MemberVO vo) throws Exception;
  //	// 관리자 수정
  //	public void updateMember(MemberVO vo) throws Exception;
  //	// 관리자 제거
  //	public void deleteMember(MemberVO vo) throws Exception;

  //	// Notice 수
  //	public int countNoticeList(PagingVO vo) throws Exception;
  //	// Notice 리스트 조회
  //	public List<NoticeVO> selectNoticeList(PagingVO vo) throws Exception;
  //	// Notice 조회
  //	public NoticeVO selectNotice(NoticeVO vo) throws Exception;
  //	// Notice 등록 
  //	public void insertNoticeList(NoticeVO vo, MultipartHttpServletRequest mpRequest) throws Exception;
  //	// Notice 파일 조회
  //	public List<Map<String, Object>> selectFileList(int notice_seq) throws Exception;
  //	// Notice 파일 제거
  //	public void deleteFile(int seq) throws Exception;
  //	// 첨부파일 다운로드
  //	public Map<String, Object> selectFileInfo(int seq) throws Exception;
  //	// Notice 수정
  //	public void updateNotice(NoticeVO vo, MultipartHttpServletRequest mpRequest) throws Exception;
  //	// Notice 제거
  //	public void deleteNotice(NoticeVO vo) throws Exception;
  //	
  //	// FAQ 수
  //	public int countFaqList(PagingVO vo) throws Exception;
  //	// FAQ 리스트 조회
  //	public List<FaqVO> selectFaqList(PagingVO vo) throws Exception;
  //	// FAQ 조회
  //	public FaqVO selectFaq(FaqVO vo) throws Exception;
  //	// FAQ 등록 
  //	public void insertFaqList(FaqVO vo) throws Exception;
  //	// FAQ 수정
  //	public void updateFaq(FaqVO vo) throws Exception;
  //	// FAQ 제거
  //	public void deleteFaq(FaqVO vo) throws Exception;
}
