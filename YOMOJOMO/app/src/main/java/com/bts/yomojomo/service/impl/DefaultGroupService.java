package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bts.yomojomo.dao.GroupDao;
import com.bts.yomojomo.domain.Group;
import com.bts.yomojomo.service.GroupService;

// 서비스객체 -> 업무로직, 트랜잭션 제어
// => 일관성을 위해 반드시 서비스 객체를 통해 DAO를 사용한다.
@Service // Spring IoC 컨테이너가 객체를 만들어 저장할 때 클래스 이름을 사용한다. 예) defaultGroupService
public class DefaultGroupService implements GroupService {

  @Autowired
  GroupDao groupDao;

  @Override
  @Transactional
  public int add(Group group) {
    groupDao.insert(group);
    System.out.println(group.getNo());
    System.out.println(group.getTags());
    groupDao.insertTags(group.getNo(), group.getTags());
    groupDao.insertJoinMember(group.getNo(), group.getGradeNo(), group.getMemberNo());
    return 1;
  }

  @Override
  public List<Group> list() {
    return groupDao.findAll();
  }

  @Override
  public List<Group> siList(Group group) {
    return groupDao.selectedSicate(group);
  }

  @Override
  public List<Group> guList(Group group) {
    return groupDao.selectedGucate(group);
  }

  @Override
  public List<Group> selectedPurpcate(Group group) {
    return groupDao.selectedPurpcate(group);
  }

  @Override
  public Group get(int no) {
    Group group = groupDao.findByNo(no);
    // if (group != null) {
    // groupDao.increaseViewCount(no);
    // }
    return group;
  }

  @Override
  public Group getview(int no) {
    return groupDao.findByGNo(no);
  }
  @Override
  public int update(Group group) {
    return groupDao.update(group);
  }

  @Override
  public int delete(int no) {
    return groupDao.delete(no);
  }

  @Override
  public int updateStatus(int no, int status) {
    return groupDao.updateStatus(no, status);
  }

  @Override
  public List<Group> listselect(int no, int cutno, String searchKeyword) {
    return groupDao.getBoardListSelect(no, cutno, searchKeyword);
  }

  @Override
  public int countSelect(String searchKeyword) {
    return groupDao.getBoardListSelectCount(searchKeyword);
  }
}
