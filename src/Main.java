import java.util.List;

import beans.Order;
import beans.Schedule;
import exeption.CustomFileNotFoundExeption;
import utils.Util;

public class Main {

	public static void main(String[] args) throws CustomFileNotFoundExeption {


		List<Schedule> show = Util.getInstance().showSchedule();
		for (Schedule schedule : show) {
			System.out.println(schedule);
		}

		System.out.println("Выберите сеанс");
		String choice = "15:40";
		System.out.println("Пользователь выбрал" + " " + choice);

		String choicePlacesOrder = "2, 4, 6";
		System.out.println(choicePlacesOrder);

		String choicePlacesOrder2 = "3";
		System.out.println(choicePlacesOrder2);

		Order order = Util.getInstance().createNewOrder(choice, choicePlacesOrder);
		System.out.println("Ваш заказ " + order);
		show = Util.getInstance().showSchedule();
		for (Schedule schedule : show) {
			System.out.println(schedule);
		}

		System.out.println(order);

		Order order2 = Util.getInstance().createNewOrder(choice, choicePlacesOrder2);

		System.out.println(order2);



		int numberFindOrder = 2;

		Order findOrder = Util.getInstance().showFindOrder(numberFindOrder);
		System.out.println("Вы забронировали " + findOrder);

		Util.getInstance().deleteOrder(numberFindOrder);
		System.out.println("Заказ номер " + numberFindOrder + "удален");


		System.out.println("Просмотр расписания");
		show = Util.getInstance().showSchedule();
		for (Schedule schedule : show) {
			System.out.println(schedule);
		}

		System.out.println("Просмотр заказов:");
		List<Order> allOrders = Util.getInstance().showOrders();
		for (Order order1 : allOrders) {
			System.out.println(order1);
		}
		// ..
	}
}
