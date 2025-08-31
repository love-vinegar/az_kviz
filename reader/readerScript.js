const socket = new WebSocket('ws://localhost:8080/ws');

let activeField;

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
    let message = JSON.parse(event.data);
    let payload = message.payload.split("*");
    const question = document.createElement("p");
    question.innerText = payload[0];
    document.getElementById("question").innerHTML = "";
    document.getElementById("question").appendChild(question)

    const answer = document.createElement("p");
    answer.innerText = payload[1];
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
    activeField = message.slice(0, -2);
    const requestDataItem = {
        sender: "READER",
        action: "GET_QUESTION",
        payload: activeField
    };

    socket.send(JSON.stringify(requestDataItem));
}

function markField(value) {
   if(activeField == null) {
       return;
   }

   document.getElementById(value+"id").classList.add(value)

    const requestDataItem = {
        sender: "READER",
        action: "MARK_FIELD",
        payload: activeField + "*" + value
    };

    socket.send(JSON.stringify(requestDataItem));
}
