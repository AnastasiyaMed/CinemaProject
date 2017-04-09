package utils;

import java.util.ArrayList;
import java.util.List;

import beans.Order;
import beans.Schedule;
import dao.OrderDAOImpl;
import dao.ScheduleDAOImpl;
import exeption.CustomFileNotFoundExeption;

public class Util {
	private static Util instance;

	private Util() {
	}

	/**
	 * Конструктор для реализации синглтона
	 * 
	 */
	public static Util getInstance() {
		if (instance == null) {
			instance = new Util();
		}
		return instance;
	}

	/**
	 * Просмотр всех сеансов
	 * 
	 * @throws CustomFileNotFoundExeption
	 * 
	 */

	public List<Schedule> showSchedule() throws CustomFileNotFoundExeption {
		List<Schedule> scheduleToShow = ScheduleDAOImpl.getInstance().showAllSchedules();
		return scheduleToShow;
	}

	/**
	 * Просмотр всех заказов
	 * 
	 * @throws CustomFileNotFoundExeption
	 * 
	 */

	public List<Order> showOrders() throws CustomFileNotFoundExeption {
		List<Order> orders = OrderDAOImpl.getInstance().showAllOrderes();
		return orders;
	}

	/**
	 * метод находит заказ по номеру
	 * 
	 * @throws CustomFileNotFoundExeption
	 * 
	 */
	public Order findOrderByNumber(int numberOfOrder) throws CustomFileNotFoundExeption {
		Order order = OrderDAOImpl.getInstance().findOrderByNumber(numberOfOrder);
		return order;
	}


	/**
	 * создание нового заказа
	 * 
	 * @throws CustomFileNotFoundExeption
	 * 
	 */
	public Order createNewOrder(String choice, String choicePlacesOrder) throws CustomFileNotFoundExeption {

		List<String> placesOrder = new ArrayList<String>();
		List<Schedule> scheduleToShow = showSchedule(); // усе расписания
		Schedule findSchedule = null; // сеанс на котором бронируем места
		// строка из вьюхи, с выбранными местами разрезается в массив
		String[] arrayChoicePlacesOrder = new String[choicePlacesOrder.split("(\\D+)").length];
		arrayChoicePlacesOrder = choicePlacesOrder.split("(\\D++)");
		// складываются места в лист
  		for (int i = 0; i < choicePlacesOrder.split("(\\D++)").length; i++) {
			String place = arrayChoicePlacesOrder[i];
			placesOrder.add(place);
		}
		for (Schedule schedule : scheduleToShow) {
			if (choice.equals(schedule.getTime())) {
				findSchedule = schedule;
			}
		}
		Order order = new Order(findSchedule.getTime(), placesOrder);
		// обновляем список свободных мест на сеансе
		List<String> swowtimePlaces = findSchedule.getPlaces();
		swowtimePlaces.removeAll(placesOrder);
		ScheduleDAOImpl.getInstance().updateSchedules(scheduleToShow);
		// обновляем список заказов
		List<Order> allOrders = OrderDAOImpl.getInstance().showAllOrderes();
		allOrders.add(order);
		OrderDAOImpl.getInstance().updateOrder(allOrders);
		return order;
	}



	/**
	 * просмотр заказа по номеру
	 * 
	 * @throws CustomFileNotFoundExeption
	 * 
	 */
	public Order showFindOrder(int numberFindOrder) throws CustomFileNotFoundExeption {
		List<Order> allOrders = OrderDAOImpl.getInstance().showAllOrderes();
		Order findOrder = null;
		for (Order anyOrder : allOrders) {
			if (anyOrder.getNumberOfOrder() == numberFindOrder) {
				findOrder = anyOrder;
			}
		}
		return findOrder;
	}

	/**
	 * проверяет, есть ли такой сеанс
	 * 
	 * @throws CustomFileNotFoundExeption
	 * 
	 */
	public boolean checkFindSchedule(String time) throws CustomFileNotFoundExeption {
		boolean check = ScheduleDAOImpl.getInstance().checkFindSchedule(time);
		return check;
	}


	/**
	 * удаление заказа
	 * 
	 * @throws CustomFileNotFoundExeption
	 * 
	 */

	public void deleteOrder(int numberFindOrder) throws CustomFileNotFoundExeption {
		Order order = showFindOrder(numberFindOrder);
		// возвращаем места из заказа обратно расписание
		// сделана заглушка для консольного варианта, надо обработать вариант с возвращением заказа null по другому, чтобы можно было передать информацию на вью
		//
		List<Schedule> allSchedules = ScheduleDAOImpl.getInstance().showAllSchedules();
		if (order != null) {
			List<String> placesOrder = order.getPlacesOrder();
			String showTime = order.getShowTime();
			List<String> placesSchedule = new ArrayList<>();
			for (Schedule schedule : allSchedules) {
				if (showTime.equals(schedule.getTime())) {
				placesSchedule = schedule.getPlaces();
			}
		}
		placesSchedule.addAll(placesOrder);
			// перезаписываем обновленное расписание
		ScheduleDAOImpl.getInstance().updateSchedules(allSchedules);
			// достаем из файла список с заказами, чтобы удалить из него нужный
			List<Order> allOrders = OrderDAOImpl.getInstance().showAllOrderes();
			allOrders.remove(order);
			OrderDAOImpl.getInstance().updateOrder(allOrders);
		} else {
			System.out.println("Выбранный для удаления заказ не найден");
		}
	}

	/**
	 * метод проверяет, свободны ли места
	 * 
	 * @throws CustomFileNotFoundExeption
	 * 
	 */
	public boolean checkFreePlace(String choicePlace, String time) throws CustomFileNotFoundExeption {
		boolean checkPlace = ScheduleDAOImpl.getInstance().checkFreePlace(choicePlace, time);
		return checkPlace;
	}

	/**
	 * метод проверяет, есть ли такой заказ
	 * 
	 * @throws CustomFileNotFoundExeption
	 * 
	 */
	public boolean checkNumberOfOrder(int numberOfOrder) throws CustomFileNotFoundExeption {
		boolean check = false;
		check = OrderDAOImpl.getInstance().checkNumberOfOrder(numberOfOrder);
		return check;
	}

}
