;; Anything you type in here will be executed
;; immediately with the results shown on the
;; right.
(require '[bgg-parser.game :refer :all]
         '[clojure.zip :as c-zip])

(def ex (c-zip/vector-zip [1 [2 3] [4 5] 6]))
ex
(c-zip/down ex)


(game->map (ids->game-maps [1 2]))

 (map #(:id (:attrs %)) (c-zip/children (ids->game-maps [1 2])))