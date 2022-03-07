import React from "react";

import {useContext} from "react";
import {Box} from "@mui/material";
import {FoobartoryContext} from "./FoobartoryContext";


const Stock = () => {

    const [,stock] = useContext(FoobartoryContext);

    return (
        <Box sx={{ width: '100%' }}>
            <h1>Stocks :</h1>
            <p>Number of Foo : {stock.foo.length}</p>
            <p>Foo's serial number :
                {stock.foo.map((currentFoo) => (
                    currentFoo.serialNumber
                )).join(" , ")}
            </p>
            <p>Number of Bar : {stock.bar.length}</p>
            <p>Bar's serial number :
                {stock.bar.map((currentBar) => (
                    currentBar.serialNumber
                )).join(" , ")}
            </p>
            <p>Number of FooBar : {stock.foobar.length}</p>
            <p>FooBar's serial number :
                {stock.foobar.map((currentFooBar) => (
                    currentFooBar.serialNumber
                )).join(" , ")}
            </p>
            <p>Euros : {stock.euros} €</p>
        </Box>
    );
}
export default Stock;