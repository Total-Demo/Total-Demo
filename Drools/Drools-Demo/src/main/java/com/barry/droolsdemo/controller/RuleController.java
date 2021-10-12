package com.barry.droolsdemo.controller;


import com.barry.droolsdemo.entity.Calculation;
import com.barry.droolsdemo.entity.CreditCardApplyInfo;
import com.barry.droolsdemo.entity.InsuranceInfo;
import com.barry.droolsdemo.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rule")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @RequestMapping("/calculate")
    public Calculation calculate(double wage){
        Calculation calculation = new Calculation();
        calculation.setWage(wage);
        calculation = ruleService.calculate(calculation);
        System.out.println(calculation);
        return calculation;
    }


    @RequestMapping("/creditCardApply")
    public CreditCardApplyInfo creditCardApply(@RequestBody
                                                       CreditCardApplyInfo creditCardApplyInfo){
        creditCardApplyInfo = ruleService.creditCardApply(creditCardApplyInfo);
        return creditCardApplyInfo;
    }


    @RequestMapping("/insuranceInfoCheck")
    public Map insuranceInfoCheck(@RequestBody InsuranceInfo insuranceInfo){
        Map map = new HashMap();
        try {
            List<String> list = ruleService.insuranceInfoCheck(insuranceInfo);
            if(list != null && list.size() > 0){
                map.put("checkResult",false);
                map.put("msg","准入失败");
                map.put("detail",list);
            }else{
                map.put("checkResult",true);
                map.put("msg","准入成功");
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("checkResult",false);
            map.put("msg","未知错误");
            return map;
        }
    }
}
