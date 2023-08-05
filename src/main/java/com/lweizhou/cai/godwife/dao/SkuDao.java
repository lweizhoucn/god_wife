package com.lweizhou.cai.godwife.dao;

import com.lweizhou.cai.godwife.dao.model.SkuInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuDao extends JpaRepository<SkuInfo, Long>, JpaSpecificationExecutor<SkuInfo> {
}
