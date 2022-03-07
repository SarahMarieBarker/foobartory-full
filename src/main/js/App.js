import React from "react";

import {FoobartoryProvider} from "./FoobartoryContext";
import {Box} from "@mui/material";
import RobotList from "./RobotList";
import Stock from "./Stock";


function App() {
  return (
    <FoobartoryProvider>
      <div className="App">
        <Box
          sx={{
            height: '100%',
            display: 'grid',
            gridTemplateColumns: 'repeat(3, 1fr)',
            gridTemplateRows: '400px 1fr',
            gridTemplateAreas:
                    `"component1 component1 component1"
                    "mainComponent mainComponent mainComponent"`,
          }}
        >
            <Box sx={{ gridArea: 'component1'}}><Stock/></Box>
            <Box sx={{ gridArea: 'mainComponent'}}><RobotList/></Box>

        </Box>
      </div>
    </FoobartoryProvider>
  );
}

export default App;
