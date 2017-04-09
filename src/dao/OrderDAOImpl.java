package dao;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import beans.Order;
import dao.interfaces.OrderDAO;
import exeption.CustomFileNotFoundExeption;


public class OrderDAOImpl implements OrderDAO {
	private static OrderDAOImpl instance;

	/**
	 * Конструктор для реализации синглтона
	 * 
	 */
	public static OrderDAOImpl getInstance() {
		if (instance == null) {
			instance = new OrderDAOImpl();
		}
		return instance;
	}

	@Override
	public List<Order> showAllOrderes() throws CustomFileNotFoundExeption {
		List<Order> orders = new ArrayList<>();
		Scanner in = null;
		String nextInputString = "";
		String[] inputString;
		try {
			in = new Scanner(new File("Order"));
			while (in.hasNext()) {
				nextInputString = in.nextLine();
				inputString = new String[nextInputString.split(" ").length];
				inputString = nextInputString.split(" ");
				Order order = initOrder(inputString);
    		orders.add(order);
			}
		} catch (FileNotFoundException e) {
			throw new CustomFileNotFoundExeption("Can't find the file", e);
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return orders;
	}


	/**
	 * Метод, который из порезанной входящей строки, особенной магией
	 * инициализирует заказ
	 * 
	 */
	private Order initOrder(String[] inputString) {
		Order order = new Order();
		List<String> orderPlaces = new ArrayList<>();
		order.setNumberOfOrder(Integer.parseInt(inputString[0]));
		order.setShowTime(inputString[1]);
		for (int i = 2; i < inputString.length; i++) {
			String place = inputString[i];
			orderPlaces.add(place);
		}
		order.setPlacesOrder(orderPlaces);
		return order;
	}

	/**
	 * метод находит заказ по номеру
	 * 
	 * @throws CustomFileNotFoundExeption
	 * 
	 */
	public Order findOrderByNumber(int numberOfOrder) throws CustomFileNotFoundExeption {
		Order order = new Order();
		List<Order> allOrders = showAllOrderes();
		for (Order anyOrder : allOrders) {
			if (numberOfOrder == anyOrder.getNumberOfOrder()) {
				order = anyOrder;
		}
		}
		return order;
	}


	/**
	 * перезаписываем файл со списком заказов
	 * 
	 * @throws CustomFileNotFoundExeption
	 */
	@Override
	public void updateOrder(List<Order> allOrders) throws CustomFileNotFoundExeption {
		PrintWriter pw = null;
		File orders = new File("Order");
		if (orders.exists()) {
			try {
				orders.delete();
				orders.createNewFile();
			} catch (IOException ex) {
				throw new CustomFileNotFoundExeption("Trouble whith writing to file ", ex);
			}
		}
		try {
			pw = new PrintWriter(orders);
			for (Order order : allOrders) {
				pw.println(order);
			}
		} catch (FileNotFoundException e) {
			throw new CustomFileNotFoundExeption("Can't find the file", e);
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	/**
	 * метод дает начальное значение счетчичка заказов
	 * 
	 * @throws CustomFileNotFoundExeption
	 */
	public int setInitialCount() throws CustomFileNotFoundExeption {
		int initialCount = 0;
		List<Order> allOrders = showAllOrderes();
		initialCount = (allOrders.size() + 1);
		return initialCount;

	}

	/**
	 * метод проверяет, есть ли такой заказ
	 * 
	 * @throws CustomFileNotFoundExeption
	 * 
	 */
	public boolean checkNumberOfOrder(int numberOfOrder) throws CustomFileNotFoundExeption {
		boolean check = false;
		List<Order> allOrders = showAllOrderes();
		for (Order order : allOrders) {
			if (numberOfOrder == order.getNumberOfOrder()) {
				check = true;
				break;
			} else {
				check = false;
			}
		}
		return check;
	}

}

