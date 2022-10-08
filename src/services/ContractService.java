package services;

import java.time.LocalDate;

import entities.Contract;
import entities.Installment;
import interfaces.OnlinePaymentService;

public class ContractService {

	private OnlinePaymentService onlinePaymentService;

	public OnlinePaymentService getOnlinePaymentService() {
		return onlinePaymentService;
	}

	public void setOnlinePaymentService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, int months) {

		double valueFee = contract.getTotalValue() / months;

		for (int i = 1; i <= months; i++) {
			LocalDate dateContract = contract.getDate().plusMonths(i);			
			double interest = onlinePaymentService.interest(valueFee, i);
			double fee = onlinePaymentService.paymentFee(valueFee + interest);

			contract.getInstallments().add(new Installment(dateContract, valueFee + interest + fee));
		}

	}

}
