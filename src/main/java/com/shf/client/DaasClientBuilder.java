package com.shf.client;

/**
 * description :
 *
 * @author songhaifeng
 * @date 2022/4/20 11:17
 */
public final class DaasClientBuilder {

    /**
     * http://host:port
     */
    private String daasAddress;

    private DaasClientBuilder() {
    }

    public static DaasClientBuilder create() {
        return new DaasClientBuilder();
    }

    public String getDaasAddress() {
        return daasAddress;
    }

    public DaasClientBuilder withDaasAddress(String daasAddress) {
        this.daasAddress = daasAddress;
        return this;
    }

    public DaasClient build() {
        return new DefaultDaasClient(daasAddress);
    }
}
