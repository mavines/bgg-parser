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



(defn game->map [z-game]
    {:name (xml1-> z-game :name (attr :value))
     :id (xml1-> z-game (attr :id))
     :thumbnail (xml1-> z-game :thumbnail text)
     :image (xml1-> z-game :image text)
     :description (xml1-> z-game :description text)
     :yearpublished (xml1-> z-game :yearpublished (attr :value))
     :minplayers (xml1-> z-game :minplayers (attr :value))
     :maxplayers (xml1-> z-game :maxplayers (attr :value))
     :playingtime (xml1-> z-game :playingtime (attr :value))
     :minplaytime (xml1-> z-game :minplaytime (attr :value))
     :maxplaytime (xml1-> z-game :maxplaytime (attr :value))
     :suggested-players (xml-> z-game :poll (attr= :name "suggested_numplayers") :results build-suggest-players)
     :suggested-playerage (xml-> z-game :poll (attr= :name "suggested_playerage") :results :result build-suggest-age)
     :categories (xml-> z-game :link (attr= :type "boardgamecategory") link-details)
     :mechanics (xml-> z-game :link (attr= :type "boardgamemechanic") link-details)
     :families (xml-> z-game :link (attr= :type "boardgamefamily") link-details)
     :designers (xml-> z-game :link (attr= :type "boardgamedesigner") link-details)
     :artists (xml-> z-game :link (attr= :type "boardgameartist") link-details)
     :publishers (xml-> z-game :link (attr= :type "boardgamepublisher") link-details)
     })

(defn link-details [link]
  {:id (xml1-> link (attr :id))
   :name (xml1-> link (attr :value))
   })

(defn build-suggest-players [results]
  {:numplayers (xml1-> results (attr :numplayers))
   :best (xml1-> results :result (attr= :value "Best") (attr :numvotes))
   :recommended (xml1-> results :result (attr= :value "Recommended") (attr :numvotes))
   :not-recommended (xml1-> results :result (attr= :value "Not Recommended") (attr :numvotes))
  })

(defn build-suggest-age [result]
  {:age (xml1-> result (attr :value))
   :votes (xml1-> result (attr :numvotes))
  })


