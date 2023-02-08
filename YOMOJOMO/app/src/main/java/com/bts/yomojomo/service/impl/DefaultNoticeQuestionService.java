package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bts.yomojomo.dao.NoticeQuestionDao;
import com.bts.yomojomo.domain.NoticeQuestion;
import com.bts.yomojomo.service.NoticeQuestionService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DefaultNoticeQuestionService implements NoticeQuestionService {

	@Autowired
	NoticeQuestionDao noticeQuestionDao;
	
	@Override
	public int add(NoticeQuestion noticeQuestion) {
	 return  noticeQuestionDao.insert(noticeQuestion);
	}

	@Override
  public int update(NoticeQuestion noticeQuestion) {
    return  noticeQuestionDao.update(noticeQuestion);
  }
	
	@Override
  public int updateAnswer(NoticeQuestion noticeQuestion) {
    return  noticeQuestionDao.updateAnswer(noticeQuestion);
  }

	@Override
  public int delete(int no) {
	  return  noticeQuestionDao.delete(no);
  }
	
	@Override
	public NoticeQuestion select(int no) {
		return noticeQuestionDao.findByQno(no);
	}
	
	@Override
	public List<NoticeQuestion> listselect(int no, int cutno, String searchKeyword) {
	  System.out.println(no);
	  System.out.println(cutno);
	  System.out.println(searchKeyword);
		return noticeQuestionDao.getBoardListSelect(no, cutno, searchKeyword);
	}

	@Override
	public int countSelect(String searchKeyword) {
		return noticeQuestionDao.getBoardListSelectCount(searchKeyword);
	}

	@Override
	public int updateStatus(int no, int status) {
		return noticeQuestionDao.updateStatus(no, status);
	}
}
