require('dotenv').config();
const { Client } = require('discord.js');
const client = new Client();
const createCaptcha = require('./captcha');
client.login(process.env.BOT_TOKEN);
const fs = require('fs');

client.on('ready', () => {
    console.log(`${client.user.tag} has logged in.`)
})

client.on('guildMemberAdd', async member => {
    const captcha = await createCaptcha();
    const guild = member.guild;
    try {
        const msg = await member.send({
            files: [{
                attachment: `${__dirname}/captchas/${captcha}.png`,
                name: `${captcha}.png`
            }]
        });
        fs.unlinkSync(`${__dirname}/captchas/${captcha}.png`)
       try{
            const filter = m => {
                if(m.author.bot) return;
                if(m.author.id === member.id && m.content === captcha) {
                    return true;
                } else {
                    m.channel.send('You entered the captcha incorrectly!');
                    return false;
                }
            }
            const response = await msg.channel.awaitMessages(filter, { max: 1, time: 60000, errors: ['time']});
            if(response) {
                msg.channel.send('You have been verified.');
                const role = await guild.roles.cache.find(r => r.name === 'Verified').id;
                member.roles.add(role);
            }
       }
        catch(err) {
            console.log(err);
            await msg.channel.send('You entered the captcha incorrectly!');
            await member.kick();
        }
    } 
    catch(err) {   
        console.log(err);
    }
});

