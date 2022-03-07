import { Client } from '@stomp/stompjs';

let stompClient = null;

function connect() {
    stompClient = new Client({
        brokerURL: 'ws://localhost:8080/foobartory',
        debug: function (str) {
            console.log(str);
        },
        reconnectDelay: 5000,
        heartbeatIncoming: 4000,
        heartbeatOutgoing: 4000,
    });
    stompClient.onConnect = function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/queue/robot', function (greeting) {
            showGreeting(greeting.body);
        });
        stompClient.subscribe('/user/queue/stock', function (greeting) {
            showGreeting(greeting.body);
        });
        start();
    };
    stompClient.activate();
}

function start() {
    //stompClient.send('/app/start', {}, JSON.stringify('test'));
}

function showGreeting(message) {
    console.log(message);
}

//connect();