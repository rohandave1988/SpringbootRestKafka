package com.rest.kafka.model;

public class Account {

    private String accountName;
    private String accountNo;
    private String accountType;

	public Account() {
    }

    public Account(String accountName, String accountNo,String accountType) {
        this.accountName = accountName;
        this.accountNo = accountNo;
        this.accountType = accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    

    public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


    @Override
    public String toString() {
        return "Account{" +
                "accountName='" + accountName + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
