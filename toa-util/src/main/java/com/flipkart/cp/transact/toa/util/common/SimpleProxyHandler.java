package com.flipkart.cp.transact.toa.util.common;

import com.flipkart.cp.convert.simple.proxy.CommandExecutable;
import com.flipkart.cp.convert.simple.proxy.SimpleProxy;
import com.flipkart.cp.convert.simple.proxy.request.HttpRequestWrapper;
import com.google.common.base.Optional;
import com.netflix.hystrix.customized.HystrixCommandKey;

/**
 * Created by padmanabh.kulkarni on 28/06/15.
 */
public class SimpleProxyHandler {
    private SimpleProxy simpleProxy;

    protected SimpleProxyHandler() { this.simpleProxy = SimpleProxy.PROXY; }

    private static class SimpleProxyHandlerProvider {
        static final SimpleProxyHandler INSTANCE = new SimpleProxyHandler();
    }
    public static SimpleProxyHandler getInstance() { return SimpleProxyHandlerProvider.INSTANCE; }

    public <CommandResponse>CommandResponse execute(HttpRequestWrapper httpRequestWrapper, String poolName, String commandKey) throws Exception {
        try {
            CommandExecutable httpCommand = simpleProxy.getHttpCommand(poolName, HystrixCommandKey.Factory.asKey(commandKey), httpRequestWrapper);
            Optional<CommandResponse> response = httpCommand.exec().getResponse();

            return response.get();
        } catch (Exception e) {
            throw e;
        }
    }
}
