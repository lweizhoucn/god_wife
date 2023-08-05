package com.lweizhou.cai.godwife.service;

import com.lweizhou.cai.godwife.dao.ClientDao;
import com.lweizhou.cai.godwife.dao.OrderDao;
import com.lweizhou.cai.godwife.dao.SkuDao;
import com.lweizhou.cai.godwife.dao.model.ClientInfo;
import com.lweizhou.cai.godwife.dao.model.OrderInfo;
import com.lweizhou.cai.godwife.dao.model.SkuInfo;
import com.lweizhou.cai.godwife.model.request.OrderRequest;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.stereotype.Service;

import java.beans.BeanProperty;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private ClientDao clientDao;

    @Resource
    private SkuDao skuDao;

    public List<OrderInfo> list() {

        return orderDao.findAll();
    }

    public OrderInfo add(OrderRequest orderRequest) {


        Long skuId = orderRequest.getSkuId();
        SkuInfo skuInfo = skuDao.findById(skuId).orElseThrow();

        Long clientId = orderRequest.getClientId();
        ClientInfo clientInfo = clientDao.findById(clientId).orElseThrow();

        double price = skuInfo.getPrice() * orderRequest.getOrderCount();

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setSkuInfo(skuInfo);
        orderInfo.setClientInfo(clientInfo);
        orderInfo.setOrderPrice(price);

        orderInfo.setState(0);
        orderInfo.setOrderCount(orderRequest.getOrderCount());
        orderInfo.setOrderDate(LocalDate.now());
        if (orderRequest.getSendDate() == null) {
            orderInfo.setSendDate(LocalDate.now());
        } else {
            orderInfo.setSendDate(orderRequest.getSendDate());
        }
        return orderDao.save(orderInfo);
    }
}
