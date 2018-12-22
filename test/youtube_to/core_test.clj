(ns youtube-to.core-test
  (:require [clojure.test :refer :all]
            [youtube-to.core :as core]
            [ring.mock.request :as mock]))

(def request-body
    "<feed xmlns:yt=\"http://www.youtube.com/xml/schemas/2015 xmlns=\"http://www.w3.org/2005/Atom\"> <link rel=\"hub\" href=\"https://pubsubhubbub.appspot.com\"/> <link rel=\"self\" href=\"https://www.youtube.com/xml/feeds/videos.xml?channel_id=CHANNEL_ID\"/> <title>YouTube video feed</title> <updated>2015-04-01T19:05:24.552394234+00:00</updated>
       <entry>
         <id>yt:video:VIDEO_ID</id>
         <yt:videoId>VIDEO_ID</yt:videoId>
         <yt:channelId>CHANNEL_ID</yt:channelId>
         <title>Video title</title>
         <link rel=\"alternate\" href=\"http://www.youtube.com/watch?v=VIDEO_ID\"/>
         <author>
           <name>Channel title</name>
           <uri>http://www.youtube.com/channel/CHANNEL_ID</uri>
         </author>
         <published>2015-03-06T21:40:57+00:00</published>
         <updated>2015-03-09T19:05:24.552394234+00:00</updated>
      </entry>
    </feed>")

(deftest subscription-test
  (testing "Test POST request for handling subscription"
    (with-redefs-fn {#'core/send-video-data-to-services (fn[url title] nil)}
      #(def response (core/app (-> (mock/request :post "/api/v1/subscription/")
                                   (mock/content-type "text/plain")
                                   (mock/body request-body)))))
    (is (= (:status response) 200))))


(deftest test-get-video-data
  (testing "Test getting url and title from request"
    (def request {:body (.getBytes request-body "UTF-8")})
    (def data (core/get-video-data request))
    (is (= (:url data) "Video title"))
    (is (= (:title data) "http://www.youtube.com/watch?v=VIDEO_ID"))
  )) 
