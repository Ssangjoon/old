package com.bts.yomojomo.service;

import java.util.List;
import com.bts.yomojomo.domain.Calendar;
import com.bts.yomojomo.domain.Destination;

public interface DestinationService {
  List<Destination> list();

  int add(Destination destination);

  Destination get(int no);

  int update(Destination destination);

  int delete(Destination destination);
}
