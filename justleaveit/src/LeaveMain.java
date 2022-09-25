import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LeaveMain {
    static Scanner input = new Scanner(System.in);
    static LeaveBAL bal = new LeaveBAL();

    public static void main(String[] args) {
        int choice;

        do {
        System.out.println("O P T I O N S");
        System.out.println("-------------");
        System.out.println("1. Take Leave");
        System.out.println("2. Show Leave Details");
        System.out.println("3. Search Leave Details");
        System.out.println("4. Update Leave Detail");
        System.out.println("----------------------");
        System.out.println("5. DELETE Leave Detail");
        System.out.println("6. EXIT");

        System.out.println("Enter your choice: ");
        choice = input.nextInt();

        switch (choice){
            case 1:
                try {
                    addLeaveMain();
                } catch (ParseException e) {
                    System.err.println(e.getMessage());
                } catch (LeaveException e){
                    System.err.println(e.getMessage());
                }
                break;
            case 2: showLeaveMain();
                break;
            case 3: searchLeaveMain();
                break;
            case 4:
                try {
                    updateLeaveMain();
                } catch (ParseException e) {
                    System.err.println(e.getMessage());
                } catch (LeaveException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case 5: deleteLeaveMain();
                break;
        }
        }while (choice != 6);
    }

    public static void addLeaveMain() throws ParseException, LeaveException{
        Leave leave = new Leave();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        System.out.println("Enter Leave ID: ");
        leave.setLeaveId(input.nextInt());

        System.out.println("Enter Start Date (in 'dd-MM-yyyy'): ");
        String startDate;
        leave.setLeaveStartDate(simpleDateFormat.parse(startDate = input.next()));

        System.out.println("Enter End Date (in 'dd-MM-yyyy'): ");
        String endDate;
        leave.setLeaveEndDate(simpleDateFormat.parse(endDate = input.next()));

        System.out.println("Enter your Employ ID: ");
        leave.setEmpId(input.nextInt());

        int sd=Integer.parseInt(startDate.substring(0, 2));
        int ed=Integer.parseInt(endDate.substring(0, 2));
        int sm=Integer.parseInt(startDate.substring(3, 5));
        int em=Integer.parseInt(endDate.substring(3, 5));
        int nod=0;
        if(sm==em)
            nod=ed-sd;
        if(em>sm)
        {
            nod=(30-sd)+ed;
        }
        leave.setNoOfDays(++nod);

        leave.setLeaveAppliedOn(new Date());

        System.out.println("Enter Leave Type: ");
        String leaveType = input.next();
        if (leaveType.equalsIgnoreCase("EL")){
            leave.setLeaveType(LeaveType.EL);
        }
        if (leaveType.equalsIgnoreCase("PL")){
            leave.setLeaveType(LeaveType.PL);
        }
        if (leaveType.equalsIgnoreCase("ML")){
            leave.setLeaveType(LeaveType.ML);
        }

        leave.setLeaveStatus(LeaveStatus.PENDING);

        System.out.println("Enter Leave Reason: ");
        leave.setLeaveReason(input.next());

        System.out.println(bal.addLeaveBAL(leave));
    }

    public static void showLeaveMain(){
        List<Leave> leaveList = bal.showLeaveBAL();

        for (Leave leave : leaveList) {
            System.out.println(leave);
        }
    }

    public static void searchLeaveMain(){
        int leaveId;

        System.out.println("Enter your leave ID: ");
        leaveId = input.nextInt();
        Leave leaveFound = bal.searchLeaveBAL(leaveId);

        if (leaveFound == null){
            System.out.println("Leave NOT found.");
        }else {
            System.out.println(leaveFound);
        }
    }

    public static void updateLeaveMain() throws ParseException, LeaveException{
        Leave leave = new Leave();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        System.out.println("Enter Leave ID: ");
        leave.setLeaveId(input.nextInt());

        System.out.println("Enter Start Date (in 'dd-MM-yyyy'): ");
        String startDate;
        leave.setLeaveStartDate(simpleDateFormat.parse(startDate = input.next()));

        System.out.println("Enter End Date (in 'dd-MM-yyyy'): ");
        String endDate;
        leave.setLeaveEndDate(simpleDateFormat.parse(endDate = input.next()));

        System.out.println("Enter your Employ ID: ");
        leave.setEmpId(input.nextInt());

        int sd=Integer.parseInt(startDate.substring(0, 2));
        int ed=Integer.parseInt(endDate.substring(0, 2));
        int sm=Integer.parseInt(startDate.substring(3, 5));
        int em=Integer.parseInt(endDate.substring(3, 5));
        int nod=0;
        if(sm==em)
            nod=ed-sd;
        if(em>sm)
        {
            nod=(30-sd)+ed;
        }
        leave.setNoOfDays(++nod);

        System.out.println("Enter Leave Type: ");
        String leaveType = input.next();
        if (leaveType.equalsIgnoreCase("EL")){
            leave.setLeaveType(LeaveType.EL);
        }
        if (leaveType.equalsIgnoreCase("PL")){
            leave.setLeaveType(LeaveType.PL);
        }
        if (leaveType.equalsIgnoreCase("ML")){
            leave.setLeaveType(LeaveType.ML);
        }

        System.out.println("Enter Leave Reason: ");
        leave.setLeaveReason(input.next());

        System.out.println(bal.updateLeaveBAL(leave));
    }

    public static void deleteLeaveMain(){
        int leaveId;

        System.out.println("Enter your Leave ID: ");
        leaveId = input.nextInt();

        System.out.println(bal.deleteLeaveBAL(leaveId));
    }
}
