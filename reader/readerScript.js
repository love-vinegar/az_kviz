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
    let p = document.createAttribute("p")
    p.innerText = event.data;
    document.body.appendChild(p);
});

socket.addEventListener('error', function (event) {
    console.error('WebSocket error:', event);
});

socket.addEventListener('close', function (event) {
    console.log('WebSocket closed:', event);
});
