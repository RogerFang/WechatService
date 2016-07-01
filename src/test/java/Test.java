import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Roger on 2016/5/15.
 */
public class Test {

    @org.junit.Test
    public void test(){
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        map.put("ir1", list);
        map.put("ir2", list);
        String jsonStr = JSON.toJSONString(map);
        // System.out.println(jsonStr);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        JSONArray jsonArray = jsonObject.getJSONArray("ir1");
        System.out.println(jsonArray.toString());
        System.out.println(jsonArray.toJSONString());
        List<String> jsontoList = JSON.parseArray(jsonArray.toJSONString(), String.class);
        System.out.println(jsontoList);
    }

    @org.junit.Test
    public void getCaptcha(){
        int code = (int) (Math.random() * 900000 + 100000);
        System.out.println("【herpink·她粉】您的验证码为:"+code+",请勿泄露。");
    }
}
