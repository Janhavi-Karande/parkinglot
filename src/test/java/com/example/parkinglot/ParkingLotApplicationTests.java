package com.example.parkinglot;

import com.example.parkinglot.controllers.BillController;
import com.example.parkinglot.controllers.OperatorController;
import com.example.parkinglot.controllers.TicketController;
import com.example.parkinglot.dtos.*;
import com.example.parkinglot.models.SupportedVehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ParkingLotApplicationTests {

	@Autowired
	OperatorController operatorController;
	@Autowired
	TicketController ticketController;
	@Autowired
	BillController billController;

	@Test
	void contextLoads() {
	}

	@Test
	void testLogin(){
		OperatorLoginRequestDto loginRequestDto = new OperatorLoginRequestDto();

		loginRequestDto.setEmail("sushilrao@gmail.com");
		loginRequestDto.setPassword("Sushilrao328");

		OperatorLoginResponseDto loginResponseDto = operatorController.login(loginRequestDto);

		if(loginResponseDto.getStatus().equals(ResponseStatus.SUCCESS))
			System.out.println("Login is successful.");

		else
			System.out.println("Login is failed.");
	}

	@Test
	void testSignup(){
		OperatorSignupRequestDto signupRequestDto = new OperatorSignupRequestDto();
		signupRequestDto.setEmail("reemasharma93@gmail.com");
		signupRequestDto.setPassword("Reemasharma@20");
		signupRequestDto.setName("Reema Sharma");

		OperatorSignupResponseDto signupResponseDto = operatorController.signup(signupRequestDto);

		if(signupResponseDto.getResponseStatus().equals(ResponseStatus.SUCCESS))
			System.out.println("Signup is successful.");
		else
			System.out.println("Signup is failed.");
	}

	@Test
	void testIssueTicket(){
		IssueTicketRequestDto issueTicketRequestDto = new IssueTicketRequestDto();

		issueTicketRequestDto.setOperatorId(10);
		issueTicketRequestDto.setLicenseNumber("MH2920020004940");
		issueTicketRequestDto.setOwnerName("Nilam Thakur");
		// entry gate, id = 1
		issueTicketRequestDto.setGateId(1);
		issueTicketRequestDto.setVehicleType(SupportedVehicle.TWO_WHEELER);
		issueTicketRequestDto.setVehicleNumber("MH29 BE 5868");

		IssueTicketResponseDto issueTicketResponseDto = ticketController.issueTicket(issueTicketRequestDto);

		if(issueTicketResponseDto.getResponseStatus().equals(ResponseStatus.SUCCESS)) {
			System.out.println("Issue ticket is successful.");
			System.out.println("Parking Spot is: " +issueTicketResponseDto.getTicket().getParkingSpot().getSpotNumber());
			System.out.println("Parking Floor is: " +issueTicketResponseDto.getTicket().getParkingFloor().getFloorNumber());
		}
			else
			System.out.println("Issue ticket is failed.");
	}

	@Test
	void testGenerateBill(){
		GenerateBillRequestDto generateBillRequestDto = new GenerateBillRequestDto();
		generateBillRequestDto.setOperatorId(9);
		generateBillRequestDto.setTickerNumber(7776832396582101570L);

		GenerateBillResponseDto generateBillResponseDto = billController.generateBill(generateBillRequestDto);

		if(generateBillResponseDto.getResponseStatus().equals(ResponseStatus.SUCCESS)){
			System.out.println("Bill is generated successfully.");
		}
		else{
			System.out.println("Bill generation is failed.");
		}
	}
}
