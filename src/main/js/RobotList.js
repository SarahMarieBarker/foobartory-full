import React from "react";

import {useContext} from "react";
import {Box} from "@mui/material";
import {FoobartoryContext} from "./FoobartoryContext";

const Robot = (props) => {
    return (
        <Box
            sx={{
                backgroundColor: 'white',
                color: 'black',
                border: "solid",
                width: "300px",
                height: "200px",
                padding: "5px"
            }}
        >
            <p>Robot ID : {props.robot.serialNumber}</p>
            <p>Is running : {props.robot.running ? "TRUE" : "FALSE"}</p>
            <p>Is changing action : {props.robot.waiting ? "TRUE" : "FALSE"}</p>
            <p>Current action : {props.robot.currentAction}</p>

        </Box>
    );
}

const RobotList = () => {

    const [robots,] = useContext(FoobartoryContext);

    return (
        <>
            <h1>Robots ( {robots.length} / 30 ) :</h1>
            <Box sx={{ height: '100%', width: '100%', display: 'grid', justifyContent: "center", alignItems: "center", gridTemplateColumns: 'repeat(5, 1fr)', gap: 1 }}>
                {robots.map((robot,index) => (
                    <Robot key={index} robot={robot}/>
                ))}
            </Box>
        </>
    );
}
export default RobotList;