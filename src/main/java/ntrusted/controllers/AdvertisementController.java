package ntrusted.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ntrusted.models.Advertisement;
import ntrusted.models.AdvertisementDao;
import ntrusted.models.Category;
import ntrusted.models.CategoryDao;
import ntrusted.models.Transaction;
import ntrusted.models.User;
import ntrusted.models.UserDao;

@Controller
@RequestMapping(value="/advertisement")
public class AdvertisementController {
	 @Autowired
	  private AdvertisementDao _adDao;
	 
	 @Autowired 
	 private CategoryDao _catDao;
	 
	 @Autowired
	 private UserDao _userDao;
	 
	 @RequestMapping(value="/delete")
	  @ResponseBody
	  public String delete(int id) {
	    try {
	      Advertisement advertisement = new Advertisement(id);
	      _adDao.delete(advertisement);
	    }
	    catch(Exception ex) {
	      return ex.getMessage();
	    }
	    return "Advertisement succesfully deleted!";
	  }
	 
	 @RequestMapping(value="/addLendingProduct", method=RequestMethod.POST)
	  @ResponseBody
	  public String createAdvertisement(@RequestParam(value="productName")String productName, 
			  @RequestParam(value="productDescription") String productDescription, 
			  @RequestParam(value="productPrice") float productPrice, 
			  @RequestParam(value="postDate")Date postDate,
			  @RequestParam(value="active") int active, 
			  @RequestParam(value="categoryId") int categoryId, 
			  @RequestParam(value="userId") String userId,
			  @RequestParam(value="adType") int adType) {
	    try {
	    	//Category category = new Category(categoryId);
	    	Category category = _catDao.getById(categoryId);
	    	User user = _userDao.getById(userId);
	    	System.out.println(category.getCategoryName());
	  
	    	Advertisement ad = new Advertisement(productName, productDescription, 
						   productPrice, postDate, active, category, user,adType);	
				 _adDao.save(ad);
			}
			catch(Exception ex) {
			      return ex.getMessage();
			    }
			
	    
	    return "Advertisement details saved!";
	  }
	 
	 //Add Borrowing ad
}
