import java.util.ArrayList;
import java.util.List;

public class LeaveDAO {
    static List<Leave> leaveList;

    static {
        leaveList = new ArrayList<>();
    }

    public String addLeave(Leave leave){
        leaveList.add(leave);
        return "Leave request added successfully, enjoy the day.";
    }

    public List<Leave> showLeaveDAO(){
        return leaveList;
    }

    public Leave searchLeaveDAO(int leaveId){
        Leave leaveFound = null;

        for (Leave leave : leaveList) {
            if (leave.getLeaveId() == leaveId){
                leaveFound = leave;
            }
        }
        return leaveFound;
    }

    public String updateLeaveDAO(Leave leaveNew){
        Leave leaveFound = searchLeaveDAO(leaveNew.getLeaveId());

        if (leaveFound != null){
            for (Leave leave : leaveList) {
                if (leave.getLeaveId() == leaveNew.getLeaveId()){
                    leave.setLeaveStartDate(leaveNew.getLeaveStartDate());
                    leave.setLeaveEndDate(leaveNew.getLeaveEndDate());
                    leave.setLeaveType(leaveNew.getLeaveType());
                    leave.setLeaveReason(leaveNew.getLeaveReason());
                    leave.setNoOfDays(leaveNew.getNoOfDays());
                }
            }
        return "Your leave has been updated successfully.";
        }
        return "Record NOT found.";
    }

    public String deleteLeaveDAO(int leaveId){
        Leave leaveFound = searchLeaveDAO(leaveId);

        if (leaveFound != null){
            leaveList.remove(leaveFound);
            return "Leave Request Deleted.";
        }
        return "Leave Request NOT found, please check the leave ID.";
    }
}
