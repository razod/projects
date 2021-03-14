const express = require('express');
const mongoose = require('mongoose');
const cfg = require('./config');

const app = express();
app.use(express.json());

mongoose
    .connect(cfg.mongo, { 
        useNewUrlParser: true,
        useCreateIndex: true
    })
    .then(() => console.log('Connected to mongodb!'))
    .catch(err => console.log(err));

app.use('/api/items', require('./routes/api/items'));

app.listen(cfg.port, () => console.log(`Listening on port ${cfg.port}`));