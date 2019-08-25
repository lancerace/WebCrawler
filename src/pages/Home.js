import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { shadows } from "@material-ui/system";
import Box from "@material-ui/core/Box";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Divider from '@material-ui/core/Divider';
import Grid from "@material-ui/core/Grid";
import axios from "axios";
import Typography from "@material-ui/core/Typography";

const useStyles = makeStyles(theme => ({
  root: {
    width: "100%",
    marginTop: "10px",
    maxWidth: 360,
    overflow: "auto",
    maxHeight: 500,
    backgroundColor: theme.palette.background.paper
  }
}));

export default function Home() {
  const classes = useStyles();
  const [data, setData] = useState({ data: [] });

  useEffect(() => {
    const fetchData = async () => {
      const result = await axios.post(
        "http://localhost:8080/api/getStockData",
        {
          headers: { "Access-Control-Allow-Origin": "*" }
        }
      );
      setData(result);
    };

    fetchData();
  }, []);
  console.log(data.data);
  const stock = data.data;
  const liItems = stock.map((stock, index) => (
    //in actual implementation,do not use array index as key.
    <React.Fragment key={index}>
      <ListItem>
       <ListItemText
         primary={
           <React.Fragment>
              <Typography color="primary">{stock.company}</Typography>
              {stock.price}
            </React.Fragment>
          }
         secondary={stock.percentage}
       />
       </ListItem>
       <Divider/>
    </React.Fragment>
  ));
  return (
    <Grid
      container
      style={{
        border: "0px solid red",
        height: "40vh"
      }}>
      <Grid
        container
        item
        justify="center"
        md={12}
        style={{ height: "300px", border: "0px solid green" }}>
        <Box boxShadow={1} clone>
          <List className={classes.root}>{liItems}</List>
        </Box>
      </Grid>
    </Grid>
  );
}
