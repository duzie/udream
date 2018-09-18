package com.f.udream.controller;

import com.f.udream.entity.IncomeDay;
import com.f.udream.entity.OrderTrade;
import com.f.udream.entity.Store;
import com.f.udream.service.StoreService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
public class DataController {

    ObjectMapper om = new ObjectMapper();
    final String url = "http://api.udream.cn/mgt/franchiseWx/getStoreStatistics";
    final String getStoreSummaryUrl = "http://api.udream.cn/basics/store/getStoreSummary";
    final String storesUrl = "http://api.udream.cn/basics/store/locations?version=1&city=1004774280682008576&district=";
    final String getCraftsmanQueuedStatusUrl = "http://api.udream.cn/queued/getCraftsmanQueuedStatus";
    final String queryOrderTradeByStoreId = "https://api.udream.cn/mgt/franchiseWx/queryOrderTradeByStoreId";
    String idCard = "432502198405012316";
    String mobile = "15989300244";
    String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJpZENhcmQiOiI0MzI1MDIxOTg0MDUwMTIzMTYiLCJtb2JpbGUiOiIxNTk4OTMwMDI0NCIsImV4cCI6MTUzMzExODI0OCwiaWF0IjoxNTMxODIyMjQ4fQ.ps0Hyi-okeHtyZ-m0ltDRyAVDuOSqn-SFBWr009HgUk";

    final String yuanlingStoreId = "1014202560627601408";
    final String minleStoreId = "1019793176204357633";

    @Autowired
    StoreService storeService;

    @RequestMapping("store/month")
    public List<IncomeDay> storeStatistics(String storeId, String month) throws IOException {
        return getStoreStatistics(storeId, month);
    }

    @PostMapping("store/add")
    public void addStore(@RequestBody UdreamStoresData data) {
        storeService.add(data.getResult().storeList);
    }


    @PostMapping("token/update")
    public void token(String token) {
        this.token = token;
    }


    Iterable<Store> tmpStores;

    @GetMapping("store/list")
    public Iterable<Store> list(boolean f) {
        if (f) {
            tmpStores = storeService.findAll();
        }
        return tmpStores;
    }

    @PostConstruct
    public void initStoretmp() {
        tmpStores = storeService.findAll();
    }

    @GetMapping("store/update")
    public void update() {
        Iterable<Store> its = storeService.findAll();
        for (Store it : its) {
            List<Store> list = getStoreInfo(it.getStoreId());
            if (list != null && list.get(0) != null) {
                storeService.update(list.get(0));
            }
        }
    }

    @Scheduled(cron = "0 30,59 9-23 * * ?")
    void updateMyStore() {
        try {
            load(yuanlingStoreId);
            load(minleStoreId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void load(String storeId){
        List<OrderTrade> list = queryOrderTradeByStoreId(storeId, DateFormatUtils.format(new Date(), "yyyy-MM"), 10);
        storeService.saveOrderTrade(list, storeId);
    }


    @GetMapping("income/list")
    public Iterable<IncomeDay> incomeList(String storeId, String month) {
        return storeService.findIncomeByDate(storeId, month);
    }


    @GetMapping("queryOrderTrade")
    public Iterable<OrderTrade> queryOrderTrade(String storeId, String month) {
        return storeService.queryOrderTrade(storeId, month);
    }

    @GetMapping("orderTrade/update")
    public String orderTradeUpdate(String storeId, String month) {
        List<OrderTrade> list = queryOrderTradeByStoreId(storeId, month, 100);
        log.info(storeId + "-" + month + " size:" + list.size());
        if (list == null || list.size() == 0)
            return "empty";
        storeService.AddOrderTrade(list, storeId, month);
        return "has";
    }


    @GetMapping("income/update")
    public String incomeUpdate(String storeId, String month) {
        List<IncomeDay> list = getStoreStatistics(storeId, month);
        log.info(storeId + "-" + month + " size:" + list.size());
        if (list == null || list.size() == 0)
            return "empty";
        storeService.addIncomeDay(list);
        return "has";
    }

    @GetMapping("store/status")
    public List<UdreamStoresStatusData> storeStatus(String storeId) {
        return getCraftsmanQueuedStatus(storeId);
    }

    @GetMapping("income/update/all")
    public String incomeUpdateAll(String month) {
        int i = 0;
        for (Store tmpStore : tmpStores) {
            log.info(i++ + tmpStore.getStoreName());
            incomeUpdate(tmpStore.getStoreId(), month);
        }
        return null;
    }


    private List<OrderTrade> queryOrderTradeByStoreId(String storeId, String month, int pageSize) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("counted", true);
            map.put("idCard", idCard);
            map.put("mobile", mobile);
            map.put("pageNum", 1);
            map.put("pageSize", pageSize);
            map.put("payMonth", month);
            map.put("storeId", storeId);
            map.put("token", token);
            log.info(om.writeValueAsString(map));
            String s = Request.Post(queryOrderTradeByStoreId)
                    .setHeader("Content-Type", "application/json")
                    .bodyString(om.writeValueAsString(map), ContentType.APPLICATION_JSON)
                    .execute().returnContent().asString();
            log.info(s);
            UdreamOrderTrade income = om.readValue(s, UdreamOrderTrade.class);
            return income.getResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private List<UdreamStoresStatusData> getCraftsmanQueuedStatus(String storeId) {
        String s = null;
        try {
            s = Request.Post(getCraftsmanQueuedStatusUrl)
                    .bodyForm(Form.form()
                            .add("storeId", storeId)//"1014202556462657536"
                            .build())
                    .execute().returnContent().asString();
            UdreamStoresStatus income = om.readValue(s, UdreamStoresStatus.class);
            return income.getResult();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private List<IncomeDay> getStoreStatistics(String storeId, String month) {
        try {
            String s = Request.Post(url)
                    .bodyForm(Form.form()
                            .add("storeId", storeId)//"1014202556462657536"
                            .add("statisticsDate", month)//"2017-03"
                            .add("idCard", idCard)
                            .add("mobile", mobile)
                            .add("token", token)
                            .build())
                    .execute().returnContent().asString();
            UdreamStoreIncomeData income = om.readValue(s, UdreamStoreIncomeData.class);
            return income.getResult();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Store> getStoreInfo(String storeId) {
        try {
            String s = Request.Post(getStoreSummaryUrl)
                    .bodyForm(Form.form()
                            .add("storeIds", storeId)//"1014202556462657536"
                            .build())
                    .execute().returnContent().asString();
            UdreamStoresInfo income = om.readValue(s, UdreamStoresInfo.class);
            return income.getResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class UdreamOrderTrade {
    List<OrderTrade> result;
    String retCode;
    String retMsg;
    boolean success;
}

@Data
class UdreamStoresStatus {
    List<UdreamStoresStatusData> result;
    String retCode;
    String retMsg;
    boolean success;
}

@Data
class UdreamStoresStatusData {
    double activeStatus;
    String craftsmanId;
    double craftsmanType;
    String nickname;
    String smallPic;
    double waitingCount;
    double waitingMinutes;
}


@Data
class UdreamStoreIncomeData {
    List<IncomeDay> result;
    String retCode;
    String retMsg;
    boolean success;
}

@Data
class UdreamStoresData {
    UdreamStores result;
    String retCode;
    String retMsg;
    boolean success;
}

@Data
class UdreamStoresInfo {
    List<Store> result;
    String retCode;
    String retMsg;
    boolean success;
}

@Data
class UdreamStores {
    List<Store> storeList;
    String version;

}