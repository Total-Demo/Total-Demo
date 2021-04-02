package com.barry.nacosprovider.handler;


import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import com.barry.nacosprovider.entity.ResponseData;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 默认限流异常处理
 * URL 限流触发后默认处理逻辑是，直接返回 "Blocked by Sentinel (flow limiting)"。 如果需要自定义处理逻辑，实现的方式如下
 */
@Component
public class CustomUrlBlockHandler implements BlockExceptionHandler {


    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        // BlockException 异常接口，其子类为Sentinel五种规则异常的实现类
        // AuthorityException 授权异常
        // DegradeException 降级异常
        // FlowException 限流异常
        // ParamFlowException 参数限流异常
        // SystemBlockException 系统负载异常
        ResponseData data = new ResponseData();


        if (e instanceof FlowException) {
            data = new ResponseData(-1, "限流了");
        } else if (e instanceof DegradeException) {
            data = new ResponseData(-2, "降级了");
        } else if (e instanceof ParamFlowException) {
            data = new ResponseData(-3, "参数限流了");
        } else if (e instanceof SystemBlockException) {
            data = new ResponseData(-4, "系统负载异常了");
        } else if (e instanceof AuthorityException) {
            data = new ResponseData(-5, "授权异常");
        }

        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(data));

    }
}