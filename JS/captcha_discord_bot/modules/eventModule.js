  const run = module.exports = async (client) => {
    require('fs').readdir('events', (err, files) => {
        if (err) return console.error(err);
        files.forEach(file => {
            const eventFunction = require('../events/' + file);
            const eventName = file.split('.')[0];
            client.on(eventName, (...args) => eventFunction(client, ...args));
        });
    });
}