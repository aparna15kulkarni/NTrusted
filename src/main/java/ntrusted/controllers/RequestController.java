package ntrusted.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import ntrusted.models.Advertisement;
import ntrusted.models.AdvertisementDao;
import ntrusted.models.Request;
import ntrusted.models.RequestDao;
import ntrusted.models.Transaction;
import ntrusted.models.TransactionDao;
import ntrusted.models.User;
import ntrusted.models.UserDao;


@Controller
@RequestMapping(value="/request")
public class RequestController {

	@Autowired
	  private RequestDao _reDao;
	@Autowired
	 private UserDao _userDao;
	@Autowired
	private AdvertisementDao _adDao;
	@Autowired
	private TransactionDao _trxDao;

	@RequestMapping(value="/addBorrowRequest", method=RequestMethod.POST)
	@ResponseBody
	public String createBorrowRequest(@RequestParam(value="adId")int adId,
			  			   @RequestParam(value="senderId") String senderId,
			  			   @RequestParam(value="Date") Date date,
			  			   @RequestParam(value="requestType") int requestType 
			  			   ) 
	{
		Advertisement ad = _adDao.getById(adId);
		Request req = new Request(_userDao.getById(senderId),ad.getUser(), ad, 1, 1);
		
		_reDao.save(req);

		return "Borrowing Request Added in Request table";

	}
	
	@RequestMapping(value="/addLendingRequest", method=RequestMethod.POST)
	@ResponseBody
	public String createLendRequest(@RequestParam(value="adId")int adId,
			  			   @RequestParam(value="senderId") String senderId,
			  			   @RequestParam(value="Date") Date date,
			  			   @RequestParam(value="requestType") int requestType 
			  			   ) 
	{
		Advertisement ad = _adDao.getById(adId);
		Request req = new Request(_userDao.getById(senderId),ad.getUser(), ad, 1, 2);
		
		_reDao.save(req);

		return "Lending Request Added in Request table";

	}
	 	
	//Requests from someone who wants to borrwo my items
	@RequestMapping(value="/getBorrowRequests", method=RequestMethod.GET)
	@ResponseBody
	public List<Request> getBorrowRequests(String receiverId)
	{
		return (List<Request>)_reDao.getBorrowRequest(receiverId);
	}
	
	//Requests from someone who wants to Lend me their items
	@RequestMapping(value="/getLendingRequests", method=RequestMethod.GET)
	@ResponseBody
	public List<Request> getLendingRequests(String receiverId)
	{			
		return (List<Request>)_reDao.getLendRequest(receiverId);
	}
	
	//Update if request Accepted by accepted/Declined
	@RequestMapping(value="/AcceptDeclineRequest", method=RequestMethod.GET)
	@ResponseBody
	public String AcceptDeclineRequests(int requestId, int response)
	{
		Request req = _reDao.getRequestById(requestId);
		User renter;
		User rentee;
		//response 0=declined, 2= accepted
		//AdType  1 = Lend Product, 2 = Borrow Product
		//Request Type: Who is sending the request - int 1: borrow 2:lend
		if(response == 0 )
		{
			req.setResponse(response);	
		}
		else
		{
			req.setResponse(response);
			
			//Get renter and rentee according to advertisement
			Advertisement ad = req.getAdvertisement();
			if(ad.getAdType() == 1)
			{
				renter = ad.getUser();
				if(req.getRequestType() == 1)
				{
					rentee = req.getSender();
				}
				else
				{
					rentee = req.getReceiver();
				}
				
			}
			else
			{
				rentee = ad.getUser(); 
				
				if(req.getRequestType() == 1)
				{
					renter = req.getReceiver();
				}
				else
				{
					renter = req.getSender();
				}
			}
			
			//set Advertisemnt active status as busy
			ad.setActive(2);
			_adDao.update(ad);
			
			//convert date format to desired format
			
				java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
				
				//parse the string to date using .parse(string) function and add it to transaction table.
				Transaction trx = new Transaction(ad, req, 0, 0,
						date,null,0,0,renter,rentee, 1);
			
			//Add to trx i.e start trx
			
		}
		_reDao.update(req);
		return "Request updated successfully";
	}
		
}