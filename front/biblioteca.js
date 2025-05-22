let idEmEdicao = null;
let jogos = [];
let carrinho = [];

window.onload = () => {
    renderizarTodosOsJogos();
    atualizarCarrinho();
};

function renderizarTodosOsJogos() {
    document.querySelector(".games-grid").innerHTML = "";
    jogos.forEach((jogo, index) => renderGameCard(jogo, index));
}

function renderGameCard(jogo, index) {
    const card = document.createElement("div");
    card.classList.add("game-card");
    card.setAttribute("data-index", index);
    card.setAttribute("onclick", `openGamePopup('${jogo.nome}', '${jogo.imagem}', '${jogo.genero}')`);
    card.innerHTML = `
        <img src="${jogo.imagem}" alt="${jogo.nome}">
        <h3>${jogo.nome}</h3>
        <p>${jogo.genero}</p>
        <button onclick="event.stopPropagation(); addToCart(${index})">Adicionar ao Carrinho</button>
        <div class="card-actions">
            <button class="edit-btn" onclick="event.stopPropagation(); editGame(${index})">
                <i class="bi bi-pencil-square"></i>
            </button>
            <button class="delete-btn" onclick="event.stopPropagation(); deleteGame(${index})">
                <i class="bi bi-trash-fill"></i>
            </button>
        </div>
    `;
    document.querySelector(".games-grid").appendChild(card);
}

function addNewGame(event) {
    event.preventDefault();

    const nome = document.getElementById("gameName").value;
    const genero = document.getElementById("gameGenre").value;
    const imagem = document.getElementById("gameImage").value;

    const jogo = { nome, genero, imagem };

    if (idEmEdicao !== null) {
        jogos[idEmEdicao] = jogo;
        idEmEdicao = null;
    } else {
        jogos.push(jogo);
    }

    document.getElementById("addGameForm").reset();
    renderizarTodosOsJogos();
}

function deleteGame(index) {
    jogos.splice(index, 1);
    renderizarTodosOsJogos();
}

function editGame(index) {
    const jogo = jogos[index];
    document.getElementById("gameName").value = jogo.nome;
    document.getElementById("gameGenre").value = jogo.genero;
    document.getElementById("gameImage").value = jogo.imagem;
    idEmEdicao = index;
}

function addToCart(index) {
    carrinho.push(jogos[index]);
    atualizarCarrinho();
}

function atualizarCarrinho() {
    document.querySelector(".cart-count").innerText = carrinho.length;

    const lista = document.getElementById("cartItems");
    lista.innerHTML = "";
    carrinho.forEach((jogo, i) => {
        const li = document.createElement("li");
        li.innerHTML = `<strong>${jogo.nome}</strong> - ${jogo.genero}`;
        lista.appendChild(li);
    });
}

function openGamePopup(nome, imagem, genero) {
    document.getElementById("popupImage").src = imagem;
    document.getElementById("popupTitle").textContent = nome;
    document.getElementById("popupGenre").textContent = genero;
    document.getElementById("popup").style.display = "flex";
}

function closeGamePopup() {
    document.getElementById("popup").style.display = "none";
}

function openCart() {
    document.getElementById("cartSidebar").classList.add("open");
}

function closeCart() {
    document.getElementById("cartSidebar").classList.remove("open");
}
