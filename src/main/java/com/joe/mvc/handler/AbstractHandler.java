package com.joe.mvc.handler;

import com.joe.mvc.domain.ReceiveMsg;
import com.joe.mvc.domain.SendMsg;

public abstract class AbstractHandler {
	
	public abstract SendMsg handle(ReceiveMsg msg);

}
