package com.example.administrator.Gion;

import java.util.List;

public class Info {
    private Page page;
    private List<Merchant> merchantKey;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<Merchant> getMerchants() {
        return merchantKey;
    }

    public void setMerchants(List<Merchant> merchantKey) {
        this.merchantKey = merchantKey;
    }
}
