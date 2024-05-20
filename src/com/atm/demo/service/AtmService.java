package com.atm.demo.service;

import com.atm.demo.exception.WithdrawlException;

import java.util.Map;

public interface AtmService {

    public Map<Integer, Integer> withdrawAmount(Integer amount) throws WithdrawlException;
}
