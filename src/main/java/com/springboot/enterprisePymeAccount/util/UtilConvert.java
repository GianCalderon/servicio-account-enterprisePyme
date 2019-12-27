package com.springboot.enterprisePymeAccount.util;

import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springboot.enterprisePymeAccount.document.PymeAccount;
import com.springboot.enterprisePymeAccount.dto.AccountDto;

@Component
public class UtilConvert {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilConvert.class);

	
	public PymeAccount convertAccountPyme(AccountDto accountDto) {

		
		LOGGER.info("Convert ---> :"+accountDto.toString());

		PymeAccount  PymeAccount = new PymeAccount();

		PymeAccount.setNameAccount(CodAccount.NAME_CURRENT_ACCOUNT);
		PymeAccount.setNumberAccount(CodAccount.COD_CURRENT_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
		PymeAccount.setState(accountDto.getState());
		PymeAccount.setBalance(accountDto.getBalance());
		PymeAccount.setTea(accountDto.getTea());
		PymeAccount.setCreateDate(new Date());
		PymeAccount.setUpdateDate(new Date());
		PymeAccount.setIdOperation(new ArrayList<String>());
		
		LOGGER.info("Convert ---> :"+PymeAccount.toString());
		
		return PymeAccount;

	}
//	
//	
//	public PymeAccount convertPymeAccountEnt(PymeAccountEntDto PymeAccountEntDto) {
//
//		PymeAccount  PymeAccount = new PymeAccount();
//
//		PymeAccount.setNameAccount(CodAccount.NAME_CURRENT_ACCOUNT);
//		PymeAccount.setNumberAccount(CodAccount.COD_CURRENT_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
//		PymeAccount.setState(PymeAccountEntDto.getState());
//		PymeAccount.setBalance(PymeAccountEntDto.getBalance());
//		PymeAccount.setTea(PymeAccountEntDto.getTea());
//		PymeAccount.setCreateDate(new Date());
//		PymeAccount.setUpdateDate(new Date());
//		PymeAccount.setIdOperation(new ArrayList<String>());
//		
//		
//		return PymeAccount;
//
//	}
//	
//	public PymeAccount convertPymeAccountAdd(AccountDto accountDto) {
//
//		PymeAccount  PymeAccount = new PymeAccount();
//
//		PymeAccount.setNameAccount(CodAccount.NAME_CURRENT_ACCOUNT);
//		PymeAccount.setNumberAccount(CodAccount.COD_CURRENT_ACCOUNT+String.valueOf((int)(Math.random()*99999999+1)));
//		PymeAccount.setState(accountDto.getState());
//		PymeAccount.setBalance(accountDto.getBalance());
//		PymeAccount.setTea(accountDto.getTea());
//		PymeAccount.setCreateDate(new Date());
//		PymeAccount.setUpdateDate(new Date());
//		PymeAccount.setIdOperation(new ArrayList<String>());
//
//		return PymeAccount;
//
//	}


}
