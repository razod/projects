const read = require('fs-readdir-recursive');

const run = module.exports = async (client, msg) => {
    var handled = false;
    const args = msg.content.trim().slice(client.config.prefix.length).split(' ');
    const reqCmd = args.shift().toLowerCase();
    try {
        const files = read('commands');
        files.forEach(file => {
            if (!file.includes('category.json')) {
                const meta = require('../commands/' + file).meta;
                if (meta.aliases.map(c => c.toLowerCase()).includes(reqCmd)) {
                    if (meta.ownerOnly == true && msg.author.id !== client.config.ownerId) {
                        handled = true;
                        return console.log('Found command but didn\'t execute due to user having insufficient permissions.'.red);
                    }
                    console.log('Command found; executing command '.green + meta.aliases[0].green.bold + ' now...'.green);
                    handled = true;
                    return require('../commands/' + file).run(client, msg, args);
                }
            }
        })
    } catch (err) {
        msg.channel.send('An error occurred.');
        console.error(err);
    }
}