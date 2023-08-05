package com.lweizhou.cai.godwife.dao;

import com.lweizhou.cai.godwife.dao.model.ClientInfo;
import com.lweizhou.cai.godwife.model.request.ClientRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDao extends JpaRepository<ClientInfo, Long>, JpaSpecificationExecutor<ClientInfo> {

//    List<ClientInfo> list();

//    int add(ClientInfo clientInfo);
}
