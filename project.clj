(defproject youtube-to "0.1.0-SNAPSHOT"
  :description "Notification about new YouTube video in services"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"],
                 [clj-http "3.9.1"], 
                 [ring/ring-core "1.6.3"], 
                 [ring/ring-jetty-adapter "1.6.3"], 
                 [ring/ring-mock "0.3.2"],
                 [compojure "1.6.1"], 
                 ;[environ "1.1.0"],
                 [crouton "0.1.2"], 
                 [org.clojure/data.json "0.2.6"]]
  ;:main youtube-to.core
  
  :ring {:handler youtube-to.core/app}
  :plugins [[lein-ring "0.12.1"] 
            [lein-kibit "0.1.6"]]
  :min-lein-version "2.7.1"
  :profiles {:uberjar {:aot :all}})
