package com.springmvc.dto.momoclasses;

public class PartnerInfo {

    private String accessKey;
    private String partnerCode;
    private String secretKey;
    private String publicKey;

    public PartnerInfo(String partnerCode, String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.partnerCode = partnerCode;
        this.secretKey = secretKey;
    }



    public String getAccessKey() {
        return accessKey;
    }


    public String getPartnerCode() {
        return partnerCode;
    }


    public String getSecretKey() {
        return secretKey;
    }

}
