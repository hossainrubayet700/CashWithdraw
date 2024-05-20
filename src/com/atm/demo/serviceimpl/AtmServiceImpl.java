package com.atm.demo.serviceimpl;
import com.atm.demo.exception.WithdrawlException;
import com.atm.demo.service.AtmService;

import java.util.*;

public class AtmServiceImpl implements AtmService {

    private final List<Integer> denominations;
    private final Map<Integer, Integer> cashStore;

    public AtmServiceImpl(List<Integer> denominations, Map<Integer, Integer> cashStore) {
        this.cashStore = cashStore;
        Collections.sort(denominations, Collections.reverseOrder());
        this.denominations = new ArrayList<>(denominations);
    }

    @Override
    public synchronized Map<Integer, Integer> withdrawAmount(Integer amount) throws WithdrawlException {

        Collections.sort(denominations, Collections.reverseOrder());

        if (amount <= 0 || amount % 10 != 0) {
            throw new WithdrawlException("Amount must be a  multiple of 10");
        }

        Integer originalAmount = amount;
        Map<Integer, Integer> withdrawalNotes = new HashMap<>();
        Map<Integer, Integer> tempStore = new HashMap<>(cashStore);

        for (Integer denom : denominations) {
            while (amount >= denom && tempStore.get(denom) > 0) {
                if (withdrawalNotes.containsKey(denom)) {
                    withdrawalNotes.put(denom, withdrawalNotes.get(denom) + 1);
                } else {
                    withdrawalNotes.put(denom, 1);
                }

                tempStore.put(denom, tempStore.get(denom) - 1);
                amount -= denom;
            }
        }

        if (amount != 0) {
            throw new WithdrawlException("Insufficient funds or denominations");
        }

        cashStore.clear();
        cashStore.putAll(tempStore);
        return withdrawalNotes;
    }
}
