(ns bgg-parser.core)

(require '[clj-http.lite.client :as client]
         '[clojure.xml :as c-xml]
         '[clojure.data.xml :as c-d-xml :refer [parse]]
         '[clojure.zip :as c-zip :refer [xml-zip]]
         '[clojure.data.zip :as c-d-zip]
         '[clojure.data.zip.xml :as c-d-z-xml :refer [xml-> xml1-> attr attr= text]]
         '[clojure.java.io :as io]
         '[clojure.pprint :as print])

(defonce die-macher (client/get "http://www.boardgamegeek.com/xmlapi2/thing?id=1,type=boardgame" {:as :byte-array }))
(defonce games (client/get "http://www.boardgamegeek.com/xmlapi2/thing?id=1,2,type=boardgame" {:as :byte-array }))
(def d-body (c-xml/parse (java.io.ByteArrayInputStream. (:body die-macher))))
(def games-body (parse (java.io.ByteArrayInputStream. (:body games))))




