package com.lweizhou.cai.godwife.dao;

import com.lweizhou.cai.godwife.dao.model.ClientInfo;
import com.lweizhou.cai.godwife.dao.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<OrderInfo,Long> {

  public   Long countByClientInfo(ClientInfo clientInfo);
}
