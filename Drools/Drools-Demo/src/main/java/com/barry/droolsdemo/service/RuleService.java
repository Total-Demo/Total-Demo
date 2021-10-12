package com.barry.droolsdemo.service;

import com.alibaba.fastjson.JSON;
import com.barry.droolsdemo.annotation.Fact;
import com.barry.droolsdemo.domain.ActivityRule;
import com.barry.droolsdemo.domain.RegisterMqDTO;
import com.barry.droolsdemo.domain.RuleDTO;
import com.barry.droolsdemo.domain.RuleExecutorResult;
import com.barry.droolsdemo.domain.fact.BaseFact;
import com.barry.droolsdemo.domain.fact.RegisterFact;
import com.barry.droolsdemo.entity.Calculation;
import com.barry.droolsdemo.entity.CreditCardApplyInfo;
import com.barry.droolsdemo.entity.InsuranceInfo;
import com.barry.droolsdemo.executor.RuleExecutor;
import com.barry.droolsdemo.generator.RuleGenerator;
import com.barry.droolsdemo.utils.CopyUtil;
import com.barry.droolsdemo.utils.KieSessionUtils;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 调用规则引擎，执行规则
 */
@Service
public class RuleService {
    public static final Logger log = LoggerFactory.getLogger(RuleService.class);
    @Autowired
    private KieBase kieBase;

    //个人所得税计算
    public Calculation calculate(Calculation calculation){
        KieSession kieSession = kieBase.newKieSession();
        kieSession.insert(calculation);
        kieSession.fireAllRules();
        kieSession.dispose();
        return calculation;
    }

    //调用Drools规则引擎实现信用卡申请
    public CreditCardApplyInfo creditCardApply(CreditCardApplyInfo creditCardApplyInfo){
        KieSession session = kieBase.newKieSession();
        session.insert(creditCardApplyInfo);
        session.fireAllRules();
        session.dispose();
        return creditCardApplyInfo;
    }

    public List<String> insuranceInfoCheck(InsuranceInfo insuranceInfo) throws Exception{
        KieSession session = KieSessionUtils.getKieSessionFromXLS("/Users/barry.cao/Desktop/drools/drl/insuranceInfoCheck.xls");
        session.getAgenda().getAgendaGroup("sign").setFocus();

        session.insert(insuranceInfo);

        List<String> listRules = new ArrayList<>();
        session.setGlobal("listRules", listRules);

        session.fireAllRules();

        return listRules;
    }



    @Autowired
    TestService testService;

    /**
     * 加载规则
     */
    public void loadRule() {
        try {
            List<RuleDTO> ruleDTOs = getActivityRuleList();
            log.info("{}条加入规则引擎", ruleDTOs.size());
            if (!ruleDTOs.isEmpty()) {
                RuleGenerator generator = new RuleGenerator();
                generator.generateRules(ruleDTOs);
            }
        } catch (Exception e) {
            log.error("RuleService.loadRule。e={}",e.getMessage(), e);
        }
    }

    /**
     * 触发规则
     */
    public void useRule(String userId, String phone) {
        BaseFact fact = buildBaseFact(userId);
        /**
         * 因为是uuid所以修改了的规则，重载加载是新的drl，故从数据库动态加载之时，is_delete属性要注意
         * */
        String orderId = UUID.randomUUID().toString();
        /**
         * 此处应当是从其他服务获取的的消息体，而不是空值
         * */
        RegisterMqDTO domain = new RegisterMqDTO();
        domain.setTelephone(phone);
        try {
            /*可以知道一条信息，匹配了多少个规则，成功了几个*/
            RuleExecutorResult ruleExecutorResult = beforeExecute(orderId, fact, domain);
            log.info("RuleService|useRule|ruleExecutorResult={}", JSON.toJSON(ruleExecutorResult));
//            Assert.isTrue(ruleExecutorResult.getFailure() == 0, String.format("有%d条规则执行失败", ruleExecutorResult.getFailure()));
        } catch (Exception e) {
            log.error("RuleService|useRule|class={},orderId={}, userId={}, 规则执行异常:{}", this.getClass().getName(), orderId, "123456789", e.getMessage(), e);
        }
    }

    /**
     * 从数据库里面取规则
     */
    public List<RuleDTO> getActivityRuleList() {
        Date begin = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());

        List<ActivityRule> list = testService.selectAll();
        List<RuleDTO> ruleDTOList = new ArrayList<>();
        for (ActivityRule dto : list) {
            RuleDTO ruleDTO = new RuleDTO();
            ruleDTO.setBeginTime(begin);
            ruleDTO.setEndTime(end);
            ruleDTO.setRule(dto);
            ruleDTOList.add(ruleDTO);
        }
        return ruleDTOList;
    }

    /**
     * 执行前
     */
    public RuleExecutorResult beforeExecute(String orderId, BaseFact fact, RegisterMqDTO domain) {
        RegisterFact registerFact = buildRegisterFact(domain);
        CopyUtil.copyPropertiesCglib(fact, registerFact);
        log.info("RuleService|beforeExecute|{}事件的orderId={}, RegisterMqDTO={}", registerFact.getClass().getAnnotation(Fact.class).value(), orderId, domain);
        return RuleExecutor.execute(registerFact, orderId);
    }

    /**
     * 生成初始的baseFact
     */
    public BaseFact buildBaseFact(String userId) {
        BaseFact fact = new BaseFact();
//        此处应获取用户的信息
//        fact.setCust();
        fact.setUserId(userId);
        return fact;
    }

    /**
     * 生成初始的registerFact
     */
    private RegisterFact buildRegisterFact(RegisterMqDTO domain) {
        RegisterFact registerFact = new RegisterFact();

        CopyUtil.copyPropertiesCglib(domain, registerFact);
        return registerFact;
    }
}
