document.addEventListener('DOMContentLoaded', () => {
    const favorites = JSON.parse(localStorage.getItem('favorites')) || [];
    const favoritesList = document.getElementById('favoriteGames');

    favoritesList.innerHTML = '';

    if (favorites.length === 0) {
        favoritesList.innerHTML = '<li>Você ainda não favoritou nenhum jogo.</li>';
    } else {
        favorites.forEach(game => {
            const favoriteItem = document.createElement('li');
            favoriteItem.textContent = game;
            favoritesList.appendChild(favoriteItem);
        });
    }
});