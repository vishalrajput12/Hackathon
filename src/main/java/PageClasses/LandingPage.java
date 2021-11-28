package PageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;

import BaseClasses.PageBaseClass;

public class LandingPage extends PageBaseClass {

	@FindBy(xpath="//*[@id='car-loan']/a")
	WebElement CarLoan;
	
	@FindBy(xpath="//*[@id='loanamount']")
	WebElement LoanAmount;
	
	@FindBy(xpath="//*[@id='loaninterest']")
	WebElement LoanInterest;
	
	@FindBy(xpath="//*[@id='loanterm']")
	WebElement LoanTenure;
	
	@FindBy(xpath="//*[@id='emicalculatorinnerform']/div[7]/div/div/div/div/div/label[1]")
	WebElement Year;
	
	//CarLoan Selected
	public void clickCarloan(){
		try{
		CarLoan.click();
		reportPass("CarLoan Clicked !!!!");
	}catch(Exception e){
		reportFail("Not Clicked!!!");
	}
	}
	
	//EnterDetails
	public void enterDetails(String Loanamount, String Loaninterest, String Tenure){
		
		LoanAmount.sendKeys(Keys.CONTROL + "a");
		LoanAmount.sendKeys(Keys.DELETE);
		try{
			LoanAmount.sendKeys(Loanamount);
	        reportPass("Entered LoanAmount");
		}catch(Exception e){
			 reportFail("LoanAmount didn't entered");
		}
		
		LoanInterest.sendKeys(Keys.CONTROL + "a");
		LoanInterest.sendKeys(Keys.DELETE);
		try{
			LoanInterest.sendKeys(Loaninterest);
		reportPass("Entered LoanInterest");
		}catch(Exception e){
			reportFail("LoanInterest didn't Entered");
		}
		
		LoanTenure.sendKeys(Keys.CONTROL + "a");
		LoanTenure.sendKeys(Keys.DELETE);
		try{
	
		LoanTenure.sendKeys(Tenure);
		reportPass("Entered Tenure");
		}catch(Exception e){
			reportFail("tenure didn't Entered");
		}
		sleep();
		try{
		Year.click();
		reportPass("Year Selected");
	}catch(Exception e){
		reportFail("Year not Selected");
	}
		
	}
	
	public void result(){
		
		String LoanEmi=driver.findElement(By.xpath("//*[@id='emiamount']/p/span")).getText();
		String TotalInterest=driver.findElement(By.xpath("//*[@id='emitotalinterest']/p/span")).getText();
		LoanEmi=LoanEmi.replaceAll(",","");
		TotalInterest=TotalInterest.replaceAll(",","");
		double interest=Integer.parseInt(TotalInterest);
		double emi=Integer.parseInt(LoanEmi);
		double MonthlyInterest=((interest)/12);
	    double MonthlyPrincipalAmount=emi-MonthlyInterest;
		System.out.println("Principal Amount for one month:- "+MonthlyPrincipalAmount);
		System.out.println("Interest of one month:- "+MonthlyInterest);
	}
	
}
