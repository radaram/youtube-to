# youtube-to
------------------------

Notification about new YouTube video in services.
Currently only telegram is supported.
Used by PubSubHubbub protocol specification.

------------------------

## Quick Start Guide

Step 1
-----------------------
Create telegram bot: https://core.telegram.org/bots


Step 2
-----------------------
Сreate environment variables
* `BASE_URL` - a url for webhook processing of up PubSubHubbub
* `CHANNEL_IDS` - YouTube channel IDs separated by commas
* `TELEGRAM_BOT_TOKEN` - a token of your telegram bot
* `TELEGRAM_CHAT_ID` - ID of your telegram chat

Step 3
-----------------------
Subscribe to channels
```
$ lein run -m youtube-to.subscription
```

Step 4
-----------------------
Start the server
```
$ lein ring server
```
