(ns bgg-parser.game-test)

(require '[clojure.test :refer :all]
         '[bgg-parser.game :refer :all])

;(defonce single-game (client/get "http://www.boardgamegeek.com/xmlapi2/thing?id=1,type=boardgame" {:as :byte-array }))
;(defonce multiple-games (client/get "http://www.boardgamegeek.com/xmlapi2/thing?id=1,2,type=boardgame" {:as :byte-array }))

(is (= "http://www.boardgamegeek.com/xmlapi2/thing?id=1,type=boardgame"
       (id->url 1)))

(is (= "http://www.boardgamegeek.com/xmlapi2/thing?id=1,2,3,type=boardgame"
       (ids->url [1 2 3])))

