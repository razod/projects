const express = require('express');
const app = express();
const sa = require('superagent');
const fs = require('fs');
app.use(express.json());
app.use(express.urlencoded({
    extended: true
}));

app.get('/', (req, res) => {
    res.sendFile(__dirname + "/views/index.html");
});

app.get('/users', (req, res) => {
  let names = JSON.parse(fs.readFileSync(`./names.json`, "utf-8"));
  res.send(names);
});

app.post('/adduser', async (request, response) => {
    var usr = request.body.user;
    console.log(request.body)
    const { body } = await sa.get(`https://api.mojang.com/users/profiles/minecraft/${usr}`);
            if(!body.id) {
                response.status(400).send('Username Invalid');
                return;
           } else {
             let names = JSON.parse(fs.readFileSync(`./names.json`, "utf-8"));
              let array = names;
              array.push(request.body["username"].replace("(", "").replace(")", ""));
              fs.writeFileSync(`./names.json`, JSON.stringify(array, null, 4), err => {
                if (err) throw err;
              });
              response.send(
                "Name added! Try relogging. If it still doesn't work contact the owner."
              );
        }});

app.listen(4000, () => console.log('api is online'));