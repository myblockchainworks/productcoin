/**
 * 
 */
package com.aequalis.dapps.productcoin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aequalis.dapps.blockchainapi.WebAPICall;
import com.aequalis.dapps.dto.BalanceDTO;
import com.aequalis.dapps.dto.CoinDTO;
import com.aequalis.dapps.model.Coin;
import com.aequalis.dapps.model.Transaction;
import com.aequalis.dapps.model.User;
import com.aequalis.dapps.service.CoinService;
import com.aequalis.dapps.service.TransactionService;
import com.aequalis.dapps.service.UserService;

/**
 * @author anand
 *
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	CoinService coinService;
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired(required = true)
	public HttpServletRequest request;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	public HttpSession getSession() {
		if (request != null) {
			return request.getSession();
		}
		return null;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginScreen(Model model) {
	    return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutScreen(Model model) {
		HttpSession session = getSession();
		session.removeAttribute("loginUser");
	    return "home";
	}
	
	@RequestMapping(value = "/listusers", method = RequestMethod.GET)
	public String listUsers(Model model) {
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			model.addAttribute("currentUser", user);
			
			List<User> users = userService.findByIsadminuser(false);
			model.addAttribute("users", users);
		    return "listusers";
		} else {
			return "home";
		}
	}
	
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public ModelAndView loginUser(Model model, HttpServletRequest httpServletRequest) {
		Date loginTime = new Date();
		String userName = httpServletRequest.getParameter("userName");
		String password = httpServletRequest.getParameter("password");
		
		User user = userService.loginUser(userName, password);
		if (user != null) {
			
			HttpSession session = getSession();
			session.setAttribute("loginUser", user.getUserid());
			
			if (user.getIsadminuser() != null && user.getIsadminuser()) {
				return new ModelAndView("redirect:/adminhome");
			} else {
				if (user.getUnlocked() == null || !user.getUnlocked()) {
				Boolean status = WebAPICall.unlockUser(user.getBcaddress(), password);
				user.setUnlocked(status);
			}
			user.setLastLogin(user.getCurrentLogin());
			user.setCurrentLogin(loginTime);
			userService.addUser(user);
			
			return new ModelAndView("redirect:/myhome");
			}
		} 
		
		model.addAttribute("errormsg", "Invalid user name or password. Please try again.");
		return new ModelAndView("redirect:/");	
		
	}
	
	@RequestMapping(value = "/adminhome", method = RequestMethod.GET)
	public String transactionListScreen(Model model) {
	    
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			
			model.addAttribute("currentUser", user);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			List<User> users = userService.findByIsadminuser(false);
			
			List<CoinDTO> coins = WebAPICall.listProductCoins();
			
			for(int currentIndex = 0; currentIndex < coins.size(); currentIndex++) {
				List<BalanceDTO> userBalances = new ArrayList<BalanceDTO>();
				for (User u : users) {
					int balance = WebAPICall.getUserBalance(currentIndex, u.getBcaddress());
					if (balance > 0) {
						BalanceDTO balanceDTO = new BalanceDTO();
						balanceDTO.setName(u.getFullname());
						balanceDTO.setAmount(coins.get(currentIndex).getSymbol() + " " + balance);
						userBalances.add(balanceDTO);
					}
				}
				coins.get(currentIndex).setUsers(userBalances);
			}
			
			model.addAttribute("coins", coins);
			
			return "adminhome";
		}
		
		return "home";
	}
	
	@RequestMapping(value = "/mybalance", method = RequestMethod.GET)
	public String balanceListScreen(Model model) {
	    
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			
			model.addAttribute("currentUser", user);
			
			List<BalanceDTO> balances = WebAPICall.listBalances(user.getBcaddress());
			model.addAttribute("balances", balances);
			
			return "mybalance";
		}
		
		return "home";
	}
	
	@RequestMapping(value = "/userbalance", method = RequestMethod.GET)
	public String userBalanceListScreen(Model model) {
	    
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			
			model.addAttribute("currentUser", user);
			
			String userid = request.getParameter("userid");
			
			if (userid != null && !userid.trim().equals("")) {
				User u = userService.findByUserid(Long.parseLong(userid));
				List<BalanceDTO> balances = WebAPICall.listBalances(u.getBcaddress());
				model.addAttribute("userName", u.getFullname());
				model.addAttribute("balances", balances);
				return "userbalance";
			}
			
			return "adminhome";
		}
		
		return "home";
	}
	
	@RequestMapping(value = "/myhome", method = RequestMethod.GET)
	public String myHome(Model model) {
	    
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			
			model.addAttribute("currentUser", user);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			List<CoinDTO> coins = WebAPICall.listProductCoins();
			model.addAttribute("coins", coins);
			
			return "myhome";
		}
		
		return "home";
	}
	
	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public String newUser(Model model, HttpServletRequest request) {
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			
			model.addAttribute("currentUser", user);
			
			return "newuser";
		} else {
			return "home";
		}
	}
	
	
	@RequestMapping(value = "/processPayout", method = RequestMethod.GET)
	public ModelAndView processPayout(Model model, HttpServletRequest request) {
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			
			User adminUser = userService.findByUserid(Long.parseLong(userId.toString()));
			String currentIndex = request.getParameter("currentindex");
			if (currentIndex != null && !currentIndex.equals("")) {
				int index = Integer.parseInt(currentIndex);
				
				CoinDTO coin = WebAPICall.getProductDetail(index);
				coin = WebAPICall.getProductMoreDetail(index, coin);
				List<User> users = userService.findByIsadminuser(false);
				
				boolean result = false;
				for (User user : users) {
					int amount = WebAPICall.getUserBalance(index, user.getBcaddress());
					if (amount > 0) {
						WebAPICall.processUserPayout(index, user.getBcaddress());
						result = true;
						Transaction transaction = new Transaction();
						transaction.setFromaddress(adminUser.getBcaddress());
						transaction.setToaddress(user.getBcaddress());
						int amountValue = (((coin.getPayoutRate() * 1000000) / 100 / coin.getPayoutsPerYear()) * amount) / 1000000;
						transaction.setAmount("" + amountValue);
						transaction.setDescription("Payout Transaction");
						transaction.setCoin(coinService.findByName(coin.getName()));
						transaction.setTransactiondate(new Date());
						transaction.setTransactionaddress("");
						transactionService.addTransaction(transaction);
					}
				}
				
				if (result) {
					WebAPICall.updatePayoutProcessedStatus(index);
				}
				
				return new ModelAndView("redirect:/adminhome");
			}
			
			return new ModelAndView("redirect:/");
		} else {
			return new ModelAndView("redirect:/");
		}
	}
	
	@RequestMapping(value = "/adminsendmoney", method = RequestMethod.GET)
	public String adminSendMoney(Model model, HttpServletRequest request) {
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			
			String currentIndex = request.getParameter("currentindex");
			if (currentIndex != null && !currentIndex.equals("")) {
				CoinDTO coin = WebAPICall.getProductDetail(Integer.parseInt(currentIndex));
				
				model.addAttribute("coinName", coin.getName());
				model.addAttribute("coinAmount", coin.getSymbol() + " " + coin.getAmount());
				
				model.addAttribute("currentindex", currentIndex);
				User user = userService.findByUserid(Long.parseLong(userId.toString()));
				
				model.addAttribute("currentUser", user);
				
				List<User> users = userService.findByIsadminuser(false);
				model.addAttribute("users", users);
				
				return "adminsendmoney";
			}
			
			return "adminhome";
		} else {
			return "home";
		}
	}
	
	@RequestMapping(value = "/sendmoney", method = RequestMethod.GET)
	public String sendMoney(Model model, HttpServletRequest request) {
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			
			String currentIndex = request.getParameter("currentindex");
			if (currentIndex != null && !currentIndex.equals("")) {
				CoinDTO coin = WebAPICall.getProductDetail(Integer.parseInt(currentIndex));
				
				model.addAttribute("coinName", coin.getName());
				model.addAttribute("coinAmount", coin.getSymbol() + " " + coin.getAmount());
				
				model.addAttribute("currentindex", currentIndex);
				User user = userService.findByUserid(Long.parseLong(userId.toString()));
				
				model.addAttribute("currentUser", user);
				
				List<User> users = userService.findByIsadminuser(false);
				for (User u : users) {
					if (u.getUserid() == user.getUserid()) {
						users.remove(u);
						break;
					}
				}
				model.addAttribute("users", users);
				
				return "sendmoney";
			}
			
			return "myhome";
		} else {
			return "home";
		}
	}
	@RequestMapping(value = "/adminTransferAmount", method = RequestMethod.POST)
	public ModelAndView adminTransferAmount(Model model, HttpServletRequest httpServletRequest) {
		
		String selectedIndex = httpServletRequest.getParameter("selectedIndex");
		String anotherAccount = httpServletRequest.getParameter("anotherAccount");
		String amountValue = httpServletRequest.getParameter("amountValue");
		String description = httpServletRequest.getParameter("description");
		
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			
			CoinDTO coin = WebAPICall.getProductDetail(Integer.parseInt(selectedIndex));
			
			String transactionAddress = WebAPICall.transferAmount(Integer.parseInt(selectedIndex), user.getBcaddress(), anotherAccount, Integer.parseInt(amountValue));
			
			Transaction transaction = new Transaction();
			transaction.setFromaddress(user.getBcaddress());
			transaction.setToaddress(anotherAccount);
			transaction.setAmount(amountValue);
			transaction.setDescription(description);
			transaction.setCoin(coinService.findByName(coin.getName()));
			transaction.setTransactiondate(new Date());
			transaction.setTransactionaddress(transactionAddress);
			transactionService.addTransaction(transaction);
			
			return new ModelAndView("redirect:/adminhome");
		} else {
			return new ModelAndView("redirect:/");
		}
		
	}
	
	@RequestMapping(value = "/transferAmount", method = RequestMethod.POST)
	public ModelAndView transferAmount(Model model, HttpServletRequest httpServletRequest) {
		
		String selectedIndex = httpServletRequest.getParameter("selectedIndex");
		String anotherAccount = httpServletRequest.getParameter("anotherAccount");
		String amountValue = httpServletRequest.getParameter("amountValue");
		String description = httpServletRequest.getParameter("description");
		
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			
			CoinDTO coin = WebAPICall.getProductDetail(Integer.parseInt(selectedIndex));
			
			String transactionAddress = WebAPICall.transferAmount(Integer.parseInt(selectedIndex), user.getBcaddress(), anotherAccount, Integer.parseInt(amountValue));
			
			Transaction transaction = new Transaction();
			transaction.setFromaddress(user.getBcaddress());
			transaction.setToaddress(anotherAccount);
			transaction.setAmount(amountValue);
			transaction.setDescription(description);
			transaction.setCoin(coinService.findByName(coin.getName()));
			transaction.setTransactiondate(new Date());
			transaction.setTransactionaddress(transactionAddress);
			transactionService.addTransaction(transaction);
			
			return new ModelAndView("redirect:/myhome");
		} else {
			return new ModelAndView("redirect:/");
		}
		
	}
	
	@RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
	public ModelAndView addNewUser(Model model, HttpServletRequest httpServletRequest) {
		
		String fullname = httpServletRequest.getParameter("fullname");
		String username = httpServletRequest.getParameter("username");
		String password = httpServletRequest.getParameter("password");
		String confirmPassword = httpServletRequest.getParameter("confirmPassword");
		String contactNumber = httpServletRequest.getParameter("contactNumber");
		String email = httpServletRequest.getParameter("email");
		String address = httpServletRequest.getParameter("address");
		
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			if (password.equals(confirmPassword)) {
				User availableUser = userService.findByUsername(username);
				if (availableUser == null) {

					String bcAddress = WebAPICall.registerNewUser(password);
					boolean status = WebAPICall.unlockUser(bcAddress, password);
					User user = new User();
					user.setUsername(username);
					user.setFullname(fullname);
					user.setPassword(password);
					user.setContactnumber(contactNumber);
					user.setEmail(email);
					user.setAddress(address);
					user.setBcaddress(bcAddress);
					user.setUnlocked(status);
					user.setIsadminuser(false);
					userService.addUser(user);
					return new ModelAndView("redirect:/listusers");
					
				} else {
					model.addAttribute("registered", "Username is not available, Please try again!");
					return new ModelAndView("redirect:/newuser");
				}
				
				
			} else {
				model.addAttribute("registered", "Password does not match, Please try again!");
				return new ModelAndView("redirect:/newuser");
			}
			
		} else {
			return new ModelAndView("redirect:/");
		}
	}
	
	@RequestMapping(value = "/newcoin", method = RequestMethod.GET)
	public String newCoin(Model model, HttpServletRequest request) {
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			
			model.addAttribute("currentUser", user);
			
			return "newcoin";
		} else {
			return "home";
		}
	}
	
	@RequestMapping(value = "/addNewCoin", method = RequestMethod.POST)
	public ModelAndView addNewCoin(Model model, HttpServletRequest httpServletRequest) {
		
		String productName = httpServletRequest.getParameter("productName");
		String nominalValue = httpServletRequest.getParameter("nominalValue");
		String issueSize = httpServletRequest.getParameter("issueSize");
		String payoutRate = httpServletRequest.getParameter("payoutRate");
		String productSymbol = httpServletRequest.getParameter("productSymbol");
		String launchDate = httpServletRequest.getParameter("launchDate");
		String expiryDate = httpServletRequest.getParameter("expiryDate");
		String payoutsPerYear = httpServletRequest.getParameter("payoutsPerYear");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 
		try {
			Date date = sdf.parse(launchDate);
			launchDate = Long.toString(date.getTime());
			
			date = sdf.parse(expiryDate);
			expiryDate = Long.toString(date.getTime());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			Coin availableCoin = coinService.findByName(productName);
			if (availableCoin == null) {
				
				WebAPICall.createProductCoin(productName, nominalValue, issueSize, payoutRate, productSymbol, launchDate, expiryDate, Integer.parseInt(payoutsPerYear));
				Coin coin = new Coin();
				coin.setName(productName);
				coin.setSymbol(productSymbol);
				coin.setAmount("" + (Integer.parseInt(nominalValue) * Integer.parseInt(issueSize)));
				coin.setExpirydate(expiryDate);
				coin.setLaunchdate(launchDate);
				coin.setPayoutsperyear(Integer.parseInt(payoutsPerYear));
				coinService.addCoin(coin);
				return new ModelAndView("redirect:/adminhome");
				
			} else {
				model.addAttribute("registered", "Coin is not available, Please try again!");
			}
			
			return new ModelAndView("redirect:/newcoin");
		} else {
			return new ModelAndView("redirect:/");
		}
	}
	
	@RequestMapping(value = "/viewtransactions", method = RequestMethod.GET)
	public String viewTransactions(Model model, HttpServletRequest request) {
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			
			model.addAttribute("currentUser", user);
			
			String currentIndex = request.getParameter("currentindex");
			if (currentIndex != null && !currentIndex.equals("")) {
				
				CoinDTO coinDTO = WebAPICall.getProductDetail(Integer.parseInt(currentIndex));
				
				Coin coin = coinService.findByName(coinDTO.getName());
				
				List<Transaction> transactions = transactionService.findByCoin(coin);
				
				model.addAttribute("coinName", coinDTO.getName());
				model.addAttribute("coinAmount", coinDTO.getSymbol() + " " + coinDTO.getAmount());
				model.addAttribute("symbol", coinDTO.getSymbol());
				model.addAttribute("transactions", transactions);
				
				return "viewtransactions";
			}
			
		    return "adminhome";
		} else {
			return "home";
		}
	}
	
	@RequestMapping(value = "/alltransactions", method = RequestMethod.GET)
	public String allTransactions(Model model, HttpServletRequest request) {
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			
			model.addAttribute("currentUser", user);
			
			List<Transaction> transactions = transactionService.findAll();
			
			model.addAttribute("transactions", transactions);
			
			return "alltransactions";
		} else {
			return "home";
		}
	}
		
	@RequestMapping(value = "/mytransactions", method = RequestMethod.GET)
	public String myTransactions(Model model, HttpServletRequest request) {
		HttpSession session = getSession();
		Object userId = session.getAttribute("loginUser");
		
		if(userId != null) {
			
			User user = userService.findByUserid(Long.parseLong(userId.toString()));
			
			model.addAttribute("currentUser", user);
			
			String currentIndex = request.getParameter("currentindex");
			if (currentIndex != null && !currentIndex.equals("")) {
				
				CoinDTO coinDTO = WebAPICall.getProductDetail(Integer.parseInt(currentIndex));
				
				Coin coin = coinService.findByName(coinDTO.getName());
				
				List<Transaction> transactions = transactionService.findByCoinAndFromaddress(coin, user.getBcaddress());
				transactions.addAll(transactionService.findByCoinAndToaddress(coin, user.getBcaddress()));
				model.addAttribute("coinName", coinDTO.getName());
				model.addAttribute("coinAmount", coinDTO.getSymbol() + " " + coinDTO.getAmount());
				model.addAttribute("transactions", transactions);
				
				return "mytransactions";
			}
			
		    return "myhome";
		} else {
			return "home";
		}
	}
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(Model model, HttpServletRequest httpServletRequest) {
		
		String userName = httpServletRequest.getParameter("userName");
		String password = httpServletRequest.getParameter("password");
		String fullName = httpServletRequest.getParameter("fullName");
		String address = httpServletRequest.getParameter("address");
		String contactNumber = httpServletRequest.getParameter("contactNumber");
		String email = httpServletRequest.getParameter("email");
		
		User availableUser = userService.findByUsername(userName);
		if (availableUser == null) {
			User user = new User();
			user.setUsername(userName);
			user.setPassword(password);
			user.setFullname(fullName);
			user.setAddress(address);
			user.setContactnumber(contactNumber);
			user.setEmail(email);
			user.setUnlocked(false);
			user.setIsadminuser(false);
			
			String bcAddress = WebAPICall.registerNewUser(password);
			user.setBcaddress(bcAddress);
			
			userService.addUser(user);
				
			model.addAttribute("registered", "You have successfully registered...!");
			
		} else {
			model.addAttribute("registered", "Username is not available, Please try again!");
		}
		
		return "registration";
	}
	
}
