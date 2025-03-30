package com.example.parkinglot;

import com.example.parkinglot.controllers.BillController;
import com.example.parkinglot.controllers.OperatorController;
import com.example.parkinglot.controllers.TicketController;
import com.example.parkinglot.dtos.*;
import com.example.parkinglot.models.SupportedVehicle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


import java.util.Scanner;

@SpringBootApplication
@EnableJpaAuditing
public class ParkingLotApplication {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		ApplicationContext context = SpringApplication.run(ParkingLotApplication.class, args);

		OperatorController operatorController = context.getBean(OperatorController.class);

		OperatorSignupRequestDto operatorSignupRequestDto = new OperatorSignupRequestDto();
		OperatorSignupResponseDto operatorSignupResponseDto = null;
		OperatorLoginRequestDto operatorLoginRequestDto = new OperatorLoginRequestDto();
		OperatorLoginResponseDto operatorLoginResponseDtoTicket = null;


		System.out.println("Do you want to sign up? Y/N");
		String isSignUp = scanner.nextLine();

		String name;
		String password;
		String email;
		if (isSignUp.equals("Y") || isSignUp.equals("y")) {
			System.out.println("Please enter your name:");
			name = scanner.nextLine();
			System.out.println("Please enter your email:");
			email = scanner.nextLine();
			System.out.println("Please enter your password:");
			password = scanner.nextLine();

			operatorSignupRequestDto.setName(name);
			operatorSignupRequestDto.setEmail(email);
			operatorSignupRequestDto.setPassword(password);

			operatorSignupResponseDto = operatorController.signup(operatorSignupRequestDto);

			if(operatorSignupResponseDto.getResponseStatus().equals(ResponseStatus.SUCCESS)){
				System.out.println(operatorSignupRequestDto.getName()+ " signed up successfully.");
				System.out.println("Your operator id is " + operatorSignupResponseDto.getOperatorId());
			}
			else{
				System.out.println("Sign up failed. Response status: " + operatorSignupResponseDto.getResponseStatus());
			}
		}

		System.out.println("Do you want to login? Y/N");
		String isLogin = scanner.nextLine();

		TicketController ticketController = context.getBean(TicketController.class);

		IssueTicketRequestDto issueTicketRequestDto = new IssueTicketRequestDto();
		IssueTicketResponseDto issueTicketResponseDto = null;

		if(isLogin.equals("Y") || isLogin.equals("y")) {
			operatorLoginResponseDtoTicket = login(operatorLoginRequestDto, operatorController);

			System.out.println("Ticket generation");

			System.out.println("Please enter Vehicle Number:");
			String vehicleNumber = scanner.nextLine();
			issueTicketRequestDto.setVehicleNumber(vehicleNumber);
			System.out.println(vehicleNumber);

			System.out.println("Please enter gate id:");
			Integer gateId = scanner.nextInt();
			issueTicketRequestDto.setGateId(gateId);
			System.out.println(gateId);
			scanner.nextLine();


			System.out.println("Please enter owner name:");
			String ownerName = scanner.nextLine();
			issueTicketRequestDto.setOwnerName(ownerName);
			System.out.println(ownerName);

			System.out.println("Please enter your operator id:");
			Integer operatorId = scanner.nextInt();
			issueTicketRequestDto.setOperatorId(operatorId);
			System.out.println(operatorId);
			scanner.nextLine();

			Integer selectedVehicleType=0;
			try {
				System.out.println("Please select vehicle type:");
				System.out.println("1 -> Two wheeler \n2 -> Four Wheeler");
				selectedVehicleType = scanner.nextInt();

				SupportedVehicle vehicleType;
				if (selectedVehicleType == 1) {
					vehicleType = SupportedVehicle.TWO_WHEELER;
				} else if (selectedVehicleType == 2) {
					vehicleType = SupportedVehicle.FOUR_WHEELER;
				} else {
					throw new IllegalArgumentException("Invalid vehicle type selected! Please enter 1 or 2.");
				}
				issueTicketRequestDto.setVehicleType(vehicleType);

			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());  // Displays error message
			}

			System.out.println(selectedVehicleType);
			scanner.nextLine();

			System.out.println("Please enter owner's license number:");
			String licenseNumber = scanner.nextLine();
			issueTicketRequestDto.setLicenseNumber(licenseNumber);


			issueTicketResponseDto = ticketController.issueTicket(issueTicketRequestDto);
		}

		if(issueTicketResponseDto != null && issueTicketResponseDto.getResponseStatus().equals(ResponseStatus.SUCCESS)){
			System.out.println("Ticket generated successfully.");
			System.out.println("Parking floor for the vehicle is " +issueTicketResponseDto.getTicket().getParkingFloor().getFloorNumber());
			System.out.println("Parking spot for the vehicle is " +issueTicketResponseDto.getTicket().getParkingSpot().getSpotNumber());
			System.out.println("Price for two wheelers is Rs.20 per hour.\n Vehicle parked more than 8 hours will be charged Rs.100 per hour. ");
			System.out.println("Price for four wheelers is Rs.40 per hour.\n Vehicle parked more than 8 hours will be charged Rs.200 per hour. ");

		}
		else{
			System.out.println("Ticket generation failed. ResponseStatus = " + issueTicketResponseDto.getResponseStatus() );
		}

		System.out.println("You are logged out.");
		System.out.println("Login in again for exit gate.\n");

		OperatorLoginResponseDto operatorLoginResponseDtoBill = login(operatorLoginRequestDto, operatorController);

		// operator on entry and exit gate should not be same
		while(operatorLoginResponseDtoBill.getOperator().getId() == operatorLoginResponseDtoTicket.getOperator().getId()){
			System.out.println("Operator on entry gate and exit gate should not be same. \n Operator id should not be same. ");
			operatorLoginResponseDtoBill = login(operatorLoginRequestDto, operatorController);
		}

		BillController billController = context.getBean(BillController.class);

		GenerateBillRequestDto generateBillRequestDto = new GenerateBillRequestDto();
		GenerateBillResponseDto generateBillResponseDto = null;

		System.out.println("Do you want to generate the bill: Y/N");
		String isGenerateBill = scanner.nextLine();

		Long ticketNumber;

		if(isGenerateBill.equals("y") || isGenerateBill.equals("Y")) {
			System.out.println("Please enter Ticket Number:");
			ticketNumber = scanner.nextLong();
			generateBillRequestDto.setTickerNumber(ticketNumber);

			System.out.println("Please enter your Operator id:");
			//operatorId = scanner.nextInt();
			//generateBillRequestDto.setOperatorId(operatorId);

			generateBillResponseDto = billController.generateBill(generateBillRequestDto);
		}

		if(generateBillResponseDto != null && generateBillResponseDto.getResponseStatus().equals(ResponseStatus.SUCCESS)){
			System.out.println("Bill generated successfully.");

		}

	}

	public static OperatorLoginResponseDto login(OperatorLoginRequestDto operatorLoginRequestDto,
					  OperatorController operatorController) {

		String name;
		String password;
		String email;
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter your email:");
		email = scanner.nextLine();
		operatorLoginRequestDto.setEmail(email);

		System.out.println("Please enter your password:");
		password = scanner.nextLine();
		operatorLoginRequestDto.setPassword(password);

		OperatorLoginResponseDto operatorLoginResponseDto = operatorController.login(operatorLoginRequestDto);

		if(operatorLoginResponseDto != null && operatorLoginResponseDto.getStatus().equals(ResponseStatus.SUCCESS)){
			System.out.println(operatorLoginRequestDto.getEmail()+ " logged in successfully.");
		}
		else{
			System.out.println("Login failed. ResponseStatus = " + operatorLoginResponseDto.getStatus() );
		}

		return operatorLoginResponseDto;

	}

}
