package utils;

import java.util.ArrayList;
import java.util.List;

import beans.Order;
import beans.Schedule;
import dao.OrderDAOImpl;
import dao.ScheduleDAOImpl;

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
	 */

	public List<Schedule> showSchedule() {
		List<Schedule> scheduleToShow = ScheduleDAOImpl.getInstance().showAllSchedules();
		return scheduleToShow;
	}

	/**
	 * Просмотр всех заказов
	 * 
	 */

	public List<Order> showOrders() {
		List<Order> orders = OrderDAOImpl.getInstance().showAllOrderes();
		return orders;
	}

	/**
	 * метод находит заказ по номеру
	 * 
	 */
	public Order findOrderByNumber(int numberOfOrder) {
		Order order = OrderDAOImpl.getInstance().findOrderByNumber(numberOfOrder);
		return order;
	}


	/**
	 * создание нового заказа
	 * 
	 */
	public Order createNewOrder(String choice, String choicePlacesOrder) {

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
	 */
	public Order showFindOrder(int numberFindOrder) {
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
	 */
	public boolean checkFindSchedule(String time) {
		boolean check = ScheduleDAOImpl.getInstance().checkFindSchedule(time);
		return check;
	}


	/**
	 * удаление заказа
	 * 
	 */

	public void deleteOrder(int numberFindOrder) {
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

	public boolean checkFreePlace(String choicePlace, String time) {
		boolean checkPlace = ScheduleDAOImpl.getInstance().checkFreePlace(choicePlace, time);
		return checkPlace;
	}

	/**
	 * метод проверяет, есть ли такой заказ
	 * 
	 */
	public boolean checkNumberOfOrder(int numberOfOrder) {
		boolean check = false;
		check = OrderDAOImpl.getInstance().checkNumberOfOrder(numberOfOrder);
		return check;
	}

}
