package cn.bucheng.pay.weixing;

import cn.bucheng.pay.utils.HttpClient;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author buchengyin
 * @create 2019/7/6 23:40
 * @describe
 */
@RestController
@RequestMapping("/wx")
public class WxPayController {
    @Value("${appid}")
    private String appid;
    @Value("${partner}")
    private String partner;
    @Value("${notifyurl}")
    private String notifyurl;
    @Value("${partnerkey}")
    private String partnerkey;

    @RequestMapping("pay")
    public Object pay() {
        Map param = new HashMap<>();
        param.put("appid", appid);
        param.put("mch_id", partner);
        param.put("nonce_str", WXPayUtil.generateNonceStr());
        param.put("body", "品优购");
        param.put("out_trade_no", "1000000");
        param.put("total_fee", "1");
        param.put("spbill_create_ip", "127.0.0.1");
        param.put("notify_url", notifyurl);
        param.put("trade_type", "NATIVE");

        try {
            String xmlParam = WXPayUtil.generateSignedXml(param, partnerkey);
            System.out.println("请求发送:" + xmlParam);
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            client.setHttps(true);
            client.setXmlParam(xmlParam);
            client.post();
            String resultXml = client.getContent();
            System.out.println("获取结果" + resultXml);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultXml);
            Map<String, String> map = new HashMap();
            if ("SUCCESS".equals(resultMap.get("result_code"))) {
                map.put("code_url", resultMap.get("code_url"));//二维码
                map.put("out_trade_no", "1000000");//交易订单
                map.put("total_fee", "1");//金额
            }
            return map;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Map<String, String> map = new HashMap();
            return map;
        }
    }
}
