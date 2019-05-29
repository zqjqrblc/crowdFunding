package utils;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;

import fabric.ChaincodeManager;
import fabric.FabricConfig;

public class main {
	public static void main(String[] args) throws CryptoException, InvalidArgumentException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, TransactionException, IOException, ProposalException, InterruptedException, ExecutionException, TimeoutException{
		ChaincodeManager  fm = FabricManager.obtain().getManager();
		fm.invoke("", args);
		fm.query("", args);
		
	}
}
