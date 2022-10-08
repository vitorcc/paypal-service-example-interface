package application;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String date = "25/06/2018";
		
		Contract contract = new Contract(8028, LocalDate.parse(date, dtf), 600.00);
		ContractService contractService = new ContractService();
		
		contractService.setOnlinePaymentService(new PaypalService());
		
		contractService.processContract(contract, 12);
		
		for(Installment installment : contract.getInstallments()) {
			System.out.println(installment.toString());
		}
		

	}

}
