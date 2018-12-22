(ns youtube-to.telegram
  (:require
    [clojure.string :as str]
    [clj-http.client :as client]
    [environ.core :refer [env]]
    [clojure.data.json :as json]))

(def bot-token (env :telegram-bot-token))
(def chat-id (env :telegram-chat-id))
(def telegram-base-url "https://api.telegram.org")
(def send-msg-url (str telegram-base-url "/bot" bot-token "/sendmessage"))

(defn send-to-chat
  [msg]
  (def body (json/write-str {:text msg :chat_id chat-id}))
  (client/post
    send-msg-url
    {:content-type :json 
     :body body})
  )

(defn -main
  []
  (println send-msg-url)
  (send-to-chat "test message"))
