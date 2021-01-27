package no.hvl.dat110.rpc;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

import no.hvl.dat110.TODO;

public class RPCUtils {

	// Utility methods for marshalling and unmarshalling of parameters and return values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the 
	// RPC message syntax [rpcid,parameter/return value]
	
	public static byte[] marshallString(byte rpcid, String str) {

		// marshall RPC identifier and string into byte array
		byte[] stringArray = str.getBytes();
		byte[] encoded = new byte[1 + stringArray.length];

		encoded[0] = rpcid;
		for(int i = 1; i <= stringArray.length; i++) {
			encoded[i] = stringArray[i-1];
		}

		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		
		byte[] stringBytes = new byte[data.length-1];

		// unmarshall String contained in data into decoded
		for(int i = 0; i < stringBytes.length; i++) {
			stringBytes[i] = data[i+1];
		}
		
		String decoded = new String(stringBytes);
		
		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded = new byte[1];

		// marshall RPC identifier in case of void type
		encoded[0] = rpcid;
		
		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		//unmarshall void type
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {
		// marshall RPC identifier and string into byte array

		byte[] encoded = new byte[5];
		encoded[0] = rpcid;
		byte[] intAsBytes = ByteBuffer.allocate(4).putInt(x).array(); 
		for(int i = 0; i < intAsBytes.length; i++) {
			encoded[i+1] = intAsBytes[i];
		}

		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {
		// unmarshall integer contained in data
		byte [] intAsBytes = new byte[4];

		for(int i = 0; i < 4; i++) {
			intAsBytes[i] = data[i+1];
		}
		
		int decoded = ByteBuffer.wrap(intAsBytes).getInt();
		return decoded;

	}
}
