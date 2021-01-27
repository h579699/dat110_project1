package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;

public class Sensor extends RPCStub {

	private byte RPCID = 1;
	
	public int read() {
		
		// implement marshalling, call and unmarshalling for read RPC method
		byte[] request = RPCUtils.marshallVoid(RPCID);
		
		byte[] reply = rpcclient.call(request);
		
		int temp = RPCUtils.unmarshallInteger(reply);
		
		return temp;
	}
	
}
