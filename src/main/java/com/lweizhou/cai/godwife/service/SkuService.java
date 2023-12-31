package com.lweizhou.cai.godwife.service;

import com.lweizhou.cai.godwife.dao.SkuDao;
import com.lweizhou.cai.godwife.dao.model.SkuInfo;
import com.lweizhou.cai.godwife.model.PageResultInfo;
import com.lweizhou.cai.godwife.model.request.SkuRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SkuService {

    @Resource
    private SkuDao skuDao;


    public PageResultInfo<SkuInfo> list(SkuRequest request) {
        Pageable pageRequest = PageRequest.of(Math.max(request.getCurrent() - 1, 0), request.getPageSize(), Sort.by(Sort.Direction.DESC, "updateTime"));
        Specification<SkuInfo> specification = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if (request.getState() != null && request.getState() >= 0) {
                list.add(cb.equal(root.get("state"), request.getState()));
            } else {
                list.add(cb.ge(root.get("state"), 0));
            }
            if (StringUtils.isNotEmpty(request.getName())) {
                list.add(cb.like(root.get("name"), "%" + request.getName() + "%"));
            }
            if (request.getSendDate() != null) {
                list.add(cb.equal(root.get("sendDate"), Date.from(request.getSendDate().atStartOfDay(ZoneId.systemDefault()).toInstant())));
            }

            return query.where(list.toArray(new Predicate[0])).getRestriction();
        };
        Page<SkuInfo> page = skuDao.findAll(specification,pageRequest);
        List<SkuInfo> list = page.getContent();
        PageResultInfo<SkuInfo> resultInfo = PageResultInfo.ofList(list, page.getTotalElements());
        resultInfo.setTotal(page.getTotalElements());
        resultInfo.setPageSize(request.getPageSize());
        resultInfo.setCurrent(request.getCurrent());
        return resultInfo;
    }

    public SkuInfo save(SkuRequest skuRequest) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setId(skuRequest.getId());
        skuInfo.setName(skuRequest.getName());
        skuInfo.setPrice(skuRequest.getPrice());
        skuInfo.setDescription(skuRequest.getDescription());
        if (skuRequest.getCloseTime() != null) {
            skuInfo.setCloseTime(Date.from(skuRequest.getCloseTime().toInstant(ZoneOffset.UTC)));
        }
        if (skuRequest.getSendDate() != null) {
            skuInfo.setSendDate(Date.from(skuRequest.getSendDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
        skuInfo.setState(skuRequest.getState());
        return skuDao.save(skuInfo);
    }

    public boolean exist(Long id) {
        return skuDao.existsById(id);
    }

    public void delete(Long id) {

        SkuInfo skuInfo = skuDao.findById(id).orElseThrow(IllegalArgumentException::new);
        skuInfo.setState(-1);
        skuDao.save(skuInfo);
    }
}
