package grupo11.demo.domain.order;

import java.util.ArrayList;
import java.util.List;

import jakarta.transaction.Transactional;

import grupo11.demo.app.context.AuthContext;
import grupo11.demo.domain.cart.CartService;
import grupo11.demo.domain.cart.CartWithItems;
import grupo11.demo.domain.order.item.OrderItem;
import grupo11.demo.domain.order.item.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final CartService cartService;

    private final AuthContext authContext;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
                        CartService cartService, AuthContext authContext) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartService = cartService;
        this.authContext = authContext;
    }

    public List<Order> listByUser() {
        var orders = orderRepository.findByUserId(authContext.getUserId());
        return null;
    }

    @Transactional
    public Order execute() {
        var order = new Order();
        order.setUserId(authContext.getUserId());
        orderRepository.save(order);

        var cart = cartService.getCompleteCart();
        createOrderItems(order.getId(), cart);
        return order;
    }

    @Transactional
    private void createOrderItems(long orderId, CartWithItems cart) {
        var orderItems = new ArrayList<OrderItem>();
        for (var cartItem : cart.getItems()) {
            var orderItem = new OrderItem();
            orderItem.setGameId(cartItem.getId());
            orderItem.setOrderId(orderId);
            orderItems.add(orderItem);
        }

        orderItemRepository.saveAll(orderItems);
    }
}
