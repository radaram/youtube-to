(ns youtube-to.subscription 
  (:require 
    [clojure.string :as str]   
    ;[environ.core :refer [env]]
    [clj-http.client :as client]))

(def server-name (System/getenv "SERVER_NAME"))
(def subscribe-url "https://pubsubhubbub.appspot.com/subscribe")
(def callback-url (str server-name "/api/v1/subscription/"))
(def topic-url "https://www.youtube.com/xml/feeds/videos.xml?channel_id=")
(def channel-ids (str/split (or (System/getenv "CHANNEL_IDS") "") #","))
  
(defn request 
  [url, method, data]
  (client/post 
    url
    {:content-type :application/x-www-form-urlencoded
    :form-params data}))


(defn subscribe-channel 
  [channel-id, subscribe-url, callback-url, topic-url] 
  (def data {:hub.mode "subscribe"
             :hub.callback callback-url
             :hub.lease_seconds 200
             :hub.topic (str topic-url channel-id)})
  (let 
    [res (request subscribe-url, "post", data)] 
    (println res))) 


(defn -main 
  []
  (doseq 
    [channel-id, channel-ids]
    (subscribe-channel channel-id, subscribe-url, callback-url, topic-url)))
  


