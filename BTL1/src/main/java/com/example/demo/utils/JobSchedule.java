package com.example.demo.utils;
//package com.example.demo.service;
//
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.example.demo.entity.Bill;
//import com.example.demo.repository.BillRepo;
//import com.example.demo.repository.UserRepo;
//
//@Component
//public class JobSchedule {
//
//	@Autowired
//	BillRepo billRepo;
//	
//	@Autowired
//	UserRepo userRepo;
//	
//	@Autowired
//	MailService mailService;
//	
//	@Scheduled(fixedDelay = 5000)
//	public void sendBill() {
//		
//		List<Bill> bills = billRepo.searchBill(new Date());
//		System.out.println(bills);
//		
////		List<Bill> bills1 = new ArrayList<Bill>();
////		for(Bill b : bills) {
////			if(new Date().getTime()-5*1000*60 <= b.getBuyDate().getTime() && b.getBuyDate().getTime() <= new Date().getTime()) {
////				
////				User u = userRepo.getById(b.getUser().getId());
////				mailService.sendEmail(u.getMailUser(), "Bill", "Ban co 1 don hang moi !");
////			}
////			
////		}
//		
////		int size = bill_Repo.findAll().size();
////		long date = new Date().getTime();
////		Date searchDate = new Date(date - 5*1000*60);
////		System.out.println("Bill: " + searchDate);
//	}
//	
////	@Scheduled(fixedDelay = 5000)
////	public void searchByDate() {
//////		Date size = bill_Repo.searchByDate(size);
////		int size = bill_Repo.findAll().size();
////		Date buyDate = new Date();
////		long timeMilli = buyDate.getTime();
////	    Date searchByDate = new Date(timeMilli - (5*1000*60));
////		System.out.println("bill: " + timeMilli);
////		System.out.println("Co don hang moi" + size);
////	}
//}
