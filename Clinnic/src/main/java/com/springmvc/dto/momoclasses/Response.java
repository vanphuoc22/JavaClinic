package com.springmvc.dto.momoclasses;

public class Response {
    protected long responseTime;

    protected String message;

    private String partnerCode;
    private String orderId;
    protected Integer resultCode;

    public Response() {
        this.responseTime = System.currentTimeMillis();
    }



    public String getMessage() {
        return message;
    }



    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getResultCode() {
        return resultCode;
    }

}
