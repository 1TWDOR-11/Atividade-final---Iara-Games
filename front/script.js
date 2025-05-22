let cartCount = 0;
const cartItems = [];

// Abrir carrinho lateral
function openCart() {
    document.getElementById('cartSidebar').classList.add('open');
}

// Fechar carrinho lateral
function closeCart() {
    document.getElementById('cartSidebar').classList.remove('open');
}

// Abrir popup com informações do jogo
function openGamePopup(gameName, imagePath, genre) {
    const popup = document.getElementById("popup");
    const popupImage = popup.querySelector("img");
    const popupTitle = popup.querySelector("h2");
    const popupGenre = popup.querySelector("p");

    popupImage.src = imagePath;
    popupImage.alt = gameName;
    popupTitle.textContent = gameName;
    popupGenre.textContent = genre;

    popup.style.display = "flex";
}

function closeGamePopup() {
    document.getElementById("popup").style.display = "none";
}


// Fechar popup do jogo
function closeGamePopup() {
    document.getElementById('popup').style.display = 'none';
}

// Adicionar jogo ao carrinho e atualizar visualização
function addToCart(event, gameName) {
    event.stopPropagation();

    cartItems.push(gameName);
    cartCount = cartItems.length;
    document.querySelector('.cart-count').textContent = cartCount;

    const cartList = document.getElementById('cartItems');
    const newItem = document.createElement('li');
    newItem.textContent = gameName;
    cartList.appendChild(newItem);

    alert(`${gameName} adicionado ao carrinho!`);
}

// Impedir que o clique do botão ative o card inteiro
document.querySelectorAll('.game-card button').forEach(button => {
    button.addEventListener('click', event => event.stopPropagation());
});

// Função para favoritar jogos e salvar no localStorage
function toggleFavorite(event, gameName) {
    event.stopPropagation();

    let favorites = JSON.parse(localStorage.getItem('favorites')) || [];

    if (favorites.includes(gameName)) {
        favorites = favorites.filter(fav => fav !== gameName);
        event.target.classList.remove('bi-heart-fill');
        event.target.classList.add('bi-heart');
        alert(`${gameName} removido dos favoritos.`);
    } else {
        favorites.push(gameName);
        event.target.classList.remove('bi-heart');
        event.target.classList.add('bi-heart-fill');
        alert(`${gameName} adicionado aos favoritos!`);
    }

    localStorage.setItem('favorites', JSON.stringify(favorites));
}

// Carregar favoritos já salvos ao abrir a biblioteca
document.addEventListener('DOMContentLoaded', () => {
    const favorites = JSON.parse(localStorage.getItem('favorites')) || [];
    document.querySelectorAll('.game-card').forEach(card => {
        const gameName = card.querySelector('h3').textContent;
        const heartIcon = card.querySelector('.favorite-icon');
        if (favorites.includes(gameName)) {
            heartIcon.classList.remove('bi-heart');
            heartIcon.classList.add('bi-heart-fill');
        }
    });
});
