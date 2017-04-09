package dao.interfaces;

import java.util.List;
import beans.Schedule;

public interface ScheduleDAO {

	List<Schedule> showAllSchedules();

	void updateSchedules(List<Schedule> allSchedules);

}
