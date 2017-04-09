/**
 * 
 */
package dao.interfaces;



import java.util.List;

import beans.Order;
import exeption.CustomFileNotFoundExeption;

/**
 * @author Medvedeva Anastasiya
 *
 */
public interface OrderDAO {

	List<Order> showAllOrderes() throws CustomFileNotFoundExeption;

	void updateOrder(List<Order> allOrders) throws CustomFileNotFoundExeption;


}
