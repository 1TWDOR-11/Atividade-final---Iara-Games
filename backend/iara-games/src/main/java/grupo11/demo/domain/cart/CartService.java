package grupo11.demo.domain.cart;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import grupo11.demo.app.context.AuthContext;
import grupo11.demo.domain.cart.item.CartItem;
import grupo11.demo.domain.cart.item.CartItemRepository;
import grupo11.demo.domain.exception.GameAlreadyPurchasedException;
import grupo11.demo.domain.exception.NotFoundException;
import grupo11.demo.domain.game.GameRepository;
import grupo11.demo.domain.user.inventory.UserGameRepository;
import grupo11.demo.infra.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final GameRepository gameRepository;

    private final UserGameRepository userGameRepository;

    private final AuthContext authContext;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository,
                       GameRepository gameRepository, UserGameRepository userGameRepository,
                       AuthContext authContext) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.gameRepository = gameRepository;
        this.userGameRepository = userGameRepository;
        this.authContext = authContext;
    }

    public Cart get() {
        var cart = cartRepository.findByUserId(authContext.getUserId());
        return cart == null ? create() : cart;
    }

    public CartWithItems getCompleteCart() {
        var cart = get();
        var cartItems = cartItemRepository.findByCartId(cart.getId());

        var gamesIds = new ArrayList<Long>(cartItems.size());
        for (var cartItem : cartItems) gamesIds.add(cartItem.getItemId());
        var games = gameRepository.findByIdIn(gamesIds);

        var completeCart = new CartWithItems();
        completeCart.setItemsQuantity(cart.getItemsQuantity());
        completeCart.setUserId(cart.getUserId());
        completeCart.setItems(games);
        return completeCart;
    }

    @Transactional
    public void addItem(long itemId) {
        if (!gameRepository.existsById(itemId)) throw new NotFoundException();
        if (userGameRepository.existsByGameIdAndUserId(itemId, authContext.getUserId()))
            throw new GameAlreadyPurchasedException();

        var cart = get();
        var cartItem = new CartItem();
        cartItem.setCartId(cart.getId());
        cartItem.setItemId(itemId);
        cartItemRepository.save(cartItem);

        cart.setItemsQuantity(cart.getItemsQuantity() + 1);
        cartRepository.save(cart);
    }

    @Transactional
    public void removeItem(long itemId) {
        if (gameRepository.existsById(itemId)) throw new NotFoundException();
        var cart = get();

        var cartItem = cartItemRepository.findByItemIdAndCartId(itemId, cart.getId());
        cartItemRepository.delete(cartItem);

        cart.setItemsQuantity(cart.getItemsQuantity() - 1);
        cartRepository.save(cart);
    }

    @Transactional
    public void clean() {
        var cart = cartRepository.findByUserId(authContext.getUserId());
        var items = cartItemRepository.findByCartId(cart.getId());
        cartItemRepository.deleteAll(items);

        cart.setItemsQuantity(0);
        cartRepository.save(cart);
    }

    @Transactional
    public Cart create() {
        var cart = new Cart();
        cart.setUserId(authContext.getUserId());
        cartRepository.save(cart);
        return cart;
    }
}
