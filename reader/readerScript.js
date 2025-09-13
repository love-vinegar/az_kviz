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

    if(message.action === "GET_QUESTION") {
        let payload = message.payload.split("*");
        const question = document.createElement("p");
        question.innerText = payload[0];
        document.getElementById("question").innerHTML = "";
        document.getElementById("question").appendChild(question)

        const answer = document.createElement("p");
        answer.innerText = payload[1];
        document.getElementById("answer").innerHTML = "";
        document.getElementById("answer").appendChild(answer)
    } else if (message.action === "MARK_FIELD") {
        document.getElementById(activeField + "id").classList.remove("gray")
        document.getElementById(activeField + "id").classList.add(message.payload)
    } else if (message.action === "WIN") {
        document.body.innerText = "WIN"
    }
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

function markField(event) {
   let value = event.target.getAttribute("id");
   if(activeField == null) {
       return;
   }

    const requestDataItem = {
        sender: "READER",
        action: "MARK_FIELD",
        payload: value
    };

    socket.send(JSON.stringify(requestDataItem));
}
