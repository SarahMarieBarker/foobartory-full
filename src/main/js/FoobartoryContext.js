import React, {useState, createContext, useCallback, useEffect} from "react";
import { Client } from '@stomp/stompjs';

const FoobartoryContext = createContext();

const FoobartoryProvider = (props) => {
    const [robots, setRobots] = useState([]);
    const [stock, setStock] = useState({foo: [], bar: [], foobar: [], euros : 0});

    let stompClient;
    useEffect(() => {
        stompClient = new Client({
            brokerURL: 'ws://localhost:8080/foobartory',
            debug: function (str) {
                //console.log(str);
            },
            reconnectDelay: 5000,
            heartbeatIncoming: 4000,
            heartbeatOutgoing: 4000,
        });
        stompClient.onConnect = function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/user/queue/robot', function (message) {
                updateRobot(JSON.parse(message.body));
            });
            stompClient.subscribe('/user/queue/stock', function (stock) {
                updateStock(JSON.parse(stock.body));
            });
            stompClient.publish({destination: '/app/start', body: JSON.stringify('test')});
        };
        stompClient.activate();
    },[]);

    const updateRobot = useCallback(
        robot => {
            let currentRobotList = robots;
            if(currentRobotList.filter((currentRobot) =>
                currentRobot.serialNumber === robot.serialNumber).length === 0){
                    currentRobotList.push(robot);
                    setRobots(currentRobotList);
            }else{
                const newState = currentRobotList.map(currentRobot =>
                    currentRobot.serialNumber === robot.serialNumber ? robot : currentRobot
                );
                setRobots(newState);
            }
        },
        [setRobots,robots]
    );

    const updateStock = useCallback(
        newStock => {
            console.log(newStock);
            setStock(newStock);
        },
        [setStock]
    );

    return (    
        <FoobartoryContext.Provider value={[robots,stock]}>
            {props.children}
        </FoobartoryContext.Provider>
    );
};
export { FoobartoryContext, FoobartoryProvider };