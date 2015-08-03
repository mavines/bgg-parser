(ns bgg-parser.core)

(require '[clj-http.lite.client :as client]
         '[clojure.xml :as xml]
         '[clojure.zip :as zip]
         '[clojure.java.io :as io])

(def die-macher (client/get "http://www.boardgamegeek.com/xmlapi2/thing?id=1,type=boardgame" {:as :byte-array }))

(defn zip-str [s]
  (zip/xml-zip
   (xml/parse (java.io.ByteArrayInputStream. (.getBytes s)))))

(def dm-tree (zip/xml-zip (xml/parse (java.io.ByteArrayInputStream. (:body die-macher)))))

(zip/root dm-tree)
