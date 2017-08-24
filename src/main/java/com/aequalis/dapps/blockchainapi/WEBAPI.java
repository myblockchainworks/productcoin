/**
 * 
 */
package com.aequalis.dapps.blockchainapi;

/**
 * @author anand
 *
 */
public interface WEBAPI {
	
	
	// Server 46.101.143.170
	
//	static String BASE = "http://10.0.0.14:3001/";
//	static String BLOCKCHAIN_URL = "http://10.0.0.14:8545";
	
	static String BASE = "http://23.239.23.232:3002/";
	static String BLOCKCHAIN_URL = "http://23.239.23.232:8545";
	
	static String REGISTER_DATA = "{ \"id\":\"1\", \"jsonrpc\":\"2.0\", \"method\": \"personal_newAccount\", \"params\": [\"PARAM1\"] }";
	
	static String UNLOCK_DATA = "{\"id\":\"1\", \"jsonrpc\":\"2.0\", \"method\": \"personal_unlockAccount\", \"params\":  [ \"PARAM1\", \"PARAM2\", 0 ] }";
	
	static String GETPRODUCTCOINCOUNT = "getProductCoinCount";
	
	static String CREATEPRODUCTCOIN = "createProductCoin";
	
	static String CREATEPRODUCTCOIN_DATA = "{ \"_productName\":\"PARAM1\", \"_productNominalValue\":PARAM2, \"_productIssueSize\":PARAM3, \"_productPayoutRate\":PARAM4, \"_productSymbol\":\"PARAM5\", \"_launchDate\":PARAM6, \"_expiryDate\":PARAM7, \"_payoutsPerYear\":PARAM8 }";

	static String GETPRODUCTCOINDETAIL = "getProductCoinDetail";
	
	static String GETPRODUCTCOINDETAIL_DATA = "{ \"_currentIndex\":PARAM1 }";
	
	static String GETPRODUCTCOINMOREDETAIL = "getProductCoinMoreDetail";
	
	static String CHECKTRIGGERALERTS = "checkTriggerAlerts";
	
	static String TRANSFERAMOUNT = "transferAmount";
	
	static String TRANSFERAMOUNT_DATA = "{ \"_selectedIndex\":PARAM1, \"_fromAccount\":\"PARAM2\", \"_toAccount\":\"PARAM3\", \"_amountValue\":PARAM4 }";
	
	static String GETUSERBALANCE = "getUserBalance";
	
	static String GETUSERBALANCE_DATA = "{ \"_currentIndex\":PARAM1, \"_userAccount\":\"PARAM2\" }";
	
	static String PROCESSUSERPAYOUT = "processUserPayout";
	
	static String UPDATEPAYOUTPROCESSEDSTATUS = "updatePayoutProcessedStatus";
	
}