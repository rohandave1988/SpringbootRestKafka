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

			JSONArray employeeList = (JSONArray) obj;
			System.out.println(employeeList);

			employeeList.forEach(acc -> parseAccountDetails((JSONObject) acc, accNumber));

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
    	JSONObject employeeObject = (JSONObject) account.get("Account");
        
        String accountname = (String) employeeObject.get("accountname");    
        System.out.println(accountname);
         
        String accountType = (String) employeeObject.get("accountType");  
        System.out.println(accountType);
         
        String accountNumber = (String) employeeObject.get("accountNumber");    
        System.out.println(accountNumber);
        
        if (accNumber.equalsIgnoreCase(accountname)) {
        	acc.setAccountName(accountname);
        	acc.setAccountNo(accountNumber);
        	acc.setAccountType(accountType);
        }
        
		return acc;	
    }

}
