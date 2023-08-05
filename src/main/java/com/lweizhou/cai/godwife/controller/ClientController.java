package com.lweizhou.cai.godwife.controller;

import com.lweizhou.cai.godwife.dao.model.ClientInfo;
import com.lweizhou.cai.godwife.model.ResultInfo;
import com.lweizhou.cai.godwife.model.request.ClientRequest;
import com.lweizhou.cai.godwife.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("client")
@RestController
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("list")
    public ResultInfo<ClientInfo> list(ClientRequest clientRequest) {
        return clientService.list(clientRequest);
    }

    @GetMapping("list/select")
    public ResultInfo<ClientInfo> listSelect(String keyword) {
        return clientService.listSelect(keyword);
    }


    @PostMapping("add")
    public ResultInfo<ClientInfo> add(@RequestBody  ClientInfo clientInfo) {
        ClientInfo cl = clientService.save(clientInfo);
        return cl != null ? ResultInfo.success() : ResultInfo.fail();
    }

    @PostMapping("update")
    public ResultInfo<ClientInfo> update(@RequestBody ClientInfo clientInfo) {

        if (clientInfo.getId() == null) {
            return ResultInfo.fail("client id is null");
        }
        if (!clientService.exist(clientInfo.getId())) {
            return ResultInfo.fail("client id not exist");
        }

        ClientInfo cl = clientService.save(clientInfo);
        return cl != null ? ResultInfo.success() : ResultInfo.fail();
    }

    @GetMapping("delete")
    public ResultInfo<ClientInfo> delete(Long id) {
        if (id == null) {
            return ResultInfo.fail("client id is null");
        }
        if (!clientService.exist(id)) {
            return ResultInfo.fail("client id not exist");
        }
        clientService.delete(id);
        return ResultInfo.success();
    }

}
