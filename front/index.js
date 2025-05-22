const db = firebase.database();
let usuario = prompt("Digite seu nome de usuário:") || "Anônimo";

function toggleChat() {
    const chat = document.getElementById("chatBox");
    chat.style.display = chat.style.display === "flex" ? "none" : "flex";
}

function sendMessage() {
    const input = document.getElementById("chatInput");
    const mensagem = input.value.trim();
    const topico = document.getElementById("chatTopic").value;

    if (mensagem) {
        const hora = new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
        db.ref("chats/" + topico).push({
            usuario: usuario,
            mensagem: mensagem,
            horario: hora
        });
        input.value = "";
    }
}

function carregarMensagens() {
    const mensagensDiv = document.getElementById("chatMessages");
    mensagensDiv.innerHTML = "";
    const topico = document.getElementById("chatTopic").value;

    db.ref("chats/" + topico).off(); // remove listeners anteriores para evitar duplicidade

    db.ref("chats/" + topico).on("child_added", snapshot => {
        const msg = snapshot.val();
        const div = document.createElement("div");
        div.innerHTML = `<strong>${msg.usuario}</strong> <small>(${msg.horario})</small>: ${formatarMensagem(msg.mensagem)}`;
        mensagensDiv.appendChild(div);
        mensagensDiv.scrollTop = mensagensDiv.scrollHeight;
    });
}

function formatarMensagem(texto) {
    return texto
        .replace(/:joy:/g, "😂")
        .replace(/:fire:/g, "🔥")
        .replace(/:heart:/g, "❤️");
}

window.onload = carregarMensagens;
