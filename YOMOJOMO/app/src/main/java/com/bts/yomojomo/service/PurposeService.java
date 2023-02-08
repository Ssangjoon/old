package com.bts.yomojomo.service;

import java.util.List;
import com.bts.yomojomo.domain.Purpose;

public interface PurposeService {

  List<Purpose> list();

  Purpose get(int no);

}
