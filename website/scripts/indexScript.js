const socket = new WebSocket('ws://localhost:8080/ws');

socket.addEventListener('open', function (event) {
    console.log('WebSocket connected');
    socket.send('board');
});

socket.addEventListener('message', function (event) {
    console.log('Message from server:', event.data);
});

socket.addEventListener('error', function (event) {
    console.error('WebSocket error:', event);
});

socket.addEventListener('close', function (event) {
    console.log('WebSocket closed:', event);
});


document.addEventListener("DOMContentLoaded", function () {
    const container = document.getElementById("container");

    const tempDiv = document.createElement("div");
    tempDiv.classList.add("question");
    tempDiv.style.visibility = "hidden";
    container.appendChild(tempDiv);

    const questionWidth = parseFloat(getComputedStyle(tempDiv).width);
    const questionMargin = 5;
    container.removeChild(tempDiv);

    const totalRows = 8;

    let index = 1;
    for (let i = 0; i < totalRows; i++) {
        const itemsInRow = i;
        const rowWidth = itemsInRow * (questionWidth + questionMargin);
        const containerMid = container.getBoundingClientRect().width / 2;
        const rowStartX = containerMid - rowWidth / 2;

        for (let j = 0; j < itemsInRow; j++) {
            const div = document.createElement("div");
            div.classList.add("question");
            div.textContent = index;
            index++;

            const x = rowStartX + j * (questionWidth + questionMargin);
            const y = (i * (questionWidth + questionMargin)) - (i * (23));

            div.style.setProperty('--left', `${x}px`);
            div.style.setProperty('--top', `${y}px`);


            container.appendChild(div);

            div.setAttribute("id", i + ";" + j);

            div.addEventListener("click", function (event) {
                if(div.classList.contains("active")) {
                    resetAnimation()
                    return;
                }
                activate(div.getAttribute("id"))
                sendMessage(div.getAttribute("id"));
            })
        }
    }
});

function sendMessage(message) {
   socket.send(JSON.stringify(message));
}

function activate(id) {
    document.querySelectorAll('.question').forEach(el => {
        if (el.id === id) {
            el.classList.add('active');
            el.classList.remove('inactive');


        } else {
            el.classList.add('inactive');
            el.classList.remove('active');
        }
    });
}

function resetAnimation() {
    document.querySelectorAll('.question').forEach(el => {
        el.classList.remove('active', 'inactive');
    });
}

