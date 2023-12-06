(ns day2
  (:require [clojure.string :as s])
  (:require [common :refer [parse-int]]))

(def input "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
           Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
           Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
           Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
           Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")

(defn find-colors [in]
  [(parse-int (last (re-find #" (\d+) red" in)))
   (parse-int (last (re-find #" (\d+) green" in)))
   (parse-int (last (re-find #" (\d+) blue" in)))])

(defn find-max [x y]
  [(if (> (nth x 0) (nth y 0)) (nth x 0) (nth y 0))
   (if (> (nth x 1) (nth y 1)) (nth x 1) (nth y 1))
   (if (> (nth x 2) (nth y 2)) (nth x 2) (nth y 2))])

(comment
  (->> (s/split (slurp "day2.txt") #"\n")
       (map #(s/split % #":"))
       (map (fn [x] (map #(s/split % #";") x)))
       (map (fn [x] {:id (parse-int (last (s/split (first (first x)) #" ")))
                     :colors (->> (last x)
                                  (map find-colors)
                                  (map (fn [y] (map #(if (nil? %) 0 %) y)))
                                  (reduce find-max))}))
       (filter #(let [colors (get % :colors)]
                  (and
                   (<= (nth colors 0) 12)
                   (<= (nth colors 1) 13)
                   (<= (nth colors 2) 14))))
       (map #(get % :id))
       (reduce +))
  
  (->> (s/split (slurp "day2.txt") #"\n")
       (map #(s/split % #":"))
       (map (fn [x] (map #(s/split % #";") x)))
       (map (fn [x] {:id (parse-int (last (s/split (first (first x)) #" ")))
                     :colors (->> (last x)
                                  (map find-colors)
                                  (map (fn [y] (map #(if (nil? %) 0 %) y)))
                                  (reduce find-max))}))
       (map #(get % :colors))
       (map #(reduce * %))
       (reduce +))
  )
