const socket = new WebSocket('ws://localhost:8080/ws');

socket.addEventListener('open', function (event) {
    console.log('WebSocket connected');
    const requestDataItem = {
        sender: "READER",
        action: "IDLE",
        payload: ""
    };

    socket.send(JSON.stringify(requestDataItem));
});

socket.addEventListener('message', function (event) {
    console.log('Message from server:', event.data);
    let ahoj = event.data.split("*");
    const question = document.createElement("p");
    question.innerText = ahoj[0];
    document.getElementById("question").innerHTML = "";
    document.getElementById("question").appendChild(question)

    const answer = document.createElement("p");
    answer.innerText = ahoj[1];
    document.getElementById("answer").innerHTML = "";
    document.getElementById("answer").appendChild(answer)
});

socket.addEventListener('error', function (event) {
    console.error('WebSocket error:', event);
});

socket.addEventListener('close', function (event) {
    console.log('WebSocket closed:', event);
});


document.addEventListener("DOMContentLoaded", function () {
    for( let i = 0; i < 28; i++) {
        const button = document.createElement("button");
        button.innerText = i + 1;
        button.setAttribute("id", (i + 1) + "id")
        document.getElementById("container").appendChild(button);
        button.addEventListener("click", function (event) {
            getQuestionFromServer(button.getAttribute("id"));
        })
    }
})

function getQuestionFromServer(message) {
    const requestDataItem = {
        sender: "READER",
        action: "GET_QUESTION",
        payload: message.slice(0, -2)
    };

    socket.send(JSON.stringify(requestDataItem));
}
