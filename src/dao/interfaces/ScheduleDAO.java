package dao.interfaces;

import java.util.List;

import beans.Schedule;
import exeption.CustomFileNotFoundExeption;

public interface ScheduleDAO {

	List<Schedule> showAllSchedules() throws CustomFileNotFoundExeption;

	void updateSchedules(List<Schedule> allSchedules) throws CustomFileNotFoundExeption;

}
