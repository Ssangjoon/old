package com.simple_board.adm.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.simple_board.adm.dao.AdmDAO;
import com.simple_board.adm.vo.FaqVO;
import com.simple_board.adm.vo.IdeaVO;
import com.simple_board.adm.vo.MemberVO;
import com.simple_board.adm.vo.NoticeVO;
import com.simple_board.utils.FileUtils;
import com.simple_board.utils.PagingVO; 

@Service("admService")
public class AdmServiceImpl implements AdmService {
  @Resource(name="fileUtils")
  private FileUtils fileUtils;

  @Inject
  private AdmDAO dao;

  // 로그인 조회
  @Override
  public MemberVO selectLogin(MemberVO vo) throws Exception {
    return dao.selectLogin(vo);
  };
  // 로그인 성공시 업데이트
  @Override
  public void updateLogin(MemberVO vo) throws Exception {
    dao.updateLogin(vo);
  };
  // 로그인 실패시 업데이트
  @Override
  public void updateFailLogin(MemberVO vo) throws Exception {
    dao.updateFailLogin(vo);
  };
  // 로그인 계정 찾기
  @Override
  public MemberVO selectEmailInfo(String email) throws Exception {
    return dao.selectEmailInfo(email);
  };
  // 로그인 계정 찾기
  @Override
  public void updatePassword(MemberVO vo) throws Exception {
    dao.updatePassword(vo);
  };

  // 관리자 수
  @Override
  public int countMemberList(PagingVO vo) throws Exception {
    return dao.countMemberList(vo);
  }
  // 관리자 리스트 조회
  @Override
  public List<MemberVO> selectMemberList(PagingVO vo) throws Exception {
    return dao.selectMemberList(vo);
  }
  // 관리자 조회
  @Override
  public MemberVO selectMember(MemberVO vo) throws Exception {
    return dao.selectMember(vo);
  }
  // 관리자 등록
  @Override
  public void insertMemberList(MemberVO vo) throws Exception {
    dao.insertMemberList(vo);
  }
  // 관리자 수정
  @Override
  public void updateMember(MemberVO vo) throws Exception {
    dao.updateMember(vo);
  }
  // 관리자 제거
  @Override
  public void deleteMember(MemberVO vo) throws Exception {
    dao.deleteMember(vo);
  }

  // Notice 수
  @Override
  public int countNoticeList(PagingVO vo) throws Exception {
    return dao.countNoticeList(vo);
  }
  // Notice 리스트 조회
  @Override
  public List<NoticeVO> selectNoticeList(PagingVO vo) throws Exception {
    return dao.selectNoticeList(vo);
  }
  // Notice 조회
  @Override
  public NoticeVO selectNotice(NoticeVO vo) throws Exception {
    return dao.selectNotice(vo);
  }

  // Notice 등록
  @Override
  public void insertNoticeList(NoticeVO vo, MultipartHttpServletRequest mpRequest) throws Exception {
    dao.insertNoticeList(vo);

    List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(vo, mpRequest); 
    int size = list.size();
    for(int i=0; i<size; i++){ 
      dao.insertFile(list.get(i)); 
    }
  }
  // Notice 파일 조회
  @Override
  public List<Map<String, Object>> selectFileList(int idea_seq) throws Exception {
    return dao.selectFileList(idea_seq);
  }
  // Notice 파일 제거
  @Override
  public void deleteFile(int seq) throws Exception {
    dao.deleteFile(seq);
  }
  // 첨부파일 다운로드
  @Override
  public Map<String, Object> selectFileInfo(int seq) throws Exception {
    // TODO Auto-generated method stub
    return dao.selectFileInfo(seq);
  }
  // Notice 수정
  @Override
  public void updateNotice(NoticeVO vo, MultipartHttpServletRequest mpRequest) throws Exception {
    dao.updateNotice(vo);

    List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(vo, mpRequest); 
    int size = list.size();
    for(int i=0; i<size; i++){ 
      dao.insertFile(list.get(i)); 
    }
  }
  // Notice 제거
  @Override
  public void deleteNotice(NoticeVO vo) throws Exception {
    dao.deleteNotice(vo);
  }

  // FAQ 수
  @Override
  public int countFaqList(PagingVO vo) throws Exception {
    return dao.countFaqList(vo);
  }
  // FAQ 리스트 조회
  @Override
  public List<FaqVO> selectFaqList(PagingVO vo) throws Exception {
    return dao.selectFaqList(vo);
  }
  // FAQ 조회
  @Override
  public FaqVO selectFaq(FaqVO vo) throws Exception {
    return dao.selectFaq(vo);
  }
  // FAQ 등록
  @Override
  public void insertFaqList(FaqVO vo) throws Exception {
    dao.insertFaqList(vo);
  }
  // FAQ 수정
  @Override
  public void updateFaq(FaqVO vo) throws Exception {
    dao.updateFaq(vo);
  }
  // FAQ 제거
  @Override
  public void deleteFaq(FaqVO vo) throws Exception {
    dao.deleteFaq(vo);
  }

  // Idea 수
  @Override
  public int countIdeaList(PagingVO vo) throws Exception {
    return dao.countIdeaList(vo);
  }
  // Idea 리스트 조회
  @Override
  public List<IdeaVO> selectIdeaList(PagingVO vo) throws Exception {
    return dao.selectIdeaList(vo);
  }

  //Idea 조회
  @Override
  public IdeaVO selectIdea(IdeaVO vo) throws Exception {
    return dao.selectIdea(vo);
  }

  // Idea 등록
  @Override
  public void insertIdeaList(IdeaVO vo, MultipartHttpServletRequest mpRequest) throws Exception {
    dao.insertIdeaList(vo);

    List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(vo, mpRequest); 
    int size = list.size();
    for(int i=0; i<size; i++){ 
      dao.insertFile(list.get(i)); 
    }
  }
  //  // Idea 파일 조회
  //  @Override
  //  public List<Map<String, Object>> selectFileList(int idea_seq) throws Exception {
  //    return dao.selectFileList(idea_seq);
  //  }
  //  // Idea 파일 제거
  //  @Override
  //  public void deleteFile(int seq) throws Exception {
  //    dao.deleteFile(seq);
  //  }
  //  // 첨부파일 다운로드
  //  @Override
  //  public Map<String, Object> selectFileInfo(int seq) throws Exception {
  //    // TODO Auto-generated method stub
  //    return dao.selectFileInfo(seq);
  //  }
  // Idea 수정
  @Override
  public void updateIdea(IdeaVO vo, MultipartHttpServletRequest mpRequest) throws Exception {
    dao.updateIdea(vo);

    List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(vo, mpRequest); 
    int size = list.size();
    for(int i=0; i<size; i++){ 
      dao.insertFile(list.get(i)); 
    }
  }
  // Idea 제거
  @Override
  public void deleteIdea(IdeaVO vo) throws Exception {
    dao.deleteIdea(vo);
  }
}

