package com.mhm.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 共享的通用接口
 * Created by MHm on 2019/6/5.
 */
public class GenerateApiScriptUtil {

    static int index = 10001;
    static int paramIndex = 10228;
    static Map<String,String> apiMap = new HashMap<String,String>();
    static{
//        apiMap.put("/token/auth","10024");
//        apiMap.put("/region/filter","10025");
//        apiMap.put("/deviceBase/base","10026");
//        apiMap.put("/debiceBzse/baseKey","10027");
//        apiMap.put("/findByPage","10028");
//        apiMap.put("/modelDeviceMete/getById","10029");
//        apiMap.put("/deviceMete/mete","10030");
//        apiMap.put("/api/queryDevice","10031");
//        apiMap.put("/api/queryAll","10032");
//        apiMap.put("/test2/test","10033");
//        apiMap.put("/energy/queryHourMaxEnergy","10034");
//        apiMap.put("/energy/queryHourMinEnergy","10035");
//        apiMap.put("/energy/queryHourAvgEnergy","10036");
//        apiMap.put("/energy/queryHourTotalEnergy","10037");
//        apiMap.put("/energy/queryDayMaxEnergy","10038");
//        apiMap.put("/energy/queryDayMinEnergy","10039");
//        apiMap.put("/energy/queryDayAvgEnergy","10040");
//        apiMap.put("/energy/queryDayTotalEnergy","10041");
//        apiMap.put("/energy/queryMonthMaxEnergy","10042");
//        apiMap.put("/energy/queryMonthMinEnergy","10043");
//        apiMap.put("/energy/queryMonthAvgEnergy","10044");
//        apiMap.put("/energy/queryMonthTotalEnergy","10045");
//        apiMap.put("/energy/queryYearMaxEnergy","10046");
//        apiMap.put("/energy/queryYearTotalEnergy","10047");
//        apiMap.put("/energy/queryYearAvgEnergy","10048");
//        apiMap.put("/energy/queryYearMinEnergy","10049");
//        apiMap.put("/energy/queryAllHourEnergy","10050");
//        apiMap.put("/energy/queryAllDayEnergy","10051");
//        apiMap.put("/energy/queryAllMonthEnergy","10052");
//        apiMap.put("/energy/queryAllYearEnergy","10053");
//        apiMap.put("/load/queryHourMinLoad","10054");
//        apiMap.put("/load/queryHourMaxLoad","10055");
//        apiMap.put("/load/queryHourAvgLoad","10056");
//        apiMap.put("/load/queryHourTotalLoad","10057");
//        apiMap.put("/load/queryDayMinLoad","10058");
//        apiMap.put("/load/queryDayMaxLoad","10059");
//        apiMap.put("/load/queryDayAvgLoad","10060");
//        apiMap.put("/load/queryDayTotalLoad","10061");
//        apiMap.put("/load/queryMonthMinLoad","10062");
//        apiMap.put("/load/queryMonthMaxLoad","10063");
//        apiMap.put("/load/queryMonthAvgLoad","10064");
//        apiMap.put("/load/queryMonthTotalLoad","10065");
//        apiMap.put("/load/queryYearMinLoad","10066");
//        apiMap.put("/load/queryYearMaxLoad","10067");
//        apiMap.put("/load/queryYearAvgLoad","10068");
//        apiMap.put("/load/queryYearTotalLoad","10069");
//        apiMap.put("/load/queryAllHourLoad","10070");
//        apiMap.put("/load/queryAllDayLoad","10071");
//        apiMap.put("/load/queryAllMonthLoad","10072");
//        apiMap.put("/load/queryAllYearLoad","10073");
        apiMap.put("/region/queryMete","10047");
        apiMap.put("/region/queryDevice","10048");
        apiMap.put("/region/queryRegionDevice","10049");
        apiMap.put("/region/queryRegionDeviceAll","10050");
        apiMap.put("/region/querySubSpace","10051");
        apiMap.put("/region/querySpace","10052");

       /* apiMap.put("/energy/queryMonthAvgEnergy","10044");
        apiMap.put("/energy/queryMonthTotalEnergy","10045");
        apiMap.put("/energy/queryYearMaxEnergy","10046");
        apiMap.put("/energy/queryYearTotalEnergy","10047");
        apiMap.put("/energy/queryYearAvgEnergy","10048");
        apiMap.put("/energy/queryYearMinEnergy","10049");
        apiMap.put("/energy/queryAllHourEnergy","10050");
        apiMap.put("/energy/queryAllDayEnergy","10051");
        apiMap.put("/energy/queryAllMonthEnergy","10052");
        apiMap.put("/energy/queryAllYearEnergy","10053");*/


    }

    public static String generateAPI(String jsonStr,int type){
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        JSONObject paths = (JSONObject) jsonObject.get("paths");
        for (String apis :paths.keySet()) {
            //接口path
            String apiId = apiMap.get(apis);
            if (null != apiId && !"".equalsIgnoreCase(apiId)) {
                JSONObject api = paths.getJSONObject(apis);
                for (Object values : api.values()) {
                    JSONObject value = (JSONObject) values;
                        //接口名称
                        String summary = value.getString("summary");
                        //接口地址
                        String method = apis;
                    if(method.indexOf("/region")>-1){
                        type=1;
                    }
//                    else if(method.indexOf("/load")>-1){
//                        type=3;
//                    }
//                    System.out.println("INSERT INTO `t_api_info` VALUES ('"+apiId+"', '"+summary+"',NULL, 1, 'http://119.3.107.85/gw/api/apimarket', 286, "+
//                            type+", 'v1.0', 1, 2, '"+method+"', NULL, NULL, '"+summary+"', NULL, NULL, NULL, NULL, '{}', '{}', 2, '"+
//                            DateTimeUtil.getDateTimeString(new Date(),false)+"', '"+DateTimeUtil.getDateTimeString(new Date(),false)+"', NULL);");
                }
            }
        }
        return "";
    }

    public static String generateParam(String jsonStr){
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        JSONObject paths = (JSONObject) jsonObject.get("paths");
        for (String apis :paths.keySet()){
            JSONObject api = paths.getJSONObject(apis);
            String apiId = apiMap.get(apis);
            if (null != apiId && !"".equalsIgnoreCase(apiId)) {
                for (Object values :api.values()){
                    JSONObject value = (JSONObject) values;
                    //接口名称
                    String summary = value.getString("summary");
                    //接口地址
                    String method =apis;
                    //                    System.out.println(method);
                    //参数
                    JSONArray parameters = value.getJSONArray("parameters");
                    if(parameters == null){
                        continue;
                    }
                    for(Object parameter :parameters){
                        JSONObject parameterJson = (JSONObject) parameter;
                        //设备类型
                        String type = parameterJson.getString("type");
                        String name = parameterJson.getString("name");
                        //是否必选
                        String required = parameterJson.getString("required");
                        int isRequired =0;
                        if(required.equalsIgnoreCase("true")){
                            isRequired =1;
                        }
                        int paramType = 1;
                        if("integer".equals(type)){
                            paramType = 2;
                        }
                        if(null!=apiId && !"".equalsIgnoreCase(apiId)){
                            System.out.println("INSERT INTO `t_api_req_params` VALUES ("+paramIndex+++", "+apiId+", '"+name+"', "+
                                    paramType+", 1, NULL, NULL, "+isRequired+", 0, '"+name+"', NULL, NULL, NULL, NULL, NULL);");
                        }
                    }
                }
            }
        }
        return "";
    }

    public static void main(String[] args) throws IOException {
        //读取api的json解析
//        String jsonStr = HttpClientUtils.getInstance().getUrl("http://127.0.0.1:8769:8769/v2/api-docs",null);
//        generateAPI(jsonStr,1);
//        generateParam(jsonStr);
//        jsonStr = HttpClientUtils.getInstance().getUrl("http://119.3.107.85:8769:8769/v2/api-docs",null);
//        generateAPI(jsonStr,2);
//        generateParam(jsonStr);
    }
}
