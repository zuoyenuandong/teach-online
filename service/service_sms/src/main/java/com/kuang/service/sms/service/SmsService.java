package com.kuang.service.sms.service;

import darabonba.core.exception.ClientException;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;


public interface SmsService{

    void send(String mobile, String checkCode) throws ClientException, ExecutionException, InterruptedException;
}
