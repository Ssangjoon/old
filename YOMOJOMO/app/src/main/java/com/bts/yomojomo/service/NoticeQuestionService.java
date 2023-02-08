package com.bts.yomojomo.service;

import java.util.List;
import com.bts.yomojomo.domain.NoticeQuestion;

public interface NoticeQuestionService {
  int add(NoticeQuestion noticeQuestion);

  int update(NoticeQuestion noticeQuestion);
  
  int updateAnswer(NoticeQuestion noticeQuestion);

  int delete(int no);
  
	public NoticeQuestion select(int no);
	
	List<NoticeQuestion> listselect(int no, int cutno, String searchKeyword);

	int countSelect(String searchKeyword);

	int updateStatus(int no, int status);

}
