import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

public class F {
    public static void main(String[] args) {

//        NetworkInterface[] devices = JpcapCaptor.getDeviceList();

        String s;
        String token="eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJpZENhcmQiOiI0MzI1MDIxOTg0MDUwMTIzMTYiLCJtb2JpbGUiOiIxNTk4OTMwMDI0NCIsImV4cCI6MTUzMzExODI0OCwiaWF0IjoxNTMxODIyMjQ4fQ.ps0Hyi-okeHtyZ-m0ltDRyAVDuOSqn-SFBWr009HgUk";
        try {
String rqs="{\"payMonth\":\"2018-07\"," +
        "\"idCard\":\"432502198405012316\"," +
        "\"mobile\":\"15989300244\"," +
        "\"counted\":true," +
        "\"storeId\":\"1014202560627601408\"," +
        "\"pageNum\":100," +
        "\"token\":\"eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJpZENhcmQiOiI0MzI1MDIxOTg0MDUwMTIzMTYiLCJtb2JpbGUiOiIxNTk4OTMwMDI0NCIsImV4cCI6MTUzMzExODI0OCwiaWF0IjoxNTMxODIyMjQ4fQ.ps0Hyi-okeHtyZ-m0ltDRyAVDuOSqn-SFBWr009HgUk\"}";
            s = Request.Post("https://api.udream.cn/mgt/franchiseWx/queryOrderTradeByStoreId")
                    .setHeader("Content-Type","application/json")
.bodyString(rqs,ContentType.APPLICATION_JSON)
//                    .bodyString("{\"storeId\":\"1014202560627601408\"," +
//                            "\"payMonth\":\"2018-07\"," +
//                            "\"counted\":true,\"pageNum\":1," +
//                            "\"pageSize\":100," +
//                            "\"idCard\":\"432502198405012316\"," +
//                            "\"mobile\":\"15989300244\"," +
//                            "\"token\":\""+token+"\"}", ContentType.APPLICATION_JSON)
//                    .bodyForm(Form.form()
//                            .add("counted", "true")
//                            .add("idCard", "432502198405012316")
//                            .add("mobile", "15989300244")
//                            .add("pageNum", "1")
//                            .add("pageSize", "10")
//                            .add("payMonth", "2018-07")
//                            .add("storeId", "1014202560627601408")
//                            .add("token", "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJpZENhcmQiOiI0MzI1MDIxOTg0MDUwMTIzMTYiLCJtb2JpbGUiOiIxNTk4OTMwMDI0NCIsImV4cCI6MTUzMzExODI0OCwiaWF0IjoxNTMxODIyMjQ4fQ.ps0Hyi-okeHtyZ-m0ltDRyAVDuOSqn-SFBWr009HgUk")
//                            .build())
                    .execute().returnContent().asString();

            System.out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//curl 'https://api.udream.cn/mgt/franchiseWx/queryOrderTradeByStoreId' -H 'Pragma: no-cache' -H 'Origin: https://wx.udream.cn' -H 'Accept-Encoding: gzip, deflate, br' -H 'Accept-Language: zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7' -H 'User-Agent: Mozilla/5.0 (iPhone; CPU iPhone OS 12_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16A5318d MicroMessenger/6.7.0 NetType/WIFI Language/zh_CN' -H 'Content-Type: application/json' -H 'Accept: application/json' -H 'Cache-Control: no-cache' -H 'Referer: https://wx.udream.cn/html5/root/businesses/index.html?v=1.2' -H 'Connection: keep-alive' --data-binary '{"storeId":"1014202560627601408","payMonth":"2018-07","counted":true,"pageNum":1,"pageSize":10,"idCard":"432502198405012316","mobile":"15989300244","token":"eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJpZENhcmQiOiI0MzI1MDIxOTg0MDUwMTIzMTYiLCJtb2JpbGUiOiIxNTk4OTMwMDI0NCIsImV4cCI6MTUzMzExODI0OCwiaWF0IjoxNTMxODIyMjQ4fQ.ps0Hyi-okeHtyZ-m0ltDRyAVDuOSqn-SFBWr009HgUk"}' --compressed
//curl 'https://api.udream.cn/mgt/franchiseWx/queryOrderTradeByStoreId' -H 'User-Agent: Mozilla/5.0 (iPhone; CPU iPhone OS 12_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16A5318d MicroMessenger/6.7.0 NetType/WIFI Language/zh_CN' -H 'Content-Type: application/json' -H 'Accept: application/json'  -H 'Connection: keep-alive' --data-binary '{"storeId":"1014202560627601408","payMonth":"2018-07","counted":true,"pageNum":1,"pageSize":10,"idCard":"432502198405012316","mobile":"15989300244","token":"eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJpZENhcmQiOiI0MzI1MDIxOTg0MDUwMTIzMTYiLCJtb2JpbGUiOiIxNTk4OTMwMDI0NCIsImV4cCI6MTUzMzExODI0OCwiaWF0IjoxNTMxODIyMjQ4fQ.ps0Hyi-okeHtyZ-m0ltDRyAVDuOSqn-SFBWr009HgUk"}' --compressed
