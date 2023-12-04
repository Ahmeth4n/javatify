# Javatify (Spotify Full Mobile API) 


# Important Update Information (05.12.2023)
---
---
- Javatify now `supports mobile streaming`. Streams will be reflected in your statistics as a mobile device. But I do not plan to release this version publicly. Those `who want to buy` can contact me at **ahmeth4n@gmail.com** . 
---
---

**Javatify** is the unofficial API of the reverse-engineered mobile (Android) version of Spotify. Developed with Java - Spring Boot, this project has been turned into a web interface with a simple (*to be honest ugly*) interface.

In the project, **MongoDB** was used as the database and **RabbitMQ** was used for the task queues. You can quickly build and use the project with docker using the `docker-compose` file.

## Features
- Account farming
- Store account sessions - device credentials
- Track - album like
- Artist follow
- Playlist Save
- Proxy support
- Remote server integration (optional)
- ~~Playlist - song stream~~ (please check *Stream Integration* tab)

### Legal Notice
This project is for research purposes only.I am absolutely not responsible for any use in Spotify's favor.

## Screenshots
![Javatify Web Interface!](https://i.imgur.com/ELMCRbm.png "Javatify Web Interface")
![Javatify Web Interface!](https://i.imgur.com/kXfAcAq.png "Javatify Web Interface")

### Task Formats
Spotify uses short URLs `spotify:artist:xxxxxxxx` -` spotify:playlist:xxxxxx` in its apps. When creating a new task in the panel, you must fill in the task url input as `spotify:TASKTYPE:xxxxxx`
for `FOLLOW - LIKE - SAVE` task types.

#### Example usage (for Follow - Like - Save tasks)
```spotify:artist:4kzJtf1HMdOfQgr0B5vqua``` - `Correct` usage

```https://open.spotify.com/artist/4kzJtf1HMdOfQgr0B5vqua``` - `Wrong` usage

### Proxy Formats
Proxies are kept in this format in the Settings tab in the interface;

```USERNAME:PASSWORD:HOST:PORT```

The proxies you add in this format will be used when running the tasks with the Proxy status `True`.
When adding proxies, remember that after each proxy you need to skip to a `new line!`

## Donations
If the project worked for you and you want to support me, you can buy me a coffee as much as you want by clicking the **"Buy me a coffee"** button below :=)

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/fbyte)


### Stream Integration

(updated on 05.12.2023)

Javatify now `supports mobile streaming`. Streams will be reflected in your statistics as a mobile device. But I do not plan to release this version publicly. Those `who want to buy` can contact me at **ahmeth4n@gmail.com** . 


