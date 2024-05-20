package com.atm.demo.test;

import com.atm.demo.exception.WithdrawlException;
import com.atm.demo.service.AtmService;
import com.atm.demo.serviceimpl.AtmServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class AtmServiceTest {

    AtmService atmService;

    @Before
    public void setUp(){

        List<Integer> deno = Arrays.asList(10,20);
        List<Thread> threads = new ArrayList<>();
        Map<Integer,Integer> cashstore = new HashMap<>();
        cashstore.put(10,10);
        cashstore.put(20,10);
        atmService = new AtmServiceImpl(deno,cashstore);
    }

    @Test
    public void testWithDrawAmount() throws WithdrawlException {
        Map<Integer, Integer> withdrawValueMap = atmService.withdrawAmount(70);
        Assert.assertEquals(Optional.of(3), Optional.ofNullable(withdrawValueMap.get(20)));

    }

    @Test(expected = WithdrawlException.class)
    public void testWithDrawAmountException() throws WithdrawlException {
        List<Integer> deno = Arrays.asList(10,20);
        List<Thread> threads = new ArrayList<>();
        Map<Integer,Integer> cashstore = new HashMap<>();
        cashstore.put(10,1);
        cashstore.put(20,1);
        atmService = new AtmServiceImpl(deno,cashstore);
        Map<Integer, Integer> withdrawValueMap = atmService.withdrawAmount(70);
        //Assert.assertEquals(Optional.of(3), Optional.ofNullable(withdrawValueMap.get(20)));

    }

}
