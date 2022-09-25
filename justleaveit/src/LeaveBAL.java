import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LeaveBAL {
    static StringBuilder errorMessage = new StringBuilder();
    static LeaveDAO dao = new LeaveDAO();

    public String addLeaveBAL(Leave leave) throws LeaveException {
        if (isValid(leave) == false) {
            throw new LeaveException(errorMessage.toString());
        }

        return dao.addLeave(leave);
    }
    public List<Leave> showLeaveBAL(){
        return dao.showLeaveDAO();
    }


    public Leave searchLeaveBAL(int leaveId){
        return dao.searchLeaveDAO(leaveId);
    }

    public String updateLeaveBAL(Leave leave) throws LeaveException {
        if(isValid(leave) == false){
            throw new LeaveException(errorMessage.toString());
        }

        return dao.updateLeaveDAO(leave);
    }

    public String deleteLeaveBAL(int leaveId){
        return dao.deleteLeaveDAO(leaveId);
    }

    public boolean isValid(Leave leave){
        boolean isValid = true;

        if (leave.getLeaveId() <= 0 || leave.getEmpId() <= 0){
            isValid = false;
            errorMessage.append("Leave ID or Employ ID can not be equal or less than ZERO.");
        }

        //Date Validations
        Calendar calendar = Calendar.getInstance();
        Date currentDate= calendar.getTime();
        if (currentDate.after(leave.getLeaveStartDate())){
            isValid = false;
            errorMessage.append("Leave Start Date can not be a PAST date.");
        }

        if (currentDate.after(leave.getLeaveEndDate())){
            errorMessage.append("Leave End Date can not be a PAST date.");
        }

        if (leave.getLeaveStartDate().after(leave.getLeaveEndDate())) {
            isValid = false;
            errorMessage.append("Leave End Date can not be LESSER than Start Date.");
        }

        return isValid;
    }
}
