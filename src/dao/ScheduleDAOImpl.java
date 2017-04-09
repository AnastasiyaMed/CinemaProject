package dao;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import beans.Schedule;
import dao.interfaces.ScheduleDAO;
import exeption.CustomFileNotFoundExeption;


public class ScheduleDAOImpl implements ScheduleDAO {
	private static ScheduleDAOImpl instance;

	/**
	 * Конструктор для реализации синглтона
	 * 
	 */
	public static ScheduleDAOImpl getInstance() {
		if (instance == null) {
			instance = new ScheduleDAOImpl();
		}
		return instance;
	}

	/**
	 * Парсим файл с расписаниями, создаем лист всех сеансов. Здесь индус-стайл
	 * потому что на xml времени нет, а БД нормальные нельзя использовать :)
	 * 
	 * @throws CustomFileNotFoundExeption
	 */

	@Override
	public List<Schedule> showAllSchedules() throws CustomFileNotFoundExeption {
		List<Schedule> schedules = new ArrayList<>();
		Scanner in = null;
		String nextInputString = "";
		String[] inputString;
	 try {
			in = new Scanner(new File("Schedule"));
			while (in.hasNext()) {
				nextInputString = in.nextLine();
				inputString = new String[nextInputString.split(" ").length];
				inputString = nextInputString.split(" ");
				Schedule schedule = initScedule(inputString);
				schedules.add(schedule);
		}
		} catch (FileNotFoundException e) {
			throw new CustomFileNotFoundExeption("Can't find the file", e);
	}finally
	{
		if (in != null) {
			in.close();
		}
		}
		return schedules;
	}


	/**
	 * Метод, который из порезанной входящей строки, особенной магией создает
	 * расписание
	 * 
	 */
	private Schedule initScedule(String[] inputString) {
		Schedule schedule = new Schedule();
		List <String> places = new ArrayList<>();
		schedule.setShowtimeID(Integer.parseInt(inputString[0]));
		schedule.setName(inputString[1]);
		schedule.setTime(inputString[2]);
		for (int i = 3; i < inputString.length; i++) {
			String place = inputString[i];
			places.add(place);
		}
		schedule.setPlaces(places);
		return schedule;
	}

	/**
	 * В этом методе обновляется файл с расписанием
	 * 
	 * @throws CustomFileNotFoundExeption
	 * 
	 * 
	 */
	@Override
	public void updateSchedules(List<Schedule> allSchedules) throws CustomFileNotFoundExeption {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("Schedule"), "Cp866"));
			for (Schedule shedule : allSchedules) {
				pw.println(shedule);
			}
		} catch (UnsupportedEncodingException e) {
			throw new CustomFileNotFoundExeption("Trouble whith encoding", e);
		} catch (FileNotFoundException e) {
			throw new CustomFileNotFoundExeption("Can't find the file", e);
		} finally {
			if (pw != null) {
				pw.close();
			}
	}
	}

	/**
	 * проверяет, есть ли такой сеанс
	 * 
	 * @throws CustomFileNotFoundExeption
	 * 
	 */
	public boolean checkFindSchedule(String time) throws CustomFileNotFoundExeption {
		boolean check = false;
		List<Schedule> allSchedules = showAllSchedules();
		for (Schedule sch : allSchedules) {
			if ((time).equals(sch.getTime())) {
				check = true;
				break;
			} else {
				check = false;
			}
		}
		return check;
	}

	public boolean checkFreePlace(String choicePlace, String time) throws CustomFileNotFoundExeption {
		boolean checkPlace = false;
		List<String> choicePlaces = new ArrayList<>();
		// строка из вьюхи, с выбранными местами разрезается в массив
		String[] arrayChoicePlaces = new String[choicePlace.split("(\\D+)").length];
		arrayChoicePlaces = choicePlace.split("(\\D++)");
		// складываются места в лист
		for (int i = 0; i < choicePlace.split("(\\D++)").length; i++) {
			String place = arrayChoicePlaces[i];
			choicePlaces.add(place);
		}

		List<String> freePlace = new ArrayList<>();
		List<Schedule> allSchedules = showAllSchedules();
		for (Schedule sch : allSchedules) {
			if (time.equals(sch.getTime())) {
				freePlace = sch.getPlaces();
			}
		}
		if ((choicePlaces.size() > 0) && (freePlace.containsAll(choicePlaces))) {
			checkPlace = true;
		} else {
			checkPlace = false;
		}
		return checkPlace;
	}

}
	
	