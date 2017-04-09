/**
 * 
 */
package dao.interfaces;



import java.util.List;

import beans.Order;

/**
 * @author Medvedeva Anastasiya
 *
 */
public interface OrderDAO {

	List<Order> showAllOrderes();

	void updateOrder(List<Order> allOrders);


}
