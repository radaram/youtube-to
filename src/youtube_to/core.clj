(ns youtube-to.core 
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [crouton.html :as html]
            [youtube-to.telegram :as telegram]))


(defn get-video-data
  [request]
  (def str-body (slurp (:body request)))
  (def result (html/parse-string str-body))
  (def content (((((((result :content) 1) :content) 0) :content) 9) :content))
  (def title (((content 7) :content) 0))
  (def url (((content 9) :attrs) :href))
  {:url title :title url})

(defn send-video-data-to-services
  [title, url]
  (telegram/send-to-chat (str (title "\n" url))))

(defn subscription 
  [request] 
  (def data (get-video-data request))
  (def url (data :url))
  (def title (data :title))
  (send-video-data-to-services url title)
  {:status 200, :headers {"Content-Type" "application/json"} :body "{}"})

(defroutes app-routes
  (POST "/api/v1/subscription/" request (subscription request))
  (route/not-found "<h1>Page not found</h1>"))


(def app
  (handler/site app-routes))

