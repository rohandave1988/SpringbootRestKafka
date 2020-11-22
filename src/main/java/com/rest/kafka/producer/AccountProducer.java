package com.rest.kafka.producer;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.kafka.model.Account;

@RestController
@RequestMapping("/publish")
public class AccountProducer {

    @Autowired
    private KafkaTemplate<String, Account> kafkaTemplate;

    Account acc = new Account();
    
    @SuppressWarnings("unchecked")
	@GetMapping("/acctNo/{accNumber}")
	public String publishAccountDetails(@PathVariable String accNumber) {

		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader("src/main/resources/account.json")) {
			Object obj = jsonParser.parse(reader);

			JSONArray accountList = (JSONArray) obj;
			System.out.println(accountList);

			accountList.forEach(acc -> parseAccountDetails((JSONObject) acc, accNumber));

			if (null != acc.getAccountNo()) {
				kafkaTemplate.send("accountTopic", acc);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "Published Account Details Successfully";
	}
    
    
    public Account parseAccountDetails(JSONObject account, String accNumber){
    	JSONObject accountObject = (JSONObject) account.get("Account");
        
        String accountname = (String) accountObject.get("accountname");    
         
        String accountType = (String) accountObject.get("accountType");  
         
        String accountNumber = (String) accountObject.get("accountNumber");    
        
        if (accNumber.equalsIgnoreCase(accountname)) {
        	acc.setAccountName(accountname);
        	acc.setAccountNo(accountNumber);
        	acc.setAccountType(accountType);
        }
        
		return acc;	
    }

}
