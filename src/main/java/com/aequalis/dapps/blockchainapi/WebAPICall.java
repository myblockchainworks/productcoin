/**
 * 
 */
package com.aequalis.dapps.blockchainapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.aequalis.dapps.dto.BalanceDTO;
import com.aequalis.dapps.dto.CoinDTO;

/**
 * @author anand
 *
 */
public class WebAPICall {
	
	public static String registerNewUser(String passcode) {
		String bcAddress = "0x123";
		try {
			URL url = new URL(WEBAPI.BLOCKCHAIN_URL);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.REGISTER_DATA.replace("PARAM1", passcode);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("result"))
					bcAddress = json.getString("result");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bcAddress;
	}
	
	public static Boolean unlockUser(String bcAddress, String passcode) {
		Boolean result = false;
		try {
			URL url = new URL(WEBAPI.BLOCKCHAIN_URL);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.UNLOCK_DATA.replace("PARAM1", bcAddress).replace("PARAM2", passcode);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("result"))
					result = json.getBoolean("result");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static int getProductCoinCount() {
		int result = 0;
		try {
			URL url = new URL(WEBAPI.BASE + WEBAPI.GETPRODUCTCOINCOUNT);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("GET");
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("productCoinCount"))
					result = json.getInt("productCoinCount");
				
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String createProductCoin(String name, String nominalValue, String issueSize, String payoutRate, String symbol, String launchDate, String expiryDate, int payoutsPerYear) {
		String transactionAddress = "0x12345";
		try {
			URL url = new URL(WEBAPI.BASE + WEBAPI.CREATEPRODUCTCOIN);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.CREATEPRODUCTCOIN_DATA.replace("PARAM1", name).replace("PARAM2", nominalValue).replace("PARAM3", issueSize).replace("PARAM4", payoutRate).replace("PARAM5", symbol).replace("PARAM6", launchDate).replace("PARAM7", expiryDate).replace("PARAM8", "" + payoutsPerYear);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				transactionAddress = sb.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return transactionAddress;
	}
	
	public static CoinDTO getProductDetail(int currentIndex) {
		CoinDTO coin = null;
		try {
			URL url = new URL(WEBAPI.BASE + WEBAPI.GETPRODUCTCOINDETAIL);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.GETPRODUCTCOINDETAIL_DATA.replace("PARAM1", "" + currentIndex);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("Name")) {
					coin = new CoinDTO();
					coin.setName(json.getString("Name"));
					coin.setAmount(json.getString("Amount"));
					coin.setSymbol(json.getString("Symbol"));
					coin.setLaunchDate(json.getString("LaunchDate"));
					coin.setExpiryDate(json.getString("ExpiryDate"));
					coin.setPayoutsPerYear(Integer.parseInt(json.getString("PayoutsPerYear")));
					coin.setReservedForPayout(Integer.parseInt(json.getString("ReservedForPayout")));
				}
				
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return coin;
	}
	
	public static int getUserBalance(int currentIndex, String address) {
		int balance = 0;
		try {
			URL url = new URL(WEBAPI.BASE + WEBAPI.GETUSERBALANCE);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.GETUSERBALANCE_DATA.replace("PARAM1", "" + currentIndex).replace("PARAM2", address);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("Balance")) {
					balance =  json.getInt("Balance");
				}
				
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return balance;
	}
	
	public static CoinDTO getProductMoreDetail(int currentIndex, CoinDTO coin) {
		try {
			URL url = new URL(WEBAPI.BASE + WEBAPI.GETPRODUCTCOINMOREDETAIL);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.GETPRODUCTCOINDETAIL_DATA.replace("PARAM1", "" + currentIndex);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("ProductAddress")) {
					coin.setProductAddress(json.getString("ProductAddress"));
					coin.setFirstPayoutDate(json.getString("FirstPayoutDate"));
					coin.setNextPayoutDate(json.getString("NextPayoutDate"));
					coin.setStatus(Integer.parseInt(json.getString("Status")));
					coin.setNominalValue(Integer.parseInt(json.getString("NominalValue")));
					coin.setIssueSize(Integer.parseInt(json.getString("IssueSize")));
					coin.setPayoutRate(Integer.parseInt(json.getString("PayoutRate")));
				}
				
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return coin;
	}
	
	public static String transferAmount(int selectIndex, String fromAddress, String toAddress, int amount) {
		String transactionAddress = "0x12345";
		try {
			URL url = new URL(WEBAPI.BASE + WEBAPI.TRANSFERAMOUNT);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.TRANSFERAMOUNT_DATA.replace("PARAM1", "" + selectIndex).replace("PARAM2", fromAddress).replace("PARAM3", toAddress).replace("PARAM4", "" + amount);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				transactionAddress = sb.toString();
			} else {
				System.out.println(httpCon.getResponseCode() + " " + httpCon.getResponseMessage());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return transactionAddress;
	}
	
	public static boolean triggerAlerts() {
		boolean result = false;
		try {
			URL url = new URL(WEBAPI.BASE + WEBAPI.CHECKTRIGGERALERTS);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("Success")) {
					result = json.getBoolean("Success");
				}
				
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean processUserPayout(int currentIndex, String address) {
		boolean result = false;
		try {
			URL url = new URL(WEBAPI.BASE + WEBAPI.PROCESSUSERPAYOUT);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.GETUSERBALANCE_DATA.replace("PARAM1", "" + currentIndex).replace("PARAM2", address);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("Success")) {
					result =  json.getBoolean("Success");
				}
				
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean updatePayoutProcessedStatus(int currentIndex) {
		boolean result = false;
		try {
			URL url = new URL(WEBAPI.BASE + WEBAPI.UPDATEPAYOUTPROCESSEDSTATUS);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type","application/json");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());
			String data = WEBAPI.GETPRODUCTCOINDETAIL_DATA.replace("PARAM1", "" + currentIndex);
			out.write(data);
			out.close();
		  
			BufferedReader br;
			if (200 <= httpCon.getResponseCode() && httpCon.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader((httpCon.getInputStream())));
				StringBuilder sb = new StringBuilder();
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
				}
				
				JSONObject json = new JSONObject(sb.toString());
				if(json.has("Success")) {
					result =  json.getBoolean("Success");
				}
				
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static List<CoinDTO> listProductCoins() {
		List<CoinDTO> coins = new ArrayList<CoinDTO>();
		CoinDTO coin;
		
		int totalCoins = getProductCoinCount();
		
		for (int index = 0; index < totalCoins; index++) {
			coin = getProductDetail(index);
			if (coin != null) {
				coin = getProductMoreDetail(index, coin);
				coins.add(coin);
			}
		}
		
		return coins;
	}
	
	public static List<BalanceDTO> listBalances(String address) {
		List<BalanceDTO> balances = new ArrayList<BalanceDTO>();
		BalanceDTO balance;
		CoinDTO coin;
		int totalCoins = getProductCoinCount();
		
		for (int index = 0; index < totalCoins; index++) {
			coin = getProductDetail(index);
			if (coin != null) {
				int amount = getUserBalance(index, address);
				if (amount > 0) {
					balance = new BalanceDTO();
					balance.setName(coin.getName());
					balance.setSymbol(coin.getSymbol());
					balance.setAmount("" + amount);
					balances.add(balance);
				}
			}
		}
		
		return balances;
	}
	
	public static void main(String[] args) {
//		WebAPICall.createProductCoin("My Test 001", "25000", "$", "1491350400000", "1491350400000", 6);
//		System.out.println(WebAPICall.getProductCoinCount());
//		CoinDTO coin = WebAPICall.getProductDetail(2);
//		coin = WebAPICall.getProductMoreDetail(2, coin);
//		System.out.println("Name : " + coin.getName());
//		System.out.println("Address : " + coin.getProductAddress());
//		System.out.println(WebAPICall.triggerAlerts());
//		System.out.println(WebAPICall.transferAmount(2, "0x7b2933eb602dd71cdb5c404ed3edde6c93b94090", "0x8be2088cb7186c35574a35e03e92a801151d04ae", 100));
		System.out.println(WebAPICall.listProductCoins().size());
	}
}
