package com.lweizhou.cai.godwife.service;

import com.lweizhou.cai.godwife.dao.ClientDao;
import com.lweizhou.cai.godwife.dao.OrderDao;
import com.lweizhou.cai.godwife.dao.model.ClientInfo;
import com.lweizhou.cai.godwife.model.ClientResponse;
import com.lweizhou.cai.godwife.model.PageResultInfo;
import com.lweizhou.cai.godwife.model.ResultInfo;
import com.lweizhou.cai.godwife.model.request.ClientRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Resource
    private ClientDao clientDao;
    @Resource
    private OrderDao orderDao;

    public PageResultInfo<ClientResponse> list(ClientRequest request) {
        Pageable pageRequest = PageRequest.of(Math.max(request.getCurrent() - 1, 0), request.getPageSize());
        Specification<ClientInfo> specification = (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if (request.getRoomNumber() > 0) {
                list.add(
                        cb.equal(root.<ClientInfo>get("roomNumber"), request.getRoomNumber()));
            }
            if (request.getBuilding() > 0) {
                list.add(
                        cb.equal(root.get("building"), request.getBuilding()));
            }
            if (request.getState() > 0) {
                list.add(
                        cb.equal(root.get("state"), request.getState()));
            }
            if (StringUtils.isNotEmpty(request.getPhoneNumber())) {
                list.add(
                        cb.like(root.get("phoneNumber"), "%" + request.getPhoneNumber() + "%"));
            }
            if (StringUtils.isNotEmpty(request.getNickName())) {
                list.add(
                        cb.like(root.get("nickName"), "%" + request.getNickName() + "%"));
            }
            return query.where(list.toArray(new Predicate[0])).getRestriction();
        };
        Page<ClientInfo> page = clientDao.findAll(specification, pageRequest);
        List<ClientInfo> list = page.getContent();

        List<ClientResponse> clientResponses = new ArrayList<>();
        for (ClientInfo clientInfo : list) {
            ClientResponse clientResponse = new ClientResponse();
            BeanUtils.copyProperties(clientInfo,clientResponse);
            clientResponse.setHistoryOrderCount(orderDao.countByClientInfo(clientInfo));
            clientResponses.add(clientResponse);
        }

        PageResultInfo<ClientResponse> resultInfo = PageResultInfo.ofList(clientResponses, page.getTotalElements());
        resultInfo.setTotal(page.getTotalElements());
        resultInfo.setPageSize(request.getPageSize());
        resultInfo.setCurrent(request.getCurrent());

        return resultInfo;
    }

    public ResultInfo<ClientInfo> listSelect(String keyWord) {
        Pageable pageRequest = PageRequest.of(0, 10);
        if (StringUtils.isEmpty(keyWord)) {
            List<ClientInfo> list = clientDao.findAll(pageRequest).getContent();
            return ResultInfo.ofList(list);
        }
        Specification<ClientInfo> specification = (root, query, cb) -> {
            Predicate p1 = cb.like(root.get("phoneNumber"), "%" + keyWord + "%");
            Predicate p2 = cb.like(root.get("roomNumber"),  "%" + keyWord + "%");
            Predicate p3 = cb.like(root.get("building"),  "%" + keyWord + "%");
            return cb.or(p1,p2,p3);
        };
        Page<ClientInfo> page = clientDao.findAll(specification, pageRequest);
        List<ClientInfo> list = page.getContent();
        return ResultInfo.ofList(list);
    }

    public ClientInfo save(ClientInfo clientInfo) {
        return clientDao.save(clientInfo);
    }

    public boolean exist(Long id) {
        return clientDao.existsById(id);
    }

    public void delete(Long id) {
        clientDao.deleteById(id);
    }
}
